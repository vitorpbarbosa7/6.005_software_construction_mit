/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collections;

import java.util.Objects;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph implements Graph<String> {
    
    // vertices represented as strings in this set
    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    
    // TODO checkRep
    
    @Override public boolean add(String vertex) {
        return vertices.add(vertex);
    }
    
    @Override public int set(String source, String target, int weight) {

        // construct local edge?
        Edge localEdge = new Edge(source, target, weight);

        for (Edge edge: edges) {
            if (localEdge.similar(edge)) {
                // if equal weight, return weight without doing nothing
                if (edge.getWeight() == localEdge.getWeight()) {
                    return weight;
                }
                else {
                    // keep old weight to return 
                    int old_weight = edge.getWeight();
                    // must remove the old edge and add new edge
                    edges.remove(edge);
                    edges.add(localEdge);
                    return old_weight;
                }    
            }
        }
        // if reaches here, so no similar edge was found, so return 0
        return 0;
    }
    
    @Override public boolean remove(String vertex) {
        return vertices.remove(vertex);
    }
    
    @Override public Set<String> vertices() {
        return Collections.unmodifiableSet(this.vertices);
    }
    
    @Override public Map<String, Integer> sources(String target) {
        Map<String, Integer> mapTarget = new HashMap<>();
        for (Edge edge: edges) {
            if (edge.getTarget() == target) {
                Integer localWeight = edge.getWeight();
                String localSource = edge.getSource();
                mapTarget.put(localSource, localWeight); 
            }
        }

        return mapTarget;
    }
    
    @Override public Map<String, Integer> targets(String source) {
        Map<String, Integer> mapSource = new HashMap<>();
        for (Edge edge: edges) {
            if (edge.getSource() == source) {
                Integer localWeight = edge.getWeight();
                String localTarget = edge.getTarget();
                mapSource.put(localTarget, localWeight);
            }
        }

        return mapSource;
    }
    
    // TODO toString()
    
}