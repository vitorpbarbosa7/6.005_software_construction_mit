package expressivo;

import java.util.Objects;
import java.util.HashMap;

class Number implements Expression {
    private final double n;

    // Abstraction Function
    //     Represents the double  
    // Representation Invariant
    //        true??
    // Safety from rep exposure
    //       All fields representing the number are private final, no external client can alter it 

    /** Constructor for the Concrete Number in this class */
    public Number(double n){
        this.n = n;
    }

    public Expression simplify(HashMap<String, Integer> vars){
        return this;
    }

    public Expression add(Number other){
        return new Number(this.n + other.n);
    }

    public Expression add(Variable other){
        return new Sum(this, other);
    }

    public Expression product(Number other){
        return new Number(this.n * other.n);
    }

    public Expression product(Variable other){
        return new Product(this, other);
    }



    public Number differentiate(Variable var) {
        return new Number(0);
    }

    @Override
    public String toString() {
        return String.valueOf(n);
        // return "Number("+ String.valueOf(n)+ ")";
    }

    @Override
    public boolean equals(Object thatObject){
        if (!(thatObject instanceof Number)) return false;
        Number thatNumber = (Number) thatObject;

        return this.n == thatNumber.n;

    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.n);
    }
}