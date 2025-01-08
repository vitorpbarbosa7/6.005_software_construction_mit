

public class MyString {
  private final char[] a;
  // Thread safety argument:
  //    This class is threadsafe because it's immutable:
  //    - a is final
  //    - a points to a mutable char array, but the array is encapsulated in this object
  //      not shared with any other object or exposed to a client


public class MyString {
  // not exposed
  private final char[] a;
  // not exposed
  private final int start;
  // not exposed
  private final int len;

  // Rep Invariant:
  //    0 <= start <= a.length
  //    0 <= len <= a.length-start
  // Abstraction Function:
  //    representes the string of characters a[start],...,a[start+length-1]
  // Thread safety argument:
  //    This class is threadsafe because it's immutable
  //    - a, start, and len are final
  //    - a points to a mutable char array, which may be shared with other String objects
  //      but they never mutate it (why do not mutate?)
  //    - the array is never exposed to a client ( was not shared?)

  // no setter methods
