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
import processing.core.PApplet;


public class InteractiveObject {
  private static PApplet processing = null;
  private final String NAME; // the constant name identifying this interactive object
  private boolean isActive; // active means this interactive object is visible and
  // can be interacted with

  /** initializes the name of this object, and sets isActive to true
   * 
   * @param name name of the interactive object being created
   */
  public InteractiveObject(String name) { 
    this.NAME = name;
    this.isActive = true;
  }
  /** returns true only when contents of name equal NAME
   *                                
   * @param name name of the interactive object
   * @return true if input name is equal to interactive object name
   */
  public boolean hasName(String name) {
    return (this.NAME.equals(name));
  } 
  /** returns true only when isActive is true
   * 
   * @return true if interactive object is active, false otherwise
   */
  public boolean isActive() {
    return this.isActive;
  } 
  /** changes isActive to true
   * 
   */
  public void activate() {
    this.isActive = true;
  } 
  /** changes isActive to false
   * 
   */
  public void deactivate() {
    this.isActive = false;
  } 
  /** this method returns null
   * subclass types will override this update() method to do more interesting things
   * 
   * @return null
   */
  public Action update() { 
    return null; 
  }
  /** initializes processing field
   * 
   * @param processing PApplet being initialized
   */
  public static void setProcessing(PApplet processing) {
    InteractiveObject.processing = processing;
  } 
  /** accessor method to retrieve this static field
   * 
   * @return PApplet
   */
  protected static PApplet getProcessing() {
    return InteractiveObject.processing;
  } 
}
