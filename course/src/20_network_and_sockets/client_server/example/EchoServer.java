import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);  // Port number passed as command-line argument

        try (
            ServerSocket serverSocket = new ServerSocket(portNumber); // Create a server socket that listens on the provided port
            Socket clientSocket = serverSocket.accept(); // Accept the incoming client connection
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // Output stream to send data back to client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Input stream to read data from the client
        ) {
            String inputLine;
            // Read the input from the client and send the same input back (echoing)
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine); // Echo the received input to the client
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}

