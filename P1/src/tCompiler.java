import tAsm.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import tAsm.TasmParser;

import java.io.*;
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

    private ArrayDeque<Instruction> instructions;
    private String sourceFileName;
    private String byteCodesFileName;
    private boolean showAssembly;

    private Stack<Instruction> instructionStack;
    private List<Object> constantPool;

    private HashMap<String, InstructionCode> simpleInstructions;

    public tCompiler()
    {
        this.sourceFileName = DEFAULT_SOURCE_FILE_NAME;
        this.byteCodesFileName = DEFAULT_BYTECODES_FILE_NAME;
        this.showAssembly = DEFAULT_SHOW_ASSEMBLY;
        this.instructionStack = new Stack<>();
        this.simpleInstructions = new HashMap<>();
        this.constantPool = new ArrayList<>();
        Arrays.stream(InstructionCode.values()).forEach((code)-> this.simpleInstructions.put(code.name().toLowerCase(),code));
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitProgram(TasmParser.ProgramContext ctx) { }


    @Override
    public void exitLine(TasmParser.LineContext ctx){

    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitLoadInt(TasmParser.LoadIntContext ctx) {
        this.instructionStack.push(new Instruction(InstructionCode.ICONST,Integer.valueOf(ctx.INT().getText())));
    }


    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitLoadDouble(TasmParser.LoadDoubleContext ctx) {
        this.constantPool.add(Double.valueOf(ctx.DOUBLE().getText()));
        this.instructionStack.push(new Instruction(InstructionCode.DCONST, this.constantPool.size()- 1));
    }


    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitLoadString(TasmParser.LoadStringContext ctx) {
        this.constantPool.add(ctx.STRING().getText().replaceAll("\"" ,""));
        this.instructionStack.push(new Instruction(InstructionCode.SCONST, this.constantPool.size()- 1));
    }


    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitLoadBool(TasmParser.LoadBoolContext ctx) {
        int boolValue = Boolean.parseBoolean(ctx.BOOL().getText()) ? 1 : 0;
        this.instructionStack.push(new Instruction(InstructionCode.ICONST,boolValue));
    }


    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitGlobal(TasmParser.GlobalContext ctx) {
        String op = ctx.operation.getText();
        int operand = Integer.parseInt(ctx.INT().getText());
        if(op.equals(InstructionCode.GALLOC.name().toLowerCase())){
            this.instructionStack.push(new Instruction(InstructionCode.GALLOC, operand));
        }
        else if(op.equals(InstructionCode.GSTORE.name().toLowerCase())){
            this.instructionStack.push(new Instruction(InstructionCode.GSTORE, operand));
        }
        else{
            this.instructionStack.push(new Instruction(InstructionCode.GLOAD, operand));
        }
    }


    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitSimpleInstruction(TasmParser.SimpleInstructionContext ctx) {
        this.instructionStack.push(new Instruction(this.simpleInstructions.get(ctx.SIMPLE_INSTRUCTION().getText())));
    }


    public void compile(String[] args) throws IOException
    {
        this.parseArguments(args);
        this.initCompiler();
        this.instructionStack.forEach(System.out::println);
        this.constantPool.forEach((obj) -> {
            System.out.println(obj);
        } );
        //this.writeByteCodes();
    }

    private void parseArguments(String[] args)
    {
        if (args.length > MAX_ARGUMENTS_SIZE)
            throw new IllegalArgumentException("Invalid arguments");

        for (int i = 0; i < args.length; i++)
        {
            if (args[i].equals(SOURCE_FILE_FLAG))
                this.sourceFileName = args[++i];
            else if (args[i].equals(BYTECODES_FILE_FLAG))
                this.byteCodesFileName = args[++i];
            else
                this.showAssembly = args[i].equals(SHOW_ASSEMBLY_FLAG);
        }
    }

    private void initCompiler() throws IOException
    {
        this.instructions = new ArrayDeque<>();
        InputStream inputStream = this.sourceFileName != null ? new FileInputStream(this.sourceFileName) : System.in;
        CharStream input = CharStreams.fromStream(inputStream);
        TasmLexer lexer = new TasmLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TasmParser parser = new TasmParser(tokens);
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);
    }

    private void writeByteCodes() throws IOException
    {
        DataOutputStream byteCodes = new DataOutputStream(new FileOutputStream(this.byteCodesFileName));
        while (!this.instructions.isEmpty())
        {
            Instruction instruction = this.instructions.removeLast();
            byteCodes.writeByte(instruction.getInstruction().ordinal());
            if (instruction.getOperand() != null)
                byteCodes.writeInt(instruction.getOperand());

            if (this.showAssembly)
                System.out.println(instruction);
        }
    }

    public static void main(String[] args) throws IOException
    {
        tCompiler compiler = new tCompiler();
        compiler.compile(args);
    }
}