/**
 * This class works with three-dimensional points and extends the Point2D class
 * @author Jeremy Griffith
 */

public class Point extends Point2D {
  
  // A private field that stores the x-coordinate for an instant of a point
  private double x;
  
  // A private field that stores the y-coordinate for an instant of a point
  private double y;
  
  // A private field that stores the z-coordinate for an instant of a point
  private double z;
  
  /** 
   * This is a constructor for Point that takes three double parameters as its coordinates
   * @param x a double that marks the x-coordinate for this point
   * @param y a double that marks the y-coordinate for this point
   * @param z a double that marks the z-coordinate for this point
   */
  public Point (double x, double y, double z) {
    super(x, y);
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  /**
   * Returns the z value of a point
   * @return the z value of a point
   */
  public double getZ() {
    return z;
  }
  
  /**
   * Overrides the toString method and returns a String version of the point
   * @return the given point as a String
   */
  @Override
  public String toString() {
    return "(" + x + "," + y + "," + z + ")";
  }
}