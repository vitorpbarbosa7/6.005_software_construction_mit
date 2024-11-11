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
            if (localEdge.equals(edge)) {
                // if equal weight, return weight without doing nothing
                if (edge.getWeight() == localEdge.getWeight()) {
                    return weight;
                }
                else {
                    // if not equal weight, change weight, and return the old weight
                    int old_weight = edge.getWeight();
                    edge.changeWeight(weight);
                    return old_weight;
                }    
            }
            // if reaches here, so no equaledge was found, so return 0
            return 0;
        }


        throw new RuntimeException("not implemented");
    }
    
    @Override public boolean remove(String vertex) {
        throw new RuntimeException("not implemented");
    }
    
    @Override public Set<String> vertices() {
        throw new RuntimeException("not implemented");
    }
    
    @Override public Map<String, Integer> sources(String target) {
        throw new RuntimeException("not implemented");
    }
    
    @Override public Map<String, Integer> targets(String source) {
        throw new RuntimeException("not implemented");
    }
    
    // TODO toString()
    
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge {

    private final String source;
    private final String target;
    private int weight;
    
    // TODO fields
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    public Edge(String source, String target, int weight){
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    // TODO checkRep
    
    // TODO methods

    // observer
    public String getSource(){
        return this.source;
    }

    // observer
    public String getTarget(){
        return this.target;
    }

    // observer
    public int getWeight(){
        return this.weight;
    }

    // mutator
    public void changeWeight(int weight) {
        this.weight = weight;
    }

    @Override equals(Object Obj) {

    }
 
    
    // TODO toString()
    
}
