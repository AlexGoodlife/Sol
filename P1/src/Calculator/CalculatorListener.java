// Generated from /home/compl3x/Programations/UAlg_LEI/CP_2324/CalculatorCompiler/src/Tasm.g4 by ANTLR 4.13.1
package Calculator;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalculatorParser}.
 */
public interface CalculatorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CalculatorParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CalculatorParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(CalculatorParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(CalculatorParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Integer}
	 * labeled alternative in {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInteger(CalculatorParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Integer}
	 * labeled alternative in {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInteger(CalculatorParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PlusMinus}
	 * labeled alternative in {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPlusMinus(CalculatorParser.PlusMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PlusMinus}
	 * labeled alternative in {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPlusMinus(CalculatorParser.PlusMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parenthisis}
	 * labeled alternative in {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenthisis(CalculatorParser.ParenthisisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parenthisis}
	 * labeled alternative in {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenthisis(CalculatorParser.ParenthisisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNegation(CalculatorParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNegation(CalculatorParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultDiv}
	 * labeled alternative in {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultDiv(CalculatorParser.MultDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultDiv}
	 * labeled alternative in {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultDiv(CalculatorParser.MultDivContext ctx);
}