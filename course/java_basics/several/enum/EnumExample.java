
// Class that uses the enum
public class EnumExample {
// Define the enum
    public enum Month {
        JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, 
        JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
    }
    public static void main(String[] args) {
        Month currentMonth = Month.JANUARY;

        switch (currentMonth) {
            case JANUARY:
                System.out.println("It's January!");
                break;
            case FEBRUARY:
                System.out.println("It's February!");
                break;
            default:
                System.out.println("Some other month.");
                break;
        }

        // Loop through all the months
        for (Month month : Month.values()) {
            System.out.println(month);
        }
    }
}
