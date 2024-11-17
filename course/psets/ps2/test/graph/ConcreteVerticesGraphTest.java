package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph,
 * as well as tests for that particular implementation.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {

    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<>();
    }

    // Test adding a vertex
    @Test
    public void testAddVertex() {
        ConcreteVerticesGraph<String> graph = new ConcreteVerticesGraph<>();
        assertTrue(graph.add("A"));
        assertTrue(graph.vertices().contains("A"));
        assertFalse(graph.add("A")); // Adding again shouldn't work
    }

    // Test setting edges
    @Test
    public void testSetEdge() {
        ConcreteVerticesGraph<String> graph = new ConcreteVerticesGraph<>();
        assertEquals(0, graph.set("A", "B", 5)); // New edge
        assertEquals(5, graph.set("A", "B", 10)); // Update edge
        assertEquals(10, graph.set("A", "B", 0)); // Remove edge
    }

    // Test removing a vertex
    @Test
    public void testRemoveVertex() {
        ConcreteVerticesGraph<String> graph = new ConcreteVerticesGraph<>();
        graph.set("A", "B", 5);
        graph.set("B", "C", 10);
        assertTrue(graph.remove("B"));
        assertFalse(graph.vertices().contains("B"));
        assertEquals(Collections.emptyMap(), graph.targets("A"));
        assertEquals(Collections.emptyMap(), graph.sources("C"));
    }

    // Test vertices method
    @Test
    public void testVertices() {
        ConcreteVerticesGraph<String> graph = new ConcreteVerticesGraph<>();
        assertEquals(Collections.emptySet(), graph.vertices());
        graph.add("A");
        graph.add("B");
        Set<String> expected = new HashSet<>();
        expected.add("A");
        expected.add("B");
        assertEquals(expected, graph.vertices());
    }

    // Test sources
    @Test
    public void testSources() {
        ConcreteVerticesGraph<String> graph = new ConcreteVerticesGraph<>();
        graph.set("A", "B", 5);
        graph.set("C", "B", 10);
        Map<String, Integer> sources = graph.sources("B");
        assertEquals(2, sources.size());
        assertEquals(5, (int) sources.get("A"));
        assertEquals(10, (int) sources.get("C"));
    }

    // Test targets
    @Test
    public void testTargets() {
        ConcreteVerticesGraph<String> graph = new ConcreteVerticesGraph<>();
        graph.set("A", "B", 5);
        graph.set("A", "C", 10);
        Map<String, Integer> targets = graph.targets("A");
        assertEquals(2, targets.size());
        assertEquals(5, (int) targets.get("B"));
        assertEquals(10, (int) targets.get("C"));
    }

    // Test toString
    @Test
    public void testToString() {
        ConcreteVerticesGraph<String> graph = new ConcreteVerticesGraph<>();
        graph.set("A", "B", 5);
        graph.set("B", "C", 10);
        String graphString = graph.toString();
        assertTrue(graphString.contains("A:: (B: 5)"));
        assertTrue(graphString.contains("B:: (C: 10)"));
    }
}
