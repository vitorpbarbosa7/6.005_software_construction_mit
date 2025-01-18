import java.util.HashSet;
import java.util.Set;

public class WizardLockOrdering {
    private final String name;
    private final Set<WizardLockOrdering> friends;

    // Rep invariant:
    // name, friends != null
    // friend links are bidirectional:
    // for all f in friends, f.friends contains this
    // Concurrency argument:
    // threadsafe by monitor pattern: all accesses to rep
    // are guarded by this object's lock

    public WizardLockOrdering(String name) {
        this.name = name;
        this.friends = new HashSet<>();
    }

    public synchronized boolean isFriendsWith(WizardLockOrdering that) {
        return this.friends.contains(that);
    }

    public void friend(WizardLockOrdering that) {
        WizardLockOrdering first, second;
        if (this.name.compareTo(that.name) < 0) {
            first = this;
            second = that;
        } else {
            first = that;
            second = this;
        }
				// lock on the first one decided by the comparison 
        // lock on the second one after the first one decided by the comparison
        synchronized (first) {
            synchronized (second) {
                if (friends.add(that)) {
                    that.friendInternal(this);
                }
            }
        }
    }

    private synchronized void friendInternal(WizardLockOrdering that) {
        friends.add(that);
    }

    public void defriend(WizardLockOrdering that) {
        WizardLockOrdering first, second;
        // lexicographical order, return a number, negative, zero, or positive
        if (this.name.compareTo(that.name) < 0) {
            first = this;
            second = that;
        } else {
            first = that;
            second = this;
        }
				// lock on the first one decided by the comparison 
        // lock on the second one after the first one decided by the comparison
        synchronized (first) {
            synchronized (second) {
                if (friends.remove(that)) {
                    that.defriendInternal(this);
                }
            }
        }
    }

    private synchronized void defriendInternal(WizardLockOrdering that) {
        friends.remove(that);
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) throws InterruptedException {
        WizardLockOrdering harry = new WizardLockOrdering("Harry Potter");
        WizardLockOrdering snape = new WizardLockOrdering("Severus Snape");

        // Runnable for Thread A
        Runnable threadARunnable = () -> {
            for (int i = 0; i < 100; i++) {
                harry.friend(snape);
                harry.defriend(snape);
            }
        };

        // Runnable for Thread B
        Runnable threadBRunnable = () -> {
            for (int i = 0; i < 100; i++) {
                snape.friend(harry);
                snape.defriend(harry);
            }
        };

        // Create and start threads
        Thread threadA = new Thread(threadARunnable);
        Thread threadB = new Thread(threadBRunnable);

        threadA.start();
        threadB.start();

        // Wait for threads to finish
        threadA.join();
        threadB.join();

        // Print final results
        System.out.println("Harry's friends: " + harry.friends);
        System.out.println("Snape's friends: " + snape.friends);
    }
}

