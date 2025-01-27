/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package lib6005.parser.grammar;

import java.util.Map;
import java.util.regex.Pattern;

import lib6005.parser.ParseTree;

/**
 * Matches exactly one character described by a regular expression, which is typically one of:
 * <pre>
 *    .     any character
 *    [...] character range
 *    \d, \s, \w   character class   
 */
public class OneCharacterRegex<Sym extends Enum<Sym>> extends GrammarTerm<Sym> {
    private final Pattern regex;

    public OneCharacterRegex(Pattern regex) {
        super();
        this.regex = regex;
    }
    
    
    @Override
    public ParseResult<Sym> parse(String s, int pos, Map<Sym, GrammarTerm<Sym>> definitions, ParserState<Sym> state){
        if (pos >= s.length()) {
            return new ParseResult<Sym>(toString(), pos);
        }
        String l = s.substring(pos, pos+1);
        if (regex.matcher(l).matches()) {
            return new ParseResult<Sym>(new ParseTree<Sym>(l), pos+1);
        }
        return new ParseResult<Sym>(toString(), pos);
    }
    
    @Override
    public String toString() {
        return regex.toString();
    }
}
