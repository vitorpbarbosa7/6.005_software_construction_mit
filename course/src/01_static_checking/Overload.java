
public class Overload {
  public static void main(String[] args) {
      Calculator calc = new Calculator();

      // Calls the method for integers
      System.out.println(calc.add(5, 10));  // Output: 15

      // Calls the method for doubles
      System.out.println(calc.add(5.5, 2.3));  // Output: 7.8

      // Calls the method for strings
      System.out.println(calc.add("Hello", " World"));  // Output: Hello World
  }
}
