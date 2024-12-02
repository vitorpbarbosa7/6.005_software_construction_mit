package expressivo;

import java.util.Objects;
import java.util.HashMap;
import java.util.Map;

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

    public Expression simplify(HashMap<String, Integer> vars){
        // if both are numbers, we can create a sum here

        // left and right with same operations

        return this.left.add(this.right);

    }

    public Expression add(Expression other) {
        return this.left.add(this.right);
    }

    public Expression product(Expression other){
        return new Product(this.left, this.right);
    }

    public Sum differentiate(Variable var){
        return new Sum(this.left.differentiate(var), this.right.differentiate(var));
    }

    @Override
    public String toString() {
        return "["+left+"] + ["+right+"]";
        // return "" + left + " + " + right + "";
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
