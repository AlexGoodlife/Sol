package Sol;

import ErrorUtils.ErrorReporter;
import Tasm.Value;
import antlrSol.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class solFunctionChecker extends SolBaseListener
{
    private final ErrorReporter reporter;
    private final HashMap<String,Function> functions;
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
        this.functions.put(id,function);
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx)
    {
        if (ctx instanceof SolParser.InstructionContext || ctx instanceof SolParser.ScopeContext)
            this.annotatedReturns.put(ctx, ctx instanceof SolParser.ReturnContext);
    }

    @Override
    public void exitScope(SolParser.ScopeContext ctx)
    {
        boolean hasReturn = this.annotatedReturns.get(ctx);
        for (SolParser.InstructionContext instruction : ctx.instruction())
        {
            hasReturn |= this.annotatedReturns.get(instruction);
            if (hasReturn)
                break;
        }
        this.annotatedReturns.put(ctx, hasReturn);
    }

    @Override
    public void exitBlock(SolParser.BlockContext ctx)
    {
        this.annotatedReturns.put(ctx, this.annotatedReturns.get(ctx.scope()));
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
        Boolean isReturned = this.annotatedReturns.get(ctx.scope());
        if (isReturned == null || !isReturned && !Value.typeOf(ctx.type.getText()).equals(Void.class))
            this.reporter.reportError(ctx, "Function might not always reach a return instruction");
    }

    public void functionCheck(ParseTree tree)
    {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);

        if (!this.hasMain)
            this.reporter.reportError(null, "Program doesn't include a main function");
    }

    public static void main(String[] args)
    {

        InputStream inputStream = null;
        try {
            ErrorReporter rep = new ErrorReporter();
            solFunctionChecker checker = new solFunctionChecker(rep);
            inputStream = new FileInputStream("inputs/returnTest.sol");
            CharStream input = CharStreams.fromStream(inputStream);
            SolLexer lexer = new SolLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ParseTree tree = new SolParser(tokens).program();
            checker.functionCheck(tree);
            System.out.println(rep);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}