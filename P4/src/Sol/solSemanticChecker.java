package Sol;

import ErrorUtils.ErrorReporter;
import Tasm.Value;
import antlrSol.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.*;

import java.util.HashMap;
import java.util.List;

public class solSemanticChecker extends SolBaseListener
{
    private static final String TYPE_MISMATCH_ERROR_MESSAGE = "Type mismatch in operation";
    private static final String DECLARED_VAR_ERROR_MESSAGE = "Declaring already declared variable in";
    private static final String UNDECLARED_VAR_ERROR_MESSAGE = "Undeclared variable in";
    private static final String OUT_OF_LOOP_BREAK_ERROR_MESSAGE = "Breaking out of loop in";
    private static final String INVALID_RETURN_TYPE_MESSAGE = "Invalid return type for function";
    private static final String WRONG_ARGUMENT_COUNT_MESSAGE = "Function call has wrong amount of passed arguments";
    private static final String UNDECLARED_FUNCTION_ERROR_MESSAGE = "Undeclared function in";
    private static final String IGNORED_RETURN_VALUE_ERROR_MESSAGE = "Return value is being ignored on";
    private static final String VOID_RETURN_VALUE_ERROR_MESSAGE = "Void function in an expression";

    private final ErrorReporter reporter;
    private final ParseTreeProperty<Class<?>> annotatedTypes;
    private ScopeTree scope;
    private final ParseTreeProperty<ScopeTree> annotatedScopes;
    private HashMap<String,Function> functions;
    private Function currentFunction;
    private int nestedLoopCount;

    public solSemanticChecker(ErrorReporter reporter)
    {
        this.reporter = reporter;
        this.annotatedTypes = new ParseTreeProperty<>();
        this.scope = new ScopeTree();
        this.annotatedScopes = new ParseTreeProperty<>();
        this.nestedLoopCount = 0;
    }

    public ParseTreeProperty<Class<?>> getAnnotatedTypes()
    {
        return this.annotatedTypes;
    }

    public ScopeTree getScope()
    {
        // Ensure we are at root
        while (this.scope.getParent() != null)
            this.scope = (ScopeTree) this.scope.getParent();
        return this.scope;
    }

    public ParseTreeProperty<ScopeTree> getScopeAnnotations()
    {
        return this.annotatedScopes;
    }

    public HashMap<String, Function> getFunctions()
    {
        return this.functions;
    }

    @Override
    public void exitInt(SolParser.IntContext ctx)
    {
        this.annotatedTypes.put(ctx, Integer.class);
    }

    @Override
    public void exitDouble(SolParser.DoubleContext ctx)
    {
        this.annotatedTypes.put(ctx, Double.class);
    }

    @Override
    public void exitString(SolParser.StringContext ctx)
    {
        this.annotatedTypes.put(ctx, String.class);
    }

    @Override
    public void exitBoolean(SolParser.BooleanContext ctx)
    {
        this.annotatedTypes.put(ctx, Boolean.class);
    }

    @Override
    public void exitIdentifier(SolParser.IdentifierContext ctx)
    {
        Class<?> variableType = this.scope.getVariable(ctx.IDENTIFIER().getText()).type();
        if (variableType != null)
            this.annotatedTypes.put(ctx, variableType);
        else
            this.reporter.reportError(ctx, UNDECLARED_VAR_ERROR_MESSAGE);
    }

    @Override
    public void exitOr(SolParser.OrContext ctx)
    {
        this.exitLogical(ctx, ctx.expr(0), ctx.expr(1));
    }

    @Override
    public void exitAnd(SolParser.AndContext ctx)
    {
        this.exitLogical(ctx, ctx.expr(0), ctx.expr(1));
    }

    private void exitLogical(SolParser.ExprContext ctx, SolParser.ExprContext left, SolParser.ExprContext right)
    {
        Class<?> leftType = this.annotatedTypes.get(left);
        Class<?> rightType = this.annotatedTypes.get(right);
        if (leftType == null || rightType == null)
            return;

        boolean isLeftBoolean = leftType == Boolean.class;
        boolean isRightBoolean = rightType == Boolean.class;
        if (isLeftBoolean && isRightBoolean)
            this.annotatedTypes.put(ctx, Boolean.class);
        else
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
    }

