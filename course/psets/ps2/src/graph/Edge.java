/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;
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
   
    // ADT
    // Abstraction function:
    //       AF(source, target, weight):
    //           Represents the edges of a Weighted Directed Graph
    //          source: starting vertex from which the direct edge comes from 
    //          target: reaching vertex from which the direct edge goes into
    //          weight: the non negative weight from this edge
    // Representation invariant:
    //      Edges are immutable
    //      Weights are always non negative
    //      source and target are non null strings
    // Safety from rep exposure:
    //      External cliente can't alter the weight or the source or target of a edge
    
    public Edge(String source, String target, int weight){
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();
    }

    public void checkRep(){
        assert source != null;
        assert target != null;
        assert weight >= 0;
    }
    

    // observer
    /** 
     * Gets the source vertex from current Edge
     */
    public String getSource(){
        return this.source;
    }

    // observer
    /**
     * Gets the target vertex from current Edge
     * @return
     */
    public String getTarget(){
        return this.target;
    }

    // observer
    /** 
     * Gets weight from current Edge
     */
    public int getWeight(){
        return this.weight;
    }

    /**
     * Compares the Source and Target of two Immutable Edges
     * If equals, than they are considered 'similar', and returned true, otherwise False
     * @param thatObject
     * @return
     */
    // equality proxy
    public boolean similar(Object thatObject){
        // must be this type 
        if (!(thatObject instanceof Edge)) return false;
        // casting
        Edge thatEdge = (Edge) thatObject;
        boolean equalSource = this.getSource().equals(thatEdge.getSource());
        boolean equalTarget = this.getTarget().equals(thatEdge.getTarget());
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
 
    
    /**
     * Represents the Edge a Sequence of String
     * Going from source vertex , showing weight, showing arrow of directed Edge
     * And finally the target vertex
     */
    @Override
    public String toString() {
        String fullString;

        fullString = "("+this.getSource() + ") - " +
            this.getWeight() + " -> (" + this.getTarget() + ")";
        return fullString;
    }
}
