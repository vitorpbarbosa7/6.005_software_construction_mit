/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collections;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<String> {
    
    // vertices represented as strings in this set
    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();
    
    // Abstraction function:
    //      AF(vertices, edges) = Represents a Weighted Directed Graph with vertices and edges connecting the vertices
    //      vertices: set of nodes in G
    //      edges: set of weighted directed positive edges in G
    //      Each Edge in G represents a vertex (source) directed to another vertex (target) with a specific weight
    // Representation invariant:
    //      Edges are immutable, therefore also no duplicate edges
    //      Weights are positive
    //      Vertices and Edges are final fields
    //      Every vertice in the edges are found in the vertice
    // Safety from Rep Exposure:
    //      vertices and edges are private, so not accessed directly from outside the class
    //      With immutable Edges, a client can't mutate the Graph without using only the implemented Graphs
    //      Edges and vertices being final, avoid side effects of reassignments
    //      Observers which return vertices and edges make defensive copies

    
    // constructor
    public ConcreteEdgesGraph() {
        checkRep();
    }
   
    // constructor, create empty graph
    public static  Graph<String> empty() {
        return new ConcreteEdgesGraph();
    }

    // checkRep
    private void checkRep(){
        Set<String> seenEdges = new HashSet<>();
        for (Edge edge: edges){
            // Check weights are positive
            assert edge.getWeight() > 0;

            // For every edge, the vertices must contain the 
            assert vertices.contains(edge.getSource());
            assert vertices.contains(edge.getTarget());

            // No duplicate edges (since they are unique, immutable)
            String edgeKey = edge.getSource() + "->" + edge.getTarget();
            assert !seenEdges.contains(edgeKey);
            seenEdges.add(edgeKey);
        }
    }
    
    // mutator, so call the checkRep
    @Override public boolean add(String vertex) {
        boolean added_bool = vertices.add(vertex);
        checkRep();
        return added_bool;
    }

    // mutator, so call the checkRep 
    @Override public int set(String source, String target, int weight) {
        int return_weight = -1;

        // construct local edge?
        Edge newEdge = new Edge(source, target, weight);

        for (Edge edge: edges) {
            if (newEdge.similar(edge)) {
                // if equal weight, return weight without doing nothing
                if (edge.getWeight() == newEdge.getWeight()) {
                    return_weight = weight;
                }
                else {
                    // keep old weight to return 
                    int old_weight = edge.getWeight();
                    // must remove the old edge and add new edge
                    edges.remove(edge);
                    edges.add(newEdge);
                    return_weight = old_weight;
                }    
            }
        }
        
        // if return weight did not changed its value, so this guy did not exist
        if (return_weight == -1) {
            // if they do not exist in the vertices, add, and add the edge to the edge list
            vertices.add(source);
            vertices.add(target);
            edges.add(newEdge);
            // if reaches here, so no similar edge was found, so return 0
            return_weight = 0;
        }

        checkRep();
        return return_weight;

    }
    
    // mutator, so call the checkRep
    @Override public boolean remove(String vertex) {

        // remove the vertex only
        boolean removed = vertices.remove(vertex);
        // now remove the edges with this vertex
        if (removed){
            Iterator<Edge> iterator = edges.iterator();
            while(iterator.hasNext()){
                Edge edge = iterator.next();
                String localSource = edge.getSource();
                String localTarget = edge.getTarget();
                if (vertex.equals(localSource) || vertex.equals(localTarget)) {
                    iterator.remove(); 
                }
            }
        }
        
        checkRep();
        return removed;
        
    }
    
    // observer
    @Override public Set<String> vertices() {
        return Collections.unmodifiableSet(this.vertices);
    }

    // observer 
    @Override public Map<String, Integer> sources(String target) {
        Map<String, Integer> mapTarget = new HashMap<>();
        for (Edge edge: edges) {
            if (edge.getTarget().equals(target)) {
                Integer localWeight = edge.getWeight();
                String localSource = edge.getSource();
                mapTarget.put(localSource, localWeight); 
            }
        }

        return mapTarget;
    }
    
    // observer 
    @Override public Map<String, Integer> targets(String source) {
        Map<String, Integer> mapSource = new HashMap<>();
        for (Edge edge: edges) {
            if (edge.getSource().equals(source)) {
                Integer localWeight = edge.getWeight();
                String localTarget = edge.getTarget();
                mapSource.put(localTarget, localWeight);
            }
        }

        return mapSource;
    }
        
    // Like a Dunder method
    // Overriding from Object
    @Override
    public String toString() {
        String graphString = "";
        for (Edge edge: edges){            
            graphString = graphString + edge.toString() + "\n";
        }
        return graphString;

    }
    
}