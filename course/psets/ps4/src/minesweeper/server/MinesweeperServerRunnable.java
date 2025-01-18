import java.net.*;
import java.io.*;

public class MinesweeperServerRunnable implements Runnable {
    private Socket socket;

    public MinesweeperServerRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override 
    public void run() { 

        try {

        // messages from the client, to be processed by the server
        BufferedReader in = new BufferedReader(new InputStreamReader(cilentSocket.getInputStream()));
        // messages of the server to be sent to the client
        PrintWriter out = new PrintWriter(cilentSocket.getOutputStream(), true);

        try {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                // input from cliente received with success, what to do?
                String output = handleRequest(line);
                if (output != null) {
                    // TODO: Consider improving spec of handleRequest to avoid use of null
                    out.println(output);
                }
            }
        } finally {
            out.close();
            in.close();
        }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
            socket.close();
            // closed
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}