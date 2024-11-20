The distinction between **declared type** and **actual type** in Java (and other statically-typed, object-oriented languages) exists because of **polymorphism** and **dynamic behavior at runtime**. Here's a clear explanation:

---

### **1. Why Two Types?**

#### **Declared Type**
- The **declared type** is the type the **compiler sees** during compile time. It's determined by the variable declaration or method return type.
- It tells the compiler what methods and properties the variable or expression is guaranteed to have.
- Example:
  ```java
  ImList<String> myList = ImList.empty();
  ```
  - **Declared type**: `ImList<String>`.
  - The compiler knows that `myList` is of type `ImList<String>`, so you can call methods defined in the `ImList` interface, like `cons`, `first`, and `rest`.

---

#### **Actual Type**
- The **actual type** is the **runtime type** of the object the variable is pointing to.
- It is the **real class type** of the object created using `new` at runtime.
- Example:
  ```java
  ImList<String> myList = ImList.empty();
  ```
  - When the program runs, `ImList.empty()` creates an `Empty<String>` object.
  - **Actual type**: `Empty<String>` (a class that implements `ImList<String>`).
  - The `myList` variable **declares** `ImList<String>` at compile time, but at runtime, the object is really of type `Empty<String>`.

---

### **2. Why This Distinction Matters**
#### **a. Polymorphism**
- Polymorphism allows a variable's declared type to be an interface or superclass, while its actual type is a class that implements the interface or extends the superclass.
- This enables **dynamic behavior**: you can write generic code using the interface but work with many possible concrete implementations at runtime.
- Example:
  ```java
  ImList<String> myList = ImList.empty();  // Declared as ImList<String>
  myList = myList.cons("hello").cons("world"); // Actual type changes to Cons<String>
  ```
  - At compile time, `myList` is treated as `ImList<String>` (declared type).
  - At runtime:
    - Initially, the actual type is `Empty<String>`.
    - After `cons("hello")`, the actual type is `Cons<String>`.
    - After `cons("world")`, the actual type is still `Cons<String>` (but a new `Cons` object is created with the previous list as `rest`).

---

#### **b. Interfaces and Abstract Classes**
- An **interface** (or abstract class) defines a common contract for behavior, but you cannot instantiate them directly.
- The actual type at runtime must always be a **concrete class** that implements the interface or extends the abstract class.
- Example:
  ```java
  ImList<String> myList = ImList.empty();  // Declared as ImList<String>
  // Actual type is Empty<String>, as returned by the static method empty()
  ```

---

### **3. Why Compile-Time Types Can't Always Be Used**

Even though Java is a compiled language, **runtime information is needed for polymorphism and dynamic behavior**:
- At compile time, the compiler doesn't know the specific type of an object created by `ImList.empty()`, only that it's an implementation of `ImList`.
- At runtime, Java's **dynamic dispatch** system ensures that the actual implementation of a method (e.g., `size()`) corresponds to the object's **actual type** (`Empty` or `Cons`).

---

### **4. Example to Show the Distinction**
```java
ImList<Integer> list = ImList.empty(); // Declared type: ImList<Integer>, Actual type: Empty<Integer>

list = list.cons(10).cons(20); 
// Declared type: ImList<Integer>, Actual type (after cons(10)): Cons<Integer>
// Actual type (after cons(20)): Cons<Integer>

// Declared type allows this:
System.out.println(list.size()); // Uses ImList's size() method

// But actual type determines behavior:
System.out.println(list.first()); // Dynamically dispatched to Cons's first()
```

---

### **5. What Happens if You Confuse Declared and Actual Types?**
If you try to call methods on the **actual type** that are not part of the **declared type**, you will get a **compile-time error**:
```java
ImList<Integer> list = ImList.empty();
// list.e; // Error: The field `e` is not part of ImList
```
This is because the compiler only sees the **declared type** (`ImList`), even though at runtime the actual object is `Cons`.

---

### **6. Key Takeaways**
- **Declared type**: What the compiler knows at compile time (based on variable or method signature).
- **Actual type**: What the object really is at runtime (based on `new` or factory methods).
- Polymorphism allows you to write generic, flexible code using interfaces or superclasses (declared types) while working with concrete implementations at runtime (actual types).
