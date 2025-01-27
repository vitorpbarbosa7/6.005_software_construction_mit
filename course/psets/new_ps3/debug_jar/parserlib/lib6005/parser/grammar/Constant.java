/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package lib6005.parser.grammar;

import java.util.Map;

import lib6005.parser.ParseTree;


/**
 * 
 * This class represents a string constant in the grammar.
 * The type parameter <code>Sym</code> is an Enum type that represents the set of non-terminals available in the grammar.
 * @author asolar
 *
 * @param <Sym>
 */
public class Constant<Sym extends Enum<Sym>> extends GrammarTerm<Sym> {
    
    private final String contents;
    
    /**
     * Create a constant from a string.
     * @param contents
     */
    public Constant(String contents) {
        super();
        this.contents = contents;
    }

    
    /**
     * 
     * @return the contents of this string constant.
     */
    public String getContents() {
        return contents;
    }

    
    @Override
    public ParseResult<Sym> parse(String s, int pos, Map<Sym, GrammarTerm<Sym>> definitions, ParserState<Sym> state){
        int newpos = pos + contents.length();
        if(newpos > s.length()){
            return new ParseResult<Sym>(contents /*"Expected '"+contents+"' but found '" + s.substring(pos) + "'"*/, pos);
        }
        String l = s.substring(pos, newpos);
        if(l.equals(contents)){
            return new ParseResult<Sym>(new ParseTree<Sym>(this.contents), newpos);
        }
        return new ParseResult<Sym>(contents, pos);
    }
    
    @Override
    public String toString(){
        return "'" + contents + "'";
    }
}
