// Generated from C:/Users/Acer/Desktop/Programations/cop_projeto/P4/src/Tasm.g4 by ANTLR 4.13.1
package antlrTasm;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TasmParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TasmVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TasmParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(TasmParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link TasmParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(TasmParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LoadInt}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadInt(TasmParser.LoadIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LoadDouble}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadDouble(TasmParser.LoadDoubleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LoadString}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadString(TasmParser.LoadStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Jump}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJump(TasmParser.JumpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Global}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal(TasmParser.GlobalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Local}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocal(TasmParser.LocalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Return}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(TasmParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Simple}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple(TasmParser.SimpleContext ctx);
}