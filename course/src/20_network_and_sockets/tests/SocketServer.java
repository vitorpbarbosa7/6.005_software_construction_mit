import java.io.*;
import java.net.*;

public class SocketServer {
    public static void main(String[] args) {
        try {
            // Create server socket on port 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started, waiting for clients...");
            
            // Accept a connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from " + clientSocket.getInetAddress());
            
            // Setup streams
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            // Call the method to handle the data
            upperCaseLine(in, out);
            
            // Close resources
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Function to read a line and output it in uppercase
    public static void upperCaseLine(BufferedReader input, PrintWriter output) throws IOException {
        String line = input.readLine();
        if (line != null) {
            output.println(line.toUpperCase());
        }
    }
}

