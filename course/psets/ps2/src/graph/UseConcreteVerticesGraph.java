package graph;

public class UseConcreteVerticesGraph {

    public static void main(String args[]) {
        ConcreteVerticesGraph graph = (ConcreteVerticesGraph) ConcreteVerticesGraph.empty();

        graph.add("A");
        graph.add("B");
        System.out.println("List vertices: " + graph.vertices());

        graph.set("C", "D", 4);
        graph.set("C", "E", 15);
        graph.set("D", "E", 20);
        System.out.println("Vertices s-t: " + graph.vertices());
        System.out.println("ToString :" + graph.toString());

        graph.targets("C");
        System.out.println("Targets from C: " + graph.targets("C"));
        graph.targets("A");
        System.out.println("Targets from A: " + graph.targets("A"));


    }
    
}
