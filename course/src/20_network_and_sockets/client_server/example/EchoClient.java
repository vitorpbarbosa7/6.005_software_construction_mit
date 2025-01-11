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

/*
 *  * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
 *   *
 *    * Redistribution and use in source and binary forms, with or without
 *     * modification, are permitted provided that the following conditions
 *      * are met:
 *       *
 *        *   - Redistributions of source code must retain the above copyright
 *         *     notice, this list of conditions and the following disclaimer.
 *          *
 *           *   - Redistributions in binary form must reproduce the above copyright
 *            *     notice, this list of conditions and the following disclaimer in the
 *             *     documentation and/or other materials provided with the distribution.
 *              *
 *               *   - Neither the name of Oracle or the names of its
 *                *     contributors may be used to endorse or promote products derived
 *                 *     from this software without specific prior written permission.
 *                  *
 *                   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 *                    * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 *                     * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *                      * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 *                       * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *                        * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *                         * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *                          * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 *                           * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *                            * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *                             * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *                              */ 

