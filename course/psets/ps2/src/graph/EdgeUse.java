package graph;

public class EdgeUse {
    public static void main(String args[]){

        Edge edge1 = new Edge("A", "B", 5);
        Edge edge2 = new Edge("A", "B", 5);

        System.out.println(edge1.equals(edge2));

        System.out.println(edge1.getSource());
        System.out.println(edge1.getTarget());
        System.out.println(edge1.getWeight());
        
        System.out.println(edge1.hashCode());
    }
}
