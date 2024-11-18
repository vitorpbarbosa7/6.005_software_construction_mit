public class HelperSubsequences {

  private static String subsequencesAfter(String partialSubsequence, String word) {
    if (word.isEmpty()) {
      // base case
      return partialSubsequence;
    } else {
      // inductive step
      // proved to work on k + 1
      String withoutWordPartial = partialSubsequence;
      String withoutWord = subsequencesAfter(withoutWordPartial, word.substring(1));
      String separator = ",";
      String withWordPartial = partialSubsequence + word.charAt(0);
      String withWord = subsequencesAfter(withWordPartial, word.substring(1));
      return withoutWord + separator + withWord;
    }
  }

  public static String subsequences(String word) {
    return subsequencesAfter("", word);
  }

  public static void main(String args[]) {
    System.out.println(subsequences("abc"));
  }
}

