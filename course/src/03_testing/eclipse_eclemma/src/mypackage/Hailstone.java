package mypackage;

public class Hailstone {
  public static void main(String[] args) {
    int n = 1;
    while (n != 1) {
        if (n % 2 == 0) {
            n = n / 2;
        } else {
            n = 3 * n + 1;
        }
    }
  }
}
