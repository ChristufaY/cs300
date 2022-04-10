//////////////// FILE HEADER (INCLUDE IN EVERY FILE) ///////////////
///////////
//
// Title: P06 Treasure Hunt
// Course: CS 300 Fall 2020
//
// Author: Christopher Yang
// Email: cyang397@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP ///////////////
///////////
// Persons: Father
// Online Sources: None
//
////////////////////////////////////////////////////////////////////
///////////
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.Scanner;

public class TreasureHunt extends PApplet {

  private PImage backgroundImage;
  private ArrayList<InteractiveObject> gameObjects;
  /**
   * Sets the size of this application’s display window
   */
  @Override
  public void settings() { 
    size(800,600); 
  }
  /**
   * Defines initial environment properties, loads background images and fonts,
   * loads the clues, and initializes the instance fields, as the program starts. */
  @Override
  public void setup() {
    gameObjects = new ArrayList<InteractiveObject>();
    //this.getSurface().setTitle("Treasure Hunt"); // Displays the title of the display window
    //this.backgroundImage = loadImage("images" + File.separator + "background-scene.png");
    InteractiveObject.setProcessing(this);
    //Action cuteKoala = new Action("What a cute stuffed koala! It looks like a real one!");
    //ClickableObject koala = new ClickableObject("koala", 350, 65, cuteKoala);
    //gameObjects.add(koala);
    //Action open = new Action("Open sesame!");
    //VisibleObject chest = new VisibleObject("chest", 365, 400);
    //gameObjects.add(chest);
    //DroppableObject key = new DroppableObject("key", 70, 70, chest, open);
    //gameObjects.add(key);
    //VisibleObject phone = new VisibleObject("phone", 700, 490);
    //phone.deactivate();
    //if(!key.isActive() && !chest.isActive())
    //phone.activate();
    loadGameSettings("clues" + File.separator + "treasureHunt.clues");

  }
  /**
   * Updates the treasure hunt game display window
   */
  @Override
  public void draw() { 
    image(backgroundImage, 0, 0);
    for(int i = 0; i < gameObjects.size(); i++) {
      Action temp = gameObjects.get(i).update();
      if(temp != null)
        temp.act(gameObjects);
    }
    for(int j = gameObjects.size() - 1; j >= 0; j--)
      if(gameObjects.get(j).isActive() == false)
        gameObjects.remove(j);
  }
  /**
   * Main method to launch graphic application
   * @param args input arguments
   */
  public static void main(String[] args) {
    PApplet.main("TreasureHunt");
  }

  /**
   * This method loads a background image, prints out some introductory text,
   * and then reads in a set of interactive objects descriptions from a text file
   * with the provided name. These represent the different clues for our 
   * treasure hunt adventure game. The image is stored in this.backgroundImage, 
   * and the activated interactive objects are added to the this.gameObjects list.
   * 
   * @param filename - relative path of file to load, relative to current working directory
   */
  private void loadGameSettings(String filename) {
    // start reading file contents
    Scanner fin = null;
    int lineNumber = 1; // report first line in file as lineNumber 1
    try {
      fin = new Scanner(new File(filename));

      // read and store background image
      String backgroundImageFilename = fin.nextLine().trim();
      backgroundImageFilename = "images" + File.separator + backgroundImageFilename + ".png";
      backgroundImage = loadImage(backgroundImageFilename);
      lineNumber++;

      // read and print out introductory text
      String introductoryText = fin.nextLine().trim();
      System.out.println(introductoryText);
      lineNumber++;

      // then read and create new objects, one line per interactive object
      while (fin.hasNextLine()) {
        String line = fin.nextLine().trim();
        if (line.length() < 1)
          continue;

        // fields are delimited by colons within a given line
        String[] parts = line.split(":");
        InteractiveObject newObject = null;

        // first letter in line determines the type of the interactive object to create
        if (Character.toUpperCase(line.charAt(0)) == 'C')
          newObject = loadNewClickableObject(parts);
        else if (Character.toUpperCase(line.charAt(0)) == 'D')
          newObject = loadNewDroppableObject(parts);

        // even deactivated object references are being added to the gameObjects arraylist,
        // so they can be found.
        // these deactivated object references will be removed, when draw() is first called
        gameObjects.add(newObject);
        if (Character.isLowerCase(line.charAt(0))) // lower case denotes non-active object
          newObject.deactivate();
        lineNumber++;
      }

      // catch and report warnings related to any problems experienced loading this file
    } catch (FileNotFoundException e) {
      System.out.println("WARNING: Unable to find or load file: " + filename);
    } catch (RuntimeException e) {
      System.out.println("WARNING: Problem loading file: " + filename + " line: " + lineNumber);
      e.printStackTrace();
    } finally {
      if (fin != null)
        fin.close();
    }
  }

