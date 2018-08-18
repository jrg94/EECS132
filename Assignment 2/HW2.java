 /** 
 * This class uses methods that work with words or Strings
 * @author Jeremy Griffith
 */
public class HW2 {
  
  /** 
   * Returns a string with a fixed number of characters removed from the front of the original String
   * @param phrase the String in which to remove a set number of characters
   * @param prefix an int that defines how many characters are to be removed from the front of the phrase parameter
   * @return a String built from the phrase parameter minus the prefix parameter of characters from the front
   */
  public static String removePrefix(String phrase, int prefix) {
    // Stores the characters as they're built in the loop
    StringBuilder builder = new StringBuilder();
    // Creates a String without the initial prefix
    for(int index = 0; index < phrase.length(); index = index + 1) {
      char character = phrase.charAt(index);
      if (index >= prefix) {
        builder.append(character);
      }
    }
    return builder.toString();
  }
  
 /**
  * Returns a string with the any whitespace characters before the first non-whitespace character removed
  * @param phrase the String in which to cut off any front side whitespaces
  * @return the phrase parameter minus the initial whitespaces
  */
  public static String trimSpacesFromFront(String phrase) {
    // Stores the characters as they're built in the loop
    StringBuilder builder = new StringBuilder();
    int index;
    // Tracks the index of the first non-whitespace in the string
    for(index = 0; index < phrase.length() && Character.isWhitespace(phrase.charAt(index)); index = index + 1) {
    };
    // Creates a String without the initial whitespaces
    while(index < phrase.length()) {
      char character = phrase.charAt(index);
      builder.append(character);
      index = index + 1;
    }
    return builder.toString();
  }
  
  /**
   * Returns the number of words in a String
   * @param phrase the String in which to be counted for words
   * @return the word count found in the phrase parameter
   */
  public static int countWords(String phrase) {
    int index = 1;
    // Stores the amount of words that appear in a String
    int wordCounter = 0;
    // If the length of the String is 0, then the method returns 0
    if (phrase.length() < index) {
      return 0;
    }
    // If there are any spaces before the first word, then the word counter starts at one
    else if (phrase.charAt(index - 1) != ' ') {
       wordCounter = wordCounter + 1;
    }
    // Counts a word everytime the loop reaches a letter with a space behind it
    while(index < phrase.length()) {
      char character = phrase.charAt(index);
      // Stores a boolean to tell whether the previous character is a space or not
      boolean previousWhiteSpace = Character.isWhitespace(phrase.charAt(index - 1));
      // Stores a boolean to tell whether the current character is a space or not
      boolean currentWhiteSpace = Character.isWhitespace(character);
      // Adds one to word counter every time the current character is a letter and the previous is a whitespace
      if (previousWhiteSpace && !currentWhiteSpace){
        wordCounter = wordCounter + 1;
      }
      index = index + 1;
    }
    return wordCounter;
  }
  
