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
        // if the vertex points to others, also add here?
        Vertex vertexObj = new Vertex(vertex);
        return this.vertices.add(vertexObj);
    }
    
    @Override public int set(String source, String target, int weight) {
        //find a way to also create the target vertex
        int return_weight = -1;

        Vertex newSourceVertex = new Vertex(source);
        newSourceVertex.set(target, weight);
        Vertex newTargetVertex = new Vertex(target);

        // find which vertex has the source source
        for (Vertex vertexObj: this.vertices) {
            if (vertexObj.sameSource(newSourceVertex)) {
                return_weight = vertexObj.set(target, weight);
                // if did not contained the newTargetVertex, add it
                if (!this.vertices.contains(newTargetVertex)) {
                    this.vertices.add(newTargetVertex);
                }
            };
        }

        // if it did not exist
        if (return_weight == -1) {
            this.vertices.add(newSourceVertex);
            if (!this.vertices.contains(newTargetVertex)) {
                this.vertices.add(newTargetVertex);
                }
            return_weight = 0;
        }

        return return_weight;
    }
    
    @Override public boolean remove(String vertex) {
        // remove vertex
        // but vertex and be source
        //      if it is source, remove from the list 
        // and vertex can be target
        //      also the vertex objects targets
    }
    
    @Override public Set<String> vertices() {
        // return all vertices, the sources and targets
    }
    
    @Override public Map<String, Integer> sources(String target) {
        // return all vertices originally sources
    }
    
    @Override public Map<String, Integer> targets(String source) {
        // return all those that appear as targets
    }
    
    // TODO toString()
    
}