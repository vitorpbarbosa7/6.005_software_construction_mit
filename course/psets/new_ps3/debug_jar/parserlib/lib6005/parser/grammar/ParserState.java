/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package lib6005.parser.grammar;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import lib6005.parser.UnableToParseException;

/**
 * Class keeps class of state during parsing
 * @author asolar
 *
 */
public class ParserState<Sym> {

    public Map<Sym, Stack<Integer> > first = new HashMap<>();
    
    public void checkLeftRecursion(int pos, Sym nonterminal){
        if(first.containsKey(nonterminal)){
            Stack<Integer> s = first.get(nonterminal); 
            if(!s.isEmpty() && s.peek() ==pos){
                throw new RuntimeException("Detected left recursion in rule for " + nonterminal.toString().toLowerCase());
            }
            s.push(pos);
        }else{
            Stack<Integer> s = new Stack<>();
            s.push(pos);
            first.put(nonterminal, s);
        }
    }
    public void leaveLeftRecursion(Sym nonterminal){
        assert first.containsKey(nonterminal);
        first.get(nonterminal).pop();
    }
    
}
