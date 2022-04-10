///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: Christopher Yang
// Campus ID: 9082293151
// WiscEmail: cyang397@wisc.edu
////////////////////////////////////////////////////////////////////////////////

// MAKE SURE TO SAVE your source file before uploading it to gradescope.
import java.util.NoSuchElementException;
/**
 * This class models a winner in an annual auto-racing competition
 * 
 * TODO: Override the public int compareTo(Winner other){} method conforming to the
 * specification provided below. 
 *   A winner is smaller than another winner if its year is smaller than the other 
 *   winner's year. 
 *   A winner is greater than another winner if its year is greater than the other 
 *   winner's year.
 *   Two winners are equals if they have the same winning year. 
 * 
 */
class Winner implements Comparable<Winner>{
  //Do not add any additional data field to this class
  private String name; // name of this winner
  private String country; // home country of this winner
  private int year; // competition year of this winner
  private int recordTime; // record time accomplished by this winner

  // Do not make any change to the constructor and the getter/setter methods of this class
  // The only method to change in this class is compareTo() method

  /**
   * Creates a new winner
   * 
   * @param name name to be assigned to this winner
   * @param country home country of this winner
   * @param year competition year
   * @param time race record time accomplished by this winner
   */
  public Winner(String name, String country, int year, int time) {
    this.name = name;
    this.country = country;
    this.year = year;
    this.recordTime = time;
  }

  /**
   * Compares this Winner to another winner based on their competition years
   * 
   * @param other other winner to compare to
   * @return a non-zero positive integer if this winner is greater than the other winner, zero
   *         if this winner equals other, a non-zero negative integer if this winner is less
   *         than the other winner.
   */
  @Override
  public int compareTo(Winner other) {
    // TODO Complete the implementation of this method
    if(this.year < other.getYear())
      return -1;
    if(this.year == other.getYear())
      return 0;
    if(this.year > other.getYear())
      return 1;
    return 1;
    // TODO CHANGE THIS: included to avoid compiler errors
  }

  /**
   * Checks whether this winner equals a specific object
   * 
   * @param o an object
   * @return true if this winner equals the input provided object and false otherwise
   */
  @Override
  public boolean equals(Object o) {
    return o instanceof Winner && this.year == (((Winner) o).year);
  }

  /**
   * Returns a String representation of this winner
   * 
   * @return a String representation of this winner
   */
  @Override
  public String toString() {
    return this.name + "(" + this.year + ")";
  }

  /**
   * Gets the record race time of this winner
   * @return the time
   */
  public int getRecordTime() {
    return recordTime;
  }

  /**
   * Sets the record race time of this winner
   * 
   * @param recordTime the record time to set
   */
  public void setRecordTime(int recordTime) {
    this.recordTime = recordTime;
  }

  /**
   * Returns the name of this winner
   * 
   * @return the name of this winner
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the home country of this winner
   * 
   * @return the country of this winner
   */
  public String getCountry() {
    return country;
  }

  /**
   * Returns the competition year of this winner
   * 
   * @return the competition year
   */
  public int getYear() {
    return year;
  }

}


//MAKE SURE TO SAVE your source file before uploading it to gradescope.
/**
 * This class models a binary search tree (BST) which stores records in an annual auto-race competition.
 * The key search in this BST is the competition year.
 *
 * TODOs: Complete the missing implementation of the following methods 
 *   1. isEmpty() 
 *   2. size() 
 *   3. addWinnerHelper()
 *   4. search()
 *   5. updateRecordTime()
 *   6. getMin()
 *   7. deleteHelper()
 */
public class AutoRaceRecords {
  private BSTNode<Winner> root; // root of this BST
  private int size; // total number of winners stored in this auto-racing records BST

  // You do not need to define a constructor for this class. The compiler will generate
  // automatically a no-argument constructor which creates a new empty Winnerrecords.

  /**
   * Checks whether this BST is empty
   * 
   * @return true if this BST is empty and false otherwise
   */
  public boolean isEmpty() {
    return this.size == 0; // TODO CHANGE THIS: included to avoid compiler errors
  }

  /**
   * Returns the size of this BST
   * 
   * @return the total number of winners stored in this BST
   */
  public int size() {
    return this.size; // TODO CHANGE THIS: included to avoid compiler errors
  }
  //MAKE SURE TO SAVE your source file before uploading it to gradescope.


  /**
   * Adds a new winner to this BST. No changes will be made to the contents of this BST in case of 
   * trying to add a duplicate.
   * 
   * @param newWinner to be added to this BST
   * @throws IllegalStateException if a duplicate with a newWinner is found in this BST.
   */
  public void addWinner(Winner newWinner) {
    // DO NOT MAKE ANY CHANGE TO THE IMPLEMENTATION OF THIS METHOD
    root = addWinnerHelper(newWinner, root);
  }

