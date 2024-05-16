package Sol;

import ErrorUtils.ErrorReporter;
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
        Type returnType = Type.typeOf(ctx.type.getText(), 0,0);
        List<Type> argTypes = new ArrayList<>();
        ctx.argument().forEach((T) -> {
            int arrayDimension = T.LSBRACKET().size();
            argTypes.add(Type.typeOf(T.type.getText(), T.REF().size(),arrayDimension));
        });
        if (id.equals("main") && returnType.type() == Void.class && argTypes.isEmpty())
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
        Function currentFunction = this.functions.get(ctx.IDENTIFIER().getText());
        currentFunction.setHasGuaranteedReturn(hasReturned);
        if (!hasReturned && !currentFunction.getReturnType().type().equals(Void.class))
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