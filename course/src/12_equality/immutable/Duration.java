public class Duration {
    private final int mins;
    private final int secs;
    // rep invariant:
    //    mins >= 0, secs >= 0
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
    // Problematic definition of equals()
    public boolean equals(Duration that) {
        return this.getLength() == that.getLength();        
    }
    public static void main(String[] args) {
      Duration d1 = new Duration (1, 2);
      Duration d2 = new Duration (1, 2);
      Object o2 = d2;
      System.out.println("d1.equals(d2)=" + d1.equals(d2));
      System.out.println("d1.equals(o2)=" + d1.equals(o2));
    }
}
