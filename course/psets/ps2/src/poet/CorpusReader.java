package poet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CorpusReader {
    /**
     * Reads the corpus file and returns a list of words.
     * Words are case-insensitive, non-empty, and defined as sequences of non-space, non-newline characters.
     *
     * @param fileName the path to the corpus file
     * @return a list of words in lowercase, in the order they appear in the file
     * @throws IOException if there is an issue reading the file
     */
    public static List<String> readCorpus(String fileName) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                // Split the line by whitespace (spaces, tabs, newlines) to get words
                String[] tokens = line.split("\\s+");

                for (String token : tokens) {
                    // Remove leading and trailing spaces and convert to lowercase
                    token = token.trim().toLowerCase();

                    // Only add non-empty tokens
                    if (!token.isEmpty()) {
                        words.add(token);
                    }
                }
            }
        }
        return words;
    }

    public static void main(String[] args) {
        try {
            List<String> corpusWords = CorpusReader.readCorpus("poet/corpus.txt");
            System.out.println(corpusWords);
        } catch (IOException e) {
            System.err.println("Error reading the corpus file: " + e.getMessage());
        }
    }
}
