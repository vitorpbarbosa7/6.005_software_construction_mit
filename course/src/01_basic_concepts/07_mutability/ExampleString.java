// Define the custom exception
class NoSuchUserException extends Exception {
    public NoSuchUserException(String message) {
        super(message);
    }
}

public class ExampleString {
    public static void main(String[] args) {
        try {
            String id = getMitId("bitdiddle");

            // The client wants to obscure the first 5 digits
            String obscuredId = "*****" + id.substring(5);
            
            // Print out the obscured ID
            System.out.println(obscuredId); // Output: *****2033

            // Print out the original ID
            System.out.println(id); // Output: 928432033

        } catch (NoSuchUserException e) {
            System.out.println("User not found: " + e.getMessage());
        }
    }

    // Simulate the getMitId method that may throw an exception
    public static String getMitId(String username) throws NoSuchUserException {
        // In a real scenario, this would check a database or cache.
        if ("bitdiddle".equals(username)) {
            return "928432033"; // Example ID fetched from database or cache
        } else {
            throw new NoSuchUserException(username + " not found in the database.");
        }
    }
}

