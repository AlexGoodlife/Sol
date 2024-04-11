package Tasm;

public class Instruction
{
    private final InstructionCode instruction;
    private final Integer operand;

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

    @Override
    public String toString()
    {
        String s = this.instruction.name().toLowerCase();
        return this.operand != null ? s + " " + this.operand : s;
    }
}
