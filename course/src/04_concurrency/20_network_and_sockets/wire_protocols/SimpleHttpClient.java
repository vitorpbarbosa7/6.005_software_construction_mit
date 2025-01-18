import java.io.*;
import java.net.*;

public class SimpleHttpClient {
    public static void main(String[] args) {
        String host = "www.google.com";  // Host to send HTTP request to
        int port = 80;  // Standard HTTP port
        
        try {
            // Step 1: Open a socket connection to the server
            Socket socket = new Socket(host, port);

            // Step 2: Send an HTTP GET request through the socket
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("GET / HTTP/1.1");
            out.println("Host: " + host);
            out.println("Connection: close");
            out.println(""); // Blank line to indicate end of request headers

            // Step 3: Read the response from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String responseLine;
            long startTime = System.currentTimeMillis();
            while ((responseLine = in.readLine()) != null) {
                System.out.println(responseLine);
                
                // Stop after 5 seconds
                if (System.currentTimeMillis() - startTime > 5000) {
                    break;
                }
            }

            // Step 4: Close the socket after getting the response
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

