// Generated from Expression.g4 by ANTLR 4.5.1

package expressivo.parser;
// Do not edit this .java file! Edit the grammar in Expression.g4 and re-run Antlr.

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionParser extends Parser {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    T__0=1, T__1=2, T__2=3, T__3=4, NUMBER=5, VAR=6, SPACES=7;
  public static final int
    RULE_sum = 0, RULE_product = 1, RULE_primitive = 2;
  public static final String[] ruleNames = {
    "sum", "product", "primitive"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'+'", "'*'", "'('", "')'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, null, null, "NUMBER", "VAR", "SPACES"
  };
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
  public String getGrammarFileName() { return "Expression.g4"; }

  @Override
  public String[] getRuleNames() { return ruleNames; }

  @Override
  public String getSerializedATN() { return _serializedATN; }

  @Override
  public ATN getATN() { return _ATN; }


      // This method makes the lexer or parser stop running if it encounters
      // invalid input and throw a ParseCancellationException.
      public void reportErrorsAsExceptions() {
          // To prevent any reports to standard error, add this line:
          //removeErrorListeners();
          
          addErrorListener(new BaseErrorListener() {
              public void syntaxError(Recognizer<?, ?> recognizer,
                                      Object offendingSymbol,
                                      int line, int charPositionInLine,
                                      String msg, RecognitionException e) {
                  throw new ParseCancellationException(msg, e);
              }
          });
      }

  public ExpressionParser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }
  public static class SumContext extends ParserRuleContext {
    public List<ProductContext> product() {
      return getRuleContexts(ProductContext.class);
    }
    public ProductContext product(int i) {
      return getRuleContext(ProductContext.class,i);
    }
    public SumContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_sum; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterSum(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitSum(this);
    }
  }

  public final SumContext sum() throws RecognitionException {
    SumContext _localctx = new SumContext(_ctx, getState());
    enterRule(_localctx, 0, RULE_sum);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(6);
      product();
      setState(11);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==T__0) {
        {
        {
        setState(7);
        match(T__0);
        setState(8);
        product();
        }
        }
        setState(13);
        _errHandler.sync(this);
        _la = _input.LA(1);
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

  public static class ProductContext extends ParserRuleContext {
    public List<PrimitiveContext> primitive() {
      return getRuleContexts(PrimitiveContext.class);
    }
    public PrimitiveContext primitive(int i) {
      return getRuleContext(PrimitiveContext.class,i);
    }
    public ProductContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_product; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterProduct(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitProduct(this);
    }
  }

  public final ProductContext product() throws RecognitionException {
    ProductContext _localctx = new ProductContext(_ctx, getState());
    enterRule(_localctx, 2, RULE_product);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(14);
      primitive();
      setState(19);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==T__1) {
        {
        {
        setState(15);
        match(T__1);
        setState(16);
        primitive();
        }
        }
        setState(21);
        _errHandler.sync(this);
        _la = _input.LA(1);
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

  public static class PrimitiveContext extends ParserRuleContext {
    public TerminalNode VAR() { return getToken(ExpressionParser.VAR, 0); }
    public TerminalNode NUMBER() { return getToken(ExpressionParser.NUMBER, 0); }
    public SumContext sum() {
      return getRuleContext(SumContext.class,0);
    }
    public PrimitiveContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_primitive; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterPrimitive(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitPrimitive(this);
    }
  }

  public final PrimitiveContext primitive() throws RecognitionException {
    PrimitiveContext _localctx = new PrimitiveContext(_ctx, getState());
    enterRule(_localctx, 4, RULE_primitive);
    try {
      setState(28);
      switch (_input.LA(1)) {
      case VAR:
        enterOuterAlt(_localctx, 1);
        {
        setState(22);
        match(VAR);
        }
        break;
      case NUMBER:
        enterOuterAlt(_localctx, 2);
        {
        setState(23);
        match(NUMBER);
        }
        break;
      case T__2:
        enterOuterAlt(_localctx, 3);
        {
        setState(24);
        match(T__2);
        setState(25);
        sum();
        setState(26);
        match(T__3);
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
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\t!\4\2\t\2\4\3"+
      "\t\3\4\4\t\4\3\2\3\2\3\2\7\2\f\n\2\f\2\16\2\17\13\2\3\3\3\3\3\3\7"+
      "\3\24\n\3\f\3\16\3\27\13\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4\37\n\4\3\4"+
      "\2\2\5\2\4\6\2\2!\2\b\3\2\2\2\4\20\3\2\2\2\6\36\3\2\2\2\b\r\5\4\3"+
      "\2\t\n\7\3\2\2\n\f\5\4\3\2\13\t\3\2\2\2\f\17\3\2\2\2\r\13\3\2\2\2"+
      "\r\16\3\2\2\2\16\3\3\2\2\2\17\r\3\2\2\2\20\25\5\6\4\2\21\22\7\4\2"+
      "\2\22\24\5\6\4\2\23\21\3\2\2\2\24\27\3\2\2\2\25\23\3\2\2\2\25\26\3"+
      "\2\2\2\26\5\3\2\2\2\27\25\3\2\2\2\30\37\7\b\2\2\31\37\7\7\2\2\32\33"+
      "\7\5\2\2\33\34\5\2\2\2\34\35\7\6\2\2\35\37\3\2\2\2\36\30\3\2\2\2\36"+
      "\31\3\2\2\2\36\32\3\2\2\2\37\7\3\2\2\2\5\r\25\36";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}