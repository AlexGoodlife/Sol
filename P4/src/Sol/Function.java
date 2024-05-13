package Sol;

import java.util.List;

public class Function
{
    private final Class<?> returnType;
    private final List<Class<?>> argTypes;
    private boolean hasGuaranteedReturn;

    public Function(Class<?> returnType, List<Class<?>> argTypes)
    {
        this.returnType = returnType;
        this.argTypes = argTypes;
        this.hasGuaranteedReturn = false;
    }

    public Class<?> getReturnType()
    {
        return this.returnType;
    }

    public List<Class<?>> getArgTypes()
    {
        return this.argTypes;
    }

    public boolean hasGuaranteedReturn()
    {
        return  this.hasGuaranteedReturn;
    }

    public void setHasGuaranteedReturn(boolean hasGuaranteedReturn)
    {
        this.hasGuaranteedReturn = hasGuaranteedReturn;
    }
}
