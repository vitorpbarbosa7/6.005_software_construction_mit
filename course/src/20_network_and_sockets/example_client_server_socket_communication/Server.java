import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        // Step 1: Create a ServerSocket that listens on port 8080
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server is listening on port 8080...");
        
        // Accept connections from two clients
        Socket client1 = serverSocket.accept();
        System.out.println("Client 1 connected!");
        
        Socket client2 = serverSocket.accept();
        System.out.println("Client 2 connected!");
        
        // Step 2: Handle communication with both clients in separate threads
        Thread client1Handler = new Thread(new ClientHandler(client1, "Client 1"));
        Thread client2Handler = new Thread(new ClientHandler(client2, "Client 2"));
        
        client1Handler.start();
        client2Handler.start();
        
        // Let the server run for approximately 10 seconds before stopping
        Thread.sleep(10000); // 10 seconds
        
        // Closing the server
        client1.close();
        client2.close();
        serverSocket.close();
        System.out.println("Server stopped after 10 seconds.");
    }
    
    // ClientHandler is a Runnable that handles communication with a single client
    static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private String clientName;
        
        public ClientHandler(Socket clientSocket, String clientName) {
            this.clientSocket = clientSocket;
            this.clientName = clientName;
        }
        
        @Override
        public void run() {
            try {
                // Set up input and output streams for communication
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                
                // Send a greeting to the client
                out.println("Hello " + clientName + "! You are connected.");
                
                // Exchange data with the client for 10 seconds
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime < 10000) {
                    // Read message from client
                    String clientMessage = in.readLine();
                    if (clientMessage != null) {
                        System.out.println(clientName + " says: " + clientMessage);
                        out.println("Received: " + clientMessage);  // Echo the message back to the client
                    }
                }
                
                // Close the connection after 10 seconds
                System.out.println(clientName + " disconnected.");
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

