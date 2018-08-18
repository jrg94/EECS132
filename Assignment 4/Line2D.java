/**
 * This class works with two-dimesnional lines.
 * @author Jeremy Griffith
 */

public class Line2D {
  
  // Stores the first point that is used to define the line
  private Point2D p1;
  
  // Stores the second point that is used to define the line
  private Point2D p2;
   
  /**
   * This constructor takes two points and creates a line from them
   * @param p1 the first of two points used to define a line
   * @param p2 the second of two points used to define a line
   */
  public Line2D (Point2D p1, Point2D p2) {
    this.p1 = p1;
    this.p2 = p2;
  }
  
  /**
   * Returns the slope of this line
   * @return the slope between the two defining points
   */
  public double getSlope() {
    // Returns the slope by calculating the difference of the y coordinates over the difference of the x coordinates
    return (p1.getY()-p2.getY())/(p1.getX()-p2.getX());
  }
  
  /**
   * Returns the x-coordinate for point one
   * @return the x value of p1 for an instance of this line
   */
  public double getXPoint1() {
    return p1.getX();
  }
  
  /**
   * Returns the x-coordinate for point two
   * @return the x value of p2 for an instance of this line
   */
  public double getXPoint2() {
    return p2.getX();
  }
  
  /**
   * Returns the y-intercept of the line
   * @return the intersection point when this line hits the y-axis
   */
  public double getYIntercept() {
    // y = mx + b OR y - mx = b
    return p1.getY() - (this.getSlope() * p1.getX());
  }
  
  /**
   * Overrides the toString() method to allow the method to return the String 
   * representation of a line.
   * @return a String that represents the Line2D
   */
  @Override
  public String toString() {
    // Determines if the slope is infinite and evaluates accordingly
    if (p1.getX()-p2.getX() == 0) {
      return "x = " + p1.getX();
    }
    else {
      if (this.getYIntercept() >= 0) {
        return "y = " + this.getSlope() + "x + " + this.getYIntercept();
      }
      else {
        return"y = " + this.getSlope() + "x - " + Math.abs(this.getYIntercept());
      }
    }
  }
  
  /**
   * Returns true if two lines are identical
   * @param o an Object that is potentially the same as this line
   * @return true if Object o and this line are identical
   */
  @Override
  public boolean equals (Object o) {
    if (o instanceof Line2D) {
      Line2D line = (Line2D)o;
      return (line.getYIntercept() == this.getYIntercept() && line.getSlope() == this.getSlope());
    }
    else {
      return false;
    }
  } 
  
  /**
   * Returns true if the two line parameters are parallel
   * @param l1 a Line2D to be compared to the other parameter
   * @param l2 the second Line2D to be compared to the first
   * @return true if the slopes of the two lines are equal
   */
  public static boolean isParallel(Line2D l1, Line2D l2) {
    if (l1.getXPoint1() - l1.getXPoint2() == 0 && l2.getXPoint1() - l2.getXPoint2() == 0) {
      return true;
    }
    else {
      return (l2.getSlope() == l1.getSlope());
    }
  }
  
  /**
   * Returns the point at which the two lines intersect
   * @param l1 the first line to intersect with the second
   * @param l2 the second line to intersect with the first
   * @return the point of intersection
   */
  public static Point2D intersection(Line2D l1, Line2D l2) {
    // Checks to see if the lines are parallel. If yes, the method returns null.
    if (isParallel(l1, l2)) {
      return null;
    }
    // Checks to see if the x-coordinates for the first line are the same. If yes, the line is vertical.
    else if (l1.getXPoint1() == l1.getXPoint2()) {
      double x = l1.getXPoint1();
      double y = (l2.getSlope() * x) + l2.getYIntercept();
      Point2D intersectionPoint = new Point2D(x,y);
      return intersectionPoint;     
    }
    // Checks to see if the x-coordinates for the second line are the same. If yes, the line is vertical.
    else if (l2.getXPoint1() == l2.getXPoint2()) {
      double x = l2.getXPoint1();
      double y = (l1.getSlope() * x) + l1.getYIntercept();
      Point2D intersectionPoint = new Point2D(x,y);
      return intersectionPoint;
    }
    // Otherwise, the calculations are straight forward for any two lines.
    else {
      double x = (l2.getYIntercept() - l1.getYIntercept()) / (l1.getSlope() - l2.getSlope());
      double y = (l1.getSlope() * x) + l1.getYIntercept();
      Point2D intersectionPoint = new Point2D(x,y);
      return intersectionPoint;
    }
  }
}