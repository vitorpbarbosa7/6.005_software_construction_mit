public interface ImList<E> {
    // Return an empty list
    public static <E> ImList<E> empty() {
        return new Empty<>();
    }

    // Add an element to the front of the list (prepend it)
    public ImList<E> cons(E e);

    // Return the first element of the list
    public E first();

    // Return the tail of the list
    public ImList<E> rest();

    // Methods for size
    public int size();

    // Check if the list is empty
    public boolean isEmpty();
}

// Base case
class Empty<E> implements ImList<E> {
    // Package-private constructor
    Empty() {
    }

    public ImList<E> cons(E e) {
        return new Cons<>(e, this);
    }

    public E first() {
        throw new UnsupportedOperationException("Empty list has no first element.");
    }

    public ImList<E> rest() {
        throw new UnsupportedOperationException("Empty list has no rest.");
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }
}

// Recursive case of the datatype
class Cons<E> implements ImList<E> {
    private final E e;
    private final ImList<E> rest;
    private int size = 0;

    // Package-private constructor
    Cons(E e, ImList<E> rest) {
        this.e = e;
        this.rest = rest;
    }

    public ImList<E> cons(E e) {
        return new Cons<>(e, this);
    }

    public E first() {
        return this.e;
    }

    public ImList<E> rest() {
        return this.rest;
    }

    public int size() {
        if (this.size == 0) {
            this.size = 1 + this.rest.size();
        }
        return this.size;
    }

    public boolean isEmpty() {
        return false;
    }
}

// Testing the implementation
class Main {
    public static void main(String[] args) {
        ImList<Integer> list = ImList.empty();
        ImList<Integer> emptyList = ImList.empty();
        list = list.cons(1).cons(2).cons(3); // Creates [3, 2, 1]

        System.out.println(list.first());  // Output: 3
        System.out.println(list.rest().first()); // Output: 2
        System.out.println(list.size());  // Output: 3
        System.out.println(list.rest().size()); // Output: 2

        System.out.println(emptyList.isEmpty()); // Output: true
        System.out.println(list.isEmpty());      // Output: false
    }
}

