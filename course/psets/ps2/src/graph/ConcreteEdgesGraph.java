package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collections;

public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    public ConcreteEdgesGraph() {
        checkRep();
    }
   
    public static <L> Graph<L> empty() {
        return new ConcreteEdgesGraph<L>();
    }

    private void checkRep() {
        Set<String> seenEdges = new HashSet<>();
        for (Edge<L> edge: edges) {
            assert edge.getWeight() > 0;
            assert vertices.contains(edge.getSource());
            assert vertices.contains(edge.getTarget());
            String edgeKey = edge.getSource() + "->" + edge.getTarget();
            assert !seenEdges.contains(edgeKey);
            seenEdges.add(edgeKey);
        }
    }
    
    @Override public boolean add(L vertex) {
        boolean added = vertices.add(vertex);
        checkRep();
        return added;
    }

    @Override public int set(L source, L target, int weight) {
        int returnWeight = -1;

        Edge<L> newEdge = new Edge<L>(source, target, weight);

        for (Edge<L> edge: edges) {
            if (newEdge.similar(edge)) {
                if (edge.getWeight() == newEdge.getWeight()) {
                    returnWeight = weight;
                } else {
                    int oldWeight = edge.getWeight();
                    edges.remove(edge);
                    if (weight != 0) {
                        edges.add(newEdge);
                    }
                    returnWeight = oldWeight;
                }    
                break;
            }
        }
        
        if (returnWeight == -1) {
            vertices.add(source);
            vertices.add(target);
            edges.add(newEdge);
            returnWeight = 0;
        }

        checkRep();
        return returnWeight;
    }
    
    @Override public boolean remove(L vertex) {
        boolean removed = vertices.remove(vertex);
        if (removed) {
            Iterator<Edge<L>> iterator = edges.iterator();
            while (iterator.hasNext()) {
                Edge<L> edge = iterator.next();
                if (vertex.equals(edge.getSource()) || vertex.equals(edge.getTarget())) {
                    iterator.remove();
                }
            }
        }
        checkRep();
        return removed;
    }
    
    @Override public Set<L> vertices() {
        return Collections.unmodifiableSet(vertices);
    }

    @Override public Map<L, Integer> sources(L target) {
        Map<L, Integer> mapTarget = new HashMap<>();
        for (Edge<L> edge: edges) {
            if (edge.getTarget().equals(target)) {
                mapTarget.put(edge.getSource(), edge.getWeight());
            }
        }
        return mapTarget;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Map<L, Integer> mapSource = new HashMap<>();
        for (Edge<L> edge: edges) {
            if (edge.getSource().equals(source)) {
                mapSource.put(edge.getTarget(), edge.getWeight());
            }
        }
        return mapSource;
    }
        
    @Override public String toString() {
        StringBuilder graphString = new StringBuilder();
        for (Edge<L> edge : edges) {            
            graphString.append(edge.toString()).append("\n");
        }
        return graphString.toString();
    }

    // Main Method for Testing
    public static void main(String[] args) {
        ConcreteEdgesGraph<String> graph = new ConcreteEdgesGraph<>();
        graph.add("A");
        graph.add("B");
        graph.add("C");

        graph.set("A", "B", 4);
        graph.set("B", "C", 5);
        graph.set("C", "A", 7);
        graph.set("B", "D", 6);
        graph.set("D", "E", 6);

        System.out.println("Vertices:");
        System.out.println(graph.vertices());

        System.out.println("Full Graph:");
        System.out.println(graph.toString());

        System.out.println("Targets of A:");
        System.out.println(graph.targets("A"));

        System.out.println("Targets of B:");
        System.out.println(graph.targets("B"));

        System.out.println("Targets of D:");
        System.out.println(graph.targets("D"));

        System.out.println("Sources of B:");
        System.out.println(graph.sources("B"));

        System.out.println("Sources of D:");
        System.out.println(graph.sources("D"));

        System.out.println("Sources of E:");
        System.out.println(graph.sources("E"));

        graph.remove("B");
        System.out.println("\nAfter Removing B:");

        System.out.println("Vertices:");
        System.out.println(graph.vertices());

        System.out.println("Targets of A:");
        System.out.println(graph.targets("A"));

        System.out.println("Targets of D:");
        System.out.println(graph.targets("D"));

        System.out.println("Sources of D:");
        System.out.println(graph.sources("D"));

        System.out.println("Sources of E:");
        System.out.println(graph.sources("E"));
    }
}
