package Tasm;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;

import java.util.ArrayList;
import java.util.List;

public class ErrorReporter
{
    public static class CompilationError
    {
        private ParserRuleContext ctx;
        private final String message;

        public CompilationError(ParserRuleContext ctx, String message)
        {
            this.ctx = ctx;
            this.message = message;
        }
        public CompilationError(String message)
        {
            this.message = message;
        }

        @Override
        public String toString()
        {
            StringBuilder message = new StringBuilder();
            message.append(this.message);
            if (this.ctx == null)
               return message.toString();

            message.append(" on line ").append(this.ctx.start.getLine()).append(":")
                    .append(this.ctx.start.getCharPositionInLine()).append("\n");
            CharStream input = this.ctx.start.getInputStream();
            int a = ctx.start.getStartIndex();
            int b = ctx.stop.getStopIndex();
            Interval interval = new Interval(a, b);
            message.append("'").append(input.getText(interval)).append("'");
            return message.toString();
        }
    }

    private final List<CompilationError> errors;

    public ErrorReporter()
    {
        this.errors = new ArrayList<>();
    }

    public boolean hasReportedErrors()
    {
        return !this.errors.isEmpty();
    }
    public void reportError(ParserRuleContext ctx, String message){
        this.errors.add(new CompilationError(ctx, message));
    }
    public void reportError(String message)
    {
        this.errors.add(new CompilationError(message));
    }

    public List<CompilationError> getErrors()
    {
        return this.errors;
    }

    public int getErrorCount()
    {
        return this.errors.size();
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ERROR REPORTER:\n");
        this.errors.forEach((e) -> builder.append("\nERROR: ").append(e).append("\n"));
        return builder.toString();
    }
}
