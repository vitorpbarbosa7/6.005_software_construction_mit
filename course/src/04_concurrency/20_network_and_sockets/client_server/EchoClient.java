/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
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

import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        
        // Check if the correct number of arguments (host and port) are passed
        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        // Retrieve the host name and port number from the arguments
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
            // Create a socket to connect to the server using the host and port
            Socket echoSocket = new Socket(hostName, portNumber);

            // Create a PrintWriter to send data to the server
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);

            // Create a BufferedReader to receive data from the server
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));

            // Create a BufferedReader to read user input from the console
            BufferedReader stdIn = new BufferedReader(
                    new InputStreamReader(System.in))
        ) {
            String userInput;
            
            // Continuously read user input from the console
            while ((userInput = stdIn.readLine()) != null) {
                // Send the user input to the server
                out.println(userInput);
                
                // Read and print the echo response from the server
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            // If the host is not found, print an error and exit
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            // If there is an I/O error while connecting, print an error and exit
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }
}