  /**
   * Returns a String that does not exceed a desired length.
   * @param phrase a String to be truncated
   * @param desiredLength an int to specify a desired length
   * @return a new String built with enough characters to satisfy the desiredLength
   */
  public static String truncate(String phrase, int desiredLength) {
    // Stores the characters as they're built in the loop
    StringBuilder builder = new StringBuilder();
    int index;
    if (phrase.length() <= desiredLength) {
      return phrase;
    } 
    else if (countWords(phrase) == 1) {
      // Tracks the index of the String up to the first letter
      for(index = 0; index < phrase.length() && Character.isWhitespace(phrase.charAt(index)); index = index + 1) {
      };
      // Tracks the index of the String to the end of the first word
      for(; !Character.isWhitespace(phrase.charAt(index + 1)); index = index + 1) {
      };
      // Stores the index of the end of the first word
      int newIndex = index + 1;
      // Creates a String containing all the characters from index 0 to the new index
      for(index = 0; index < newIndex; index = index + 1) {
        char character = phrase.charAt(index);
        builder.append(character);
      }
    }
    else if (countWords(phrase) > 1) {
      // Tracks the index of the String up to the first letter
      for(index = 0; index < phrase.length() && Character.isWhitespace(phrase.charAt(index)); index = index + 1) {
      };
      // Tracks the index of the String to the end of the first word
      for(; !Character.isWhitespace(phrase.charAt(index + 1)); index = index + 1) {
      };
      // Stores the index of the end of the first word
      int nextIndex = index + 1;
      // Creates a String containing all the characters from index 0 to the next index
      for(index = 0; index < nextIndex; index = index + 1) {
        char character = phrase.charAt(index);
        builder.append(character);
      }
      // Counts back from the desired length until the index matches a whitespace
      for(index = desiredLength; index > nextIndex && !Character.isWhitespace(phrase.charAt(index)); index = index - 1) {
      };
      // Stores the index of the whitespace
      int finalIndex = index;
      // Creates a String from index 0 to the final index
      for(index = nextIndex; index < finalIndex; index = index + 1) {
        char character = phrase.charAt(index);
        builder.append(character);
      }
    }
    return builder.toString();
  }
 
  
  /**
   * Returns a String with extra spaces added evenly between words
   * @param phrase a String to be stretched to the length parameter with spaces added evenly to the initial spaces
   * @param length an int that specifies the length of the new String derived from phrase
   * @return a new String with additional spaces corresponding to the amount of current spaces versus the new length
   */
  public static String padString(String phrase, int length) {
    StringBuilder builder = new StringBuilder();
    int index;
    // Stores the amount of spaces found between the first word and last word
    int spaceCount = 0;
    // Finds the index of the first non-whitespace
    for(index = 0; index < phrase.length() && Character.isWhitespace(phrase.charAt(index)); index = index + 1) {
    };
    // Stores the index of the first non-whitespace character
    int firstCharIndex = index;
    // Finds the index of the last non-whitespace
    for(index = phrase.length() - 1; index > firstCharIndex && Character.isWhitespace(phrase.charAt(index)); index = index - 1) {
    };
    // Stores the index of the last non-whitespace
    int lastCharIndex = index;
    // Stores the the number of times a space occurs in the restricted string
    for(index = 0; index < lastCharIndex; index = index + 1) {
      // Stores a boolean to tell if the current character is a space or not
      boolean currentChar = Character.isWhitespace(phrase.charAt(index));
      // Stores a boolean to tell if the next character is a space or not
      boolean nextChar = Character.isWhitespace(phrase.charAt(index + 1));
      if (currentChar && !nextChar) {
        spaceCount = spaceCount + 1;
      }
    }
    // Stores the length of the String from the first non-whitespace to the last non-whitespace
    int newLength = lastCharIndex - firstCharIndex;
    // Begins a String with all the spaces from the beginning
    for(index = 0; index < firstCharIndex; index = index + 1) {
      char character = phrase.charAt(index);
      builder.append(character);
    }
    // Stores the amount of spaces needed to be added throughout the entire String
    int totalSpacesToAdd = (length - phrase.length());
    // Adds to the builder String with the first non-whitespace character to the last non-whitespace character
    for(index = firstCharIndex; index < lastCharIndex; index = index + 1) {
      char character = phrase.charAt(index);
      boolean currentChar = Character.isWhitespace(phrase.charAt(index));
      boolean nextChar = Character.isWhitespace(phrase.charAt(index + 1));
      // Adds a String of characters until the loop hits a space
      if (!currentChar) {
        builder.append(character);
      }
      // Adds the correct number of spaces needed for the string to match the specified length
      else if (currentChar && !nextChar) {
        // Stores the number of spaces to be added at this particular space
        int numberAdded = (totalSpacesToAdd / spaceCount);
        // Adds the correct number of spaces to the current space
        for(int newIndex = 0; newIndex <= numberAdded; newIndex = newIndex + 1) {
          builder.append(' ');
        }
        totalSpacesToAdd = totalSpacesToAdd - numberAdded;
        spaceCount = spaceCount - 1;
      }
    }
    // Ends a String with all the spaces from the end
    for(index = lastCharIndex; index < phrase.length(); index = index + 1) {
      char character = phrase.charAt(index);
      builder.append(character);
    }
    return builder.toString();
  }
  
  /**
   * Prints a block from the String input with a specified width
   * @param phrase a String to be formed into a column
   * @param width an int that specifies how long the line can be before it creates a new line
   */
  public static void prettyPrint(String phrase, int width) {
    // Stores the characters as they're built in the loop
    StringBuilder builder = new StringBuilder();
    int index;
    // Stores the truncated, trimmed, and padded form of the line to be printed
    String line;
    // Stores the length of the builder before being trimmed, truncated, and padded
    int lineLength = width;
    // Prints a line from current index to the specified width
    for(index = 0; phrase.length() > index && lineLength >= width - 1;) {
      // Builds a String from the current index to the index of the width times the line number
      for(; builder.toString().length() <= width && phrase.length() > index; index = index + 1) {  
        char character = phrase.charAt(index);
        builder.append(character);
      }
      // Stores the index of the nearest space behind the width index
      for(; !Character.isWhitespace(phrase.charAt(index - 1)); index = index - 1) {
      };
      lineLength = builder.toString().length();
      line = padString(truncate(trimSpacesFromFront(builder.toString()), width - 1), width);
      System.out.println(line);
      // Resets the builder
      builder = new StringBuilder();
    } 
  }
}
          
        