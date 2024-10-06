public class Hailstone {
  public static void main(String[] args) {
    
    int[] a = new int[15];
    int i = 0;
    int n = 8;
    while (n != 1) {
      a[i] = n;
      i++;
      if (n % 2 == 0) { 
        n  = n /2;
      } else {
        n = 3 * n + 1;
      }
      a[i] = n;
      i++;
    }

    // Use a for loop to print each value in the array
    System.out.println("Array elements are:");
    for (int j = 0; j < a.length; j++) {
        System.out.println(a[j]);
    }

  }
}
