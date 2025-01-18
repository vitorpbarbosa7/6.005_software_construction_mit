/** Represents an immutable set of elements of type E. */
interface Set<E> {
  /** make an empty set */
  public Set();
  /** @return true if this set contains e as a member */
  public boolean contains(E e);
  /** @return a set which is the union of this and that */
  public ArraySet<E> union(Set<E> that);
}

  /** Implementation of Set<E>. */
  class ArraySet<E> implements Set<E> {
    /** make an empty set */
    public ArraySet() { ... }
    /** @ return a set which is the union of this and that */
    public ArraySet<E> union(Set<E> that) { ... }
    /** add e to this set */
    public void add(E e) { ... }
  }
