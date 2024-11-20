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

  // methods for size, for this interface generic type
  public int size();

  public boolean isEmpty();
} 

// Base case
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

    public int size() { return 0; }

    // our beautiful base case concrete implementation of ImList
    public boolean isEmpty() {
      return true;
    }
}

// recursive case of the datatype
class Cons<E> implements ImList<E> {
    // base case
    private final E e;
    // recursive
    private final ImList<E> rest;

    // the concrete class Cons<E> uses the ImList interface here, even if the 
    // concrete implements the ImList itself
    public Cons(E e, ImList<E> rest) {
        // base case
        this.e = e;
        // recursive
        this.rest = rest;
    }
    // e is equal the new element
    // the new object is pointing to the current object
    public ImList<E> cons(E e) {
        return new Cons<>(e, this);
    }
    public E first() {
        // base case
        return e;
    }
    public ImList<E> rest() {
      // recursive, considering rest
      // the ones down the stack will consider their rests 
        return rest;
    }

    public int size() {
      // recursive, considering rest
      return 1 + rest.size();
    }

    public boolean isEmpty() {
        // recursive
        // in fact, the first element exists, so if it is not empty
        // return false?
        return false;
        // return !(rest.size()>0);
    }

    
}

// Testing the implementation
class Main {
    public static void main(String[] args) {
        ImList<Integer> list = ImList.empty();
        list = list.cons(1).cons(2).cons(3); // Creates [3, 2, 1]

        System.out.println(list.first());  // Output: 3
        System.out.println(list.rest().first()); // Output: 2
        
        System.out.println(list.size());
        System.out.println(list.rest().size());

    }
}
