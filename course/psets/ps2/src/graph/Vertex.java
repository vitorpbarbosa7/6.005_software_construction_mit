/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */


package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Collections;

public class Vertex {
    
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
    
    // constructor with both
    public Vertex(String source, HashMap<String, Integer> adjNodes) {
        this.source = source;
        this.adjNodes = adjNodes;
        checkRep();
    }
    
    // constructor with only the source vertex
    public Vertex(String source){
        this.source = source;
        this.adjNodes = new HashMap<>();
    }
    
    // checkRep
    public void checkRep(){
        assert source != null;
        assert adjNodes != null;
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
    public Integer set(String target, Integer weight){
        Integer return_weight;
        // if it has the key and we are trying to set to 0, so we should remove it
        if (this.getAdjNodes().containsKey(target)) {
            if (weight <=0) {
                return_weight = this.getTargetWeight(target);
                this.remove(target);
            }
            else{
                return_weight = adjNodes.put(target, weight);
            }
        }
        else
            return_weight = 0;

        checkRep();
        return return_weight;
    }

    // mutator
    /**
     *Remove a existing connection from source to target 
     * @param target
     * @return true if the value existed, or false if the value did not exist there
     */
    public boolean remove(String target){
        Integer removed_value = adjNodes.remove(target);
        checkRep();
        if (removed_value == null){
            return false;
        } else {
            return true;
        }
    }

    public boolean sameSource(Vertex thatObject) {
        // must be this type 
        if (!(thatObject instanceof Vertex)) return false;
        // casting
        Vertex thatVertex = (Vertex) thatObject;
        return this.getSource().equals(thatVertex.getSource());
    }

    @Override
        public boolean equals(Object thatObject) {
        // must be this type 
        if (!(thatObject instanceof Vertex)) return false;
        // casting
        Vertex thatVertex = (Vertex) thatObject;
        boolean equalSource = this.getSource() == thatVertex.getSource();
        return equalSource;
    }

    //observer of string representation
    @Override
    public String toString() {
        StringBuilder repString = new StringBuilder();
        repString.append(this.getSource()).append(":: ");

        int count = 0;
        int size = adjNodes.size();
        
        if (size > 0) {
            for (Map.Entry<String, Integer> entry : adjNodes.entrySet()) {
                String target = entry.getKey();
                Integer weight = entry.getValue();
                repString.append("(").append(target).append(": ").append(weight).append(") ");
                // Only add a comma if it's not the last item
                if (++count < size) {
                    repString.append(", ");
                }
            }
        } else {
            repString.append("[[No Target Nodes]]");
        }
        
        return repString.toString(); // Remove trailing space
    }

    
}