  /**
   * Helper method to retrieve interactive objects' references from the 
   * gameObjects list, based on their names. If multiple objects have that
   * name, this method will return the first (lowest-index) reference found.
   * 
   * @param name is the name of the object that is being found
   * @return a reference to an interactive object with the specified name, 
   *         or null when none is found
   */
  private InteractiveObject findObjectByName(String name) {
    for (int i = 0; i < gameObjects.size(); i++)
      if (gameObjects.get(i).hasName(name)) {
        return gameObjects.get(i);
      }
    System.out.println("WARNING: Failed to find an interactive object with name: " + name);
    return null;
  }

  /**
   * This method creates and returns a new ClickableObject based on the properties
   * specified as strings within the provided parts array.
   * 
   * @param parts contains the following strings in this order: 
   *     - C: indicates that a ClickableObject is being created
   *     - name: the name of the newly created interactive object 
   *     - x: the starting x position (as an int) for this object
   *     - y: the starting y position (as an int) for this object
   *     - message: a string of text to display when this object is clicked 
   *     - name of the object to activate (optional): activates this object when clicked
   * @return the newly created object
   */
  private ClickableObject loadNewClickableObject(String[] parts) {
    // C: name: x: y: message: name of object to activate (optional)

    // parse parts
    String name = parts[1].trim();
    int x = Integer.parseInt(parts[2].trim());
    int y = Integer.parseInt(parts[3].trim());
    String message = parts[4].trim();
    InteractiveObject activate = null;
    if (parts.length > 5)
      activate = findObjectByName(parts[5].trim());
    // create new clickable object
    ClickableObject newObject = new ClickableObject(name, x, y, 
        new Action(message, activate));
    return newObject;
  }

  /**
   * This method creates and returns a new DroppableObject based on the properties
   * specified as strings within the provided parts array.
   * 
   * @param parts contains the following strings in this order: 
   *   - D: indicates that a DroppableObject is being created 
   *   - name: the name of the newly created droppable object
   *   - x: the starting x position (as an int) for this object
   *   - y: the starting y position (as an int) for this object 
   *   - message: a string of text to display when this object is dropped on target
   *   - name of the object to activate (optional): activates this object 
   *     when dropped on target
   *     
   * @return the newly created droppable object
   */
  private DroppableObject loadNewDroppableObject(String[] parts) {
    // D: name: x: y: target: message: name of object to activate (optional)

    // parse parts
    String name = parts[1].trim();
    int x = Integer.parseInt(parts[2].trim());
    int y = Integer.parseInt(parts[3].trim());
    InteractiveObject dropTarget = findObjectByName(parts[4].trim());
    if (!(dropTarget instanceof VisibleObject))
      dropTarget = null;
    String message = parts[5].trim();
    InteractiveObject activate = null;
    if (parts.length > 6)
      activate = findObjectByName(parts[6].trim());
    // create new droppable object
    DroppableObject newObject =
        new DroppableObject(name, x, y, (VisibleObject) dropTarget, new Action(message, activate));
    return newObject;
  }
}
