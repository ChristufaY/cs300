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
import java.util.ArrayList;

public class Action {
  private String message; // message printed by this action (or null to do nothing) 
  private InteractiveObject object;
  // create and initialize this new action
  public Action(String message) {
    this.message = message;
  } 
  
  /** when message is not null, message is printed to System.out // this was original purpose
   * new purpose is to check interactive object and see if it is non null and activated, if
   * it is activated, add to input arraylist, then set object back to null so that same object
   * won't be activated twice
   * @param io input arraylist that gets interactive objects added to it if they are activated
   */
  public void act(ArrayList<InteractiveObject> io) {
    if(this.object != null) {
      this.object.activate();
      io.add(this.object);
      this.object = null;
    }
    if(this.message != null)
      System.out.println(this.message);
  } 
  /**
   * initializes the class interactive object
   * @param object input interactive object
   */
  public Action(InteractiveObject object) {
    this.object = object;
  }
  /**
   * initializes the message and interactive object
   * @param message message that the action outputs
   * @param object interactive object to be activated later
   */
  public Action(String message, InteractiveObject object) {
    this.object = object;
    this.message = message;
  }
}
