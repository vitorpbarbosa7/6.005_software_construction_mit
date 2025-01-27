/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package lib6005.parser.grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lib6005.parser.ParseTree;


/**
 * This class represents a non-terminal in the grammar. 
 * The type parameter <code>Sym</code> is an Enum type that represents the set of non-terminals available in the grammar.
 * @author asolar
 *
 * @param <Sym>
 */
public class NonTerminal<Sym extends Enum<Sym>> extends GrammarTerm<Sym> {
    
    /**
     * 
     * @param name note that the type system prevents us from passing an illegal name.
     */
    public NonTerminal(Sym name) {
        super();
        this.name = name;
    }

    private final Sym name;

    public Sym getName() {
        return name;
    }

    @Override
    public ParseResult<Sym> parse(String s, int pos, Map<Sym, GrammarTerm<Sym>> definitions, ParserState<Sym> state) {
        GrammarTerm<Sym> gt = definitions.get(name);
        if(gt == null){
            // GrammarConstruct should be enforcing this invariant 
            throw new RuntimeException("nonterminal has no definition: " + name);
        }
        state.checkLeftRecursion(pos, name);
        ParseResult<Sym> pr = gt.parse(s, pos, definitions, state);
        if (pr.failed()) {
            state.leaveLeftRecursion(name);
            return pr;
        }
        if(!pr.tree.assignedNT()){
            pr.tree.setName(this.name);
        }else{
            Map<Sym, List<ParseTree<Sym>>> chmap = new HashMap<>();
            List<ParseTree<Sym>> alist = new ArrayList<>();
            alist.add(pr.tree);
            chmap.put(pr.tree.getName(), alist);
            pr = pr.replaceTree( new ParseTree<Sym>(this.name, pr.tree.getContents(), chmap, new ArrayList<>(alist))  );            
        }
        state.leaveLeftRecursion(name);
        return pr;
    }
    
    @Override
    public String toString(){
        return name.toString().toLowerCase();
    }
}
