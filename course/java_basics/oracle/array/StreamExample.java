import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // Using Java Stream to filter and map values (similar to Python's list comprehension)
        List<Integer> squaredEvens = numbers.stream()
            .filter(n -> n % 2 == 0)  // Filter even numbers
            .map(n -> n * n)          // Square the even numbers
            .collect(Collectors.toList());  // Collect into a list

        System.out.println(squaredEvens);  // Output: [4, 16, 36]
    }
}
