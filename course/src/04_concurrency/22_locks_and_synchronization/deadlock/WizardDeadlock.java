public class WizardDeadlock {
    private final String name;
    private final Set<WizardDeadlock> friends;
    // Rep invariant:
    //    name, friends != null
    //    friend links are bidirectional: 
    //        for all f in friends, f.friends contains this
    // Concurrency argument:
    //    threadsafe by monitor pattern: all accesses to rep 
    //    are guarded by this object's lock

    public WizardDeadlock(String name) {
        this.name = name;
        this.friends = new HashSet<WizardDeadlock>();
    }

    public synchronized boolean isFriendsWith(WizardDeadlock that) {
        return this.friends.contains(that);
    }

    public synchronized void friend(WizardDeadlock that) {
        // if not there add it, if there, do not add it
        if (friends.add(that)) {
            that.friend(this);
        } 
    }

    public synchronized void defriend(WizardDeadlock that) {
        // if there, remove it, if not there, do not remove it
        if (friends.remove(that)) {
            that.defriend(this);
        } 
    }

    public static void main(String args[]) {
      WizardDeadlock harry = new WizardDeadlock("Harry Potter");
      WizardDeadlock snape = new WizardDeadlock("Severus Snape");
    
				// Runnable for Thread A
        Runnable threadARunnable = () -> {
            harry.friend(snape);
            harry.defriend(snape);
        };

        // Runnable for Thread B
        Runnable threadBRunnable = () -> {
            harry.friend(harry);
            harry.defriend(harry);
        };

        // Create and start threads
        Thread threadA = new Thread(threadARunnable);
        Thread threadB = new Thread(threadBRunnable);

        threadA.start();
        threadB.start();

        // Wait for threads to complete
        threadA.join();
        threadB.join();

        // Final check
        System.out.println("Harry's friends: " + harry.friends);
        System.out.println("Snape's friends: " + snape.friends);
}
