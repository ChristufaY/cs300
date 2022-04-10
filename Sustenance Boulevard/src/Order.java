//////////////// FILE HEADER (INCLUDE IN EVERY FILE) ///////////////
///////////
//
// Title: P07 Sustenence Boulevard
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
/**
 * This class models and implements an order which carries dishes of type String and timeStamp of type int
 *
 */
public class Order implements Comparable<Order> {
  private String dishes;  // a String representation of the dishes carried 
                          // by this order
  private long timeStamp; // Time at which this order was placed, as with System.currentTimeMillis()
  /**
   * Creates a new Order carrying the given dishes and corresponding time stamp
   * 
   * @param dishes string representation of the dishes which are going to be contained in this order
   * @param timeStamp integer representing the time at which this order was placed
   */
  public Order(String dishes, long timeStamp) {
    this.dishes = dishes;
    this.timeStamp = timeStamp;
  }

  /**
   * Returns String representation of the dishes carried by this Order
   * 
   * @return a String representation of the dishes carried by this Order
   */
  @Override
  public String toString() {
    return dishes;
  }

  /**
   * Returns the dishes contained in this order
   * 
   * @return a the dishes contained in this order
   */
  public String getDishes() {
    return dishes;
  }

  /**
   * Compares this order with another, based on which one was placed earlier
   * 
   * @return a negative integer, zero, or a positive integer as this Order has a time stamp which is less
   *         than, equal to, or greater than the specified other Order's timestamp.
   */
  @Override
  public int compareTo(Order other) {
    if(timeStamp == other.timeStamp) {
    	return 0;
    }
    else if(timeStamp < other.timeStamp) {
    	return -1;
    }
    else {
    	return 1;
    }
  }
  
  /**
   * Determines whether the content of this order's dishes field is the same as another's.
   * Does NOT compare timestamps; use compareTo() method for timestamp comparison.
   * 
   * @return true if and only if this.dishes is the same as other.dishes; false otherwise
   */
  public boolean equals(Object other) {
    if (other instanceof Order) {
      return ((Order) other).dishes.equals(this.dishes);
    }
    return false;
  }

}