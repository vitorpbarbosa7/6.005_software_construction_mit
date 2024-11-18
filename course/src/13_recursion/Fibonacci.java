public class Fibonacci {

    public static int fibonacci(int n) { 

      if (n == 0 || n == 1) {
        // Base case
        return 1;
      } else {
        // Inductive step
        return fibonacci(n-2)+fibonacci(n-1);
      }
    }

    public static void main(String args[]) {
      System.out.println(fibonacci(4));
      System.out.println(fibonacci(5));
      System.out.println(fibonacci(6));
    }
}
