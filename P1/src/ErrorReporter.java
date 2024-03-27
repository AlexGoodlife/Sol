import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

public class ErrorReporter
{
    public static class SemanticError
    {
        private final String ctx;
        private final String message;


        public SemanticError(String ctx, String message)
        {
            this.ctx = ctx;
            this.message = message;
        }

        public String getMessage()
        {
            return this.message;
        }

        public String getCtx()
        {
            return this.ctx;
        }
    }

    List<SemanticError> errors;

    public ErrorReporter()
    {
        this.errors = new ArrayList<>();
    }

    public void reportError(String ctx, String message)
    {
        this.errors.add(new SemanticError(ctx, message));
    }

    public List<SemanticError> getErrors()
    {
        return this.errors;
    }

    public int getErrorCount()
    {
        return this.errors.size();
    }
}
