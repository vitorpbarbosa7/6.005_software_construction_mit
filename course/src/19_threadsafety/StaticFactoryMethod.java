class StaticFactoryMethod {
    private static final StaticFactoryMethod INSTANCE = new StaticFactoryMethod();

    // Private constructor ensures the class can't be instantiated outside
    private StaticFactoryMethod() {}

    // Static factory method to get the single instance
    public static StaticFactoryMethod getInstance() {
        return INSTANCE;
    }

    public void doSomething() {
        System.out.println("Singleton instance is doing something!");
    }
}

public class Main {
    public static void main(String[] args) {
        // Access the singleton instance via the static factory method
        StaticFactoryMethod instance1 = StaticFactoryMethod.getInstance();
        StaticFactoryMethod instance2 = StaticFactoryMethod.getInstance();
        
        // Verify that both references point to the same object
        System.out.println(instance1 == instance2); // Output: true

        // Call a method on the singleton instance
        instance1.doSomething();
    }
}

