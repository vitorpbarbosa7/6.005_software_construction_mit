/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package lib6005.parser.grammar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * 
 * This class represents a choice between multiple terms in the grammar, corresponding to the
 * alternation operator <code>(a | b)</code> that matches either <code>a</code> or <code>b</code>.
 * The type parameter <code>Sym</code> is an Enum type that represents the set of non-terminals available in the grammar.
 * @author asolar
 *
 * @param <Sym>
 */
public class Choice<Sym extends Enum<Sym>> extends GrammarTerm<Sym> {
    private final List<GrammarTerm<Sym>> choices;
    
    /**
     * Choice between two or more terms in the grammar. 
     * Class keeps an unmodifiable reference to the list, but other
     * classes should not modify the choices list.
     * @param choices list of choices.
     */
    public Choice(List<GrammarTerm<Sym>> choices) {
        super();        
        this.choices = Collections.unmodifiableList(choices);
    }    

    public List<GrammarTerm<Sym>> getChoices() {
        return choices;
    }    
    
    @Override
    public ParseResult<Sym> parse(String s, int pos, Map<Sym, GrammarTerm<Sym>> definitions, ParserState<Sym> state) {
        String msg = "";
        List<ParseResult<Sym>> results = new ArrayList<>();
        for(GrammarTerm<Sym> choice : choices){
            ParseResult<Sym> pr = choice.parse(s, pos, definitions, state);
            if(!pr.failed()){
                results.add(pr);
            }
            msg += "|" + pr.msg;
        }
        if(results.isEmpty()){
            return new ParseResult<Sym>(msg , pos);
        }
        Optional<ParseResult<Sym>> result = Optional.empty();        
        for(ParseResult<Sym> current : results){
            if(result.isPresent()){
                if(result.get().pos < current.pos){
                    result = Optional.of(current);
                }
            }else{
                result = Optional.of(current);
            }
        }
        return result.get();
    }
    
    @Override
    public String toString(){
        String s = "";
        boolean first = true;
        for(GrammarTerm<Sym> choice: choices){
            if(!first){
                s += "|";
            }
            first = false;
            s += choice;
        }
        return "(" + s + ")";
    }
    
}
