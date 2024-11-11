package graph;

public class UseConcreteEdgesGraph {

    public static void main(String args[]){

        ConcreteEdgesGraph graph = new ConcreteEdgesGraph();

        graph.add("A");
        graph.add("B");
        
        System.out.println(graph.vertices());

        graph.add("C");
        System.out.println(graph.vertices());

        graph.set("A", "B", 4);
        graph.set("B", "D", 6);
        graph.set("D", "E", 6);
        //print edges
        for (Edge edge: graph.getEdges()) {
            System.out.println(edge);
        }
        // show vertices
        System.out.println(graph.vertices());

        
        System.out.println("Targets");
        System.out.println(graph.targets("A"));
        System.out.println(graph.targets("B"));
        System.out.println("Sources");
        System.out.println(graph.sources("B"));
        System.out.println(graph.sources("D"));


        graph.remove("B");
        System.out.println("Remove B");
        System.out.println(graph.vertices());

        System.out.println("Sources");
        System.out.println(graph.sources("B"));

        System.out.println("Targets");
        System.out.println(graph.targets("A"));

    }
    
}
