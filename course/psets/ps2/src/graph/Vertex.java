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

public class Vertex<L> {
    
    // fields
    private final L source;
    private final HashMap<L, Integer> adjTargets;
    
    // Abstraction function:
    //   AF(source, adjTargets):
    //      Represents the connections of target vertices to the a source vertex, with their positive weights
    //      Source vertex points to (directed) target vertices
    // Representation invariant:
    //   source and adjTargets are final fields, so no reassignment is done, only mutable inside the adjTargets HashMap
    // Safety from rep exposure:
    //   Client can't change 
    //   adjTargets field is private, so no external access directly, only modifiable by the public methods
    
    // constructor with both
    public Vertex(L source, HashMap<L, Integer> adjTargets) {
        this.source = source;
        this.adjTargets = adjTargets;
        checkRep();
    }
    
    // constructor with only the source vertex
    public Vertex(L source){
        this.source = source;
        this.adjTargets = new HashMap<>();
    }
    
    // checkRep
    public void checkRep(){
        assert source != null;
        assert adjTargets != null;
    }
    
    // observer
    public Map<L, Integer> getAdjTargets(){
        Map<L, Integer> unmodifiableMap = Collections.unmodifiableMap(this.adjTargets);
        return unmodifiableMap;
    }

    // observer
    /**
     *  
     * @return The source node
     */
    public L getSource() {
        return this.source;
    }

    // observer
    /**
     * Recevies the target and gets the Integer weight from a Target from the source node
     * @param target
     * @return weight from target 
     */
    public Integer getTargetWeight(L target) {
        return adjTargets.get(target);
    }

    /**
     * 
     * @return The set of targets from source node
     */
    public Set<L> getTargets(){
        Set<L> targets = adjTargets.keySet();
        return targets;
    }



    // mutator
    /**
     *Set a new connection from source to the target, with weight 
     * If weight is non positive, it will remove the connection from there
     * @param target
     * @param weight
     * @return the weight of old, or if did not contain the target there, return 0
     */
    public Integer set(L target, Integer weight){
        Integer return_weight;
        // if it has the key and we are trying to set to 0, so we should remove it
        if (this.getTargets().contains(target)) {
            if (weight <=0) {
                return_weight = this.getTargetWeight(target);
                this.remove(target);
            }
            else{
                return_weight = this.adjTargets.put(target, weight);
            }
        }
        // if did not contain, the return will be 0, and must add it
        else
            this.adjTargets.put(target, weight);
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
    public boolean remove(L target){
        Integer removed_value = adjTargets.remove(target);
        checkRep();
        if (removed_value == null){
            return false;
        } else {
            return true;
        }
    }

    @Override
        public boolean equals(Object thatObject) {
        // must be this type 
        if (!(thatObject instanceof Vertex<?>)) return false;
        // casting
        Vertex<L> thatVertex = (Vertex<L>) thatObject;
        boolean equalSource = this.getSource().equals(thatVertex.getSource());
        return equalSource;
    }

    //observer of string representation
    @Override
    public String toString() {
        StringBuilder repString = new StringBuilder();
        repString.append("\n").append(this.getSource()).append(":: ");

        int count = 0;
        int size = adjTargets.size();
        
        if (size > 0) {
            for (Map.Entry<L, Integer> entry : adjTargets.entrySet()) {
                L target = entry.getKey();
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
