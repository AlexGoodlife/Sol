package Sol;

import ErrorUtils.*;
import antlrSol.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import Tasm.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/* Why use a visitor? Because we want to know if we need to insert conversion instructions prior to visiting any upcoming nodes in an expression.
* With a listener this would be much harder to do in a clean manner,
* as we would need to check the type of the parent node and its other child nodes in the child node for type conversion instructions */
public class solCompiler extends SolBaseVisitor<Void>
{
    private static final int MAX_ARGUMENTS_SIZE = 5;

    private static final String SOURCE_FILE_FLAG = "-i";
    public static final String SOURCE_FILE_EXTENSION = "sol";
    private static final String BYTECODES_FILE_FLAG = "-o";
    private static final String SHOW_ASSEMBLY_FLAG = "--asm";

    private static final String DEFAULT_SOURCE_FILE_NAME = null;
    private static final String DEFAULT_BYTECODES_FILE_NAME = "a.tbc";
    private static final boolean DEFAULT_SHOW_ASSEMBLY = false;

    private String sourceFileName;
    private String byteCodesFileName;
    private boolean showAssembly;

    private SolParser parser;
    private ParseTree tree;
    private ArrayList<Instruction> instructions;
    private ConstantPool constantPool;
    private ParseTreeProperty<Class<?>> annotatedTypes;
    private ErrorReporter typeErrorReporter;

    public solCompiler()
    {
        this.sourceFileName = DEFAULT_SOURCE_FILE_NAME;
        this.byteCodesFileName = DEFAULT_BYTECODES_FILE_NAME;
        this.showAssembly = DEFAULT_SHOW_ASSEMBLY;
    }

    @Override
    public Void visitInt(SolParser.IntContext ctx)
    {
        this.instructions.add(new Instruction(InstructionCode.ICONST, Integer.valueOf(ctx.INT().getText())));
        return null;
    }

    @Override
    public Void visitDouble(SolParser.DoubleContext ctx)
    {
        Value doubleValue = new Value(Double.valueOf(ctx.DOUBLE().getText()));
        this.instructions.add(new Instruction(InstructionCode.DCONST, this.constantPool.addIfAbsent(doubleValue)));
        return null;
    }

    @Override
    public Void visitString(SolParser.StringContext ctx)
    {
        String rawString = ctx.STRING().getText();
        String readString = rawString.substring(1, rawString.length() - 1).replaceAll("\\\\([\"\\\\])", "$1");
        Value stringValue = new Value(readString);
        this.instructions.add(new Instruction(InstructionCode.SCONST, this.constantPool.addIfAbsent(stringValue)));
        return null;
    }

    @Override
    public Void visitBoolean(SolParser.BooleanContext ctx)
    {
        if (Boolean.parseBoolean(ctx.BOOLEAN().getText()))
            this.instructions.add(new Instruction(InstructionCode.TCONST));
        else
            this.instructions.add(new Instruction(InstructionCode.FCONST));
        return null;
    }

    @Override
    public Void visitOr(SolParser.OrContext ctx)
    {
        // No need to check type conversion in logical operations
        this.visit(ctx.expr(0));
        this.visit(ctx.expr(1));
        this.instructions.add(new Instruction(InstructionCode.OR));
        return null;
    }

    @Override
    public Void visitAnd(SolParser.AndContext ctx)
    {
        // No need to check type conversion in logical operations
        this.visit(ctx.expr(0));
        this.visit(ctx.expr(1));
        this.instructions.add(new Instruction(InstructionCode.AND));
        return null;
    }

    /* A return value of null in this function signifies something is very wrong with the implementation.
    * As such we throw an InternalError if such ever occurs */
    private Class<?> visitNodesWithTypeConversionChecking(SolParser.ExprContext leftNode, SolParser.ExprContext rightNode)
    {
        Class<?> leftType = this.annotatedTypes.get(leftNode);
        Class<?> rightType = this.annotatedTypes.get(rightNode);
        Class<?> type = leftType == rightType ? leftType : null;

        this.visit(leftNode);
        type = this.addConversionIfPossible(leftType, rightType) ? rightType : type;
        this.visit(rightNode);
        type = this.addConversionIfPossible(rightType, leftType) ? leftType : type;

        if (type == null)
            throw new InternalError("Return type value is null");
        return type;
    }

