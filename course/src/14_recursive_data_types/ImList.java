public interface ImList<E> {
    // Return an empty list
    public static <E> ImList<E> empty() {
        return new Empty<>();
    }

    // Add an element to the front of the list (prepend it)
  // constructor return the list with the new element added as head
    public ImList<E> cons(E e);

    // Return the first element of the list
  // Content of Address Register
    public E first();

    // Return the tail of the list
  // Equivalent of cdr, Content of Decrement Register, that is the tail
  public ImList<E> rest();
} 

class Empty<E> implements ImList<E> {
    public Empty() {
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
}

class Cons<E> implements ImList<E> {
    private final E e;
    private final ImList<E> rest;

    public Cons(E e, ImList<E> rest) {
        this.e = e;
        this.rest = rest;
    }
    public ImList<E> cons(E e) {
        return new Cons<>(e, this);
    }
    public E first() {
        return e;
    }
    public ImList<E> rest() {
        return rest;
    }
}

// Testing the implementation
class Main {
    public static void main(String[] args) {
        ImList<Integer> list = ImList.empty();
        list = list.cons(1).cons(2).cons(3); // Creates [3, 2, 1]

        System.out.println(list.first());  // Output: 3
        System.out.println(list.rest().first()); // Output: 2
    }
}
