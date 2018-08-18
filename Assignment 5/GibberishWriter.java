import java.util.*;
import java.io.*;

/**
 * A class that analyzes text an creates a new gibberish text
 * @author Jeremy Griffith
 */
public class GibberishWriter implements Iterable<String> {
  
  // Stores a LinkedList of ContextData
  private LinkedList<ContextData> dataLL;
  
  // Stores an ArrayList of ContextData
  private ArrayList cData;
  
  // Stores the context size
  private int contextSize;
  
  // Stores the current ContextData
  private ContextData c;
  
  // Stores the current Context
  private Context words;
  
  /**
   * A constructor for GibberishWriter that takes an int and a Scanner
   * @param contextSize an int that specifies the size of the desired Context
   * @param scanner a Scanner based on a file in this class's main method
   */
  public GibberishWriter(int contextSize, Scanner scanner) {
    // Stores the contextSize
    this.contextSize = contextSize;
    // Initializes the LinkedList of ContextData
    dataLL = new LinkedList<ContextData>();
    // Stores a String array based on the contextSize parameter
    String[] phrase = new String[contextSize];
    // Stores Strings in the phrase array
    for(int i = 0; i < contextSize; i++) {
      phrase[i] = scanner.next();
    }
    // Creates a Context from the initial contextSize of words
    Context c = new Context(phrase);
    // Creates the initial Context from the first phrase
    ContextData initial = new ContextData(c);
    // Stores the initial ContextData in the LinkedList
    dataLL.addToFront(initial);
    // Stores a nodepointer for GibberishWriter's ContextData LinkedList
    LLNode<ContextData> nodeptr = dataLL.getHead();
    // Runs a loop until Scanner has no more words to return
    while(scanner.hasNext()) {
      // Stores the word the results from calling the next word from scanner
      String nextWord = scanner.next();
      // Loop stops if the context is found in this LinkedList or the loop reaches the end of this LinkedList
      while(nodeptr != null && !nodeptr.getElement().getContext().equals(new Context(phrase))) {
        nodeptr = nodeptr.getNext();
      }
      // Checks to see if the nodepointer has reached the end of this LinkedList
      if (nodeptr == null) {
        ContextData data = new ContextData(new Context(phrase));
        LinkedList.insertInOrder(dataLL, data);
        data.addFollowingWord(nextWord);
        phrase = GibberishWriter.shiftWords(phrase, nextWord);
      }
      // Otherwise, the nodepointer is at the matching Context   
      else {
        nodeptr.getElement().addFollowingWord(nextWord);
        phrase = GibberishWriter.shiftWords(c.getWords(), nextWord);
      }
      nodeptr = dataLL.getHead();
    }
    // Transfers all the newly added data from this LinkedList into an ArrayList
    cData = dataLL.toArrayList();
  }
  
  /**
   * Returns a the parameter array with the words shifted to the front and a new word added on the end
   * @param phrase a String array to be reorganized
   * @param word a word to be tagged onto the end of the array
   * @return the String away with the first word deleted and a new word added
   */
  public static String[] shiftWords(String[] phrase, String word) {
    int length = phrase.length;
    if (length == 0) {
      phrase[length] = word;
    }
    else {
      for(int i = 0; i < length - 1; i++) {
        phrase[i] = phrase[i + 1];
      }
      phrase[length - 1] = word;
    }
    return phrase;
  }
  
  /**
   * Returns the arrayList
   * @return the field cData
   */
  public ArrayList getCData() {
    return cData;
  }
  
  /**
   * Overrides the method stub in the Iterable interface.  Returns an Iterator that will return
   * all the elements of this linked list.
   * @return  an iterator that iterates over the elements of this linked list
   */
  public Iterator<String> iterator() {
    return new ListIterator();
  }
  
  /** A non-static inner class that we need to make an iterator for the list */
  public class ListIterator implements Iterator<String> {
    /** Points to the node containing the next element we need this iterator to return */
    private LLNode<GibberishWriter.ContextData> nodeptr;
    
