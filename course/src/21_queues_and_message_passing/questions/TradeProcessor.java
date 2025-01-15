// A simple Class with some structure
public interface Trade {
    public int numShares();
    public String stockName();
}

// Handles requests, so it is a producer?
// requests come from the trades itself?
public class TradeWorker implements Runnable {
    private final Queue<Trade> tradesQueue;
    
    // constructor
    public TradeWorker(Queue<Trade> tradesQueue) {
        this.tradesQueue = tradesQueue;
    }
    
    // no new thread was created here, only calling the TradeProcessor in the main thread
    public void run() {
        while (true) {
            Trade trade = tradesQueue.poll();
            // give the trade itself as object here
            TradeProcessor.handleTrade(trade.numShares(), trade.stockName());
        }
    }
}

// z
public class TradeProcessor {
    public static void handleTrade(int numShares, String stockName) {
        /* ... process the trade ... takes a while ... */
    }
}
