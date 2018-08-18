import java.util.*;

/**
 * A class to represent a linked list of nodes.
 */
public class LinkedList<T> implements Iterable<T> {
  /** the first node of the list, or null if the list is empty */
  private LLNode<T> head;
  
  /**
   * Creates an initially empty linked list
   */
  public LinkedList() {
    head = null;
  }
  
  /**
   * Returns the head node.
   */
  protected LLNode<T> getHead() {
    return head;
  }

  /**
   * Changes the head node.
   * @param head  the first node of the new linked list
   */
  protected void setHead(LLNode<T> head) {
    this.head = head;
  }

  /**
   * Add an element to the front of the linked list
   */
  public void addToFront(T element) {
    setHead(new LLNode<T>(element, getHead()));
  }
  
  /**
   * Return whether the list is empty
   * @return true if the list is empty
   */
  public boolean isEmpty() {
    return (getHead() == null);
  }
  
  /**
   * Returns the length of the linked list
   * @return the number of nodes in the list
   */
  public int length() {
    int lengthSoFar = 0;
    LLNode<T> nodeptr = getHead();
    while (nodeptr != null) {
      lengthSoFar++;
      nodeptr = nodeptr.getNext();
    }
    return lengthSoFar;
  }
  
  /**
   * Overrides the method stub in the Iterable interface.  Returns an Iterator that will return
   * all the elements of this linked list.
   * @return  an iterator that iterates over the elements of this linked list
   */
  public Iterator<T> iterator() {
    return new ListIterator();
  }
  
  /** A non-static inner class that we need to make an iterator for the list */
  public class ListIterator implements Iterator<T> {
    /** Points to the node containing the next element we need this iterator to return */
    private LLNode<T> nodeptr;
    
    /**
     * Creates an iterator for the linked list.  In order to return all the elements of the
     * list, we have to start at the front of the linked list.
     */
    public ListIterator() {
      nodeptr = getHead();
    }
    
    /**
     * This method returns true if there are still more elements in the linked list that
     * will be returned by a call to next()
     * @return true   if the linked list has more elements to return
     */
    public boolean hasNext() {
      return (nodeptr != null);
    }
    
    /**
     * Each time the next method is called, the next element stored in the linked list is
     * returned.  If we have returned all the elements of the list, the method will throw
     * a NoSuchElementException, as indicated by the Iterator API.
     * @return  the next element stored in the linked list
     */
    public T next() {
      try {
        T element = nodeptr.getElement();
        nodeptr = nodeptr.getNext();
        return element;
      } 
      catch (NullPointerException e) {
        throw new NoSuchElementException();
      }
    }
  
    /**
     * This method from the Iterator interface is not supported
     */
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
  
  
  /**
   * Returns a String representation of the list
   * This method demonstrates the use of the "foreach" loop.  We can use this loop
   * because LinkedList implements Iterable.
   * 
   * @return a String representing the list
   */
  public String toString() {
    StringBuilder b = new StringBuilder();
    for (T element : this) {
      b.append(element);
      b.append(' ');
    }
    return b.toString();
  }
  
  /**
   * Checks to see if this LinkedList contains this Object
   * @param o takes an Object to be searched for in the list
   * @return true if the LinkedList contains this object
   */
  public boolean contains(Object o) {
    LLNode<T> nodeptr = this.getHead();
    while(nodeptr != null && !nodeptr.getElement().equals(o)) {
      nodeptr = nodeptr.getNext();
    }
    if (nodeptr == null) {
      return false;
    }
    else if (nodeptr.getElement().equals(o)) {
      return true;
    }
    else {
      return false;
    }
  }
  
  /**
   * Creates an ArrayList of the elements in this list and in the same order
   * @return an ArrayList from this list
   */
  public ArrayList<T> toArrayList() {
    LLNode<T> nodeptr = this.getHead();
    ArrayList<T> a = new ArrayList<T>();
    while(nodeptr != null) {
      a.add(nodeptr.getElement());
      nodeptr = nodeptr.getNext();
    }
    return a;
  }
  
  /** 
   * Print the contents of a linked list.  The method demonstrates the use of the wildcard ?
   * which is used when we do not care, and are not going to use, the type stored in the list
   * @param list   the list to print the contents of
   */
  public static void print(LinkedList<?> list) {
    LLNode<?> nodeptr = list.getHead();
    while (nodeptr != null) {
      System.out.print(nodeptr.getElement());
      System.out.print(" ");
      nodeptr = nodeptr.getNext();
    }
    System.out.println();
  }
  
  /** 
    * Inserts an element into the correct location, in non-decreasing order.
    * This method demonstrates defining a generic type for a method, and it demonstrates
    * how to use types that implement the Comparable interface.
    *
    * @param list  the linked list, all elements in the list are in non-decreasing order
    * @param element the element to add to the list
    */
  public static <S extends Comparable<S>> void insertInOrder(LinkedList<S> list, S element) {
    /* first case: the new element goes in the front of the list */
    if (list.isEmpty() || element.compareTo(list.getHead().getElement()) < 0) {
      list.setHead(new LLNode<S>(element, list.getHead()));
    }
    /* second case, the new element does not go in the front of the list */
    else {
      LLNode<S> nodeptr = list.getHead();
      
      /* stop when the nodeptr points to the node that the new element goes after */
      while (nodeptr.getNext() != null && 
             nodeptr.getNext().getElement().compareTo(element) < 0)
        nodeptr = nodeptr.getNext();
      
      nodeptr.setNext(new LLNode<S>(element, nodeptr.getNext()));
    }
  }
  
}