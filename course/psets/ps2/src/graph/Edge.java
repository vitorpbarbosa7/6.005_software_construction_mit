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
 * TODO specification
 * Immutable.
 * This class is internal to the rep of EdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
public class Edge {

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

    // equality proxy
    public boolean similar(Object thatObject){
        // must be this type 
        if (!(thatObject instanceof Edge)) return false;
        // casting
        Edge thatEdge = (Edge) thatObject;
        boolean equalSource = this.getSource() == thatEdge.getSource();
        boolean equalTarget = this.getTarget() == thatEdge.getTarget();
        return equalSource & equalTarget;
    }

    // equality methods
    @Override 
    public boolean equals(Object thatObject) {
        // must be this type 
        if (!(thatObject instanceof Edge)) return false;
        // casting
        Edge thatEdge = (Edge) thatObject;
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
