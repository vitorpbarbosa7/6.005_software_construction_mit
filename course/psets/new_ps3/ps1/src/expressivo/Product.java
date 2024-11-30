package expressivo;

import java.util.Objects;

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

}