    @Override
    public void exitEquality(SolParser.EqualityContext ctx)
    {
        Class<?> leftType = this.annotatedTypes.get(ctx.expr(0));
        Class<?> rightType = this.annotatedTypes.get(ctx.expr(1));
        if (leftType == null || rightType == null)
            return;

        boolean isLeftNumber = leftType == Integer.class || leftType == Double.class;
        boolean isRightNumber = rightType == Integer.class || rightType == Double.class;
        if (leftType == rightType || (isLeftNumber && isRightNumber))
            this.annotatedTypes.put(ctx, Boolean.class);
        else
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
    }

    @Override
    public void exitRelational(SolParser.RelationalContext ctx)
    {
        Class<?> leftType = this.annotatedTypes.get(ctx.expr(0));
        Class<?> rightType = this.annotatedTypes.get(ctx.expr(1));
        if (leftType == null || rightType == null)
            return;

        boolean isLeftNumber = leftType == Integer.class || leftType == Double.class;
        boolean isRightNumber = rightType == Integer.class || rightType == Double.class;
        if (isLeftNumber && isRightNumber)
            this.annotatedTypes.put(ctx, Boolean.class);
        else
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
    }

    @Override
    public void exitAddSub(SolParser.AddSubContext ctx)
    {
        if (ctx.op.getText().equals("+"))
            this.exitAdd(ctx);
        else
            this.exitArithmetic(ctx, ctx.expr(0), ctx.expr(1));
    }

    private void exitAdd(SolParser.AddSubContext ctx)
    {
        Class<?> leftType = this.annotatedTypes.get(ctx.expr(0));
        Class<?> rightType = this.annotatedTypes.get(ctx.expr(1));
        if (leftType == null || rightType == null)
            return;

        boolean isLeftString = leftType == String.class;
        boolean isRightString = rightType == String.class;
        boolean isLeftNumber = leftType == Integer.class || leftType == Double.class;
        boolean isRightNumber = rightType == Integer.class || rightType == Double.class;
        if ((isLeftString || isRightString) || (isLeftNumber && isRightNumber))
            this.annotateAddType(ctx, leftType, rightType);
        else
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
    }

    private void annotateAddType(SolParser.AddSubContext ctx, Class<?> leftType, Class<?> rightType)
    {
        boolean isLeftString = leftType == String.class;
        boolean isRightString = rightType == String.class;
        boolean isLeftDouble = leftType == Double.class;
        boolean isRightDouble = rightType == Double.class;
        if (isLeftString || isRightString)
            this.annotatedTypes.put(ctx, String.class);
        else if (isLeftDouble || isRightDouble)
            this.annotatedTypes.put(ctx, Double.class);
        else
            this.annotatedTypes.put(ctx, Integer.class);
    }

    private void exitArithmetic(SolParser.ExprContext ctx, SolParser.ExprContext left, SolParser.ExprContext right)
    {
        Class<?> leftType = this.annotatedTypes.get(left);
        Class<?> rightType = this.annotatedTypes.get(right);
        if (leftType == null || rightType == null)
            return;

        boolean isLeftNumber = leftType == Integer.class || leftType == Double.class;
        boolean isRightNumber = rightType == Integer.class || rightType == Double.class;
        if (isLeftNumber && isRightNumber)
            this.annotateArithmeticType(ctx, leftType, rightType);
        else
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
    }

    private void annotateArithmeticType(SolParser.ExprContext ctx, Class<?> leftType, Class<?> rightType)
    {
        boolean isLeftDouble = leftType == Double.class;
        boolean isRightDouble = rightType == Double.class;
        if (isLeftDouble || isRightDouble)
            this.annotatedTypes.put(ctx, Double.class);
        else
            this.annotatedTypes.put(ctx, Integer.class);
    }

    @Override
    public void exitMultDivMod(SolParser.MultDivModContext ctx)
    {
        if (ctx.op.getText().matches("[*/]"))
            this.exitArithmetic(ctx, ctx.expr(0), ctx.expr(1));
        else if (ctx.op.getText().equals("%"))
            this.exitMod(ctx);
    }

    private void exitMod(SolParser.MultDivModContext ctx)
    {
        Class<?> leftType = this.annotatedTypes.get(ctx.expr(0));
        Class<?> rightType = this.annotatedTypes.get(ctx.expr(1));
        if (leftType == null || rightType == null)
            return;

        boolean isLeftInteger = leftType == Integer.class;
        boolean isRightInteger = rightType == Integer.class;
        if (isLeftInteger && isRightInteger)
            this.annotatedTypes.put(ctx, Integer.class);
        else
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
    }

