package poet;

import java.io.File;

public class ListFiles {
    public static void main(String[] args) {
        // Get the current working directory
        String workingDir = System.getProperty("user.dir");
        System.out.println("Current Working Directory: " + workingDir);

        // Create a File object for the current directory
        File currentDir = new File(workingDir);

        // List all files and directories recursively
        listFilesRecursively(currentDir, "");
    }

    public static void listFilesRecursively(File dir, String indent) {
        // Ensure the directory exists and is indeed a directory
        if (!dir.exists() || !dir.isDirectory()) {
            System.err.println("The directory does not exist or is not a directory: " + dir.getAbsolutePath());
            return;
        }

        // List all files and directories
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Print the directory name
                    System.out.println(indent + "[DIR] " + file.getName());

                    // Recurse into the subdirectory
                    listFilesRecursively(file, indent + "  ");
                } else {
                    // Print the file name
                    System.out.println(indent + "[FILE] " + file.getName());
                }
            }
        }
    }
}
