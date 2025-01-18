import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String[] args) {
        try {
            // Connect to server on localhost and port 12345
            Socket socket = new Socket("localhost", 12345);
            
            // Setup streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            // Send some input to the server
            out.println("hello world");
            
            // Receive the response from the server
            String response = in.readLine();
            System.out.println("Server responded: " + response);
            
            // Close resources
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

