import java.io.*;
import java.net.*;

public class EchoClient{
  public static void main(String[] args) throws IOException {

    if (args.length != 2) {
      // args should be two, because we need host and port
      System.err.println(
          "Usage: java EchoClient <host name> <port number>");
      System.exit(1);
    }
    
    // get args
    String hostName = args[0];
    int portNumber = Integer.parseInt(args[1]);

    try { 
      // create socket to receive and send data
      // only socket is a client socket
      // socket is low level object that connects and receive data
      // writer is a high level module to wrap the low level, and makes easier to interact 
      // allow us to send data using println (human readable one), which converts bytes to strings
      Socket echoSocket = new Socket(hostName, portNumber);
      PrintWriter out = 
        new PrintWriter(echoSocket.getOutputStream(), true);
      // now to receive data
      // socket gets the byte byte byte stream
      // InputStreamReader converts bytes to characters
      // BufferedReader makes possible to use the buffer here
      BufferedReader in = 
        new BufferedReader(
            new InputStreamReader(echoSocket.getInputStream()));
      // To receive from Command Line, ok, beautiful
      BufferedReader stdIn = 
        new BufferedReader(
            new InputStreamReader(System.in));

      String userInput;
      // reads any input from user from command line
      // using stdIn
      while ((userInput = stdIn.readLine()) != null) {
        // sends to server
        out.println(userInput);
        // received from server
        System.out.println("echo: " + in.readLine());
      }
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host " + hostName);
      System.exit(1);
    } catch (IOException e){
      System.err.println("Couldn't get I/O for the connection to " + hostName);
      System.exit(1);
    }
  }
}







    }
      
