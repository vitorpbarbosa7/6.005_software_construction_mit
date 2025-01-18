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
    // Rep invariant: in, out != null
    
    // two queues, one for requests, and another for replies
    // instead of protocol, with sockets and so on 
    // use two differente synchronized queues, for requests and replies
    /**
     * Make a squarer that will listen for requests and generate replies.
     * @param requests queue to receive requests from
     * @param replies queue to send replies to
     */
    public Squarer(BlockingQueue<Integer> requests, BlockingQueue<SquareResult> replies) {
        this.in = requests;
        this.out = replies;
    }
    
    /**
     * Start handling squaring requests.
     */
    public void start() {
        // anonymous Class Runnable for implementing the function which can be threaded
        // oh fuck it is obvious, when call start, start new Thread 
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    // TODO: we may want a way to stop the thread
                    try {
                        // blockings are importante, so here we see that will block until there is something in the queue
                        // block until a request arrives
                        // a request:
                        int x = in.take();
                        // compute te answer and send it back
                        // does what must be done
                        int y = x * x;
                        // send this out to the queue again 
                        // a reply
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
    // final and private attributes, fields, for this ADT
    private final int input;
    private final int output;
    
    /**
     * Make a new result message.
     * @param input input number
     * @param output square of input
     */
    public SquareResult(int input, int output) {
        this.input = input;
        this.output = output;
    }
    
    // TODO: we will want more observers, but for now...
    
    @Override public String toString() {
        return input + "^2 = " + output;
    }
}

public class SquareQueue {
    
    /**
     * Create and use a squarer.
     */
    public static void main(String[] args) {
        
        BlockingQueue<Integer> requests = new LinkedBlockingQueue<>();
        BlockingQueue<SquareResult> replies = new LinkedBlockingQueue<>();
        
        Squarer squarer = new Squarer(requests, replies);
        // the thread is open, so now I can externally put things in the 
        // queues
        squarer.start();
        
        try {
            // make a request
            requests.put(42);
            // read the reply
            System.out.println(replies.take());

            requests.put(2);
            requests.put(3);
            requests.put(4);
            requests.put(5);
            System.out.println(replies.take());
            // 4 and 5 are in the replies to be sent yet
            
            // maybe do something concurrently...
            
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
