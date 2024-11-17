/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.io.File;
import java.io.IOException;

/**
 * Example program using GraphPoet.
 * 
 * <p>PS2 instructions: you are free to change this example class.
 */
public class Main {
    
    /**
     * Generate example poetry.
     * 
     * @param args unused
     * @throws IOException if a poet corpus file cannot be found or read
     */
    public static void main(String[] args) throws IOException {
        // System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
        // File currentDir = new File("."); // "." means the current directory
        // // Call the static method from ListFiles class
        // System.out.println("Listing all files and directories recursively:");
        // ListFiles.listFilesRecursively(currentDir, "");

        String filename = "src/poet/fuckingfile.txt";
        File fileCorpus = new File(filename);
        final GraphPoet graphpoet = new GraphPoet(fileCorpus);
        final String input = "Test the system.";
        System.out.println(input + "\n>>>\n" + graphpoet.poem(input));

        System.out.println(graphpoet.toString());
    }
    
}
