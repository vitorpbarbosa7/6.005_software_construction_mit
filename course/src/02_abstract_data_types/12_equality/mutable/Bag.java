/** make an empty bag */
public Bag<E>()

// mutator
/** modify this bag by adding an occurrence of e, and return this bag */
public Bag<E> add(E e)

// mutator
/** modify this bag by removing an occurence of (if any), and return this bag */
public Bag<E> remove(E e)

// observer
/** return number of times e occurs in this bag */
public int count(E e)

// Code to run 
Bag<String> b1 = new Bag<>().add("a").add("b");
Bag<String> b2 = new Bag<>().add("a").add("b");
Bag<String> b3 = b1.remove("b");
Bag<String> b4 = new Bag<>().add("b").add("a"); // swap
