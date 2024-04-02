package Tasm;

import antlrTasm.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class tAssembler extends TasmBaseListener
{
    private static final int MAX_ARGUMENTS_SIZE = 5;

    private static final String SOURCE_FILE_FLAG = "-i";
    private static final String BYTECODES_FILE_FLAG = "-o";
    private static final String SHOW_ASSEMBLY_FLAG = "--asm";

    private static final String DEFAULT_SOURCE_FILE_NAME = null;
    private static final String DEFAULT_BYTECODES_FILE_NAME = "a.tbc";
    private static final boolean DEFAULT_SHOW_ASSEMBLY = false;

    private String sourceFileName;
    private String byteCodesFileName;
    private boolean showAssembly;

    private TasmParser parser;
    private ArrayList<Instruction> instructions;
    private List<Object> constantPool;
    private HashMap<Object, Integer> constantPoolChecker;
    private HashMap<String, InstructionCode> nameToCode;
    private HashMap<String, Integer> labelToInstruction;
    private ErrorReporter semanticErrorReporter;

    public tAssembler()
    {
        this.sourceFileName = DEFAULT_SOURCE_FILE_NAME;
        this.byteCodesFileName = DEFAULT_BYTECODES_FILE_NAME;
        this.showAssembly = DEFAULT_SHOW_ASSEMBLY;
    }

    @Override
    public void exitLoadInt(TasmParser.LoadIntContext ctx)
    {
        if (ctx.INT() == null)
            return;

        this.instructions.add(new Instruction(InstructionCode.ICONST, Integer.valueOf(ctx.INT().getText())));
    }

    @Override
    public void exitLoadDouble(TasmParser.LoadDoubleContext ctx)
    {
        if(ctx.DOUBLE() == null && ctx.INT() == null)
            return;

        Double readDouble = Double.valueOf(ctx.value.getText());
        Integer index = this.constantPoolChecker.get(readDouble);
        if (index == null)
        {
            this.constantPool.add(readDouble);
            index = this.constantPool.size() - 1;
            this.constantPoolChecker.put(readDouble, index);
        }
        this.instructions.add(new Instruction(InstructionCode.DCONST, index));
    }

    @Override
    public void exitLoadString(TasmParser.LoadStringContext ctx)
    {
        if (ctx.STRING() == null)
            return;

        String rawString = ctx.STRING().getText();
        String readString = rawString.substring(1, rawString.length()-1).replaceAll("\\\\([\"\\\\])", "$1");

        Integer index = this.constantPoolChecker.get(readString);
        if (index == null)
        {
            this.constantPool.add(readString);
            index = this.constantPool.size() - 1;
            this.constantPoolChecker.put(readString, index);
        }
        this.instructions.add(new Instruction(InstructionCode.SCONST, index));
    }

    @Override
    public void exitJump(TasmParser.JumpContext ctx)
    {
        if (ctx.LABEL() == null)
            return;

        Integer operand = this.labelToInstruction.get(ctx.LABEL().getText());
        if(operand == null)
            this.semanticErrorReporter.reportError(ctx, "Invalid jump label");
        else
            this.instructions.add(new Instruction(this.nameToCode.get(ctx.jump.getText()), operand));
    }

    @Override
    public void exitGlobal(TasmParser.GlobalContext ctx)
    {
        if (ctx.INT() == null)
            return;

        int operand = Integer.parseInt(ctx.INT().getText());
        this.instructions.add(new Instruction(this.nameToCode.get(ctx.global.getText()), operand));
    }

    @Override
    public void exitSimpleInstruction(TasmParser.SimpleInstructionContext ctx)
    {
        this.instructions.add(new Instruction(this.nameToCode.get(ctx.SIMPLE_INSTRUCTION().getText())));
    }

    public void assemble(String[] args) throws IOException
    {
        this.parseArguments(args);
        this.initCompiler();
        this.assembleInstructions();
        if (this.successfulAssemble())
            this.writeByteCodes();
        else
            this.throwUnsuccessfulAssemble();
    }

    private void parseArguments(String[] args)
    {
        if (args.length > MAX_ARGUMENTS_SIZE)
            throw new IllegalArgumentException("Invalid arguments");

        for (int i = 0; i < args.length; i++)
            switch (args[i])
            {
                case SOURCE_FILE_FLAG:
                    String filename = args[++i];
                    this.sourceFileName = filename;
                    this.byteCodesFileName = filename.replaceAll("\\.tasm", ".tbc");
                    break;
                case BYTECODES_FILE_FLAG:
                    this.byteCodesFileName = args[++i];
                    break;
                case SHOW_ASSEMBLY_FLAG:
                    this.showAssembly = true;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid flag");
            }
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
        tSemanticChecker checker = new tSemanticChecker(this.semanticErrorReporter);
        checker.semanticCheck(tree);
        this.labelToInstruction = checker.getLabelToInstruction();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);
    }

    private boolean successfulAssemble()
    {
        return !this.semanticErrorReporter.hasReportedErrors() && !(this.parser.getNumberOfSyntaxErrors() > 0);
    }

    private void throwUnsuccessfulAssemble()
    {
        //ANTLR should print all the syntax errors prior, if any
        System.err.println(this.semanticErrorReporter);

        int semanticErrorCount = this.semanticErrorReporter.getErrorCount();
        int syntaxErrorCount = this.parser.getNumberOfSyntaxErrors();
        String syntaxErrors = syntaxErrorCount == 1 ? " syntax error " : " syntax errors ";
        String semanticErrors = semanticErrorCount == 1 ? " semantic error" : " semantic errors";
        String message = "Program assembled unsuccessfully with " + syntaxErrorCount + syntaxErrors +
                "and " + semanticErrorCount + semanticErrors;

        System.err.println(message);
        System.exit(1);
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
        for (Object o : this.constantPool)
            if (o instanceof Double doubleValue)
            {
                byteCodes.writeByte(TypeCode.DOUBLE.ordinal());
                byteCodes.writeDouble(doubleValue);
            }
            else if (o instanceof String string)
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

    public static void main(String[] args) throws IOException
    {
        tAssembler assembler = new tAssembler();
        assembler.assemble(args);
    }
}