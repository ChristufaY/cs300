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
import processing.core.PImage;

public class VisibleObject extends InteractiveObject {
  private PImage image; // the graphical representation of this object
  private int x; // the horizontal position (in pixels of this object’s left side)
  private int y; // the vertical position (in pixels of this object’s top side)

  /** initialize this new VisibleObject 
   * the image for this visible object should be loaded from :
   * "images"+File.separator+ name +".png"
   * @param name string representation of the name of visible object
   * @param x horizontal location of object
   * @param y vertical location of the object
   */
  public VisibleObject(String name, int x, int y) {
    super(name);
    this.image = InteractiveObject.getProcessing()
        .loadImage("images" + File.separator + name + ".png");
    this.x = x;
    this.y = y;
  } 
  @Override
  /** draws image at its position before returning null
   * 
   */
  public Action update() {
    InteractiveObject.getProcessing().image(this.image, this.x, this.y);
    return null;
  }   
  /** changes x by adding dx to it (and y by dy)
   * 
   * @param dx change in horizontal value
   * @param dy change in vertical value
   */
  public void move(int dx, int dy) {
    this.x += dx;
    this.y += dy;
  } 
  /** return true only when point x,y is over image
   * 
   * @param x horizontal location being checked
   * @param y vertical location being checked
   * @return true if x,y is over the image
   */
  public boolean isOver(int x, int y) {
    return((x > this.x && x < this.x + this.image.width) 
        && (y > this.y && y < this.y + this.image.height));
  } 
  /** return true only when other’s image
   * overlaps this one’s
   * @param other visible object being compared to
   * @return true if object is over the other, false otherwise
   */
  public boolean isOver(VisibleObject other) {
    boolean overlap = false;
    for(int i = 0; i < this.image.width; i++)
      for(int j = 0; j < this.image.height; j++)
        if((other.x + i > this.x && other.x < this.x + this.image.width)
            && (other.y - j < this.y && other.y > this.y - this.image.height))
          overlap = true;
    return overlap;
  } 
}
