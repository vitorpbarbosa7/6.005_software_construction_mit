import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class MinesweeperClientSimulator {

    // Server details
    private static final String HOST = "localhost";
    private static final int PORT = 4444;

    // Commands to be sent
    private static final String[] COMMANDS = {
        "dig 0 1",
        "dig 0 2",
        "dig 1 0",
        "flag 3 3",
        "flag 0 0",
        "dig 2 1",
        "dig 3 2",
        "flag 3 2"
    };

    public static void main(String[] args) {
        int numClients = 2; // Number of simulated clients
        ExecutorService executor = Executors.newFixedThreadPool(numClients);

        // Create client tasks
        for (int i = 0; i < numClients; i++) {
            executor.execute(() -> sendCommands());
        }

        // Shut down the executor
        executor.shutdown();
    }

    private static void sendCommands() {
        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            for (String command : COMMANDS) {
                out.println(command); // Send command to the server
                String response = in.readLine(); // Read server response
                if (response != null) {
                    System.out.println("Server response: " + response);
                }
                Thread.sleep(10); // Simulate a small delay between commands
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

