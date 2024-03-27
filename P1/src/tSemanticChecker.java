import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import tAsm.TasmBaseListener;
import tAsm.TasmParser;

import java.util.HashMap;

/**
 * Checks for basic semantic like presence of halts, label uniqueness and stores all labels
 */
public class tSemanticChecker extends TasmBaseListener {

    private HashMap<String, Integer> labelsToInstruction = new HashMap<>();
    private int instructionCount = 0;

    private boolean halted = false;

    ErrorReporter reporter;

    public tSemanticChecker(ErrorReporter reporter) {
        this.reporter = reporter;
    }

    @Override
    public void exitLine(TasmParser.LineContext ctx) {
        if (ctx.LABEL() != null){
            for(TerminalNode label : ctx.getTokens(TasmParser.LABEL)) {
                String labelText = label.getText();
                if(this.labelsToInstruction.containsKey(labelText)){
                    this.reporter.reportError(ctx.getText(), "Duplicate label");
                }
                this.labelsToInstruction.put(labelText, this.instructionCount);
            }
        }
        this.instructionCount++;
    }


    @Override
    public void exitSimpleInstruction(TasmParser.SimpleInstructionContext ctx)
    {
        if(ctx.SIMPLE_INSTRUCTION().getText().equals("halt")){
            this.halted = true;
        }
    }

    public void semanticCheck(ParseTree tree) throws Exception{
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);

        if(!this.halted){
            this.reporter.reportError(null, "Program is not halted");
        }
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        this.reporter.reportError(node.getText(), "Syntax error");
    }
    public HashMap<String, Integer> getLabelsToInstruction(){
        return this.labelsToInstruction;
    }
}
