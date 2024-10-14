public class MyClass {
    public static void main(String[] args) {
        // Create an instance of MyClass
        MyClass myClassInstance = new MyClass();
        myClassInstance.display();

        // Create an instance of AnotherClass (non-public class)
        AnotherClass anotherClassInstance = new AnotherClass();
        anotherClassInstance.sayHello();
    }

    public void display() {
        System.out.println("This is the public class: MyClass");
    }
}

// private by default
class AnotherClass {
    public void sayHello() {
        System.out.println("This is the non-public class: AnotherClass");
    }
}