  /**
   * Recursive helper method to add newWinner to the subtree rooted at node. 
   * 
   * @param newWinner new winner to add
   * @param node    root of the subtree where to add newWinner.
   * @return the new root of the subtree after adding newWinner.
   * @throws IllegalStateException with or without error message if a duplicate with a newWinner is 
   *         found in this BST.
   */
  protected BSTNode<Winner> addWinnerHelper(Winner newWinner, BSTNode<Winner> node) {
    // TODO complete the implementation of this method


    // DO NOT CHECK whether newWinner is null or not. The method will automatically throw
    // a NullPointerException if this case occurs.
    int comparison = newWinner.compareTo(node.getData());

    // base case2: subtree contains a match with newWinner
    if(comparison == 0)
      throw new IllegalStateException("Duplicate winner.");
    // if one exist
    if(node.getLeft() != null || node.getRight() != null) {
      BSTNode<Winner> temp;
      BSTNode<Winner> optimalChild = node.getLeft();
      // if both exist
      if(node.getLeft() != null && node.getRight() != null) {
        comparison = newWinner.compareTo(node.getData());
        // left child is greater
        if(comparison < 0)
          optimalChild = node.getLeft();
        // right child is greater
        if(comparison > 0) 
          optimalChild = node.getRight(); 
      }
      // swap with whichever was greater if it is greater than parent
      if(newWinner.compareTo(node.getData()) < 0) {
        temp = optimalChild;
        optimalChild.setData(newWinner);
        newWinner = temp.getData();
        addWinnerHelper(newWinner, temp);
      }

    }   
    // base case1: the current subtree is empty (node is null) --> add newWinner, update size, and
    // return the new root (node) of this subtree
    node.setData(newWinner);
    this.size++;
    return node; // return the root of this subtree
  }
  //MAKE SURE TO SAVE your source file before uploading it to gradescope.

  /**
   * Looks for the winner of the auto-racing competition held in a specific year
   *
   * @param year competition year
   * @return the winner on the specific year
   * @throws IllegalStateException with the error message "No winner found!"  if no winner was found
   *         for the provided year.
   */
  public Winner search(int year) {
    // DO NOT CHECK FOR THE VALIDITY of the input year
    // TODO Implement this method
    if(searchHelper(year, this.root) == null )
      throw new IllegalStateException("No winner found!");

    return searchHelper(year, this.root); // CHANGE THIS: included to avoid compiler errors
  }

  /**
   * Recursive helper method which looks for the winner in the specific year
   * 
   * @param year competition year
   * @param node root of a subtree of this BST
   * @return the winner of the competition held at the specific year.
   * @throws IllegalStatetException with the error message "No winner found!" if no winner in the 
   *         specific year was not found in the subtree rooted at node
   * 
   */
  protected static Winner searchHelper(int year, BSTNode<Winner> node) {
    // DO NOT MAKE ANY CHANGE TO THE IMPLEMENTATION OF THIS METHOD
    if (node == null) // reach a leaf or this binary search tree empty
      throw new IllegalStateException("No winner found!"); // unsuccessful search

    if (year == node.getData().getYear())
      return node.getData(); // successful search
    if (year < node.getData().getYear())
      return searchHelper(year, node.getLeft()); // recurse on the left child (left sub-tree)
    return searchHelper(year, node.getRight()); // recurse on the right child (right sub-tree)
  }


  /**
   * Updates the record time of the winner who won the race in a specific year
   * It PRINTS "No winner found!" if no winner was found for that year.
   * This method MUST NOT throw any exception.
   * 
   * @param year competition year
   * @param time record time to set
   */
  public void updateRecordTime(int year, int time) {
    // TODO Complete the implementation of this method
    Winner temp = search(year);
    if(temp == null)
      System.out.println("No winner found!");
    temp.setRecordTime(time);
  }

  /**
   * Returns the smallest winner (with respect to the result of Winner.compareTo() method) 
   * in the subtree rooted at node 
   * 
   * @param node root of a subtree of a binary search tree
   * @return the smallest winner in the subtree rooted at node and null if node is null.
   */
  protected static Winner getMin(BSTNode<Winner> node) {
    // TODO complete the implementation of this method with respect to its specification
    // Feel free to design a recursive or an iterative implementation of this method
    if(node == null)
      return null;
    BSTNode<Winner> temp = null;
    for(int i = 0; i < 1000; i++) {
      if(node.getData().compareTo(node.getLeft().getData()) < 0)
        temp = node;
    }
    return temp.getData();
    // TODO CHANGE THIS: included to avoid compiler errors
  }
  //MAKE SURE TO SAVE your source file before uploading it to gradescope.  

  /**
   * Deletes a winner given their phone number from this BST. 
   * @param year competition year
   * @throws NoSuchElementException if no match with year was found in this BST.
   */
  public void delete(int year) {
    // DO NOT MAKE ANY CHANGE TO THE IMPLEMENTATION OF THIS METHOD
    Winner toDelete = new Winner("winner", "US", year, 0);   
    root = deleteHelper(toDelete, root);
    size--;
  }

