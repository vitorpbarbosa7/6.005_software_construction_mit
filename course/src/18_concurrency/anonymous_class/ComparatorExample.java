import java.util.Arrays;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A comparison function that imposes a total ordering on some objects.
 * This interface defines the compare method to compare two objects for order.
 */
public class ComparatorExample {

    /** 
     * Orders Strings by length (shorter first) and then lexicographically.
     */
    public static class StringLengthComparator implements Comparator<String> {

        /**
         * Compares two Strings by their length, and if they are of equal length, compares lexicographically.
         * 
         * @param s1 the first String to compare
         * @param s2 the second String to compare
         * @return a negative integer, zero, or a positive integer as the first String is shorter, equal to,
         *         or longer than the second String, respectively. If they are of equal length, a lexicographical
         *         comparison is made.
         */
        @Override
        public int compare(String s1, String s2) {
            if (s1.length() == s2.length()) {
                return s1.compareTo(s2); // lexicographical comparison
            }
            return s1.length() - s2.length(); // compare by length
        }
    }

    /**
     * Main method that demonstrates the usage of the StringLengthComparator for sorting.
     */
    public static void main(String[] args) {
        // SortedSet without a Comparator, uses natural ordering (lexicographical order)
        SortedSet<String> stringsWithoutComparator = new TreeSet<>();
        stringsWithoutComparator.addAll(Arrays.asList("yolanda", "zach", "alice", "bob"));
        System.out.println("Sorted without comparator: " + stringsWithoutComparator);
        // Output: Sorted without comparator: [alice, bob, yolanda, zach]

        // Using StringLengthComparator to sort by string length and then lexicographically
        Comparator<String> compareByLength = new StringLengthComparator();
        SortedSet<String> stringsWithComparator = new TreeSet<>(compareByLength);
        stringsWithComparator.addAll(Arrays.asList("yolanda", "zach", "alice", "bob"));
        System.out.println("Sorted with StringLengthComparator: " + stringsWithComparator);
        // Output: Sorted with StringLengthComparator: [bob, zach, alice, yolanda]

        // Alternative: directly using the comparator without storing it in a variable
        // Without creating an exclusive object to be reused
        SortedSet<String> stringsDirectComparator = new TreeSet<>(new StringLengthComparator());
        stringsDirectComparator.addAll(Arrays.asList("yolanda", "zach", "alice", "bob"));
        System.out.println("Sorted directly with StringLengthComparator: " + stringsDirectComparator);
        // Output: Sorted directly with StringLengthComparator: [bob, zach, alice, yolanda]
    }
}

