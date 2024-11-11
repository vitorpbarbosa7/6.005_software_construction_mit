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
        Edge newEdge = new Edge(source, target, weight);

        for (Edge edge: edges) {
            if (newEdge.similar(edge)) {
                // if equal weight, return weight without doing nothing
                if (edge.getWeight() == newEdge.getWeight()) {
                    return weight;
                }
                else {
                    // keep old weight to return 
                    int old_weight = edge.getWeight();
                    // must remove the old edge and add new edge
                    edges.remove(edge);
                    edges.add(newEdge);
                    return old_weight;
                }    
            }
        }

        // if they do not exist in the vertices, add, and add the edge to the edge list
        vertices.add(source);
        vertices.add(target);
        edges.add(newEdge);
        // if reaches here, so no similar edge was found, so return 0
        return 0;
    }
    
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
                    edges.remove(edge);
                }
            }
        }
        
        return removed;
        
    }
    
    @Override public Set<String> vertices() {
        return Collections.unmodifiableSet(this.vertices);
    }

    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }
    
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
    
    // TODO toString()
    
}