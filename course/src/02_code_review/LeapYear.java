
public class LeapYear {

    public static boolean isDivisibleBy(int number, int factor) {
        return number % factor == 0;
    }

    public static boolean isLeapYear(int year) {
        // 4 magic numbers
        if (isDivisibleBy(year, 400)) return true;
        else if (isDivisibleBy(year, 100)) return false;
        else return isDivisibleBy(year, 4);
    }

    public static void main(String args[]) {
        boolean result;
        result = isLeapYear(2028);

        System.out.println(result);
    }
}