### Example 1: Compile-Time Error (Static Checking)

In Java, some errors can be caught during **compile time**, meaning before the program is ever run. These errors are related to the language syntax, type checking, or the presence of variables that don't exist.

**Code with Compile-Time Error:**
```java
public class CompileTimeErrorExample {
    public static void main(String[] args) {
        int x = "hello";  // Compile-time error: incompatible types
        System.out.println(x);
    }
}
```

**Explanation:**
- The error in this code happens because Java is statically typed, meaning that the type of each variable must be explicitly defined. Here, we are trying to assign a string (`"hello"`) to an integer variable (`x`). This type mismatch is caught during compilation, and the compiler will throw an error before it even runs the program.

**Compile-Time Error Message:**
```
CompileTimeErrorExample.java:3: error: incompatible types: String cannot be converted to int
        int x = "hello";  
                 ^
1 error
```

### Example 2: Runtime Error (Dynamic Checking)

Some errors can only be caught at **runtime**, meaning they occur after the code has successfully compiled but encounters an issue during execution.

**Code with Runtime Error:**
```java
public class RuntimeErrorExample {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3};
        System.out.println(numbers[5]);  // Runtime error: ArrayIndexOutOfBoundsException
    }
}
```

**Explanation:**
- This code compiles successfully because there are no syntax or type errors. However, it tries to access an array index (`numbers[5]`) that doesn't exist (since the array only has 3 elements). When the program runs and tries to access this invalid index, it throws an `ArrayIndexOutOfBoundsException`, resulting in a **runtime error**.

**Runtime Error Message:**
```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 3
```

### Example 3: Wrong Answer (No Errors, but Incorrect Logic)

Sometimes the program compiles and runs without errors, but it produces the wrong output because of a logical mistake.

**Code with Logical Error (Wrong Answer):**
```java
public class LogicalErrorExample {
    public static void main(String[] args) {
        int x = 5;
        int y = 10;
        int sum = x - y;  // Logical error: should be x + y
        System.out.println("Sum: " + sum);  // Prints: Sum: -5
    }
}
```

**Explanation:**
- This code compiles and runs without any issues. However, the logic is wrong because we are using subtraction (`x - y`) instead of addition (`x + y`). The result will be `-5` instead of the expected `15`. Even though there are no errors during compilation or runtime, the program gives the wrong result due to a mistake in logic.

### Summary:
1. **Compile-Time Error**: Caught by the Java compiler before the program is run (e.g., type mismatch).
2. **Runtime Error**: Occurs during program execution after successful compilation (e.g., accessing an invalid array index).
3. **Logical Error**: The program runs without errors but gives an incorrect result due to a mistake in logic.

This demonstrates the difference between **static checking** (compile-time) and **dynamic checking** (runtime). In Java, static checking catches many errors before the program is run, but some issues, like accessing an invalid array index, can only be detected at runtime.