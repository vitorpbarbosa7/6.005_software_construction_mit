/**
 * This program demonstrates two approaches to working with threads in Java:
 * 1. Using the Runnable interface (Composition)
 * 2. Subclassing the Thread class (Inheritance)
 * 
 * Both approaches achieve the same goal: executing code in a new thread.
 * The program explains the benefits and limitations of each approach.
 */
public class ThreadExample {

    /**
     * HelloRunnable class demonstrates the Composition approach. 
     * It implements the Runnable interface, and the run() method contains 
     * the code that will be executed by the thread. The Runnable object 
     * is passed to the Thread constructor.
     */
    public static class HelloRunnable implements Runnable {

        /**
         * This is the run method that defines the task to be executed by the thread.
         */
        public void run() {
            System.out.println("Hello from a thread (using Runnable)!");
        }

        /**
         * Main method that creates a new Thread using the Runnable object 
         * and starts the thread.
         */
        public static void main(String[] args) {
            // Using Composition: Passing Runnable object to Thread
            (new Thread(new HelloRunnable())).start();
        }
    }

    /**
     * HelloThread class demonstrates the Inheritance approach.
     * It extends the Thread class and overrides the run() method to 
     * define the task to be executed by the thread.
     */
    public static class HelloThread extends Thread {

        /**
         * This is the run method that defines the task to be executed by the thread.
         */
        public void run() {
            System.out.println("Hello from a thread (using Inheritance)!");
        }

        /**
         * Main method that creates a new HelloThread object and starts the thread.
         */
        public static void main(String[] args) {
            // Using Inheritance: Creating a subclass of Thread
            (new HelloThread()).start();
        }
    }

    /**
     * Summary:
     * 
     * In the first approach (HelloRunnable), a Runnable object is passed to 
     * the Thread constructor. This method is more general because the Runnable 
     * object can implement any class, not just Thread. It is more flexible 
     * and applies well to high-level thread management APIs.
     * 
     * In the second approach (HelloThread), the task class must inherit from 
     * the Thread class, limiting its flexibility. However, this method is simpler 
     * for basic use cases.
     */
}

