package expressivo;

import lib6005.parser.Parser;
import lib6005.parser.UnableToParseException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    public static void main(String[] args) throws UnableToParseException, IOException {
       // Sample expressions

    // Create the variable 'x' for differentiation
    Variable varX = new Variable("x");
    Variable varY = new Variable("y");
    
    HashMap<String, Integer> vars = new HashMap<>();
    vars.put("x", 2);
    vars.put("y", 3);
    vars.put("z", 4);

    // Expression 1: x * x * x
    String a1 = "x * x * x";
    Expression expr1 = parse(a1);  // Expression 1
    Expression derivative1 = expr1.differentiate(new Variable("x"));
    System.out.println("\n\nExpression 1: " + a1);
    System.out.println("AST for Expression 1 (x * x * x): " + expr1.toString());
    System.out.println("Derivative for Expression 1 (x * x * x) with respect to x: " + derivative1);
    System.out.println("Simplified Expression 1: " + expr1.simplify(vars).toString());  // Should output 8 (2 * 2 * 2)

    // Expression 2: x * y + z
    String a2 = "x * y + z";
    Expression expr2 = parse(a2);  // Expression 2
    Expression derivative2 = expr2.differentiate(new Variable("x"));
    System.out.println("\n\nExpression 2: " + a2);
    System.out.println("AST for Expression 2 (x * y + z): " + expr2.toString());
    System.out.println("Derivative for Expression 2 (x * y + z) with respect to x: " + derivative2);
    System.out.println("Simplified Expression 2: " + expr2.simplify(vars).toString());  // Should output 10 (2 * 3 + 4)

    // Expression 3: (x) * (y + z)
    String a3 = "(x) * (y + z)";
    Expression expr3 = parse(a3);  // Expression 3
    Expression derivative3 = expr3.differentiate(new Variable("y"));
    System.out.println("\n\nExpression 3: " + a3);
    System.out.println("AST for Expression 3 ((x) * (y + z)): " + expr3.toString());
    System.out.println("Derivative for Expression 3 ((x) * (y + z)) with respect to y: " + derivative3);
    System.out.println("Simplified Expression 3: " + expr3.simplify(vars).toString());  // Should output 14 (2 * (3 + 4))

    // Expression 4: (x) + (y * 3)
    String a4 = "(x) + (y * 3)";
    Expression expr4 = parse(a4);  // Expression 4
    Expression derivative4 = expr4.differentiate(new Variable("y"));
    System.out.println("\n\nExpression 4: " + a4);
    System.out.println("AST for Expression 4 ((x) + (y * 3)): " + expr4.toString());
    System.out.println("Derivative for Expression 4 ((x) + (y * 3)) with respect to y: " + derivative4);
    System.out.println("Simplified Expression 4: " + expr4.simplify(vars).toString());  // Should output 11 (2 + 3 * 3)

    // Expression 5: (x) + (y * 3.1)
    String a5 = "(x) + (y * 3.1)";
    Expression expr5 = parse(a5);  // Expression 5
    Expression derivative5 = expr5.differentiate(new Variable("y"));
    System.out.println("\n\nExpression 5: " + a5);
    System.out.println("AST for Expression 5 ((x) + (y * 3.1)): " + expr5.toString());
    System.out.println("Derivative for Expression 5 ((x) + (y * 3.1)) with respect to y: " + derivative5);
    System.out.println("Simplified Expression 5: " + expr5.simplify(vars).toString());  // Should output 11.3 (2 + 3 * 3.1)

    }
    
    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS1 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String string) throws UnableToParseException, IOException {
        Parser<ElementsGrammar> parser = GrammarCompiler.compile(new File("Expression.g"), ElementsGrammar.ROOT);
        ParseTree<ElementsGrammar> tree = parser.parse(string);

        tree.display();

        Expression AbstractSyntaxTree = buildAST(tree);
        return AbstractSyntaxTree;
        
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

    public Expression simplify(HashMap<String, Integer> vars);

    public boolean isThisFuckingNumber();

    public Double getValue();
    

    public Expression differentiate(Variable var);



    private static Expression buildAST(ParseTree<ElementsGrammar> p) {
        
        switch(p.getName()) { 

            // base case 
            case NUMBER:
                return new Number(Double.parseDouble(p.getContents()));

            case VARIABLE:
                return new Variable(p.getContents());

            // Inductive steps:

            // we will reach NUMBER or VARIABLE, the terminal nodes, by the SUM and PRODUCT non terminals, going by the PRIMITIVE
            case PRIMITIVE:
                // if it is primitive, we must check if in the children, we have NUMBER of VARIABLE
                // Which are terminals
                if (p.childrenByName(ElementsGrammar.NUMBER).isEmpty() && p.childrenByName(ElementsGrammar.VARIABLE).isEmpty()) { 
                    // if not terminal, go in the left and expand
                    if (p.childrenByName(ElementsGrammar.SUM).isEmpty()) { 
                        return buildAST(p.childrenByName(ElementsGrammar.PRODUCT).get(0));
                    } else {
                        return buildAST(p.childrenByName(ElementsGrammar.SUM).get(0));
                    }
                } else {
                    if (p.childrenByName(ElementsGrammar.NUMBER).isEmpty()) {
                        return buildAST(p.childrenByName(ElementsGrammar.VARIABLE).get(0));
                    } else {
                        return buildAST(p.childrenByName(ElementsGrammar.NUMBER).get(0));
                    }

                }

            case SUM:
                // track if it is the first operand from the SUM
                boolean firstSum = true;
                Expression resultSum = null;
                // for each child which is a PRODUCT
                for (ParseTree<ElementsGrammar> child : p.childrenByName(ElementsGrammar.PRODUCT)){
                    if (firstSum) {
                        resultSum = buildAST(child);
                        firstSum = false;
                    } else {
                        // if it is not the first one, the first operand in the left, we go on to the next operands
                        // using the previous result as left, and the remaining one as right to build recursively down 
                        // we can have like some products for the sum,
                        // and from left to right, we go constructing the final, and recursively down
                        resultSum = new Sum(resultSum, buildAST(child));
                    }
                }

                if (firstSum) {
                    throw new RuntimeException("The SUM had no PRODUCT under it!!!");
                }

                return resultSum;
            
            case PRODUCT:
                // track if it is the first operand from the SUM
                boolean firstProduct = true;
                Expression resultProduct = null;
                for (ParseTree<ElementsGrammar> child : p.childrenByName(ElementsGrammar.PRIMITIVE)){
                    if (firstProduct) {
                        resultProduct = buildAST(child);
                        firstProduct = false;
                    } else {
                        // if it is not the first one, the first operand in the left, we go on to the next operands
                        // using the previous result as left, and the remaining one as right to build recursively down 
                        // we can have like some primitives for the product,
                        // and from left to right, we go constructing the final, and recursively down
                        resultProduct = new Product(resultProduct, buildAST(child));
                    }
                }

                if (firstProduct) {
                    throw new RuntimeException("The PRODUCT had no PRIMITIVE under it !!");
                }

                return resultProduct;

            // the first node of all, which starts with SUM
            case ROOT:
                return buildAST(p.childrenByName(ElementsGrammar.SUM).get(0)) ;

            case WHITESPACE:
                throw new RuntimeException("You reached a WHITESPACE, and this is not allowed!!" + p);

        }

        throw new RuntimeException("It went beyond the Switch case expressions, which is very weird !!");

    }

    public enum ElementsGrammar {
        ROOT, SUM, PRODUCT, PRIMITIVE, WHITESPACE, NUMBER, VARIABLE
    }





    
    /* Copyright (c) 2015-2017 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires permission of course staff.
     */
}
