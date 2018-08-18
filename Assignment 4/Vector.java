/**
 * This class works with three-dimensional vectors
 * @author Jeremy Griffith
 */

public class Vector {
  
  // Stores the x-coordinate of the point that defines this vector
  private double x;
  
  // Stores the y-coordinate of the point that defines this vector
  private double y;
  
  // Stores the z-coordinate of the point that defines this vector
  private double z;
  
  // Stores the point that defines this vector
  private Point p;
  
  // Stores the length of this vector
  private double length;
  
  // Stores this Vector
  private Vector vector;
  
  /**
   * This constructor creates a Vector from three coordinates that make a point
   * @param x the x-coordinate for the point
   * @param y the y-coordinate for the point
   * @param z the z-coordinate for the point
   */
  public Vector (double x, double y, double z) {
    this.p = new Point(x,y,z);
    this.x = x;
    this.y = y;
    this.z = z; 
    this.length = Point2D.distance(this.getOrigin(), p);
  }
  
  /**
   * This constructor creates a Vector from a Point
   * @param p the Point from which to specify the direction from the origin
   */
  public Vector(Point p) {
    this.x = p.getX();
    this.y = p.getY();
    this.z = p.getZ();
    this.p = p;
    this.length = Point2D.distance(this.getOrigin(), p);
  }
  
  /**
   * This constructor creates a Vector from a Vector and a new length
   * @param vector the vector in which to specify the direction of the new vector
   * @param length the length in which the new vector will have from the origin to the new point
   */
  public Vector(Vector vector, double length) {
    Vector vScaled = scale(vector, (length / vector.magnitude()));
    this.x = vScaled.getPointX();
    this.y = vScaled.getPointY();
    this.z = vScaled.getPointZ();
    this.p = new Point(x, y, z);
    this.length = Point2D.distance(this.getOrigin(), this.p);
  }
  
  /**
   * Returns the origin of the coordinate system
   * @return the Point (0,0,0) as the origin or reference point for this Vector
   */
  public Point getOrigin() {
    Point origin = new Point(0,0,0);
    return origin;
  }
  
  /**
   * Returns the private field p
   * @return the Point p that defines this Vector
   */
  public Point getPoint() {
    return p;
  }
  
  /**
   * Returns the X-coordinate for the point that defines the direction of the vector
   * @return the field x for Point p
   */
  public double getPointX() {
    return p.getX();
  }
  
  /**
   * Returns the y-coordinate for the point that defines the direction of the vector
   * @return the field y for Point p
   */
  public double getPointY() {
    return p.getY();
  }
  
  /**
   * Returns the z-coordinate for the point that defines the direction of the vector
   * @return the field z for the Point p
   */
  public double getPointZ() {
    return p.getZ();
  }
  
  /**
   * Returns the length of the vector
   * @return gets the length of this Vector
   */
  public double magnitude() {
    return length;
  }
  
  /** 
   * Overrides the toString method to return a String representation of the vector
   * @return A string to represent the vector at Point p 
   */
  @Override
  public String toString() {
    return "<" + p.getX() + "," + p.getY() + "," + p.getZ() + ">";
  }
  
  /** 
   * Returns true if the two points are equal
   * @param o an object to be compared to this point
   * @return true if the two points are identical
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Vector) {
      Vector vector = (Vector)o;
      return (this.getPoint().equals(vector.getPoint()));
    }
    else {
      return false;
    }
  }
   
  /**
   * Returns a Vector of the same direction as this Vector but with a length of one
   * @return the unit Vector for this vector by dividing each point by the length
   */
  public Vector unitVector() {
    double unitX = this.getPointX() / this.magnitude();
    double unitY = this.getPointY() / this.magnitude();
    double unitZ = this.getPointZ() / this.magnitude();
    Vector vUnit = new Vector(unitX, unitY, unitZ);
    return vUnit;
  }
  
  /**
   * Returns the Vector sum of two vectors
   * @param v1 the first vector to be added to the second
   * @param v2 the second vector to be added to the first
   * @return the term by term summation of the two Vectors: v1 and v2
   */
  public static Vector sum(Vector v1, Vector v2) {
    double sumX = (v1.getPointX() + v2.getPointX());
    double sumY = (v1.getPointY() + v2.getPointY());
    double sumZ = (v1.getPointZ() + v2.getPointZ());
    Vector vSum = new Vector(sumX, sumY, sumZ);
    return vSum;
  }
  
  /**
   * Returns a Vector with a new length
   * @param v1 a Vector that is to be multiplied by scale to obtain a new vector
   * @param scale a double that is multiplied by each coordinate to obtain a new point to define a new vector
   * @return a new vector with the coordinates of this vector multiplied by the scale
   */
  public static Vector scale(Vector v1, double scale) {
    double scaleX = (v1.getPointX() * scale);
    double scaleY = (v1.getPointY() * scale);
    double scaleZ = (v1.getPointZ() * scale);
    Vector vScale = new Vector(scaleX, scaleY, scaleZ);
    return vScale;
  }
  
  /**
   * Returns the dot product of two vectors
   * @param v1 a Vector that is to form the dot product with another Vector
   * @param v2 the second Vector to form the dot product with the first
   * @return the dot product as a double
   */
  public static double dotProduct(Vector v1, Vector v2) {
    double dotX = (v1.getPointX() * v2.getPointX());
    double dotY = (v1.getPointY() * v2.getPointY());
    double dotZ = (v1.getPointZ() * v2.getPointZ());
    return (dotX + dotY + dotZ);
  }
  
  /**
   * Returns the cross product of two vectors
   * @param v1 a Vector that is to form a cross product vector with another Vector
   * @param v2 the second Vector to form a cross product with the first Vector
   * @return the cross product of v1 and v2 as a Vector
   */
  public static Vector crossProduct(Vector v1, Vector v2) {
    double crossX = (v1.getPointY() * v2.getPointZ()) - (v1.getPointZ() * v2.getPointY()); 
    double crossY = -((v1.getPointX() * v2.getPointZ()) - (v1.getPointZ() * v2.getPointX()));
    double crossZ = (v1.getPointX() * v2.getPointY()) - (v1.getPointY() * v2.getPointX());
    Vector vCross = new Vector(crossX, crossY, crossZ);
    return vCross;
  }
  
  /** 
   * Returns the angle between two vectors
   * @param v1 the Vector that sets the base for the angle to be determined
   * @param v2 the Vector that forms the angle with v1
   * @return the radian value of the angle between v1 and v2
   */
  public static double angle(Vector v1, Vector v2) {
    return Math.acos((Vector.dotProduct(v1, v2)) / Math.abs(v1.magnitude() * v2.magnitude()));
  } 
  
  /**
   * Returns true if two Vectors are orthogonal
   * @param v1 the Vector that forms the base to determine if the intersection is orthogonal
   * @param v2 the Vector that forms with v1 to determine if the intersection is orthogonal
   * @return true if the two vectors intersect perpendicularly
   */
  public static boolean isOrthogonal(Vector v1, Vector v2) {
    if (Vector.dotProduct(v1, v2) == 0) {
      return true;
    }
    else {
      return false; 
    } 
  }
  
  /**
   * Returns true if two Vectors are parallel
   * @param v1 a Vector to be compared to v2
   * @param v2 a Vector to be compared to v1
   * @return true if the two Vectors are parallel
   */
  public static boolean isParallel(Vector v1, Vector v2) {
    Vector originV = new Vector(0,0,0);
    if (Vector.crossProduct(v1, v2).equals(originV)) {
      return true;
    }
    else {
      return false;
    }
  }
}