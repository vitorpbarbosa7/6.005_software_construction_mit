import java.util.ArrayList;
import java.util.Iterator;

public class RemoveExample {
    public static void main(String[] args) {
        // Create an ArrayList with some subjects
        ArrayList<String> subjects = new ArrayList<>();
        subjects.add("6.045");
        subjects.add("6.005");
        subjects.add("6.813");
        subjects.add("2.001");

        // Use an iterator to safely remove elements starting with "6."
        Iterator<String> iter = subjects.iterator();
        while (iter.hasNext()) {
            String subject = iter.next();
            if (subject.startsWith("6.")) {
                iter.remove(); // Safely remove using the iterator's remove method
            }
        }

        // Print the remaining subjects
        System.out.println("Remaining subjects: " + subjects);
    }
}

