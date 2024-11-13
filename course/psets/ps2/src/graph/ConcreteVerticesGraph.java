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
public class ConcreteVerticesGraph implements Graph<String> {
    
    private final List<Vertex> vertices = new ArrayList<>();
    
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
    public static Graph<String> empty() {
        return new ConcreteVerticesGraph();
    }

    // checkRep
    private void checkRep() {
        Set<Vertex> vertexSet = new HashSet<>(this.vertices);
        assert vertexSet.size() == this.vertices.size();
    }

    
    // mutator 
    @Override public boolean add(String vertex) {
        // if the vertex points to others, also add here?
        Vertex vertexObj = new Vertex(vertex);
        checkRep();
        return this.vertices.add(vertexObj);
    }
    
    // mutator 
    @Override public int set(String source, String target, int weight) {
        //find a way to also create the target vertex
        int return_weight = -1;
        boolean add_to_list = true;
        boolean remove_from_list = false;

        Vertex newSourceVertex = new Vertex(source);
        newSourceVertex.set(target, weight);
        Vertex newTargetVertex = new Vertex(target);

        // find which vertex has the source source
        for (Vertex vertexObj: this.vertices) {
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
    @Override public boolean remove(String vertex) {
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
        Vertex removeVertex = new Vertex(vertex);

        vertices.remove(removeVertex);

        String target = vertex;
        Map<String, Integer> sourcesWithTarget= this.sources(target);
        Set<String> sourcesLabel = sourcesWithTarget.keySet();
        // the source vertices must be modified, removing this target from there
        for (String source: sourcesLabel){
            for (Vertex vertexLocal: this.vertices){
                if (vertexLocal.getSource().equals(source)) {
                    vertexLocal.remove(target);
                }
            }

        }
        checkRep();
        return true;

    }
    
    // observer
    @Override public Set<String> vertices() {
        Set<String> verticesSet = new HashSet<>();
        for (Vertex vertex: this.vertices){
            verticesSet.add(vertex.getSource());
        }
        return verticesSet;
    }
    
    @Override public Map<String, Integer> sources(String target) {
        Map<String, Integer> sourcesMap = new HashMap<>();

        // look in all vertices, who has target target, if it has, return the source and the weight
        for (Vertex vertex: this.vertices){
            if (vertex.getTargets().contains(target)) {
                Integer localWeight = vertex.getTargetWeight(target);
                String localSource = vertex.getSource();
                sourcesMap.put(localSource, localWeight);
            }
        }
        return sourcesMap;
    }
    
    // observer
    @Override public Map<String, Integer> targets(String source) {
        Map<String, Integer> targetsMap = new HashMap<>();

        Vertex vertexSource = new Vertex(source);

        for (Vertex vertex: this.vertices){
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
        
        for(Vertex vertex: this.vertices){
            repString = repString + vertex.toString() + "\n"; 
        }

        return repString;
    }

    
}