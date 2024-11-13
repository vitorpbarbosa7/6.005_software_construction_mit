package graph;

import java.util.Map;
import java.util.HashMap;

public class VertexUse {

    public static void main(String args[]) {
        
        HashMap<String, Integer> adjTargets1 = new HashMap<>();
        adjTargets1.put("B", 4);
        adjTargets1.put("D", 5);
        Vertex v1 = new Vertex("A", adjTargets1);
        System.out.println(v1.toString());

        Vertex v2 = new Vertex("C");

        v2.set("F", 1);
        v2.set("G", 2);
        System.out.println("Add F " + v2.getTargets());
        System.out.println("Add G " + v2.toString());

        v2.remove("F");
        System.out.println("Remove F "+ v2.toString());

        // modify
        v2.set("G", 10);
        System.out.println("Modify G " + v2.toString());

        v2.set("H", 12);
        System.out.println("Add H" + v2.toString());

        // observers
        System.out.println("Observers:\n");
        System.out.println(v2.toString());
        System.out.println(v2.getTargets());
        System.out.println(v2.getTargetWeight("H"));
        System.out.println(v2.getSource());
        System.out.println(v2.getAdjTargets());



    }
    
}
