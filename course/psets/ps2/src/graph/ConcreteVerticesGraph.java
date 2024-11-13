/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
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
        // if the vertex points to others, also add here?
        Vertex<L> vertexObj = new Vertex<L>(vertex);
        checkRep();
        return this.vertices.add(vertexObj);
    }
    
    // mutator 
    @Override public int set(L source, L target, int weight) {
        //find a way to also create the target vertex
        int return_weight = -1;
        boolean add_to_list = true;
        boolean remove_from_list = false;

        Vertex<L> newSourceVertex = new Vertex<L>(source);
        newSourceVertex.set(target, weight);
        Vertex<L> newTargetVertex = new Vertex<L>(target);

        // find which vertex has the source source
        for (Vertex<L> vertexObj: this.vertices) {
            if (vertexObj.equals(newSourceVertex)) {
                return_weight = vertexObj.set(target, weight);
                if (weight <= 0) {
                    add_to_list = false;
                    remove_from_list = true;
                }

            };
        }

        // if it did not exist
        if (return_weight == -1) {
            this.vertices.add(newSourceVertex);
            return_weight = 0;
        }

        if (add_to_list) {
            // if did not contained the newTargetVertex, add it
            if (!this.vertices.contains(newTargetVertex)) {
                this.vertices.add(newTargetVertex);
            }
        if (remove_from_list) {
            this.vertices.remove(newTargetVertex);
        }
    }

        checkRep();
        return return_weight;
    }
    // mutator 
    @Override public boolean remove(L vertex) {
        // remove vertex
        // but vertex and be source
        //      if it is source, remove from the list 
        // and vertex can be target
        //      also the vertex objects targets

        // in all vertices, look for D as a target, if it exists as a target
        // remove from the target
        // finally remove from the list of vertices
        // so I am removing the connections

        // vertex as source is easy to remove
        Vertex<L> removeVertex = new Vertex<L>(vertex);

        vertices.remove(removeVertex);

        L target = vertex;
        Map<L, Integer> sourcesWithTarget= this.sources(target);
        Set<L> sourcesLabel = sourcesWithTarget.keySet();
        // the source vertices must be modified, removing this target from there
        for (L source: sourcesLabel){
            for (Vertex <L>vertexLocal: this.vertices){
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
        for (Vertex<L> vertex: this.vertices){
            verticesSet.add(vertex.getSource());
        }
        return verticesSet;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L, Integer> sourcesMap = new HashMap<>();

        // look in all vertices, who has target target, if it has, return the source and the weight
        for (Vertex<L> vertex: this.vertices){
            if (vertex.getTargets().contains(target)) {
                Integer localWeight = vertex.getTargetWeight(target);
                L localSource = vertex.getSource();
                sourcesMap.put(localSource, localWeight);
            }
        }
        return sourcesMap;
    }
    
    // observer
    @Override public Map<L, Integer> targets(L source) {
        Map<L, Integer> targetsMap = new HashMap<>();

        Vertex<L> vertexSource = new Vertex<L>(source);

        for (Vertex<L> vertex: this.vertices){
            if (vertexSource.equals(vertex)) {
                targetsMap = vertex.getAdjTargets();
            }
        }
    
        return targetsMap;
    }
    
    // observer
    // toString()
    @Override
    public String toString() {
        String repString = "";
        
        for(Vertex<L> vertex: this.vertices){
            repString = repString + vertex.toString() + "\n"; 
        }

        return repString;
    }

    
}