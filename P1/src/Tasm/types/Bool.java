package Tasm.types;

public class Bool extends Type<Boolean>{
    public Bool(Boolean num){
        this.value = num;
        this.typeCode = TypeCode.BOOLEAN;
    }

    @Override
    public int size() {
        return 1;
    }

    public Bool equals(Bool other){
        return new Bool(this.value.equals(other.value));
    }
    public Bool notEquals(Bool other){
        return new Bool(!this.value.equals(other.value));
    }
    public Bool or(Bool other){
        return new Bool(this.value || other.value);
    }

    public Bool and(Bool other){
        return new Bool(this.value && other.value);
    }
    public Bool not(){
        return new Bool(!this.value);
    }

}
