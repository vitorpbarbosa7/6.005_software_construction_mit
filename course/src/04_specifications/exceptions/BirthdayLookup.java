import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

// Custom exception class
class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}

// Class representing the birthday book
class BirthdayBook {

    // Constructor
    // initializes the birthdays private variable
    private Map<String, LocalDate> birthdays;

    // Finishes the constructor, by really instantiating this variable of HashMap
    public BirthdayBook() {
        birthdays = new HashMap<>();
    }

    // public method because I will use it in the object instantiated
    // Add a new birthday
    public void addBirthday(String name, LocalDate date) {
        birthdays.put(name, date);
    }

    // public method because I will use it in the object instantiated
    // Lookup method that throws NotFoundException if name is not found
    public LocalDate lookup(String name) throws NotFoundException {
        if (birthdays.containsKey(name)) {
            return birthdays.get(name);
        } else {
            throw new NotFoundException("Birthday not found for: " + name);
        }
    }
}

public class BirthdayLookup {
    public static void main(String[] args) {
        // Create a BirthdayBook instance and add some birthdays
        BirthdayBook birthdayBook = new BirthdayBook();
        birthdayBook.addBirthday("Alice", LocalDate.of(1995, 5, 10));
        birthdayBook.addBirthday("Bob", LocalDate.of(2000, 8, 25));

        //  case 1: Lookup a birthday that exists
        try {
            LocalDate aliceBirthday = birthdayBook.lookup("Alice");
            System.out.println("Alice's Birthday: " + aliceBirthday);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }

        //  case 2: Lookup a birthday that does not exist
        try {
            LocalDate charlieBirthday = birthdayBook.lookup("Charlie");
            System.out.println("Charlie's Birthday: " + charlieBirthday);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

