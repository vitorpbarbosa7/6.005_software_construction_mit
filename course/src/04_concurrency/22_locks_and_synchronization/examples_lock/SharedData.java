class SharedData {
    private int balance = 0;
    private final Object lock = new Object(); // Shared lock

    // a operation which needs to use the syncronized lock on the account
    public void deposit(int amount) {
        synchronized (lock) { // Acquires the lock
            balance += amount;
        } // Releases the lock
    }

    // a operation which needs to use the syncronized lock on the account
    public int getBalance() {
        synchronized (lock) { // Acquires the same lock
            return balance;
        } // Releases the lock
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SharedData account = new SharedData();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                account.deposit(1);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final Balance: " + account.getBalance()); // Always prints 2000
    }
}

