package Sol;

import java.util.List;

public class Function
{
    private final Class<?> returnType;
    private final List<Class<?>> argTypes;

    public Function(Class<?> returnType, List<Class<?>> argTypes)
    {
        this.returnType = returnType;
        this.argTypes = argTypes;
    }


    public Class<?> getReturnType()
    {
        return this.returnType;
    }

    public List<Class<?>> getArgTypes()
    {
        return this.argTypes;
    }

}
