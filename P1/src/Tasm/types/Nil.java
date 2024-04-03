package Tasm.types;

public class Nil extends Type<Void>{
    @Override
    public int size() {
        return 0;
    }

    @Override
    public String toString(){
        return "Nil";
    }
}
