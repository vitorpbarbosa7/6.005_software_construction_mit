import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println("Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];  // Host name of the server to connect to
        int portNumber = Integer.parseInt(args[1]);  // Port number to connect to

        try (
            Socket echoSocket = new Socket(hostName, portNumber);  // Create a socket connection to the server
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true); // Output stream for sending data to the server
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));  // Input stream for reading data from the server
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)) // Read user input from the console
        ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) { // Keep reading user input until null (i.e., user presses Ctrl+D or enters empty line)
                out.println(userInput);  // Send input to the server
                System.out.println("echo: " + in.readLine());  // Print the echoed message from the server
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}

