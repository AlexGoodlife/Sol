// Generated from /home/compl3x/Programations/cop_projeto/P4/src/Sol.g4 by ANTLR 4.13.1
package antlrSol;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class SolParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, INT=3, DOUBLE=4, STRING=5, BOOLEAN=6, PRINT=7, INT_T=8, 
		DOUBLE_T=9, STRING_T=10, BOOLEAN_T=11, TRUE=12, FALSE=13, BEGIN=14, END=15, 
		WHILE=16, DO=17, FOR=18, TO=19, IF=20, THEN=21, ELSE=22, BREAK=23, VOID=24, 
		RETURN=25, MULT=26, ADD=27, MINUS=28, MOD=29, DIV=30, LPAREN=31, RPAREN=32, 
		GT=33, LT=34, GET=35, EQ=36, NEQ=37, LET=38, NOT=39, AND=40, OR=41, IDENTIFIER=42, 
		EOL=43, WS=44, SL_COMMENT=45, ML_COMMENT=46, LETTER=47;
	public static final int
		RULE_program = 0, RULE_declaration = 1, RULE_declarationAssign = 2, RULE_functionDeclaration = 3, 
		RULE_argument = 4, RULE_scope = 5, RULE_instruction = 6, RULE_assign = 7, 
		RULE_expr = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "declaration", "declarationAssign", "functionDeclaration", 
			"argument", "scope", "instruction", "assign", "expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'='", null, null, null, null, "'print'", "'int'", "'real'", 
			"'string'", "'bool'", "'true'", "'false'", "'begin'", "'end'", "'while'", 
			"'do'", "'for'", "'to'", "'if'", "'then'", "'else'", "'break'", "'void'", 
			"'return'", "'*'", "'+'", "'-'", "'%'", "'/'", "'('", "')'", "'>'", "'<'", 
			"'>='", "'=='", "'!='", "'<='", "'not'", "'and'", "'or'", null, "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "INT", "DOUBLE", "STRING", "BOOLEAN", "PRINT", "INT_T", 
			"DOUBLE_T", "STRING_T", "BOOLEAN_T", "TRUE", "FALSE", "BEGIN", "END", 
			"WHILE", "DO", "FOR", "TO", "IF", "THEN", "ELSE", "BREAK", "VOID", "RETURN", 
			"MULT", "ADD", "MINUS", "MOD", "DIV", "LPAREN", "RPAREN", "GT", "LT", 
			"GET", "EQ", "NEQ", "LET", "NOT", "AND", "OR", "IDENTIFIER", "EOL", "WS", 
			"SL_COMMENT", "ML_COMMENT", "LETTER"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Sol.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SolParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(SolParser.EOF, 0); }
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<FunctionDeclarationContext> functionDeclaration() {
			return getRuleContexts(FunctionDeclarationContext.class);
		}
		public FunctionDeclarationContext functionDeclaration(int i) {
			return getRuleContext(FunctionDeclarationContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(18);
					declaration();
					}
					} 
				}
				setState(23);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(25); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(24);
				functionDeclaration();
				}
				}
				setState(27); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 16781056L) != 0) );
			setState(29);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationContext extends ParserRuleContext {
		public Token type;
		public List<DeclarationAssignContext> declarationAssign() {
			return getRuleContexts(DeclarationAssignContext.class);
		}
		public DeclarationAssignContext declarationAssign(int i) {
			return getRuleContext(DeclarationAssignContext.class,i);
		}
		public TerminalNode EOL() { return getToken(SolParser.EOL, 0); }
		public TerminalNode INT_T() { return getToken(SolParser.INT_T, 0); }
		public TerminalNode DOUBLE_T() { return getToken(SolParser.DOUBLE_T, 0); }
		public TerminalNode STRING_T() { return getToken(SolParser.STRING_T, 0); }
		public TerminalNode BOOLEAN_T() { return getToken(SolParser.BOOLEAN_T, 0); }
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			((DeclarationContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3840L) != 0)) ) {
				((DeclarationContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(32);
			declarationAssign();
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(33);
				match(T__0);
				setState(34);
				declarationAssign();
				}
				}
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(40);
			match(EOL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationAssignContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SolParser.IDENTIFIER, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DeclarationAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterDeclarationAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitDeclarationAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitDeclarationAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationAssignContext declarationAssign() throws RecognitionException {
		DeclarationAssignContext _localctx = new DeclarationAssignContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declarationAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(IDENTIFIER);
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(43);
				match(T__1);
				setState(44);
				expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionDeclarationContext extends ParserRuleContext {
		public Token type;
		public TerminalNode IDENTIFIER() { return getToken(SolParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(SolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(SolParser.RPAREN, 0); }
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public TerminalNode INT_T() { return getToken(SolParser.INT_T, 0); }
		public TerminalNode DOUBLE_T() { return getToken(SolParser.DOUBLE_T, 0); }
		public TerminalNode STRING_T() { return getToken(SolParser.STRING_T, 0); }
		public TerminalNode BOOLEAN_T() { return getToken(SolParser.BOOLEAN_T, 0); }
		public TerminalNode VOID() { return getToken(SolParser.VOID, 0); }
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitFunctionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_functionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			((FunctionDeclarationContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 16781056L) != 0)) ) {
				((FunctionDeclarationContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(48);
			match(IDENTIFIER);
			setState(49);
			match(LPAREN);
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3840L) != 0)) {
				{
				setState(50);
				argument();
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(51);
					match(T__0);
					setState(52);
					argument();
					}
					}
					setState(57);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(60);
			match(RPAREN);
			setState(61);
			scope();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentContext extends ParserRuleContext {
		public Token type;
		public TerminalNode IDENTIFIER() { return getToken(SolParser.IDENTIFIER, 0); }
		public TerminalNode INT_T() { return getToken(SolParser.INT_T, 0); }
		public TerminalNode DOUBLE_T() { return getToken(SolParser.DOUBLE_T, 0); }
		public TerminalNode STRING_T() { return getToken(SolParser.STRING_T, 0); }
		public TerminalNode BOOLEAN_T() { return getToken(SolParser.BOOLEAN_T, 0); }
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_argument);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			((ArgumentContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3840L) != 0)) ) {
				((ArgumentContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(64);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScopeContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(SolParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(SolParser.END, 0); }
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public ScopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterScope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitScope(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitScope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScopeContext scope() throws RecognitionException {
		ScopeContext _localctx = new ScopeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_scope);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(BEGIN);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3840L) != 0)) {
				{
				{
				setState(67);
				declaration();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 13194182869120L) != 0)) {
				{
				{
				setState(73);
				instruction();
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(79);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstructionContext extends ParserRuleContext {
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
	 
		public InstructionContext() { }
		public void copyFrom(InstructionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentContext extends InstructionContext {
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public TerminalNode EOL() { return getToken(SolParser.EOL, 0); }
		public AssignmentContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrintContext extends InstructionContext {
		public TerminalNode PRINT() { return getToken(SolParser.PRINT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EOL() { return getToken(SolParser.EOL, 0); }
		public PrintContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EmptyContext extends InstructionContext {
		public TerminalNode EOL() { return getToken(SolParser.EOL, 0); }
		public EmptyContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterEmpty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitEmpty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitEmpty(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReturnContext extends InstructionContext {
		public TerminalNode RETURN() { return getToken(SolParser.RETURN, 0); }
		public TerminalNode EOL() { return getToken(SolParser.EOL, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForContext extends InstructionContext {
		public TerminalNode FOR() { return getToken(SolParser.FOR, 0); }
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public TerminalNode TO() { return getToken(SolParser.TO, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DO() { return getToken(SolParser.DO, 0); }
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public ForContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitFor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitFor(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BreakContext extends InstructionContext {
		public TerminalNode BREAK() { return getToken(SolParser.BREAK, 0); }
		public TerminalNode EOL() { return getToken(SolParser.EOL, 0); }
		public BreakContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterBreak(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitBreak(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitBreak(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends InstructionContext {
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public BlockContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileContext extends InstructionContext {
		public TerminalNode WHILE() { return getToken(SolParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DO() { return getToken(SolParser.DO, 0); }
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public WhileContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VoidFunctionCallContext extends InstructionContext {
		public TerminalNode IDENTIFIER() { return getToken(SolParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(SolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(SolParser.RPAREN, 0); }
		public TerminalNode EOL() { return getToken(SolParser.EOL, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public VoidFunctionCallContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterVoidFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitVoidFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitVoidFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfContext extends InstructionContext {
		public TerminalNode IF() { return getToken(SolParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode THEN() { return getToken(SolParser.THEN, 0); }
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(SolParser.ELSE, 0); }
		public IfContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_instruction);
		int _la;
		try {
			setState(131);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new PrintContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				match(PRINT);
				setState(82);
				expr(0);
				setState(83);
				match(EOL);
				}
				break;
			case 2:
				_localctx = new AssignmentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				assign();
				setState(86);
				match(EOL);
				}
				break;
			case 3:
				_localctx = new BlockContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(88);
				scope();
				}
				break;
			case 4:
				_localctx = new WhileContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(89);
				match(WHILE);
				setState(90);
				expr(0);
				setState(91);
				match(DO);
				setState(92);
				instruction();
				}
				break;
			case 5:
				_localctx = new ForContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(94);
				match(FOR);
				setState(95);
				assign();
				setState(96);
				match(TO);
				setState(97);
				expr(0);
				setState(98);
				match(DO);
				setState(99);
				instruction();
				}
				break;
			case 6:
				_localctx = new IfContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(101);
				match(IF);
				setState(102);
				expr(0);
				setState(103);
				match(THEN);
				setState(104);
				instruction();
				setState(107);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(105);
					match(ELSE);
					setState(106);
					instruction();
					}
					break;
				}
				}
				break;
			case 7:
				_localctx = new EmptyContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(109);
				match(EOL);
				}
				break;
			case 8:
				_localctx = new BreakContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(110);
				match(BREAK);
				setState(111);
				match(EOL);
				}
				break;
			case 9:
				_localctx = new VoidFunctionCallContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(112);
				match(IDENTIFIER);
				setState(113);
				match(LPAREN);
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4950218244216L) != 0)) {
					{
					setState(114);
					expr(0);
					setState(119);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__0) {
						{
						{
						setState(115);
						match(T__0);
						setState(116);
						expr(0);
						}
						}
						setState(121);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(124);
				match(RPAREN);
				setState(125);
				match(EOL);
				}
				break;
			case 10:
				_localctx = new ReturnContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(126);
				match(RETURN);
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4950218244216L) != 0)) {
					{
					setState(127);
					expr(0);
					}
				}

				setState(130);
				match(EOL);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SolParser.IDENTIFIER, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(IDENTIFIER);
			setState(134);
			match(T__1);
			setState(135);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OR() { return getToken(SolParser.OR, 0); }
		public OrContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitOr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NegationContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(SolParser.MINUS, 0); }
		public TerminalNode NOT() { return getToken(SolParser.NOT, 0); }
		public NegationContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitNegation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitNegation(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddSubContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ADD() { return getToken(SolParser.ADD, 0); }
		public TerminalNode MINUS() { return getToken(SolParser.MINUS, 0); }
		public AddSubContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitAddSub(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NonVoidFunctionCallContext extends ExprContext {
		public TerminalNode IDENTIFIER() { return getToken(SolParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(SolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(SolParser.RPAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public NonVoidFunctionCallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterNonVoidFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitNonVoidFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitNonVoidFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelationalContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LT() { return getToken(SolParser.LT, 0); }
		public TerminalNode GT() { return getToken(SolParser.GT, 0); }
		public TerminalNode LET() { return getToken(SolParser.LET, 0); }
		public TerminalNode GET() { return getToken(SolParser.GET, 0); }
		public RelationalContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterRelational(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitRelational(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitRelational(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends ExprContext {
		public TerminalNode STRING() { return getToken(SolParser.STRING, 0); }
		public StringContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DoubleContext extends ExprContext {
		public TerminalNode DOUBLE() { return getToken(SolParser.DOUBLE, 0); }
		public DoubleContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterDouble(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitDouble(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitDouble(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntContext extends ExprContext {
		public TerminalNode INT() { return getToken(SolParser.INT, 0); }
		public IntContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends ExprContext {
		public TerminalNode IDENTIFIER() { return getToken(SolParser.IDENTIFIER, 0); }
		public IdentifierContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultDivModContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MULT() { return getToken(SolParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(SolParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(SolParser.MOD, 0); }
		public MultDivModContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterMultDivMod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitMultDivMod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitMultDivMod(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode AND() { return getToken(SolParser.AND, 0); }
		public AndContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualityContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode EQ() { return getToken(SolParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(SolParser.NEQ, 0); }
		public EqualityContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterEquality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitEquality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitEquality(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanContext extends ExprContext {
		public TerminalNode BOOLEAN() { return getToken(SolParser.BOOLEAN, 0); }
		public BooleanContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitBoolean(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenthesesContext extends ExprContext {
		public TerminalNode LPAREN() { return getToken(SolParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SolParser.RPAREN, 0); }
		public ParenthesesContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).enterParentheses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolListener ) ((SolListener)listener).exitParentheses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolVisitor ) return ((SolVisitor<? extends T>)visitor).visitParentheses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				_localctx = new ParenthesesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(138);
				match(LPAREN);
				setState(139);
				expr(0);
				setState(140);
				match(RPAREN);
				}
				break;
			case 2:
				{
				_localctx = new NegationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(142);
				((NegationContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==MINUS || _la==NOT) ) {
					((NegationContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(143);
				expr(13);
				}
				break;
			case 3:
				{
				_localctx = new IntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(144);
				match(INT);
				}
				break;
			case 4:
				{
				_localctx = new DoubleContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(145);
				match(DOUBLE);
				}
				break;
			case 5:
				{
				_localctx = new StringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(146);
				match(STRING);
				}
				break;
			case 6:
				{
				_localctx = new BooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(147);
				match(BOOLEAN);
				}
				break;
			case 7:
				{
				_localctx = new IdentifierContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(148);
				match(IDENTIFIER);
				}
				break;
			case 8:
				{
				_localctx = new NonVoidFunctionCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(149);
				match(IDENTIFIER);
				setState(150);
				match(LPAREN);
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4950218244216L) != 0)) {
					{
					setState(151);
					expr(0);
					setState(156);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__0) {
						{
						{
						setState(152);
						match(T__0);
						setState(153);
						expr(0);
						}
						}
						setState(158);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(161);
				match(RPAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(184);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(182);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new MultDivModContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(164);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(165);
						((MultDivModContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1677721600L) != 0)) ) {
							((MultDivModContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(166);
						expr(13);
						}
						break;
					case 2:
						{
						_localctx = new AddSubContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(167);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(168);
						((AddSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==MINUS) ) {
							((AddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(169);
						expr(12);
						}
						break;
					case 3:
						{
						_localctx = new RelationalContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(170);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(171);
						((RelationalContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 335007449088L) != 0)) ) {
							((RelationalContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(172);
						expr(11);
						}
						break;
					case 4:
						{
						_localctx = new EqualityContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(173);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(174);
						((EqualityContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQ || _la==NEQ) ) {
							((EqualityContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(175);
						expr(10);
						}
						break;
					case 5:
						{
						_localctx = new AndContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(176);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(177);
						match(AND);
						setState(178);
						expr(9);
						}
						break;
					case 6:
						{
						_localctx = new OrContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(179);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(180);
						match(OR);
						setState(181);
						expr(8);
						}
						break;
					}
					} 
				}
				setState(186);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 8:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 12);
		case 1:
			return precpred(_ctx, 11);
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001/\u00bc\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0001\u0000\u0005\u0000\u0014\b\u0000\n\u0000\f\u0000\u0017"+
		"\t\u0000\u0001\u0000\u0004\u0000\u001a\b\u0000\u000b\u0000\f\u0000\u001b"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0005\u0001$\b\u0001\n\u0001\f\u0001\'\t\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002.\b\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003"+
		"6\b\u0003\n\u0003\f\u00039\t\u0003\u0003\u0003;\b\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0005\u0005E\b\u0005\n\u0005\f\u0005H\t\u0005\u0001\u0005\u0005"+
		"\u0005K\b\u0005\n\u0005\f\u0005N\t\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006l\b\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005"+
		"\u0006v\b\u0006\n\u0006\f\u0006y\t\u0006\u0003\u0006{\b\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0081\b\u0006\u0001\u0006"+
		"\u0003\u0006\u0084\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005"+
		"\b\u009b\b\b\n\b\f\b\u009e\t\b\u0003\b\u00a0\b\b\u0001\b\u0003\b\u00a3"+
		"\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0005\b\u00b7\b\b\n\b\f\b\u00ba\t\b\u0001\b\u0000\u0001\u0010\t\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0000\u0007\u0001\u0000\b\u000b\u0002"+
		"\u0000\b\u000b\u0018\u0018\u0002\u0000\u001c\u001c\'\'\u0002\u0000\u001a"+
		"\u001a\u001d\u001e\u0001\u0000\u001b\u001c\u0002\u0000!#&&\u0001\u0000"+
		"$%\u00d6\u0000\u0015\u0001\u0000\u0000\u0000\u0002\u001f\u0001\u0000\u0000"+
		"\u0000\u0004*\u0001\u0000\u0000\u0000\u0006/\u0001\u0000\u0000\u0000\b"+
		"?\u0001\u0000\u0000\u0000\nB\u0001\u0000\u0000\u0000\f\u0083\u0001\u0000"+
		"\u0000\u0000\u000e\u0085\u0001\u0000\u0000\u0000\u0010\u00a2\u0001\u0000"+
		"\u0000\u0000\u0012\u0014\u0003\u0002\u0001\u0000\u0013\u0012\u0001\u0000"+
		"\u0000\u0000\u0014\u0017\u0001\u0000\u0000\u0000\u0015\u0013\u0001\u0000"+
		"\u0000\u0000\u0015\u0016\u0001\u0000\u0000\u0000\u0016\u0019\u0001\u0000"+
		"\u0000\u0000\u0017\u0015\u0001\u0000\u0000\u0000\u0018\u001a\u0003\u0006"+
		"\u0003\u0000\u0019\u0018\u0001\u0000\u0000\u0000\u001a\u001b\u0001\u0000"+
		"\u0000\u0000\u001b\u0019\u0001\u0000\u0000\u0000\u001b\u001c\u0001\u0000"+
		"\u0000\u0000\u001c\u001d\u0001\u0000\u0000\u0000\u001d\u001e\u0005\u0000"+
		"\u0000\u0001\u001e\u0001\u0001\u0000\u0000\u0000\u001f \u0007\u0000\u0000"+
		"\u0000 %\u0003\u0004\u0002\u0000!\"\u0005\u0001\u0000\u0000\"$\u0003\u0004"+
		"\u0002\u0000#!\u0001\u0000\u0000\u0000$\'\u0001\u0000\u0000\u0000%#\u0001"+
		"\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&(\u0001\u0000\u0000\u0000"+
		"\'%\u0001\u0000\u0000\u0000()\u0005+\u0000\u0000)\u0003\u0001\u0000\u0000"+
		"\u0000*-\u0005*\u0000\u0000+,\u0005\u0002\u0000\u0000,.\u0003\u0010\b"+
		"\u0000-+\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000.\u0005\u0001"+
		"\u0000\u0000\u0000/0\u0007\u0001\u0000\u000001\u0005*\u0000\u00001:\u0005"+
		"\u001f\u0000\u000027\u0003\b\u0004\u000034\u0005\u0001\u0000\u000046\u0003"+
		"\b\u0004\u000053\u0001\u0000\u0000\u000069\u0001\u0000\u0000\u000075\u0001"+
		"\u0000\u0000\u000078\u0001\u0000\u0000\u00008;\u0001\u0000\u0000\u0000"+
		"97\u0001\u0000\u0000\u0000:2\u0001\u0000\u0000\u0000:;\u0001\u0000\u0000"+
		"\u0000;<\u0001\u0000\u0000\u0000<=\u0005 \u0000\u0000=>\u0003\n\u0005"+
		"\u0000>\u0007\u0001\u0000\u0000\u0000?@\u0007\u0000\u0000\u0000@A\u0005"+
		"*\u0000\u0000A\t\u0001\u0000\u0000\u0000BF\u0005\u000e\u0000\u0000CE\u0003"+
		"\u0002\u0001\u0000DC\u0001\u0000\u0000\u0000EH\u0001\u0000\u0000\u0000"+
		"FD\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000GL\u0001\u0000\u0000"+
		"\u0000HF\u0001\u0000\u0000\u0000IK\u0003\f\u0006\u0000JI\u0001\u0000\u0000"+
		"\u0000KN\u0001\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000LM\u0001\u0000"+
		"\u0000\u0000MO\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000OP\u0005"+
		"\u000f\u0000\u0000P\u000b\u0001\u0000\u0000\u0000QR\u0005\u0007\u0000"+
		"\u0000RS\u0003\u0010\b\u0000ST\u0005+\u0000\u0000T\u0084\u0001\u0000\u0000"+
		"\u0000UV\u0003\u000e\u0007\u0000VW\u0005+\u0000\u0000W\u0084\u0001\u0000"+
		"\u0000\u0000X\u0084\u0003\n\u0005\u0000YZ\u0005\u0010\u0000\u0000Z[\u0003"+
		"\u0010\b\u0000[\\\u0005\u0011\u0000\u0000\\]\u0003\f\u0006\u0000]\u0084"+
		"\u0001\u0000\u0000\u0000^_\u0005\u0012\u0000\u0000_`\u0003\u000e\u0007"+
		"\u0000`a\u0005\u0013\u0000\u0000ab\u0003\u0010\b\u0000bc\u0005\u0011\u0000"+
		"\u0000cd\u0003\f\u0006\u0000d\u0084\u0001\u0000\u0000\u0000ef\u0005\u0014"+
		"\u0000\u0000fg\u0003\u0010\b\u0000gh\u0005\u0015\u0000\u0000hk\u0003\f"+
		"\u0006\u0000ij\u0005\u0016\u0000\u0000jl\u0003\f\u0006\u0000ki\u0001\u0000"+
		"\u0000\u0000kl\u0001\u0000\u0000\u0000l\u0084\u0001\u0000\u0000\u0000"+
		"m\u0084\u0005+\u0000\u0000no\u0005\u0017\u0000\u0000o\u0084\u0005+\u0000"+
		"\u0000pq\u0005*\u0000\u0000qz\u0005\u001f\u0000\u0000rw\u0003\u0010\b"+
		"\u0000st\u0005\u0001\u0000\u0000tv\u0003\u0010\b\u0000us\u0001\u0000\u0000"+
		"\u0000vy\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000wx\u0001\u0000"+
		"\u0000\u0000x{\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000zr\u0001"+
		"\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000"+
		"|}\u0005 \u0000\u0000}\u0084\u0005+\u0000\u0000~\u0080\u0005\u0019\u0000"+
		"\u0000\u007f\u0081\u0003\u0010\b\u0000\u0080\u007f\u0001\u0000\u0000\u0000"+
		"\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000\u0000\u0000"+
		"\u0082\u0084\u0005+\u0000\u0000\u0083Q\u0001\u0000\u0000\u0000\u0083U"+
		"\u0001\u0000\u0000\u0000\u0083X\u0001\u0000\u0000\u0000\u0083Y\u0001\u0000"+
		"\u0000\u0000\u0083^\u0001\u0000\u0000\u0000\u0083e\u0001\u0000\u0000\u0000"+
		"\u0083m\u0001\u0000\u0000\u0000\u0083n\u0001\u0000\u0000\u0000\u0083p"+
		"\u0001\u0000\u0000\u0000\u0083~\u0001\u0000\u0000\u0000\u0084\r\u0001"+
		"\u0000\u0000\u0000\u0085\u0086\u0005*\u0000\u0000\u0086\u0087\u0005\u0002"+
		"\u0000\u0000\u0087\u0088\u0003\u0010\b\u0000\u0088\u000f\u0001\u0000\u0000"+
		"\u0000\u0089\u008a\u0006\b\uffff\uffff\u0000\u008a\u008b\u0005\u001f\u0000"+
		"\u0000\u008b\u008c\u0003\u0010\b\u0000\u008c\u008d\u0005 \u0000\u0000"+
		"\u008d\u00a3\u0001\u0000\u0000\u0000\u008e\u008f\u0007\u0002\u0000\u0000"+
		"\u008f\u00a3\u0003\u0010\b\r\u0090\u00a3\u0005\u0003\u0000\u0000\u0091"+
		"\u00a3\u0005\u0004\u0000\u0000\u0092\u00a3\u0005\u0005\u0000\u0000\u0093"+
		"\u00a3\u0005\u0006\u0000\u0000\u0094\u00a3\u0005*\u0000\u0000\u0095\u0096"+
		"\u0005*\u0000\u0000\u0096\u009f\u0005\u001f\u0000\u0000\u0097\u009c\u0003"+
		"\u0010\b\u0000\u0098\u0099\u0005\u0001\u0000\u0000\u0099\u009b\u0003\u0010"+
		"\b\u0000\u009a\u0098\u0001\u0000\u0000\u0000\u009b\u009e\u0001\u0000\u0000"+
		"\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000"+
		"\u0000\u009d\u00a0\u0001\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000"+
		"\u0000\u009f\u0097\u0001\u0000\u0000\u0000\u009f\u00a0\u0001\u0000\u0000"+
		"\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u00a3\u0005 \u0000\u0000"+
		"\u00a2\u0089\u0001\u0000\u0000\u0000\u00a2\u008e\u0001\u0000\u0000\u0000"+
		"\u00a2\u0090\u0001\u0000\u0000\u0000\u00a2\u0091\u0001\u0000\u0000\u0000"+
		"\u00a2\u0092\u0001\u0000\u0000\u0000\u00a2\u0093\u0001\u0000\u0000\u0000"+
		"\u00a2\u0094\u0001\u0000\u0000\u0000\u00a2\u0095\u0001\u0000\u0000\u0000"+
		"\u00a3\u00b8\u0001\u0000\u0000\u0000\u00a4\u00a5\n\f\u0000\u0000\u00a5"+
		"\u00a6\u0007\u0003\u0000\u0000\u00a6\u00b7\u0003\u0010\b\r\u00a7\u00a8"+
		"\n\u000b\u0000\u0000\u00a8\u00a9\u0007\u0004\u0000\u0000\u00a9\u00b7\u0003"+
		"\u0010\b\f\u00aa\u00ab\n\n\u0000\u0000\u00ab\u00ac\u0007\u0005\u0000\u0000"+
		"\u00ac\u00b7\u0003\u0010\b\u000b\u00ad\u00ae\n\t\u0000\u0000\u00ae\u00af"+
		"\u0007\u0006\u0000\u0000\u00af\u00b7\u0003\u0010\b\n\u00b0\u00b1\n\b\u0000"+
		"\u0000\u00b1\u00b2\u0005(\u0000\u0000\u00b2\u00b7\u0003\u0010\b\t\u00b3"+
		"\u00b4\n\u0007\u0000\u0000\u00b4\u00b5\u0005)\u0000\u0000\u00b5\u00b7"+
		"\u0003\u0010\b\b\u00b6\u00a4\u0001\u0000\u0000\u0000\u00b6\u00a7\u0001"+
		"\u0000\u0000\u0000\u00b6\u00aa\u0001\u0000\u0000\u0000\u00b6\u00ad\u0001"+
		"\u0000\u0000\u0000\u00b6\u00b0\u0001\u0000\u0000\u0000\u00b6\u00b3\u0001"+
		"\u0000\u0000\u0000\u00b7\u00ba\u0001\u0000\u0000\u0000\u00b8\u00b6\u0001"+
		"\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u0011\u0001"+
		"\u0000\u0000\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000\u0012\u0015\u001b"+
		"%-7:FLkwz\u0080\u0083\u009c\u009f\u00a2\u00b6\u00b8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}