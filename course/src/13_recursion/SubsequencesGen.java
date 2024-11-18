public class SubsequencesGen {

  public static String subsequences(String word) {
    if (word.isEmpty()) {
      // base case
      return "";
    } else {
      // inductive step
      
      // get first letter from current word
      char firstLetter = word.charAt(0);
      String restOfWord = word.substring(1);
    
      // at last level this will return something, so we apply the rule for other returnings down
      String subsequencesOfRest = subsequences(restOfWord);

      String result = "";
      // sequences are separated by ","
      for (String subsequence : subsequencesOfRest.split(",", -1)) {
        // does not incluce the letter
        result += "," + subsequence;
        // subsequence that includes the letter
        result += "," + firstLetter + subsequence;
      }
      result = result.substring(1); // just remove extra leading comma
      return result;
    }
  }
  public static void main(String args[]) {
    System.out.println(subsequences("abc"));
  }
}
        
