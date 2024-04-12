package Sol;

import ErrorUtils.ErrorReporter;
import ErrorUtils.RuntimeError;
import Tasm.*;
import antlrSol.*;
import antlrTasm.TasmLexer;
import antlrTasm.TasmParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class solCompiler extends SolBaseListener
{
    private static final int MAX_ARGUMENTS_SIZE = 5;

    private static final String SOURCE_FILE_FLAG = "-i";
    private static final String SOURCE_FILE_EXTENSION = "sol";
    private static final String BYTECODES_FILE_FLAG = "-o";
    public static final String BYTECODES_FILE_EXTENSION = "tbc";
    private static final String SHOW_ASSEMBLY_FLAG = "--asm";

    private static final String DEFAULT_SOURCE_FILE_NAME = null;
    private static final String DEFAULT_BYTECODES_FILE_NAME = "a.tbc";
    private static final boolean DEFAULT_SHOW_ASSEMBLY = false;

    private String sourceFileName;
    private String byteCodesFileName;
    private boolean showAssembly;

    private TasmParser parser;
    private ArrayList<Instruction> instructions;
    private List<Value> constantPool;
    private HashMap<Value, Integer> constantPoolChecker;
    private HashMap<String, InstructionCode> nameToCode;
    private HashMap<String, Integer> labelToInstruction;
    private ErrorReporter semanticErrorReporter;
    private ParseTreeProperty<Class<?>> annotatedTypes;

    private void parseArguments(String[] args)
    {
        if (args.length > MAX_ARGUMENTS_SIZE)
            RuntimeError.dispatchError("Invalid arguments size of " + args.length);

        for (int i = 0; i < args.length; i++)
            switch (args[i])
            {
                case SOURCE_FILE_FLAG:
                    String filename = args[++i];
                    this.sourceFileName = filename;
                    this.byteCodesFileName = filename.replaceAll("\\." + SOURCE_FILE_EXTENSION, ".tbc");
                    break;
                case BYTECODES_FILE_FLAG:
                    this.byteCodesFileName = args[++i];
                    break;
                case SHOW_ASSEMBLY_FLAG:
                    this.showAssembly = true;
                    break;
                default:
                    RuntimeError.dispatchError("Invalid flag '" + args[i] + "'");
            }

        this.checkFile();
    }

    private void checkFile()
    {
        if (this.sourceFileName != null)
        {
            String[] splitSourceFileName = this.sourceFileName.split("\\.");
            String sourceFileExtension = splitSourceFileName[splitSourceFileName.length - 1];
            if (!sourceFileExtension.equals(SOURCE_FILE_EXTENSION))
                RuntimeError.dispatchError("Invalid source file extension '." + sourceFileExtension + "'");
        }

        String[] splitByteCodesFileName = this.byteCodesFileName.split("\\.");
        String byteCodesFileExtension = splitByteCodesFileName[splitByteCodesFileName.length - 1];
        if (!byteCodesFileExtension.equals(BYTECODES_FILE_EXTENSION))
            RuntimeError.dispatchError("Invalid bytecode file extension '." + byteCodesFileExtension + "'");
    }

    private void initCompiler() throws IOException
    {
        this.parser = this.generateParser();
        this.instructions = new ArrayList<>();
        this.constantPool = new ArrayList<>();
        this.constantPoolChecker = new HashMap<>();
        this.nameToCode = new HashMap<>();
        Arrays.stream(InstructionCode.values()).forEach((code) -> this.nameToCode.put(code.name().toLowerCase(), code));
        this.semanticErrorReporter = new ErrorReporter();
    }

    private TasmParser generateParser() throws IOException
    {
        InputStream inputStream = this.sourceFileName != null ? new FileInputStream(this.sourceFileName) : System.in;
        CharStream input = CharStreams.fromStream(inputStream);
        TasmLexer lexer = new TasmLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new TasmParser(tokens);
    }

    private void assembleInstructions()
    {
        // We walk the tree twice
        // First to check semantic errors and storing all labels
        // Second to assemble code and check for jumps with invalid labels
        ParseTree tree = this.parser.program();
        solTypeAnnotator checker = new solTypeAnnotator(this.semanticErrorReporter);
        checker.annotateTypes(tree);
        this.annotatedTypes = checker.getAnnotatedTypes();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);
    }

    private boolean successfulAssemble()
    {
        return !this.semanticErrorReporter.hasReportedErrors() && !(this.parser.getNumberOfSyntaxErrors() > 0);
    }

    private void unsuccessfulAssemble()
    {
        //ANTLR should print all the syntax errors prior, if any
        System.err.println(this.semanticErrorReporter);

        int semanticErrorCount = this.semanticErrorReporter.getErrorCount();
        int syntaxErrorCount = this.parser.getNumberOfSyntaxErrors();
        String syntaxErrors = syntaxErrorCount == 1 ? " syntax error " : " syntax errors ";
        String semanticErrors = semanticErrorCount == 1 ? " semantic error" : " semantic errors";
        String message = "Program assembled unsuccessfully with " + syntaxErrorCount + syntaxErrors +
                "and " + semanticErrorCount + semanticErrors;

        RuntimeError.dispatchError(message);
    }

    private void writeByteCodes() throws IOException
    {
        DataOutputStream byteCodes = new DataOutputStream(new FileOutputStream(this.byteCodesFileName));
        this.writeInstructions(byteCodes);
        this.writeConstantPool(byteCodes);
        if (this.showAssembly)
            this.printAssembledCode();
    }

    private void writeInstructions(DataOutputStream byteCodes) throws IOException
    {
        for (Instruction instruction : this.instructions)
        {
            byteCodes.writeByte(instruction.getInstruction().ordinal());
            if (instruction.getOperand() != null)
                byteCodes.writeInt(instruction.getOperand());
        }
        byteCodes.writeByte(InstructionCode.END.ordinal());
    }

    private void writeConstantPool(DataOutputStream byteCodes) throws IOException
    {
        for (Value value : this.constantPool)
            if (value.getValue() instanceof Double doubleValue)
            {
                byteCodes.writeByte(TypeCode.DOUBLE.ordinal());
                byteCodes.writeDouble(doubleValue);
            }
            else if (value.getValue() instanceof String string)
            {
                byteCodes.writeByte(TypeCode.STRING.ordinal());
                byte[] stringBytes = string.getBytes(StandardCharsets.UTF_8);
                byteCodes.writeInt(stringBytes.length);
                byteCodes.write(stringBytes);
            }
    }

    private void printAssembledCode()
    {
        System.out.println("ASSEMBLED INSTRUCTIONS:");
        for (int i = 0; i < this.instructions.size(); i++)
            System.out.println(i + 1 + ":\t" + this.instructions.get(i));

        System.out.println("\nASSEMBLED CONSTANT POOL:");
        System.out.println(this.constantPool);
    }

    public void compile(String[] args) throws IOException
    {
        this.parseArguments(args);
        this.initCompiler();
        this.assembleInstructions();
        if (this.successfulAssemble())
            this.writeByteCodes();
        else
            this.unsuccessfulAssemble();
    }

    @Override
    public void exitInt(SolParser.IntContext ctx)
    {
        if(ctx.INT() == null) return;
        this.instructions.add(new Instruction(InstructionCode.ICONST, Integer.valueOf(ctx.getText())));
    }

    @Override
    public void exitDouble(SolParser.DoubleContext ctx)
    {
       if(ctx.DOUBLE() == null)
            return;

        Value doubleValue = new Value(Double.valueOf(ctx.DOUBLE().getText()));
        Integer index = this.constantPoolChecker.get(doubleValue);
        if (index == null)
        {
            this.constantPool.add(doubleValue);
            index = this.constantPool.size() - 1;
            this.constantPoolChecker.put(doubleValue, index);
        }
        this.instructions.add(new Instruction(InstructionCode.DCONST, index));
    }



    @Override
    public void exitString(SolParser.StringContext ctx)
    {
        if (ctx.STRING() == null)
            return;

        String rawString = ctx.STRING().getText();
        String readString = rawString.substring(1, rawString.length() - 1).replaceAll("\\\\([\"\\\\])", "$1");
        Value stringValue = new Value(readString);

        Integer index = this.constantPoolChecker.get(stringValue);
        if (index == null)
        {
            this.constantPool.add(stringValue);
            index = this.constantPool.size() - 1;
            this.constantPoolChecker.put(stringValue, index);
        }
        this.instructions.add(new Instruction(InstructionCode.SCONST, index));
    }

    @Override
    public void exitBoolean(SolParser.BooleanContext ctx)
    {
        if (ctx.BOOLEAN() == null)
            return;
        if(Boolean.parseBoolean(ctx.getText())){
            this.instructions.add(new Instruction(InstructionCode.TCONST));
        }
        else{
            this.instructions.add(new Instruction(InstructionCode.FCONST));
        }
    }

    @Override
    public void exitOr(SolParser.OrContext ctx)
    {
        this.instructions.add(new Instruction(InstructionCode.OR));
    }

    @Override
    public void exitAnd(SolParser.AndContext ctx)
    {
        this.instructions.add(new Instruction(InstructionCode.AND));
    }

    @Override
    public void exitEquality(SolParser.EqualityContext ctx)
    {
        Class<?> leftType = this.annotatedTypes.get(ctx.expr(0));
        Class<?> rightType = this.annotatedTypes.get(ctx.expr(1));
        if (leftType == null || rightType == null)
            return;

        // Alex forgive me
        if(leftType == Double.class || rightType == Double.class){
            if(rightType == Integer.class){
               this.instructions.add(new Instruction(InstructionCode.ITOD));
            }
            if(leftType == Integer.class){ // in this case the stack should have the right operand in it so we have to squeeze a conversion between the two
               this.instructions.add(this.instructions.size()-2, new Instruction(InstructionCode.ITOD));
            }
            this.instructions.add(new Instruction(InstructionCode.DEQ));
        }
        else if(leftType == String.class && rightType == String.class){
            this.instructions.add(new Instruction(InstructionCode.SEQ));
        }
        else if(leftType == Boolean.class && rightType == Boolean.class){
            this.instructions.add(new Instruction(InstructionCode.BEQ));
        }
        else{
            this.instructions.add(new Instruction(InstructionCode.IEQ));
        }
    }

    @Override
    public void exitRelational(SolParser.RelationalContext ctx)
    {
        Class<?> leftType = this.annotatedTypes.get(ctx.expr(0));
        Class<?> rightType = this.annotatedTypes.get(ctx.expr(1));
        if (leftType == null || rightType == null)
            return;

       // Alex forgive me
        if(leftType == Double.class || rightType == Double.class){
            if(rightType == Integer.class){
               this.instructions.add(new Instruction(InstructionCode.ITOD));
            }
            if(leftType == Integer.class){ // in this case the stack should have the right operand in it so we have to squeeze a conversion between the two
               this.instructions.add(this.instructions.size()-2, new Instruction(InstructionCode.ITOD));
            }
            this.instructions.add(new Instruction(InstructionCode.DEQ));
        }
        else if(leftType == String.class && rightType == String.class){
            this.instructions.add(new Instruction(InstructionCode.SEQ));
        }
        else if(leftType == Boolean.class && rightType == Boolean.class){
            this.instructions.add(new Instruction(InstructionCode.BEQ));
        }
        else{
            this.instructions.add(new Instruction(InstructionCode.IEQ));
        }
    }

    @Override
    public void exitAddSub(SolParser.AddSubContext ctx)
    {
        if (ctx.op.getText().equals("+"))
            this.exitAdd(ctx, ctx.expr(0), ctx.expr(1));
        else
            this.exitArithmetic(ctx, ctx.expr(0), ctx.expr(1));
    }

    private void exitAdd(SolParser.AddSubContext ctx, SolParser.ExprContext left, SolParser.ExprContext right)
    {
        Class<?> leftType = this.annotatedTypes.get(left);
        Class<?> rightType = this.annotatedTypes.get(right);
        if (leftType == null || rightType == null)
            return;

        boolean isLeftString = leftType == String.class;
        boolean isRightString = rightType == String.class;
        boolean isLeftNumber = leftType == Integer.class || leftType == Double.class;
        boolean isRightNumber = rightType == Integer.class || rightType == Double.class;
        if ((isLeftString || isRightString) || (isLeftNumber && isRightNumber))
            this.determineAddType(ctx, leftType, rightType);
        else
            this.reporter.reportError(ctx, ERROR_MESSAGE);
    }

    private void determineAddType(SolParser.AddSubContext ctx, Class<?> leftType, Class<?> rightType)
    {
        boolean isLeftString = leftType == String.class;
        boolean isRightString = rightType == String.class;
        boolean isLeftDouble = leftType == Double.class;
        boolean isRightDouble = rightType == Double.class;
        if (isLeftString || isRightString)
            this.annotatedTypes.put(ctx, String.class);
        else if (isLeftDouble || isRightDouble)
            this.annotatedTypes.put(ctx, Double.class);
        else
            this.annotatedTypes.put(ctx, Integer.class);
    }

    private void exitArithmetic(SolParser.ExprContext ctx, SolParser.ExprContext left, SolParser.ExprContext right)
    {
        Class<?> leftType = this.annotatedTypes.get(left);
        Class<?> rightType = this.annotatedTypes.get(right);
        if (leftType == null || rightType == null)
            return;

        boolean isLeftNumber = leftType == Integer.class || leftType == Double.class;
        boolean isRightNumber = rightType == Integer.class || rightType == Double.class;
        if (isLeftNumber && isRightNumber)
            this.determineArithmeticType(ctx, leftType, rightType);
        else
            this.reporter.reportError(ctx, ERROR_MESSAGE);
    }

    private void determineArithmeticType(SolParser.ExprContext ctx, Class<?> leftType, Class<?> rightType)
    {
        boolean isLeftDouble = leftType == Double.class;
        boolean isRightDouble = rightType == Double.class;
        if (isLeftDouble || isRightDouble)
            this.annotatedTypes.put(ctx, Double.class);
        else
            this.annotatedTypes.put(ctx, Integer.class);
    }

    @Override
    public void exitMultDivMod(SolParser.MultDivModContext ctx)
    {
        if (ctx.op.getText().matches("[*/]"))
            this.exitArithmetic(ctx, ctx.expr(0), ctx.expr(1));
        else if (ctx.op.getText().equals("%"))
            this.exitMod(ctx, ctx.expr(0), ctx.expr(1));
    }

    private void exitMod(SolParser.MultDivModContext ctx, SolParser.ExprContext left, SolParser.ExprContext right)
    {
        Class<?> leftType = this.annotatedTypes.get(left);
        Class<?> rightType = this.annotatedTypes.get(right);
        if (leftType == null || rightType == null)
            return;

        boolean isLeftInteger = leftType == Integer.class;
        boolean isRightInteger = rightType == Integer.class;
        if (isLeftInteger && isRightInteger)
            this.annotatedTypes.put(ctx, Integer.class);
        else
            this.reporter.reportError(ctx, ERROR_MESSAGE);
    }

    @Override
    public void exitNegation(SolParser.NegationContext ctx)
    {
        if (ctx.op.getText().equals("-"))
            this.exitNumberNegation(ctx, ctx.expr());
        else
            this.exitNot(ctx, ctx.expr());
    }

    private void exitNumberNegation(SolParser.NegationContext ctx, SolParser.ExprContext exprContext)
    {
        Class<?> exprType = this.annotatedTypes.get(exprContext);
        if (exprType == null)
            return;

        boolean isExprNumber = exprType == Integer.class || exprType == Double.class;
        if (isExprNumber)
            this.annotatedTypes.put(ctx, exprType);
        else
            this.reporter.reportError(ctx, ERROR_MESSAGE);
    }

    private void exitNot(SolParser.NegationContext ctx, SolParser.ExprContext exprContext)
    {
        Class<?> exprType = this.annotatedTypes.get(exprContext);
        if (exprType == null)
            return;

        boolean isExprBoolean = exprType == Boolean.class;
        if (isExprBoolean)
            this.annotatedTypes.put(ctx, exprType);
        else
            this.reporter.reportError(ctx, ERROR_MESSAGE);
    }

    public void exitParentheses(SolParser.ParenthesesContext ctx)
    {
        this.annotatedTypes.put(ctx, this.annotatedTypes.get(ctx.expr()));
    }
    public static void main(String[] args)
    {
        solCompiler compiler = new solCompiler();
        try { compiler.compile(args); }
        catch (IOException e) { RuntimeError.dispatchError(e.getMessage()); }
    }

}
