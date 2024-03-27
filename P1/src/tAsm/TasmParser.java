// Generated from /home/alex/uni/COP_2324/cop_projeto/P1/src/Tasm.g4 by ANTLR 4.13.1
package tAsm;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class TasmParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, INT=3, DOUBLE=4, STRING=5, BOOL=6, LOAD_INT=7, LOAD_DOUBLE=8, 
		LOAD_STRING=9, UJUMP=10, CJUMP_TRUE=11, CJUMP_FALSE=12, GLOBAL_ALLOC=13, 
		GLOBAL_LOAD=14, GLOBAL_STORE=15, SIMPLE_INSTRUCTION=16, LABEL=17, EOL=18, 
		WS=19, LETTER=20;
	public static final int
		RULE_program = 0, RULE_line = 1, RULE_instruction = 2, RULE_instructionWithArguments = 3, 
		RULE_simpleInstruction = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "line", "instruction", "instructionWithArguments", "simpleInstruction"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "':'", null, null, null, null, "'iconst'", "'dconst'", "'sconst'", 
			"'jump'", "'jumpt'", "'jumpf'", "'galloc'", "'gload'", "'gstore'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "INT", "DOUBLE", "STRING", "BOOL", "LOAD_INT", "LOAD_DOUBLE", 
			"LOAD_STRING", "UJUMP", "CJUMP_TRUE", "CJUMP_FALSE", "GLOBAL_ALLOC", 
			"GLOBAL_LOAD", "GLOBAL_STORE", "SIMPLE_INSTRUCTION", "LABEL", "EOL", 
			"WS", "LETTER"
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
	public String getGrammarFileName() { return "Tasm.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TasmParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public TerminalNode EOF() { return getToken(TasmParser.EOF, 0); }
		public List<TerminalNode> EOL() { return getTokens(TasmParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(TasmParser.EOL, i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TasmVisitor ) return ((TasmVisitor<? extends T>)visitor).visitProgram(this);
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
			setState(10);
			line();
			setState(19);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(12); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(11);
						match(EOL);
						}
						}
						setState(14); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==EOL );
					setState(16);
					line();
					}
					} 
				}
				setState(21);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(22);
				match(EOL);
				}
				}
				setState(27);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(28);
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
	public static class LineContext extends ParserRuleContext {
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public List<TerminalNode> LABEL() { return getTokens(TasmParser.LABEL); }
		public TerminalNode LABEL(int i) {
			return getToken(TasmParser.LABEL, i);
		}
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TasmVisitor ) return ((TasmVisitor<? extends T>)visitor).visitLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_line);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LABEL) {
				{
				setState(30);
				match(LABEL);
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(31);
					match(T__0);
					setState(32);
					match(LABEL);
					}
					}
					setState(37);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(38);
				match(T__1);
				}
			}

			setState(41);
			instruction();
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
		public InstructionWithArgumentsContext instructionWithArguments() {
			return getRuleContext(InstructionWithArgumentsContext.class,0);
		}
		public SimpleInstructionContext simpleInstruction() {
			return getRuleContext(SimpleInstructionContext.class,0);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TasmVisitor ) return ((TasmVisitor<? extends T>)visitor).visitInstruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_instruction);
		try {
			setState(45);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOAD_INT:
			case LOAD_DOUBLE:
			case LOAD_STRING:
			case UJUMP:
			case CJUMP_TRUE:
			case CJUMP_FALSE:
			case GLOBAL_ALLOC:
			case GLOBAL_LOAD:
			case GLOBAL_STORE:
				enterOuterAlt(_localctx, 1);
				{
				setState(43);
				instructionWithArguments();
				}
				break;
			case SIMPLE_INSTRUCTION:
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				simpleInstruction();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class InstructionWithArgumentsContext extends ParserRuleContext {
		public InstructionWithArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instructionWithArguments; }
	 
		public InstructionWithArgumentsContext() { }
		public void copyFrom(InstructionWithArgumentsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LoadIntContext extends InstructionWithArgumentsContext {
		public TerminalNode LOAD_INT() { return getToken(TasmParser.LOAD_INT, 0); }
		public TerminalNode INT() { return getToken(TasmParser.INT, 0); }
		public LoadIntContext(InstructionWithArgumentsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterLoadInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitLoadInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TasmVisitor ) return ((TasmVisitor<? extends T>)visitor).visitLoadInt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LoadDoubleContext extends InstructionWithArgumentsContext {
		public Token value;
		public TerminalNode LOAD_DOUBLE() { return getToken(TasmParser.LOAD_DOUBLE, 0); }
		public TerminalNode DOUBLE() { return getToken(TasmParser.DOUBLE, 0); }
		public TerminalNode INT() { return getToken(TasmParser.INT, 0); }
		public LoadDoubleContext(InstructionWithArgumentsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterLoadDouble(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitLoadDouble(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TasmVisitor ) return ((TasmVisitor<? extends T>)visitor).visitLoadDouble(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LoadStringContext extends InstructionWithArgumentsContext {
		public TerminalNode LOAD_STRING() { return getToken(TasmParser.LOAD_STRING, 0); }
		public TerminalNode STRING() { return getToken(TasmParser.STRING, 0); }
		public LoadStringContext(InstructionWithArgumentsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterLoadString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitLoadString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TasmVisitor ) return ((TasmVisitor<? extends T>)visitor).visitLoadString(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class JumpContext extends InstructionWithArgumentsContext {
		public Token jump;
		public TerminalNode LABEL() { return getToken(TasmParser.LABEL, 0); }
		public TerminalNode UJUMP() { return getToken(TasmParser.UJUMP, 0); }
		public TerminalNode CJUMP_TRUE() { return getToken(TasmParser.CJUMP_TRUE, 0); }
		public TerminalNode CJUMP_FALSE() { return getToken(TasmParser.CJUMP_FALSE, 0); }
		public JumpContext(InstructionWithArgumentsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterJump(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitJump(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TasmVisitor ) return ((TasmVisitor<? extends T>)visitor).visitJump(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GlobalContext extends InstructionWithArgumentsContext {
		public Token global;
		public TerminalNode INT() { return getToken(TasmParser.INT, 0); }
		public TerminalNode GLOBAL_ALLOC() { return getToken(TasmParser.GLOBAL_ALLOC, 0); }
		public TerminalNode GLOBAL_LOAD() { return getToken(TasmParser.GLOBAL_LOAD, 0); }
		public TerminalNode GLOBAL_STORE() { return getToken(TasmParser.GLOBAL_STORE, 0); }
		public GlobalContext(InstructionWithArgumentsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterGlobal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitGlobal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TasmVisitor ) return ((TasmVisitor<? extends T>)visitor).visitGlobal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionWithArgumentsContext instructionWithArguments() throws RecognitionException {
		InstructionWithArgumentsContext _localctx = new InstructionWithArgumentsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_instructionWithArguments);
		int _la;
		try {
			setState(57);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOAD_INT:
				_localctx = new LoadIntContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(47);
				match(LOAD_INT);
				setState(48);
				match(INT);
				}
				break;
			case LOAD_DOUBLE:
				_localctx = new LoadDoubleContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(49);
				match(LOAD_DOUBLE);
				setState(50);
				((LoadDoubleContext)_localctx).value = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==INT || _la==DOUBLE) ) {
					((LoadDoubleContext)_localctx).value = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case LOAD_STRING:
				_localctx = new LoadStringContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(51);
				match(LOAD_STRING);
				setState(52);
				match(STRING);
				}
				break;
			case UJUMP:
			case CJUMP_TRUE:
			case CJUMP_FALSE:
				_localctx = new JumpContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(53);
				((JumpContext)_localctx).jump = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 7168L) != 0)) ) {
					((JumpContext)_localctx).jump = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(54);
				match(LABEL);
				}
				break;
			case GLOBAL_ALLOC:
			case GLOBAL_LOAD:
			case GLOBAL_STORE:
				_localctx = new GlobalContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(55);
				((GlobalContext)_localctx).global = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 57344L) != 0)) ) {
					((GlobalContext)_localctx).global = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(56);
				match(INT);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class SimpleInstructionContext extends ParserRuleContext {
		public TerminalNode SIMPLE_INSTRUCTION() { return getToken(TasmParser.SIMPLE_INSTRUCTION, 0); }
		public SimpleInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterSimpleInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitSimpleInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TasmVisitor ) return ((TasmVisitor<? extends T>)visitor).visitSimpleInstruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleInstructionContext simpleInstruction() throws RecognitionException {
		SimpleInstructionContext _localctx = new SimpleInstructionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_simpleInstruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(SIMPLE_INSTRUCTION);
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

	public static final String _serializedATN =
		"\u0004\u0001\u0014>\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001"+
		"\u0000\u0001\u0000\u0004\u0000\r\b\u0000\u000b\u0000\f\u0000\u000e\u0001"+
		"\u0000\u0005\u0000\u0012\b\u0000\n\u0000\f\u0000\u0015\t\u0000\u0001\u0000"+
		"\u0005\u0000\u0018\b\u0000\n\u0000\f\u0000\u001b\t\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001\"\b\u0001\n\u0001"+
		"\f\u0001%\t\u0001\u0001\u0001\u0003\u0001(\b\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0003\u0002.\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003:\b\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0000\u0000\u0005\u0000\u0002\u0004\u0006\b\u0000\u0003\u0001"+
		"\u0000\u0003\u0004\u0001\u0000\n\f\u0001\u0000\r\u000fB\u0000\n\u0001"+
		"\u0000\u0000\u0000\u0002\'\u0001\u0000\u0000\u0000\u0004-\u0001\u0000"+
		"\u0000\u0000\u00069\u0001\u0000\u0000\u0000\b;\u0001\u0000\u0000\u0000"+
		"\n\u0013\u0003\u0002\u0001\u0000\u000b\r\u0005\u0012\u0000\u0000\f\u000b"+
		"\u0001\u0000\u0000\u0000\r\u000e\u0001\u0000\u0000\u0000\u000e\f\u0001"+
		"\u0000\u0000\u0000\u000e\u000f\u0001\u0000\u0000\u0000\u000f\u0010\u0001"+
		"\u0000\u0000\u0000\u0010\u0012\u0003\u0002\u0001\u0000\u0011\f\u0001\u0000"+
		"\u0000\u0000\u0012\u0015\u0001\u0000\u0000\u0000\u0013\u0011\u0001\u0000"+
		"\u0000\u0000\u0013\u0014\u0001\u0000\u0000\u0000\u0014\u0019\u0001\u0000"+
		"\u0000\u0000\u0015\u0013\u0001\u0000\u0000\u0000\u0016\u0018\u0005\u0012"+
		"\u0000\u0000\u0017\u0016\u0001\u0000\u0000\u0000\u0018\u001b\u0001\u0000"+
		"\u0000\u0000\u0019\u0017\u0001\u0000\u0000\u0000\u0019\u001a\u0001\u0000"+
		"\u0000\u0000\u001a\u001c\u0001\u0000\u0000\u0000\u001b\u0019\u0001\u0000"+
		"\u0000\u0000\u001c\u001d\u0005\u0000\u0000\u0001\u001d\u0001\u0001\u0000"+
		"\u0000\u0000\u001e#\u0005\u0011\u0000\u0000\u001f \u0005\u0001\u0000\u0000"+
		" \"\u0005\u0011\u0000\u0000!\u001f\u0001\u0000\u0000\u0000\"%\u0001\u0000"+
		"\u0000\u0000#!\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$&\u0001"+
		"\u0000\u0000\u0000%#\u0001\u0000\u0000\u0000&(\u0005\u0002\u0000\u0000"+
		"\'\u001e\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000()\u0001\u0000"+
		"\u0000\u0000)*\u0003\u0004\u0002\u0000*\u0003\u0001\u0000\u0000\u0000"+
		"+.\u0003\u0006\u0003\u0000,.\u0003\b\u0004\u0000-+\u0001\u0000\u0000\u0000"+
		"-,\u0001\u0000\u0000\u0000.\u0005\u0001\u0000\u0000\u0000/0\u0005\u0007"+
		"\u0000\u00000:\u0005\u0003\u0000\u000012\u0005\b\u0000\u00002:\u0007\u0000"+
		"\u0000\u000034\u0005\t\u0000\u00004:\u0005\u0005\u0000\u000056\u0007\u0001"+
		"\u0000\u00006:\u0005\u0011\u0000\u000078\u0007\u0002\u0000\u00008:\u0005"+
		"\u0003\u0000\u00009/\u0001\u0000\u0000\u000091\u0001\u0000\u0000\u0000"+
		"93\u0001\u0000\u0000\u000095\u0001\u0000\u0000\u000097\u0001\u0000\u0000"+
		"\u0000:\u0007\u0001\u0000\u0000\u0000;<\u0005\u0010\u0000\u0000<\t\u0001"+
		"\u0000\u0000\u0000\u0007\u000e\u0013\u0019#\'-9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}