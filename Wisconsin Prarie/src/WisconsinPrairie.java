//////////////// FILE HEADER (INCLUDE IN EVERY FILE) ///////////////
///////////
//
// Title: P02 Wisconsin Prarie
// Course: CS 300 Fall 2020
//
// Author: Christopher Yang
// Email: cyang397@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP ///////////////
///////////
// Persons: None
// Online Sources: None
//
////////////////////////////////////////////////////////////////////
///////////
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

public class WisconsinPrairie {
  private static PApplet processing; // PApplet object that represents the graphic
  // interface of the WisconsinPrairie application
  private static PImage backgroundImage; // PImage object that represents the background
  // image
  private static Cow[] cows; // array storing the current cows present in the prairie
  private static Random randGen; // Generator of random numbers

  public static void main(String[] args) {
    Utility.startApplication();

  }
  /**
   * Defines the initial environment properties of the application
   * @param processingObj represents a reference to the graphical interface of the application
   */
  public static void setup(PApplet processingObj) {
    processing = processingObj; // Initialize the processing field to the one passed 
    // into the input argument parameter.
    // initialize and load the image of the background
    backgroundImage = processing.loadImage("images/background.png");
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    // width [resp. height]: System variable of the processing library that stores
    // the width [resp. height] of the display window.
    // initialize an Cow[] with 10 null references
    cows = new Cow[10];
    randGen = new Random();
  }
  /**
   * Draws and updates the application display window.
   * this callback method called in an infinite loop/
   */
  public static void draw() {
    // background update
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    // cows update
    for(int i = 0; i < cows.length; i++) {
      if(cows[i] != null)
        cows[i].draw();
    }
  }
  /**
   * Checks if the mouse is over a given cow whose reference is provided
   * as input parameter
   * 
   * @param cow reference to a given cow object
   * @return true if the mouse is over the given cow object (i.e. over the
   * image of the cow), false otherwise.
   */
  public static boolean isMouseOver(Cow cow) {
    // calculated the boundaries of the cow image
    float cowWidthStart = cow.getPositionX() - (cow.getImage().width/2);
    float cowWidthEnd = cow.getPositionX() + (cow.getImage().width/2);
    float cowHeightStart = cow.getPositionY() - (cow.getImage().height/2);
    float cowHeightEnd = cow.getPositionY() + (cow.getImage().height/2);
    // Checks to see if mouse is in the boundary of the image
    if((processing.mouseX <= cowWidthEnd && processing.mouseX >= cowWidthStart) 
        && (processing.mouseY <= cowHeightEnd && processing.mouseY >= cowHeightStart))
      return true;
    return false;
  }
  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() { 
    // loops through the cows array, ignores null values,
    // and finds the lowest index cow in overlap and allows that to be dragged
    for(int i = 0; i < cows.length; i++) {
      if(cows[i] != null) {
        if(isMouseOver(cows[i]) == true && cows[i].isDragging() == false)  {
          cows[i].setDragging(true);
          cows[i].setPositionX(processing.mouseX);
          cows[i].setPositionY(processing.mouseY);
          // added a break here because it doesn't need to find the next cow after the 
          // lower index cow is selected
          break;
        }
      }
    }
  }
  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    // turns every cow's "isDragging" to false because none are selected
    for(int i = 0; i < cows.length; i++) {
      if(cows[i] != null) {
        cows[i].setDragging(false);
      }
    }
  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {
    // creates a new cow at the next null index value
    if(processing.key == 'C' || processing.key == 'c') {
      for(int i = 0; i < cows.length; i++) {
        if(cows[i] == null) {
          float x = (float)randGen.nextInt(processing.width);
          float y = (float)randGen.nextInt(processing.height);        
          cows[i] = new Cow(processing, x, y);
          cows[i].draw();
          break;
        }
      }
    } 
    // deletes the cow with the mouse hovering over it
    if(processing.key == 'd' || processing.key == 'D') {
      for(int j = 0; j < cows.length; j++) {
        if(cows[j] != null && isMouseOver(cows[j]) == true) {
          cows[j] = null;
          processing.image(backgroundImage, processing.width / 2, processing.height / 2);
          for(int k = 0; k < cows.length; k++) {
            if(cows[k] != null) {
              cows[k].draw();
            }
          }
          break;
        }
      }
    }
  }

}











