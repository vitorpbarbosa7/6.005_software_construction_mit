package expressivo;

import java.util.Objects;

class Sum implements Expression{
    private final Expression left, right;
   
    // Abstraction Fcuntion
    //     Represents the product of two Expressions
    // Representation Invariant
    // .   ??
    // All fields are private final 

    /** Make a Sum which is the product of left and right expressions */
    public Sum(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "["+left+"] + ["+right+"]";
    }

    @Override
    public boolean equals(Object thatObject){
        if(!(thatObject instanceof Sum)) return false;
        Sum thatSum = (Sum) thatObject;

        return this.toString().equals(thatSum.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }

}
