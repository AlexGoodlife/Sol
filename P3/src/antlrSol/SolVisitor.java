// Generated from C:/Users/Acer/Desktop/Programations/cop_projeto/P2/src/Sol.g4 by ANTLR 4.13.1
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
	 * Visit a parse tree produced by {@link SolParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(SolParser.LineContext ctx);
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
	 * Visit a parse tree produced by the {@code Double}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble(SolParser.DoubleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parentheses}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentheses(SolParser.ParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link SolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(SolParser.IntContext ctx);
}