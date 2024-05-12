package Sol;

import ErrorUtils.*;
import antlrSol.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import Tasm.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/* Why use a visitor? Because we want to know if we need to insert conversion instructions prior to visiting any upcoming nodes in an expression.
* With a listener this would be much harder to do in a clean manner,
* as we would need to check the type of the parent node and its other child nodes in the child node for type conversion instructions */
public class solCompiler extends SolBaseVisitor<Void>
{
    private static final int MAX_ARGUMENTS_SIZE = 6;

    private static final String SOURCE_FILE_FLAG = "-i";
    public static final String SOURCE_FILE_EXTENSION = "sol";
    private static final String BYTECODES_FILE_FLAG = "-o";
    private static final String SHOW_ASSEMBLY_FLAG = "--asm";
    private static final String NO_TASM_FILE_FLAG = "--no-tasm";

    private static final String DEFAULT_SOURCE_FILE_NAME = null;
    private static final String DEFAULT_BYTECODES_FILE_NAME = "a.tbc";
    private static final boolean DEFAULT_SHOW_ASSEMBLY = false;
    private static final boolean DEFAULT_GENERATE_TASM_FILE = true;


    private String sourceFileName;
    private String byteCodesFileName;
    private boolean showAssembly;
    private boolean generateTasmFile;

    private SolParser parser;
    private ParseTree tree;
    private ArrayList<Instruction> instructions;
    private ConstantPool constantPool;
    private ParseTreeProperty<Class<?>> annotatedTypes;
    private HashMap<String, Integer> variableIndices;
    private ScopeTree scope;
    private Stack<Instruction> breaks;
    private ErrorReporter reporter;
    private ParseTreeProperty<ScopeTree> scopeAnnotations;
    private HashMap<String, Instruction> functionCalls;
    private HashMap<String, Function> functions;

    public solCompiler()
    {
        this.sourceFileName = DEFAULT_SOURCE_FILE_NAME;
        this.byteCodesFileName = DEFAULT_BYTECODES_FILE_NAME;
        this.showAssembly = DEFAULT_SHOW_ASSEMBLY;
        this.generateTasmFile = DEFAULT_GENERATE_TASM_FILE;
    }

    private void loadVariable(String identifier){
        ScopeTree.Variable var = this.scope.getVariable(identifier);
        if(var.global()){
            this.instructions.add(new Instruction(Instruction.Code.GLOAD, var.index()));
        }
        else{
            this.instructions.add(new Instruction(Instruction.Code.LLOAD, var.index()));
        }
    }

    private void storeVariable(String identifier){
        ScopeTree.Variable var = this.scope.getVariable(identifier);
        if(var.global()){
            this.instructions.add(new Instruction(Instruction.Code.GSTORE, var.index()));
        }
        else{
            this.instructions.add(new Instruction(Instruction.Code.LSTORE, var.index()));
        }
    }

    @Override
    public Void visitInt(SolParser.IntContext ctx)
    {
        this.instructions.add(new Instruction(Instruction.Code.ICONST, Integer.valueOf(ctx.INT().getText())));
        return null;
    }

    @Override
    public Void visitDouble(SolParser.DoubleContext ctx)
    {
        Value doubleValue = new Value(Double.valueOf(ctx.DOUBLE().getText()));
        this.instructions.add(new Instruction(Instruction.Code.DCONST, this.constantPool.addIfAbsent(doubleValue)));
        return null;
    }

    @Override
    public Void visitString(SolParser.StringContext ctx)
    {
        String rawString = ctx.STRING().getText();
        String readString = rawString.substring(1, rawString.length() - 1).replaceAll("\\\\([\"\\\\])", "$1");
        Value stringValue = new Value(readString);
        this.instructions.add(new Instruction(Instruction.Code.SCONST, this.constantPool.addIfAbsent(stringValue)));
        return null;
    }

