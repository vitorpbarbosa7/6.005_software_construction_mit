package expressivo;

import java.util.Objects;

class Plus implements Expression{
    private final Expression left, right;
   
    // Abstraction Fcuntion
    //     Represents the product of two Expressions
    // Representation Invariant
    // .   ??
    // All fields are private final 

    /** Make a Plus which is the product of left and right expressions */
    public Plus(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "["+left+"] + ["+right+"]";
    }

    @Override
    public boolean equals(Object thatObject){
        if(!(thatObject instanceof Plus)) return false;
        Plus thatPlus = (Plus) thatObject;

        return this.toString().equals(thatPlus.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }

}
