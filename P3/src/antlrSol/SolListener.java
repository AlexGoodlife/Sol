// Generated from C:/Users/Acer/Desktop/Programations/cop_projeto/P2/src/Sol.g4 by ANTLR 4.13.1
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
	 * Enter a parse tree produced by {@link SolParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(SolParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link SolParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(SolParser.LineContext ctx);
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
}