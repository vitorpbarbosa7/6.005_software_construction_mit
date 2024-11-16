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
                words.addAll(parseWords(line));
            }
        }
        return words;
    }

    /**
     * Processes a raw string input and returns a list of words.
     * Words are case-insensitive, non-empty, and defined as sequences of non-space, non-newline characters.
     *
     * @param text the raw string input
     * @return a list of words in lowercase, in the order they appear in the text
     */
    public static List<String> readText(String text) {
        List<String> words = new ArrayList<>();
        String[] lines = text.split("\\R"); // Split into lines based on line breaks
        for (String line : lines) {
            words.addAll(parseWords(line));
        }
        return words;
    }

    /**
     * Helper method to parse a line into words based on whitespace and other rules.
     *
     * @param line the line to parse
     * @return a list of parsed words
     */
    private static List<String> parseWords(String line) {
        List<String> words = new ArrayList<>();
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
        return words;
    }

    public static void main(String[] args) {
        try {
            // Example: Read words from a file
            List<String> corpusWords = CorpusReader.readCorpus("src/poet/corpus.txt");
            System.out.println("From file: " + corpusWords);

            // Example: Read words from a raw string
            String text = "Hello world! This is a test.";
            List<String> textWords = CorpusReader.readText(text);
            System.out.println("From text: " + textWords);
        } catch (IOException e) {
            System.err.println("Error reading the corpus file: " + e.getMessage());
        }
    }
}
