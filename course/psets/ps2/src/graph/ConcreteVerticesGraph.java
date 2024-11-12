/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Collections;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph implements Graph<String> {
    
    private final List<Vertex> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    
    // TODO checkRep
    
    @Override public boolean add(String vertex) {
        throw new RuntimeException("not implemented");
    }
    
    @Override public int set(String source, String target, int weight) {
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
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex {
    
    // fields
    private final String source;
    private final HashMap<String, Integer> adjNodes;
    
    // Abstraction function:
    //   AF(source, adjNodes):
    //      Represents the connections of target vertices to the a source vertex, with their positive weights
    //      Source vertex points to (directed) target vertices
    // Representation invariant:
    //   source and adjNodes are final fields, so no reassignment is done, only mutable inside the adjNodes HashMap
    // Safety from rep exposure:
    //   Client can't change 
    //   adjNodes field is private, so no external access directly, only modifiable by the public methods
    
    // constructor
    public Vertex(String source, HashMap<String, Integer> adjNodes) {
        this.source = source;
        this.adjNodes = adjNodes;
    }
    
    // checkRep
    public void checkRep(){
        assert source != null;
        for (Map.Entry<String, Integer> entry : adjNodes.entrySet()){
            assert entry.getKey() != null;
            assert entry.getValue() != null;
        }
    }
    
    // observer
    public Map<String, Integer> getAdjNodes(){
        Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(this.adjNodes);
        return unmodifiableMap;
    }

    // observer
    /**
     *  
     * @return The source node
     */
    public String getSource() {
        return this.source;
    }

    // observer
    /**
     * Recevies the target and gets the Integer weight from a Target from the source node
     * @param target
     * @return weight from target 
     */
    public Integer getTargetWeight(String target) {
        return adjNodes.get(target);
    }

    /**
     * 
     * @return The set of targets from source node
     */
    public Set<String> getTargets(){
        Set<String> targets = adjNodes.keySet();
        return targets;
    }



    // mutator
    /**
     *Set a new connection from source to the target, with weight 
     * If weight is non positive, it will remove the connection from there
     * @param target
     * @param weight
     * @return true if the value was changed, or false if the vajue did not exist there
     */
    public boolean set(String target, Integer weight){
        if (weight <=0) {
            return this.remove(target);
        }
        Integer previous_value = adjNodes.put(target, weight);
        if (previous_value == null) {
            return false;
        } else {
            return true;
        }
    }

    // mutator
    /**
     *Remove a existing connection from source to target 
     * @param target
     * @return true if the value existed, or false if the value did not exist there
     */
    public boolean remove(String target){
        Integer removed_value = adjNodes.remove(target);
        if (removed_value == null){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder repString = new StringBuilder();
        repString.append(this.getSource()).append(":: ");

        int count = 0;
        int size = adjNodes.size();
        
        for (Map.Entry<String, Integer> entry : adjNodes.entrySet()) {
            String target = entry.getKey();
            Integer weight = entry.getValue();
            repString.append("(").append(target).append(": ").append(weight).append(") ");
            // Only add a comma if it's not the last item
            if (++count < size) {
                repString.append(", ");
        }
        }
        
        return repString.toString().trim(); // Remove trailing space
    }

    
}
