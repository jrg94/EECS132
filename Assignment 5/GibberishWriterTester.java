import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;

/**
 * Test class GibberishWriter and associated LinkedList methods
 */
public class GibberishWriterTester {
  
  /**
   * Test the insertAfter method of LLNode
   */
  @Test
  public void testInsertAfter() {
    
    LinkedList<String> s = new LinkedList<String>();
    s.setHead(new LLNode<String>("Spider", null));
    s.getHead().insertAfter("Tiger");
    assertEquals("Tiger", s.getHead().getNext().getElement());
    assertEquals("Spider Tiger ", s.toString());
  }
  
  /**
   * Test the toArrayList method of LinkedList
   */
  @Test
  public void testToArrayList() {
    
    LinkedList<String> s = new LinkedList<String>();
    assertEquals("[]", s.toArrayList().toString());
    
    s.addToFront("Tiger");
    s.addToFront("Spider");
    assertEquals("[Spider, Tiger]", s.toArrayList().toString());
  }
  
  /**
   * Test the contains method of LinkedList
   */
  @Test
  public void testLinkedListContains() {
    
   LinkedList<String> s = new LinkedList<String>();
   
   assertFalse(s.contains(""));
   assertFalse(s.contains(null));
   
   s.addToFront("Hello");
   s.addToFront("Goodbye");
   assertTrue(s.contains("Hello"));
   assertTrue(s.contains("Goodbye"));
   assertFalse(s.contains(null));
   assertFalse(s.contains("Welcome"));
  }
  
  /**
   * Test the constructor for GibberishWriter
   */
  @Test
  public void testGibberishWriter() {
    String s = "Failure to follow these warnings could result in serious consequences";
    GibberishWriter writer = new GibberishWriter(2, new Scanner(new StringReader(s)));
    // The constructor doesn't seem to be storing data correctly into the LinkedList
    assertEquals("[could, result]", ((GibberishWriter.ContextData)(writer.getCData().get(2))).getContext());
    assertEquals("[Failure, to]", ((GibberishWriter.ContextData)(writer.getCData().get(1))).getContext());
  }
  
  /**
   * Test the shiftWords method of GibberishWriter
   */
  @Test
  public void testShiftWords() {
    String[] s = {"This", "Is", "Hard"};
    assertEquals("[Is, Hard, LastWord]", Arrays.toString(GibberishWriter.shiftWords(s, "LastWord")));
    
    String[] a = new String[1];
    assertEquals("[LastWord]", Arrays.toString(GibberishWriter.shiftWords(a, "LastWord")));
    
    String[] b = {"Hi"};
    assertEquals("[LastWord]", Arrays.toString(GibberishWriter.shiftWords(b, "LastWord")));
  }
  
  /**
   * Test the hasNext method of GibberishWriter
   */
  @Test
  public void testHasNext() {
    String s = "Jingle bells, jingle bells, jingle all the way";
    GibberishWriter writer = new GibberishWriter(2, new Scanner(new StringReader(s)));
    assertEquals(true, writer.iterator().hasNext());
  }
  
  /**
   * Test the Next method of GibberishWriter
   */
  @Test
  public void testNext() {
    String s = "Jingle bells";
    GibberishWriter writer = new GibberishWriter(2, new Scanner(new StringReader(s)));
    // Claims that there is no data in the array
    assertEquals("Jingle", writer.iterator().next());
    assertEquals("bells", writer.iterator().next());
  }
  
  /**
   * Test the constructor for WordData
   */
  @Test
  public void testWordData() {
    GibberishWriter.WordData w = new GibberishWriter.WordData("Hello");
    assertEquals("Hello", w.getWord());
    assertEquals(1, w.getCount());
  }
  
  /**
   * Test the IncrementCount method of WordData
   */
  @Test
  public void testIncrementCount() {
    GibberishWriter.WordData data = new GibberishWriter.WordData(null);
    assertEquals(1, data.getCount());
    
    data.incrementCount();
    assertEquals(2, data.getCount());
  }
  
  /**
   * Test the getWord method of WordData
   */
  @Test
  public void testWordDataGetWord() {
    GibberishWriter.WordData data = new GibberishWriter.WordData("Hello");
    assertEquals("Hello", data.getWord());
  }
  
  /**
   * Test the getCount method of WordData
   */
  @Test
  public void testGetCount() {
    GibberishWriter.WordData data = new GibberishWriter.WordData(null);
    assertEquals(1, data.getCount());
    
    data.incrementCount();
    data.incrementCount();
    assertEquals(3, data.getCount());
  }
  
  /**
   * Test the constructor for Context 
   */
  @Test
  public void testContext() {
    GibberishWriter.Context c = new GibberishWriter.Context("this", "is", "the", "shorthand");
    assertEquals("[this, is, the, shorthand]", c.toString());
  }
  
  /**
   * Test the getWords method of Context
   */
  @Test
  public void testGetWords() {
    String[] words = {"How", "Now", "Brown", "Cow"};
    GibberishWriter.Context c = new GibberishWriter.Context(words);
    assertEquals("[How, Now, Brown, Cow]", c.toString());
  }
  
  /** 
   * Test the length method of Context
   */
  @Test
  public void testLength() {
    
    String[] words = {"One", "Hello", "Why"};
    GibberishWriter.Context context = new GibberishWriter.Context(words);
    assertEquals(3, context.length());
  }
  
