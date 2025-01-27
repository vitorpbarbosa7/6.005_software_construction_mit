/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package lib6005.parser.grammar;

import lib6005.parser.ParseTree;

/**
 * Immutable class that represents the result of parsing part of a string.
 * @author asolar
 *
 * @param <Sym>
 */
public class ParseResult<Sym extends Enum<Sym>> {
    public enum Result {PASS, FAIL};
    public final String msg;
    public final ParseTree<Sym> tree;
    public final int pos;
    public final ParseResult<Sym> additional;
    public final Result res;   
    
    
    /**
     * Keep track of an additional result so it can be used for debugging purposes. 
     * @param additional
     * @return
     */
    public ParseResult<Sym> addAdditional(ParseResult<Sym> additional){
        return new ParseResult<Sym>(this.tree, this.pos, this.res, this.msg, additional);
    }
    
    public ParseResult<Sym> replaceTree(ParseTree<Sym> newTree){
        assert this.res == Result.PASS;
        return new ParseResult<Sym>(newTree, this.pos, this.res, this.msg, this.additional);
    }
    
    /**
     * Concatenate a new result into the current result. 
     * @param pr
     * @return
     */
    public ParseResult<Sym> mergeResult(ParseResult<Sym> pr){
        assert pr.res == Result.PASS && this.res == Result.PASS;
        return new ParseResult<Sym>(ParseTree.concat(this.tree, pr.tree), pr.pos, this.res, this.msg, this.additional);        
    }
    
    
    private ParseResult(ParseTree<Sym> tree, int pos, Result res, String msg, ParseResult<Sym> additional) {        
        this.tree = tree;
        this.pos = pos;
        this.res = res;
        this.msg = msg;
        this.additional = additional;
    }
    
    
    public ParseResult(ParseTree<Sym> tree, int pos) {
        this.tree = tree;
        this.pos = pos;
        this.res = Result.PASS;
        this.msg = null;
        this.additional = null;
    }
    
    String desugar(String msg){
        msg = msg.replace("\\", "\\\\");
        msg = msg.replace("\n", "\\n");
        msg = msg.replace("\r", "\\r");
        msg = msg.replace("\t", "\\t");        
        return msg;
    }
    
    public ParseResult(String errmsg, int pos) {
        this.pos = pos;
        this.res = Result.FAIL;
        this.msg = desugar(errmsg);
        this.tree = null;
        this.additional = null;
    }
    
    public boolean failed(){
        return res == Result.FAIL;
    }
}
