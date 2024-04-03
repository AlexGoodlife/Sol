package Tasm.types;

public abstract class Type<T> {
    TypeCode typeCode;
    T value;
    public TypeCode getTypeCode(){
        return this.typeCode;
    }

    public T getValue(){
        return this.value;
    }

    @Override
    public String toString(){
        return this.value.toString();
    }

    @Override
    public boolean equals(Object other){
        if(other.getClass() != this.getClass())
            return false;
        return this.value.equals(((Type<?>) other).value);
    }
    @Override
    public int hashCode(){
        return this.value.hashCode();
    }
    public abstract int size();
}