    @Override
    public Void visitBoolean(SolParser.BooleanContext ctx)
    {
        if (Boolean.parseBoolean(ctx.BOOLEAN().getText()))
            this.instructions.add(new Instruction(Instruction.Code.TCONST));
        else
            this.instructions.add(new Instruction(Instruction.Code.FCONST));
        return null;
    }

    @Override
    public Void visitIdentifier(SolParser.IdentifierContext ctx)
    {
        this.loadVariable(ctx.IDENTIFIER().getText());
        return null;
    }

    @Override
    public Void visitOr(SolParser.OrContext ctx)
    {
        // No need to check type conversion in logical operations
        this.visit(ctx.expr(0));
        this.visit(ctx.expr(1));
        this.instructions.add(new Instruction(Instruction.Code.OR));
        return null;
    }

    @Override
    public Void visitAnd(SolParser.AndContext ctx)
    {
        // No need to check type conversion in logical operations
        this.visit(ctx.expr(0));
        this.visit(ctx.expr(1));
        this.instructions.add(new Instruction(Instruction.Code.AND));
        return null;
    }

    /* A return value of null in this function signifies something is very wrong with the implementation.
    * As such we throw an InternalError if such ever occurs */
    private Class<?> visitNodesWithTypeConversionChecking(SolParser.ExprContext firstToVisit, SolParser.ExprContext secondToVisit)
    {
        Class<?> firstType = this.annotatedTypes.get(firstToVisit);
        Class<?> secondType = this.annotatedTypes.get(secondToVisit);
        Class<?> type = firstType == secondType ? firstType : null;

        this.visit(firstToVisit);
        type = this.addConversionIfPossible(firstType, secondType) ? secondType : type;
        this.visit(secondToVisit);
        type = this.addConversionIfPossible(secondType, firstType) ? firstType : type;

        if (type == null)
            throw new InternalError("Return type value is null");
        return type;
    }

    private boolean addConversionIfPossible(Class<?> from, Class<?> to)
    {
        if (from == to)
            return false;

        int initialSize = this.instructions.size();

        if (from == Integer.class && to == String.class)
            this.instructions.add(new Instruction(Instruction.Code.ITOS));
        else if (from == Integer.class && to == Double.class)
            this.instructions.add(new Instruction(Instruction.Code.ITOD));
        else if (from == Double.class && to == String.class)
            this.instructions.add(new Instruction(Instruction.Code.DTOS));
        else if (from == Boolean.class && to == String.class)
            this.instructions.add(new Instruction(Instruction.Code.BTOS));

        return this.instructions.size() > initialSize;
    }

    @Override
    public Void visitEquality(SolParser.EqualityContext ctx)
    {
        Class<?> type = this.visitNodesWithTypeConversionChecking(ctx.expr(0), ctx.expr(1));

        Instruction.Code code;
        if (type == Integer.class)
            code = ctx.op.getText().equals("==") ? Instruction.Code.IEQ : Instruction.Code.INEQ;
        else if (type == Double.class)
            code = ctx.op.getText().equals("==") ? Instruction.Code.DEQ : Instruction.Code.DNEQ;
        else if (type == String.class)
            code = ctx.op.getText().equals("==") ? Instruction.Code.SEQ : Instruction.Code.SNEQ;
        else if (type == Boolean.class)
            code = ctx.op.getText().equals("==") ? Instruction.Code.BEQ : Instruction.Code.BNEQ;
        else
            throw new InternalError("Invalid type caused instruction code to be null");

        this.instructions.add(new Instruction(code));
        return null;
    }

    @Override
    public Void visitRelational(SolParser.RelationalContext ctx)
    {
        SolParser.ExprContext firstToVisit = ctx.op.getText().matches("<|<=") ? ctx.expr(0) : ctx.expr(1);
        SolParser.ExprContext secondToVisit = firstToVisit.equals(ctx.expr(0)) ? ctx.expr(1) : ctx.expr(0);
        Class<?> type = this.visitNodesWithTypeConversionChecking(firstToVisit, secondToVisit);

        Instruction.Code code;
        if (type == Integer.class)
            code = ctx.op.getText().matches("[<>]") ? Instruction.Code.ILT : Instruction.Code.ILEQ;
        else if (type == Double.class)
            code = ctx.op.getText().matches("[<>]") ? Instruction.Code.DLT : Instruction.Code.DLEQ;
        else
            throw new InternalError("Invalid type caused instruction code to be null");

        this.instructions.add(new Instruction(code));
        return null;
    }

