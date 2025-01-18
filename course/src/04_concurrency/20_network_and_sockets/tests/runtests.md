### Running Tests with JUnit 4:

1. **Ensure JUnit 4 is in your classpath** (`junit-4.13.2.jar`).

2. **Compile the test class:**

   ```bash
   javac -cp .:junit-4.13.2.jar *.java
   ```

3. **Run the test class with JUnit 4:**

   ```bash
   java -cp .:junit-4.13.2.jar org.junit.runner.JUnitCore SocketServerTest
   ```

