To compile and run your Java code from the terminal, follow these steps:

1. **Compile your code:**

   Use the `javac` command to compile your `.java` files. The `-d` option is used to specify where the compiled `.class` files should be stored. Typically, you store them in the `bin` directory.

   ```bash
   javac -d bin src/rules/RulesOf6005.java src/turtle/TurtleSoup.java
   ```

   This command will compile the `RulesOf6005.java` and `TurtleSoup.java` files and put the `.class` files in the `bin` directory, maintaining the package structure.

2. **Run the compiled program:**

   After compiling, you can run the program using the `java` command, specifying the class with the `main` method. You need to include the `bin` directory in your classpath because the compiled `.class` files are there.

   ```bash
   java -cp bin rules.RulesOf6005
   ```

   This command will execute the `main` method inside the `RulesOf6005` class.

### About `src` and `bin` Directories:

- **`src` (Source)**: This folder contains your raw `.java` source files. It is the directory where you write and store the code.
- **`bin` (Binary)**: This folder stores the compiled `.class` files (the bytecode). It's good practice to separate source code from compiled code.

### When `.class` Files Are Generated Locally (Not in `bin`):

If you compile without specifying the `-d` option, the `.class` files will be created in the same directory as the `.java` files. This isn't wrong per se, but it clutters your source folder and is not considered good practice. It's better to separate compiled files into a `bin` or `build` directory to keep your project organized.

### If it was a central project (like in an IDE like Eclipse):

In a central project, an IDE typically handles the compilation and execution automatically. If you set up your project correctly, it will compile everything and search for the `main` method to run, usually from a designated entry point class (e.g., `Main.java`).

In summary, use the `javac` command to compile your `.java` files and specify the `bin` directory for output. Then run your program using the `java` command, ensuring you set the correct classpath.