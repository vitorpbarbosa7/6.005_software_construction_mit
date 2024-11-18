
public class ConvertBase{

  private static String toStr(Integer remainder) {
    // only 10 places here, if you want hexadecimal, change here
    return Character.toString("0123456789ABCDEF".charAt(remainder));
  }

  private static String helper(Integer n, Integer base) {

    Integer quotient = n/base;
    Integer remainder = n%base;

    // Base Case
    if (quotient.equals(0)) {
      return toStr(remainder);
    }

    // relate subproblems
    String currentLevel = toStr(remainder);
    String depeerLevel = helper(quotient, base);

    return depeerLevel + currentLevel;

  }

  public static String stringValue(Integer n, Integer base) {

    String sign = "";

    if (n <0){
      n = -n;
      sign = "-";
    }

    return sign + helper(n, base);
  }


  public static void main(String args[]) {
    Integer n = 16;
    Integer base10 = 10;
    Integer base2 = 2;
    
    System.out.println(stringValue(n, base10));
    System.out.println(stringValue(-n, base10));
    System.out.println(stringValue(n, base2));
    System.out.println(stringValue(-n, base2));
    System.out.println(stringValue(170, 16));
}
}
