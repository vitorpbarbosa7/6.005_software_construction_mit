// Base interface for all formulas
public interface Formula {
    // Method to evaluate the formula given truth values for variables
    boolean evaluate(java.util.Map<String, Boolean> variableValues);

    // Method to convert the formula to a string
    String toString();
}

// Variable formula (leaf node)
class Variable implements Formula {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public boolean evaluate(java.util.Map<String, Boolean> variableValues) {
        return variableValues.getOrDefault(name, false);
    }

    public String toString() {
        return name;
    }
}

// Negation formula
class Not implements Formula {
    private final Formula formula;

    public Not(Formula formula) {
        this.formula = formula;
    }

    public boolean evaluate(java.util.Map<String, Boolean> variableValues) {
        return !formula.evaluate(variableValues);
    }

    public String toString() {
        return "¬" + formula.toString();
    }
}

// Conjunction (AND) formula
class And implements Formula {
    private final Formula left;
    private final Formula right;

    public And(Formula left, Formula right) {
        this.left = left;
        this.right = right;
    }

    public boolean evaluate(java.util.Map<String, Boolean> variableValues) {
        return left.evaluate(variableValues) && right.evaluate(variableValues);
    }

    public String toString() {
        return "(" + left.toString() + " ∧ " + right.toString() + ")";
    }
}

// Disjunction (OR) formula
class Or implements Formula {
    private final Formula left;
    private final Formula right;

    public Or(Formula left, Formula right) {
        this.left = left;
        this.right = right;
    }

    public boolean evaluate(java.util.Map<String, Boolean> variableValues) {
        return left.evaluate(variableValues) || right.evaluate(variableValues);
    }

    public String toString() {
        return "(" + left.toString() + " ∨ " + right.toString() + ")";
    }
}

// Main class to test the implementation
public class Main {
    public static void main(String[] args) {
        // Formula: (P ∨ Q) ∧ (¬P ∨ R)
        Formula formula = new And(
            new Or(new Variable("P"), new Variable("Q")),
            new Or(new Not(new Variable("P")), new Variable("R"))
        );

        // Truth values for variables
        java.util.Map<String, Boolean> values = new java.util.HashMap<>();
        values.put("P", true);
        values.put("Q", false);
        values.put("R", true);

        System.out.println("Formula: " + formula);
        System.out.println("Evaluation: " + formula.evaluate(values)); // Output: true
    }
}

