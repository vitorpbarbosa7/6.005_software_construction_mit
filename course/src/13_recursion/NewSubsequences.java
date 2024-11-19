public class NewSubsequences {

  public static String subsequences(String word) {

    if (word.isEmpty()) {
      return ""; // base case
    } else {
      char firstLetter = word.charAt(0);
      String restOfWord = word.substring(1);
      
      // call to solve subproblem on restOfWord
      String subsequencesOfRest = subsequences(restOfWord);

      // Relate subproblems
      // construct the result using the current letter with subsequences, and not
      String result = "";
      for (String subsequence: subsequencesOfRest.split(",", -1)) { 
        // without the word
        result += "," + subsequence;
        // with the word
        result += "," + firstLetter + subsequence;
      }
      // so result is the new subsequence to be returned;

      // remove initial ","
      if (result.startsWith(",")) result = result.substring(1);
      return result;

    }
  }

  public static void main(String args[]) {
    String word = "abc";
    System.out.println(subsequences(word));
  }
}
