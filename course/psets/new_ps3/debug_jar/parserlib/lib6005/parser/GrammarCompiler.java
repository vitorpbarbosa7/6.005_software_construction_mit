/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package lib6005.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lib6005.parser.grammar.GrammarTerm;

/**
 * Parse a grammar from a character stream into a GrammarTerm value.
 * 
 * The grammar of a grammar is as follows.
 * <pre>
 *   @skip whitespaceAndComments {
 *     grammar ::= ( production | skipBlock )+
 *     production ::= nonterminal '::=' union ';'?
 *     skipBlock ::= '@skip' nonterminal '{' production* '}'
 *     union :: = concatenation ('|' concatenation)*
 *     concatenation ::= repetition+
 *     repetition ::= unit repeatOperator
 *     repeatOperator ::= [*+?]
 *     unit ::= nonterminal | terminal | '(' union ')'
 *   }
 *   nonterminal ::= [a-zA-Z_][a-zA-Z_0-9]*
 *   terminal ::= quotedString | characterSet | anyChar | characterClass
 *   quotedString ::= "'" ([^'\r\n\\] | '\\' . )+ "'"   // e.g. 'hello', '\'',  '\r\n\t'
 *                  | '"' ([^"\r\n\\] | '\\' . )+ '"'   // e.g. "world", "\"", "\r\n\t"
 *   characterSet ::= '[' ([^\]\r\n\\] | '\\' . )+ ']'   // e.g. [abc], [a-z], [^a-z], [\]], [\r\n\t]
 *   anyChar ::= '.'
 *   characterClass ::= '\\' [dsw]     // e.g. \d, \s, \w
 *   whitespaceAndComments ::= (whitespace | oneLineComment | blockComment)*
 *   whitespace ::= [ \t\r\n]* 
 *   oneLineComment ::= '//' [^\r\n]* [\r\n]+ 
 *   blockComment ::= '/*' [^*]* '*' ([^/]* '*')* '/'
 * </pre>
 */
public class GrammarCompiler {

    enum Terms {GRAMMAR, PRODUCTION, SKIPBLOCK, UNION, CONCATENATION, 
        REPETITION, REPEATOPERATOR, UNIT, NONTERMINAL, TERMINAL, QUOTEDSTRING, 
        CHARACTERSET, ANYCHAR, CHARACTERCLASS, WHITESPACE, ONELINECOMMENT, BLOCKCOMMENT, SKIP};
    
