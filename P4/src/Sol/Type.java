package Sol;

public record Type(Class<?> type, int refDepth, int arrDimension)
{
    public Type(Class<?> type)
    {
        this(type, 0, 0);
    }

    public boolean isRef()
    {
        return this.refDepth > 0 || this.arrDimension > 0;
    }

    public boolean isArr()
    {
        return this.arrDimension > 0;
    }

    public static Type getType(String type, int refDepth, int arrDimension)
    {
        switch (type)
        {
            case "int" -> { return new Type(Integer.class, refDepth, arrDimension); }
            case "real" -> { return new Type(Double.class, refDepth, arrDimension); }
            case "string" -> { return new Type(String.class, refDepth, arrDimension); }
            case "bool" -> { return new Type(Boolean.class, refDepth, arrDimension); }
            case "void" -> { return new Type(Void.class, 0, 0); }
            default -> throw new InternalError("Shouldn't happen...");
        }
    }

    public static Type getPrimitiveType(String type)
    {
        return getType(type, 0, 0);
    }

    @Override
    public boolean equals(Object that)
    {
        if (that == this)
            return true;
        if (!(that instanceof Type thatType))
            return false;

        return this.type == thatType.type &&
                this.refDepth == thatType.refDepth &&
                this.arrDimension == thatType.arrDimension;
    }
}
