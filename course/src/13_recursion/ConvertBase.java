
public class ConvertBase{

  private static String toStr(Integer remainder) {
    return Character.toString("0123456789".charAt(remainder));
  }

  public static String stringValue(Integer n, Integer base) {

    Integer quotient = n/base;
    Integer remainder = n%base;

    // Base Case
    if (quotient.equals(0)) {
      return toStr(remainder);
    }

    // relate subproblems
    String currentLevel = toStr(remainder);
    String depeerLevel = stringValue(quotient, base);

    return depeerLevel + currentLevel;

  }

  public static void main(String args[]) {
    Integer n = 16;
    Integer base10 = 10;
    Integer base2 = 2;
    
    System.out.println(stringValue(n, base10));
    System.out.println(stringValue(n, base2));
}
}
