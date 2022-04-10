import java.util.ArrayList;
import java.util.Collections; // will be used for sorting your Furniture Inventory list later

/**
 * The FurnitureInventory class contains various Furniture objects, and offers various utility
 * methods for analyzing the contents of this inventory.
 * Note: Do NOT add any additional data fields! You are allowed to add private helper methods 
 * if needed.
 */
public class FurnitureInventory {

  // private static fields
  private static String[] names = {"desk", "chair", "sofa", "table", "book case", "lamp", "laptop",
      "desktop", "paper shredder", "printer"}; // names of all the furniture objects stored in this
                                               // inventory
  // private instance fields
  private ArrayList<Furniture> list; // list of all the furniture elements which belong to this
                                     // inventory

  // constructors
  /**
   * Default constructor: initializes this inventory's list of furniture.
   */
  public FurnitureInventory() {
    // TODO: initialize the FurnitureInventory's instance fields
    list = new ArrayList<Furniture>();
  }

  /**
   * Creates a backup copy of a given FurnitureInventory object
   * 
   * @param toCopy the FurnitureInventory to copy
   * @return a new FurnitureInventory object with the same contents as toCopy
   */
  public static FurnitureInventory copyOf(FurnitureInventory toCopy) {
    // TODO: create a new FurnitureInventory which is a deep copy (not the deepest) of toCopy
    // The two objects will have different ArrayLists objects which contain
    // the same elements
    FurnitureInventory copy = new FurnitureInventory();
    copy.list = toCopy.list;
      
    return copy; // CHANGE THIS, included only to avoid compile errors
  }


  // mutators
  /**
   * Adds a Furniture to the FurnitureInventory
   * 
   * @param f the Furniture to add
   */
  public void addFurniture(Furniture f) {
    // TODO add the provided Furniture to this FurnitureInventory's list and increment its number of
    // occurrences in the stock
    // You do not need to check whether the furniture to add is null or not
    list.add(f);
    
    
  }

  // accessors
  /**
   * Calculates the number of occurrences of a specific furniture in the list of this inventory
   * 
   * @param f a given furniture
   * @return the number of occurrences of f in the list of this inventory
   */
  public int getNumOccurrences(Furniture f) {
    // TODO calculate the number of furniture objects which match with f.
    // You do not need to check whether f is null or not.
    int occurences = 0;
    for(int i = 0; i < list.size(); i++)
      if(list.get(i).equals(f))
        occurences++;
    return occurences; // CHANGE THIS, included only to avoid compile errors
  }

  /**
   * A private helper method to sort the list of this inventory in ascending order. Useful for the
   * getHeaviestFurniture() method!
   * 
   * NOTE: do not uncomment this method until you have correctly implemented the Comparable
   * interface in the Furniture class; it will cause an error and your code will not compile.
   */
  // TODO uncomment the code below before you proceed
  private void sortInventory() {
    Collections.sort(this.list);
  }

  /**
   * Gets the inventory id of the heaviest Furniture stored within this FurnitureInventory
   * 
   * @return the inventory id of the highest-weight (heaviest) furniture item in the list of this
   *         FurnitureInventory
   */
  public int getHeaviestFurniture() {
    // TODO find and return the inventory id of the heaviest Furniture in this FurnitureInventory
    // Hint: implement the Comparable interface for Furniture first, then use the helper method
    // below.
    sortInventory();
    int heaviestIndex = 0;
    for(int i = 0; i < list.size(); i++)
      if(list.get(i).getWeight() > list.get(heaviestIndex).getWeight())
        heaviestIndex = i;
    return list.get(heaviestIndex).getInventoryId();

  }

// Note: MAKE SURE TO SAVE your source file before uploading it to gradescope.
}