  /**
   * Test the getWord method of Context
   */
  @Test
  public void testContextGetWord() {
    
    String[] words = {"Hello", "Cat", "Fifty", "Nifty"};
    GibberishWriter.Context context = new GibberishWriter.Context(words);
    assertEquals("Hello", context.getWord(0));
    assertEquals("Cat", context.getWord(1));
    assertEquals("Fifty", context.getWord(2));
  }
  
  /**
   * Test the toString method of Context
   */
  @Test
  public void testContextToString() {
    
    String[] words = {"There", "Their", "They're"};
    GibberishWriter.Context context = new GibberishWriter.Context(words);
    assertEquals("[There, Their, They're]", context.toString());
  }
  
  /**
   * Test the equals method of Context
   */
  @Test
  public void testContextEquals() {
    
    String[] words1 = {"Bear", "Tiger", "Pedro"};
    String[] words2 = {"Bear", "Pedro", "Tiger"};
    String[] words3 = {"Bear", "Tiger", "Fire", "Hello"};
    String[] words4 = {"Bear", "Tiger", "Pedro"};
    GibberishWriter.Context context1 = new GibberishWriter.Context(words1);
    GibberishWriter.Context context2 = new GibberishWriter.Context(words2);
    GibberishWriter.Context context3 = new GibberishWriter.Context(words3);
    GibberishWriter.Context context4 = new GibberishWriter.Context(words4);
    assertFalse(context1.equals(context3));
    assertFalse(context1.equals(context2));
    assertTrue(context1.equals(context1));
    assertTrue(context1.equals(context4));
    assertTrue(context4.equals(context1));
  }
  
  /**
   * Test the compareTo method of Context
   */
  @Test
  public void testContextCompareTo() {
    
    String[] words1 = {"Bet", "Best", "Fly"};
    String[] words2 = {"Bet", "Best", "Fat"};
    GibberishWriter.Context context1 = new GibberishWriter.Context(words1);
    GibberishWriter.Context context2 = new GibberishWriter.Context(words2);
    assertEquals(11, context1.compareTo(context2));
  }
  
  /**
   * Test the constructor for ContextData
   */
  @Test
  public void testContextData() {
    GibberishWriter.ContextData g = new GibberishWriter.ContextData(new GibberishWriter.Context("New", "World"));
    assertEquals("[New, World]", g.getContext().toString());
    assertEquals(0, g.getOccurrences());
    assertEquals("", g.getData().toString());
  }
  
  /**
   * Test the getData method of ContextData
   */
  @Test
  public void testGetData() {
    String[] a = {};
    GibberishWriter.Context c = new GibberishWriter.Context(a);
    GibberishWriter.ContextData cD = new GibberishWriter.ContextData(c);
    cD.addFollowingWord("Hello");
    assertEquals("Hello", cD.getData().getHead().getElement().getWord());
  }
  
  /**
   * Test the getContext method of ContextData
   */
  @Test
  public void testGetContext() {
    
    String[] words = {"Bear", "Tiger", "Pedro"};
    GibberishWriter.Context context = new GibberishWriter.Context(words);
    GibberishWriter.ContextData data = new GibberishWriter.ContextData(context);
    assertEquals(context, data.getContext());
  }
  
  /**
   * Test the getOccurrencs method of ContextData
   */
  @Test
  public void testGetOccurrences() {
    String[] words = {};
    GibberishWriter.Context c = new GibberishWriter.Context(words);
    GibberishWriter.ContextData cD = new GibberishWriter.ContextData(c);
    cD.addFollowingWord("First");
    cD.addFollowingWord("Second");
    cD.addFollowingWord("Third");
    assertEquals(3, cD.getOccurrences());
  }
  
  /**
   * Test the compareTo method of ContextData
   */
  @Test
  public void testContextDataCompareTo() {
    
    String[] words1 = {"Robert", "Chris", "Jake"};
    String[] words2 = {"Matt", "Anthony", "Patrick"};
    GibberishWriter.Context context1 = new GibberishWriter.Context(words1);
    GibberishWriter.Context context2 = new GibberishWriter.Context(words2);
    GibberishWriter.ContextData data1 = new GibberishWriter.ContextData(context1);
    GibberishWriter.ContextData data2 = new GibberishWriter.ContextData(context2);
    assertEquals(2, data1.compareTo(data2));
  }
  
  /**
   * Test the addFollowingWord method of ContextData
   */
  @Test
  public void testAddFollowingWord() {
    
    String[] words1 = {};
    GibberishWriter.Context context1 = new GibberishWriter.Context(words1);
    GibberishWriter.ContextData data1 = new GibberishWriter.ContextData(context1);
    data1.addFollowingWord("Robert");
    assertEquals(1, data1.getOccurrences());
    
    LLNode<GibberishWriter.WordData> nodeptr = data1.getData().getHead();
    assertEquals(1, nodeptr.getElement().getCount());
    assertEquals("Robert", data1.getFollowingWord(1));
  }
  
  /**
   * Test the getFollowingWord method of ContextData
   */
  @Test
  public void testGetFollowingWord() {
    String[] words1 = {};
    GibberishWriter.Context context1 = new GibberishWriter.Context(words1);
    GibberishWriter.ContextData data1 = new GibberishWriter.ContextData(context1);
    data1.addFollowingWord("Robert");
    data1.addFollowingWord("Panther");
    data1.addFollowingWord("Rabbit");
    data1.addFollowingWord("Robert");
    assertEquals("Robert", data1.getFollowingWord(1));
    assertEquals("Robert", data1.getFollowingWord(2));
    assertEquals("Panther", data1.getFollowingWord(3));
    assertEquals("Rabbit", data1.getFollowingWord(4));
  }
}