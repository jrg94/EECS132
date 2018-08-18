/**
 * This class works with three-dimensional Lines
 * @author Jeremy Griffith
 */

public class Line extends Line2D {
  
  // a private field that stores the first point in a Line
  private Point p1;
  
  // a private field that stores the second point in a Line
  private Point p2;
  
  /** 
   * This constructor builds a three-dimensional line based on two three-dimensional points
   * @param p1 a Point used to define this Line
   * @param p2 the second Point used to define this Line
   */
  public Line(Point p1, Point p2) {
    super(p1, p2);
    this.p1 = p1;
    this.p2 = p2;
  }
  
  /**
   * Returns NaN for the slope of a three-dimensional line
   * @return NaN because a three-dimensional line uses a Vector as the slope
   */
  @Override
  public double getSlope() {
    return Double.NaN;
  }
  
  /** 
   * Returns NaN for the y-intercept of a three-dimensional line
   * @return NaN because this method is fairly useless in three dimensions
   */
  @Override
  public double getYIntercept() {
    return Double.NaN;
  }
  
  /**
   * Returns the String representation of the three parametric equations for this line
   * @return the parametric equations for this Line
   */
  public String toString() {
    return ("x = " + (p1.getX() - p2.getX()) + " + " + p1.getX() + 
            ", y = " + (p1.getY() - p2.getY()) + " + " + p1.getY() + 
            ", z = " + (p1.getZ() - p2.getZ()) + " + " + p1.getZ());
  }
}