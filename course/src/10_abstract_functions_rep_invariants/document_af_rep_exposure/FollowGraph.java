// Mutable type representing Twitter users' followers.
public class FollowGraph {
  private final Map<String,Set<String>> followersOf;

  // Rep Invariant:
  //    all Strings in followersOf are Twitter usernames
  //        (i.e., nonempty strings of letters, digits, underscores)
  //    no user follows themselfs, i.e. x is not in followersOf.get(x)
  // Abstraction Function:
  //    represents the follower graph where Twitter user x is followed by user y
  //        if and only if followersOf.get(x).contains(y)
  // Safety from rep exposure:
  //    All fields are private, and 
  //
  //    Strings are immutable, the

  // Operations (specs and method bodies ommited to save space)
  public FollowGraph() { ... }
  public void addFollower(String user, String follower) { ... }
  public void removeFollower(String user, String follower) { ... }
  public Set<String> getFollowers(String user) { ... }
}
