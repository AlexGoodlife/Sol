// Generated from /home/compl3x/Programations/cop_projeto/P4/src/Sol.g4 by ANTLR 4.13.1
package antlrSol;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SolParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SolVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SolParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(SolParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link SolParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(SolParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SolParser#declarationAssign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationAssign(SolParser.DeclarationAssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link SolParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(SolParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SolParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(SolParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SolParser#scope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScope(SolParser.ScopeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Print}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(SolParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Assignment}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(SolParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Block}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(SolParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code While}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(SolParser.WhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code For}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor(SolParser.ForContext ctx);
	/**
	 * Visit a parse tree produced by the {@code If}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(SolParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Empty}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmpty(SolParser.EmptyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Break}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreak(SolParser.BreakContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VoidFunctionCall}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidFunctionCall(SolParser.VoidFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Return}
	 * labeled alternative in {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(SolParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by {@link SolParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(SolParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(SolParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegation(SolParser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(SolParser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Reference}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReference(SolParser.ReferenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NonVoidFunctionCall}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonVoidFunctionCall(SolParser.NonVoidFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Relational}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelational(SolParser.RelationalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code String}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(SolParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Double}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble(SolParser.DoubleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(SolParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayAccess}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccess(SolParser.ArrayAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Identifier}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(SolParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultDivMod}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultDivMod(SolParser.MultDivModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code And}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(SolParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality(SolParser.EqualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Boolean}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean(SolParser.BooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parentheses}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentheses(SolParser.ParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Dereference}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDereference(SolParser.DereferenceContext ctx);
}