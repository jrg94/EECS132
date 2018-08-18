public class Compare {
  public String compareValues(int x, int y) {
    if (x > y) {
      return ( x + " larger than " + y ); 
    }
    if (x < y) {
      return (" + y + " is larger than " + x + ");
    }
    else {
      return ("The two values are equal");
    }
  }
}