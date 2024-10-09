package classcreation;
import static java.lang.System.*;

public class Computer {

    private String aliasName;
    private int year;
    
    // Constructor (__init__ in python?)
    public Computer(String aliasName, int year) {
        this.aliasName = aliasName;
        this.year = year;
        addYears(2);
    }

    public void boot() {
        out.println("Booting the " + this.aliasName + " from the year of " + 
        this.year);
    }

    // getter in python ?
    public int getYear() {
        return this.year;
    }

    // setter
    public void setAge(int year) {
        this.year = year;
    }

    private int addYears(int years) { 
        return this.year += years;
    }
}