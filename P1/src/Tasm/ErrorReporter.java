package Tasm;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;

import java.util.ArrayList;
import java.util.List;

public class ErrorReporter
{
    private static class CompilationError
    {
        private final ParserRuleContext ctx;
        private final String message;

        private CompilationError(ParserRuleContext ctx, String message)
        {
            this.ctx = ctx;
            this.message = message;
        }

        @Override
        public String toString()
        {
            if (this.ctx == null)
                return this.message;
            Interval interval = new Interval(ctx.start.getStartIndex(), ctx.stop.getStopIndex());
            CharStream input = this.ctx.start.getInputStream();
            return "line " + this.ctx.start.getLine() + ":" +
                    this.ctx.start.getCharPositionInLine() + " " +
                    this.message + " '" + input.getText(interval) + "'";
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

    public int getErrorCount()
    {
        return this.errors.size();
    }

    public void reportError(ParserRuleContext ctx, String message)
    {
        this.errors.add(new CompilationError(ctx, message));
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        this.errors.forEach((e) -> builder.append(e).append("\n"));
        return builder.toString();
    }
}
