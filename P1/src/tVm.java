import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class tVm
{
    public static final int MAX_ARGUMENTS_SIZE = 3;
    private static final String BYTECODES_FILE_FLAG = "-b";
    private static final String TRACE_FLAG = "--trace";

    private static final String DEFAULT_BYTECODES_FILE_NAME = null;
    private static final boolean DEFAULT_SHOW_TRACE = false;

    private String byteCodesFileName;
    private DataInputStream byteCodes;
    private boolean showTrace;
    private ArrayList<Instruction> instructions;
    private int instructionPointer;
    private Stack<Object> executionStack;
    private ArrayList<Object> constantPool;
    private ArrayList<Object> globalMemory;

    public tVm()
    {
        this.byteCodesFileName = DEFAULT_BYTECODES_FILE_NAME;
        this.showTrace = DEFAULT_SHOW_TRACE;
    }

    public void execute(String[] args) throws IOException
    {
        this.parseArguments(args);
        this.initVirtualMachine();
        this.readByteCodes();
        System.out.println(this.instructions);
        System.out.println(this.constantPool);
        System.out.println((String) this.constantPool.get(0));
    }

    private void parseArguments(String[] args)
    {
        if (args.length == 0 || args.length > MAX_ARGUMENTS_SIZE)
            throw new IllegalArgumentException("Invalid arguments");

        for (int i = 0; i < args.length; i++)
        {
            if (args[i].equals(BYTECODES_FILE_FLAG))
                this.byteCodesFileName = args[++i];
            else if (args[i].equals(TRACE_FLAG))
                this.showTrace = true;
            else
                throw new IllegalArgumentException("Invalid flag");
        }
    }

    private void initVirtualMachine() throws IOException
    {

        this.byteCodes = new DataInputStream(new FileInputStream(this.byteCodesFileName));
        this.instructions = new ArrayList<>();
        this.instructionPointer = 0;
        this.executionStack = new Stack<>();
        this.constantPool = new ArrayList<>();
        this.globalMemory = new ArrayList<>();
    }

    private void readByteCodes() throws IOException
    {
        this.readInstructions();
        this.readConstantPool();
        this.execute();
    }

    private void readInstructions() throws IOException
    {
        InstructionCode code;
        InstructionCode[] codes = InstructionCode.values();
        while ((code = codes[this.byteCodes.readByte()]) != InstructionCode.END)
            if (code.ordinal() <= InstructionCode.GLOAD.ordinal())
                this.instructions.add(new Instruction(code, this.byteCodes.readInt()));
            else
                this.instructions.add(new Instruction(code));
    }

    private void readConstantPool() throws IOException
    {
        TypeCode[] types = TypeCode.values();
        while (this.byteCodes.available() > 0)
        {
            int type = this.byteCodes.readByte();
            if (types[type] == TypeCode.DOUBLE)
                this.constantPool.add(this.byteCodes.readDouble());
            else if (types[type] == TypeCode.STRING)
            {
                int size = this.byteCodes.readInt();
                byte[] stringBytes = this.byteCodes.readNBytes(size);
                this.constantPool.add(new String(stringBytes));
            }
        }
    }

    private void execute()
    {
        while (this.instructionPointer < this.instructions.size())
            this.executeInstruction(this.instructions.get(this.instructionPointer++));
    }

    private void executeInstruction(Instruction instruction)
    {
        switch (instruction.getInstruction())
        {
            case ICONST:
                this.executionStack.push(instruction.getOperand());
                break;
            case DCONST, SCONST:
                this.executionStack.push(this.constantPool.get(instruction.getOperand()));
                break;
            case TCONST, FCONST:
                this.executionStack.push(instruction.getInstruction() == InstructionCode.TCONST);
                break;
            case JUMP:
                this.instructionPointer = instruction.getOperand();
                break;
            case SPRINT:
                System.out.println(this.executionStack.pop());
                break;
            case HALT:
                System.exit(0);
                break;
        }
    }

    public static void main(String[] args) throws IOException
    {
        tVm virtualMachine = new tVm();
        virtualMachine.execute(args);
    }
}
