package Sol;

import ErrorUtils.ErrorReporter;
import Tasm.Value;
import antlrSol.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class solFunctionChecker extends SolBaseListener
{
    private final ErrorReporter reporter;
    private final HashMap<String, Function> functions;
    private final ParseTreeProperty<Boolean> annotatedReturns;
    private boolean hasMain;

    public solFunctionChecker(ErrorReporter reporter)
    {
        this.reporter = reporter;
        this.functions = new HashMap<>();
        this.annotatedReturns = new ParseTreeProperty<>();
        this.hasMain = false;
    }

    public HashMap<String,Function> getFunctions()
    {
        return this.functions;
    }

    @Override
    public void enterFunctionDeclaration(SolParser.FunctionDeclarationContext ctx)
    {
        String id = ctx.IDENTIFIER().getText();
        Class<?> returnType = Value.typeOf(ctx.type.getText());
        List<Class<?>> argTypes = new ArrayList<>();
        ctx.argument().forEach((T) -> argTypes.add(Value.typeOf(T.type.getText())));
        if (id.equals("main") && returnType == Void.class && argTypes.isEmpty())
            this.hasMain = true;
        Function function = new Function(returnType, argTypes);
        if (this.functions.containsKey(id))
            this.reporter.reportError(ctx, "Declaring already declared function");
        else
            this.functions.put(id, function);
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx)
    {
        if (ctx instanceof SolParser.InstructionContext)
            this.annotatedReturns.put(ctx, ctx instanceof SolParser.ReturnContext);
    }

    @Override
    public void exitScope(SolParser.ScopeContext ctx)
    {
        boolean hasReturn = false;
        for (SolParser.InstructionContext instruction : ctx.instruction())
        {
            hasReturn = this.annotatedReturns.get(instruction);
            if (hasReturn)
                break;
        }
        this.annotatedReturns.put(ctx, hasReturn);
    }

    @Override
    public void exitBlock(SolParser.BlockContext ctx)
    {
        Boolean hasReturned = this.annotatedReturns.get(ctx.scope());
        if (hasReturned == null)
            return;
        this.annotatedReturns.put(ctx, hasReturned);
    }

    @Override
    public void exitIf(SolParser.IfContext ctx)
    {
        if (ctx.ELSE() == null)
            return;

        this.annotatedReturns.put(ctx, this.annotatedReturns.get(ctx.instruction(0)) && this.annotatedReturns.get(ctx.instruction(1)));
    }

    @Override
    public void exitFunctionDeclaration(SolParser.FunctionDeclarationContext ctx)
    {
        Boolean hasReturned = this.annotatedReturns.get(ctx.scope());
        if (hasReturned == null)
            return;
        this.functions.get(ctx.IDENTIFIER().getText()).setHasGuaranteedReturn(hasReturned);
        if (!hasReturned && !Value.typeOf(ctx.type.getText()).equals(Void.class))
            this.reporter.reportError(ctx, "Function might not always reach a return instruction");
    }

    public void functionCheck(ParseTree tree)
    {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);

        if (!this.hasMain)
            this.reporter.reportError(null, "Program doesn't include a main function");
    }
}