// Confinement

import java.math.BigInteger;

public class Factorial {

  /**
   * Computes n! and prints it on the standard output.
   * @param n must be >= 0
   */

  private static void computeFact(final int n) {
    BigInteger result = new BigInteger("1");
    for (int i = 1; i <= n; i++) {
      System.out.println("working on fact " + n);
      result = result.multiply(new BigInteger(String.valueOf(i)));
    }
    System.out.println("fact(" + n + ") = " + result);
}

    public static void main(String[] args) {
      // thread starts computeFact(99)
      new Thread(new Runnable() {
        public void run() {
          computeFact(99);
        }
      }).start();
      // main thread starts the computeFact(100)
      computeFact(100);

      // both threads might execute simultaneously 
      // they are threadsafe, because we are using local variables for the method computeFact
    }
}




