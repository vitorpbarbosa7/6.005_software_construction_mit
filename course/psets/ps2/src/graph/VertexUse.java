package graph;

import java.util.Map;
import java.util.HashMap;

public class VertexUse {

    public static void main(String args[]) {
        
        HashMap<String, Integer> adjNodes1 = new HashMap<>();
        adjNodes1.put("B", 4);
        adjNodes1.put("D", 5);
        Vertex v1 = new Vertex("A", adjNodes1);
        System.out.println(v1.toString());

        Vertex v2 = new Vertex("C");
        System.out.println(v2.toString());

        v2.set("F", 1);
        v2.set("G", 2);
        System.out.println(v2.toString());

        v2.remove("F");
        System.out.println(v2.toString());

        // modify
        v2.set("G", 10);
        System.out.println(v2.toString());

        v2.set("H", 12);

        // observers
        System.out.println(v2.toString());
        System.out.println(v2.getTargets());
        System.out.println(v2.getTargetWeight("H"));
        System.out.println(v2.getSource());
        System.out.println(v2.getAdjNodes());



    }
    
}
