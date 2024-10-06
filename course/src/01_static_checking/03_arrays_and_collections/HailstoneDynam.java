import java.util.List;
import java.util.ArrayList;


public class HailstoneDynam {
  public static void main(String[] args) {
   
    List<Integer> list = new ArrayList<Integer>();
    int n = 3;
    while (n != 1) {
      list.add(n);
      if (n % 2 == 0) { 
        n  = n /2;
      } else {
        n = 3 * n + 1;
      }
    }

    // Use a for loop to print each value in the array
    System.out.println("Array elements are:");
    for (int j = 0; j < list.size(); j++) {
        System.out.println(list.get(j));
    }

    int max = 0;
    for (int x: list) {
      max = Math.max(x, max);
    }
    System.out.println(max);
  }
}
