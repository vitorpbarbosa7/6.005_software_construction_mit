/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package lib6005.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import lib6005.parser.grammar.Choice;
import lib6005.parser.grammar.Concatenation;
import lib6005.parser.grammar.Constant;
import lib6005.parser.grammar.GrammarTerm;
import lib6005.parser.grammar.Repetition;
import lib6005.parser.grammar.NonTerminal;
import lib6005.parser.grammar.OneCharacterRegex;

/**
 * Helper class to facilitate instantiating grammars through code.
 * Helper methods nt, option, or, plus, regex, star, and str create GrammarTerms more concisely than calling constructors directly. 
 * 
 * You need to override the <code>get</code> method to make it produce your own grammar.
 * @author asolar
 *
 */
public abstract class GrammarConstruct<Sym extends Enum<Sym>> {
    
    protected Map<Sym, GrammarTerm<Sym>> grammar = new HashMap<Sym, GrammarTerm<Sym>>();
    
    @SafeVarargs
    final protected GrammarTerm<Sym> or(GrammarTerm<Sym> ... choices){
        List<GrammarTerm<Sym>> p = new ArrayList<GrammarTerm<Sym>>();
        for(GrammarTerm<Sym> gt : choices){
            p.add(gt);
        }
        return new Choice<Sym>(p);
    }
    
    @SafeVarargs    
    final protected GrammarTerm<Sym> cat(GrammarTerm<Sym> ... terms){
        List<GrammarTerm<Sym>> p = new ArrayList<GrammarTerm<Sym>>();
        for(GrammarTerm<Sym> gt : terms){
            p.add(gt);
        }
        return new Concatenation<Sym>(p);
    }
    
    protected GrammarTerm<Sym> str(String s){
        return new Constant<Sym>(s);
    }

    protected GrammarTerm<Sym> str(char c){
        return str(String.valueOf(c));
    }
    
    protected GrammarTerm<Sym> regex(String regex) {
        return new OneCharacterRegex<Sym>(Pattern.compile(regex));
    }
    
    protected GrammarTerm<Sym> nt(Sym s){
        return new NonTerminal<Sym>(s);
    }
    
    protected GrammarTerm<Sym> star(GrammarTerm<Sym> gt){
        return new Repetition<Sym>(gt, Repetition.HowMany.ZERO_OR_MORE);
    }
    
    protected GrammarTerm<Sym> plus(GrammarTerm<Sym> gt){
        return new Repetition<Sym>(gt, Repetition.HowMany.ONE_OR_MORE);
    }
    
    protected GrammarTerm<Sym> option(GrammarTerm<Sym> gt){
        return new Repetition<Sym>(gt, Repetition.HowMany.ZERO_OR_ONE);
    }
    
    /**
     * A particular use of the GrammarConstruct class overrides this method to actually return 
     * a map of GrammarTerms.
     * @return Map of symbols and their corresponding grammar terms. All elements in the Sym Enum must
     * be represented in this map.
     */
    public abstract Map<Sym, GrammarTerm<Sym>> get();

}
