package Sol;

import ErrorUtils.ErrorReporter;
import Tasm.Value;
import antlrSol.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class solSemanticChecker extends SolBaseListener
{
    private static final String TYPE_MISMATCH_ERROR_MESSAGE = "Type mismatch in operation";
    private static final String DECLARED_VAR_ERROR_MESSAGE = "Declaring already declared variable in";
    private static final String UNDECLARED_VAR_ERROR_MESSAGE = "Undeclared variable in";
    private static final String OUT_OF_LOOP_BREAK_ERROR_MESSAGE = "Breaking out of loop in";

    private static final String INVALID_RETURN_TYPE_MESSAGE = "Invalid return type for function";

    private final ErrorReporter reporter;
    private final ParseTreeProperty<Class<?>> annotatedTypes;
    //private final HashMap<String, Class<?>> variableTypes;

    private ScopeTree scope;
    private int nestedLoopCount;
    private Class<?> currentFunctionReturnType;
    private HashMap<String,Function> functions;


    public solSemanticChecker(ErrorReporter reporter)
    {
        this.reporter = reporter;
        this.annotatedTypes = new ParseTreeProperty<>();
        //this.variableTypes = new HashMap<>();
        this.nestedLoopCount = 0;
        this.scope = new ScopeTree();
    }

    public ParseTreeProperty<Class<?>> getAnnotatedTypes()
    {
        return this.annotatedTypes;
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
        // Class<?> variableType = this.variableTypes.get(ctx.IDENTIFIER().getText());
        Class<?> variableType = this.scope.getVariable(ctx.IDENTIFIER().getText());
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

        if (compatibleTypes(leftType, rightType))
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
        this.scope.putVariable(variableName,variableType);
    }

    private static boolean compatibleTypes(Class<?> type1, Class<?> type2)
    {
        boolean isType1Number = type1 == Integer.class || type1 == Double.class;
        boolean isType2Number = type2 == Integer.class || type2 == Double.class;
        return type1 == type2 || (isType1Number && isType2Number);
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

        Class<?> variableType = this.scope.getVariable(variableName);
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
    public void enterFunctionDeclaration(SolParser.FunctionDeclarationContext ctx){
        // MAJOR HACK INCOMING OHH MY GOOOOD
        this.currentFunctionReturnType = this.functions.get(ctx.IDENTIFIER().getText()).getReturnType();
    }

    @Override
    public void exitReturn(SolParser.ReturnContext ctx){
        // MAJOR HACK INCOMING OHH MY GOOOOD
        Class<?> cachedType = this.annotatedTypes.get(ctx.expr());
        Class<?> returnType = cachedType != null ? cachedType : Void.class;
        if(!compatibleTypes(returnType,this.currentFunctionReturnType)){
            this.reporter.reportError(
                ctx,
        INVALID_RETURN_TYPE_MESSAGE +
                ", Expected: " +
                Value.typeString(this.currentFunctionReturnType) +
                " but got: " +
                Value.typeString(returnType)
            );
        }
    }


    private void checkFunctionArguments(ParserRuleContext ctx, Function function, List<SolParser.ExprContext> expr, int providedArgs){
        int expectedArgs = function.getArgTypes().size();
        if(providedArgs != expectedArgs){
            this.reporter.reportError(ctx, "Expected " + expectedArgs + "Arguments but got " + providedArgs);
            return;
        }
        List<Class<?>> argTypes = function.getArgTypes();
        for(int i = 0; i < expectedArgs; i++){
            Class<?> type = this.annotatedTypes.get(expr.get(i));
            if(type == null){ // Something went very wrong we should stop now
                throw new InternalError("Error annotating failed to return a type");
            }
            if(!type.equals(argTypes.get(i))){
                this.reporter.reportError(
                    expr.get(i),
            "Mismatched argument type expected: " +
                    Value.typeString(argTypes.get(i)) +
                    "but got: " +
                    Value.typeString(type)
                );
            }
        }
    }
    @Override
    public void exitVoidFunctionCall(SolParser.VoidFunctionCallContext ctx){
        Function function = this.functions.get(ctx.IDENTIFIER().getText());
        if(function == null){
            this.reporter.reportError(ctx, "Call to unknown function : " + ctx.IDENTIFIER().getText());
            return;
        }
        int providedArgs = ctx.expr().size();
        checkFunctionArguments(ctx,function,ctx.expr(),providedArgs);
        this.annotatedTypes.put(ctx,function.getReturnType());
    }

    @Override
    public void exitNonVoidFunctionCall(SolParser.NonVoidFunctionCallContext ctx){
        Function function = this.functions.get(ctx.IDENTIFIER().getText());
        if(function == null){
            this.reporter.reportError(ctx, "Call to unknown function : " + ctx.IDENTIFIER().getText());
            return;
        }
        int providedArgs = ctx.expr().size();
        checkFunctionArguments(ctx,function,ctx.expr(),providedArgs);
        this.annotatedTypes.put(ctx,function.getReturnType());
    }

    @Override
    public void enterScope(SolParser.ScopeContext ctx){
        this.scope.addChild(new ScopeTree(this.scope));
        this.scope = this.scope.getRightmostChild();
        ParserRuleContext parent = ctx.getParent();
        if(parent instanceof SolParser.FunctionDeclarationContext){ // Because we are using a listener we have to add the argument types after the created scope
           ((SolParser.FunctionDeclarationContext) parent).argument().forEach(
               (arg) -> {
                   Class<?> type = Value.typeOf(arg.type.getText());
                   this.scope.putVariable(arg.IDENTIFIER().getText(), type);
                   this.annotatedTypes.put(arg,type); // Because this is a variable assignment we also need to annotate the node
               }
           );
        }
    }

    @Override
    public void exitScope(SolParser.ScopeContext ctx){
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