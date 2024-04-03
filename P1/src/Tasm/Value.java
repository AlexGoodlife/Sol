package Tasm;

public class Value
{
    private static final Class<?>[] SUPPORTED_TYPES = {Integer.class, Double.class, String.class, Boolean.class};

    private final Object value;

    public Value(Object value)
    {
        if (this.isInvalidType(value))
            this.throwInvalidTypeError();
        this.value = value;
    }

    private boolean isInvalidType(Object value)
    {
        boolean result = true;
        for (Class<?> c : SUPPORTED_TYPES)
            if (c.isInstance(value))
            {
                result = false;
                break;
            }
        return result;
    }

    private void throwInvalidTypeError()
    {
        System.err.println("Invalid type");
        System.exit(1);
    }

    public Object getValue()
    {
        return this.value;
    }

    @Override
    public String toString()
    {
        return this.value == null ? "NIL" : this.value.toString();
    }

    @Override
    public boolean equals(Object that)
    {
        if (that == this)
            return true;
        if (!(that instanceof Value thatValue))
            return false;

        return this.value.equals(thatValue.value);
    }

    @Override
    public int hashCode()
    {
        return this.value.hashCode();
    }
}
