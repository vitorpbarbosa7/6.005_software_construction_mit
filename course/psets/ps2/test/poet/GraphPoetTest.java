package poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class GraphPoetTest {
    
    // Testing strategy:
    // - Initialization: valid and invalid corpus files.
    // - Graph representation: verify vertices and edge weights.
    // - Poem generation:
    //   - No bridge words.
    //   - Single bridge word.
    //   - Multiple possible bridge words.
    //   - Case-insensitivity.
    //   - Empty corpus or input.
    // - Edge cases: special characters, whitespace-only input, etc.

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // ensure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testValidCorpus() throws IOException {
        File corpus = new File("src/poet/corpus.txt");
        GraphPoet poet = new GraphPoet(corpus);

        // Verify that graphCorpus is correctly initialized.
        List<String> words = CorpusReader.readCorpus("src/poet/corpus.txt");
        assertNotNull(words);
        assertFalse(words.isEmpty());
    }

    @Test(expected = IOException.class)
    public void testInvalidCorpusFile() throws IOException {
        File invalidCorpus = new File("nonexistent_file.txt");
        new GraphPoet(invalidCorpus);
    }

    @Test
    public void testGraphRepresentation() throws IOException {
        File corpus = new File("src/poet/corpus.txt");
        GraphPoet poet = new GraphPoet(corpus);

        // Assuming a corpus "To explore strange new worlds"
        String[] expectedVertices = {"to", "explore", "strange", "new", "worlds"};
        for (String vertex : expectedVertices) {
            assertTrue(poet.toString().contains(vertex));
        }

        // Verify edges (e.g., "to -> explore" with weight 1)
        // assertTrue(poet.toString().contains("to -> explore"));
    }

    @Test
    public void testNoBridgeWords() throws IOException {
        File corpus = new File("src/poet/corpus.txt");
        GraphPoet poet = new GraphPoet(corpus);

        String input = "Hello world!";
        String expectedOutput = "Hello world!";
        assertEquals(expectedOutput, poet.poem(input));
    }

    @Test
    public void testSingleBridgeWord() throws IOException {
        File corpus = new File("src/poet/corpus.txt");
        GraphPoet poet = new GraphPoet(corpus);

        // Assuming a corpus with "To explore strange new worlds"
        String input = "Explore new";
        String expectedOutput = "Explore strange new"; // "strange" bridges "Explore" and "new"
        assertEquals(expectedOutput, poet.poem(input));
    }

    @Test
    public void testMultipleBridgeWords() throws IOException {
        File corpus = new File("src/poet/corpus_explore_test.txt");
        GraphPoet poet = new GraphPoet(corpus);

        // Assuming a corpus with multiple paths between input words
        String input = "To worlds";
        String expectedOutput = "To explore worlds"; // "explore" is the bridge word with max weight
        assertEquals(expectedOutput, poet.poem(input));
    }

    @Test
    public void testCaseInsensitiveCorpusAndInput() throws IOException {
        File corpus = new File("src/poet/corpus.txt");
        GraphPoet poet = new GraphPoet(corpus);

        // Assuming a corpus "To Explore strange NEW worlds"
        String input = "explore new";
        String expectedOutput = "explore strange new";
        assertEquals(expectedOutput, poet.poem(input));
    }

    @Test
    public void testEmptyCorpus() throws IOException {
        File emptyCorpus = new File("src/poet/empty_corpus.txt");
        GraphPoet poet = new GraphPoet(emptyCorpus);

        String input = "This is a test.";
        assertEquals("This is a test.", poet.poem(input));
    }

    @Test
    public void testEmptyInput() throws IOException {
        File corpus = new File("src/poet/corpus.txt");
        GraphPoet poet = new GraphPoet(corpus);

        String input = "";
        assertEquals("", poet.poem(input));
    }

    @Test
    public void testSpecialCharacters() throws IOException {
        File corpus = new File("src/poet/corpus.txt");
        GraphPoet poet = new GraphPoet(corpus);

        String input = "Hello, world!";
        String expectedOutput = "Hello, world!";
        assertEquals(expectedOutput, poet.poem(input));
    }

    @Test
    public void testWhitespaceInput() throws IOException {
        File corpus = new File("src/poet/corpus.txt");
        GraphPoet poet = new GraphPoet(corpus);

        String input = "";
        assertEquals("", poet.poem(input));
    }
}
