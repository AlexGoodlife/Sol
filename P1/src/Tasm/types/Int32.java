package Tasm.types;

public class Int32 extends Type<Integer>{
    public Int32(Integer num){
        this.value = num;
        this.typeCode = TypeCode.INTEGER;
    }

    @Override
    public int size() {
        return 4;
    }
    public Int32 add(Int32 other){
        return new Int32(this.value + other.value);
    }
    public Int32 negate(){
        return new Int32(-this.value);
    }
    public Int32 sub(Int32 other){
        return new Int32(this.value - other.value);
    }

    public Int32 mod(Int32 other){
        return new Int32(this.value % other.value);
    }
    public Bool notEquals(Int32 other){
        return new Bool(!this.value.equals(other.value));
    }
    public Int32 div(Int32 other){
        return new Int32(this.value / other.value);
    }

    public Int32 mult(Int32 other){
        return new Int32(this.value * other.value);
    }

    public Bool equals(Int32 other){
        return new Bool(this.value.equals(other.value));
    }
    public Bool lessThan(Int32 other){
        return new Bool(this.value.compareTo(other.value) < 0);
    }

    public Bool lessThanOrEqual(Int32 other){
        return new Bool( this.value.compareTo(other.value) <= 0);
    }
}
