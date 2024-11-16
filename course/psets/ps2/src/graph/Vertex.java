// package graph;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Set;

public class Vertex<L> {
    
    // fields
    private final L source;
    private final HashMap<L, Integer> adjTargets;
    
    // Abstraction function:
    //   AF(source, adjTargets):
    //      Represents the connections of target vertices to the source vertex, with their positive weights.
    // Representation invariant:
    //   source and adjTargets are final fields, so no reassignment is done, only mutable inside the adjTargets HashMap.
    // Safety from rep exposure:
    //   Client can't change adjTargets (private and final).

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
    private void checkRep(){
        assert source != null;
        assert adjTargets != null;
    }
    
    // observer
    public Map<L, Integer> getAdjTargets(){
        return Collections.unmodifiableMap(this.adjTargets);
    }

    // observer
    public L getSource() {
        return this.source;
    }

    public Integer getTargetWeight(L target) {
        return adjTargets.get(target);
    }

    public Set<L> getTargets(){
        return adjTargets.keySet();
    }

    // mutator
    public Integer set(L target, Integer weight){
        Integer return_weight;
        if (this.getTargets().contains(target)) {
            if (weight <= 0) {
                return_weight = this.getTargetWeight(target);
                this.remove(target);
            } else {
                return_weight = this.adjTargets.put(target, weight);
            }
        } else {
            this.adjTargets.put(target, weight);
            return_weight = 0;
        }
        checkRep();
        return return_weight;
    }

    public boolean remove(L target){
        Integer removed_value = adjTargets.remove(target);
        checkRep();
        return removed_value != null;
    }

    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof Vertex<?>)) return false;
        Vertex<L> thatVertex = (Vertex<L>) thatObject;
        return this.getSource().equals(thatVertex.getSource());
    }

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
                if (++count < size) {
                    repString.append(", ");
                }
            }
        } else {
            repString.append("[[No Target Nodes]]");
        }
        return repString.toString();
    }

    // Main method for testing
    public static void main(String[] args) {
        
        HashMap<String, Integer> adjTargets1 = new HashMap<>();
        adjTargets1.put("B", 4);
        adjTargets1.put("D", 5);
        Vertex<String> v1 = new Vertex<>("A", adjTargets1);
        System.out.println(v1.toString());

        Vertex<String> v2 = new Vertex<>("C");

        v2.set("F", 1);
        v2.set("G", 2);
        System.out.println("Add F " + v2.getTargets());
        System.out.println("Add G " + v2.toString());

        v2.remove("F");
        System.out.println("Remove F "+ v2.toString());

        v2.set("G", 10);
        System.out.println("Modify G " + v2.toString());

        v2.set("H", 12);
        System.out.println("Add H " + v2.toString());

        System.out.println("Observers:\n");
        System.out.println(v2.toString());
        System.out.println(v2.getTargets());
        System.out.println(v2.getTargetWeight("H"));
        System.out.println(v2.getSource());
        System.out.println(v2.getAdjTargets());
    }
}
