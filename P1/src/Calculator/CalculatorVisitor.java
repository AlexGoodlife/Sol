// Generated from /home/compl3x/Programations/UAlg_LEI/CP_2324/CalculatorCompiler/src/Tasm.g4 by ANTLR 4.13.1
package Calculator;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CalculatorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CalculatorVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CalculatorParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CalculatorParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalculatorParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(CalculatorParser.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Integer}
	 * labeled alternative in {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(CalculatorParser.IntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PlusMinus}
	 * labeled alternative in {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusMinus(CalculatorParser.PlusMinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parenthisis}
	 * labeled alternative in {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthisis(CalculatorParser.ParenthisisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegation(CalculatorParser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultDiv}
	 * labeled alternative in {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultDiv(CalculatorParser.MultDivContext ctx);
}