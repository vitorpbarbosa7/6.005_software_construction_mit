public class MyPerson {
    // Instance variables
    private String name;
    private int age;

    // Constructor
    public MyPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Method to simulate the speak method in Python
    public String speak() {
        return "My name is " + name + " and my age is " + age;
    }

    // equivalent of __main__ --> if __name__ == '__main__' in python 
    // used only when running directly, not when imported
    // Main method for testing
    public static void main(String[] args) {
        MyPerson person = new MyPerson("John", 30);
        System.out.println(person.speak()); // Output: My name is John and my age is 30
    }
}
