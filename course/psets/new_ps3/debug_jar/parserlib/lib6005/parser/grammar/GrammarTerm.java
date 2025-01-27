/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package lib6005.parser.grammar;

import java.util.Map;

import lib6005.parser.ParseTree;


/**
 * This abstract class defines a term in a grammar. 
 * The type parameter <code>Sym</code> is an Enum type that represents the set of non-terminals available in the grammar.
 * Every GrammarTerm must implement a parse method that parses that particular kind of term.
 * @author asolar
 *
 * @param <Sym>
 */
public abstract class GrammarTerm<Sym extends Enum<Sym>> {
    
    /**
     * Given a string s and a position pos within the string, this method will attempt to parse 
     * this particular GrammarTerm from the string. If the GrammarTerm is successfully parsed, 
     * the ParseResult will include a {@link ParseTree} for the term and the position in the string where
     * that term ends. This means that {@link ParseResult}.pos is between pos and s.length.
     * 
     * If the GrammarTerm is not parsed successfully, then the ParseResult will
     * indicate that (see definition of {@link ParseResult}). 
     *  
     * 
     * @param s
     * @param pos should be a valid position in the string s.
     * @param definitions should contain a definition of *all* the non-terminals in the grammar.
     * @return
     */
    public abstract ParseResult<Sym> parse(String s, int pos, Map<Sym, GrammarTerm<Sym>> definitions, ParserState<Sym> state);
}
