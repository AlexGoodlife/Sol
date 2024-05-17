package Sol;

public record Type(Class<?> type, int refDepth)
{
    public Type(Class<?> type)
    {
        this(type, 0);
    }

    public boolean isRef()
    {
        return this.refDepth > 0;
    }

    public static Type getPrimitiveType(String type)
    {
        switch (type)
        {
            case "int" -> { return new Type(Integer.class, 0); }
            case "real" -> { return new Type(Double.class, 0); }
            case "string" -> { return new Type(String.class, 0); }
            case "bool" -> { return new Type(Boolean.class, 0); }
            case "void" -> { return new Type(Void.class, 0); }
            default -> throw new InternalError("Shouldn't happen...");
        }
    }

    public static Type getReferenceType(String type, int refDepth)
    {
        switch (type)
        {
            case "int" -> { return new Type(Integer.class, refDepth); }
            case "real" -> { return new Type(Double.class, refDepth); }
            case "string" -> { return new Type(String.class, refDepth); }
            case "bool" -> { return new Type(Boolean.class, refDepth); }
            default -> throw new InternalError("Shouldn't happen...");
        }
    }

    @Override
    public boolean equals(Object that)
    {
        if (that == this)
            return true;
        if (!(that instanceof Type thatType))
            return false;

        return this.type == thatType.type && this.refDepth == thatType.refDepth;
    }
}
