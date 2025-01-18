import java.util.*;

class Main {
    public static void main(String[] args) {
        // Create an Environment (ImList of Object[])
        List<Object[]> environment = new ArrayList<>();

        // Add tuples (Variable, Boolean)
        environment.add(new Object[]{"var1", true});
        environment.add(new Object[]{"var2", false});

        // Lookup function
        String variableToFind = "var1";
        Boolean value = lookup(environment, variableToFind);
        System.out.println("Value for " + variableToFind + ": " + value);
    }

    // Lookup method
    public static Boolean lookup(List<Object[]> environment, String variable) {
        for (Object[] pair : environment) {
            if (pair[0].equals(variable)) {
                return (Boolean) pair[1];
            }
        }
        return null; // Not found
    }
}

