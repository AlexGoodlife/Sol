// Generated from C:/Users/Acer/Desktop/Programations/cop_projeto/P1/src/Tasm.g4 by ANTLR 4.13.1
package antlrTasm;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class TasmLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, INT=3, DOUBLE=4, STRING=5, LOAD_INT=6, LOAD_DOUBLE=7, 
		LOAD_STRING=8, UJUMP=9, CJUMP_TRUE=10, CJUMP_FALSE=11, GLOBAL_ALLOC=12, 
		GLOBAL_LOAD=13, GLOBAL_STORE=14, SIMPLE_INSTRUCTION=15, LABEL=16, EOL=17, 
		WS=18, LETTER=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "INT", "DOUBLE", "STRING", "LOAD_INT", "LOAD_DOUBLE", 
			"LOAD_STRING", "UJUMP", "CJUMP_TRUE", "CJUMP_FALSE", "GLOBAL_ALLOC", 
			"GLOBAL_LOAD", "GLOBAL_STORE", "SIMPLE_INSTRUCTION", "LABEL", "EOL", 
			"WS", "DIGIT", "LETTER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "':'", null, null, null, "'iconst'", "'dconst'", "'sconst'", 
			"'jump'", "'jumpt'", "'jumpf'", "'galloc'", "'gload'", "'gstore'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "INT", "DOUBLE", "STRING", "LOAD_INT", "LOAD_DOUBLE", 
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


	public TasmLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Tasm.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0013\u0146\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0003\u0002/\b\u0002\u0001\u0002"+
		"\u0004\u00022\b\u0002\u000b\u0002\f\u00023\u0001\u0003\u0003\u00037\b"+
		"\u0003\u0001\u0003\u0004\u0003:\b\u0003\u000b\u0003\f\u0003;\u0001\u0003"+
		"\u0001\u0003\u0004\u0003@\b\u0003\u000b\u0003\f\u0003A\u0001\u0004\u0001"+
		"\u0004\u0005\u0004F\b\u0004\n\u0004\f\u0004I\t\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0003\u000e\u0129\b\u000e\u0001\u000f\u0001\u000f\u0003\u000f"+
		"\u012d\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u0132\b"+
		"\u000f\n\u000f\f\u000f\u0135\t\u000f\u0001\u0010\u0003\u0010\u0138\b\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0011\u0004\u0011\u013d\b\u0011\u000b\u0011"+
		"\f\u0011\u013e\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013"+
		"\u0001\u0013\u0001G\u0000\u0014\u0001\u0001\u0003\u0002\u0005\u0003\u0007"+
		"\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b"+
		"\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0000"+
		"\'\u0013\u0001\u0000\u0003\u0003\u0000\t\t\r\r  \u0001\u000009\u0002\u0000"+
		"AZaz\u0175\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000"+
		"\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000"+
		"\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000\'"+
		"\u0001\u0000\u0000\u0000\u0001)\u0001\u0000\u0000\u0000\u0003+\u0001\u0000"+
		"\u0000\u0000\u0005.\u0001\u0000\u0000\u0000\u00076\u0001\u0000\u0000\u0000"+
		"\tC\u0001\u0000\u0000\u0000\u000bL\u0001\u0000\u0000\u0000\rS\u0001\u0000"+
		"\u0000\u0000\u000fZ\u0001\u0000\u0000\u0000\u0011a\u0001\u0000\u0000\u0000"+
		"\u0013f\u0001\u0000\u0000\u0000\u0015l\u0001\u0000\u0000\u0000\u0017r"+
		"\u0001\u0000\u0000\u0000\u0019y\u0001\u0000\u0000\u0000\u001b\u007f\u0001"+
		"\u0000\u0000\u0000\u001d\u0128\u0001\u0000\u0000\u0000\u001f\u012c\u0001"+
		"\u0000\u0000\u0000!\u0137\u0001\u0000\u0000\u0000#\u013c\u0001\u0000\u0000"+
		"\u0000%\u0142\u0001\u0000\u0000\u0000\'\u0144\u0001\u0000\u0000\u0000"+
		")*\u0005,\u0000\u0000*\u0002\u0001\u0000\u0000\u0000+,\u0005:\u0000\u0000"+
		",\u0004\u0001\u0000\u0000\u0000-/\u0005-\u0000\u0000.-\u0001\u0000\u0000"+
		"\u0000./\u0001\u0000\u0000\u0000/1\u0001\u0000\u0000\u000002\u0003%\u0012"+
		"\u000010\u0001\u0000\u0000\u000023\u0001\u0000\u0000\u000031\u0001\u0000"+
		"\u0000\u000034\u0001\u0000\u0000\u00004\u0006\u0001\u0000\u0000\u0000"+
		"57\u0005-\u0000\u000065\u0001\u0000\u0000\u000067\u0001\u0000\u0000\u0000"+
		"79\u0001\u0000\u0000\u00008:\u0003%\u0012\u000098\u0001\u0000\u0000\u0000"+
		":;\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000"+
		"\u0000<=\u0001\u0000\u0000\u0000=?\u0005.\u0000\u0000>@\u0003%\u0012\u0000"+
		"?>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000A?\u0001\u0000\u0000"+
		"\u0000AB\u0001\u0000\u0000\u0000B\b\u0001\u0000\u0000\u0000CG\u0005\""+
		"\u0000\u0000DF\t\u0000\u0000\u0000ED\u0001\u0000\u0000\u0000FI\u0001\u0000"+
		"\u0000\u0000GH\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000HJ\u0001"+
		"\u0000\u0000\u0000IG\u0001\u0000\u0000\u0000JK\u0005\"\u0000\u0000K\n"+
		"\u0001\u0000\u0000\u0000LM\u0005i\u0000\u0000MN\u0005c\u0000\u0000NO\u0005"+
		"o\u0000\u0000OP\u0005n\u0000\u0000PQ\u0005s\u0000\u0000QR\u0005t\u0000"+
		"\u0000R\f\u0001\u0000\u0000\u0000ST\u0005d\u0000\u0000TU\u0005c\u0000"+
		"\u0000UV\u0005o\u0000\u0000VW\u0005n\u0000\u0000WX\u0005s\u0000\u0000"+
		"XY\u0005t\u0000\u0000Y\u000e\u0001\u0000\u0000\u0000Z[\u0005s\u0000\u0000"+
		"[\\\u0005c\u0000\u0000\\]\u0005o\u0000\u0000]^\u0005n\u0000\u0000^_\u0005"+
		"s\u0000\u0000_`\u0005t\u0000\u0000`\u0010\u0001\u0000\u0000\u0000ab\u0005"+
		"j\u0000\u0000bc\u0005u\u0000\u0000cd\u0005m\u0000\u0000de\u0005p\u0000"+
		"\u0000e\u0012\u0001\u0000\u0000\u0000fg\u0005j\u0000\u0000gh\u0005u\u0000"+
		"\u0000hi\u0005m\u0000\u0000ij\u0005p\u0000\u0000jk\u0005t\u0000\u0000"+
		"k\u0014\u0001\u0000\u0000\u0000lm\u0005j\u0000\u0000mn\u0005u\u0000\u0000"+
		"no\u0005m\u0000\u0000op\u0005p\u0000\u0000pq\u0005f\u0000\u0000q\u0016"+
		"\u0001\u0000\u0000\u0000rs\u0005g\u0000\u0000st\u0005a\u0000\u0000tu\u0005"+
		"l\u0000\u0000uv\u0005l\u0000\u0000vw\u0005o\u0000\u0000wx\u0005c\u0000"+
		"\u0000x\u0018\u0001\u0000\u0000\u0000yz\u0005g\u0000\u0000z{\u0005l\u0000"+
		"\u0000{|\u0005o\u0000\u0000|}\u0005a\u0000\u0000}~\u0005d\u0000\u0000"+
		"~\u001a\u0001\u0000\u0000\u0000\u007f\u0080\u0005g\u0000\u0000\u0080\u0081"+
		"\u0005s\u0000\u0000\u0081\u0082\u0005t\u0000\u0000\u0082\u0083\u0005o"+
		"\u0000\u0000\u0083\u0084\u0005r\u0000\u0000\u0084\u0085\u0005e\u0000\u0000"+
		"\u0085\u001c\u0001\u0000\u0000\u0000\u0086\u0087\u0005i\u0000\u0000\u0087"+
		"\u0088\u0005p\u0000\u0000\u0088\u0089\u0005r\u0000\u0000\u0089\u008a\u0005"+
		"i\u0000\u0000\u008a\u008b\u0005n\u0000\u0000\u008b\u0129\u0005t\u0000"+
		"\u0000\u008c\u008d\u0005i\u0000\u0000\u008d\u008e\u0005u\u0000\u0000\u008e"+
		"\u008f\u0005m\u0000\u0000\u008f\u0090\u0005i\u0000\u0000\u0090\u0091\u0005"+
		"n\u0000\u0000\u0091\u0092\u0005u\u0000\u0000\u0092\u0129\u0005s\u0000"+
		"\u0000\u0093\u0094\u0005i\u0000\u0000\u0094\u0095\u0005a\u0000\u0000\u0095"+
		"\u0096\u0005d\u0000\u0000\u0096\u0129\u0005d\u0000\u0000\u0097\u0098\u0005"+
		"i\u0000\u0000\u0098\u0099\u0005s\u0000\u0000\u0099\u009a\u0005u\u0000"+
		"\u0000\u009a\u0129\u0005b\u0000\u0000\u009b\u009c\u0005i\u0000\u0000\u009c"+
		"\u009d\u0005m\u0000\u0000\u009d\u009e\u0005u\u0000\u0000\u009e\u009f\u0005"+
		"l\u0000\u0000\u009f\u0129\u0005t\u0000\u0000\u00a0\u00a1\u0005i\u0000"+
		"\u0000\u00a1\u00a2\u0005d\u0000\u0000\u00a2\u00a3\u0005i\u0000\u0000\u00a3"+
		"\u0129\u0005v\u0000\u0000\u00a4\u00a5\u0005i\u0000\u0000\u00a5\u00a6\u0005"+
		"m\u0000\u0000\u00a6\u00a7\u0005o\u0000\u0000\u00a7\u0129\u0005d\u0000"+
		"\u0000\u00a8\u00a9\u0005i\u0000\u0000\u00a9\u00aa\u0005e\u0000\u0000\u00aa"+
		"\u0129\u0005q\u0000\u0000\u00ab\u00ac\u0005i\u0000\u0000\u00ac\u00ad\u0005"+
		"n\u0000\u0000\u00ad\u00ae\u0005e\u0000\u0000\u00ae\u0129\u0005q\u0000"+
		"\u0000\u00af\u00b0\u0005i\u0000\u0000\u00b0\u00b1\u0005l\u0000\u0000\u00b1"+
		"\u0129\u0005t\u0000\u0000\u00b2\u00b3\u0005i\u0000\u0000\u00b3\u00b4\u0005"+
		"l\u0000\u0000\u00b4\u00b5\u0005e\u0000\u0000\u00b5\u0129\u0005q\u0000"+
		"\u0000\u00b6\u00b7\u0005i\u0000\u0000\u00b7\u00b8\u0005t\u0000\u0000\u00b8"+
		"\u00b9\u0005o\u0000\u0000\u00b9\u0129\u0005d\u0000\u0000\u00ba\u00bb\u0005"+
		"i\u0000\u0000\u00bb\u00bc\u0005t\u0000\u0000\u00bc\u00bd\u0005o\u0000"+
		"\u0000\u00bd\u0129\u0005s\u0000\u0000\u00be\u00bf\u0005d\u0000\u0000\u00bf"+
		"\u00c0\u0005p\u0000\u0000\u00c0\u00c1\u0005r\u0000\u0000\u00c1\u00c2\u0005"+
		"i\u0000\u0000\u00c2\u00c3\u0005n\u0000\u0000\u00c3\u0129\u0005t\u0000"+
		"\u0000\u00c4\u00c5\u0005d\u0000\u0000\u00c5\u00c6\u0005u\u0000\u0000\u00c6"+
		"\u00c7\u0005m\u0000\u0000\u00c7\u00c8\u0005i\u0000\u0000\u00c8\u00c9\u0005"+
		"n\u0000\u0000\u00c9\u00ca\u0005u\u0000\u0000\u00ca\u0129\u0005s\u0000"+
		"\u0000\u00cb\u00cc\u0005d\u0000\u0000\u00cc\u00cd\u0005a\u0000\u0000\u00cd"+
		"\u00ce\u0005d\u0000\u0000\u00ce\u0129\u0005d\u0000\u0000\u00cf\u00d0\u0005"+
		"d\u0000\u0000\u00d0\u00d1\u0005s\u0000\u0000\u00d1\u00d2\u0005u\u0000"+
		"\u0000\u00d2\u0129\u0005b\u0000\u0000\u00d3\u00d4\u0005d\u0000\u0000\u00d4"+
		"\u00d5\u0005m\u0000\u0000\u00d5\u00d6\u0005u\u0000\u0000\u00d6\u00d7\u0005"+
		"l\u0000\u0000\u00d7\u0129\u0005t\u0000\u0000\u00d8\u00d9\u0005d\u0000"+
		"\u0000\u00d9\u00da\u0005d\u0000\u0000\u00da\u00db\u0005i\u0000\u0000\u00db"+
		"\u0129\u0005v\u0000\u0000\u00dc\u00dd\u0005d\u0000\u0000\u00dd\u00de\u0005"+
		"e\u0000\u0000\u00de\u0129\u0005q\u0000\u0000\u00df\u00e0\u0005d\u0000"+
		"\u0000\u00e0\u00e1\u0005n\u0000\u0000\u00e1\u00e2\u0005e\u0000\u0000\u00e2"+
		"\u0129\u0005q\u0000\u0000\u00e3\u00e4\u0005d\u0000\u0000\u00e4\u00e5\u0005"+
		"l\u0000\u0000\u00e5\u0129\u0005t\u0000\u0000\u00e6\u00e7\u0005d\u0000"+
		"\u0000\u00e7\u00e8\u0005l\u0000\u0000\u00e8\u00e9\u0005e\u0000\u0000\u00e9"+
		"\u0129\u0005q\u0000\u0000\u00ea\u00eb\u0005d\u0000\u0000\u00eb\u00ec\u0005"+
		"t\u0000\u0000\u00ec\u00ed\u0005o\u0000\u0000\u00ed\u0129\u0005s\u0000"+
		"\u0000\u00ee\u00ef\u0005s\u0000\u0000\u00ef\u00f0\u0005p\u0000\u0000\u00f0"+
		"\u00f1\u0005r\u0000\u0000\u00f1\u00f2\u0005i\u0000\u0000\u00f2\u00f3\u0005"+
		"n\u0000\u0000\u00f3\u0129\u0005t\u0000\u0000\u00f4\u00f5\u0005s\u0000"+
		"\u0000\u00f5\u00f6\u0005a\u0000\u0000\u00f6\u00f7\u0005d\u0000\u0000\u00f7"+
		"\u0129\u0005d\u0000\u0000\u00f8\u00f9\u0005s\u0000\u0000\u00f9\u00fa\u0005"+
		"e\u0000\u0000\u00fa\u0129\u0005q\u0000\u0000\u00fb\u00fc\u0005s\u0000"+
		"\u0000\u00fc\u00fd\u0005n\u0000\u0000\u00fd\u00fe\u0005e\u0000\u0000\u00fe"+
		"\u0129\u0005q\u0000\u0000\u00ff\u0100\u0005t\u0000\u0000\u0100\u0101\u0005"+
		"c\u0000\u0000\u0101\u0102\u0005o\u0000\u0000\u0102\u0103\u0005n\u0000"+
		"\u0000\u0103\u0104\u0005s\u0000\u0000\u0104\u0129\u0005t\u0000\u0000\u0105"+
		"\u0106\u0005f\u0000\u0000\u0106\u0107\u0005c\u0000\u0000\u0107\u0108\u0005"+
		"o\u0000\u0000\u0108\u0109\u0005n\u0000\u0000\u0109\u010a\u0005s\u0000"+
		"\u0000\u010a\u0129\u0005t\u0000\u0000\u010b\u010c\u0005b\u0000\u0000\u010c"+
		"\u010d\u0005p\u0000\u0000\u010d\u010e\u0005r\u0000\u0000\u010e\u010f\u0005"+
		"i\u0000\u0000\u010f\u0110\u0005n\u0000\u0000\u0110\u0129\u0005t\u0000"+
		"\u0000\u0111\u0112\u0005b\u0000\u0000\u0112\u0113\u0005e\u0000\u0000\u0113"+
		"\u0129\u0005q\u0000\u0000\u0114\u0115\u0005b\u0000\u0000\u0115\u0116\u0005"+
		"n\u0000\u0000\u0116\u0117\u0005e\u0000\u0000\u0117\u0129\u0005q\u0000"+
		"\u0000\u0118\u0119\u0005a\u0000\u0000\u0119\u011a\u0005n\u0000\u0000\u011a"+
		"\u0129\u0005d\u0000\u0000\u011b\u011c\u0005o\u0000\u0000\u011c\u0129\u0005"+
		"r\u0000\u0000\u011d\u011e\u0005n\u0000\u0000\u011e\u011f\u0005o\u0000"+
		"\u0000\u011f\u0129\u0005t\u0000\u0000\u0120\u0121\u0005b\u0000\u0000\u0121"+
		"\u0122\u0005t\u0000\u0000\u0122\u0123\u0005o\u0000\u0000\u0123\u0129\u0005"+
		"s\u0000\u0000\u0124\u0125\u0005h\u0000\u0000\u0125\u0126\u0005a\u0000"+
		"\u0000\u0126\u0127\u0005l\u0000\u0000\u0127\u0129\u0005t\u0000\u0000\u0128"+
		"\u0086\u0001\u0000\u0000\u0000\u0128\u008c\u0001\u0000\u0000\u0000\u0128"+
		"\u0093\u0001\u0000\u0000\u0000\u0128\u0097\u0001\u0000\u0000\u0000\u0128"+
		"\u009b\u0001\u0000\u0000\u0000\u0128\u00a0\u0001\u0000\u0000\u0000\u0128"+
		"\u00a4\u0001\u0000\u0000\u0000\u0128\u00a8\u0001\u0000\u0000\u0000\u0128"+
		"\u00ab\u0001\u0000\u0000\u0000\u0128\u00af\u0001\u0000\u0000\u0000\u0128"+
		"\u00b2\u0001\u0000\u0000\u0000\u0128\u00b6\u0001\u0000\u0000\u0000\u0128"+
		"\u00ba\u0001\u0000\u0000\u0000\u0128\u00be\u0001\u0000\u0000\u0000\u0128"+
		"\u00c4\u0001\u0000\u0000\u0000\u0128\u00cb\u0001\u0000\u0000\u0000\u0128"+
		"\u00cf\u0001\u0000\u0000\u0000\u0128\u00d3\u0001\u0000\u0000\u0000\u0128"+
		"\u00d8\u0001\u0000\u0000\u0000\u0128\u00dc\u0001\u0000\u0000\u0000\u0128"+
		"\u00df\u0001\u0000\u0000\u0000\u0128\u00e3\u0001\u0000\u0000\u0000\u0128"+
		"\u00e6\u0001\u0000\u0000\u0000\u0128\u00ea\u0001\u0000\u0000\u0000\u0128"+
		"\u00ee\u0001\u0000\u0000\u0000\u0128\u00f4\u0001\u0000\u0000\u0000\u0128"+
		"\u00f8\u0001\u0000\u0000\u0000\u0128\u00fb\u0001\u0000\u0000\u0000\u0128"+
		"\u00ff\u0001\u0000\u0000\u0000\u0128\u0105\u0001\u0000\u0000\u0000\u0128"+
		"\u010b\u0001\u0000\u0000\u0000\u0128\u0111\u0001\u0000\u0000\u0000\u0128"+
		"\u0114\u0001\u0000\u0000\u0000\u0128\u0118\u0001\u0000\u0000\u0000\u0128"+
		"\u011b\u0001\u0000\u0000\u0000\u0128\u011d\u0001\u0000\u0000\u0000\u0128"+
		"\u0120\u0001\u0000\u0000\u0000\u0128\u0124\u0001\u0000\u0000\u0000\u0129"+
		"\u001e\u0001\u0000\u0000\u0000\u012a\u012d\u0005_\u0000\u0000\u012b\u012d"+
		"\u0003\'\u0013\u0000\u012c\u012a\u0001\u0000\u0000\u0000\u012c\u012b\u0001"+
		"\u0000\u0000\u0000\u012d\u0133\u0001\u0000\u0000\u0000\u012e\u0132\u0005"+
		"_\u0000\u0000\u012f\u0132\u0003\'\u0013\u0000\u0130\u0132\u0003%\u0012"+
		"\u0000\u0131\u012e\u0001\u0000\u0000\u0000\u0131\u012f\u0001\u0000\u0000"+
		"\u0000\u0131\u0130\u0001\u0000\u0000\u0000\u0132\u0135\u0001\u0000\u0000"+
		"\u0000\u0133\u0131\u0001\u0000\u0000\u0000\u0133\u0134\u0001\u0000\u0000"+
		"\u0000\u0134 \u0001\u0000\u0000\u0000\u0135\u0133\u0001\u0000\u0000\u0000"+
		"\u0136\u0138\u0005\r\u0000\u0000\u0137\u0136\u0001\u0000\u0000\u0000\u0137"+
		"\u0138\u0001\u0000\u0000\u0000\u0138\u0139\u0001\u0000\u0000\u0000\u0139"+
		"\u013a\u0005\n\u0000\u0000\u013a\"\u0001\u0000\u0000\u0000\u013b\u013d"+
		"\u0007\u0000\u0000\u0000\u013c\u013b\u0001\u0000\u0000\u0000\u013d\u013e"+
		"\u0001\u0000\u0000\u0000\u013e\u013c\u0001\u0000\u0000\u0000\u013e\u013f"+
		"\u0001\u0000\u0000\u0000\u013f\u0140\u0001\u0000\u0000\u0000\u0140\u0141"+
		"\u0006\u0011\u0000\u0000\u0141$\u0001\u0000\u0000\u0000\u0142\u0143\u0007"+
		"\u0001\u0000\u0000\u0143&\u0001\u0000\u0000\u0000\u0144\u0145\u0007\u0002"+
		"\u0000\u0000\u0145(\u0001\u0000\u0000\u0000\r\u0000.36;AG\u0128\u012c"+
		"\u0131\u0133\u0137\u013e\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}