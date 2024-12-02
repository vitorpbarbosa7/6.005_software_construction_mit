package expressivo;

import java.util.HashMap;
import java.util.Map;

public class HelperSimplifly {

    public static Map<Variable, Integer> createVars(HashMap<String, Integer> vars){
        // Create a new Map to store Variable -> Integer
        Map<Variable, Integer> variables = new HashMap<>();

        // Iterate through each entry in the given HashMap
        for (Map.Entry<String, Integer> entry : vars.entrySet()) {
            String varName = entry.getKey();
            Integer value = entry.getValue();
            // Create a new Variable from the String key
            Variable var = new Variable(varName);
            // Add the Variable and its corresponding value to the new map
            variables.put(var, value);
        }

        return variables;

    }
    
}
