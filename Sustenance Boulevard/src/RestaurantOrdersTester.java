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
 * This class implements unit test methods to check the correctness of LinkedOrders and 
 * RestaurantOrders classes defined in the CS300 Fall 2020 - P07 Restaurant Orders programming 
 * assignment.
 *
 */
public class RestaurantOrdersTester {

  /**
   * This method should test and make use of at least the LinkedOrders constructor, an accessor
   * (getter) method, and a mutator (setter) method defined in the LinkedOrders class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedOrders() {
    try {
      Order temp = new Order("test", 0);
      Order temp2 = new Order("test2", 1);
      LinkedOrder order = new LinkedOrder(temp);
      LinkedOrder order2 = new LinkedOrder(temp2);
      order.setNext(order2);
      order2.setPrevious(order);
      if(!order.getNext().getOrder().getDishes().equals("test2"))
        return false;
    } catch (IllegalArgumentException iae) {
      iae.getMessage();
      iae.printStackTrace();
    } catch (Exception e) {
      e.getMessage();
      e.printStackTrace();
    }
    return true;
  }

  /**
   * This method checks for the correctness of both RestaurantOrders constructors and the instance
   * method isEmpty() when called on an empty restaurant orders object.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersConstructorIsEmpty() {
    RestaurantOrders ro = new RestaurantOrders(2);
    if(!ro.isEmpty())
      return false;
    return true;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders(int) constructor when passed a
   * negative int value for the capacity.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersConstructorBadInput() {
    try {
      RestaurantOrders ro = new RestaurantOrders(-2);
    } catch (IllegalArgumentException iae) {
      return true;
    }
    return false;
  }


  /**
   * This method checks for the correctness of the RestaurantOrders.placeOrder()() method when it is 
   * passed bad inputs. This method must consider at least the test scenarios provided in the detailed
   * description of these javadocs. (1) Try adding a null to the list; (2) Try adding an order which
   * carries a negative timestamp. (3) Try adding an order with an existing timestamp to the list.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersAddBadInput() {
    try {
      RestaurantOrders ro = new RestaurantOrders(5);
      Order order1 = new Order("Burger", -3);
      ro.placeOrder(order1);
      return false;
    } catch (IllegalArgumentException iae) {
      ;
    } 
    try {
      RestaurantOrders ro = new RestaurantOrders(5);
      Order order2 = null;
      ro.placeOrder(order2);
      return false;
    } catch (IllegalArgumentException iae2) {
      ;
    } 
    try {
      RestaurantOrders ro = new RestaurantOrders(5);
      Order order3 = new Order("Pizza", 2);
      Order order4 = new Order("Sandwich", 2);
      ro.placeOrder(order3);
      ro.placeOrder(order4);
      return false;
    } catch (IllegalArgumentException iae3) {
      return true;
    }
  }

  /**
   * This method checks for the correctness of the RestaurantOrders.placeOrder()() considering at least
   * the test scenarios provided in the detailed description of these javadocs. (1) Try adding an order
   * to an empty list; (2) Try adding an order which is expected to be added at the head of a non-empty
   * restaurant list; (3) Try adding an order which is expected to be added at the end of a non-empty
   * restaurant list; (4) Try adding an order which is expected to be added at the middle of a 
   * non-empty restaurant list. For each of those scenarios, make sure that the size of the list is
   * appropriately updated after a call without errors of the add() method, and that the contents of
   * the list is as expected whatever if list is read in the forward or backward directions. You can
   * also check the correctness of RestaurantOrders.get(int), RestaurantOrders.indexOf(Order), and
   * RestaurantOrders.size() in this test method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersAdd() {
    // Add order to empty list
    RestaurantOrders orders = new RestaurantOrders(5);
    System.out.println(orders.readForward());
    Order order1 = new Order("Burger", 1);
    orders.placeOrder(order1);
    System.out.println(orders.readForward());
    // Add order to head of non-empty list
    Order order2 = new Order("Sandwich", 0);
    orders.placeOrder(order2);;
    System.out.println(orders.readForward());
    // Add order to end of non-empty list
    Order order3 = new Order("Pizza", 3);
    orders.placeOrder(order3);
    System.out.println(orders.readForward());
    // Add order to middle of non-empty list
    Order order4 = new Order("Falafel", 2);
    orders.placeOrder(order4);
    System.out.println(orders.readBackward());
    return true;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders.removeOrder() considering at least 
   * the test scenarios provided in the detailed description of these javadocs. (1) Try removing an 
   * order from an empty list or pass a negative index to RestaurantOrders.removeOrder() method; (2) 
   * Try removing an order (at position index 0) from a list which contains only one order; (3) Try to
   * remove an order (position index 0) from a list which contains at least 2 orders; (4) Try to remove
   * an order from the middle of a non-empty list containing at least 3 orders; (5) Try to remove the 
   * order at the end of a list containing at least two orders. For each of those scenarios, make sure
   * that the size of the list is appropriately updated after a call without errors of the add() 
   * method, and that the contents of the list is as expected whatever if list is read in the forward 
   * or backward directions.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersRemove() {
    // remove from empty list or pass a negative index to removeOrder()
    try {
    RestaurantOrders ro = new RestaurantOrders(5);
    ro.removeOrder(0);
    return false;
    } catch (IndexOutOfBoundsException iob) {
      ;
    }
    // remove an order at index 0 from list with only one order
    try {
    RestaurantOrders ro = new RestaurantOrders(5);
    Order order1 = new Order("Burger", 1);
    ro.placeOrder(order1);
    ro.removeOrder(0);
    } catch (IndexOutOfBoundsException iob) {
      return false;
    }
    // remove an order at index 0 from list with at least 2 orders
    try {
    RestaurantOrders ro = new RestaurantOrders(5);
    Order order2 = new Order("Sandwich", 1);
    Order order3 = new Order("Pizza", 2);
    ro.placeOrder(order2);
    ro.placeOrder(order3);
    ro.removeOrder(0);
    } catch (IndexOutOfBoundsException iob) {
      return false;
    }
    // remove an order from middle of non-empty list with at least 3 orders
    try {
    RestaurantOrders ro = new RestaurantOrders(5);
    Order order4 = new Order("Falafel", 3);
    Order order5 = new Order("Ice cream", 4);
    Order order6 = new Order("Gelatto", 5);
    ro.placeOrder(order4);
    ro.placeOrder(order5);
    ro.placeOrder(order6);
    ro.removeOrder(1);
    } catch (IndexOutOfBoundsException iob) {
      return false;
    }
    // remove order at end of a list with at least 2 orders
    try {
      RestaurantOrders ro = new RestaurantOrders(5);
      Order order7 = new Order("Salmon", 6);
      Order order8 = new Order("Crab", 7);
      ro.placeOrder(order7);
      ro.placeOrder(order8);
      ro.removeOrder(1);
    } catch (IndexOutOfBoundsException iob) {
      return false;
    }
    return true;
  }


  /**
   * This method calls all the test methods defined and implemented in your RestaurantOrdersTester
   * class.
   * 
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
    if(!testRestaurantOrdersAdd())
      return false;
    if(!testLinkedOrders())
      return false;
    if(!testRestaurantOrdersConstructorIsEmpty())
      return false;
    if(!testRestaurantOrdersConstructorBadInput())
     return false;
    if(!testRestaurantOrdersAddBadInput())
      return false;
    if(!testRestaurantOrdersRemove())
      return false;
    return true;
  }

  /**
   * Driver method defined in this RestaurantOrdersTester class
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    System.out.println("runAllTests: " + runAllTests());
  }
}