package expressivo;

import java.util.Objects;

class Variable implements Expression{
    private final String var;

    // Abstraction Function
    //       Represents a Variable
    // Representation Invariant
    //       
    // Safety from rep exposure
    //      All fields representing the variable are private and final

    public Variable(String var){
        this.var = var;
    }

    @Override
    public String toString() {
        return this.var;
    }

    @Override
    public boolean equals(Object thatObject){
        if (!(thatObject instanceof Variable)) return false;
        Variable thatVariable = (Variable) thatObject;

        return this.var.equals(thatVariable.var);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.var);
    }
    
}
