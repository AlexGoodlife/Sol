package Tasm;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.IntStream;

public class tVm
{
    public static final int MAX_ARGUMENTS_SIZE = 3;

    private static final String BYTECODES_FILE_FLAG = "-b";
    private static final String TRACE_FLAG = "--trace";

    private static final String DEFAULT_BYTECODES_FILE_NAME = null;
    private static final boolean DEFAULT_SHOW_TRACE = false;

    private String byteCodesFileName;
    private boolean showTrace;

    private DataInputStream byteCodes;
    private ArrayList<Instruction> instructions;
    private int instructionPointer;
    private Stack<Value> executionStack;
    private ArrayList<Value> constantPool;
    private ArrayList<Value> globalMemory;

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
            RuntimeError.dispatchError("Invalid arguments size of " + args.length);

        for (int i = 0; i < args.length; i++)
        {
            if (args[i].equals(BYTECODES_FILE_FLAG))
                this.byteCodesFileName = args[++i];
            else if (args[i].equals(TRACE_FLAG))
                this.showTrace = true;
            else
                RuntimeError.dispatchError("Invalid flag '" + args[i] + "'");
        }

        this.checkFile();
    }

    private void checkFile()
    {
        if (this.byteCodesFileName == null)
            RuntimeError.dispatchError("Bytecode input file not specified");

        String[] splitByteCodesFileName = this.byteCodesFileName.split("\\.");
        String byteCodesFileExtension = splitByteCodesFileName[splitByteCodesFileName.length - 1];
        if (!byteCodesFileExtension.equals(tAssembler.BYTECODES_FILE_EXTENSION))
            RuntimeError.dispatchError("Invalid bytecode file extension '." + byteCodesFileExtension + "'");
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
            this.printDisassembledByteCodes();
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
                this.constantPool.add(new Value(this.byteCodes.readDouble()));
            else if (types[type] == TypeCode.STRING)
            {
                int size = this.byteCodes.readInt();
                byte[] stringBytes = this.byteCodes.readNBytes(size);
                this.constantPool.add(new Value(new String(stringBytes)));
            }
        }
    }

    private void printDisassembledByteCodes()
    {
        System.out.println("DISASSEMBLED INSTRUCTIONS:");
        for (int i = 0; i < this.instructions.size(); i++)
            System.out.println(i + ":\t" + this.instructions.get(i));

        System.out.println("\nDISASSEMBLED CONSTANT POOL:");
        System.out.println(this.constantPool);
    }

    private void executeInstructions()
    {
        if (this.showTrace)
            System.out.print("\nEXECUTION TRACE:");
        while (this.instructionPointer < this.instructions.size())
        {
            Instruction currentInstruction = this.instructions.get(this.instructionPointer++);
            if (this.showTrace)
                this.printTrace(currentInstruction);
            this.executeInstruction(currentInstruction);
        }

        //If it reaches the end without halting it means no halt was encountered, throw error
        Instruction lastInstruction = this.instructions.get(this.instructionPointer - 1);
        this.genericError(lastInstruction, "Program terminated with a non halt instruction");
    }

    private void printTrace(Instruction currentInstruction)
    {
        String s = currentInstruction.toString();
        String opStr = s + " ".repeat(Math.max(0, 20 - s.length()));
        System.out.println("\n" + (this.instructionPointer - 1) + ":" + "\t" + opStr + "\tStack:\t" + this.executionStack);
        System.out.println("\t".repeat(7) + "Global Memory:\t" + this.globalMemory);
    }

    private void executeInstruction(Instruction instruction)
    {
        switch (instruction.getInstruction())
        {
            case ICONST -> this.executionStack.push(new Value(instruction.getOperand()));
            case DCONST, SCONST -> this.executionStack.push(this.constantPool.get(instruction.getOperand()));
            case TCONST, FCONST -> this.executionStack.push(new Value(instruction.getInstruction() == InstructionCode.TCONST));
            case JUMP -> this.instructionPointer = instruction.getOperand();
            case GALLOC -> {
                if (instruction.getOperand() < 0)
                    this.genericError(instruction, "Can't allocate negative quantity of memory");
                IntStream.range(0, instruction.getOperand()).forEach(index -> this.globalMemory.add(new Value(null)));
            }
            case GLOAD -> this.executeGlobalMemoryAccessInstruction(instruction);
            case HALT -> System.exit(0);
            default -> this.executeStackInstruction(instruction);
        }
    }

    private void executeGlobalMemoryAccessInstruction(Instruction instruction)
    {
        int index = instruction.getOperand();
        if (index < 0 || index >= this.globalMemory.size())
            this.indexOutOfBoundsError(instruction);

        switch (instruction.getInstruction())
        {
            case GLOAD -> this.executionStack.push(this.globalMemory.get(index));
            case GSTORE -> this.globalMemory.set(index, this.executionStack.pop());
        }
    }

    private void executeStackInstruction(Instruction instruction)
    {
        if (this.executionStack.isEmpty())
            this.insufficientStackError(instruction);

        switch (instruction.getInstruction())
        {
            case GSTORE -> this.executeGlobalMemoryAccessInstruction(instruction);
            case IPRINT, IUMINUS, ITOD, ITOS -> this.intStackInstruction(this.executionStack.pop(), instruction);
            case DPRINT, DUMINUS, DTOS -> this.doubleStackInstruction(this.executionStack.pop(), instruction);
            case BPRINT, NOT, BTOS, JUMPT, JUMPF -> this.booleanStackInstruction(this.executionStack.pop(), instruction);
            case SPRINT -> {
                if (this.executionStack.pop().getValue() instanceof String poppedString)
                    System.out.println(this.getEscapedContent(poppedString));
                else
                    this.typeError(instruction, String.class);
            }
            default -> this.executeTwoOperandsInstruction(instruction);
        }
    }

    private String getEscapedContent(String raw)
    {
        return raw.replaceAll("\\\\n", "\n").replaceAll("\\\\t","\t");
    }

    private void intStackInstruction(Value popped, Instruction instruction)
    {
        if (popped.getValue() instanceof Integer poppedInt)
            switch (instruction.getInstruction())
            {
                case IPRINT -> System.out.println(poppedInt);
                case IUMINUS -> this.executionStack.push(new Value(-poppedInt));
                case ITOD -> this.executionStack.push(new Value((double) poppedInt));
                case ITOS -> this.executionStack.push(new Value(String.valueOf(poppedInt)));
            }
        else
            this.typeError(instruction, Integer.class);
    }

    private void doubleStackInstruction(Value popped, Instruction instruction)
    {
        if (popped.getValue() instanceof Double poppedDouble)
            switch (instruction.getInstruction())
            {
                case DPRINT -> System.out.println(poppedDouble);
                case DUMINUS -> this.executionStack.push(new Value(-poppedDouble));
                case DTOS -> this.executionStack.push(new Value(String.valueOf(poppedDouble)));
            }
        else
            this.typeError(instruction, Double.class);
    }
    private void booleanStackInstruction(Value popped, Instruction instruction)
    {
        if (popped.getValue() instanceof Boolean poppedBoolean)
            switch (instruction.getInstruction())
            {
                case BPRINT -> System.out.println(poppedBoolean);
                case NOT -> this.executionStack.push(new Value(!poppedBoolean));
                case BTOS -> this.executionStack.push(new Value(String.valueOf(poppedBoolean)));
                case JUMPT -> {
                    if (poppedBoolean)
                        this.instructionPointer = instruction.getOperand();
                }
                case JUMPF -> {
                    if (!poppedBoolean)
                        this.instructionPointer = instruction.getOperand();
                }
            }
        else
            this.typeError(instruction, Boolean.class);
    }

    private void executeTwoOperandsInstruction(Instruction instruction)
    {
        if (this.executionStack.size() < 2)
            this.insufficientStackError(instruction);

        Value right = this.executionStack.pop();
        Value left = this.executionStack.pop();
        switch (instruction.getInstruction())
        {
            case IADD, ISUB, IMULT, IDIV, IMOD, IEQ, INEQ, ILEQ, ILT -> this.intStackInstruction(left, right, instruction);
            case DADD, DSUB, DMULT, DDIV, DEQ, DNEQ, DLEQ, DLT -> this.doubleStackInstruction(left, right, instruction);
            case SNEQ, SADD, SEQ -> this.stringStackInstruction(left, right, instruction);
            case BEQ, BNEQ, AND, OR -> this.booleanStackInstruction(left, right, instruction);
        }
    }

    private void intStackInstruction(Value left, Value right, Instruction instruction)
    {
        if (right.getValue() instanceof Integer rightInt && left.getValue() instanceof Integer leftInt)
            switch (instruction.getInstruction())
            {
                case IADD -> this.executionStack.push(new Value(leftInt + rightInt));
                case ISUB -> this.executionStack.push(new Value(leftInt - rightInt));
                case IMULT -> this.executionStack.push(new Value(leftInt * rightInt));
                case IDIV -> {
                    if (rightInt == 0)
                        this.genericError(instruction, "Division by 0 error");
                    this.executionStack.push(new Value(leftInt / rightInt));
                }
                case IMOD -> this.executionStack.push(new Value(leftInt % rightInt));
                case IEQ -> this.executionStack.push(new Value(leftInt.equals(rightInt)));
                case INEQ -> this.executionStack.push(new Value(!leftInt.equals(rightInt)));
                case ILEQ -> this.executionStack.push(new Value(leftInt.compareTo(rightInt) <= 0));
                case ILT -> this.executionStack.push(new Value(leftInt.compareTo(rightInt) < 0));
            }
        else
            this.typeError(instruction, Integer.class);
    }

    private void doubleStackInstruction(Value left, Value right, Instruction instruction)
    {
        if (right.getValue() instanceof Double rightDouble && left.getValue() instanceof Double leftDouble)
            switch (instruction.getInstruction())
            {
                case DADD -> this.executionStack.push(new Value(leftDouble + rightDouble));
                case DSUB -> this.executionStack.push(new Value(leftDouble - rightDouble));
                case DMULT -> this.executionStack.push(new Value(leftDouble * rightDouble));
                case DDIV -> {
                    if (rightDouble == 0)
                        this.genericError(instruction, "Division by 0 error");
                    this.executionStack.push(new Value(leftDouble / rightDouble));
                }
                case DEQ -> this.executionStack.push(new Value(leftDouble.equals(rightDouble)));
                case DNEQ -> this.executionStack.push(new Value(!leftDouble.equals(rightDouble)));
                case DLEQ -> this.executionStack.push(new Value(leftDouble.compareTo(rightDouble) <= 0));
                case DLT -> this.executionStack.push(new Value(leftDouble.compareTo(rightDouble) < 0));
            }
        else
            this.typeError(instruction, Double.class);
    }

    private void stringStackInstruction(Value left, Value right, Instruction instruction)
    {
        if (right.getValue() instanceof String rightString && left.getValue() instanceof String leftString)
            switch (instruction.getInstruction())
            {
                case SADD -> this.executionStack.push(new Value(leftString + rightString));
                case SEQ -> this.executionStack.push(new Value(leftString.equals(rightString)));
                case SNEQ -> this.executionStack.push(new Value(!leftString.equals(rightString)));
            }
        else
            this.typeError(instruction, String.class);
    }

    private void booleanStackInstruction(Object left, Object right, Instruction instruction)
    {
        if (right instanceof Boolean rightBoolean && left instanceof Boolean leftBoolean)
            switch (instruction.getInstruction())
            {
                case BEQ -> this.executionStack.push(new Value(leftBoolean == rightBoolean));
                case BNEQ -> this.executionStack.push(new Value(leftBoolean != rightBoolean));
                case AND -> this.executionStack.push(new Value(leftBoolean && rightBoolean));
                case OR -> this.executionStack.push(new Value(leftBoolean || rightBoolean));
            }
        else
            this.typeError(instruction, Boolean.class);
    }

    private void insufficientStackError(Instruction instruction)
    {
        String message = this.instructionPointer + ":0 " +
                "Stack doesn't have enough elements for '" +
                instruction + "'; Stack: " + this.executionStack;
        RuntimeError.dispatchError(message);
    }

    private void typeError(Instruction instruction, Class<?> expectedType)
    {
        String message = this.instructionPointer + ":0 " +
                "Expected " + expectedType.getSimpleName() +
                " type for '" + instruction + "'";
        RuntimeError.dispatchError(message);
    }

    private void genericError(Instruction instruction, String errorMessage)
    {
        String message = this.instructionPointer + ":0 " + instruction + " "  + errorMessage;
        RuntimeError.dispatchError(message);
    }

    private void indexOutOfBoundsError(Instruction instruction)
    {
        String message = this.instructionPointer + ":0 " +
                "Index " + instruction.getOperand() +
                " out of bounds for global memory size " +
                this.globalMemory.size() +
                " '" + instruction + "'";
        RuntimeError.dispatchError(message);
    }

    public static void main(String[] args)
    {
        tVm virtualMachine = new tVm();
        try { virtualMachine.execute(args); }
        catch (IOException e) { RuntimeError.dispatchError(e.getMessage()); }
    }
}
