import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(-5);
        numbers.add(3);
        numbers.add(-2);
        numbers.add(7);
      
        System.out.println(numbers);
        int result = sumOfAbsoluteValues(numbers);
        System.out.println(numbers);
        System.out.println("Sum of absolute values: " + result);
        System.out.println("Sum of values: " + sum(numbers));
    }

    /**
     * @return the sum of the absolute values of the numbers in the list
     */
    public static int sumOfAbsoluteValues(List<Integer> list) {
        // let's reuse sum(), because DRY, so first we take absolute values
        for (int i = 0; i < list.size(); ++i) {
            list.set(i, Math.abs(list.get(i)));
        }
        return sum(list);
    }

    public static int sum(List<Integer> list) {
        int total = 0;
        for (int num : list) {
            total += num;
        }
        return total;
    }
}

