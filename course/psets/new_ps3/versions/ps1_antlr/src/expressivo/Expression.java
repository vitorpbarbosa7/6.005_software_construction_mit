package expressivo;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import expressivo.parser.ExpressionLexer;
import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;



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

    public static void main(String[] args) throws IOException {
        // Sample expressions

        // Create the variable 'x' for differentiation
        Variable varX = new Variable("x");
        Variable varY = new Variable("y");
        
        HashMap<String, Integer> vars = new HashMap<>();
        vars.put("x", 2);
        vars.put("y", 3);
        vars.put("z", 4);

        //Expression 1: x * x * x
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
    public static Expression parse(String string){
        try {
            CharStream cs = new ANTLRInputStream(string);
            ExpressionLexer lexer = new ExpressionLexer(cs);
            lexer.removeErrorListeners();
            lexer.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                        int line, int charPositionInLine, String msg, RecognitionException e) {
                    throw new IllegalArgumentException("Invalid expression: " + msg);
                }
            });

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExpressionParser parser = new ExpressionParser(tokens);

            parser.removeErrorListeners();
            parser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                        int line, int charPositionInLine, String msg, RecognitionException e) {
                    throw new IllegalArgumentException("Invalid expression: " + msg);
                }
            });

            ExpressionParser.SumContext sumContext = parser.sum();
            Expression AbstractSyntaxTree = buildAST(sumContext);
            return AbstractSyntaxTree; 
            
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse expression", e);
        }
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

    // The return is always a expression, different kinds of them 
    // Polymorphism?
    private static Expression buildAST(ExpressionParser.SumContext sumContext) {

        if (sumContext.product().size() == 1) {
            return buildProductAST(sumContext.product(0));
        }

        Expression left = buildProductAST(sumContext.product(0));

        // explore the others if available using the recursion also
        for (int i = 1; i < sumContext.product().size(); i ++) {
            Expression right = buildProductAST(sumContext.product(i));
            // when back in this stack level of the frame, we must add, because we are at the buildAST Sum level (the first one)
            left = new Sum(left, right); 
        }

        return left;
    } 


    private static Expression buildProductAST(ExpressionParser.ProductContext productContext) {

        if (productContext.primitive().size() == 0) {
            return buildPrimitiveAST(productContext.primitive(0));
        }

        Expression left = buildPrimitiveAST(productContext.primitive(0));

        for (int i = 1; i < productContext.primitive().size();  i++ ) {
            Expression right = buildPrimitiveAST(productContext.primitive(i));
            left = new Product(left, right);
        }

        return left;
    }


    private static Expression buildPrimitiveAST(ExpressionParser.PrimitiveContext primitiveContext) {

        if (primitiveContext.NUMBER() != null) {
            return new Number(Double.parseDouble(primitiveContext.NUMBER().getText()));
        } else if (primitiveContext.VAR() != null) {
            return new Variable(primitiveContext.VAR().getText());
        } else {
            return buildAST(primitiveContext.sum());
        }

    }
    /* Copyright (c) 2015-2017 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires permission of course staff.
     */
}
