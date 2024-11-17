package graph;

import java.util.Objects;

/**
 * Immutable.
 * This class is internal to the rep of EdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
public class Edge<L> {

    private final L source;
    private final L target;
    private final int weight;

    // Constructor
    public Edge(L source, L target, int weight){
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();
    }

    // Representation invariant checker
    private void checkRep(){
        assert source != null;
        assert target != null;
        assert weight >= 0;
    }

    // Observers
    public L getSource(){
        return this.source;
    }

    public L getTarget(){
        return this.target;
    }

    public int getWeight(){
        return this.weight;
    }

    // Equality methods
    public boolean similar(Object thatObject){
        if (!(thatObject instanceof Edge<?>)) return false;
        Edge<L> thatEdge = (Edge<L>) thatObject;
        return this.source.equals(thatEdge.source) && this.target.equals(thatEdge.target);
    }

    @Override 
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof Edge<?>)) return false;
        Edge<L> thatEdge = (Edge<L>) thatObject;
        return this.source.equals(thatEdge.source) &&
               this.target.equals(thatEdge.target) &&
               this.weight == thatEdge.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.source, this.target, this.weight);
    }

    @Override
    public String toString() {
        return "(" + this.source + ") - " + this.weight + " -> (" + this.target + ")";
    }

    /**
     * Main method for testing the Edge class.
     */
    public static void main(String[] args) {

        // Test Edge class
        Edge<String> edge1 = new Edge<>("A", "B", 5);
        Edge<String> edge2 = new Edge<>("A", "B", 5);

        // Testing equality
        System.out.println("Are edges equal? " + edge1.equals(edge2)); // Should print true

        // Testing getters
        System.out.println("Source: " + edge1.getSource()); // Should print A
        System.out.println("Target: " + edge1.getTarget()); // Should print B
        System.out.println("Weight: " + edge1.getWeight()); // Should print 5

        // Testing hashCode and toString
        System.out.println("HashCode: " + edge1.hashCode());
        System.out.println("ToString: " + edge1.toString()); // Should print (A) -5-> (B)
    }
}
