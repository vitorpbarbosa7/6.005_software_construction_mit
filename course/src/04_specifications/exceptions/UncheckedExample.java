public class UncheckedExample {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3};
        
        // Error in Runtime, not Checked Exception, but Unchecked Exception
        // Attempting to access an index that is out of bounds
        System.out.println(numbers[5]);  // This will throw an ArrayIndexOutOfBoundsException
    }
}

