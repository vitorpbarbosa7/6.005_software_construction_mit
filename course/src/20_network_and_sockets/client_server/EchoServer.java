/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.net.*;
import java.io.*;

public class EchoServer {
    // Main method - entry point for the EchoServer
    public static void main(String[] args) throws IOException {
        
        // Ensure that the user has provided a port number as an argument
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1); // If not, display usage message and terminate
        }
        
        // Parse the port number from the command line argument
        int portNumber = Integer.parseInt(args[0]);
        
        // Try-with-resources block to automatically close resources when done
        try (
            // Create a server socket that listens on the provided port number
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
            // Accept incoming client connections and obtain the client socket
            Socket clientSocket = serverSocket.accept();
            // Create output stream for sending data to the client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                   
            // Create input stream for receiving data from the client
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
            // Declare a variable to hold the client input
            String inputLine;
            // Loop to read client input and send it back (echo) until the connection is closed
            while ((inputLine = in.readLine()) != null) {
                // Echo the input back to the client
                out.println(inputLine);
            }
        } catch (IOException e) {
            // If an error occurs, print the exception and the error message
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}

