package expressivo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class EchoProgram {
    public static void main(String[] args) {
        // Create a BufferedReader to read input from the console (System.in)
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Enter some text (type 'exit' to quit):");
        
        while (true) {
            try {
                // Read a line of input from the console
                String input = reader.readLine();
                
                // If the user types 'exit', break out of the loop and end the program
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }
                
                // Echo the input back to the console
                System.out.println("You entered: " + input);
                
            } catch (IOException e) {
                // Handle any IO exceptions that may occur
                System.err.println("An error occurred while reading input: " + e.getMessage());
                break;
            }
        }
    }
}
