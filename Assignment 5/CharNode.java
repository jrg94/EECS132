/**
 * A node class that stores a character and its frequency
 * @author Jeremy Griffith
 */
public class CharNode implements Comparable {

  private char character;
  private int frequency;
  private CharNode next;
  private CharNode left;
  private CharNode right;
  private String huffmanCode;
  
  /**
   * The basic node constructor
   */
  public CharNode() {
  }
  
  /**
   * The node constructor
   * @param next takes a node and sets the next pointer
   */
  public CharNode(CharNode next) {
    this();
    this.next = next;
  }
  
  /**
   * The node constructor
   * @param character  the element to store in the node
   * @param next  a reference to the next node of the list 
   */
  public CharNode(CharNode next, char character) {
    this(next);
    this.character = character;
    this.next = next;
    frequency = 1;
  }
  
  /**
   * Sets the character in this nodes
   * @param character the character to be added to this node
   */
  public void setChar(char character) {
    this.character = character;
  }
  
  /**
   * Returns the character in this node
   * @return the character field
   */
  public char getChar() {
    return character;
  }
  
  /**
   * Sets the next node in the list
   * @param next the next node in the list
   */
  public void setNext(CharNode next) {
    this.next = next;
  }
  
  /**
   * Returns the next node in the list
   * @return the next node in the list
   */
  public CharNode getNext() {
    return next;
  }
  
  /**
   * Sets the frequency of the node
   * @param num takes an int and sets the frequency of that node
   */
  public void setFrequency(int num) {
    frequency = num;
  }
  
  /**
   * Returns the frequency of the character
   * @return frequency of this character
   */
  public int getFrequency() {
    return frequency;
  }
  
  /**
   * Sets the left node
   * @param left the left node
   */
  public void setLeft(CharNode left) {
    this.left = left;
  }
  
  /**
   * Returns the left node
   * @return the left node
   */
  public CharNode getLeft() {
    return left;
  }
  
  /**
   * Sets the right node
   * @param right the right node
   */
  public void setRight(CharNode right) {
    this.right = right;
  }
  
  /**
   * Returns the right node
   * @return the right node
   */
  public CharNode getRight() {
    return right;
  }
  
  /**
   * Sets the Huffman Code for the node
   * @param the code
   */
  public void setCode(String code) {
    huffmanCode = code;
  }
  
  /**
   * Returns the Huffman Code for the node
   * @return the binary code for the node
   */
  public String getCode() {
    return huffmanCode;
  }
  
  /**
   * Increments the frequency by 1
   */
  public void incrementCount() {
    frequency = frequency + 1;
  }
  
  /**
   * Compares two elements
   * @param o an object
   * @return an int that resembles a comparison
   */
  public int compareTo(Object o) {
    CharNode node = (CharNode)o;
    if (this.getFrequency() > node.getFrequency()) {
      return 1;
    }
    else if (this.getFrequency() < node.getFrequency()) {
      return -1;
    }
    else {
      return 0;
    }
  }
  
}