    private static GrammarConstruct<Terms> grammarGrammar = new GrammarConstruct<Terms>() {
        @Override
        public Map<Terms, GrammarTerm<Terms>> get() {
            // grammar ::= ( production | skipBlock )+
            grammar.put(Terms.GRAMMAR, cat(nt(Terms.SKIP), star(cat(or (nt(Terms.PRODUCTION), nt(Terms.SKIPBLOCK)), nt(Terms.SKIP)))));

            // skipBlock ::= '@skip' nonterminal '{' production* '}'
            grammar.put(Terms.SKIPBLOCK, cat(str("@skip"), nt(Terms.SKIP), nt(Terms.NONTERMINAL), nt(Terms.SKIP), 
                                         str('{'), nt(Terms.SKIP), 
                                         star(cat(nt(Terms.PRODUCTION), nt(Terms.SKIP))),
                                         str('}')));

            // production ::= nonterminal '::=' union ';'
            grammar.put(Terms.PRODUCTION, cat(nt(Terms.NONTERMINAL), nt(Terms.SKIP), 
                                          str("::="), nt(Terms.SKIP), 
                                          nt(Terms.UNION), nt(Terms.SKIP), 
                                          str(';')));

            // union :: = concatenation ('|' concatenation)*
            grammar.put(Terms.UNION, cat(nt(Terms.CONCATENATION), star(cat(nt(Terms.SKIP), str('|'), nt(Terms.SKIP), nt(Terms.CONCATENATION)))));
            
            // concatenation :: = repetition concatenation* | repetition
            grammar.put(Terms.CONCATENATION, or(cat(nt(Terms.REPETITION), nt(Terms.SKIP), nt(Terms.CONCATENATION)), nt(Terms.REPETITION)));
            
            // repetition ::= unit repeatOperator?
            grammar.put(Terms.REPETITION, cat(nt(Terms.UNIT), nt(Terms.SKIP), option(nt(Terms.REPEATOPERATOR))));

            // repeatOperator ::= [*+?]
            grammar.put(Terms.REPEATOPERATOR, regex("[*+?]"));
            
            // unit ::= nonterminal | terminal | '(' union ')'
            grammar.put(Terms.UNIT, or(nt(Terms.NONTERMINAL),
                                   nt(Terms.TERMINAL),
                                   cat(str('('), nt(Terms.SKIP), nt(Terms.UNION), nt(Terms.SKIP), str(')'))));

            // nonterminal ::= [a-zA-Z_][a-zA-Z_0-9]*
            grammar.put(Terms.NONTERMINAL, cat(regex("[a-zA-Z_]"), star(regex("[a-zA-Z_0-9]*"))));

            // terminal ::= quotedString | characterSet
            grammar.put(Terms.TERMINAL, or(nt(Terms.QUOTEDSTRING), nt(Terms.CHARACTERSET), nt(Terms.ANYCHAR), nt(Terms.CHARACTERCLASS)));
            
            // quotedString ::= "'" ([^'\r\n\\] | '\\' . )+ "'" | '"' ([^"\r\n\\] | '\\' . )+ '"'
            grammar.put(Terms.QUOTEDSTRING, or(cat(str("'"), plus(or(regex("[^'\r\n\\\\]"),
                                                                 cat(str('\\'), regex(".")))),
                                               str("'")),
                                           cat(str('"'), plus(or(regex("[^\"\r\n\\\\]"),
                                                                 cat(str('\\'), regex(".")))), 
                                               str('"'))));

            // characterSet ::= '[' ([^\]\r\n\\] | '\\' . )+ ']'
            grammar.put(Terms.CHARACTERSET, cat(str('['), plus(or(regex("[^\\]\r\n\\\\]"),
                                                              cat(str('\\'), regex(".")))), 
                                            str(']')));
            
            // anyChar ::= '.'
            grammar.put(Terms.ANYCHAR, str('.'));

            // characterClass ::= '\\' [dsw]
            grammar.put(Terms.CHARACTERCLASS, cat(str('\\'), regex("[dsw]")));
            
            // whitespace ::= [ \t\r\n]*
            grammar.put(Terms.WHITESPACE, regex("[ \t\r\n]"));
            grammar.put(Terms.ONELINECOMMENT, cat(str("//"), star(regex("[^\r\n]")), or(str("\r\n"), str('\n'), str('\r'))));
            grammar.put(Terms.BLOCKCOMMENT, cat(str("/*"),
                                            cat(star(regex("[^*]")), str('*')),
                                            star(cat(regex("[^/]"), star(regex("[^*]")), str('*'))), 
                                            str('/')));
            grammar.put(Terms.SKIP, star(or(nt(Terms.WHITESPACE), nt(Terms.ONELINECOMMENT), nt(Terms.BLOCKCOMMENT))));
            
            return grammar;
        }            
    };
    
    
    private static <Sym extends Enum<Sym>> Parser<Sym> compile(ParseTree<Terms> grammarTree, Sym rootNonterminal) throws UnableToParseException{

        
        
        return new Parser<Sym>(new GrammarConstruct<Sym>() {
            
            /**
             * Returns an Enum value whose name is equal to name.
             * Method is case insensitive.
             * @param name name of the Enum element.
             * @return a value of the Sym Enum type.
             */
            @SuppressWarnings("unchecked")
            Sym valueOf(String name){
                // This could have been a call to Enum.valueOf(rootNonterminal.getClass(), name);
                // but we want it to be case insensitive.                
                for(Object e : java.util.EnumSet.allOf(rootNonterminal.getClass())){            
                    if(e.toString().toLowerCase().equals(name.toLowerCase())){
                        return (Sym)e;
                    }
                }
                throw new RuntimeException(name + " is not a valid choice for a " + rootNonterminal.getClass().getName());
            }
            
            @Override
            public Map<Sym, GrammarTerm<Sym>> get() {
                // productions outside @skip blocks
                makeProductions(grammarTree.childrenByName(Terms.PRODUCTION), Optional.empty());
                
                // production inside @skip blocks
                for (ParseTree<Terms> skipBlock : grammarTree.childrenByName(Terms.SKIPBLOCK)) {
                    String nonterminal = skipBlock.childrenByName(Terms.NONTERMINAL).get(0).getContents();
                    makeProductions(skipBlock.childrenByName(Terms.PRODUCTION), Optional.of(valueOf(nonterminal)));
                }
                
                if(!grammar.containsKey(rootNonterminal)){
                    throw new RuntimeException("The grammar is missing a definition for the root nonterminal " + rootNonterminal);
                }
                return grammar;
            }

            private void makeProductions(List<ParseTree<Terms>> productions, Optional<Sym> skip) {
                for (ParseTree<Terms> production : productions) {
                    String nonterminal = production.childrenByName(Terms.NONTERMINAL).get(0).getContents();
                    GrammarTerm<Sym> expression = makeGrammarTerm(production.childrenByName(Terms.UNION).get(0), skip);
                    if (skip.isPresent()) expression = cat(makeSkip(skip.get()), expression, makeSkip(skip.get()));
                    
                    Sym s = valueOf(nonterminal);
                    grammar.put(s, expression);
                }                
            }
            
            private GrammarTerm<Sym> makeGrammarTerm(ParseTree<Terms> tree, Optional<Sym> skip) {
                Terms rule = tree.getName();
                if (rule  == Terms.UNION) {
                    GrammarTerm<Sym> result = null;
                    for (ParseTree<Terms> child : tree.childrenByName(Terms.CONCATENATION)) {
                        GrammarTerm<Sym> term = makeGrammarTerm(child, skip);
                        if (result == null) {
                            result = term;
                        } else {
                            result = or(result, term);
                        }
                    }
                    return result;
                } else if (rule==Terms.CONCATENATION) {
                    GrammarTerm<Sym> result = makeGrammarTerm(tree.childrenByName(Terms.REPETITION).get(0), skip);
                    for (ParseTree<Terms> child : tree.childrenByName(Terms.CONCATENATION)) {
                        if (skip.isPresent()) result = cat(result, makeSkip(skip.get()));
                        result = cat(result, makeGrammarTerm(child, skip));
                    }
                    return result;
                } else if (rule==Terms.REPETITION) {
                    GrammarTerm<Sym> result = makeGrammarTerm(tree.childrenByName(Terms.UNIT).get(0), skip);
                    if (!tree.childrenByName(Terms.REPEATOPERATOR).isEmpty()) {
                        if (skip.isPresent()) result = cat(result, makeSkip(skip.get()));
                        ParseTree<Terms> op = tree.childrenByName(Terms.REPEATOPERATOR).get(0);
                        if(op.getContents().equals("*")){
                            result = star(result);
                        }
                        if(op.getContents().equals("+")){
                            result = plus(result);
                        }
                        if(op.getContents().equals("?")){
                            result = option(result);
                        }                        
                    }
                    return result;
                } else if (rule==Terms.UNIT) {
                    if (!tree.childrenByName(Terms.NONTERMINAL).isEmpty()) {
                        return makeGrammarTerm(tree.childrenByName(Terms.NONTERMINAL).get(0), skip);
                    } else if (!tree.childrenByName(Terms.TERMINAL).isEmpty()) {
                        return makeGrammarTerm(tree.childrenByName(Terms.TERMINAL).get(0), skip);
                    } else if (!tree.childrenByName(Terms.UNION).isEmpty()) {
                        return makeGrammarTerm(tree.childrenByName(Terms.UNION).get(0), skip);
                    } else {
                        throw new AssertionError("internal error: no children found");
                    }
                } else if (rule==Terms.NONTERMINAL) {
                    return nt(valueOf(tree.getContents()));
                } else if (rule==Terms.TERMINAL) {
                    String content = tree.getContents();
                    if (!tree.childrenByName(Terms.QUOTEDSTRING).isEmpty()) {
                        String literal = replaceEscapeSequences(content.substring(1, content.length()-1));
                        return str(literal);
                    } else if (!tree.childrenByName(Terms.CHARACTERSET).isEmpty()) {
                        return regex(content);                        
                    } else if (!tree.childrenByName(Terms.ANYCHAR).isEmpty()
                               || !tree.childrenByName(Terms.CHARACTERCLASS).isEmpty()) {
                        // these expression correspond directly to one-character regexes:
                        // .   \d  \s  \w
                        return regex(content);
                    } else {
                        throw new AssertionError("internal error: no children found");
                    }
                } else {
                    throw new AssertionError("internal error: unknown grammar rule: " + rule);
                }
            }

            /**
             * @param nonterminal nonterminal in @skip clause
             * @return a grammar term for skipping over occurrences of it
             */
            private GrammarTerm<Sym> makeSkip(Sym nonterminal) {
                return star(nt(nonterminal));
            }
            
            /**
             * Replace \t, \r, \n with their character codes.
             * Replaces all other \x with literal x.
             */
            private String replaceEscapeSequences(String s) {
                Matcher m = Pattern.compile("\\\\(.)").matcher(s);
                StringBuffer sb = new StringBuffer();
                while (m.find()) {
                    char oldChar = m.group(1).charAt(0);
                    char newChar;
                    switch (oldChar) {
                    case 't': newChar = '\t'; break;
                    case 'r': newChar = '\r'; break;
                    case 'n': newChar = '\n'; break;
                    default:  newChar = oldChar; break;
                    }
                    // The normal .find()/.appendReplacement()/.appendTail() idiom here would 
                    // be m.appendReplacement(sb, newChar), but we can't do that because 
                    // appendReplacement() treats backslashes and dollar signs specially.
                    // We just want the literal newChar, so give appendReplacement nothing,
                    // and append it ourselves.
                    m.appendReplacement(sb, "");
                    sb.append(newChar);
                }
                m.appendTail(sb);
                return sb.toString();
            }

        }, rootNonterminal);

    }
    
    
    /**
     * Method takes a String with a grammar description and produces a Parser for that grammar.
     * The function is parameterized by a type Sym which should be an Enum type that contains one
     * value for every symbol (terminal or non-terminal) in the grammar.
     * @param in should be String containing a valid grammar.
     * @param rootNonterminal This is a value in the Enum that corresponds to the root symbol in the grammar.
     * @return A parser for the given grammar.
     * @throws UnableToParseException Thrown if the grammar file does not contain a valid grammar.
     */
    public static <Sym extends Enum<Sym>> Parser<Sym> compile(String grammar, Sym rootNonterminal) throws UnableToParseException {
        Parser<Terms> grammarParser = new Parser<Terms>(grammarGrammar, Terms.GRAMMAR);
        ParseTree<Terms> grammarTree = grammarParser.parse(grammar);
        return compile(grammarTree, rootNonterminal);
    }
    
