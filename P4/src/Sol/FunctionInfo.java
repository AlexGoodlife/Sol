package Sol;

import java.util.List;

public class FunctionInfo {
    String identifier;
    Class<?> returnType;
    List<Class<?>> argTypes;

    public FunctionInfo(String identifier, Class<?> returnType, List<Class<?>> argTypes){
        this.identifier = identifier;
        this.returnType = returnType;
        this.argTypes = argTypes;
    }

}
