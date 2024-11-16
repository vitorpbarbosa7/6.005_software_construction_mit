interface Duck {
    void quack();
    void swim();
}

class RealDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("Quack!");
    }
    @Override
    public void swim() {
        System.out.println("Swimming in the pond!");
    }
}

class Dog {
    public void quack() {
        System.out.println("Bark like a duck!");
    }
    public void swim() {
        System.out.println("Swimming like a dog!");
    }
}

public class Main {
    public static void makeItQuackAndSwim(Duck duck) {
        duck.quack();
        duck.swim();
    }

    public static void main(String[] args) {
        RealDuck duck = new RealDuck();
        makeItQuackAndSwim(duck); // Works, RealDuck implements Duck

        // Dog dog = new Dog();
        // makeItQuackAndSwim(dog); // Compile-time error: Dog is not a Duck
    }
}

