// Generated from /home/compl3x/Programations/cop_projeto/P3/src/Tasm.g4 by ANTLR 4.13.1
package antlrTasm;
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
		T__0=1, T__1=2, INT=3, DOUBLE=4, STRING=5, LOAD_INT=6, LOAD_DOUBLE=7, 
		LOAD_STRING=8, UJUMP=9, CJUMP_TRUE=10, CJUMP_FALSE=11, GLOBAL_ALLOC=12, 
		GLOBAL_LOAD=13, GLOBAL_STORE=14, LOCAL_ALLOC=15, LOCAL_LOAD=16, LOCAL_STORE=17, 
		POP=18, CALL=19, RETURN_VALUE=20, RETURN=21, SIMPLE_INSTRUCTION=22, LABEL=23, 
		EOL=24, WS=25, LETTER=26;
	public static final int
		RULE_program = 0, RULE_line = 1, RULE_instruction = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "line", "instruction"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "':'", null, null, null, "'iconst'", "'dconst'", "'sconst'", 
			"'jump'", "'jumpt'", "'jumpf'", "'galloc'", "'gload'", "'gstore'", "'lalloc'", 
			"'lload'", "'lstore'", "'pop'", "'call'", "'retval'", "'ret'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "INT", "DOUBLE", "STRING", "LOAD_INT", "LOAD_DOUBLE", 
			"LOAD_STRING", "UJUMP", "CJUMP_TRUE", "CJUMP_FALSE", "GLOBAL_ALLOC", 
			"GLOBAL_LOAD", "GLOBAL_STORE", "LOCAL_ALLOC", "LOCAL_LOAD", "LOCAL_STORE", 
			"POP", "CALL", "RETURN_VALUE", "RETURN", "SIMPLE_INSTRUCTION", "LABEL", 
			"EOL", "WS", "LETTER"
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
			setState(6);
			line();
			setState(15);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(8); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(7);
						match(EOL);
						}
						}
						setState(10); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==EOL );
					setState(12);
					line();
					}
					} 
				}
				setState(17);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(21);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(18);
				match(EOL);
				}
				}
				setState(23);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(24);
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
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LABEL) {
				{
				setState(26);
				match(LABEL);
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(27);
					match(T__0);
					setState(28);
					match(LABEL);
					}
					}
					setState(33);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(34);
				match(T__1);
				}
			}

			setState(37);
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
	public static class LoadIntContext extends InstructionContext {
		public TerminalNode LOAD_INT() { return getToken(TasmParser.LOAD_INT, 0); }
		public TerminalNode INT() { return getToken(TasmParser.INT, 0); }
		public LoadIntContext(InstructionContext ctx) { copyFrom(ctx); }
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
	public static class LoadDoubleContext extends InstructionContext {
		public Token value;
		public TerminalNode LOAD_DOUBLE() { return getToken(TasmParser.LOAD_DOUBLE, 0); }
		public TerminalNode DOUBLE() { return getToken(TasmParser.DOUBLE, 0); }
		public TerminalNode INT() { return getToken(TasmParser.INT, 0); }
		public LoadDoubleContext(InstructionContext ctx) { copyFrom(ctx); }
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
	public static class LoadStringContext extends InstructionContext {
		public TerminalNode LOAD_STRING() { return getToken(TasmParser.LOAD_STRING, 0); }
		public TerminalNode STRING() { return getToken(TasmParser.STRING, 0); }
		public LoadStringContext(InstructionContext ctx) { copyFrom(ctx); }
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
	public static class ReturnContext extends InstructionContext {
		public Token return_;
		public TerminalNode INT() { return getToken(TasmParser.INT, 0); }
		public TerminalNode RETURN_VALUE() { return getToken(TasmParser.RETURN_VALUE, 0); }
		public TerminalNode RETURN() { return getToken(TasmParser.RETURN, 0); }
		public ReturnContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TasmVisitor ) return ((TasmVisitor<? extends T>)visitor).visitReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LocalContext extends InstructionContext {
		public Token local;
		public TerminalNode INT() { return getToken(TasmParser.INT, 0); }
		public TerminalNode LOCAL_ALLOC() { return getToken(TasmParser.LOCAL_ALLOC, 0); }
		public TerminalNode LOCAL_LOAD() { return getToken(TasmParser.LOCAL_LOAD, 0); }
		public TerminalNode LOCAL_STORE() { return getToken(TasmParser.LOCAL_STORE, 0); }
		public TerminalNode POP() { return getToken(TasmParser.POP, 0); }
		public LocalContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterLocal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitLocal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TasmVisitor ) return ((TasmVisitor<? extends T>)visitor).visitLocal(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class JumpContext extends InstructionContext {
		public Token jump;
		public TerminalNode LABEL() { return getToken(TasmParser.LABEL, 0); }
		public TerminalNode UJUMP() { return getToken(TasmParser.UJUMP, 0); }
		public TerminalNode CJUMP_TRUE() { return getToken(TasmParser.CJUMP_TRUE, 0); }
		public TerminalNode CJUMP_FALSE() { return getToken(TasmParser.CJUMP_FALSE, 0); }
		public TerminalNode CALL() { return getToken(TasmParser.CALL, 0); }
		public JumpContext(InstructionContext ctx) { copyFrom(ctx); }
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
	public static class GlobalContext extends InstructionContext {
		public Token global;
		public TerminalNode INT() { return getToken(TasmParser.INT, 0); }
		public TerminalNode GLOBAL_ALLOC() { return getToken(TasmParser.GLOBAL_ALLOC, 0); }
		public TerminalNode GLOBAL_LOAD() { return getToken(TasmParser.GLOBAL_LOAD, 0); }
		public TerminalNode GLOBAL_STORE() { return getToken(TasmParser.GLOBAL_STORE, 0); }
		public GlobalContext(InstructionContext ctx) { copyFrom(ctx); }
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
	@SuppressWarnings("CheckReturnValue")
	public static class SimpleContext extends InstructionContext {
		public TerminalNode SIMPLE_INSTRUCTION() { return getToken(TasmParser.SIMPLE_INSTRUCTION, 0); }
		public SimpleContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterSimple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitSimple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TasmVisitor ) return ((TasmVisitor<? extends T>)visitor).visitSimple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_instruction);
		int _la;
		try {
			setState(54);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOAD_INT:
				_localctx = new LoadIntContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(39);
				match(LOAD_INT);
				setState(40);
				match(INT);
				}
				break;
			case LOAD_DOUBLE:
				_localctx = new LoadDoubleContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(41);
				match(LOAD_DOUBLE);
				setState(42);
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
				setState(43);
				match(LOAD_STRING);
				setState(44);
				match(STRING);
				}
				break;
			case UJUMP:
			case CJUMP_TRUE:
			case CJUMP_FALSE:
			case CALL:
				_localctx = new JumpContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(45);
				((JumpContext)_localctx).jump = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 527872L) != 0)) ) {
					((JumpContext)_localctx).jump = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(46);
				match(LABEL);
				}
				break;
			case GLOBAL_ALLOC:
			case GLOBAL_LOAD:
			case GLOBAL_STORE:
				_localctx = new GlobalContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(47);
				((GlobalContext)_localctx).global = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 28672L) != 0)) ) {
					((GlobalContext)_localctx).global = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(48);
				match(INT);
				}
				break;
			case LOCAL_ALLOC:
			case LOCAL_LOAD:
			case LOCAL_STORE:
			case POP:
				_localctx = new LocalContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(49);
				((LocalContext)_localctx).local = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 491520L) != 0)) ) {
					((LocalContext)_localctx).local = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(50);
				match(INT);
				}
				break;
			case RETURN_VALUE:
			case RETURN:
				_localctx = new ReturnContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(51);
				((ReturnContext)_localctx).return_ = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==RETURN_VALUE || _la==RETURN) ) {
					((ReturnContext)_localctx).return_ = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(52);
				match(INT);
				}
				break;
			case SIMPLE_INSTRUCTION:
				_localctx = new SimpleContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(53);
				match(SIMPLE_INSTRUCTION);
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

	public static final String _serializedATN =
		"\u0004\u0001\u001a9\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0001\u0000\u0001\u0000\u0004\u0000\t\b\u0000\u000b"+
		"\u0000\f\u0000\n\u0001\u0000\u0005\u0000\u000e\b\u0000\n\u0000\f\u0000"+
		"\u0011\t\u0000\u0001\u0000\u0005\u0000\u0014\b\u0000\n\u0000\f\u0000\u0017"+
		"\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0005"+
		"\u0001\u001e\b\u0001\n\u0001\f\u0001!\t\u0001\u0001\u0001\u0003\u0001"+
		"$\b\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u00027\b\u0002\u0001\u0002\u0000\u0000\u0003\u0000\u0002\u0004"+
		"\u0000\u0005\u0001\u0000\u0003\u0004\u0002\u0000\t\u000b\u0013\u0013\u0001"+
		"\u0000\f\u000e\u0001\u0000\u000f\u0012\u0001\u0000\u0014\u0015A\u0000"+
		"\u0006\u0001\u0000\u0000\u0000\u0002#\u0001\u0000\u0000\u0000\u00046\u0001"+
		"\u0000\u0000\u0000\u0006\u000f\u0003\u0002\u0001\u0000\u0007\t\u0005\u0018"+
		"\u0000\u0000\b\u0007\u0001\u0000\u0000\u0000\t\n\u0001\u0000\u0000\u0000"+
		"\n\b\u0001\u0000\u0000\u0000\n\u000b\u0001\u0000\u0000\u0000\u000b\f\u0001"+
		"\u0000\u0000\u0000\f\u000e\u0003\u0002\u0001\u0000\r\b\u0001\u0000\u0000"+
		"\u0000\u000e\u0011\u0001\u0000\u0000\u0000\u000f\r\u0001\u0000\u0000\u0000"+
		"\u000f\u0010\u0001\u0000\u0000\u0000\u0010\u0015\u0001\u0000\u0000\u0000"+
		"\u0011\u000f\u0001\u0000\u0000\u0000\u0012\u0014\u0005\u0018\u0000\u0000"+
		"\u0013\u0012\u0001\u0000\u0000\u0000\u0014\u0017\u0001\u0000\u0000\u0000"+
		"\u0015\u0013\u0001\u0000\u0000\u0000\u0015\u0016\u0001\u0000\u0000\u0000"+
		"\u0016\u0018\u0001\u0000\u0000\u0000\u0017\u0015\u0001\u0000\u0000\u0000"+
		"\u0018\u0019\u0005\u0000\u0000\u0001\u0019\u0001\u0001\u0000\u0000\u0000"+
		"\u001a\u001f\u0005\u0017\u0000\u0000\u001b\u001c\u0005\u0001\u0000\u0000"+
		"\u001c\u001e\u0005\u0017\u0000\u0000\u001d\u001b\u0001\u0000\u0000\u0000"+
		"\u001e!\u0001\u0000\u0000\u0000\u001f\u001d\u0001\u0000\u0000\u0000\u001f"+
		" \u0001\u0000\u0000\u0000 \"\u0001\u0000\u0000\u0000!\u001f\u0001\u0000"+
		"\u0000\u0000\"$\u0005\u0002\u0000\u0000#\u001a\u0001\u0000\u0000\u0000"+
		"#$\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000\u0000%&\u0003\u0004\u0002"+
		"\u0000&\u0003\u0001\u0000\u0000\u0000\'(\u0005\u0006\u0000\u0000(7\u0005"+
		"\u0003\u0000\u0000)*\u0005\u0007\u0000\u0000*7\u0007\u0000\u0000\u0000"+
		"+,\u0005\b\u0000\u0000,7\u0005\u0005\u0000\u0000-.\u0007\u0001\u0000\u0000"+
		".7\u0005\u0017\u0000\u0000/0\u0007\u0002\u0000\u000007\u0005\u0003\u0000"+
		"\u000012\u0007\u0003\u0000\u000027\u0005\u0003\u0000\u000034\u0007\u0004"+
		"\u0000\u000047\u0005\u0003\u0000\u000057\u0005\u0016\u0000\u00006\'\u0001"+
		"\u0000\u0000\u00006)\u0001\u0000\u0000\u00006+\u0001\u0000\u0000\u0000"+
		"6-\u0001\u0000\u0000\u00006/\u0001\u0000\u0000\u000061\u0001\u0000\u0000"+
		"\u000063\u0001\u0000\u0000\u000065\u0001\u0000\u0000\u00007\u0005\u0001"+
		"\u0000\u0000\u0000\u0006\n\u000f\u0015\u001f#6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}