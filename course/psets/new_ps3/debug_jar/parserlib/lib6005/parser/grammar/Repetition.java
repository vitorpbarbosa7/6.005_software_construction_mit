/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package lib6005.parser.grammar;

import java.util.Map;

import lib6005.parser.ParseTree;


/**
 * This class represents different kinds of repetitions in the grammar. It can either represent
 * (a) a Kleen closure (x*) which matches zero or more instances of a term <code>x</code>, 
 * (b) the operator x+ which matches one or more instances of <code>x</code>, or 
 * (c) the operator x? which matches zero or one copies of <code>x</code>.
 * 
 * The type parameter <code>Sym</code> is an Enum type that represents the set of non-terminals available in the grammar.
 * @author asolar
 *
 * @param <Sym>
 */
public class Repetition<Sym extends Enum<Sym>> extends GrammarTerm<Sym> {
    public static enum HowMany { ZERO_OR_MORE, ONE_OR_MORE, ZERO_OR_ONE };
    
    private final GrammarTerm<Sym> body;
    private final HowMany howmany;
    
    /**
     * Construct a kind of repetition determined by the <code>howmany</code> parameter.
     * @param body
     * @param howmany
     */
    public Repetition(GrammarTerm<Sym> body, HowMany howmany) {
        super();
        this.howmany = howmany;
        this.body = body;
    }
    
    public GrammarTerm<Sym> getBody() {
        return body;
    }
    
    @Override
    public ParseResult<Sym> parse(String s, int pos, Map<Sym, GrammarTerm<Sym>> definitions, ParserState<Sym> state) {
        final ParseResult<Sym> UNINITIALIZED = null;
        ParseResult<Sym> prout=UNINITIALIZED;
        do{
            ParseResult<Sym> pr = body.parse(s, pos, definitions, state);
            if(pr.failed()){
                if(prout == UNINITIALIZED){
                    if(howmany == HowMany.ONE_OR_MORE){
                        return pr;
                    }else{
                        prout = new ParseResult<Sym>(new ParseTree<Sym>(""), pos);
                    }
                }
                prout = prout.addAdditional(pr);
                return prout;
            }
            if(pr.pos == pos){
                if(prout == UNINITIALIZED){
                    return pr;
                }
                return prout;
            }
            pos = pr.pos;
            if(prout == UNINITIALIZED){
                prout = pr;
            }else{
                prout = prout.mergeResult(pr);                
            }
            if (howmany == HowMany.ZERO_OR_ONE) {
                return prout;
            }
        }while(true);        
    }

    @Override
    public String toString(){
        switch (howmany) {
        case ZERO_OR_MORE: return body + "*";
        case ONE_OR_MORE: return body + "+";
        case ZERO_OR_ONE: return body + "?";
        default: throw new AssertionError("can't get here");
        }
    }
    
}
