public class Person {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) return false;
        Person that = (Person) obj;
        return this.lastName.equalsIgnoreCase(that.lastName);
    }

    @Override
    public int hashCode() {
        return lastName.toUpperCase().hashCode();
    }

    public static void main(String[] args) {
        Person person1 = new Person("Alice", "Smith");
        Person person2 = new Person("Bob", "smith");
        Person person3 = new Person("Charlie", "Johnson");

        System.out.println("person1 equals person2: " + person1.equals(person2));
        System.out.println("person1 hashCode: " + person1.hashCode());
        System.out.println("person2 hashCode: " + person2.hashCode());
        
        System.out.println("\nperson1 equals person3: " + person1.equals(person3));
        System.out.println("person1 hashCode: " + person1.hashCode());
        System.out.println("person3 hashCode: " + person3.hashCode());
    }
}

