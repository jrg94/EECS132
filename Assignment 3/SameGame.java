/**
 * This is a class that creates a simple computer game. I did Extra Credit #2.
 * @author Jeremy Griffith
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SameGame implements ActionListener {
  
  // Stores the JFrame that holds the game
  private JFrame game;
  
  // Stores the arrays of JButtons and their instances once they are created
  private JButton[][] buttons;
  
  // Stores the height of the grid
  private int height;
  
  // Stores the width of the grid
  private int width;
  
  // Stores the x-coordinate of the array
  private int x;
  
  // Stores the y-coordinate of the array
  private int y;
  
  /**
   * This constructor creates a standard grid of 12x12 buttons for the game
   */
  public SameGame() {
    this.height = 12;
    this.width = 12;
    game = new JFrame("Same Game");
    JPanel board = new JPanel(new GridLayout(12, 12));
    board.setBorder(BorderFactory.createMatteBorder(40,40,40,40, 
    new ImageIcon(getClass().getResource("Users/Jeremy/Desktop/Java Course/Images/snowflake-thumbnail.gif"))));
    buttons = new JButton[12][12];
    Color[] colors = {Color.red, Color.blue, Color.green};
    Container c = game.getContentPane();
    // Runs through all the columns
    for(y = 0; y < 12; y++) {
      // Runs through all the rows
      for(int x = 0; x < 12; x++) {
        buttons[y][x] = new JButton();
        buttons[y][x].setBackground(colors[(int)(Math.random() * 3)]);
        buttons[y][x].addActionListener(this);
        board.add(buttons[y][x]);
      }
    }
    c.add(board, "Center");
    game.setSize(650,650);    
    game.setVisible(true);
  }
  
  /**
   * This constructor changes the default height, width and number of colors for the game
   * @param height the number of buttons per column for the grid
   * @param width the number of buttons per row for the grid
   * @param numColors the number of colors used in the game
   */
  public SameGame(int height, int width, int numColors) {
    this.height = height;
    this.width = width;
    game = new JFrame("Same Game");
    JPanel board = new JPanel(new GridLayout(height, width));
    board.setBorder(BorderFactory.createMatteBorder(40,40,40,40, 
    new ImageIcon(getClass().getResource("Users/Jeremy/Desktop/Java Course/Images/snowflake-thumbnail.gif"))));
    buttons = new JButton[height][width];
    Color[] colors = {Color.red, Color.blue, Color.green, Color.pink, Color.yellow, Color.magenta};
    Container c = game.getContentPane();
    for(y = 0; y < height; y++) {
      for(x = 0; x < width; x++) {
        buttons[y][x] = new JButton();
        buttons[y][x].setBackground(colors[(int)(Math.random() * numColors)]);
        buttons[y][x].addActionListener(this);
        board.add(buttons[y][x]);
      }
    }
    c.add(board, "Center");
    game.setSize(650, 650);    
    game.setVisible(true);
  }
  
  /**
   * Returns the width of the game board
   * @return the private field width
   */
  public int getWidth() {
    return width;
  }
  
  /** 
   * Returns the height of the game board
   * @return the private field height
   */
  public int getHeight() {
    return height;
  }
  
  /**
   * Replaces a column of buttons with the column of buttons to the right
   * @param rowY the row at which to begin copying the column to the right
   * @param columnX the column at which to clone as the column to the right
   */
  public void copyColumn(int rowY, int columnX) {
    for(rowY = 0; rowY < this.getHeight(); rowY++) {
      buttons[rowY][columnX].setBackground(buttons[rowY][columnX + 1].getBackground());
    }
  }
  
  /**
   * Designates the action for any button in the game when pressed
   * @param e the ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    JButton button = (JButton)e.getSource();
    Color color = button.getBackground();
    int buttonX = 0;
    int buttonY = 0;
    int numUp = 0;
    int numDown = 0;
    int totHor = 0;
    int totVert = 0;
    int numLeft = 0;
    int numRight = 0;
    // Identifies which button is being pressed and stores the coordinates
    for(y = 0; button != buttons[buttonY][buttonX] ; y++) {
      // Stores the x-coordinate for the button pressed
      for(x = 0; x < this.getWidth() && button != buttons[y][buttonX]; x = x + 1) {
        buttonX = x;
      }
      buttonY = y;
    }
    // If the button is not at the starting position, the y-coordinate is then subtracted by one
    if (button != buttons[0][0]) {
      y = y - 1;
    }
    // Tracks the number of spaces that are contiguous to this current space to the left
    for(x = buttonX; x >= 0 && button.getBackground() == buttons[y][x].getBackground(); x--) {
      numLeft++;
    }
    // Counts the number of spaces that share the same contiguous color from left to right
    for(x = x + 1; x < this.getWidth() && button.getBackground() == buttons[y][x].getBackground(); x++) {
      totHor++;
      numRight++;
    }
    // Tracks the number of spaces above this space that are contiguous
    for(x = buttonX; y >= 0 && button.getBackground() == buttons[y][x].getBackground(); y--) {
      numUp++;
    }
    // Counts the number of spaces that share the same contiguous color from the top down
    for(y = y + 1; y < this.getHeight() && button.getBackground() == buttons[y][x].getBackground(); y++) {
      totVert++;
      numDown++;
    }
    // Checks to see if this button is in a column of the same color
    if (totVert >=2 && totHor == 1) {
      // Loops until the current button is not the same color as the original
      for(int i = 0; i < totVert;i++) {
        // Runs through a column replacing each button with the color above it
        for(y = (buttonY - numUp) + numDown, x = buttonX; y >= 0; y--) {
          // Sets the current button's background to gray if it is in the first row
          if (y == 0) {
            buttons[y][x].setBackground(Color.gray);
          }
          // Sets the current button's background to the color of the button immediately above it
          else {
            buttons[y][x].setBackground(buttons[y - 1][x].getBackground());
          }
        }
      }
    }
    // Checks to see if this button is in a row of the same color
    else if (totHor >= 2 && totVert == 1) {
      // Runs through each contiguous horizontal button
      for(x = buttonX - numLeft + 1; x < buttonX + (numRight - numLeft + 1); x++) {
        // Replaces every button with the button above it
        for(y = buttonY; y >= 0; y--) {
          // Sets the current button's background to gray if it is in the first row
          if (y == 0) {
            buttons[y][x].setBackground(Color.gray);
          }
          // Sets the current button's background to the color of the button immediately above it
          else {
            buttons[y][x].setBackground(buttons[y - 1][x].getBackground());
          }
        }
      }
    }
    // Checks to see if this button is in a cross of the same button
    else if (totHor >= 2 && totVert >= 2) {
      // Loops until the current button is not the same color as the original
      for(int i = 0; i < totVert;i++) {
        // Runs through a column replacing each button with the color above it
        for(y = (buttonY - numUp) + numDown, x = buttonX; y >= 0; y--) {
          // Sets the current button's background to gray if it is in the first row
          if (y == 0) {
            buttons[y][x].setBackground(Color.gray);
          }
          // Sets the current button's background to the color of the button immediately above it
          else {
            buttons[y][x].setBackground(buttons[y - 1][x].getBackground());
          }
        }
      }
      // Runs through each contiguous horizontal button
      for(x = buttonX - numLeft + 1; x < buttonX + (numRight - numLeft + 1); x++) {
        // Replaces every button with the button above it
        for(y = buttonY; y >= 0; y--) {
          // Sets the current button's background to gray if it is in the first row
          if (y == 0) {
            buttons[y][x].setBackground(Color.gray);
          }
          // Sets the current button's background to the color of the button immediately above it
          else {
            // Skips the current button because it has already been deleted
            if (x == buttonX) {
            }
            // Sets the button to the color of the button above it
            else {
              buttons[y][x].setBackground(buttons[y - 1][x].getBackground());
            }
          }
        }
      }
    }
    // Runs through a row
    for(x = this.getWidth() - 1; x >= 0; x--) {
      // Checks to see if the button on the bottom of the grid at index x is gray
      if(buttons[this.getHeight() - 1][x].getBackground() == Color.gray) {
        // If the button is gray, this loop runs through the rest of the row
        for(int column = x; column < this.getWidth(); column++) {
          // If this is the last button in the row, it turns the entire column gray
          if(column == this.getWidth() - 1) {
            // Replaces a column of buttons with gray buttons
            for(int row = 0; row < this.getHeight(); row++) {
              buttons[row][column].setBackground(Color.gray);
            }
          }
          // Otherwise, this copies the row next to the current row into the current row
          else {
            this.copyColumn(y, column);
          }
        }
      }
    }
  }
  
  /**
   * Creates a new cross game with default settings
   */
  public static void crossGame() {
    new SameGame();
  }
  
  /**
   * Creates a new cross game with three parameters
   * @param height the number of buttons high for the grid
   * @param width the number of buttons wide for the grid
   * @param numColors the number of colors used in the game
   */
  public static void crossGame(int height, int width, int numColors) {
    new SameGame(height, width, numColors);
  }
  
  /**
   * The main method for the game
   * @param args an array of Strings that act as inputs for the main method
   */
  public static void main(String[] args) {
    // Checks to see if the length of the String array is greater than 0
    if (args.length > 0) {
      // Tries to check for arguments within these limits
      try {
        int height = Integer.parseInt(args[0]);
        int width = Integer.parseInt(args[1]);
        int numColors = Integer.parseInt(args[2]);
        // Prints an error if the number of colors is less than 2 or more than 6
        if (numColors < 2 || numColors > 6) {
          System.out.print("Your last value must be at or between 2 and 6.");
        }
        // The arguments were entered correctly. The game loads with the chosen settings
        else {
          SameGame.crossGame(height, width, numColors);
        }
      }
      // Prints an error if the user enters a number outside the array
      catch (ArrayIndexOutOfBoundsException e) {
        System.out.print("You did not enter three arguments.");
      }
      // Prints an error if the user enters a negative number
      catch (NegativeArraySizeException e) {
        System.out.print("You can not enter a negative number.");
      }
    }
    // Runs the game as default
    else {
      SameGame.crossGame();
    }
  }
}