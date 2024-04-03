package Tasm.types;

public class Double64 extends Type<Double>{
    public Double64(Double num){
        this.value = num;
        this.typeCode = TypeCode.DOUBLE;
    }

    public Double64(Int32 num){
        this.value = (double)num.getValue();
        this.typeCode = TypeCode.DOUBLE;
    }

    @Override
    public int size() {
        return 8;
    }

    public Double64 add(Double64 other){
        return new Double64(this.value + other.value);
    }
    public Double64 negate(){
        return new Double64(-this.value);
    }
    public Double64 mod(Double64 other){
        return new Double64(this.value % other.value);
    }
    public Double64 sub(Double64 other){
        return new Double64(this.value - other.value);
    }

    public Double64 div(Double64 other){
        return new Double64(this.value / other.value);
    }

    public Double64 mult(Double64 other){
        return new Double64(this.value * other.value);
    }

    public Bool equals(Double64 other){
        return new Bool(this.value.equals(other.value));
    }
    public Bool notEquals(Double64 other){
        return new Bool(!this.value.equals(other.value));
    }
    public Bool lessThan(Double64 other){
        return new Bool(this.value.compareTo(other.value) < 0);
    }

    public Bool lessThanOrEqual(Double64 other){
        return new Bool( this.value.compareTo(other.value) <= 0);
    }
}
