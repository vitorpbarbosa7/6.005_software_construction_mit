package expressivo;

import java.util.Objects;

class Variable implements Expression{
    private final String variable;

    // Abstraction Function
    //       Represents a Variable
    // Representation Invariant
    //       
    // Safety from rep exposure
    //      All fields representing the variable are private and final

    public Variable(String variable){
        this.variable = variable;
    }

    public Number differentiate(Variable var){
        if (this.equals(var)){
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    @Override
    public String toString() {
        return this.variable;
    }

    @Override
    public boolean equals(Object thatObject){
        if (!(thatObject instanceof Variable)) return false;
        Variable thatVariable = (Variable) thatObject;

        return this.variable.equals(thatVariable.variable);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.variable);
    }
    
}
