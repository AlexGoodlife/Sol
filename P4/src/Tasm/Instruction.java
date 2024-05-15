package Tasm;

public class Instruction
{
    public static final int TO_DEFINE = -1;

    private final Code instruction;
    private Integer operand;

    public Instruction(Code instruction, Integer operand)
    {
        this.instruction = instruction;
        this.operand = operand;
    }

    public Instruction(Code instruction)
    {
        this(instruction, null);
    }

    public Code getInstruction()
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

    public enum Code
    {
        ICONST, DCONST, SCONST,
        JUMP, JUMPT, JUMPF, CALL,
        GALLOC, GSTORE, GLOAD,
        LALLOC, LSTORE, LLOAD, POP,
        RETVAL, RET,
        //Simple instructions
        IPRINT, IUMINUS, IADD, ISUB, IMULT, IDIV, IMOD, IEQ, INEQ, ILT, ILEQ, ITOD, ITOS,
        DPRINT, DUMINUS, DADD, DSUB, DMULT, DDIV, DEQ, DNEQ, DLT, DLEQ, DTOS,
        SPRINT, SADD, SEQ, SNEQ,
        TCONST, FCONST, BPRINT, BEQ, BNEQ, AND, OR, NOT, BTOS,
        RPRINT, GREF, LREF, DREF, REFSTORE,
        HALT;

        public static boolean hasArgument(Code code)
        {
            return code.ordinal() <= RET.ordinal();
        }
    }
}