    @Override
    public Void visitAddSub(SolParser.AddSubContext ctx)
    {
        Class<?> type = this.visitNodesWithTypeConversionChecking(ctx.expr(0), ctx.expr(1));

        Instruction.Code code;
        if (type == Integer.class)
            code = ctx.op.getText().equals("+") ? Instruction.Code.IADD : Instruction.Code.ISUB;
        else if (type == Double.class)
            code = ctx.op.getText().equals("+") ? Instruction.Code.DADD : Instruction.Code.DSUB;
        else if (type == String.class && ctx.op.getText().equals("+"))
            code = Instruction.Code.SADD;
        else
            throw new InternalError("Invalid type caused instruction code to be null");

        this.instructions.add(new Instruction(code));
        return null;
    }

    @Override
    public Void visitMultDivMod(SolParser.MultDivModContext ctx)
    {
        Class<?> type = this.visitNodesWithTypeConversionChecking(ctx.expr(0), ctx.expr(1));

        Instruction.Code code;
        if (type == Integer.class && ctx.op.getText().equals("%"))
            code = Instruction.Code.IMOD;
        else if (type == Integer.class)
            code = ctx.op.getText().equals("*") ? Instruction.Code.IMULT : Instruction.Code.IDIV;
        else if (type == Double.class)
            code = ctx.op.getText().equals("*") ? Instruction.Code.DMULT : Instruction.Code.DDIV;
        else
            throw new InternalError("Invalid type caused instruction code to be null");

        this.instructions.add(new Instruction(code));
        return null;
    }

    @Override
    public Void visitNegation(SolParser.NegationContext ctx)
    {
        this.visit(ctx.expr());
        Class<?> exprType = this.annotatedTypes.get(ctx.expr());

        if (exprType == Integer.class)
            this.instructions.add(new Instruction(Instruction.Code.IUMINUS));
        else if (exprType == Double.class)
            this.instructions.add(new Instruction(Instruction.Code.DUMINUS));
        else if (exprType == Boolean.class)
            this.instructions.add(new Instruction(Instruction.Code.NOT));

        return null;
    }

    public Void visitDeclarationAssign(SolParser.DeclarationAssignContext ctx)
    {
        if (ctx.expr() != null)
        {
            this.visit(ctx.expr());
            if (this.annotatedTypes.get(ctx.getParent()) == Double.class && this.annotatedTypes.get(ctx.expr()) == Integer.class)
                this.instructions.add(new Instruction(Instruction.Code.ITOD));
            this.storeVariable(ctx.IDENTIFIER().getText());
            //this.instructions.add(new Instruction(Instruction.Code.GSTORE, this.variableIndices.size()));
        }
        //this.variableIndices.put(ctx.IDENTIFIER().getText(), this.variableIndices.size()); // I think we no longer need to do this since we get the indexes on semantic check
        return null;
    }

    @Override
    public Void visitPrint(SolParser.PrintContext ctx)
    {
        this.visit(ctx.expr());

        Class<?> exprType = this.annotatedTypes.get(ctx.expr());
        if (exprType == Integer.class)
            this.instructions.add(new Instruction(Instruction.Code.IPRINT));
        else if (exprType == Double.class)
            this.instructions.add(new Instruction(Instruction.Code.DPRINT));
        else if (exprType == String.class)
            this.instructions.add(new Instruction(Instruction.Code.SPRINT));
        else if (exprType == Boolean.class)
            this.instructions.add(new Instruction(Instruction.Code.BPRINT));

        return null;
    }

