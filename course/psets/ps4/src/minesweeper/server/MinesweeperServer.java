/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper.server;

import java.io.*;
import java.net.*;
import java.util.*;

import minesweeper.Board;

/**
 * Multiplayer Minesweeper server.
 */
public class MinesweeperServer {

    // System thread safety argument
    //   TODO Problem 5

    /** Default server port. */
    private static final int DEFAULT_PORT = 4444;
    /** Maximum port number as defined by ServerSocket. */
    private static final int MAXIMUM_PORT = 65535;
    /** Default square board size. */
    private static final int DEFAULT_SIZE = 4;

    /** Socket for receiving incoming connections. */
    private final ServerSocket serverSocket;
    /** True if the server should *not* disconnect a client after a BOOM message. */
    private final boolean debug;

    /** The board */
    public final Board board;

    // TODO: Abstraction function, rep invariant, rep exposure

    /**
     * Make a MinesweeperServer that listens for connections on port.
     * 
     * @param port port number, requires 0 <= port <= 65535
     * @param debug debug mode flag
     * @throws IOException if an error occurs opening the server socket
     */
    public MinesweeperServer(int port, boolean debug) throws IOException {
        serverSocket = new ServerSocket(port);
        this.debug = debug;

        this.board = new Board(DEFAULT_SIZE, DEFAULT_SIZE);
    }

    /**
     * Run the server, listening for client connections and handling them.
     * Never returns unless an exception is thrown.
     * 
     * @throws IOException if the main server socket is broken
     *                     (IOExceptions from individual clients do *not* terminate serve())
     */
    public void serve() throws IOException {
        while (true) {
            // block until a client connects
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            // handle the client
            // try {
            /* single thread way, let's try with multi thread */
            // handleConnection(clientSocket);

            Runnable clientHandler = new MinesweeperServerRunnable(clientSocket, this.board);

            Thread clientThread = new Thread(clientHandler);
            clientThread.start();

            // } catch (IOException ioe) {
            //     ioe.printStackTrace(); // but don't terminate serve()
            // } finally {
            //     clientSocket.close();
            // }
        }
    }

    public class MinesweeperServerRunnable implements Runnable {
        private Socket clientSocket;
        public Board board;

        public MinesweeperServerRunnable(Socket clientSocket, Board board) {
            System.out.println(" New connection stablished");
            this.clientSocket = clientSocket;
            this.board = board;
        }

        @Override 
        public void run() { 

            try {

                handleConnection(this.clientSocket);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                this.clientSocket.close();
                // closed
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        /**
         * Handle a single client connection. Returns when client disconnects.
         * 
         * @param clientSocket clientSocket where the client is connected
         * @throws IOException if the connection encounters an error or terminates unexpectedly
         */
        private void handleConnection(Socket clientSocket) throws IOException {
            // messages from the client, to be processed by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // messages of the server to be sent to the client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

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
                System.out.println(this.board.toString());
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

    public static void main(String[] args) {
        System.out.println("main started");
        // Command-line argument parsing is provided. Do not change this method.
        boolean debug = false;
        int port = DEFAULT_PORT;
        int sizeX = DEFAULT_SIZE;
        int sizeY = DEFAULT_SIZE;
        Optional<File> file = Optional.empty();

        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        try {
            while ( ! arguments.isEmpty()) {
                String flag = arguments.remove();
                try {
                    if (flag.equals("--debug")) {
                        debug = true;
                    } else if (flag.equals("--no-debug")) {
                        debug = false;
                    } else if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        if (port < 0 || port > MAXIMUM_PORT) {
                            throw new IllegalArgumentException("port " + port + " out of range");
                        }
                    } else if (flag.equals("--size")) {
                        String[] sizes = arguments.remove().split(",");
                        sizeX = Integer.parseInt(sizes[0]);
                        sizeY = Integer.parseInt(sizes[1]);
                        file = Optional.empty();
                    } else if (flag.equals("--file")) {
                        sizeX = -1;
                        sizeY = -1;
                        file = Optional.of(new File(arguments.remove()));
                        if ( ! file.get().isFile()) {
                            throw new IllegalArgumentException("file not found: \"" + file.get() + "\"");
                        }
                    } else {
                        throw new IllegalArgumentException("unknown option: \"" + flag + "\"");
                    }
                } catch (NoSuchElementException nsee) {
                    throw new IllegalArgumentException("missing argument for " + flag);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("unable to parse number for " + flag);
                }
            }
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.err.println("usage: MinesweeperServer [--debug | --no-debug] [--port PORT] [--size SIZE_X,SIZE_Y | --file FILE]");
            return;
        }

        try {
            System.out.println("port: " + port + " -- debug: " + debug + " -- SIZE_X: " + sizeX + " -- SIZE_Y: " + sizeY + " -- debug:" + debug);
            runMinesweeperServer(debug, file, sizeX, sizeY, port);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Start a MinesweeperServer running on the specified port, with either a random new board or a
     * board loaded from a file.
     * 
     * @param debug The server will disconnect a client after a BOOM message if and only if debug is false.
     * @param file If file.isPresent(), start with a board loaded from the specified file,
     *             according to the input file format defined in the documentation for main(..).
     * @param sizeX If (!file.isPresent()), start with a random board with width sizeX
     *              (and require sizeX > 0).
     * @param sizeY If (!file.isPresent()), start with a random board with height sizeY
     *              (and require sizeY > 0).
     * @param port The network port on which the server should listen, requires 0 <= port <= 65535.
     * @throws IOException if a network error occurs
     */
    public static void runMinesweeperServer(boolean debug, Optional<File> file, int sizeX, int sizeY, int port) throws IOException {

        // TODO: Continue implementation here in problem 4
        // if file we recreate the board, altering to other size
        
        MinesweeperServer server = new MinesweeperServer(port, debug);
        server.serve();
    }
}