    @Override
    public void exitNegation(SolParser.NegationContext ctx)
    {
        if (ctx.op.getText().equals("-"))
            this.exitNumberNegation(ctx);
        else
            this.exitNot(ctx);
    }

    private void exitNumberNegation(SolParser.NegationContext ctx)
    {
        Class<?> exprType = this.annotatedTypes.get(ctx.expr());
        if (exprType == null)
            return;

        boolean isExprNumber = exprType == Integer.class || exprType == Double.class;
        if (isExprNumber)
            this.annotatedTypes.put(ctx, exprType);
        else
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
    }

    private void exitNot(SolParser.NegationContext ctx)
    {
        Class<?> exprType = this.annotatedTypes.get(ctx.expr());
        if (exprType == null)
            return;

        boolean isExprBoolean = exprType == Boolean.class;
        if (isExprBoolean)
            this.annotatedTypes.put(ctx, exprType);
        else
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
    }

    public void exitParentheses(SolParser.ParenthesesContext ctx)
    {
        this.annotatedTypes.put(ctx, this.annotatedTypes.get(ctx.expr()));
    }

    @Override
    public void enterDeclaration(SolParser.DeclarationContext ctx)
    {
        this.annotatedTypes.put(ctx, Value.typeOf(ctx.type.getText()));
    }

    @Override
    public void exitDeclarationAssign(SolParser.DeclarationAssignContext ctx)
    {
        if (ctx.IDENTIFIER() == null)
            return;

        String variableName = ctx.IDENTIFIER().getText();
        if (this.scope.containsVariableLocal(variableName)) // Variable already declared in local scope
        {
            this.reporter.reportError(ctx, DECLARED_VAR_ERROR_MESSAGE);
            return;
        }

        Class<?> variableType = this.annotatedTypes.get(ctx.getParent());
        if (variableType == null)
            throw new InternalError("Variable has no type after declaration? Shouldn't happen");

        if (ctx.expr() != null)
        {
            Class<?> exprType = this.annotatedTypes.get(ctx.expr());
            if (exprType != null && !compatibleTypes(variableType, exprType))
                this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
        }
        this.scope.putVariable(variableName, variableType);
    }

    private static boolean compatibleTypes(Class<?> variableType, Class<?> expressionType)
    {
        boolean isVariableDouble = variableType == Double.class;
        boolean isExpressionNumber = expressionType == Integer.class || expressionType == Double.class;
        return variableType == expressionType || (isVariableDouble && isExpressionNumber);
    }

    @Override
    public void exitAssign(SolParser.AssignContext ctx)
    {
        if (ctx.IDENTIFIER() == null)
            return;

        String variableName = ctx.IDENTIFIER().getText();
        if (!this.scope.containsVariable(variableName))
        {
            this.reporter.reportError(ctx, UNDECLARED_VAR_ERROR_MESSAGE);
            return;
        }

        Class<?> variableType = this.scope.getVariable(variableName).type();
        Class<?> exprType = this.annotatedTypes.get(ctx.expr());
        if (exprType == null)
            return;

        if (compatibleTypes(variableType, exprType))
            this.annotatedTypes.put(ctx, variableType);
        else
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
    }

    @Override
    public void enterWhile(SolParser.WhileContext ctx)
    {
        this.nestedLoopCount++;
    }

    @Override
    public void exitWhile(SolParser.WhileContext ctx)
    {
        this.nestedLoopCount--;
        Class<?> exprType = this.annotatedTypes.get(ctx.expr());
        if (exprType == null)
            return;

        if (exprType != Boolean.class)
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
    }

    @Override
    public void enterFor(SolParser.ForContext ctx)
    {
        this.nestedLoopCount++;
    }

    @Override
    public void exitFor(SolParser.ForContext ctx)
    {
        this.nestedLoopCount--;
        Class<?> variableType = this.annotatedTypes.get(ctx.assign());
        Class<?> exprType = this.annotatedTypes.get(ctx.expr());
        if (variableType == null || exprType == null)
            return;

        if (variableType != Integer.class || exprType != Integer.class)
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
    }

