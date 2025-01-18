import java.util.HashSet;
import java.util.Set;

public class WizardCoarseGrained {
    private final String name;
    private final Set<WizardCoarseGrained> friends;
    private final Castle castle; // Shared lock object for all Wizards in the same Castle

    // Rep invariant:
    // name, friends != null
    // friend links are bidirectional:
    // for all f in friends, f.friends contains this
    // Concurrency argument:
    // threadsafe by monitor pattern: all accesses to rep
    // are guarded by the castle's lock

    public WizardCoarseGrained(String name, Castle castle) {
        this.name = name;
        this.friends = new HashSet<>();
        this.castle = castle;
    }

    public boolean isFriendsWith(WizardCoarseGrained that) {
        synchronized (castle) { // Locking the castle for thread safety
            return this.friends.contains(that);
        }
    }

    public void friend(WizardCoarseGrained that) {
        synchronized (castle) { // Locking the castle for thread safety
            if (this.friends.add(that)) {
                that.friendInternal(this); // Establish bidirectional friendship
            }
        }
    }

    private void friendInternal(WizardCoarseGrained that) {
        synchronized (castle) { // Locking the castle for thread safety
            friends.add(that);
        }
    }

    public void defriend(WizardCoarseGrained that) {
        synchronized (castle) { // Locking the castle for thread safety
            if (this.friends.remove(that)) {
                that.defriendInternal(this); // Remove bidirectional friendship
            }
        }
    }

    private void defriendInternal(WizardCoarseGrained that) {
        synchronized (castle) { // Locking the castle for thread safety
            friends.remove(that);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) throws InterruptedException {
        /* ########################################################################## */
        /* ########################################################################## */
        /* ########################################################################## */
        Castle hogwarts = new Castle(); // Shared lock for all Wizards in this Castle
        /* ########################################################################## */
        /* ########################################################################## */
        /* ########################################################################## */

        WizardCoarseGrained harry = new WizardCoarseGrained("Harry Potter", hogwarts);
        WizardCoarseGrained snape = new WizardCoarseGrained("Severus Snape", hogwarts);

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

// Castle class acts as the global lock for all Wizards in the same castle
class Castle {
    // No fields are necessary; the Castle object is only used for its intrinsic lock
}

