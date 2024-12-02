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


    // ok, but in fact, this is the same as a instanceof????
    // no it is not, very clever
    // you define a method inside the variant to corresponde to some instanceof, that is crazy
    public boolean isThisFuckingNumber() {
        return true;
    }

    public Double getValue() {
        return Double.valueOf(this.n);
    }
}