    public Void visitAssign(SolParser.AssignContext ctx)
    {
        this.visit(ctx.expr());
        if (this.annotatedTypes.get(ctx) == Double.class && this.annotatedTypes.get(ctx.expr()) == Integer.class)
            this.instructions.add(new Instruction(Instruction.Code.ITOD));
        this.storeVariable(ctx.IDENTIFIER().getText());
        //this.instructions.add(new Instruction(Instruction.Code.GSTORE, this.variableIndices.get(ctx.IDENTIFIER().getText())));
        return null;
    }

    public Void visitWhile(SolParser.WhileContext ctx)
    {
        int initialBreakCount = this.breaks.size();
        int loopStart = this.instructions.size();

        this.visit(ctx.expr());
        Instruction jump = new Instruction(Instruction.Code.JUMPF, Instruction.TO_DEFINE);
        this.instructions.add(jump);

        this.visit(ctx.instruction());
        this.instructions.add(new Instruction(Instruction.Code.JUMP, loopStart));

        jump.backPatch(this.instructions.size());
        this.backPatchBreaks(this.breaks.size() - initialBreakCount);
        return null;
    }

    private void backPatchBreaks(int nBreaks)
    {
        for (;nBreaks > 0; nBreaks--)
            this.breaks.pop().backPatch(this.instructions.size());
    }

    public Void visitFor(SolParser.ForContext ctx)
    {
        this.visit(ctx.assign());
        int initialBreakCount = this.breaks.size();
        int loopStart = this.instructions.size();
        //int variableIndex = this.variableIndices.get(ctx.assign().IDENTIFIER().getText());
        int variableIndex = this.scope.getVariable(ctx.assign().IDENTIFIER().getText()).index();

        this.visit(ctx.expr());
        this.instructions.add(new Instruction(Instruction.Code.GLOAD, variableIndex));
        this.instructions.add(new Instruction(Instruction.Code.ILT));
        Instruction jump = new Instruction(Instruction.Code.JUMPT, Instruction.TO_DEFINE);
        this.instructions.add(jump);

        this.visit(ctx.instruction());
        //Increment variable
        this.instructions.add(new Instruction(Instruction.Code.GLOAD, variableIndex));
        this.instructions.add(new Instruction(Instruction.Code.ICONST, 1));
        this.instructions.add(new Instruction(Instruction.Code.IADD));
        this.instructions.add(new Instruction(Instruction.Code.GSTORE, variableIndex));
        this.instructions.add(new Instruction(Instruction.Code.JUMP, loopStart));

        jump.backPatch(this.instructions.size());
        this.backPatchBreaks(this.breaks.size() - initialBreakCount);
        return null;
    }

    public Void visitIf(SolParser.IfContext ctx)
    {
        this.visit(ctx.expr());
        Instruction jump = new Instruction(Instruction.Code.JUMPF, Instruction.TO_DEFINE);
        this.instructions.add(jump);

        this.visit(ctx.instruction(0));
        Instruction unconditionalJump = null;
        if (ctx.ELSE() != null)
        {
            unconditionalJump = new Instruction(Instruction.Code.JUMP, Instruction.TO_DEFINE);
            this.instructions.add(unconditionalJump);
        }

        jump.backPatch(this.instructions.size());
        if (unconditionalJump != null) //The presence of an unconditionalJump signals that there is an else block
        {
            this.visit(ctx.instruction(1));
            unconditionalJump.backPatch(this.instructions.size());
        }
        return null;
    }

    public Void visitBreak(SolParser.BreakContext ctx)
    {
        Instruction breakInstruction = new Instruction(Instruction.Code.JUMP, Instruction.TO_DEFINE);
        this.breaks.push(breakInstruction);
        this.instructions.add(breakInstruction);
        return null;
    }

