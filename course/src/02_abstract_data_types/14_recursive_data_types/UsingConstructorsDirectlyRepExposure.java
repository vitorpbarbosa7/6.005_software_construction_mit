public class Client {
    public static void main(String[] args) {
        // Directly creating an Empty instance
        ImList<Integer> emptyList = new Empty<>();

        // Directly creating a Cons instance, bypassing the ImList interface
        ImList<Integer> badList = new Cons<>(1, emptyList);

        // This bypasses the intended API of ImList and uses the internal representation
        System.out.println(badList.first()); // Output: 1
        System.out.println(badList.isEmpty()); // Output: false

        // Bypassing the static empty() method and creating inconsistencies
        Empty<Integer> empty = new Empty<>();
        System.out.println(empty.isEmpty()); // Output: true
    }
}

