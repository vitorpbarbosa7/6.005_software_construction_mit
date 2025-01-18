public class Duration {

  private final int mins;
  private final int secs;

  // rep invariant:
  //    mins >=0, secs >= 0
  // abstraction function:
  //    represents a span of time of mins minutes and secs seconds
  
  /** Make a duration lasting for m minutes and s seconds. */
  public Duration(int m, int s) {
    mins = m; secs = s;
  }

  /** @return length of this duration in seconds */
  public long getLength() {
    return mins*60 + secs;
  }
}

Duration d1 = new Duration(1,2);
Duration d2 = new Duration(1,3);
Duration d3 = new Duration(0,62);
Duration d4 = new Duration(1,2);
