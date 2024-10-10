public class Person {
    // Fields (or member variables)
    String name;  // Instance variable (belongs to the object)
    int age;      // Instance variable

    static int population = 0;  // Static field (belongs to the class)

    // Constructor to initialize fields
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        population++;
    }
}
