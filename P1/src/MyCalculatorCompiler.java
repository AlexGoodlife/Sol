import Calculator.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
import java.util.ArrayDeque;

public class MyCalculatorCompiler extends CalculatorBaseListener
{
    private static final int MAX_ARGUMENTS_SIZE = 5;

    private static final String SOURCE_FILE_FLAG = "-i";
    private static final String BYTECODES_FILE_FLAG = "-o";
    private static final String SHOW_ASSEMBLY_FLAG = "--asm";

    private static final String DEFAULT_SOURCE_FILE_NAME = null;
    private static final String DEFAULT_BYTECODES_FILE_NAME = "a.calcbc";
    private static final boolean DEFAULT_SHOW_ASSEMBLY = false;

    private ArrayDeque<Instruction> instructions;
    private String sourceFileName;
    private String byteCodesFileName;
    private boolean showAssembly;

    public MyCalculatorCompiler()
    {
        this.sourceFileName = DEFAULT_SOURCE_FILE_NAME;
        this.byteCodesFileName = DEFAULT_BYTECODES_FILE_NAME;
        this.showAssembly = DEFAULT_SHOW_ASSEMBLY;
    }

    @Override
    public void exitNegation(CalculatorParser.NegationContext ctx)
    {
        this.instructions.push(new Instruction(OperationCode.IUMINUS));
    }

    @Override
    public void exitMultDiv(CalculatorParser.MultDivContext ctx)
    {
        if (ctx.operator.getText().equals("*"))
            this.instructions.push(new Instruction(OperationCode.IMULT));
        else
            this.instructions.push(new Instruction(OperationCode.IDIV));
    }

    @Override
    public void exitPlusMinus(CalculatorParser.PlusMinusContext ctx)
    {
        if (ctx.operator.getText().equals("+"))
            this.instructions.push(new Instruction(OperationCode.IADD));
        else
            this.instructions.push(new Instruction(OperationCode.ISUB));

    }

    @Override
    public void exitInteger(CalculatorParser.IntegerContext ctx)
    {
        this.instructions.push(new Instruction(OperationCode.ICONST, Integer.valueOf(ctx.INT().getText())));
    }

    @Override
    public void exitInstruction(CalculatorParser.InstructionContext ctx)
    {
        this.instructions.push(new Instruction(OperationCode.IPRINT));
    }

    public void compile(String[] args) throws IOException
    {
        this.parseArguments(args);
        this.initCompiler();
        this.writeByteCodes();
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
        CalculatorLexer lexer = new CalculatorLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokens);
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
        MyCalculatorCompiler compiler = new MyCalculatorCompiler();
        compiler.compile(args);
    }
}