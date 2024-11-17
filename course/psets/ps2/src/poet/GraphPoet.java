/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;

import java.io.File;
import java.io.IOException;

import graph.ConcreteVerticesGraph;
import graph.ConcreteEdgesGraph;
import graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */

public class GraphPoet {

    private final Graph<String> graphCorpus;
    private final List<String> corpusWords;

    /**
     * Create a new poet with the graph from corpus (as described above).
     *
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        this.graphCorpus = Graph.empty();
        this.corpusWords = CorpusReader.readCorpus(corpus.getPath());
        genGraph(this.corpusWords);
        checkRep();
    }

    private void checkRep() {
        assert graphCorpus != null : "graphCorpus must not be null";
        assert corpusWords != null : "corpusWords must not be null";

        for (String word : corpusWords) {
            assert word != null && !word.isEmpty() : "All words in corpusWords must be non-empty";
        }
    }

    /**
     * Generate a poem.
     *
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        // Split the input into words while preserving punctuation
        List<String> inputWords = CorpusReader.readText(input);
    
        if (inputWords.isEmpty()) {
            return input; // Return the original input if no words are present
        }
    
        Map<String, SimpleEntry<String, Integer>> bridgeMap = new HashMap<>();
    
        // Create a case-insensitive map for input words
        List<String> lowerInputWords = new ArrayList<>();
        for (String word : inputWords) {
            lowerInputWords.add(word.toLowerCase());
        }
    
        for (int i = 0; i < lowerInputWords.size() - 1; i++) {
            String sourceWord = lowerInputWords.get(i);
            String targetWord = lowerInputWords.get(i + 1);
    
            Map<String, Integer> targetsLevel1 = graphCorpus.targets(sourceWord);
            for (String bridgeWord : targetsLevel1.keySet()) {
                Map<String, Integer> targetsLevel2 = graphCorpus.targets(bridgeWord);
                if (targetsLevel2.containsKey(targetWord)) {
                    int weight = targetsLevel1.get(bridgeWord) + targetsLevel2.get(targetWord);
                    bridgeMap.put(sourceWord + "_" + targetWord,
                            new SimpleEntry<>(bridgeWord, weight));
                }
            }
        }
    
        StringBuilder output = new StringBuilder();
    
        for (int i = 0; i < inputWords.size() - 1; i++) {
            String sourceWord = inputWords.get(i);
            String targetWord = inputWords.get(i + 1);
    
            // Add the original source word to the output
            output.append(sourceWord);
    
            String key = sourceWord.toLowerCase() + "_" + targetWord.toLowerCase();
            if (bridgeMap.containsKey(key)) {
                // Add the bridge word in lowercase
                output.append(" ").append(bridgeMap.get(key).getKey());
            }
    
            output.append(" "); // Add space between words
        }
    
        // Add the final word to the output
        output.append(inputWords.get(inputWords.size() - 1));
    
        return output.toString().trim();
    }
    

    private void genGraph(List<String> words) {
        for (int i = 0; i < words.size() - 1; i++) {
            String source = words.get(i);
            String target = words.get(i + 1);
            int currentWeight = graphCorpus.set(source, target, 1);
            graphCorpus.set(source, target, currentWeight + 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("GraphPoet Corpus Representation:\n");
    
        // Add vertices
        result.append("Vertices:\n");
        for (String vertex : graphCorpus.vertices()) {
            result.append("- ").append(vertex).append("\n");
        }
    
        // Add edges
        result.append("Edges:\n");
        for (String source : graphCorpus.vertices()) {
            Map<String, Integer> targets = graphCorpus.targets(source);
            for (Map.Entry<String, Integer> entry : targets.entrySet()) {
                String target = entry.getKey();
                int weight = entry.getValue();
                result.append(source)
                      .append(" -> ")
                      .append(target)
                      .append(" (weight: ")
                      .append(weight)
                      .append(")\n");
            }
        }
    
        return result.toString();
    }
    

    //main
    public static void main(String args[]) {

        String filenameCorpus = "src/poet/corpus.txt";
        File fileCorpus = new File(filenameCorpus);
        // where is my corpus, oh my god
        // System.out.println("Current working directory: " + System.getProperty("user.dir"));
        try {
            GraphPoet poet = new GraphPoet(fileCorpus);
            String filenameInput = "src/poet/input.txt";
            String outputPoem = poet.poem(filenameInput);
            System.out.println(outputPoem);
        } catch (IOException e) {
            System.err.println("Error reading the corpus file: " + e.getMessage());
        }
        

    }
    
}
