package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collections;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   AF(vertices) -> Positive Weighted Directed Graph 
    //   Represents:
    //          Source and target vertices are represented as a list of vertices
    // Representation invariant:
    //   The list of vertices is private and final references
    //   Unique vertices, no duplicated
    // Safety from rep exposure:
    //   No client can reassign the list of vertices
    
    // empty constructor to instantiate the object
    public static <L> Graph<L> empty() {
        return new ConcreteVerticesGraph<>();
    }

    // checkRep
    private void checkRep() {
        Set<Vertex<L>> vertexSet = new HashSet<>(this.vertices);
        assert vertexSet.size() == this.vertices.size();
    }

    // mutator 
    @Override public boolean add(L vertex) {
        Vertex<L> vertexObj = new Vertex<L>(vertex);
        checkRep();
        return this.vertices.add(vertexObj);
    }
    
    // mutator 
    @Override public int set(L source, L target, int weight) {
        int returnWeight = -1;
        boolean addToList = true;
        boolean removeFromList = false;

        Vertex<L> newSourceVertex = new Vertex<L>(source);
        newSourceVertex.set(target, weight);
        Vertex<L> newTargetVertex = new Vertex<L>(target);

        for (Vertex<L> vertexObj: this.vertices) {
            if (vertexObj.equals(newSourceVertex)) {
                returnWeight = vertexObj.set(target, weight);
                if (weight <= 0) {
                    addToList = false;
                    removeFromList = true;
                }
            }
        }

        if (returnWeight == -1) {
            this.vertices.add(newSourceVertex);
            returnWeight = 0;
        }

        if (addToList) {
            if (!this.vertices.contains(newTargetVertex)) {
                this.vertices.add(newTargetVertex);
            }
        }
        if (removeFromList) {
            this.vertices.remove(newTargetVertex);
        }

        checkRep();
        return returnWeight;
    }

    // mutator 
    @Override public boolean remove(L vertex) {
        Vertex<L> removeVertex = new Vertex<L>(vertex);
        vertices.remove(removeVertex);

        L target = vertex;
        Map<L, Integer> sourcesWithTarget = this.sources(target);
        Set<L> sourcesLabel = sourcesWithTarget.keySet();

        for (L source: sourcesLabel) {
            for (Vertex<L> vertexLocal: this.vertices) {
                if (vertexLocal.getSource().equals(source)) {
                    vertexLocal.remove(target);
                }
            }
        }

        checkRep();
        return true;
    }

    // observer
    @Override public Set<L> vertices() {
        Set<L> verticesSet = new HashSet<>();
        for (Vertex<L> vertex: this.vertices) {
            verticesSet.add(vertex.getSource());
        }
        return verticesSet;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L, Integer> sourcesMap = new HashMap<>();

        for (Vertex<L> vertex: this.vertices) {
            if (vertex.getTargets().contains(target)) {
                Integer localWeight = vertex.getTargetWeight(target);
                L localSource = vertex.getSource();
                sourcesMap.put(localSource, localWeight);
            }
        }
        return sourcesMap;
    }

    @Override public Map<L, Integer> targets(L source) {
        Map<L, Integer> targetsMap = new HashMap<>();
        Vertex<L> vertexSource = new Vertex<L>(source);

        for (Vertex<L> vertex: this.vertices) {
            if (vertexSource.equals(vertex)) {
                targetsMap = vertex.getAdjTargets();
            }
        }

        return targetsMap;
    }

    @Override public String toString() {
        StringBuilder repString = new StringBuilder();
        for (Vertex<L> vertex: this.vertices) {
            repString.append(vertex.toString()).append("\n");
        }
        return repString.toString();
    }

    // Main Method for Testing
    public static void main(String[] args) {
        ConcreteVerticesGraph<String> graph = new ConcreteVerticesGraph<>();
        graph.add("A");
        graph.add("B");
        System.out.println("List vertices: " + graph.vertices());

        graph.set("C", "D", 4);
        graph.set("C", "E", 15);
        graph.set("D", "E", 20);

        System.out.println("Vertices s-t: " + graph.vertices());
        System.out.println("ToString: " + graph.toString());

        System.out.println("Targets from C: " + graph.targets("C"));
        System.out.println("Targets from A: " + graph.targets("A"));
        System.out.println("Sources from E: " + graph.sources("E"));

        graph.remove("E");
        System.out.println("After removing vertex E: " + graph.toString());
    }
}
