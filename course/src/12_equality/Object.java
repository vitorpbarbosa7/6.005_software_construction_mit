// We should override this, because this is referential equality 
// And for Immutable types, they will point to different objects
// Different locations in memory
// With Mutable data types, they can point to the same object?
public class Object {

  public boolean equals(Object that) {
    return this == that;
  }
}