  /**
   * Deletes a winner from this records if match found with the provided one
   * 
   * @param winner a winner equal to the winner to be removed
   * @param node the root of a subtree of a binary search tree of winners
   * @return the new root of the subtree after removing the specified winner
   * @throws NoSuchElementException with or without error message if if no match found
   *         
   */
  protected static BSTNode<Winner> deleteHelper (Winner winner, BSTNode<Winner> node) {
    // TODO Complete the missing code in this implementation
    if (node == null) { // Winner not found
      throw new NoSuchElementException();
    }
    if (winner.compareTo(node.getData()) < 0) {
      // TODO recurse on the left subtree rooted at node.getLeft()
      // Hint: node.setLeft(/* recursive call */);
      node.setLeft(deleteHelper(winner, node.getLeft()));

    }else if (winner.compareTo(node.getData()) > 0) {
      // TODO recurse on the right subtree
      node.setRight(deleteHelper(winner, node.getRight()));
    } else // Winner found
      if (node.getLeft() != null && node.getRight() != null) { // node has two children      
        // TODO replace the data of node with the data field value of its successor
        node.setData(winner);
        // remove the successor
        node.setRight(deleteHelper(node.getData(), node.getRight()));
      } else // node has up to one child
        if (node.getLeft() != null) { // node has one left child
          // TODO Set node to its left child
          node.setLeft(node.getLeft());
        }else { // node has one right child or zero children
          // TODO Set node to its right child
          node.setRight(node.getRight());
        }  
    return node; // return the new root of this subtree otherwise the changes to node will be lost
  }
  //MAKE SURE TO SAVE your source file before uploading it to gradescope.

  /**
   * Returns a String representation of the contents of this Winner records sorted 
   * in the increasing order
   * 
   * @return a String representation of this Winner records
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Returns a string representation of this BST
   * 
   * @param node root of a subtree of a BST storing winners
   * @return a String representation of the subtree rooted at node
   */
  protected static String toStringHelper(BSTNode<Winner> node) {
    String s = "";
    if (node == null)
      return s;
    else {
      // visit left subtree
      s = s + toStringHelper(node.getLeft());
      // visit data within the current node
      s = s + (node.getData()) + "; ";
      // visit right subtree
      s = s + toStringHelper(node.getRight());
      return s;
    }
  }

  /**
   * Runs a demo of this code
   */
  public static void demo() {
    AutoRaceRecords records = new AutoRaceRecords();
    System.out.println("Size: " + records.size() + " ; Contents: " + records);
    // Size: 0 ; Contents:
    records.addWinner(new Winner("w1", "US", 2000, 300));
    System.out.println("Size: " + records.size() + " ; Contents: " + records);
    // Size: 1 ; Contents: w1(2000);
    records.addWinner(new Winner("w2", "US", 2019, 250));
    System.out.println("Size: " + records.size() + " ; Contents: " + records);
    // Size: 2 ; Contents: w1(2000); w2(2019);
    records.addWinner(new Winner("w3", "US", 1995, 270));
    System.out.println("Size: " + records.size() + " ; Contents: " + records);
    // Size: 3 ; Contents: w3(1995); w1(2000); w2(2019);
    records.addWinner(new Winner("w4", "US", 2005, 260));
    System.out.println("Size: " + records.size() + " ; Contents: " + records);
    // Size: 4 ; Contents: w3(1995); w1(2000); w4(2005); w2(2019);
    records.addWinner(new Winner("w5", "US", 1990, 310));
    System.out.println("Size: " + records.size() + " ; Contents: " + records);
    // Size: 5 ; Contents: w5(1990); w3(1995); w1(2000); w4(2005); w2(2019);
    records.addWinner(new Winner("w6", "US", 1998, 280));
    System.out.println("Size: " + records.size() + " ; Contents: " + records);
    // Size: 6 ; Contents: w5(1990); w3(1995); w6(1998); w1(2000); w4(2005); w2(2019);
    System.out.println("Try to update the record time for 2005 race. ");
    records.updateRecordTime(2005, 275);
    // Try to update the record time for 2005 race.
    System.out.print("Try to update the record time for 2010 race. ");
    records.updateRecordTime(2010, 200);
    // Try to update the record time for 2010 race. No winner found!
    records.delete(2019);
    System.out.println("Size: " + records.size() + " ; Contents: " + records);
    // Size: 5 ; Contents: w5(1990); w3(1995); w6(1998); w1(2000); w4(2005);
    records.delete(1995);
    System.out.println("Size: " + records.size() + " ; Contents: " + records);
    // Size: 4 ; Contents: w5(1990); w6(1998); w1(2000); w4(2005);
  }

  /**
   * main method
   * @param args input arguments
   */
  public static void main(String[] args) {
    demo();
  }
}