    @Override
    public Void visitProgram(SolParser.ProgramContext ctx)
    {
        Instruction globalAlloc = new Instruction(Instruction.Code.GALLOC, Instruction.TO_DEFINE);
        this.instructions.add(globalAlloc);
        for (SolParser.DeclarationContext declaration : ctx.declaration())
            this.visit(declaration);
        globalAlloc.backPatch(this.scope.getVariableCount()); // scope is going to be root
        Instruction mainCall = new Instruction(Instruction.Code.CALL, Instruction.TO_DEFINE); // we trust that it will be backpatched
        this.functionCalls.put("main", mainCall);
        this.instructions.add(mainCall);
        this.instructions.add(new Instruction(Instruction.Code.HALT));

        for (SolParser.FunctionDeclarationContext func : ctx.functionDeclaration())
            this.visit(func);
        Instruction mainCheck = this.functionCalls.get("main");
        if(mainCheck == null || mainCheck.getOperand() == Instruction.TO_DEFINE) throw new InternalError("Couldn't find main, something very wrong happened");

        // TODO: Remove any Lalloc 0 and Galloc 0
        return null;
    }

    @Override public Void visitScope(SolParser.ScopeContext ctx){
        this.scope = this.scopeAnnotations.get(ctx);
        Instruction alloc = new Instruction(Instruction.Code.LALLOC, Instruction.TO_DEFINE);
        this.instructions.add(alloc);
        ctx.declaration().forEach(this::visit);
        ctx.instruction().forEach(this::visit);
        
        int allocAmount = this.scope.getVariableCount();
        
        if(ctx.getParent() instanceof SolParser.BlockContext)
            this.instructions.add(new Instruction(Instruction.Code.POP,this.scope.getVariableCount()));
        
        if(ctx.getParent() instanceof  SolParser.FunctionDeclarationContext parent){
           allocAmount = allocAmount - this.functions.get(parent.IDENTIFIER().getText()).getArgTypes().size();
        }
        alloc.backPatch(allocAmount);
        this.scope = (ScopeTree) this.scope.getParent(); // return scope to previous state
       return null;
    }
    
    @Override
    public Void visitFunctionDeclaration(SolParser.FunctionDeclarationContext ctx){
        int currentInstructionSize = this.instructions.size();
        visit(ctx.scope());
        Instruction toJump = this.functionCalls.get(ctx.IDENTIFIER().getText());
        if(toJump == null){
            this.functionCalls.put(ctx.IDENTIFIER().getText(),new Instruction(Instruction.Code.CALL, currentInstructionSize));
        }
        else{
            toJump.backPatch(currentInstructionSize);
        }
        Function function = this.functions.get(ctx.IDENTIFIER().getText());
        Instruction.Code code = function.getReturnType().equals(Void.class) ? Instruction.Code.RET : Instruction.Code.RETVAL;
        this.instructions.add(new Instruction(code, function.getArgTypes().size()));
       return null;
    }

    private void addFunctionJumps(String identifier){
        Instruction toJump = this.functionCalls.get(identifier);

        if(toJump == null){
            Instruction newJump = new Instruction(Instruction.Code.CALL, Instruction.TO_DEFINE);
            this.functionCalls.put(identifier, newJump);
            toJump = newJump;
        }
        this.instructions.add(toJump);
    }
    @Override
    public Void visitNonVoidFunctionCall(SolParser.NonVoidFunctionCallContext ctx){
        ctx.expr().forEach(this::visit);
        this.addFunctionJumps(ctx.IDENTIFIER().getText());
        return null;
    }
    @Override
    public Void visitVoidFunctionCall(SolParser.VoidFunctionCallContext ctx){
        ctx.expr().forEach(this::visit);
        this.addFunctionJumps(ctx.IDENTIFIER().getText());
        return null;
    }

    public void compile(String[] args) throws IOException
    {
        this.parseArguments(args);
        this.initCompiler();
        this.semanticCheck();
        if (this.hasNoErrors())
        {
            this.generateInstructions();
            this.writeByteCodes();
        }
        else
            this.unsuccessfulCompileError();
    }