    private boolean addConversionIfPossible(Class<?> from, Class<?> to)
    {
        if (from == to)
            return false;

        int initialSize = this.instructions.size();

        if (from == Integer.class && to == String.class)
            this.instructions.add(new Instruction(InstructionCode.ITOS));
        else if (from == Integer.class && to == Double.class)
            this.instructions.add(new Instruction(InstructionCode.ITOD));
        else if (from == Double.class && to == String.class)
            this.instructions.add(new Instruction(InstructionCode.DTOS));
        else if (from == Boolean.class && to == String.class)
            this.instructions.add(new Instruction(InstructionCode.BTOS));

        return this.instructions.size() > initialSize;
    }

    @Override
    public Void visitEquality(SolParser.EqualityContext ctx)
    {
        Class<?> type = this.visitNodesWithTypeConversionChecking(ctx.expr(0), ctx.expr(1));

        InstructionCode code;
        if (type == Integer.class)
            code = ctx.op.getText().equals("==") ? InstructionCode.IEQ : InstructionCode.INEQ;
        else if (type == Double.class)
            code = ctx.op.getText().equals("==") ? InstructionCode.DEQ : InstructionCode.DNEQ;
        else if (type == String.class)
            code = ctx.op.getText().equals("==") ? InstructionCode.SEQ : InstructionCode.SNEQ;
        else if (type == Boolean.class)
            code = ctx.op.getText().equals("==") ? InstructionCode.BEQ : InstructionCode.BNEQ;
        else
            throw new InternalError("Invalid type caused instruction code to be null");

        this.instructions.add(new Instruction(code));
        return null;
    }

    @Override
    public Void visitRelational(SolParser.RelationalContext ctx)
    {
        Class<?> type = this.visitNodesWithTypeConversionChecking(ctx.expr(0), ctx.expr(1));

        InstructionCode code;
        if (type == Integer.class)
            code = ctx.op.getText().matches("<|>=") ? InstructionCode.ILT : InstructionCode.ILEQ;
        else if (type == Double.class)
            code = ctx.op.getText().matches("<|>=") ? InstructionCode.DLT : InstructionCode.DLEQ;
        else
            throw new InternalError("Invalid type caused instruction code to be null");

        this.instructions.add(new Instruction(code));
        if (ctx.op.getText().matches(">|>="))
            this.instructions.add(new Instruction(InstructionCode.NOT));

        return null;
    }

    @Override
    public Void visitAddSub(SolParser.AddSubContext ctx)
    {
        Class<?> type = this.visitNodesWithTypeConversionChecking(ctx.expr(0), ctx.expr(1));

        InstructionCode code;
        if (type == Integer.class)
            code = ctx.op.getText().equals("+") ? InstructionCode.IADD : InstructionCode.ISUB;
        else if (type == Double.class)
            code = ctx.op.getText().equals("+") ? InstructionCode.DADD : InstructionCode.DSUB;
        else if (type == String.class && ctx.op.getText().equals("+"))
            code = InstructionCode.SADD;
        else
            throw new InternalError("Invalid type caused instruction code to be null");

        this.instructions.add(new Instruction(code));

        return null;
    }

    @Override
    public Void visitMultDivMod(SolParser.MultDivModContext ctx)
    {
        Class<?> type = this.visitNodesWithTypeConversionChecking(ctx.expr(0), ctx.expr(1));

        InstructionCode code;
        if (type == Integer.class && ctx.op.getText().equals("%"))
            code = InstructionCode.IMOD;
        else if (type == Integer.class)
            code = ctx.op.getText().equals("*") ? InstructionCode.IMULT : InstructionCode.IDIV;
        else if (type == Double.class)
            code = ctx.op.getText().equals("*") ? InstructionCode.DMULT : InstructionCode.DDIV;
        else
            throw new InternalError("Invalid type caused instruction code to be null");

        this.instructions.add(new Instruction(code));

        return null;
    }

