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
public class DraggableObject extends VisibleObject {
  private boolean mouseWasPressed; // similar to use in ClickableObject
  private boolean isDragging; // true when this object is being dragged by the user
  private int oldMouseX; // horizontal position of mouse during last update
  private int oldMouseY; // vertical position of mouse during last update
  /**
   * initializes new draggable object
   * @param name name of draggable object
   * @param x horizontal location of the object
   * @param y vertical location of the object
   */
  public DraggableObject(String name, int x, int y) { 
    super(name, x, y);
    mouseWasPressed = false;
    isDragging = false;
    oldMouseX = InteractiveObject.getProcessing().mouseX;
    oldMouseY = InteractiveObject.getProcessing().mouseY;
  }
  /** calls VisibleObject update() first, then moves
   * according to mouse drag
   * each time isDragging changes from true to false, the drop() method below will be
   * called once and any action objects returned from that method should then be
   * returned from update()
   * 
   */
  @Override
  public Action update() {
    super.update();
    mouseWasPressed = InteractiveObject.getProcessing().mousePressed;
    int mouseX = InteractiveObject.getProcessing().mouseX;
    int mouseY = InteractiveObject.getProcessing().mouseY;
    if(isOver(mouseX, mouseY) && mouseWasPressed)
      isDragging = true;
    if(isDragging) {
      move(mouseX - oldMouseX, mouseY - oldMouseY);
    }
    oldMouseX = mouseX;
    oldMouseY = mouseY;
    if(!mouseWasPressed)
      isDragging = false;
    if(!isDragging)
      return drop();
    return null;
  } 
  /** this method returns null. subclass types will override this drop() method to perform 
   * more interesting behavior
   * 
   * @return null
   */
  protected Action drop() { 
    return null; 
  } 
}
