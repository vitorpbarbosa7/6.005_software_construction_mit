package graph;

public class UseConcreteEdgesGraph {

    public static void main(String args[]){
        ConcreteEdgesGraph graph = (ConcreteEdgesGraph) ConcreteEdgesGraph.empty();

        graph.add("A");
        graph.add("B");
        graph.add("C");

        graph.set("A", "B", 4);
        graph.set("B", "D", 6);
        graph.set("D", "E", 6);


        // show vertices
        System.out.println("Vertices");
        System.out.println(graph.vertices());
        System.out.println("Full Graph:");
        System.out.println(graph.toString());
        
        System.out.println("Targets A");
        System.out.println(graph.targets("A"));
        System.out.println("Targets B");
        System.out.println(graph.targets("B"));
        System.out.println("Targets D");
        System.out.println(graph.targets("D"));
        System.out.println("Sources B");
        System.out.println(graph.sources("B"));
        System.out.println("Sources D");
        System.out.println(graph.sources("D"));
        System.out.println("Sources E");
        System.out.println(graph.sources("E"));


        


        graph.remove("B");
        System.out.println("Remove B");
        System.out.println("Vertices");
        System.out.println(graph.vertices());

        System.out.println("Targets A");
        System.out.println(graph.targets("A"));
        System.out.println("Targets B");
        System.out.println(graph.targets("B"));
        System.out.println("Targets D");
        System.out.println(graph.targets("D"));
        System.out.println("Sources B");
        System.out.println(graph.sources("B"));
        System.out.println("Sources D");
        System.out.println(graph.sources("D"));
        System.out.println("Sources E");
        System.out.println(graph.sources("E"));



    }
    
}
