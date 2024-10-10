package classcreation;
import static java.lang.System.*;

public class ComputerCreator {

    private String aliasName;
    private int year;
    
//    How to create variables that are the same for each instance?
//    If we change the value in one class each will change in the others
    
    protected static int count = 1;  
    
    // Constructor (__init__ in python?)
    public ComputerCreator(String aliasName, int year) {
        this.aliasName = aliasName;
        this.year = year;
        this.display2();
        
        ComputerCreator.count += 1;
        
        ComputerCreator.display();
        addYears(2);
    }
    
    public static void display() {
    	System.out.println("I am a computer \n");
    }
    
    public void display2() {
    	System.out.print("This is not static \n");
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