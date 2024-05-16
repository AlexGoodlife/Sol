package Sol;

import java.util.List;

public class Function
{
    private final Type returnType;
    private final List<Type> argTypes;
    private boolean hasGuaranteedReturn;

    public Function(Type returnType, List<Type> argTypes)
    {
        if (returnType.refDepth() != 0)
            throw new InternalError("Shouldn't happen");

        this.returnType = returnType;
        this.argTypes = argTypes;
        this.hasGuaranteedReturn = false;
    }

    public Type getReturnType()
    {
        return this.returnType;
    }

    public List<Type> getArgTypes()
    {
        return this.argTypes;
    }

    public boolean hasGuaranteedReturn()
    {
        return this.hasGuaranteedReturn;
    }

    public void setHasGuaranteedReturn(boolean hasGuaranteedReturn)
    {
        this.hasGuaranteedReturn = hasGuaranteedReturn;
    }
}
