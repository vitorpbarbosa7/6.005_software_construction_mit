// Bad argument:

/**
 * Why doesn't this argument work? String is indeed immutable and threadsafe; but the rep pointing to that string, specifically the text variable, is not immutable. text is not a final variable, and in fact it can't be final in this data type, because we need the data type to support insertion and deletion operations. So reads and writes of the text variable itself are not threadsafe. This argument is false.
 */

public class MyStringBuffer {
  // it is not a final datatype, what makes not threadsafe
  private String text;
  // Rep invariant:
  //    none
  // Abstraction Function:
  //    represents the sequence text[0],...,text[text.length()-1]
  // Thread safety argument:
  //    text is an immutable (and hence threadsafe) String,
  //    so this object is also threadsafe

// Another broken argument:

/**
 * This code has a race condition in it. There is a crucial moment when the rep invariant is violated, right after the edges map is mutated, but just before the nodes set is mutated. Another operation on the graph might interleave at that moment, discover the rep invariant broken, and return wrong results. Even though the threadsafe set and map data types guarantee that their own add() and put() methods are atomic and noninterfering, they can't extend that guarantee to interactions between the two data structures. So the rep invariant of Graph is not safe from race conditions. Just using immutable and threadsafe-mutable data types is not sufficient when the rep invariant depends on relationships between objects in the rep.

We'll have to fix this with synchronization, and we'll see how in a future reading.
*/

public class Graph {
  private final Set<Node> nodes = Collections.synchronizedSet(new HashSet<>());
  private final Map<Node, Set<Node>> edges = Collections.synchronizedMap(new HashMap<>());

  // Rep Invariant:
  //    for all x, y such that y is a member of edges.get(x)
  //    x, y are both members of nodes
  // Abstraction function:
  //    represents a directed graph whose nodes are the set of nodes
  //        and whose edges are the set (x,y) such that y is a member of edges.get(x)
  //
  // Thread safety argument:
  //    - nodes and edges are final, so those variables are immutable and threadsafe
  //    - nodes and edges point to the threadsafe set and map data types