    private void parseArguments(String[] args)
    {
        if (args.length > MAX_ARGUMENTS_SIZE)
            RuntimeError.dispatchError("Invalid arguments size of " + args.length);

        for (int i = 0; i < args.length; i++)
            switch (args[i])
            {
                case SOURCE_FILE_FLAG:
                    String filename = args[++i];
                    this.sourceFileName = filename;
                    if (this.byteCodesFileName.equals(DEFAULT_BYTECODES_FILE_NAME))
                        this.byteCodesFileName = filename.replaceAll("\\.sol", ".tbc");
                    break;
                case BYTECODES_FILE_FLAG:
                    this.byteCodesFileName = args[++i];
                    break;
                case SHOW_ASSEMBLY_FLAG:
                    this.showAssembly = true;
                    break;
                case NO_TASM_FILE_FLAG:
                    this.generateTasmFile = false;
                    break;
                default:
                    RuntimeError.dispatchError("Invalid flag '" + args[i] + "'");
            }

        this.checkFiles();
    }

    private void checkFiles()
    {
        if (this.sourceFileName != null)
        {
            String[] splitSourceFileName = this.sourceFileName.split("\\.");
            String sourceFileExtension = splitSourceFileName[splitSourceFileName.length - 1];
            if (!sourceFileExtension.equals(SOURCE_FILE_EXTENSION))
                RuntimeError.dispatchError("Invalid source file extension '." + sourceFileExtension + "'");
        }

        String[] splitByteCodesFileName = this.byteCodesFileName.split("\\.");
        String byteCodesFileExtension = splitByteCodesFileName[splitByteCodesFileName.length - 1];
        if (!byteCodesFileExtension.equals(tVm.BYTECODES_FILE_EXTENSION))
            RuntimeError.dispatchError("Invalid bytecode file extension '." + byteCodesFileExtension + "'");
    }

    private void initCompiler() throws IOException
    {
        this.parser = this.generateParser();
        this.tree = this.parser.program();
        this.instructions = new ArrayList<>();
        this.constantPool = new ConstantPool();
        this.variableIndices = new HashMap<>();
        this.breaks = new Stack<>();
        this.reporter = new ErrorReporter();
        this.functionCalls = new HashMap<>();
    }

    private SolParser generateParser() throws IOException
    {
        InputStream inputStream = this.sourceFileName != null ? new FileInputStream(this.sourceFileName) : System.in;
        CharStream input = CharStreams.fromStream(inputStream);
        SolLexer lexer = new SolLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new SolParser(tokens);
    }

    private void semanticCheck()
    {
        solSemanticChecker semanticChecker = new solSemanticChecker(this.reporter);
        semanticChecker.semanticCheck(this.tree);
        this.annotatedTypes = semanticChecker.getAnnotatedTypes();
        this.scope = semanticChecker.getScope();
        this.scopeAnnotations = semanticChecker.getScopeAnnotations();
        this.functions = semanticChecker.getFunctions();
    }

    private boolean hasNoErrors()
    {
        return !this.reporter.hasReportedErrors() && !(this.parser.getNumberOfSyntaxErrors() > 0);
    }

    private void unsuccessfulCompileError()
    {
        //ANTLR should print all the syntax errors prior, if any
        System.err.println(this.reporter);

        int semanticErrorCount = this.reporter.getErrorCount();
        int syntaxErrorCount = this.parser.getNumberOfSyntaxErrors();
        String syntaxErrors = syntaxErrorCount == 1 ? " syntax error " : " syntax errors ";
        String semanticErrors = semanticErrorCount == 1 ? " semantic error" : " semantic errors";
        String message = "Program compiled unsuccessfully with " + syntaxErrorCount + syntaxErrors +
                "and " + semanticErrorCount + semanticErrors;

        RuntimeError.dispatchError(message);
    }

    private void generateInstructions()
    {
        this.visit(this.tree);
    }

