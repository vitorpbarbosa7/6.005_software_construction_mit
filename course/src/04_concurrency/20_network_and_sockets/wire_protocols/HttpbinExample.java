import java.io.*;
import java.net.*;

public class HttpbinExample {
    public static void main(String[] args) {
        String host = "httpbin.org";  // Host for httpbin
        int port = 80;  // Standard HTTP port
        
        try {
            // Step 1: Open a socket connection to httpbin.org
            Socket socket = new Socket(host, port);

            // Step 2: Send an HTTP GET request through the socket
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("GET /get HTTP/1.1");  // Requesting the /get endpoint
            out.println("Host: " + host);
            out.println("Connection: close");  // Close the connection after the response
            out.println(""); // Blank line to indicate the end of the headers

            // Step 3: Read the response from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                System.out.println(responseLine);  // Print the response to the console
            }

            // Step 4: Close the socket after receiving the response
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

