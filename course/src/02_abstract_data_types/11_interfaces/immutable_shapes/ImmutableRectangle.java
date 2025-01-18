/** An immutable rectangle */
public interface ImmutableRectangle {
  /** @return the width of this rectangle */
  public int getWidth();
  /** @return the height of this rectangle */
  public int getHeight();
}

/** An immutable square */
public class ImmutableSquare implements ImmutableRectangle {
  private final int side;
  /** Make a new side x side square. */
  // creator (constructor) 
  public ImmutableSquare(int side) { this.side = side; }
  /** @return the width of this square */
  public int getWidth() { return side; }
  /** @return the height of this square */
  public int getHeight() { return side; }
}