    private void writeByteCodes() throws IOException
    {
        DataOutputStream byteCodes = new DataOutputStream(new FileOutputStream(this.byteCodesFileName));
        this.writeInstructions(byteCodes);
        this.writeConstantPool(byteCodes);
        if (this.showAssembly)
            this.printAssembledCode();
        if (this.generateTasmFile)
            this.writeTasmFile();
    }

    private void writeInstructions(DataOutputStream byteCodes) throws IOException
    {
        for (Instruction instruction : this.instructions)
        {
            byteCodes.writeByte(instruction.getInstruction().ordinal());
            if (instruction.getOperand() != null)
                byteCodes.writeInt(instruction.getOperand());
        }
        byteCodes.writeByte(ConstantPool.CONSTANT_POOL_DELIMITER);
    }

    private void writeConstantPool(DataOutputStream byteCodes) throws IOException
    {
        for (Value value : this.constantPool)
            if (value.getValue() instanceof Double doubleValue)
            {
                byteCodes.writeByte(ConstantPool.Type.DOUBLE.ordinal());
                byteCodes.writeDouble(doubleValue);
            }
            else if (value.getValue() instanceof String string)
            {
                byteCodes.writeByte(ConstantPool.Type.STRING.ordinal());
                byte[] stringBytes = string.getBytes(StandardCharsets.UTF_8);
                byteCodes.writeInt(stringBytes.length);
                byteCodes.write(stringBytes);
            }
    }

    private void printAssembledCode()
    {
        System.out.println("ASSEMBLED INSTRUCTIONS:");
        for (int i = 0; i < this.instructions.size(); i++)
            System.out.println(i + ":\t" + this.instructions.get(i));

        System.out.println("\nASSEMBLED CONSTANT POOL:");
        System.out.println(this.constantPool);
    }

    private void writeTasmFile() throws IOException
    {
        FileWriter tasmFile = new FileWriter(this.byteCodesFileName.replaceAll("\\.tbc", ".tasm"));
        String[] tasmCode = this.generateTasmCode();
        for (String line : tasmCode)
            tasmFile.write(line);
        tasmFile.close();
    }

    private String[] generateTasmCode()
    {
        String[] tasmCode = new String[this.instructions.size()];
        for (int i = 0; i < this.instructions.size(); i++)
        {
            Instruction instruction = this.instructions.get(i);
            tasmCode[i] = this.constructTasmLine(instruction);
        }
        return this.generateLabels(tasmCode);
    }

    private String constructTasmLine(Instruction instruction)
    {
        StringBuilder line = new StringBuilder("\t".repeat(4) + instruction.getInstruction().name().toLowerCase());
        switch (instruction.getInstruction())
        {
            case DCONST -> line.append(" ").append(this.constantPool.get(instruction.getOperand()));
            case SCONST -> line.append(" \"").append(this.constantPool.get(instruction.getOperand())).append("\"");
            case JUMP, JUMPT, JUMPF, CALL -> {
                String label = "_l" + instruction.getOperand();
                line.append(" ").append(label);
            }
            default -> {
                if (instruction.getOperand() != null)
                    line.append(" ").append(instruction.getOperand());
            }
        }
        line.append("\n");
        return line.toString();
    }

    private String[] generateLabels(String[] tasmCode)
    {
        for (Instruction instruction : this.instructions)
        {
            Instruction.Code code = instruction.getInstruction();
            if (code == Instruction.Code.JUMP || code == Instruction.Code.JUMPT || code == Instruction.Code.JUMPF || code == Instruction.Code.CALL)
            {
                String label = "_l" + instruction.getOperand() + ":";
                if (!tasmCode[instruction.getOperand()].contains(label))
                    tasmCode[instruction.getOperand()] = tasmCode[instruction.getOperand()].replaceFirst("\t", label);
            }
        }
        return tasmCode;
    }

    public static void main(String[] args)
    {
        solCompiler compiler = new solCompiler();
        try { compiler.compile(args); }
        catch (IOException e) { RuntimeError.dispatchError(e.getMessage()); }
    }
}