package Sol;

public record Type(Class<?> type, int refDepth, int arrayDim)
{
    public Type(Class<?> type, int refDepth, int arrayDim){
        this.type = type;
        this.refDepth = refDepth;
        this.arrayDim = arrayDim;
    }

   // public Type(Class<?> type, int refDepth){
   //     this(type,refDepth,0);
   // }
    public boolean isRef()
    {
        return this.refDepth > 0 || this.arrayDim > 0;
    }

    public static Type typeOf(String type, int refDepth, int arrayDim)
    {
        switch (type)
        {
            case "int" -> { return new Type(Integer.class, refDepth, arrayDim); }
            case "real" -> { return new Type(Double.class, refDepth,arrayDim); }
            case "string" -> { return new Type(String.class, refDepth,arrayDim); }
            case "bool" -> { return new Type(Boolean.class, refDepth,arrayDim); }
            case "void" -> { return new Type(Void.class, 0,0); }
            default -> throw new InternalError("Shouldn't happen...");
        }
    }
    //public static Type typeOf(String type, int refDepth)
    //{
    //    return typeOf(type, refDepth,0);
    //}

    @Override
    public boolean equals(Object that)
    {
        if (that == this)
            return true;
        if (!(that instanceof Type thatType))
            return false;

        return this.type == thatType.type && this.refDepth == thatType.refDepth && this.arrayDim == thatType.arrayDim;
    }
}
