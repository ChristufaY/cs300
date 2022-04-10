///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: Christopher Yang
// Campus ID: 9082293151
// WiscEmail: cyang397@wisc.edu
////////////////////////////////////////////////////////////////////////////////


import java.util.ArrayList;
import java.util.Collections; // will be used for sorting your Furniture Inventory list later

/**
 * This class models Furniture objects which can be added to an inventory. Each object contains a
 * furniture's inventory number, a name, and weight, with accessors for these three data fields.
 * 
 * TODO: Modify this class to implement the Comparable interface, so that a Furniture object can be
 * compared to another Furniture object, and any collection of Furniture objects can be sorted.
 * 
 * Note: Do NOT add any additional data fields! You are allowed to add private helper methods 
 * if needed.
 * Note: MAKE SURE TO SAVE your source file before uploading it to gradescope.
 */
class Furniture implements Comparable<Furniture> {
  private static int inventoryNumGenerator = 1000; // generator of unique inventory numbers
  private final int INVENTORY_ID; // a unique inventory number assigned to this furniture
  private String name; // name of this furniture
  private int weight; // weight in lbs of this furniture

  /**
   * Create a new Furniture object
   * 
   * @param name   the name of the furniture
   * @param weight the weight of the Furniture in pounds
   */
  public Furniture(String name, int weight) {
    // TODO: Complete the implementation of this constructor to initialize all the instance fields
    INVENTORY_ID = 0; // CHANGE THIS, included only to avoid compile errors
  }

  /**
   * Access the name of this furniture
   * 
   * @return the Furniture's name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Access the weight of this Furniture
   * 
   * @return the Furniture's weight in LB
   */
  public int getWeight() {
    return this.weight;
  }

  /**
   * Access the inventory number of this Furniture
   * 
   * @return the inventory number of this Furniture
   */
  public int getInventoryId() {
    return this.INVENTORY_ID;
  }

  /**
   * Should return true if and only if the Object o is a Furniture with the same name as this one.
   * 
   * @param o the Object to compare to
   * @return true if the Object is a Furniture, and this furniture and object have equal names;
   *         false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if((o instanceof Furniture) && ((Furniture)o).getName() == this.getName())
      return true;
    return false; // CHANGE THIS, included only to avoid compile errors
  }
  @Override
  public int compareTo(Furniture f) {
    if(this.weight < f.getWeight())
      return -1;
    else if(this.weight > f.getWeight())
      return 1;
    else if(this.weight == f.getWeight())
      return 0;
    return 0;
  }
  
  // TODO: add any methods required by the Comparable interface here.
  // The furniture objects will be compared with respect to their weights.
  // A furniture is greater than another furniture if its weight is higher than the other
  // furniture's weight.
  // Two furniture items are equal if they have the same weight.
  // A furniture item is smaller than another furniture item if its weight is lower than the other
  // item's weight.
}

// Note: MAKE SURE TO SAVE your source file before uploading it to gradescope.



