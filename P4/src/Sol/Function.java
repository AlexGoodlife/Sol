package Sol;

import java.util.List;

public class Function
{
    private final String identifier;
    private final Class<?> returnType;
    private final List<Class<?>> argTypes;

    public Function(String identifier, Class<?> returnType, List<Class<?>> argTypes)
    {
        this.identifier = identifier;
        this.returnType = returnType;
        this.argTypes = argTypes;
    }

    public String getIdentifier()
    {
        return this.identifier;
    }

    public Class<?> getReturnType()
    {
        return this.returnType;
    }

    public List<Class<?>> getArgTypes()
    {
        return this.argTypes;
    }

    @Override
    public boolean equals(Object that)
    {
        if (that == this)
            return true;
        if (!(that instanceof Function thatFunction))
            return false;

        return this.identifier.equals(thatFunction.identifier);
    }

    @Override
    public int hashCode()
    {
        return this.identifier.hashCode();
    }
}
