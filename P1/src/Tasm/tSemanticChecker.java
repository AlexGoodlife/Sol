package Tasm;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import antlrTasm.TasmBaseListener;
import antlrTasm.TasmParser;

import java.util.HashMap;

/**
 * Checks for basic semantic like presence of halts, label uniqueness and stores all labels
 */
public class tSemanticChecker extends TasmBaseListener
{
    private final HashMap<String, Integer> labelToInstruction;
    private int instructionCount;
    private boolean halted;
    private final ErrorReporter reporter;

    public tSemanticChecker(ErrorReporter reporter)
    {
        this.labelToInstruction = new HashMap<>();
        this.instructionCount = 0;
        this.halted = false;
        this.reporter = reporter;
    }

    public HashMap<String, Integer> getLabelToInstruction()
    {
        return this.labelToInstruction;
    }

    @Override
    public void exitLine(TasmParser.LineContext ctx)
    {
        if (ctx.LABEL() != null)
        {
            for(TerminalNode label : ctx.getTokens(TasmParser.LABEL))
            {
                String labelText = label.getText();
                if (this.labelToInstruction.containsKey(labelText))
                    this.reporter.reportError(ctx, "Duplicate label");
                this.labelToInstruction.put(labelText, this.instructionCount);
            }
        }
        this.instructionCount++;
    }

    @Override
    public void exitSimpleInstruction(TasmParser.SimpleInstructionContext ctx)
    {
        this.halted = ctx.SIMPLE_INSTRUCTION().getText().equals(InstructionCode.HALT.name().toLowerCase());
    }

    public void semanticCheck(ParseTree tree)
    {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);

        if (!this.halted)
            this.reporter.reportError(null, "Program is not halted at any point");
    }
}
