package Tasm;

import Tasm.types.*;

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
    private Stack<Type<?>> executionStack;
    private ArrayList<Type<?>> constantPool;
    private ArrayList<Type<?>> globalMemory;

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
                this.constantPool.add(new Double64(this.byteCodes.readDouble()));
            else if (types[type] == TypeCode.STRING)
            {
                int size = this.byteCodes.readInt();
                byte[] stringBytes = this.byteCodes.readNBytes(size);
                this.constantPool.add(new Str(new String(stringBytes)));
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
        Instruction lastInstruction = this.instructions.get(this.instructionPointer - 1);
        if(!lastInstruction.getInstruction().equals(InstructionCode.HALT)){
            throwGenericError(lastInstruction, "Program terminated with a non halt instruction");
        }
    }

    private void printTrace(Instruction currentInstruction)
    {
        String s = currentInstruction.toString();
        String opStr = s + " ".repeat(Math.max(0, 20 - s.length()));
        System.out.println("\n" + (this.instructionPointer - 1) + ":" + "\t" + opStr + "\tStack:\t" + this.executionStack);
        System.out.println
        (
            "\t".repeat(7) +
            "Global Memory:\t" +
            this.globalMemory.toString()
        );
    }

    private void executeInstruction(Instruction instruction)
    {
        switch (instruction.getInstruction())
        {
            case ICONST -> this.executionStack.push(new Int32(instruction.getOperand()));
            case DCONST, SCONST -> this.executionStack.push(this.constantPool.get(instruction.getOperand()));
            case TCONST, FCONST -> this.executionStack.push(new Bool(instruction.getInstruction() == InstructionCode.TCONST));
            case JUMP -> this.instructionPointer = instruction.getOperand();
            case GALLOC -> IntStream.range(0, instruction.getOperand()).forEach(index -> this.globalMemory.add(new Nil()));
            case GLOAD -> this.executeGlobalMemoryAccessInstruction(instruction);
            case HALT -> System.exit(0);
            default -> this.executeStackInstruction(instruction);
        }
    }

    private void executeGlobalMemoryAccessInstruction(Instruction instruction)
    {
        int index = instruction.getOperand();
        if (index >= this.globalMemory.size())
            this.throwIndexOutOfBounds(instruction);

        switch (instruction.getInstruction())
        {
            case GLOAD -> this.executionStack.push(this.globalMemory.get(index));
            case GSTORE -> this.globalMemory.set(index, this.executionStack.pop());
        }
    }

    private void executeStackInstruction(Instruction instruction)
    {
        if (this.executionStack.isEmpty())
            this.throwInsufficientStackError(instruction);

        switch (instruction.getInstruction())
        {
            case GSTORE -> this.executeGlobalMemoryAccessInstruction(instruction);
            case IPRINT, IUMINUS, ITOD, ITOS -> this.intStackInstruction(this.executionStack.pop(), instruction);
            case DPRINT, DUMINUS, DTOS -> this.doubleStackInstruction(this.executionStack.pop(), instruction);
            case BPRINT, NOT, BTOS, JUMPT, JUMPF -> this.booleanStackInstruction(this.executionStack.pop(), instruction);
            case SPRINT -> {
                if (this.executionStack.pop() instanceof Str poppedString)
                    System.out.println(getEscapedContent(poppedString.getValue()));
                else
                    this.throwTypeError(instruction, String.class);
            }
            default -> this.executeTwoOperandsInstruction(instruction);
        }
    }
    private String getEscapedContent(String raw){
        return raw.replaceAll("\\\\n", "\n").replaceAll("\\\\t","\t");
    }
    private void intStackInstruction(Type<?> popped, Instruction instruction)
    {
        if (popped instanceof Int32 poppedInt) {
            switch (instruction.getInstruction()) {
                case IPRINT -> System.out.println(poppedInt);
                case IUMINUS -> this.executionStack.push(poppedInt.negate());
                case ITOD -> this.executionStack.push(new Double64(poppedInt));
                case ITOS -> this.executionStack.push(new Str(poppedInt));
            }
        }
        else
            this.throwTypeError(instruction, Integer.class);
    }

    private void doubleStackInstruction(Type<?> popped, Instruction instruction)
    {
        if (popped instanceof Double64 poppedDouble) {
            switch (instruction.getInstruction()) {
                case DPRINT -> System.out.println(poppedDouble);
                case DUMINUS -> this.executionStack.push(poppedDouble.negate());
                case DTOS -> this.executionStack.push(new Str(poppedDouble));
            }
        }
        else
            this.throwTypeError(instruction, Double.class);
    }
    private void booleanStackInstruction(Type<?> popped, Instruction instruction)
    {
        if (popped instanceof Bool poppedBoolean) {
            switch (instruction.getInstruction()) {
                case BPRINT -> System.out.println(poppedBoolean);
                case NOT -> this.executionStack.push(poppedBoolean.not());
                case BTOS -> this.executionStack.push(new Str(poppedBoolean));
                case JUMPT -> {
                    if (poppedBoolean.getValue())
                        this.instructionPointer = instruction.getOperand();
                }
                case JUMPF -> {
                    if (!poppedBoolean.getValue())
                        this.instructionPointer = instruction.getOperand();
                }
            }
        }
        else
            this.throwTypeError(instruction, Boolean.class);
    }

    private void executeTwoOperandsInstruction(Instruction instruction)
    {
        if (this.executionStack.size() < 2)
            this.throwInsufficientStackError(instruction);

        Type<?> right = this.executionStack.pop();
        Type<?> left = this.executionStack.pop();
        switch (instruction.getInstruction())
        {
            case IADD, ISUB, IMULT, IDIV, IMOD, IEQ, INEQ, ILEQ, ILT -> this.intStackInstruction(left, right, instruction);
            case DADD, DSUB, DMULT, DDIV, DEQ, DNEQ, DLEQ, DLT -> this.doubleStackInstruction(left, right, instruction);
            case SNEQ, SADD, SEQ -> this.stringStackInstruction(left, right, instruction);
            case BEQ, BNEQ, AND, OR -> this.booleanStackInstruction(left, right, instruction);
        }
    }

    private void intStackInstruction(Type<?> left, Type<?> right, Instruction instruction)
    {
        if (right instanceof Int32 rightInt && left instanceof Int32 leftInt) {
            switch (instruction.getInstruction()) {
                case IADD -> this.executionStack.push(leftInt.add(rightInt));
                case ISUB -> this.executionStack.push(leftInt.sub(rightInt));
                case IMULT -> this.executionStack.push(leftInt.mult(rightInt));
                case IDIV -> {
                    if (rightInt.getValue() == 0) {
                        throwGenericError(instruction, "Division by 0 error");
                    }
                    this.executionStack.push(leftInt.div(rightInt));
                }
                case IMOD -> this.executionStack.push(leftInt.mod(rightInt));
                case IEQ -> this.executionStack.push(leftInt.equals(rightInt));
                case INEQ -> this.executionStack.push(leftInt.notEquals(rightInt));
                case ILEQ -> this.executionStack.push(leftInt.lessThanOrEqual(rightInt));
                case ILT -> this.executionStack.push(leftInt.lessThan(rightInt));
            }
        }
        else
            this.throwTypeError(instruction, Integer.class);
    }

    private void doubleStackInstruction(Type<?> left, Type<?> right, Instruction instruction)
    {
        if (right instanceof Double64 rightDouble && left instanceof Double64 leftDouble) {
            switch (instruction.getInstruction()) {
                case DADD -> this.executionStack.push(leftDouble.add(rightDouble));
                case DSUB -> this.executionStack.push(leftDouble.sub(rightDouble));
                case DMULT -> this.executionStack.push(leftDouble.mult(rightDouble));
                case DDIV -> {
                    if (rightDouble.getValue() == 0) {
                        throwGenericError(instruction, "Division by 0 error");
                    }
                    this.executionStack.push(leftDouble.div(rightDouble));
                }
                case DEQ -> this.executionStack.push(leftDouble.equals(rightDouble));
                case DNEQ -> this.executionStack.push(leftDouble.notEquals(rightDouble));
                case DLEQ -> this.executionStack.push(leftDouble.lessThanOrEqual(rightDouble));
                case DLT -> this.executionStack.push(leftDouble.lessThan(rightDouble));
            }
        }
        else
            this.throwTypeError(instruction, Double.class);
    }

    private void stringStackInstruction(Type<?> left, Type<?> right, Instruction instruction)
    {
        if (right instanceof Str rightStr && left instanceof Str leftStr) {
            switch (instruction.getInstruction()) {
                case SADD -> this.executionStack.push(leftStr.concat(rightStr));
                case SEQ  -> this.executionStack.push(leftStr.equals(rightStr));
                case SNEQ -> this.executionStack.push(leftStr.notEquals(rightStr));
            }
        }
        else
            this.throwTypeError(instruction, String.class);
    }

    private void booleanStackInstruction(Type<?> left, Type<?> right, Instruction instruction)
    {
        if (right instanceof Bool rightBoolean && left instanceof Bool leftBoolean) {
            switch (instruction.getInstruction()) {
                case BEQ -> this.executionStack.push(leftBoolean.equals(rightBoolean));
                case BNEQ -> this.executionStack.push(leftBoolean.notEquals(rightBoolean));
                case AND -> this.executionStack.push(leftBoolean.and(rightBoolean));
                case OR -> this.executionStack.push(leftBoolean.or(rightBoolean));
            }
        }
        else
            this.throwTypeError(instruction, Boolean.class);
    }


    private void throwInsufficientStackError(Instruction instruction)
    {
        String message = this.instructionPointer + ":0 " +
                "Stack doesn't have enough elements for '" +
                instruction + "'; Stack: " + this.executionStack;
        System.err.println(message);
        System.exit(1);
    }

    private void throwTypeError(Instruction instruction, Class<?> expectedType)
    {
        String message = this.instructionPointer + ":0 " +
                "Expected " + expectedType.getSimpleName() +
                " type for '" + instruction + "'";
        System.err.println(message);
        System.exit(1);
    }
    private void throwGenericError(Instruction instruction, String errorMessage)
    {
        String message = this.instructionPointer + ":0 " + instruction + " "  + errorMessage;
        System.err.println(message);
        System.exit(1);
    }
    private void throwIndexOutOfBounds(Instruction instruction)
    {
        String message = this.instructionPointer + ":0 " +
                "Index " + instruction.getOperand() +
                " out of bounds for global memory size " +
                this.globalMemory.size() +
                " '" + instruction + "'";
        System.err.println(message);
        System.exit(1);
    }

    public static void main(String[] args) throws IOException
    {
        tVm virtualMachine = new tVm();
        virtualMachine.execute(args);
    }
}
