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
