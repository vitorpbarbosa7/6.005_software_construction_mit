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


    /**
     * Handle a single client connection. Returns when client disconnects.
     * 
     * @param cilentSocket socket where the client is connected
     * @throws IOException if the connection encounters an error or terminates unexpectedly
     */
    private void handleConnection(Socket cilentSocket) throws IOException {
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
    }

    private String handleRequest(String input) {
        String regex = "(look)|(help)|(bye)|"
                     + "(dig -?\\d+ -?\\d+)|(flag -?\\d+ -?\\d+)|(deflag -?\\d+ -?\\d+)";
        if ( ! input.matches(regex)) {
            System.out.println(" no command");
            // invalid input
            // TODO Problem 5
        }
        String[] tokens = input.split(" ");
        if (tokens[0].equals("look")) {
            System.out.println(" look!");
            System.exit(0);
            // 'look' request
            // TODO Problem 5
        } else if (tokens[0].equals("help")) {
            System.out.println(" help!");
            // 'help' request
            // TODO Problem 5
        } else if (tokens[0].equals("bye")) {
            System.out.println(" bye!");
            // 'bye' request
            // TODO Problem 5
        } else {
            int x = Integer.parseInt(tokens[1]);
            int y = Integer.parseInt(tokens[2]);
            if (tokens[0].equals("dig")) {
                System.out.println(" dig");
                // 'dig x y' request
                // TODO Problem 5
            } else if (tokens[0].equals("flag")) {
                System.out.println(" flag");
                // 'flag x y' request
                // TODO Problem 5
            } else if (tokens[0].equals("deflag")) {
                System.out.println(" deflag");
                // 'deflag x y' request
                // TODO Problem 5
            }
        }
        // TODO: Should never get here, make sure to return in each of the cases above
        throw new UnsupportedOperationException();
    }
}

