/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper.server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import minesweeper.Board;
import minesweeper.Constants;

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
    private final int sizeY;
    private final int sizeX;
    private final List<Integer> xBombPositions;
    private final List<Integer> yBombPositions;

    /** The board */
    public final Board board;

    private static final ConcurrentHashMap<Thread, String> activeThreads = new ConcurrentHashMap<>();

    // TODO: Abstraction function, rep invariant, rep exposure

    /**
     * Make a MinesweeperServer that listens for connections on port.
     * 
     * @param port port number, requires 0 <= port <= 65535
     * @param debug debug mode flag
     * @throws IOException if an error occurs opening the server socket
     */
    public MinesweeperServer(
        int port, 
        boolean debug, 
        int sizeY, 
        int sizeX, 
        List<Integer> xBombPositions,
        List<Integer> yBombPositions) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.debug = debug;
        this.sizeY = sizeY;
        this.sizeX = sizeX;
        this.xBombPositions = xBombPositions;
        this.yBombPositions = yBombPositions;
        
        this.board = new Board(
            this.sizeX, 
            this.sizeY, 
            this.xBombPositions, 
            this.yBombPositions);
    }

    /*################################################ */
    // * beginning of Runnable to be executed */
    public class MinesweeperServerRunnable implements Runnable {
        private final Socket clientSocket;
        private final Board board;
        private final boolean debug; 

        public MinesweeperServerRunnable(Socket clientSocket, Board board, boolean debug) { 
            this.clientSocket = clientSocket;
            this.board = board;
            this.debug = debug;

        }

        @Override 
        public void run() { 
            
            try {
                // from the server to the client
                handleConnection(this.clientSocket);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    // when removing threads, should be able to only one at a time
                    // coarsed?
                    synchronized (this) {
                    // removing current threads as it closed
                        activeThreads.remove(Thread.currentThread());
                        this.clientSocket.close();
                    }
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

            String helloMessage = displayHelloMessage();
            out.println(helloMessage);

            try {
                for (String line = in.readLine(); line != null; line = in.readLine()) {
                    // input from cliente received with success, what to do?
                    String output = handleRequest(line);
                    out.println(output);
                    
                    // handles close the connection after some conditions
                    if (this.debug == false & output == Constants.BOOM) {
                        String messageOut = "You lost, connection will be closed";
                        out.println(messageOut);
                        clientSocket.close();
                    }
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

        private synchronized String handleRequest(String input) throws IOException{
            String regex = "(look)|(help)|(bye)|"
                        + "(dig -?\\d+ -?\\d+)|(flag -?\\d+ -?\\d+)|(deflag -?\\d+ -?\\d+)";
            if ( ! input.matches(regex)) {
                System.out.println(" no command");
                return Constants.HELP_MESSAGE;
                // invalid input
                // TODO Problem 5
            }
            String[] tokens = input.split(" ");
            if (tokens[0].equals("look")) {
                System.out.println("\n Content: \n " + this.board.debugContent()+"\n");
                return this.board.returnBoard();
                // 'look' request
                // TODO Problem 5
            } else if (tokens[0].equals("help")) {
                System.out.println(" help!");
                return Constants.HELP_MESSAGE;
                // 'help' request
                // TODO Problem 5
            } else if (tokens[0].equals("bye")) {
                System.out.println(" bye!");
                this.clientSocket.close();
                // 'bye' request
                // TODO Problem 5
            } else {
                int y = Integer.parseInt(tokens[1]);
                int x = Integer.parseInt(tokens[2]);
                if (tokens[0].equals("dig")) {
                    // System.out.println(this.board.debugContent());
                    return this.board.dig(y,x);
                    // 'dig x y' request
                    // TODO Problem 5
                } else if (tokens[0].equals("flag")) {
                    System.out.println(" flag");
                    return this.board.flag(y,x);
                    // 'flag x y' request
                    // TODO Problem 5
                } else if (tokens[0].equals("deflag")) {
                    System.out.println(" deflag");
                    return this.board.deflag(y,x);
                    // 'deflag x y' request
                    // TODO Problem 5
                } else {
                    return Constants.HELP_MESSAGE;
                }
            }
            // TODO: Should never get here, make sure to return in each of the cases above
            throw new UnsupportedOperationException();
        }
    }
    /*################################################ */

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
            Runnable clientHandler = new MinesweeperServerRunnable(clientSocket, this.board, this.debug);
            Thread clientThread = new Thread(clientHandler);

            synchronized (this) {
                activeThreads.put(clientThread, clientSocket.getRemoteSocketAddress().toString());
            }

            clientThread.start();

        }
    }

    private String displayHelloMessage() {
        int threadCount = this.getActiveThreadCount();
        String helloMessage =  Constants.HELLO_MESSAGE;
        String helloMessageRendered = String.format(
            helloMessage, board.getSizeX(), board.getSizeY(), threadCount);

        return helloMessageRendered;
    }

    // Method to get the number of active threads
    private int getActiveThreadCount() {
        synchronized (this){
            return activeThreads.size();
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
                        //file and other sizes not specified together
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
        List<Integer> xBombPositions = new ArrayList<>();
        List<Integer> yBombPositions = new ArrayList<>();

        if (file.isPresent()) {
            int matrix[][] = readFile(file.get().getAbsolutePath());
            // indirect way to get it
            sizeY = matrix.length; // rows
            sizeX = matrix[0].length; // columns

            // number of rows 
            for (int j = 0; j < matrix.length; j ++) {
                // number of columns
                for (int i = 0; i < matrix[j].length; i ++) {
                    if (matrix[j][i] == 1) {
                        yBombPositions.add(j);
                        xBombPositions.add(i);
                    }
                }
            }
        }

        else {
            System.out.println("passou aqui random");

            // generate at random
            int upperBound = 12;
            int lowerBound = 8;
            Random random = new Random();
            sizeY = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            sizeX = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

            int numOfCells = sizeY*sizeX;
            int lowerBoundPctBombs = 12;
            int upperBoundPctBombs = 20;
            int pctOfCells = random.nextInt(upperBoundPctBombs + 1) + lowerBoundPctBombs;
            // integer division for those types here
            int numberOfCellsBombs = (int) Math.round(numOfCells * (pctOfCells / 100.0)); // Ensure floating-point division
            // System.out.println("Number of Cell Bombs: " + numberOfCellsBombs);
            
            String position;
            Set<String> bombPositions = new HashSet<>();
            int yPositionBomb;
            int xPositionBomb;
            for (int k = 0; k < numberOfCellsBombs; k++) {
                yPositionBomb = random.nextInt(sizeY);
                xPositionBomb = random.nextInt(sizeX);
                position = yPositionBomb + "," + xPositionBomb;
                if (bombPositions.add(position)) {
                    yBombPositions.add(yPositionBomb);
                    xBombPositions.add(xPositionBomb);
                }
            }

            // System.out.println("Bomb Positions: " + yBombPositions + "\n " + xBombPositions);
            // System.out.println("Sizes: " + sizeY + "," + sizeX);
        }

        MinesweeperServer server = new MinesweeperServer(
            port, debug, sizeY, sizeX, xBombPositions, yBombPositions);
        server.serve();
    }

    public static int[][] readFile(String fileName) throws IOException{

        int[][] matrix = null;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            // Read the dimensions (first line)
            String dimensions = br.readLine();
            String[] size = dimensions.split(" ");
            int cols = Integer.parseInt(size[0]);
            int rows = Integer.parseInt(size[1]);


            // Read the matrix
            matrix = new int[rows][cols];
            for (int j = 0; j < rows; j++) {
                // each time readLine is called, it reads the next line, as the one before was already read
                String line = br.readLine();

                if (line != null) {
                    String[] values = line.split(" "); // Split by space
                    for (int i = 0; i < cols; i++) {
                        matrix[j][i] = Integer.parseInt(values[i]);
                    }
                }
            }

            System.out.println(matrix);
            
            return matrix;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matrix;
    }

}
