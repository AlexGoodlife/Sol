package Sol;

import ErrorUtils.ErrorReporter;
import Tasm.Value;
import antlrSol.*;
import org.antlr.v4.runtime.tree.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class solFunctionChecker extends SolBaseListener
{
    private final ErrorReporter reporter;
    private final HashMap<String, FunctionInfo> functions;

    public solFunctionChecker(ErrorReporter reporter)
    {
        this.reporter = reporter;
        this.functions = new HashMap<>();
    }

    public void exitFunctionDeclaration(SolParser.FunctionDeclarationContext ctx){
        String id = ctx.IDENTIFIER().getText();
        Class<?> returnType = Value.typeOf(ctx.type.getText());
        List<Class<?>> argTypes = new ArrayList<>();
        ctx.argument().forEach((T) ->{
            argTypes.add(Value.typeOf(T.type.getText()));
        });
        FunctionInfo info = new FunctionInfo(id,returnType,argTypes);
        this.functions.put(id, info);
    }

    public void exitIf(SolParser.IfContext ctx){

    }

    public void functionCheck(ParseTree tree)
    {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);
    }
}