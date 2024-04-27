package Tasm;

public class Instruction
{
    private static final int TO_DEFINE = -1;

    private final InstructionCode instruction;
    private Integer operand;

    public Instruction(InstructionCode instruction, Integer operand)
    {
        this.instruction = instruction;
        this.operand = operand;
    }

    public Instruction(InstructionCode instruction)
    {
        this(instruction, null);
    }

    public InstructionCode getInstruction()
    {
        return this.instruction;
    }

    public Integer getOperand()
    {
        return this.operand;
    }
    
    public void backPatch(int newOperand)
    {
        if (this.operand == TO_DEFINE)
            this.operand = newOperand;
    }

    @Override
    public String toString()
    {
        String s = this.instruction.name().toLowerCase();
        return this.operand != null ? s + " " + this.operand : s;
    }
}
