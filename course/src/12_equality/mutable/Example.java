import java.util.ArrayList;
import java.util.List;

public class Example {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = list1;  // Both references point to the same list

        list1.add("Hello");
        
        System.out.println(list1 == list2);           // true (same object)
        System.out.println(list1.equals(list2));      // true (same object and content)

        List<String> list3 = new ArrayList<>(list1);  // New object with same content
        System.out.println(list1 == list3);           // false (different objects)
        System.out.println(list1.equals(list3));      // true (same content)
    }
}

