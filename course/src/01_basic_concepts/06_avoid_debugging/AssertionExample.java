public class AssertionExample {
    public static void main(String[] args) {
        int x = 5;
        assert x > 10 : "Assertion failed: x is not greater than 10";
        System.out.println("Program continues...");
    }
}

