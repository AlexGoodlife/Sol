import java.io.*;
import java.util.Stack;

public class tVm
{
    public static final int MAX_ARGUMENTS_SIZE = 3;
    private static final String BYTECODES_FILE_FLAG = "-b";
    private static final String TRACE_FLAG = "--trace";

    private static final String DEFAULT_BYTECODES_FILE_NAME = null;
    private static final boolean DEFAULT_SHOW_TRACE = false;

    private Stack<Integer> stack;
    private DataInputStream byteCodes;
    private String byteCodesFileName;
    private boolean showTrace;

    public tVm()
    {
        this.byteCodesFileName = DEFAULT_BYTECODES_FILE_NAME;
        this.showTrace = DEFAULT_SHOW_TRACE;
    }

    public void execute(String[] args) throws IOException
    {
        this.parseArguments(args);
        this.initVirtualMachine();

        InstructionCode[] operations = InstructionCode.values();
        if (this.showTrace)
            System.out.println("Stack:\t" + this.stack);
        while (this.byteCodes.available() > 0)
        {
            byte code = this.byteCodes.readByte();
            if (this.showTrace)
                System.out.print(String.format("0x%02X: \t", code) + operations[code].name().toLowerCase());
            this.executeOperation(operations[code]);
            if (this.showTrace)
                System.out.println("\n" + "Stack:\t" + this.stack);
        }
    }

    private void parseArguments(String[] args)
    {
        if (args.length == 0 || args.length > MAX_ARGUMENTS_SIZE)
            throw new IllegalArgumentException("Invalid arguments");

        for (int i = 0; i < args.length; i++)
        {
            if (args[i].equals(BYTECODES_FILE_FLAG))
                this.byteCodesFileName = args[++i];
            else
                this.showTrace = args[i].equals(TRACE_FLAG);
        }
    }

    public void initVirtualMachine() throws IOException
    {
        this.stack = new Stack<>();
        this.byteCodes = new DataInputStream(new FileInputStream(this.byteCodesFileName));
    }

    private void executeOperation(InstructionCode code) throws IOException
    {
        switch (code)
        {
            case ICONST:
                int operand = this.byteCodes.readInt();
                if (this.showTrace)
                    System.out.print(" " + operand);
                this.stack.push(operand);
                break;
            case IUMINUS:
                this.stack.push(-this.stack.pop());
                break;
            case IPRINT:
                if (this.showTrace)
                    System.out.println();
                System.out.println(this.stack.pop());
                break;
            default:
                this.executeArithmeticOperation(code);
        }
    }

    private void executeArithmeticOperation(InstructionCode code)
    {
        int right = this.stack.pop();
        int left = this.stack.pop();
        switch (code)
        {
            case IADD:
                this.stack.push(left + right);
                break;
            case ISUB:
                this.stack.push(left - right);
                break;
            case IMULT:
                this.stack.push(left * right);
                break;
            case IDIV:
                this.stack.push(left / right);
        }
    }

    public static void main(String[] args) throws IOException
    {
        tVm virtualMachine = new tVm();
        virtualMachine.execute(args);
    }
}
