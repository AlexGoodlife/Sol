package Tasm;

import ErrorUtils.RuntimeError;
import java.util.Objects;

public class Value
{
    private static final Class<?>[] SUPPORTED_TYPES = {Integer.class, Double.class, String.class, Boolean.class};
    private final Object value;

    public Value(Object value)
    {
        if (this.isInvalidType(value))
            this.invalidTypeError(value.getClass());
        this.value = value;
    }

    private boolean isInvalidType(Object value)
    {
        if (value == null)
            return false;

        boolean result = true;
        for (Class<?> c : SUPPORTED_TYPES)
            if (c.isInstance(value))
            {
                result = false;
                break;
            }
        return result;
    }

    private void invalidTypeError(Class<?> c)
    {
        RuntimeError.dispatchError("Invalid type " + c.getSimpleName());
    }

    public Object getValue()
    {
        return this.value;
    }

    public static Class<?> typeOf(String type)
    {
        switch (type)
        {
            case "int" -> { return Integer.class; }
            case "real" -> { return Double.class; }
            case "string" -> { return String.class; }
            case "bool" -> { return Boolean.class; }
            case "void" -> { return Void.class; }
            default -> throw new InternalError("Shouldn't happen...");
        }
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

        return Objects.equals(this.value, thatValue.value);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(this.value);
    }
}
