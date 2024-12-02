package expressivo;

import java.util.Objects;
import java.util.HashMap;
import java.util.Map;

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

    public Expression simplify(HashMap<String, Integer> vars) {

        Map<Variable, Integer> variables = HelperSimplifly.createVars(vars);

        // get the value of the variable, if does not exist, then return the variable itself
        Integer intValue = variables.get(this);
        if (intValue == null){
            return this;
        } else {
            return new Number(intValue);
        }

        }

    public Expression add(Variable other){
        return new Sum(this, other);
    }
    public Expression add(Number other){
        return new Sum(this, other);
    }

    public Expression product(Variable other){
        return new Product(this, other);
    }
    public Expression product(Number other){
        return new Product(this, other);
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
    
    // ok, but in fact, this is the same as a instanceof????
    // no it is not, very clever
    // you define a method inside the variant to corresponde to some instanceof, that is crazy
    public boolean isThisFuckingNumber() {
        return false;
    }

    public Double getValue() {
        throw new RuntimeException("A variable has no value");
    }
}
