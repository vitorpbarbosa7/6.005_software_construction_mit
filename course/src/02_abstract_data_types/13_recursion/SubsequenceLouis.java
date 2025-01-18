public class SubsequenceLouis {

    private static String partialSubsequence = "";

    public static String subsequencesLouis(String word) {
        System.out.println("Entering subsequencesLouis with word: \"" + word + "\" and partialSubsequence: \"" + partialSubsequence + "\"");

        if (word.isEmpty()) {
            // Base case
            System.out.println("Base case reached with partialSubsequence: \"" + partialSubsequence + "\"");
            return partialSubsequence;
        } else {
            // Recursive step
            System.out.println("Recursive step: word = \"" + word + "\", partialSubsequence = \"" + partialSubsequence + "\"");

            // Compute subsequences without the first letter
            String withoutFirstLetter = subsequencesLouis(word.substring(1));
            System.out.println("After withoutFirstLetter: \"" + withoutFirstLetter + "\" for word: \"" + word + "\"");

            // Include the first letter in the partial subsequence
            partialSubsequence += word.charAt(0);
            System.out.println("Updated partialSubsequence: \"" + partialSubsequence + "\"");

            // Compute subsequences with the first letter
            String withFirstLetter = subsequencesLouis(word.substring(1));
            System.out.println("After withFirstLetter: \"" + withFirstLetter + "\" for word: \"" + word + "\"");

            // Combine results
            String result = withoutFirstLetter + "," + withFirstLetter;
            System.out.println("Returning result: \"" + result + "\" for word: \"" + word + "\"");

            return result;
        }
    }

    public static void main(String[] args) {
        String word = "c";
        System.out.println("Subsequences for word \"" + word + "\":");
        String result = subsequencesLouis(word);
        System.out.println("Final Result: \"" + result + "\"");
    }
}

