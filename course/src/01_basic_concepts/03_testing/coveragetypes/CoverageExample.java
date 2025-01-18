public class CoverageExample {
    
    public int absoluteValue(int x) {
        if (x < 0) {
            x = -x; // This line will execute if x is negative
        }
        return x; // This line will always execute
    }
    
    public int max(int a, int b) {
        if (a > b) {
            return a; // This line will execute if a is greater than b
        } else {
            return b; // This line will execute if b is greater than or equal to a
        }
    }
    
    public void loopExample(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(i); // This will print the value of i
        }
    }
    
    public static void main(String[] args) {
        CoverageExample example = new CoverageExample();
        
        // Statement coverage: We need to run all the statements in the program at least once.
        System.out.println("Absolute value of -5: " + example.absoluteValue(-5));
        System.out.println("Maximum of 3 and 7: " + example.max(3, 7));
        example.loopExample(3);
        
        // Branch coverage: Test both true and false cases for each condition.
        System.out.println("Absolute value of 5: " + example.absoluteValue(5));
        System.out.println("Maximum of 7 and 3: " + example.max(7, 3));
    }
}

