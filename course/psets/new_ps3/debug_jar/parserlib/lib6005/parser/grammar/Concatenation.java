/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package lib6005.parser.grammar;

import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * This class represents a concatenation of multiple terms in the grammar.
 * The type parameter <code>Sym</code> is an Enum type that represents the set of non-terminals available in the grammar.
 * @author asolar
 *
 * @param <Sym>
 */
public class Concatenation<Sym extends Enum<Sym>> extends GrammarTerm<Sym> {
    private final List<GrammarTerm<Sym>> parts;
    
    
    /**
     * Concatenation of two or more terms in the grammar. 
     * Class keeps an unmodifiable reference to the list, but other
     * classes should not modify the parts list.
     * @param choices list of terms to concatenate.
     */
    public Concatenation(List<GrammarTerm<Sym>> parts) {
        super();
        this.parts = Collections.unmodifiableList(parts);
    }
    
    public List<GrammarTerm<Sym>> getParts() {
        return parts;
    }    
    
    
    @Override
    public ParseResult<Sym> parse(String s, int pos, Map<Sym, GrammarTerm<Sym>> definitions, ParserState<Sym> state) {
        ParseResult<Sym> prout=null;
        for(GrammarTerm<Sym> gt: parts){
            ParseResult<Sym> pr = gt.parse(s, pos, definitions, state);
            if(pr.failed())
                return pr;
            pos = pr.pos;
            if(prout != null){
                prout = prout.mergeResult(pr);                
                if(pr.additional != null){
                    prout = prout.addAdditional(pr.additional);                    
                }
            }else{
                prout = pr;
            }
        }
        return prout;
    }
    
    @Override
    public String toString(){
        String s = "";        
        for(GrammarTerm<Sym> choice: parts){            
            s += choice;
        }
        return "(" + s + ")";
    }

}
