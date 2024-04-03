package Tasm.types;

import java.nio.charset.StandardCharsets;

public class Str extends Type<String>{
    public Str(String s){
        this.value = s;
        this.typeCode = TypeCode.STRING;
    }
    public Str(Int32 s){
        this.value = String.valueOf(s.value);
        this.typeCode = TypeCode.STRING;
    }

    public Str(Double64 s){
        this.value = String.valueOf(s.value);
        this.typeCode = TypeCode.STRING;
    }
    public Str(Bool s){
        this.value = String.valueOf(s.value);
        this.typeCode = TypeCode.STRING;
    }
    @Override
    public int size() {
        String val = this.value; // We know we are storing strings
        return val.getBytes(StandardCharsets.UTF_8).length;
    }

    public Bool equals(Str other){
        return new Bool(this.value.equals(other.value));
    }

    public Str concat(Str other){
        return new Str(this.value + other.value);
    }

    public Bool notEquals(Str other) {
        return new Bool(!this.value.equals(other.value));
    }
}

