/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   TODO
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph

    @Test
    public void testAdd() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.vertices();
        assertTrue(graph.vertices().contains("A"));
        graph.add("B");
        assertTrue(graph.vertices().contains("B"));
    }

    @Test
    public void testSet() {
        Graph<String> graph = emptyInstance();
        graph.set("A", "B", 1);
        Set<String> verticesExpected = new HashSet<>();
        verticesExpected.add("A");
        verticesExpected.add("B");
        assertEquals("Vertices are A and B", verticesExpected, graph.vertices());
    }

     @Test
    public void testAddVertex() {
        Graph<String> graph = emptyInstance();
        assertTrue("Adding a new vertex should return true", graph.add("A"));
        assertTrue("Vertex A should exist in the graph", graph.vertices().contains("A"));
        assertFalse("Adding the same vertex again should return false", graph.add("A"));
    }

    @Test
    public void testSetEdge() {
        Graph<String> graph = emptyInstance();

        // Add edge A -> B with weight 5
        int previousWeight = graph.set("A", "B", 5);
        assertEquals("Expected previous weight to be 0", 0, previousWeight);

        // Check vertices
        Set<String> expectedVertices = new HashSet<>();
        expectedVertices.add("A");
        expectedVertices.add("B");
        assertEquals("Vertices should include A and B", expectedVertices, graph.vertices());

        // Check edge
        Map<String, Integer> expectedTargets = new HashMap<>();
        expectedTargets.put("B", 5);
        assertEquals("Edge A -> B with weight 5 should exist", expectedTargets, graph.targets("A"));

        // Update edge A -> B to weight 10
        previousWeight = graph.set("A", "B", 10);
        assertEquals("Expected previous weight to be 5", 5, previousWeight);

        // Check updated edge
        expectedTargets.put("B", 10);
        assertEquals("Edge A -> B should now have weight 10", expectedTargets, graph.targets("A"));

        // Remove edge A -> B by setting weight to 0
        previousWeight = graph.set("A", "B", 0);
        assertEquals("Expected previous weight to be 10", 10, previousWeight);
        assertTrue("Edge A -> B should be removed", graph.targets("A").isEmpty());
    }

    @Test
    public void testRemoveVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);

        // Remove vertex A
        assertTrue("Removing vertex A should return true", graph.remove("A"));
        assertFalse("Vertex A should no longer exist", graph.vertices().contains("A"));

        // Check if edge A -> B is also removed
        assertTrue("Edges from A should be removed", graph.targets("A").isEmpty());
        assertTrue("Edges to A should be removed", graph.sources("A").isEmpty());
    }

    @Test
    public void testVertices() {
        Graph<String> graph = emptyInstance();
        assertEquals("Graph should initially have no vertices", Collections.emptySet(), graph.vertices());

        graph.add("A");
        graph.add("B");
        Set<String> expectedVertices = new HashSet<>();
        expectedVertices.add("A");
        expectedVertices.add("B");

        assertEquals("Graph should contain A and B", expectedVertices, graph.vertices());
    }

    @Test
    public void testSourcesAndTargets() {
        Graph<String> graph = emptyInstance();
        graph.set("A", "B", 5);
        graph.set("A", "C", 10);
        graph.set("D", "A", 7);

        // Check targets of A
        Map<String, Integer> expectedTargets = new HashMap<>();
        expectedTargets.put("B", 5);
        expectedTargets.put("C", 10);
        assertEquals("Targets of A should be B and C with respective weights", expectedTargets, graph.targets("A"));

        // Check sources of A
        Map<String, Integer> expectedSources = new HashMap<>();
        expectedSources.put("D", 7);
        assertEquals("Sources of A should be D with weight 7", expectedSources, graph.sources("A"));
    }
 
}
