package Sol;

import ErrorUtils.ErrorReporter;
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
    private static final String FUNCTION_ID_ERROR_MESSAGE = "Identifier is not a variable";
    private static final String POINTER_OPERATION_ERROR_MESSAGE = "Pointer operation is not allowed";
    private static final String DEREFERENCE_NON_POINTER_ERROR_MESSAGE = "Dereferencing non pointer variable";

    private final ErrorReporter reporter;
    private final ParseTreeProperty<Type> annotatedTypes;
    private ScopeTree scope;
    private final ParseTreeProperty<ScopeTree> annotatedScopes;
    private HashMap<String, Function> functions;
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

    public ParseTreeProperty<Type> getAnnotatedTypes()
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
        this.annotatedTypes.put(ctx, new Type(Integer.class, 0,0));
    }

    @Override
    public void exitDouble(SolParser.DoubleContext ctx)
    {
        this.annotatedTypes.put(ctx, new Type(Double.class, 0,0));
    }

    @Override
    public void exitString(SolParser.StringContext ctx)
    {
        this.annotatedTypes.put(ctx, new Type(String.class, 0,0));
    }

    @Override
    public void exitBoolean(SolParser.BooleanContext ctx)
    {
        this.annotatedTypes.put(ctx, new Type(Boolean.class, 0,0));
    }

    @Override
    public void exitIdentifier(SolParser.IdentifierContext ctx)
    {
        String variableName = ctx.IDENTIFIER().getText();
        ScopeTree.Variable var = this.scope.getVariable(variableName);
        if (this.checkIdentifierError(ctx, variableName, var))
            return;
        this.annotatedTypes.put(ctx, var.scopedType());
    }

    private boolean checkIdentifierError(SolParser.ExprContext ctx, String variableName, ScopeTree.Variable var)
    {
        int initialErrorCount = this.reporter.getErrorCount();

        if (this.functions.containsKey(variableName))
            this.reporter.reportError(ctx, FUNCTION_ID_ERROR_MESSAGE);
        else if (var == null)
            this.reporter.reportError(ctx, UNDECLARED_VAR_ERROR_MESSAGE);

        return this.reporter.getErrorCount() > initialErrorCount;
    }

    @Override
    public void exitReference(SolParser.ReferenceContext ctx)
    {
        String variableName = ctx.IDENTIFIER().getText();
        ScopeTree.Variable var = this.scope.getVariable(variableName);
        if (this.checkIdentifierError(ctx, variableName, var))
            return;
        this.annotatedTypes.put(ctx, new Type(var.scopedType().type(), var.scopedType().refDepth() + 1,var.scopedType().arrayDim()));
    }

    @Override
    public void exitDereference(SolParser.DereferenceContext ctx)
    {
        String variableName = ctx.IDENTIFIER().getText();
        ScopeTree.Variable var = this.scope.getVariable(variableName);
        if (this.checkIdentifierError(ctx, variableName, var))
            return;
        if (ctx.DREF().size() > var.scopedType().refDepth())
        {
            this.reporter.reportError(ctx, DEREFERENCE_NON_POINTER_ERROR_MESSAGE);
            return;
        }

        this.annotatedTypes.put(ctx, new Type(var.scopedType().type(), var.scopedType().refDepth() - ctx.DREF().size(),var.scopedType().arrayDim()));
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
        Type leftType = this.annotatedTypes.get(left);
        Type rightType = this.annotatedTypes.get(right);
        if (leftType == null || rightType == null)
            return;
        if (this.referenceErrorCheck(ctx, leftType, rightType))
            return;

        boolean isLeftBoolean = leftType.type() == Boolean.class;
        boolean isRightBoolean = rightType.type() == Boolean.class;
        if (isLeftBoolean && isRightBoolean)
            this.annotatedTypes.put(ctx, new Type(Boolean.class, 0,0));
        else
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
    }

    private boolean referenceErrorCheck(SolParser.ExprContext ctx, Type left, Type right)
    {
        boolean hasRef = left.isRef() || right.isRef();
        if (hasRef)
            this.reporter.reportError(ctx, POINTER_OPERATION_ERROR_MESSAGE);
        return hasRef;
    }

    @Override
    public void exitEquality(SolParser.EqualityContext ctx)
    {
        Type leftType = this.annotatedTypes.get(ctx.expr(0));
        Type rightType = this.annotatedTypes.get(ctx.expr(1));
        if (leftType == null || rightType == null)
            return;

        boolean isLeftNumber = leftType.type() == Integer.class || leftType.type() == Double.class;
        boolean isRightNumber = rightType.type() == Integer.class || rightType.type() == Double.class;
        boolean notRef = !leftType.isRef() && !rightType.isRef();
        if (leftType.equals(rightType) || (isLeftNumber && isRightNumber && notRef))
            this.annotatedTypes.put(ctx, new Type(Boolean.class, 0,0));
        else
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
    }

    @Override
    public void exitRelational(SolParser.RelationalContext ctx)
    {
        Type leftType = this.annotatedTypes.get(ctx.expr(0));
        Type rightType = this.annotatedTypes.get(ctx.expr(1));
        if (leftType == null || rightType == null)
            return;
        if (this.referenceErrorCheck(ctx, leftType, rightType))
            return;

        boolean isLeftNumber = leftType.type() == Integer.class || leftType.type() == Double.class;
        boolean isRightNumber = rightType.type() == Integer.class || rightType.type() == Double.class;
        if (isLeftNumber && isRightNumber)
            this.annotatedTypes.put(ctx, new Type(Boolean.class, 0,0));
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
        Type leftType = this.annotatedTypes.get(ctx.expr(0));
        Type rightType = this.annotatedTypes.get(ctx.expr(1));
        if (leftType == null || rightType == null)
            return;
        if (this.referenceErrorCheck(ctx, leftType, rightType))
            return;

        boolean isLeftString = leftType.type() == String.class;
        boolean isRightString = rightType.type() == String.class;
        boolean isLeftNumber = leftType.type() == Integer.class || leftType.type() == Double.class;
        boolean isRightNumber = rightType.type() == Integer.class || rightType.type() == Double.class;
        if ((isLeftString || isRightString) || (isLeftNumber && isRightNumber))
            this.annotateAddType(ctx, leftType.type(), rightType.type());
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
            this.annotatedTypes.put(ctx, new Type(String.class, 0,0));
        else if (isLeftDouble || isRightDouble)
            this.annotatedTypes.put(ctx, new Type(Double.class, 0,0));
        else
            this.annotatedTypes.put(ctx, new Type(Integer.class, 0,0));
    }

    private void exitArithmetic(SolParser.ExprContext ctx, SolParser.ExprContext left, SolParser.ExprContext right)
    {
        Type leftType = this.annotatedTypes.get(left);
        Type rightType = this.annotatedTypes.get(right);
        if (leftType == null || rightType == null)
            return;
        if (this.referenceErrorCheck(ctx, leftType, rightType))
            return;

        boolean isLeftNumber = leftType.type() == Integer.class || leftType.type() == Double.class;
        boolean isRightNumber = rightType.type() == Integer.class || rightType.type() == Double.class;
        if (isLeftNumber && isRightNumber)
            this.annotateArithmeticType(ctx, leftType.type(), rightType.type());
        else
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
    }

    private void annotateArithmeticType(SolParser.ExprContext ctx, Class<?> leftType, Class<?> rightType)
    {
        boolean isLeftDouble = leftType == Double.class;
        boolean isRightDouble = rightType == Double.class;
        if (isLeftDouble || isRightDouble)
            this.annotatedTypes.put(ctx, new Type(Double.class, 0,0));
        else
            this.annotatedTypes.put(ctx, new Type(Integer.class, 0,0));
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
        Type leftType = this.annotatedTypes.get(ctx.expr(0));
        Type rightType = this.annotatedTypes.get(ctx.expr(1));
        if (leftType == null || rightType == null)
            return;
        if (this.referenceErrorCheck(ctx, leftType, rightType))
            return;

        boolean isLeftInteger = leftType.type() == Integer.class;
        boolean isRightInteger = rightType.type() == Integer.class;
        if (isLeftInteger && isRightInteger)
            this.annotatedTypes.put(ctx, new Type(Integer.class, 0,0));
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
        Type exprType = this.annotatedTypes.get(ctx.expr());
        if (exprType == null)
            return;

        boolean isExprNumber = exprType.type() == Integer.class || exprType.type() == Double.class;
        if (exprType.isRef())
            this.reporter.reportError(ctx, POINTER_OPERATION_ERROR_MESSAGE);
        else if (!isExprNumber)
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
        else
            this.annotatedTypes.put(ctx, exprType);
    }

    private void exitNot(SolParser.NegationContext ctx)
    {
        Type exprType = this.annotatedTypes.get(ctx.expr());
        if (exprType == null)
            return;

        boolean isExprBoolean = exprType.type() == Boolean.class;
        if (exprType.isRef())
            this.reporter.reportError(ctx, POINTER_OPERATION_ERROR_MESSAGE);
        else if (!isExprBoolean)
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
        else
            this.annotatedTypes.put(ctx, exprType);
    }

    public void exitParentheses(SolParser.ParenthesesContext ctx)
    {
        this.annotatedTypes.put(ctx, this.annotatedTypes.get(ctx.expr()));
    }

    @Override
    public void enterDeclaration(SolParser.DeclarationContext ctx)
    {
        this.annotatedTypes.put(ctx, Type.typeOf(ctx.type.getText(), ctx.REF().size(),ctx.LSBRACKET().size()));
    }

    @Override
    public void exitDeclarationAssign(SolParser.DeclarationAssignContext ctx)
    {
        if (ctx.IDENTIFIER() == null)
            return;
        String variableName = ctx.IDENTIFIER().getText();
        if (this.checkVariableDeclarationsErrors(ctx, variableName))
            return;
        Type variableType = this.annotatedTypes.get(ctx.getParent());
        if (variableType == null)
            throw new InternalError("Variable has no type after declaration? Shouldn't happen");

        if (ctx.expr() != null)
        {
            Type exprType = this.annotatedTypes.get(ctx.expr());
            if (exprType != null && !compatibleTypes(variableType, exprType))
                this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
        }
        if(ctx.staticArray() != null){
            // Check if dimensions are correct
        }
        this.scope.putVariable(variableName, variableType);
    }

    private boolean checkVariableDeclarationsErrors(SolParser.DeclarationAssignContext ctx, String variableName)
    {
        boolean hasError = false;
        if (this.functions.containsKey(variableName))
        {
            this.reporter.reportError(ctx, FUNCTION_ID_ERROR_MESSAGE);
            hasError = true;
        }
        else if (this.scope.containsVariableLocal(variableName))
        {
            this.reporter.reportError(ctx, DECLARED_VAR_ERROR_MESSAGE);
            hasError = true;
        }
        return hasError;
    }

    private static boolean compatibleTypes(Type variableType, Type expressionType)
    {
        boolean isVariableDouble = variableType.type() == Double.class;
        boolean isExpressionNumber = expressionType.type() == Integer.class || expressionType.type() == Double.class;
        boolean notRef = !variableType.isRef() && !expressionType.isRef();
        return variableType.equals(expressionType) || (isVariableDouble && isExpressionNumber && notRef);
    }

    @Override
    public void exitAssign(SolParser.AssignContext ctx)
    {
        if (ctx.IDENTIFIER() == null)
            return;
        String variableName = ctx.IDENTIFIER().getText();
        if (this.checkVariableAssignmentErrors(ctx, variableName))
            return;
        Type variableType = this.getAssignVariableType(ctx, variableName);
        if (variableType == null)
            return;
        Type exprType = this.annotatedTypes.get(ctx.expr(ctx.expr().size()-1));
        if (exprType == null)
            return;

        if (compatibleTypes(variableType, exprType))
            this.annotatedTypes.put(ctx, variableType);
        else
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
    }

    private Type getAssignVariableType(SolParser.AssignContext ctx, String variableName)
    {
        Type variableType = this.scope.getVariable(variableName).scopedType();
        if (ctx.DREF().size() > variableType.refDepth())
        {
            this.reporter.reportError(ctx, DEREFERENCE_NON_POINTER_ERROR_MESSAGE);
            return null;
        }
        int arrayAccessSize = ctx.LSBRACKET().size();
        int arrayDimension = variableType.arrayDim() - arrayAccessSize;
        if(arrayDimension < 0){
            this.reporter.reportError(ctx, DEREFERENCE_NON_POINTER_ERROR_MESSAGE);
            return null;
        }
        return  new Type(variableType.type(), variableType.refDepth() - ctx.DREF().size(),arrayDimension);
    }

    private boolean checkVariableAssignmentErrors(SolParser.AssignContext ctx, String variableName)
    {
        boolean hasError = false;
        if (this.functions.containsKey(variableName))
        {
            this.reporter.reportError(ctx, FUNCTION_ID_ERROR_MESSAGE);
            hasError = true;
        }
        else if (!this.scope.containsVariable(variableName))
        {
            this.reporter.reportError(ctx, UNDECLARED_VAR_ERROR_MESSAGE);
            hasError = true;
        }
        return hasError;
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
        Type exprType = this.annotatedTypes.get(ctx.expr());
        if (exprType == null)
            return;

        if (exprType.type() != Boolean.class || exprType.isRef())
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
        Type variableType = this.annotatedTypes.get(ctx.assign());
        Type exprType = this.annotatedTypes.get(ctx.expr());
        if (variableType == null || exprType == null)
            return;

        boolean hasRef = variableType.isRef() || exprType.isRef();
        if (variableType.type() != Integer.class || exprType.type() != Integer.class || hasRef)
            this.reporter.reportError(ctx, TYPE_MISMATCH_ERROR_MESSAGE);
    }

    @Override
    public void exitIf(SolParser.IfContext ctx)
    {
        Type exprType = this.annotatedTypes.get(ctx.expr());
        if (exprType == null)
            return;

        if (exprType.type() != Boolean.class || exprType.isRef())
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
        Type cachedType = this.annotatedTypes.get(ctx.expr());
        Type returnType = cachedType != null ? cachedType : new Type(Void.class, 0,0);
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

        List<Type> argTypes = function.getArgTypes();
        for (int i = 0; i < expectedArgs; i++)
        {
            Type type = this.annotatedTypes.get(args.get(i));
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
        if (function.getReturnType().type() != Void.class)
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
        if (function.getReturnType().type() == Void.class)
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
           args.forEach((arg) -> this.scope.putVariable(arg.IDENTIFIER().getText(), Type.typeOf(arg.type.getText(), arg.REF().size(),arg.LSBRACKET().size())));
           this.scope.offset(2); // Offset to compensate for frame pointer and return address on execution stack
        }
    }

    @Override
    public void exitScope(SolParser.ScopeContext ctx)
    {
        this.scope = (ScopeTree) this.scope.getParent(); // Back track on our scopes when moving because global scope is not classified as such we never go beyond root
    }

    @Override
    public void exitStaticArray(SolParser.StaticArrayContext ctx){
        if(ctx.expr() != null) return;

        Type arrayType = this.annotatedTypes.get(ctx.staticArray(0));
        for(SolParser.StaticArrayContext arr : ctx.staticArray()){
           if(!this.annotatedTypes.get(arr).equals(arrayType)){
               this.reporter.reportError(arr, TYPE_MISMATCH_ERROR_MESSAGE);
               return;
           }
        }
        this.annotatedTypes.put(ctx, this.annotatedTypes.get(ctx.getParent().getParent()));
    }

    @Override
    public void exitArrayAccess(SolParser.ArrayAccessContext ctx){
        ScopeTree.Variable var = this.scope.getVariable(ctx.IDENTIFIER().getText());
        int accessAmount = ctx.LSBRACKET().size();
        if( var.scopedType().arrayDim() - accessAmount< 0 ){
            this.reporter.reportError(ctx, DEREFERENCE_NON_POINTER_ERROR_MESSAGE);
            return;
        }
        this.annotatedTypes.put(ctx, new Type(var.scopedType().type(), var.scopedType().refDepth(), var.scopedType().arrayDim() - accessAmount));
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