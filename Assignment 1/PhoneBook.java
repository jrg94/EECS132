/**
 * A class that resembles a phone book
 * @author Jeremy Griffith
 */
public class PhoneBook<T> {
  
  // Stores a LinkedList of names or social security numbers
  public LinkedList<T> list;
  
  /** 
   * The constructor initializes the Phonebook(List)
   */
  public PhoneBook() {
    list = new LinkedList<T>();
  }
  
  /**
   * Takes an element and adds it to the front of the PhoneBook
   * @param element takes a generic Object and adds it to the Phonebook
   */
  public void addPerson(T element) {
    list.addToFront(element);
  }
  
  /**
   * Searches the list for a person
   * @param element takes a generic object and searches the list for it
   */
  public void findPerson(T element) {
    LLNode<T> nodeptr;
    // Searches through the list for the input element
    for(nodeptr = list.getHead(); nodeptr != null && !nodeptr.getElement().equals(element); nodeptr = nodeptr.getNext()) {
    }
    // Checks to see if the entire list has been searched
    if(nodeptr == null) {
      System.out.println(element + " was not found.");
    }
    // Otherwise, this node holds your element
    else {
      System.out.println(element + " was found"); 
    }
  }
  
  /**
   * Removes a person from the PhoneBook
   * @param element takes a generic Object and removes it from the list if it exists
   */
  public void deletePerson(T element) {
    try {
      LLNode<T> nodeptr;
      // Checks to see if the first element is the one you're looking for 
      if(list.getHead().getElement().equals(element)) {
        list.setHead(list.getHead().getNext());
        System.out.println(element + " was successfully deleted.");
      }
      // Otherwise, deletePerson searches the list for the input element
      else {
        for(nodeptr = list.getHead(); nodeptr.getNext() != null && 
            !nodeptr.getNext().getElement().equals(element); nodeptr = nodeptr.getNext()) {
        }
        // Checks to see if the search has reached the end of the list
        if(nodeptr.getNext() == null) {
          System.out.println(element + " does not exist");
        }
        // Otherwise, element is stored while nodeptr is directed beyond element and element is directed to null
        else {
          LLNode<T> nextNode = nodeptr.getNext();
          nodeptr.setNext(nodeptr.getNext().getNext());
          nextNode.setNext(null);
          System.out.println(element + " was successfully deleted.");
        }
      }
    }
    // Lets the user know that the list is empty
    catch(NullPointerException e) {
      System.out.println("This PhoneBook is empty");
    }
  }
  
  /**
   * Creates two PhoneBooks, adds another item into each PhoneBook, 
   * and tries to find a person in each Phonebook.
   * @param args takes an array of Strings
   */
  public static void main(String[] args) {
    PhoneBook<String> names = new PhoneBook<String>();
    PhoneBook<Integer> socials = new PhoneBook<Integer>();
    String name;
    Integer social;
    try {
      name = args[0];
      social = Integer.parseInt(args[1]);
      names.addPerson(name);
      socials.addPerson(social);
      names.findPerson(name);
      socials.findPerson(social);
    }
    catch(ArrayIndexOutOfBoundsException e) {
      System.out.println("Requires two inputs");
    } 
  }
}