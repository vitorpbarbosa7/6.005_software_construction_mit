package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override 
    public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    // Partition:
    // - Empty graph
    // - Graph with one edge
    // - Graph with multiple edges
    @Test
    public void testToString() {
        ConcreteEdgesGraph<String> graph = new ConcreteEdgesGraph<>();
        assertEquals("Expected empty graph representation", "", graph.toString());

        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        assertEquals("Expected graph with one edge", "(A) - 5 -> (B)\n", graph.toString());

        graph.set("B", "C", 3);
        assertTrue("Expected graph with multiple edges", graph.toString().contains("(A) - 5 -> (B)"));
        assertTrue("Expected graph with multiple edges", graph.toString().contains("(B) - 3 -> (C)"));
    }
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge:
    // - Valid edges with positive weights
    // - Invalid edges with negative weights
    // - Verify behavior of methods like getSource, getTarget, getWeight, similar, equals, hashCode, and toString
    
    @Test
    public void testEdgeConstructor() {
        Edge<String> edge = new Edge<>("A", "B", 5);
        assertEquals("Expected source to be A", "A", edge.getSource());
        assertEquals("Expected target to be B", "B", edge.getTarget());
        assertEquals("Expected weight to be 5", 5, edge.getWeight());
    }
    
    @Test(expected = AssertionError.class)
    public void testEdgeConstructorInvalidWeight() {
        new Edge<>("A", "B", -1); // Should throw an exception
    }
    
    @Test
    public void testEdgeSimilar() {
        Edge<String> edge1 = new Edge<>("A", "B", 5);
        Edge<String> edge2 = new Edge<>("A", "B", 10); // Different weight
        assertTrue("Edges with same source and target should be similar", edge1.similar(edge2));
    }
    
    @Test
    public void testEdgeEquals() {
        Edge<String> edge1 = new Edge<>("A", "B", 5);
        Edge<String> edge2 = new Edge<>("A", "B", 5);
        Edge<String> edge3 = new Edge<>("A", "C", 5);
        assertTrue("Edges with same source, target, and weight should be equal", edge1.equals(edge2));
        assertFalse("Edges with different targets should not be equal", edge1.equals(edge3));
    }
    
    @Test
    public void testEdgeHashCode() {
        Edge<String> edge1 = new Edge<>("A", "B", 5);
        Edge<String> edge2 = new Edge<>("A", "B", 5);
        assertEquals("Equal edges should have the same hash code", edge1.hashCode(), edge2.hashCode());
    }
    
    @Test
    public void testEdgeToString() {
        Edge<String> edge = new Edge<>("A", "B", 5);
        assertEquals("Expected string representation of edge", "(A) - 5 -> (B)", edge.toString());
    }
}
