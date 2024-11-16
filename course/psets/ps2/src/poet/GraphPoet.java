/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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
    
    private final Graph<String> graphInput = Graph.empty();
    private final Graph<String> graphCorpus = Graph.empty();

    private final List<String> corpusWords;
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {

        // graphCorpus generate
        this.corpusWords = CorpusReader.readCorpus(corpus.getPath());
        genGraph(this.corpusWords, this.graphCorpus);
        // System.out.println(this.graphCorpus);

    }
    

    // TODO checkRep
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {

        List<String> inputWords = readWords(input);

        Map<String, Map<String, Integer>> bridgeMap = new HashMap<>();
        for (int i = 0; i< inputWords.size() - 1; i++) {
            // for each consecutive words, that is, source and target
            String sourceWord = inputWords.get(i);
            String targetWord = inputWords.get(i + 1);
            // get all targets from the sourceWord from input, from the Corpus
            Map<String, Integer> targetsLevel1 = this.graphCorpus.targets(sourceWord);
            // for each target from source, we must go a level deeper to find another targets
            for (String targetLevel1: targetsLevel1.keySet()) {
                Map<String, Integer> targetsLevel2 = this.graphCorpus.targets(targetLevel1);
                // from this second level of targets, we try to find a word that matches the targetWord
                for (String targetLevel2: targetsLevel2.keySet()) {
                    // if at this level we find our target, we can store it
                    if (targetLevel2.equals(targetWord)) {
                        Integer weightSumEdges = targetsLevel1.get(targetLevel1) + targetsLevel2.get(targetLevel2);
                        
                        String keyConcat = sourceWord + "_" + targetWord;
                        bridgeMap.putIfAbsent(keyConcat, new HashMap<>());
                            
                        // if this one has higher weight, we substitute it
                        // targetLevel1 is the bridgeWord
                        Integer previousWeight = bridgeMap.get(keyConcat).get(targetLevel1);
                        if (weightSumEdges >= previousWeight) {
                            bridgeMap.get(keyConcat).put(targetLevel1, weightSumEdges);
                        }

                    }
                }
            }
        }
        System.out.println(bridgeMap);

        // // modify the list, according to higher weight of w1-b-w2 bridge
        // for(int i = 0; i<inputWords.size() -1; i++) {
        //     String sourceWord = inputWords.get(i);
        //     String targetWord = inputWords.get(i+1);

        // }
        return input;


    }

    private List<String> readWords(String filename){
        File inputFile = new File(filename);
        try {
            List<String> words = CorpusReader.readCorpus(inputFile.getPath());
            return words;
        } catch (IOException e) {
            System.err.println("Could read file" + filename + "Exception: " + e);
            System.exit(1);
            return new ArrayList<>();
        }
    }

    private void genGraph(List<String> localCorpusWords, Graph<String> localGraph) {

        Map<String, Map<String, Integer>> preGraphCorpus = createPreGraph(localCorpusWords);

        for (Map.Entry<String, Map<String, Integer>> outerEntry : preGraphCorpus.entrySet()) {
            String outerKey = outerEntry.getKey(); // Outer key
            Map<String, Integer> innerMap = outerEntry.getValue(); // Inner map

            for (Map.Entry<String, Integer> innerEntry : innerMap.entrySet()) {
                String innerKey = innerEntry.getKey(); // Inner key
                Integer innerValue = innerEntry.getValue(); // Inner value
                
                // populate correctly the graph
                localGraph.set(outerKey, innerKey, innerValue);
            }
        }
    }
    /**
     * Creates a weighted adjacency list (pre-graph) from a list of vertices.
     *
     * @param vertices a list of strings representing the sequence of vertices
     * @return a map representing the adjacency structure
     */
    private  Map<String, Map<String, Integer>> createPreGraph(List<String> vertices) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        // Populate the graph based on consecutive pairs in the input
        for (int i = 0; i < vertices.size() - 1; i++) {
            String source = vertices.get(i);
            String target = vertices.get(i + 1);

            // Add the source vertex if it doesn't already exist
            graph.putIfAbsent(source, new HashMap<>());

            // Increment the weight of the edge from source to target
            Map<String, Integer> edges = graph.get(source);
            edges.put(target, edges.getOrDefault(target, 0) + 1);
        }

        return graph;
        }

    
    // TODO toString()



    //main
    public static void main(String args[]) {

        String filenameCorpus = "poet/corpus.txt";
        File fileCorpus = new File(filenameCorpus);
        // where is my corpus
        // System.out.println("Current working directory: " + System.getProperty("user.dir"));
        try {
            GraphPoet poet = new GraphPoet(fileCorpus);
            // where is my corpus oh my god

            String filenameInput = "poet/input.txt";
            String outputPoem = poet.poem(filenameInput);
        } catch (IOException e) {
            System.err.println("Error reading the corpus file: " + e.getMessage());
        }
        

    }
    
}
