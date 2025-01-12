import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Squares integers.
 */
class Squarer {
    // Integer and SquareResult are threadsafe?
    // Immutable datatypes inside the BlockingQueue
    private final BlockingQueue<Integer> in;
    private final BlockingQueue<SquareResult> out;

    public Squarer(BlockingQueue<Integer> requests, BlockingQueue<SquareResult> replies) {
        this.in = requests;
        this.out = replies;
    }

    /**
     * Start handling squaring requests.
     */
    public void start() {
        // anonymous Class Runnable for implementing the function which can be threaded
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        // block until a request arrives
                        int x = in.take();
                        // compute the square
                        int y = x * x;
                        // send this out to the queue again as a reply
                        out.put(new SquareResult(x, y));
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

/**
 * An immutable squaring result message.
 */
class SquareResult {
    private final int input;
    private final int output;

    public SquareResult(int input, int output) {
        this.input = input;
        this.output = output;
    }

    @Override public String toString() {
        return input + "^2 = " + output;
    }
}

public class SquareQueueMulti {

    /**
     * Create and use a squarer.
     */
    public static void main(String[] args) {

        BlockingQueue<Integer> requests = new LinkedBlockingQueue<>();
        BlockingQueue<SquareResult> replies = new LinkedBlockingQueue<>();

				// the processor for the queue:
				// starts to wait:
        Squarer squarer = new Squarer(requests, replies);
        squarer.start(); // Start the squarer thread

        // Create and start 3 threads to send requests
        Thread thread1 = new Thread(new RequestSender(requests, 1, 2, 3));
        Thread thread2 = new Thread(new RequestSender(requests, 4, 5, 6));
        Thread thread3 = new Thread(new RequestSender(requests, 7, 8, 9));

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            // Read the replies and print them
						// not printing all of them
						// consumer
            System.out.println(replies.take());
            System.out.println(replies.take());
            System.out.println(replies.take());
            System.out.println(replies.take());
            System.out.println(replies.take());
            System.out.println(replies.take());
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}

/**
 * A thread that sends numbers to the requests queue.
 */
class RequestSender implements Runnable {

    private final BlockingQueue<Integer> requests;
    private final int[] numbers;

    public RequestSender(BlockingQueue<Integer> requests, int... numbers) {
        this.requests = requests;
        this.numbers = numbers;
    }

    @Override
    public void run() {
        try {
            for (int number : numbers) {
								// putting the numbers
                // producer
                requests.put(number); // Add numbers to the request queue
                System.out.println("Thread " + Thread.currentThread().getName() + " sent: " + number);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

