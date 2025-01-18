/** Represents an immmutable right triangle. */
class RightTriangle {
  private double[] sides;

  // sides[0] and sides[1] are the two legs,
  // and sides[2] is the hypotenuse, so declare it to avoid having a 
  // magic number in the code;
  public static final int HYPOTENUSE = 2;
  
  // creator (constructor)
  /** Make a right triangle.
   * @param legA, legB the two legs of the triangle
   * @param hypotenuse : the hypotenuse of the triangle
   *    Requires hypotenuse^2 = legA^2 + legB^2
   *     (within the error tolerance of double arithmetic)
   */
   public RightTriangle(double legA, double legB, double hypotenuse) {
     this.sides = new double[] { legA, legB, hypotenuse };
   }
    
   // observer (getter)
   /** Get all the sides of the triangle.
    * @return three-element array with triangle's side lengths
    */
   public double[] getAllSides() {
     return sides;
   }

   // observer (getter)
   public double getHypotenuse() {
     return sides[HYPOTENUSE];
   }

   /** @param factor to multiply the sides by
    * @return a triangle made from this triangle by 
    * multiplies all side lenghts by factor.
    */
   public RightTriangle scale(double factor) {
     // return new fresh object, not a reference to current one
     return new RightTriangle (sides[0]*factor, sides[1]*factor, sides[2]*factor);
   }

   /** @return a regular triangle made from this triangle
    * A regular triangle is one in which 
    * both legs have the same lengths
    */
   public RightTriangle regularize() {
     double bigLeg = Math.max(sides[0], sides[1]);
     return new RightTriangle (bigLeg, bigLeg, sides[HYPOTENUSE]);
   }
}
