// Generated from /home/compl3x/Programations/cop_projeto/P3/src/Tasm.g4 by ANTLR 4.13.1
package antlrTasm;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TasmParser}.
 */
public interface TasmListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TasmParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(TasmParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link TasmParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(TasmParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link TasmParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(TasmParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link TasmParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(TasmParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LoadInt}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterLoadInt(TasmParser.LoadIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LoadInt}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitLoadInt(TasmParser.LoadIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LoadDouble}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterLoadDouble(TasmParser.LoadDoubleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LoadDouble}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitLoadDouble(TasmParser.LoadDoubleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LoadString}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterLoadString(TasmParser.LoadStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LoadString}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitLoadString(TasmParser.LoadStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Jump}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterJump(TasmParser.JumpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Jump}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitJump(TasmParser.JumpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Global}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterGlobal(TasmParser.GlobalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Global}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitGlobal(TasmParser.GlobalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Local}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterLocal(TasmParser.LocalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Local}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitLocal(TasmParser.LocalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Return}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterReturn(TasmParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Return}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitReturn(TasmParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Simple}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterSimple(TasmParser.SimpleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Simple}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitSimple(TasmParser.SimpleContext ctx);
}