    @Override
    public void exitIf(SolParser.IfContext ctx)
    {
        Class<?> exprType = this.annotatedTypes.get(ctx.expr());
        if (exprType == null)
            return;

        if (exprType != Boolean.class)
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
    }

    @Override
    public void exitBreak(SolParser.BreakContext ctx)
    {
        if (this.nestedLoopCount == 0)
            this.reporter.reportError(ctx, OUT_OF_LOOP_BREAK_ERROR_MESSAGE);
    }

    @Override
    public void enterFunctionDeclaration(SolParser.FunctionDeclarationContext ctx)
    {
        this.currentFunction = this.functions.get(ctx.IDENTIFIER().getText());
    }

    @Override
    public void exitReturn(SolParser.ReturnContext ctx)
    {
        Class<?> cachedType = this.annotatedTypes.get(ctx.expr());
        Class<?> returnType = cachedType != null ? cachedType : Void.class;
        if (!compatibleTypes(this.currentFunction.getReturnType(), returnType))
            this.reporter.reportError(ctx, INVALID_RETURN_TYPE_MESSAGE);
    }

    private void checkFunctionArguments(ParserRuleContext ctx, Function function, List<SolParser.ExprContext> args)
    {
        int expectedArgs = function.getArgTypes().size();
        if (args.size() != expectedArgs)
        {
            this.reporter.reportError(ctx, WRONG_ARGUMENT_COUNT_MESSAGE);
            return;
        }

        List<Class<?>> argTypes = function.getArgTypes();
        for (int i = 0; i < expectedArgs; i++)
        {
            Class<?> type = this.annotatedTypes.get(args.get(i));
            if (type == null)
                return;
            if (!compatibleTypes(argTypes.get(i), type))
                this.reporter.reportError(args.get(i), TYPE_MISMATCH_ERROR_MESSAGE);
        }
    }

    @Override
    public void exitVoidFunctionCall(SolParser.VoidFunctionCallContext ctx)
    {
        Function function = this.functions.get(ctx.IDENTIFIER().getText());
        if (function == null)
        {
            this.reporter.reportError(ctx, UNDECLARED_FUNCTION_ERROR_MESSAGE);
            return;
        }
        if (function.getReturnType() != Void.class)
        {
            this.reporter.reportError(ctx, IGNORED_RETURN_VALUE_ERROR_MESSAGE);
            return;
        }
        this.checkFunctionArguments(ctx, function, ctx.expr());
    }

    @Override
    public void exitNonVoidFunctionCall(SolParser.NonVoidFunctionCallContext ctx)
    {
        Function function = this.functions.get(ctx.IDENTIFIER().getText());
        if (function == null)
        {
            this.reporter.reportError(ctx, UNDECLARED_FUNCTION_ERROR_MESSAGE);
            return;
        }
        if (function.getReturnType() == Void.class)
        {
            this.reporter.reportError(ctx, VOID_RETURN_VALUE_ERROR_MESSAGE);
            return;
        }
        this.checkFunctionArguments(ctx, function, ctx.expr());

        this.annotatedTypes.put(ctx, function.getReturnType());
    }

    @Override
    public void enterScope(SolParser.ScopeContext ctx)
    {
        this.scope.addChild(new ScopeTree(this.scope));
        this.scope = this.scope.getRightmostChild();
        this.annotatedScopes.put(ctx, this.scope);
        ParserRuleContext parent = ctx.getParent();
        if (parent instanceof SolParser.FunctionDeclarationContext) // Because we are using a listener we have to add the argument types after the created scope
        {
           List<SolParser.ArgumentContext> args = ((SolParser.FunctionDeclarationContext) parent).argument();
           this.scope.offset(-args.size());
           args.forEach((arg) -> this.scope.putVariable(arg.IDENTIFIER().getText(), Value.typeOf(arg.type.getText())));
            this.scope.offset(2);
        }
    }

    @Override
    public void exitScope(SolParser.ScopeContext ctx)
    {
        this.scope = (ScopeTree) this.scope.getParent(); // Back track on our scopes when moving because global scope is not classified as such we never go beyond root
    }

    public void semanticCheck(ParseTree tree)
    {
        solFunctionChecker funcChecker = new solFunctionChecker(this.reporter);
        funcChecker.functionCheck(tree);
        this.functions = funcChecker.getFunctions();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);
    }
}