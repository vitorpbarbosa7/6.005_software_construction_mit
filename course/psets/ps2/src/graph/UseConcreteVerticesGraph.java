package graph;

public class UseConcreteVerticesGraph {

    public static void main(String args[]) {
        ConcreteVerticesGraph graph = (ConcreteVerticesGraph) ConcreteVerticesGraph.empty();

        graph.add("A");
        graph.add("B");
        System.out.println("List vertices: " + graph.vertices());

        graph.set("C", "D", 4);
        System.out.println("Vertices after C-D s-t: " + graph.vertices());
        System.out.println("ToString after C-D: \n" + graph.toString());


    }
    
}
