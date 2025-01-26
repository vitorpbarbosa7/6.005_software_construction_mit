/* Copyright (c) 2015-2017 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */

package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;


/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    // Sample expressions for testing
    private final Expression expr1 = new Product(new Variable("x"), new Product(new Variable("x"), new Variable("x")));
    private final Expression expr2 = new Sum(new Number(5), new Variable("y"));
    private final Expression expr3 = new Number(10);
    private final Expression expr4 = new Variable("z");

    // Testing toString()
    @Test
    public void testToString() {
        assertEquals("[x] * [[x] * [x]]", expr1.toString());  // Product of x * x * x
        assertEquals("[5.0] + [y]", expr2.toString());  // Sum of 5 + y
        assertEquals("10.0", expr3.toString());     // Single Number 10
        assertEquals("z", expr4.toString());      // Single Variable z
    }

    // Testing equals() method
    @Test
    public void testEquals() {
        Expression expr5 =new Product(new Variable("x"), new Product(new Variable("x"), new Variable("x")));
        Expression expr7 = new Number(10);
        
        // Same expression
        assertTrue(expr1.equals(expr5));  // expr1 and expr5 are structurally equal
        
        // Different expressions
        assertFalse(expr1.equals(expr2));  // expr1 and expr2 are different
        
        // Same number but different instances
        assertTrue(expr3.equals(expr7));  // Both are Number(10)
    }

    // Testing hashCode() method
    @Test
    public void testHashCode() {
        Expression expr5 = new Product(new Variable("x"), new Product(new Variable("x"), new Variable("x")));
        assertEquals(expr1.hashCode(), expr5.hashCode());  // Same structure, same hashcode
        
        Expression expr6 = new Sum(new Number(5), new Variable("y"));
        assertNotEquals(expr1.hashCode(), expr6.hashCode());  // Different structures, different hashcodes
    }

    // Testing differentiate()
    @Test
    public void testDifferentiate() {
        Variable varX = new Variable("x");
        Variable varY = new Variable("y");

        Expression derivative1 = expr1.differentiate(varX);
        assertEquals("[[x] * [[[x] * [1.0]] + [[x] * [1.0]]]] + [[[x] * [x]] * [1.0]]", derivative1.toString());  // Derivative of x * x * x with respect to x is 3 * x^2

        Expression expr2Diff = expr2.differentiate(varX);
        assertEquals("[0.0] + [0.0]", expr2Diff.toString());  // Derivative of 5 + y with respect to x is 0
    }

    // Testing simplify()
    @Test
    public void testSimplify() {
        HashMap<String, Integer> vars = new HashMap<>();
        vars.put("x", 2);
        vars.put("y", 3);
        
        Expression simplifiedExpr = expr1.simplify(vars);
        assertEquals("8.0", simplifiedExpr.toString());  // Simplifying x * x * x with x = 2 gives 8
        
        simplifiedExpr = expr2.simplify(vars);
        assertEquals("8.0", simplifiedExpr.toString());  // Simplifying 5 + y with y = 3 gives 10
    }

    // Additional tests as needed (for edge cases, etc.)

    @Test
    public void testInvalidExpression() throws IOException{
        // Example: test for invalid expression
        String invalidExpr = "x * + y";
        Expression.parse(invalidExpr);
    }

    @Test
    public void testDifferentiateSum() {
        // Test for Sum differentiation
        Expression expr = new Sum(new Variable("x"), new Variable("y"));
        Expression derivative = expr.differentiate(new Variable("x"));
        assertEquals("[1.0] + [0.0]", derivative.toString());  // Derivative of x + y with respect to x is 1
    }
    
}
