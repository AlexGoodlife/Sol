// Generated from C:/Users/Acer/Desktop/Programations/cop_projeto/P4/src/Sol.g4 by ANTLR 4.13.1
package antlrSol;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SolParser}.
 */
public interface SolListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SolParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(SolParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link SolParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(SolParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link SolParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(SolParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SolParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(SolParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SolParser#declarationAssign}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationAssign(SolParser.DeclarationAssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link SolParser#declarationAssign}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationAssign(SolParser.DeclarationAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link SolParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(SolParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SolParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(SolParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SolParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(SolParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SolParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(SolParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SolParser#scope}.
	 * @param ctx the parse tree
	 */
	void enterScope(SolParser.ScopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SolParser#scope}.
	 * @param ctx the parse tree
	 */
	void exitScope(SolParser.ScopeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Print}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterPrint(SolParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Print}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitPrint(SolParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assignment}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(SolParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assignment}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(SolParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Block}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterBlock(SolParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Block}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitBlock(SolParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code While}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterWhile(SolParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code While}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitWhile(SolParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code For}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterFor(SolParser.ForContext ctx);
	/**
	 * Exit a parse tree produced by the {@code For}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitFor(SolParser.ForContext ctx);
	/**
	 * Enter a parse tree produced by the {@code If}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterIf(SolParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code If}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitIf(SolParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Empty}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterEmpty(SolParser.EmptyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Empty}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitEmpty(SolParser.EmptyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Break}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterBreak(SolParser.BreakContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Break}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitBreak(SolParser.BreakContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VoidFunctionCall}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterVoidFunctionCall(SolParser.VoidFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VoidFunctionCall}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitVoidFunctionCall(SolParser.VoidFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Return}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterReturn(SolParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Return}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitReturn(SolParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by {@link SolParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(SolParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link SolParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(SolParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Or}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOr(SolParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOr(SolParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegation(SolParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegation(SolParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(SolParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(SolParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Reference}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterReference(SolParser.ReferenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Reference}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitReference(SolParser.ReferenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NonVoidFunctionCall}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNonVoidFunctionCall(SolParser.NonVoidFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NonVoidFunctionCall}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNonVoidFunctionCall(SolParser.NonVoidFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Relational}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRelational(SolParser.RelationalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Relational}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRelational(SolParser.RelationalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code String}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterString(SolParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code String}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitString(SolParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Double}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDouble(SolParser.DoubleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Double}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDouble(SolParser.DoubleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Int}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(SolParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(SolParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Identifier}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(SolParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Identifier}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(SolParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultDivMod}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultDivMod(SolParser.MultDivModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultDivMod}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultDivMod(SolParser.MultDivModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code And}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAnd(SolParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code And}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAnd(SolParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEquality(SolParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEquality(SolParser.EqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Boolean}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolean(SolParser.BooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Boolean}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolean(SolParser.BooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parentheses}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParentheses(SolParser.ParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parentheses}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParentheses(SolParser.ParenthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Dereference}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDereference(SolParser.DereferenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Dereference}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDereference(SolParser.DereferenceContext ctx);
}