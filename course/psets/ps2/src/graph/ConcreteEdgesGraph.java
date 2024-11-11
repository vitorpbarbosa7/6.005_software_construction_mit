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
    private final List<ConcreteEdge> edges = new ArrayList<>();
    
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
        ConcreteEdge localEdge = new ConcreteEdge(source, target, weight);

        for (ConcreteEdge edge: edges) {
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
        for (ConcreteEdge edge: edges) {
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
        for (ConcreteEdge edge: edges) {
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

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
public class ConcreteEdge {

    // He asked for a immutable Edge
    private final String source;
    private final String target;
    private final int weight;
    
    // TODO fields
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    public ConcreteEdge(String source, String target, int weight){
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

    // equality proxy
    public boolean similar(Object thatObject){
        // must be this type 
        if (!(thatObject instanceof ConcreteEdge)) return false;
        // casting
        ConcreteEdge thatEdge = (ConcreteEdge) thatObject;
        boolean equalSource = this.getSource() == thatEdge.getSource();
        boolean equalTarget = this.getTarget() == thatEdge.getTarget();
        return equalSource & equalTarget;
    }

    // equality methods
    @Override 
    public boolean equals(Object thatObject) {
        // must be this type 
        if (!(thatObject instanceof ConcreteEdge)) return false;
        // casting
        ConcreteEdge thatEdge = (ConcreteEdge) thatObject;
        boolean equalSource = this.getSource() == thatEdge.getSource();
        boolean equalTarget = this.getTarget() == thatEdge.getTarget();
        boolean equalWeight = this.getWeight() == thatEdge.getWeight();
        return equalSource & equalTarget & equalWeight;
    }

    @Override
    public int hashCode() {
        // follow the same rule as equals, to a source and target have the same
        // rule
        // maybe I should implement with the same weight?
        return Objects.hash(this.source, this.target, this.weight);
    }
 
    
    // TODO toString()

    @Override
    public String toString() {
        String fullString;

        fullString = "("+this.getSource() + ") - (" +
            this.getWeight() + " - (" + this.getTarget() + ")";
        return fullString;
    }
}
