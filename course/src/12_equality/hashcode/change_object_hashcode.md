But why, what is really going on here 

Equality of Mutable Types
We've been focusing on equality of immutable objects so far in this reading. What about mutable objects?

Recall our definition: two objects are equal when they cannot be distinguished by observation. With mutable objects, there are two ways to interpret this:

when they cannot be distinguished by observation that doesn't change the state of the objects, i.e., by calling only observer, producer, and creator methods. This is often strictly called observational equality, since it tests whether the two objects “look” the same in the current state of the program.
when they cannot be distinguished by any observation, even state changes. This interpretation allows calling any methods on the two objects, including mutators. This is often called behavioral equality, since it tests whether the two objects will “behave” the same, in this and all future states.
For immutable objects, observational and behavioral equality are identical, because there aren't any mutator methods.

For mutable objects, it's tempting to implement strict observational equality. Java uses observational equality for most of its mutable data types, in fact. If two distinct List objects contain the same sequence of elements, then equals() reports that they are equal.

But using observational equality leads to subtle bugs and in fact allows us to easily break the rep invariants of other collection data structures. Suppose we make a List, and then drop it into a Set:

List<String> list = new ArrayList<>();
list.add("a");

Set<List<String>> set = new HashSet<List<String>>();
set.add(list);
We can check that the set contains the list we put in it, and it does:

set.contains(list) → true
But now we mutate the list:

list.add("goodbye");
And it no longer appears in the set!

set.contains(list) → false!
It's worse than that, in fact: when we iterate over the members of the set, we still find the list in there, but contains() says it's not there!

for (List<String> l : set) { 
    set.contains(l) → false! 
}
If the set's own iterator and its own contains() method disagree about whether an element is in the set, then the set clearly is broken. You can see this code in action on Online Java Tutor.


The issue here arises because, for mutable types, equality based on observational equality can lead to subtle bugs when the object's state changes after it has been added to a data structure that depends on immutability or consistent hashing for equality checks, like a Set or a HashMap.

What’s Going On with Observational Equality?
In Java, the Set data structure (specifically HashSet) relies on the hash code and equality of its elements to manage membership. When we add an element to a HashSet, the set uses the element's hash code to place it in a particular “bucket” and remembers its location based on the hash. If the element’s state changes in a way that changes its hash code, the set won’t be able to locate it reliably anymore.

Steps of the Problem
Initial Addition: When you add list to set, the set calculates the hash code of list based on its current contents (["a"]). This hash code is used to store list in a specific location within set.

Mutation of the Element: When you call list.add("goodbye"), you change the contents of list to ["a", "goodbye"]. Now, the list has a different hash code because its contents have changed.

Inconsistent Hash and Equality Check: Now, if you call set.contains(list), the set computes the hash of list again, but this new hash code doesn’t match the hash that was initially used to place it in the set. The set therefore looks in the wrong bucket and cannot find list. This leads to the unexpected and broken behavior you observe.


