/**
 * This class works with two-dimensional points
 * @author Jeremy Griffith
 */

public class Point2D { 
  
  // A private field that stores the x-coordinate for an instant of a point
  private double x;
  
  // A private field that stores the y-coordinate for an instant of a point
  private double y;
  
  // A private field that stores the z-coordinate for an instant of a point. z = 0 for a Point2D.
  private double z;
  
  /**
   * This is a constructor for Point2D that takes two double parameters as its coordinates
   * @param x a double that marks the x-coordinate for this point
   * @param y a double that marks the y-coordinate for this point
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
    this.z = 0;
  }   
  
   /** 
   * Returns the x value of a point
   * @return the x value of a point
   */
  public double getX() {
    return x;
  }
   
  /** 
   * Returns the y value of a point
   * @return the y value of a point
   */
  public double getY() {
    return y;
  }
  
  /**
   * Returns the z value of a point
   * @return the z value of a point
   */
  public double getZ() {
    return z;
  }
  
  /**
   * Overrides the toString() method and returns a String representation of this Point2D
   * @return the Point2D as a String
   */
  @Override
  public String toString() {
    return "(" + x + "," + y + ")";
  }
  
  /** 
   * Returns true if the two points are equal
   * @param o an object to be compared to this point
   * @return true if the two points are identical
   */
  @Override
  public boolean equals(Object o) {
    // Checks to see if Object o is an instance of Point2D
    if (o instanceof Point2D) {
      Point2D point2 = (Point2D)o;
      return (point2.getX() == this.getX() && point2.getY() == this.getY() && point2.getZ() == this.getZ());
    }
    else {
      return false;
    }
  }
  
  /** 
   * Returns the distance between two points
   * @param p1 the first point used to compute the distance
   * @param p2 the second point used to compute the distance
   * @return the distance between p1 and p2
   */
  public static double distance(Point2D p1, Point2D p2) {
    // Stores the squared x distance of the Point
    double xLength = Math.pow((p2.getX()-p1.getX()), 2);
    // Stores the squared y distance of the Point
    double yLength = Math.pow((p2.getY()-p1.getY()), 2);
    // Stores the squared z distance of the Point. Always equals zero when the point is two-dimensional.
    double zLength = Math.pow((p2.getZ()-p1.getZ()), 2);
    // Returns the distance between two points through the use of the pythagorean theorem
    return Math.sqrt(xLength + yLength + zLength);
  }
}