    @Override
    public Void visitNegation(SolParser.NegationContext ctx)
    {
        this.visit(ctx.expr());
        Class<?> exprType = this.annotatedTypes.get(ctx.expr());

        if (exprType == Integer.class)
            this.instructions.add(new Instruction(InstructionCode.IUMINUS));
        else if (exprType == Double.class)
            this.instructions.add(new Instruction(InstructionCode.DUMINUS));
        else if (exprType == Boolean.class)
            this.instructions.add(new Instruction(InstructionCode.NOT));

        return null;
    }

    @Override
    public Void visitLine(SolParser.LineContext ctx)
    {
        this.visit(ctx.expr());

        Class<?> type = this.annotatedTypes.get(ctx.expr());
        if (type == Integer.class)
            this.instructions.add(new Instruction(InstructionCode.IPRINT));
        else if (type == Double.class)
            this.instructions.add(new Instruction(InstructionCode.DPRINT));
        else if (type == String.class)
            this.instructions.add(new Instruction(InstructionCode.SPRINT));
        else if (type == Boolean.class)
            this.instructions.add(new Instruction(InstructionCode.BPRINT));

        return null;
    }

    @Override
    public Void visitProgram(SolParser.ProgramContext ctx)
    {
        for (SolParser.LineContext line : ctx.line())
            this.visit(line);
        this.instructions.add(new Instruction(InstructionCode.HALT));
        return null;
    }

    public void compile(String[] args) throws IOException
    {
        this.parseArguments(args);
        this.initCompiler();
        this.semanticCheck();
        if (this.hasNoErrors())
        {
            this.generateInstructions();
            this.writeByteCodes();
        }
        else
            this.unsuccessfulCompileError();
    }

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
                    this.byteCodesFileName = filename.replaceAll("\\.sol", ".tbc");
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

        this.checkFiles();
    }

    private void checkFiles()
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
        if (!byteCodesFileExtension.equals(tVm.BYTECODES_FILE_EXTENSION))
            RuntimeError.dispatchError("Invalid bytecode file extension '." + byteCodesFileExtension + "'");
    }

    private void initCompiler() throws IOException
    {
        this.parser = this.generateParser();
        this.tree = this.parser.program();
        this.instructions = new ArrayList<>();
        this.constantPool = new ConstantPool();
        this.typeErrorReporter = new ErrorReporter();
    }

    private SolParser generateParser() throws IOException
    {
        InputStream inputStream = this.sourceFileName != null ? new FileInputStream(this.sourceFileName) : System.in;
        CharStream input = CharStreams.fromStream(inputStream);
        SolLexer lexer = new SolLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new SolParser(tokens);
    }

    private void semanticCheck()
    {
        solTypeAnnotator typeAnnotator = new solTypeAnnotator(this.typeErrorReporter);
        typeAnnotator.annotateTypes(this.tree);
        this.annotatedTypes = typeAnnotator.getAnnotatedTypes();
    }

    private boolean hasNoErrors()
    {
        return !this.typeErrorReporter.hasReportedErrors() && !(this.parser.getNumberOfSyntaxErrors() > 0);
    }

    private void unsuccessfulCompileError()
    {
        //ANTLR should print all the syntax errors prior, if any
        System.err.println(this.typeErrorReporter);

        int semanticErrorCount = this.typeErrorReporter.getErrorCount();
        int syntaxErrorCount = this.parser.getNumberOfSyntaxErrors();
        String syntaxErrors = syntaxErrorCount == 1 ? " syntax error " : " syntax errors ";
        String semanticErrors = semanticErrorCount == 1 ? " semantic error" : " semantic errors";
        String message = "Program compiled unsuccessfully with " + syntaxErrorCount + syntaxErrors +
                "and " + semanticErrorCount + semanticErrors;

        RuntimeError.dispatchError(message);
    }

    private void generateInstructions()
    {
        this.visit(this.tree);
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
        byteCodes.writeByte(ConstantPool.CONSTANT_POOL_DELIMITER);
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

    public static void main(String[] args)
    {
        solCompiler compiler = new solCompiler();
        try { compiler.compile(args); }
        catch (IOException e) { RuntimeError.dispatchError(e.getMessage()); }
    }
}
