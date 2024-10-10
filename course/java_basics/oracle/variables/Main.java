class Car {
    // Class variable (shared across all instances)
    public static String manufacturer = "Generic Motors";
    
    // Instance variables (unique for each instance)
    private String make;
    private String model;
    private int year;
    private int speed;

    // Constructor (parameters are local variables)
    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.speed = 0; // Local variable in constructor
    }

    // Public method
    public void start() {
        this.speed = 0;  // Local variable inside method
        System.out.println(this.make + " " + this.model + " started.");
    }

    // Public method with parameter (parameter is a local variable)
    public void accelerate(int increase) {
        // Local variable `increase`
        this.speed += increase;
        System.out.println(this.make + " " + this.model + " accelerating. Speed: " + this.speed);
    }

    // Private method (only accessible within this class)
    private void checkOil() {
        System.out.println(this.make + " " + this.model + ": Oil level checked.");
    }

    // Getter for private field
    public int getSpeed() {
        return this.speed;
    }

    // Public method to call private method
    public void service() {
        checkOil();  // Calling private method within class
    }
}

// Main class to test the Car class
public class Main {
    public static void main(String[] args) {
        // Create an instance of Car
        Car car1 = new Car("Toyota", "Corolla", 2020);
        car1.start();
        car1.accelerate(30);

        // Access class variable
        System.out.println("Manufacturer: " + Car.manufacturer);

        // Access instance variable via public method
        System.out.println("Speed: " + car1.getSpeed());

        // Call a public method that internally calls a private method
        car1.service();
    }
}
