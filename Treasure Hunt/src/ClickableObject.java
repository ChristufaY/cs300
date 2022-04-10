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
public class ClickableObject extends VisibleObject {
  private Action action; // action returned from update when object is clicked
  private boolean mouseWasPressed; // tracks whether the mouse was pressed during the last update()
  /** Initializes this new object
   * 
   * @param name name of clickable object
   * @param x horizontal location of the object
   * @param y vertical location of the object
   * @param action action that is produced when a certain event happens
   */
  public ClickableObject(String name, int x, int y, Action action) {
    super(name, x, y);
    this.action = action;
    this.mouseWasPressed = false;
  }
  /** calls VisibleObject update, then returns action only when mouse is first clicked 
   * on this object
   * 
   */
  @Override
  public Action update() {
    super.update();
    int mouseX = InteractiveObject.getProcessing().mouseX;
    int mouseY = InteractiveObject.getProcessing().mouseY;
    boolean mousePressed = InteractiveObject.getProcessing().mousePressed;
    if(!mouseWasPressed && isOver(mouseX, mouseY) 
        && mousePressed) {
      mouseWasPressed = true;
      return this.action;
    } else {
      if(!mousePressed) {
        mouseWasPressed = false;
        return null;
      }
    }
    return null;
  }

}
