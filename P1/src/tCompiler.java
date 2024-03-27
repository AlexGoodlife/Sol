import tAsm.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class tCompiler extends TasmBaseListener
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
    private ArrayDeque<Instruction> instructions;
    private List<Object> constantPool;
    private HashMap<String, InstructionCode> nameToCodes;
    private HashMap<String, Integer> labelsToInstruction;
    private ErrorReporter errorReporter;

    public tCompiler()
    {
        this.sourceFileName = DEFAULT_SOURCE_FILE_NAME;
        this.byteCodesFileName = DEFAULT_BYTECODES_FILE_NAME;
        this.showAssembly = DEFAULT_SHOW_ASSEMBLY;
    }

    @Override
    public void exitLoadInt(TasmParser.LoadIntContext ctx)
    {
        this.instructions.push(new Instruction(InstructionCode.ICONST, Integer.valueOf(ctx.INT().getText())));
    }

    @Override
    public void exitLoadDouble(TasmParser.LoadDoubleContext ctx)
    {
        this.constantPool.add(Double.valueOf(ctx.value.getText()));
        this.instructions.push(new Instruction(InstructionCode.DCONST, this.constantPool.size() - 1));
    }

    @Override
    public void exitLoadString(TasmParser.LoadStringContext ctx)
    {
        this.constantPool.add(ctx.STRING().getText().replaceAll("\"", ""));
        this.instructions.push(new Instruction(InstructionCode.SCONST, this.constantPool.size() - 1));
    }

    @Override
    public void exitJump(TasmParser.JumpContext ctx)
    {
        Integer operand = this.labelsToInstruction.get(ctx.LABEL().getText());
        if(operand == null){
            this.errorReporter.reportError(ctx.getText(), "Invalid jump label");
            return;
        }
        this.instructions.push(new Instruction(this.nameToCodes.get(ctx.jump.getText()), operand));
    }

    @Override
    public void exitGlobal(TasmParser.GlobalContext ctx)
    {
        int operand = Integer.parseInt(ctx.INT().getText());
        this.instructions.push(new Instruction(this.nameToCodes.get(ctx.global.getText()), operand));
    }

    @Override
    public void exitSimpleInstruction(TasmParser.SimpleInstructionContext ctx)
    {
        this.instructions.push(new Instruction(this.nameToCodes.get(ctx.SIMPLE_INSTRUCTION().getText())));
    }

    public void compile(String[] args) throws IOException
    {
        this.parseArguments(args);
        this.initCompiler();
        if(!this.hasSemanticErrors())
            this.writeByteCodes();
    }

    private void parseArguments(String[] args)
    {
        if (args.length > MAX_ARGUMENTS_SIZE)
            throw new IllegalArgumentException("Invalid arguments");

        for (int i = 0; i < args.length; i++)
            switch (args[i])
            {
                case SOURCE_FILE_FLAG:
                    this.sourceFileName = args[++i];
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
        this.initCompilerVariables();
        InputStream inputStream = this.sourceFileName != null ? new FileInputStream(this.sourceFileName) : System.in;
        CharStream input = CharStreams.fromStream(inputStream);
        TasmLexer lexer = new TasmLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TasmParser parser = new TasmParser(tokens);
        ParseTree tree = parser.program();
        tSemanticChecker checker = new tSemanticChecker(this.errorReporter);
        checker.semanticCheck(tree);
        this.labelsToInstruction = checker.getLabelsToInstruction();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);
    }

    private void initCompilerVariables()
    {
        this.instructions = new ArrayDeque<>();
        this.constantPool = new ArrayList<>();
        this.nameToCodes = new HashMap<>();
        Arrays.stream(InstructionCode.values()).forEach((code) -> this.nameToCodes.put(code.name().toLowerCase(), code));
        this.errorReporter = new ErrorReporter();
    }

    private boolean hasSemanticErrors()
    {
        boolean hasErrors = this.errorReporter.getErrorCount() > 0;
        if (hasErrors)
            this.errorReporter.getErrors().forEach((e) -> System.err.println("ERROR: " + e.getCtx() + " " + e.getMessage()));
        return hasErrors;
    }

    private void writeByteCodes() throws IOException
    {
        DataOutputStream byteCodes = new DataOutputStream(new FileOutputStream(this.byteCodesFileName));
        this.writeInstructions(byteCodes);
        this.writeConstantPool(byteCodes);
    }

    private void writeInstructions(DataOutputStream byteCodes) throws IOException
    {
        while (!this.instructions.isEmpty())
        {
            Instruction instruction = this.instructions.removeLast();
            byteCodes.writeByte(instruction.getInstruction().ordinal());
            if (instruction.getOperand() != null)
                byteCodes.writeInt(instruction.getOperand());

            if (this.showAssembly)
                System.out.println(instruction);
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

    public static void main(String[] args) throws IOException
    {
        tCompiler compiler = new tCompiler();
        compiler.compile(args);
    }
}