    /**
     * Creates an iterator for the linked list
     */
    public ListIterator() {
      nodeptr = dataLL.getHead();
    }
    
    /**
     * This method always returns true
     * @return Always returns true
     */
    public boolean hasNext() {
      return true;
    }
    
    /**
     * Each time the next method is called, the next word is returned
     * @return the next word 
     */
    public String next() {
      int i = 0;
      // Picks a random location in the list of contextData and stores it as an int
      int context = ((int)Math.random() * (cData.size()) - 1);
      String[] startingContext = new String[contextSize];
      // Returns the first context size of words
      if (i < contextSize) {
        c = (ContextData)(cData.get(context));
        String w = c.getContext().getWord(i);
        i++;
        return w;
      }
      // Returns the next word chosen at random
      else {
        // Stores the index of my Context
        int a = Collections.binarySearch(cData, c);
        // Stores a random number from the occurences to be used to get the next word
        int b = ((int)Math.random() * ((ContextData)(cData.get(a))).getOccurrences());
        String d = ((ContextData)(cData.get(a))).getFollowingWord(b);
        words = new Context(GibberishWriter.shiftWords(((ContextData)(cData.get(a))).getContext().getWords(), d));
        return d;
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
   * An inner class that stores how many times a word occurs after a given context
   */
  public static class WordData {
    
    // Stores a particular word
    private String word;
    
    // Stores how many times a particular word appears in a context
    private int count;
    
    /**
     * A constructor that initializes the fields word and count
     * @param word a word to be searched for a counted within a context
     */
    public WordData(String word) {
      this.word = word;
      this.count = 1;
    }
    
    /**
     * Increments the field count
     */
    public void incrementCount() {
      count = count + 1;
    }
    
    /**
     * A getter method that gets the current word
     * @return the field word
     */
    public String getWord() {
      return word;
    }
    
    /**
     * A getter method that gets the total count of a word in a context
     * @return the field count
     */
    public int getCount() {
      return count;
    }
    
  }
  
  /**
   * An inner class that works with a a group of words
   */
  public static class Context implements Comparable<Context> {
    
    // Stores an array of words, a.k.a. the context
    private String[] words;
    
    /**
     * A constructor that takes an array of String to create the context
     * @param words an array of Strings
     */
    public Context(String... words) {
      this.words = words;
    }
    
    /**
     * Gets this String[]
     * @return the field words
     */
    public String[] getWords() {
      return words;
    }
    
    /**
     * Determines the length of the array that defines the context
     * @return the length of the field words
     */
    public int length() {
      return this.getWords().length;
    }
    
    /**
     * Gets the word located at a specific index in the field words
     * @param index the location in the field array words
     * @return the word at a specific index in the field array words
     */
    public String getWord(int index) {
      String[] s = this.getWords();
      return s[index];
    }
    
    /**
     * Returns a String representation of a Context
     * @return this Context as a String
     */
    @Override
    public String toString() {
      return Arrays.toString(this.getWords());
    }
    
    /**
     * Returns true if the two Contexts are equal
     * @param o an Object to be compared to this context
     * @return true if both contexts have the same length and the same words in the same order
     */
    public boolean equals(Object o) {
      // Checks to see if the input is an instance of Context
      if (o instanceof Context) {
        return (((Context)o).length() == this.length() && ((Context)o).toString().equals(this.toString()));
      }
      // Otherwise, the two Objects are not equivalent
      else {
        return false;
      }
    }
    
    /** 
     * Returns a value that indicates whether this Context is >, <, or = to the parameter Context
     * @param c a Context to be compared to this Context
     * @return a value depending on the relationship of this Context and the parameter Context
     */
    public int compareTo(Context c) {
      Arrays.sort(c.getWords());
      Arrays.sort(this.getWords());
      int i = 0;
      // Runs through two contexts until the two words are different and returns the difference
      for(; c.getWord(i).equals(this.getWord(i)); i++) {
      }
      return this.getWord(i).compareTo(c.getWord(i));
    }     
  }
  
  /**
   * An inner class that works with data pertaining to a particular Context
   */
  public static class ContextData implements Comparable<ContextData> {
    
    // Stores a Context
    private Context context;
    
    // Stores the number of occurences of a Context
    private int occurrence;
    
    // Stores a LinkedList of WordData
    private LinkedList<WordData> data;
    
    /**
     * A constructor that assigns a context and initializes both the LinkedList<T> and occurrence field
     * @param context a context to be examined
     */
    public ContextData(Context context) {
      this.context = context;
      this.occurrence = 0;
      this.data = new LinkedList<WordData>();
    }
    
    /**
     * Gets the LinkedList that stores the WordData
     * @return this LinkedList of Worddata
     */
    public LinkedList<WordData> getData() {
      return data;
    }
    
    /**
     * A getter method the gets this context
     * @return the field context
     */
    public Context getContext() {
      return context;
    }
    
    /**
     * A getter method that gets the occurrences of this context
     * @return the field occurrence
     */
    public int getOccurrences() {
      return occurrence;
    }
    
    /**
     * Overrides the compareTo() method
     * @return an integer related to the comparing of this ContextData and c
     */
    public int compareTo(ContextData c) {
      return this.getContext().compareTo(c.getContext());
    }     
    
    /**
     * Adds new WordData to the LinkedList for a word
     * @param word takes a String and searches the LinkedList for it
     */
    public void addFollowingWord(String word) {
      LLNode<WordData> nodeptr = data.getHead();
      // Runs through a this LinkedList until it finds this word or it hits the end
      while(nodeptr != null && nodeptr.getNext() != null && !nodeptr.getElement().getWord().equals(word)) {
        nodeptr = nodeptr.getNext();
        }
      // Checks to see if the LinkedList is empty
      if (nodeptr == null) {
        data.setHead(new LLNode<WordData>(new GibberishWriter.WordData(word), null));
        occurrence = occurrence + 1;
      }
      // Checks to see if the loop hit the end of this LinkedList
      else if (nodeptr.getNext() == null) {
        nodeptr.insertAfter(new GibberishWriter.WordData(word));
        occurrence = occurrence + 1;
      }
      // Checks to see if this word matches the parameter
      else {
        nodeptr.getElement().incrementCount();
        occurrence = occurrence + 1;
      }
    }
    
    
    /**
     * Returns a word based on the value parameter
     * @param value the integer used to pick a word that comes next
     * @return a String from the LinkedList of WordData
     */
    public String getFollowingWord(int value) {
      LLNode<WordData> nodeptr = data.getHead();
      // Runs through this LinkedList until the total number of counts is greater than or equal to the value
      for(int x = 0; x < value;) {
        x = x + nodeptr.getElement().getCount();
        // Checks to see if the total counts are greater than or equal to the value
        if(x >= value) {
        }
        // Otherwise, move to the next word
        else {
          nodeptr = nodeptr.getNext();
        }
      }
      return nodeptr.getElement().getWord();
    }
  }
  
  /**
   * The main method for the GibberishWriter
   * @param args an array of Strings that act as inputs for the main method
   */
  public static void main(String[] args) {
    // Tries to find the elements at the index of the args as well as testing the input file
    try {
      String file = new String(args[0]);
      int contextSize = Integer.parseInt(args[1]);
      int numOfWords = Integer.parseInt(args[2]);
      Scanner scanner = new Scanner(new FileReader(file));
      GibberishWriter print = new GibberishWriter(contextSize, scanner);
      for(int count = 0; count < numOfWords; count++) {
        System.out.print(print.iterator().next());
      }
    }
    // Prints an error if the user does not enter an accessible or correct file
    catch (FileNotFoundException e) {
      System.out.print("You did not enter a usable file");
    }
    // Prints an error if the user enters a number outside the array
    catch (ArrayIndexOutOfBoundsException e) {
      System.out.print("You did not enter three arguments.");
    }
  }
}