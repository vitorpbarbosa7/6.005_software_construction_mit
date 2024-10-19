public class OverflowExample {
    public static void main(String[] args) {
        int maxInt = Integer.MAX_VALUE;
        int overflow = maxInt + 1;

        System.out.println("Max int: " + maxInt);
        System.out.println("Binary of max int: " + Integer.toBinaryString(maxInt));

        System.out.println("After overflow: " + overflow);
        System.out.println("Binary of overflowed int: " + Integer.toBinaryString(overflow));
    }
}

