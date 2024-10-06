class Animal {
    public void speak() {
        System.out.println("The animal speaks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        myAnimal.fly();  // Error: fly() is not defined for class Animal
    }
}
