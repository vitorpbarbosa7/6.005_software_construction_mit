/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package lib6005.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.Map.Entry;

import lib6005.parser.grammar.GrammarTerm;
import lib6005.parser.grammar.NonTerminal;
import lib6005.parser.grammar.ParseResult;
import lib6005.parser.grammar.ParserState;




/**
 * Parser has an internal representation of a grammar and contains a parse method that takes in a String 
 * (or an InputStream or a Reader) and produces a ParseTree representing that string.
 * @author asolar
 *
 */
public class Parser<Symbols extends Enum<Symbols>> {
    
    public static final String VERSION = "1.2";
    
    private final Map<Symbols, GrammarTerm<Symbols>> grammar;
    private final Symbols start;
    
    @SuppressWarnings("unchecked")
    public Parser(Map<Symbols, GrammarTerm<Symbols>> grammar, Symbols start){
        for(Object e : java.util.EnumSet.allOf(start.getClass())){            
            if(!grammar.containsKey(e)){
                throw new RuntimeException("Grammar is missing a definition for " + e);
            }
        }
        this.grammar = grammar;
        this.start = start;
    }
    
    public Parser(GrammarConstruct<Symbols> gc, Symbols start){
        this(gc.get(), start);
    }

    
    /**
     * Parses a string based on the grammar internally represented by the parser.
     * @param stream
     * InputStream from which to extract the text to be parsed.
     * @return
     * {@link ParseTree} representing the string.
     * @throws UnableToParseException
     * If the string cannot be parsed, this class will throw an UnableToParseException that describes approximately where the
     * parsing error occurred.
     */
    public ParseTree<Symbols> parse(InputStream stream) throws UnableToParseException, IOException{
        return parse(new InputStreamReader(stream));
    }
    
    /**
     * Parses a string based on the grammar internally represented by the parser.
     * @param in
     * Reader from which to extract the text to be parsed.
     * @return
     * {@link ParseTree} representing the string.
     * @throws UnableToParseException
     * If the string cannot be parsed, this class will throw an UnableToParseException that describes approximately where the
     * parsing error occurred.
     */
    public ParseTree<Symbols> parse(Reader in) throws UnableToParseException, IOException{
        BufferedReader br = new BufferedReader(in);       
        StringBuffer sb = new StringBuffer();
        String s;
        while((s=br.readLine())!= null){
            sb.append(s + "\n");
        }
        return parse(sb.toString());
        
    }
    
    /**
     * Parses a string based on the grammar internally represented by the parser.
     * @param f 
     * File containing the text to be parsed.
     * @return
     * {@link ParseTree} representing the string.
     * @throws UnableToParseException
     * If the string cannot be parsed, this class will throw an UnableToParseException that describes approximately where the
     * parsing error occurred.
     */
    public ParseTree<Symbols> parse(File f) throws UnableToParseException, IOException{
        return parse(new FileReader(f));        
    }
    
    protected String getPos(String stringToParse, int pos){
        int line = 1;
        int idx = stringToParse.indexOf('\n');
        if(idx == -1 || idx >= pos){ return "line " + line + " col " + pos; }
        while(idx < pos){
            ++line;
            int idxold = idx;
            idx = stringToParse.indexOf('\n', idx+1);
            if(idx == -1 || idx >= pos){ return "line " + line + " col " + (pos - idxold ); }            
        }
        throw new RuntimeException("unreachable");
    }
    
    protected String rest(String stringToParse, int pos){
        
        int idx = stringToParse.indexOf('\n');
        if(idx == -1 || idx > pos){ return stringToParse.substring(pos) + "<EOIN>"; }
        while(idx < pos){            
            idx = stringToParse.indexOf('\n', idx+1);
            if(idx == -1 || idx >= pos){ return stringToParse.substring(pos, Math.max(idx, stringToParse.length()) ) + "<EOL>"; }            
        }
        assert false : "unreachable";
        return null;
    }
    
    protected String errorMsg(String stringToParse, ParseResult<Symbols> pr){
        String pos = getPos(stringToParse, pr.pos);
        return "String does not match grammar \n" +
                "Error in " + pos + "\n" + 
                "Expected " + pr.msg + " but saw " + rest(stringToParse, pr.pos);
    }
    
    
    /**
     * Parses a string based on the grammar internally represented by the parser.
     * @param textToParse 
     * String to be parsed
     * @return
     * {@link ParseTree} representing the string.
     * @throws UnableToParseException
     * If the string cannot be parsed, this class will throw an UnableToParseException that describes approximately where the
     * parsing error occurred.
     */
    public ParseTree<Symbols> parse(String textToParse) throws UnableToParseException {
        ParseResult<Symbols> pr;
        try{
            pr= new NonTerminal<Symbols>(start).parse(textToParse, 0, grammar, new ParserState());
        }catch(RuntimeException re){
            throw new UnableToParseException(re.getMessage());
        }
        if(pr.failed()){            
            throw new UnableToParseException(errorMsg(textToParse, pr));
        }
        if(pr.pos < textToParse.length()){
            String msg = "Only parsed up to " + getPos(textToParse, pr.pos) + " the rest of the input did not parse";            
            if(pr.additional != null){
                msg += "\n" + errorMsg(textToParse, pr.additional);
            }
            throw new UnableToParseException(msg);
        }
        
        return pr.tree;
    }
    
    @Override
    public String toString(){
        String s = "";
        for(Entry<Symbols, GrammarTerm<Symbols>> ent : grammar.entrySet()){
            s += ent.getKey() + "::=" + ent.getValue() + ";\n";
        }
        return s;
    }
}
