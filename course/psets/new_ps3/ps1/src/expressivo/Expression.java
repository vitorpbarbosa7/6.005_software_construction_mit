package expressivo;

import lib6005.parser.Parser;
import lib6005.parser.UnableToParseException;

import java.io.IOException;

import expressivo.GrammarRun.ElementsGrammar;
import lib6005.parser.GrammarCompiler;
import lib6005.parser.ParseTree;

/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 * 
 * <p>PS1 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 * Declare concrete variants of Expression in their own Java source files.
 */
public interface Expression {
    
    // Datatype definition
    //   Expression = Number(n: double) 
    //            Variable(variable:String) + 
    //            Product(left:Expression, right:Expression)
    //            Sum(left:Expression, right:Expression)
    // Once defined their variants, need to implement them concretely
    
    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS1 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String string) throws UnableToParseException, IOException {
        // Parser<ElementsGrammar> parser = GrammarCompiler.compile(new File("Expression.g"), ElementsGrammar.ROOT);
        // ParseTree<ElementsGrammar> tree = parser.parse(string);


        // Expression AbstractSyntaxTree = buildAST(tree);

        // return AbstractSyntaxTree;
        
        throw new RuntimeException("unimplemented");
    }
    
    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override 
    public String toString();

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS1 handout.
     */
    @Override
    public boolean equals(Object thatObject);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();

    private static Expression buildAST(ParseTree<ElementsGrammar> p) {
        
        switch(p.getName()) { 
            
            // base case 
            case NUMBER:
                return new Number(Double.parseDouble(p.getContents()));

            case VARIABLE:
                return new Variable(p.getContents());

            case PRIMITIVE:
                // if it is primitive, we must check if in the children, we have NUMBER of VARIABLE
                // Which are terminals
                if (p.childrenByName(ElementsGrammar.NUMBER).isEmpty()) { 
                    // if not terminal, go in the left and expand
                    if (p.childrenByName(ElementsGrammar.SUM).isEmpty()) { 
                        return buildAST(p.childrenByName(ElementsGrammar.PRODUCT).get(0));
                    }
                }
                if (p.childrenByName(ElementsGrammar.NUMBER).isEmpty()) { 
                    // if not terminal, go in the left and expand
                    if (p.childrenByName(ElementsGrammar.PRODUCT).isEmpty()) { 
                        return buildAST(p.childrenByName(ElementsGrammar.SUM).get(0));
                    }
                }
            

        }
    }

    public enum ElementsGrammar {
        ROOT, SUM, PRODUCT, PRIMITIVE, WHITESPACE, NUMBER, VARIABLE
    }



    
    /* Copyright (c) 2015-2017 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires permission of course staff.
     */
}
