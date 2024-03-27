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
        this.executeInstructions();
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
        if (this.showTrace)
            this.printDisassembled();
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
            else if (types[type] == TypeCode.STRING) {
                int size = this.byteCodes.readInt();
                byte[] stringBytes = this.byteCodes.readNBytes(size);
                this.constantPool.add(new String(stringBytes));
            }
        }
    }

    private void printDisassembled()
    {
        System.out.println("DISASSEMBLED INSTRUCTIONS:");
        for(int i = 0; i < instructions.size(); i++)
            System.out.println(i + ":\t" + instructions.get(i));

        System.out.println("\nDISASSEMBLED CONSTANT POOL:");
        System.out.println(this.constantPool);
    }

    private void executeInstructions()
    {
        while (this.instructionPointer < this.instructions.size())
        {
            Instruction currentInstruction = this.instructions.get(this.instructionPointer++);
            if(this.showTrace)
                this.printTrace(currentInstruction);
            this.executeInstruction(currentInstruction);

        }
    }

    private void printTrace(Instruction currentInstruction)
    {
        String s = currentInstruction.toString();
        String opStr = s + " ".repeat(Math.max(0, 20 - s.length()));
        System.out.println(this.instructionPointer + ":" + "\t" + opStr + "\tStack:\t" + this.executionStack);
        System.out.println("\t".repeat(7) + "Global Memory:\t" + this.globalMemory + "\n");
    }

    private void executeInstruction(Instruction instruction)
    {
        switch (instruction.getInstruction())
        {
            case ICONST -> this.executionStack.push(instruction.getOperand());
            case DCONST, SCONST -> this.executionStack.push(this.constantPool.get(instruction.getOperand()));
            case TCONST, FCONST -> this.executionStack.push(instruction.getInstruction() == InstructionCode.TCONST);
            case JUMP -> this.instructionPointer = instruction.getOperand();
            case JUMPT -> {
                if (this.executionStack.pop() instanceof Boolean bool && bool)
                    this.instructionPointer = instruction.getOperand();
            }
            case JUMPF -> {
                if (this.executionStack.pop() instanceof Boolean bool && !bool)
                    this.instructionPointer = instruction.getOperand();
            }
            case GALLOC -> {
                for (int i = 0; i < instruction.getOperand(); i++)
                    this.globalMemory.add(null);
            }
            case GSTORE -> this.globalMemory.set(instruction.getOperand(), this.executionStack.pop());
            case GLOAD -> this.executionStack.push(this.globalMemory.get(instruction.getOperand()));
            case IPRINT -> {
                if (this.executionStack.pop() instanceof Integer i)
                    System.out.println(i);
            }
            case DPRINT -> {
                if (this.executionStack.pop() instanceof Double d)
                    System.out.println(d);
            }
            case SPRINT -> {
                if (this.executionStack.pop() instanceof String s)
                    System.out.println(s);
            }
            case BPRINT -> {
                if (this.executionStack.pop() instanceof Boolean b)
                    System.out.println(b);
            }
            case IUMINUS -> {
                if (this.executionStack.pop() instanceof Integer i)
                    this.executionStack.push(-i);
            }
            case DUMINUS -> {
                if (this.executionStack.pop() instanceof Double d)
                    this.executionStack.push(-d);
            }
            case NOT -> {
                if (this.executionStack.pop() instanceof Boolean b)
                    this.executionStack.push(!b);
            }
            case ITOD -> {
                if (this.executionStack.pop() instanceof Integer i)
                    this.executionStack.push((double) i);
            }
            case ITOS -> {
                if (this.executionStack.pop() instanceof Integer i)
                    this.executionStack.push(String.valueOf(i));
            }
            case DTOS -> {
                if (this.executionStack.pop() instanceof Double d)
                    this.executionStack.push(String.valueOf(d));
            }
            case BTOS -> {
                if (this.executionStack.pop() instanceof Boolean b)
                    this.executionStack.push(String.valueOf(b));
            }
            case HALT -> System.exit(0);
            default -> executeTwoOperands(instruction);
        }
    }

    private void executeTwoOperands(Instruction instruction)
    {
        Object right = this.executionStack.pop();
        Object left = this.executionStack.pop();
        switch (instruction.getInstruction())
        {
            case IADD, ISUB, IMOD, IMULT, IEQ, INEQ, ILEQ, ILT -> intOperation(left, right, instruction.getInstruction());
            case DADD, DSUB, DMULT, DEQ, DNEQ, DLEQ, DLT -> doubleOperation(left, right, instruction.getInstruction());
            case SNEQ, SADD, SEQ -> stringOperation(left, right, instruction.getInstruction());
            case BEQ, BNEQ, AND, OR -> booleanOperation(left, right, instruction.getInstruction());
        }
    }

    private void intOperation(Object left, Object right, InstructionCode op)
    {
        if (right instanceof Integer rightInt && left instanceof Integer leftInt)
        {
            switch (op)
            {
                case IADD -> this.executionStack.push( leftInt + rightInt );
                case ISUB -> this.executionStack.push(leftInt - rightInt);
                case IMOD -> this.executionStack.push(leftInt % rightInt);
                case IMULT -> this.executionStack.push(leftInt * rightInt);
                case IEQ -> this.executionStack.push(leftInt.equals(rightInt));
                case INEQ -> this.executionStack.push(!leftInt.equals(rightInt));
                case ILEQ -> this.executionStack.push(rightInt.compareTo(leftInt) <= 0 );
                case ILT -> this.executionStack.push(rightInt.compareTo(leftInt) < 0 );
            }
        }
    }
    private void doubleOperation(Object left, Object right, InstructionCode op)
    {
        if (right instanceof Double rightDouble && left instanceof Double leftDouble)
        {
            switch (op)
            {
                case DADD -> this.executionStack.push( leftDouble + rightDouble );
                case DSUB -> this.executionStack.push(leftDouble - rightDouble);
                case DMULT -> this.executionStack.push(leftDouble * rightDouble);
                case DEQ -> this.executionStack.push(leftDouble.equals(rightDouble));
                case DNEQ -> this.executionStack.push(!leftDouble.equals(rightDouble));
                case DLEQ -> this.executionStack.push(leftDouble.compareTo(rightDouble) <= 0 );
                case DLT -> this.executionStack.push(leftDouble.compareTo(rightDouble) < 0 );

            }
        }
    }

    private void stringOperation(Object left, Object right, InstructionCode op){
        if (right instanceof String rightString && left instanceof String leftString) {
            switch (op){
                case SADD -> this.executionStack.push( leftString + rightString );
                case SEQ -> this.executionStack.push(leftString.equals(rightString));
                case SNEQ -> this.executionStack.push(!leftString.equals(rightString));

            }
        }
    }

    private void booleanOperation(Object left, Object right, InstructionCode op){
        if (right instanceof Boolean rightBoolean && left instanceof Boolean leftBoolean) {
            switch (op){

                case BEQ -> this.executionStack.push( leftBoolean == rightBoolean);
                case BNEQ -> this.executionStack.push(leftBoolean != rightBoolean);
                case AND -> this.executionStack.push(leftBoolean && rightBoolean);
                case OR -> this.executionStack.push(leftBoolean || rightBoolean);
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        tVm virtualMachine = new tVm();
        virtualMachine.execute(args);
    }
}
