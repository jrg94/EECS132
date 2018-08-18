/**
 * This class works with three-dimensional planes
 * @author Jeremy Griffith
 */

public class Plane {
  
  // a private field that stores the reference point for the normal vector
  private Point p;
  
  // a private field that stores the normal vector for the plane
  private Vector v;
  
  /**
   * This constructor creates a plane from a point and a normal vector
   * @param p a point located in the plane
   * @param v a vector that is perpendicular to the plane
   */
  public Plane(Point p, Vector v) {
    this.p = p;
    this.v = v;
  }
  
  
  public Plane(Point p1, Point p2, Point p3) {
  }
  
  /**
   * Returns the normal vector for this plane
   * @return the private field Vector v
   */
  public Vector getNormal() {
    return v;
  }
  
  /**
   * Returns the Point that defines this plane
   * @return the private field p
   */
  public Point getPoint() {
    return p;
  }
  
  /**
   * Overrides the toString method to return this plane as a String equation
   * @return a String that represents this plane
   */
  @Override
  public String toString() {
    double a = v.getPointX();
    double b = v.getPointY();
    double c = v.getPointZ();
    double d = (p.getX() * v.getPointX()) + (p.getY() * v.getPointY()) + (p.getZ() * v.getPointZ());
    if (b < 0 || c < 0 || d < 0) {
      if (b < 0 && c >= 0 && d >= 0) {
        return a + "x - " + Math.abs(b) + "y + " + c + "z + " + d + " = 0";
      }
      else if (b < 0 && c < 0 && d >= 0) {
        return a + "x - " + Math.abs(b) + "y - " + Math.abs(c) + "z + " + d + " = 0";
      }
      else if (b < 0 && c >= 0 && d < 0) {
        return a + "x - " + Math.abs(b) + "y + " + c + "z - " + Math.abs(d) + " = 0";
      }
      else if (b >= 0 && c < 0 && d >= 0) {
        return a + "x + " + b + "y - " + Math.abs(c) + "z + " + d + " = 0";
      }
      else if (b >= 0 && c < 0 && d < 0) {
        return a + "x + " + b + "y - " + Math.abs(c) + "z - " + Math.abs(d) + " = 0";
      }
      else if (b >= 0 && c >= 0 && d < 0) {
        return a + "x + " + b + "y + " + c + "z - " + Math.abs(d) + " = 0";
      }
      else {
        return a + "x - " + Math.abs(b) + "y - " + Math.abs(c) + "z - " + Math.abs(d) + " = 0";
      }
    }
    else {
      return a + "x + " + b + "y + " + c + "z + " + d + " = 0"; 
    }
  }
  
  /**
   * Returns true if this plane contains a point
   * @param p a Point that has the potential to lie on a plane
   * @return true if this plane contains parameter p
   */
  public boolean contains(Point p) {
    double a = v.getPointX();
    double b = v.getPointY();
    double c = v.getPointZ();
    double d = (p.getX() * v.getPointX()) + (p.getY() * v.getPointY()) + (p.getZ() * v.getPointZ());
    if (((a * p.getX()) + (b * p.getY()) + (c * p.getZ()) + d) == 
        ((a * this.p.getX()) + (b * this.p.getY()) + (c * this.p.getZ()) + d)) {
      return true;
    }
    else {
      return false;
    }
  }
  
  /**
   * Returns true if a Plane is equal to this Plane
   * @param o a Plane that is to be compared with this Plane
   * @return true if the normal vectors are parallel and one plane contains the point of this Plane
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Plane) {
      Plane plane = (Plane)o;
      return (Vector.isParallel(plane.getNormal(), this.getNormal()) && plane.contains(this.getPoint()));
    }
    else {
      return false;
    }
  }
  
  /**
   * A static method that returns true if two planes are parallel
   * @param p1 a Plane to be checked against another Plane
   * @param p2 the second Plane to be compared to the first
   * @return true if the two planes have parallel normal vectors
   */
  public static boolean isParallel(Plane p1, Plane p2) {
    return Vector.isParallel(p1.getNormal(), p2.getNormal());
  }
  
  /**
   * A static method that returns true if two planes are perpendicular
   * @param p1 a Plane to be checked against another Plane
   * @param p2 the second Plane to be compared to the first
   * @return true if the two planes have perpendicular normal vectors
   */
  public static boolean isOrthogonal(Plane p1, Plane p2) {
    return Vector.isOrthogonal(p1.getNormal(), p2.getNormal());
  }
}