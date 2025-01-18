class Tuple<A, B> {
    public final A first;
    public final B second;

    public Tuple(A first, B second) {
        this.first = first;
        this.second = second;
    }
}

public class Main {
    public static void main(String[] args) {
        Tuple<String, Integer> tuple = new Tuple<>("Alice", 25);
        System.out.println("Name: " + tuple.first);
        System.out.println("Age: " + tuple.second);
    }
}

