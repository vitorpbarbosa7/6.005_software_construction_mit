// Immutable type representing a rational number
public class RatNum {
  private final int numer;
  private final int denom;

  // Rep Invariant:
  //    denom > 0
  //    numer/denom is in reduced form, i.e. gcd(|numer|, denom) = 1
  // Abstraction Function:
  //    represents the rational number numer/denom
  // Safety from rep exposure:
  //    All fields are private, and all types in the rep are immutable (integers)
  
  // Operations (specs and method bodies omitted to save space)
  public RatNum(int n) { ... }
  public RatNum(int n, int d) throws ArithmeticException { ... }
  ...
}
