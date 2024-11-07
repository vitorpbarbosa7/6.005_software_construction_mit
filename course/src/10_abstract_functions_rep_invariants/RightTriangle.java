      /** Represents an immutable right triangle. */
      class RightTriangle {
        /* mutable array type*/
        // but this a private field, so no external will use this
        // the thing to learn from here is to know when to create new object or keep the reference
/*A*/     private double[] sides;
      
          // sides[0] and sides[1] are the two legs,
          // and sides[2] is the hypotenuse, so declare it to avoid having a
          // magic number in the code:
          // fixed reference to HYPOTENUSE, without magic number
/*B*/     public static final int HYPOTENUSE = 2;
      
          /** Make a right triangle.
           * @param legA, legB  the two legs of the triangle
           * @param hypotenuse    the hypotenuse of the triangle.
 *C*       *        Requires hypotenuse^2 = legA^2 + legB^2 
           *           (within the error tolerance of double arithmetic) ---- floats are complicated IEEE 754
           */
          public RightTriangle(double legA, double legB, double hypotenuse) {
            /* creating new object */
/*D*/         this.sides = new double[] { legA, legB, hypotenuse };
          }
      
          /** Get all the sides of the triangle.
           *  @return three-element array with the triangle's side lengths
           */
          public double[] getAllSides() {
            /* arrays are mutable, so returning reference to a mutable object can be dangerous */ 
/*E*/         return sides;
          }
      
          /** @return length of the triangle's hypotenuse */ 
          public double getHypotenuse() {
            /* arrays are mutable, so returning reference to a mutable object can be dangerous */ 
              return sides[HYPOTENUSE];
          }
      
          /** @param factor to multiply the sides by
           *  @return a triangle made from this triangle by 
           *  multiplies all side lengths by factor.
           */
          public RightTriangle scale(double factor) {
              return new RightTriangle (sides[0]*factor, sides[1]*factor, sides[2]*factor);
          }
      
          /** @return a regular triangle made from this triangle.
           *  A regular right triangle is one in which
           *  both legs have the same length.
           */
          public RightTriangle regularize() {
              double bigLeg = Math.max(side[0], side[1]);
              return new RightTriangle (bigLeg, bigLeg, side[2]);
          }
      
      }
