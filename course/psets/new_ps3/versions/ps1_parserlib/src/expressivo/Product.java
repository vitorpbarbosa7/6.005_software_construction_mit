package expressivo;

import java.util.Objects;
import java.util.HashMap;

class Product implements Expression{
    private final Expression left, right;
   
    // Abstraction Fcuntion
    //     Represents the product of two Expressions
    // Representation Invariant
    // .   ??
    // All fields are private final 

    /** Make a Product which is the product of left and right expressions */
    public Product(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "["+left+"] * ["+right+"]";
        // return "" + left + " * " + right + "";
    }

    public Expression simplify(HashMap<String, Integer> vars){

        System.out.println("This object is: "+ this.toString());
        System.out.println("Left is: "+ this.left.toString());
        System.out.println("Right is: "+ this.right.toString());
        Expression leftReplaced = this.left.simplify(vars);
        Expression rightReplaced = this.right.simplify(vars);

        if (leftReplaced.isThisFuckingNumber() && rightReplaced.isThisFuckingNumber()) {
            Double result = leftReplaced.getValue() * rightReplaced.getValue();
            System.out.println("" + leftReplaced + "*" + rightReplaced + "= " + result);
            System.out.println("passou aqui");
            return new Number(result);
        } else {
            return new Product(leftReplaced, rightReplaced);
        }
    }

    // derivative of a product is a sum 
    public Sum differentiate(Variable var) {
        // u'
        Expression leftPrime = this.left.differentiate(var);
        // v'
        Expression rightPrime = this.right.differentiate(var);

        // uv'
        Product leftProduct = new Product(this.left, rightPrime);
        // vu'
        Product rightProduct = new Product(this.right, leftPrime);

        return new Sum(leftProduct, rightProduct);
    }

    @Override
    public boolean equals(Object thatObject){
        if(!(thatObject instanceof Product)) return false;
        Product thatProduct = (Product) thatObject;

        return this.toString().equals(thatProduct.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }

    // ok, but in fact, this is the same as a instanceof????
    // no it is not, very clever
    // you define a method inside the variant to corresponde to some instanceof, that is crazy
    public boolean isThisFuckingNumber() {
        return false;
    }

    public Double getValue() {
        throw new RuntimeException("A product has no value");
    }

}
