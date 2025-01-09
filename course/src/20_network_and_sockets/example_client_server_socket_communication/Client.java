import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        // Step 1: Connect to the server at localhost (127.0.0.1) on port 8080
        Socket socket = new Socket("127.0.0.1", 8080);
        
        // Set up input and output streams for communication
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        // Read the greeting from the server
        String serverMessage = in.readLine();
        System.out.println("Server: " + serverMessage);
        
        // Send some messages to the server
        for (int i = 0; i < 5; i++) {
            out.println("Hello from client! Message #" + (i + 1));
            String response = in.readLine();
            System.out.println("Server responds: " + response);
            Thread.sleep(1000); // Wait for 1 second before sending the next message
        }
        
        // Close the connection after 5 messages
        out.println("Goodbye!");
        in.close();
        out.close();
        socket.close();
        System.out.println("Disconnected from server.");
    }
}