    public static <Sym extends Enum<Sym>> Parser<Sym> compile(Reader in, Sym rootNonterminal) throws UnableToParseException, IOException {
        Parser<Terms> grammarParser = new Parser<Terms>(grammarGrammar, Terms.GRAMMAR);
        ParseTree<Terms> grammarTree = grammarParser.parse(in);
        
        return compile(grammarTree, rootNonterminal);
    }
    
    /**
     * Method takes a file with a grammar description and produces a Parser for that grammar.
     * The function is parameterized by a type Sym which should be an Enum type that contains one
     * value for every symbol (terminal or non-terminal) in the grammar.
     * @param in should be a valid grammar file.
     * @param rootNonterminal This is a value in the Enum that corresponds to the root symbol in the grammar.
     * @return A parser for the given grammar.
     * @throws UnableToParseException Thrown if the grammar file does not contain a valid grammar.
     * @throws IOException Thrown if the grammar file cannot be opened.
     */
    public static <Sym extends Enum<Sym>> Parser<Sym> compile(File in, Sym rootNonterminal) throws UnableToParseException, IOException {
        if(!in.canRead()){
            throw new IOException("Cannot read from file " + in.getAbsolutePath());
        }
        Parser<Terms> grammarParser = new Parser<Terms>(grammarGrammar, Terms.GRAMMAR);
        ParseTree<Terms> grammarTree = grammarParser.parse(in);
        
        return compile(grammarTree, rootNonterminal);
    }

    /**
     * Method takes an {@link InputStream} with a grammar description and produces a Parser for that grammar.
     * The function is parameterized by a type Sym which should be an Enum type that contains one
     * value for every symbol (terminal or non-terminal) in the grammar.
     * @param in should be an {@link InputStream} containing a valid grammar.
     * @param rootNonterminal This is a value in the Enum that corresponds to the root symbol in the grammar.
     * @return A parser for the given grammar.
     * @throws UnableToParseException Thrown if the grammar file does not contain a valid grammar.
     * @throws IOException Thrown if the {@link InputStream} throws an exception.
     */
    public static <Sym extends Enum<Sym>> Parser<Sym> compile(InputStream in, Sym rootNonterminal) throws UnableToParseException, IOException {
        Parser<Terms> grammarParser = new Parser<Terms>(grammarGrammar, Terms.GRAMMAR);
        ParseTree<Terms> grammarTree = grammarParser.parse(in);
        
        return compile(grammarTree, rootNonterminal);
    }
    
}
