
// Immutable type representing a tweet.
public class Tweet {

  private final String author;
  private final String text;
  private final Date timestamp;

  // Rep Invariant:
  //    author is a Twitter username (a nonempty string of letters, digits, underscores)
  //    text.length <= 140
  // Abstraction Function:
  //    represents a tweet posted by author, with context text, at time timestamp
  // Safety from rep exposure:
  //    All fields are private:
  //    author and text are Strings, so we garantee immutability;
  //    timestamp is a mutable Date, so Tweet() constructor and getTimestamp()
  //        make defensive copies to avoid sharing the rep's Date object with clients
  
  // Operations (specs and method bodies omitted to save space)
  public Tweet(String author, String text, Date timestamp);
  public String getAuthor() { ... }
  public String getText() { ... }
  public Date getTimestamp() { ... }
}
