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
public class DroppableObject extends DraggableObject {
  private VisibleObject target; // object over which this object can be dropped
  private Action action; // action that results from dropping this object over target
  /** initialize new object
   * 
   * @param name name of droppable object
   * @param x horizontal location of object
   * @param y vertical location of object
   * @param target visible object that is waiting for a droppable object to drop on it
   * @param action the action produced when the correct object is dropped onto target object
   */
  public DroppableObject(String name, int x, int y, VisibleObject target, Action action) {
    super(name, x, y);
    this.target = target;
    this.action = action;
  }
  /** returns action and deactivates objects
   * in response to successful drop
   * When this object is over its target and its target is active:
   * deactivate both this object and the target object, and return action,
   * otherwise return null
   * 
   */
  @Override
  protected Action drop() {
    if(this.isOver(target) && target.isActive()) {
      target.deactivate();
      this.deactivate();
      return this.action;
    }
    return null;
  } 
}
