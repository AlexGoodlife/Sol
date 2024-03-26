public class Instruction
{
    private final OperationCode instruction;
    private final Integer operand;

    public Instruction(OperationCode instruction, Integer operand)
    {
        this.instruction = instruction;
        this.operand = operand;
    }

    public Instruction(OperationCode instruction)
    {
        this(instruction, null);
    }

    public OperationCode getInstruction()
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
