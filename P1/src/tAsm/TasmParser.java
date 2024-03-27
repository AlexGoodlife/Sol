// Generated from C:/Users/Acer/Desktop/Programations/cop_projeto/P1/src/Tasm.g4 by ANTLR 4.13.1
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
		T__0=1, INT=2, DOUBLE=3, STRING=4, BOOL=5, NULL=6, LOAD_INT=7, LOAD_DOUBLE=8, 
		LOAD_STRING=9, LOAD_BOOL=10, UJUMP=11, CJUMP_TRUE=12, CJUMP_FALSE=13, 
		GLOBAL_ALLOC=14, GLOBAL_LOAD=15, GLOBAL_STORE=16, SIMPLE_INSTRUCTION=17, 
		LABEL=18, EOL=19, WS=20, LETTER=21;
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
			null, "':'", null, null, null, null, "'NIL'", "'iconst'", "'dconst'", 
			"'sconst'", "'bconst'", "'jump'", "'jumpt'", "'jumpf'", "'galloc'", "'gload'", 
			"'gstore'", null, null, "'\\n'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "INT", "DOUBLE", "STRING", "BOOL", "NULL", "LOAD_INT", "LOAD_DOUBLE", 
			"LOAD_STRING", "LOAD_BOOL", "UJUMP", "CJUMP_TRUE", "CJUMP_FALSE", "GLOBAL_ALLOC", 
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
		public TerminalNode EOF() { return getToken(TasmParser.EOF, 0); }
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(11); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(10);
				line();
				}
				}
				setState(13); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1048448L) != 0) );
			setState(15);
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
		public TerminalNode EOL() { return getToken(TasmParser.EOL, 0); }
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public TerminalNode LABEL() { return getToken(TasmParser.LABEL, 0); }
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
			setState(22);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 524160L) != 0)) {
				{
				setState(19);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LABEL) {
					{
					setState(17);
					match(LABEL);
					setState(18);
					match(T__0);
					}
				}

				setState(21);
				instruction();
				}
			}

			setState(24);
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
			setState(28);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOAD_INT:
			case LOAD_DOUBLE:
			case LOAD_STRING:
			case LOAD_BOOL:
			case UJUMP:
			case CJUMP_TRUE:
			case CJUMP_FALSE:
			case GLOBAL_ALLOC:
			case GLOBAL_LOAD:
			case GLOBAL_STORE:
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				instructionWithArguments();
				}
				break;
			case SIMPLE_INSTRUCTION:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
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
	public static class LoadBoolContext extends InstructionWithArgumentsContext {
		public TerminalNode LOAD_BOOL() { return getToken(TasmParser.LOAD_BOOL, 0); }
		public TerminalNode BOOL() { return getToken(TasmParser.BOOL, 0); }
		public LoadBoolContext(InstructionWithArgumentsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterLoadBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitLoadBool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TasmVisitor ) return ((TasmVisitor<? extends T>)visitor).visitLoadBool(this);
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
			setState(42);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOAD_INT:
				_localctx = new LoadIntContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				match(LOAD_INT);
				setState(31);
				match(INT);
				}
				break;
			case LOAD_DOUBLE:
				_localctx = new LoadDoubleContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
				match(LOAD_DOUBLE);
				setState(33);
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
				setState(34);
				match(LOAD_STRING);
				setState(35);
				match(STRING);
				}
				break;
			case LOAD_BOOL:
				_localctx = new LoadBoolContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(36);
				match(LOAD_BOOL);
				setState(37);
				match(BOOL);
				}
				break;
			case UJUMP:
			case CJUMP_TRUE:
			case CJUMP_FALSE:
				_localctx = new JumpContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(38);
				((JumpContext)_localctx).jump = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14336L) != 0)) ) {
					((JumpContext)_localctx).jump = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(39);
				match(LABEL);
				}
				break;
			case GLOBAL_ALLOC:
			case GLOBAL_LOAD:
			case GLOBAL_STORE:
				_localctx = new GlobalContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(40);
				((GlobalContext)_localctx).global = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 114688L) != 0)) ) {
					((GlobalContext)_localctx).global = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(41);
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
			setState(44);
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
		"\u0004\u0001\u0015/\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001"+
		"\u0000\u0004\u0000\f\b\u0000\u000b\u0000\f\u0000\r\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0003\u0001\u0014\b\u0001\u0001\u0001\u0003\u0001"+
		"\u0017\b\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0003\u0002"+
		"\u001d\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003+\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0000\u0000\u0005\u0000\u0002\u0004\u0006\b\u0000\u0003\u0001\u0000\u0002"+
		"\u0003\u0001\u0000\u000b\r\u0001\u0000\u000e\u00102\u0000\u000b\u0001"+
		"\u0000\u0000\u0000\u0002\u0016\u0001\u0000\u0000\u0000\u0004\u001c\u0001"+
		"\u0000\u0000\u0000\u0006*\u0001\u0000\u0000\u0000\b,\u0001\u0000\u0000"+
		"\u0000\n\f\u0003\u0002\u0001\u0000\u000b\n\u0001\u0000\u0000\u0000\f\r"+
		"\u0001\u0000\u0000\u0000\r\u000b\u0001\u0000\u0000\u0000\r\u000e\u0001"+
		"\u0000\u0000\u0000\u000e\u000f\u0001\u0000\u0000\u0000\u000f\u0010\u0005"+
		"\u0000\u0000\u0001\u0010\u0001\u0001\u0000\u0000\u0000\u0011\u0012\u0005"+
		"\u0012\u0000\u0000\u0012\u0014\u0005\u0001\u0000\u0000\u0013\u0011\u0001"+
		"\u0000\u0000\u0000\u0013\u0014\u0001\u0000\u0000\u0000\u0014\u0015\u0001"+
		"\u0000\u0000\u0000\u0015\u0017\u0003\u0004\u0002\u0000\u0016\u0013\u0001"+
		"\u0000\u0000\u0000\u0016\u0017\u0001\u0000\u0000\u0000\u0017\u0018\u0001"+
		"\u0000\u0000\u0000\u0018\u0019\u0005\u0013\u0000\u0000\u0019\u0003\u0001"+
		"\u0000\u0000\u0000\u001a\u001d\u0003\u0006\u0003\u0000\u001b\u001d\u0003"+
		"\b\u0004\u0000\u001c\u001a\u0001\u0000\u0000\u0000\u001c\u001b\u0001\u0000"+
		"\u0000\u0000\u001d\u0005\u0001\u0000\u0000\u0000\u001e\u001f\u0005\u0007"+
		"\u0000\u0000\u001f+\u0005\u0002\u0000\u0000 !\u0005\b\u0000\u0000!+\u0007"+
		"\u0000\u0000\u0000\"#\u0005\t\u0000\u0000#+\u0005\u0004\u0000\u0000$%"+
		"\u0005\n\u0000\u0000%+\u0005\u0005\u0000\u0000&\'\u0007\u0001\u0000\u0000"+
		"\'+\u0005\u0012\u0000\u0000()\u0007\u0002\u0000\u0000)+\u0005\u0002\u0000"+
		"\u0000*\u001e\u0001\u0000\u0000\u0000* \u0001\u0000\u0000\u0000*\"\u0001"+
		"\u0000\u0000\u0000*$\u0001\u0000\u0000\u0000*&\u0001\u0000\u0000\u0000"+
		"*(\u0001\u0000\u0000\u0000+\u0007\u0001\u0000\u0000\u0000,-\u0005\u0011"+
		"\u0000\u0000-\t\u0001\u0000\u0000\u0000\u0005\r\u0013\u0016\u001c*";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}