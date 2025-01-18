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
    // remove the public here if want to make
    // package-private
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
    // data augmentation as learned in 6.006
    private int size = 0;
    // rep invariant:
    //      e != null, rest != null, size >= 0;
    //      sie > 0 implies size == 1+rest.size();

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
        return this.e;
    }
    public ImList<E> rest() {
      // recursive, considering rest
      // the ones down the stack will consider their rests 
        return this.rest;
    }

    public int size() {
      // recursive, considering rest
      // data augmentation as in 6.006 (caching the size)
      // making retrieve it in O(1), not calculate always in O(n)
      // computes during construction of the last pointer 
      if (this.size == 0) {
        this.size = 1 + this.rest.size();
      }
      return this.size;
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
        ImList<Integer> emptyList = ImList.empty();
        list = list.cons(1).cons(2).cons(3); // Creates [3, 2, 1]

        System.out.println(list.first());  // Output: 3
        System.out.println(list.rest().first()); // Output: 2
        
        System.out.println(list.size());
        System.out.println(list.rest().size());

        System.out.println(emptyList.isEmpty());
        System.out.println(list.isEmpty());

    }
}
