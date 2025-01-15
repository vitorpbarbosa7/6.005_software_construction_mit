public class Deadlock {
    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void bow(Friend bower) {
            // Order locks based on hash code to ensure consistent locking order
            Friend firstLock = System.identityHashCode(this) < System.identityHashCode(bower) ? this : bower;
            Friend secondLock = System.identityHashCode(this) < System.identityHashCode(bower) ? bower : this;

            // Synchronized block to prevent deadlock
            synchronized (firstLock) { // Lock the first object
                synchronized (secondLock) { // Then lock the second object
                    System.out.format("%s: %s has bowed to me!%n", this.name, bower.getName());
                    bower.bowBack(this); // Safe because locks are acquired in a consistent order
                }
            }
        }

        public void bowBack(Friend bower) {
            // Locking happens in the `bow` method, so this is safe
            System.out.format("%s: %s has bowed back to me!%n", this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");

        // Thread 1: Alphonse tries to bow to Gaston
        new Thread(() -> alphonse.bow(gaston)).start();

        // Thread 2: Gaston tries to bow to Alphonse
        new Thread(() -> gaston.bow(alphonse)).start();
    }
}

