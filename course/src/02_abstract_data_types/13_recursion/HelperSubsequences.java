public class HelperSubsequences {

  private static String subsequencesAfter(String partialSubsequence, String word) {
    if (word.isEmpty()) {
      // base case
      return partialSubsequence;
    } else {
      // inductive step
      // proved to work on k + 1
      String withoutCharPartial = partialSubsequence;
      String withoutChar = subsequencesAfter(withoutCharPartial, word.substring(1));
      String separator = ",";
      String withCharPartial = partialSubsequence + word.charAt(0);
      String withChar = subsequencesAfter(withCharPartial, word.substring(1));
      return withoutChar + separator + withChar;
    }
  }

  public static String subsequences(String word) {
    return subsequencesAfter("", word);
  }

  public static void main(String args[]) {
    System.out.println(subsequences("abc"));
  }
}

