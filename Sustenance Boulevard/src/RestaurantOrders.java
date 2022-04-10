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
public class RestaurantOrders implements SortedListADT<Order>{
  private LinkedOrder head; // front of doubly linked list
  private LinkedOrder tail; // end of doubly linked list
  private int size; // number of orders currently in the list
  private final int CAPACITY; // Maximum capacity of order for this list

  public RestaurantOrders() {
    this.CAPACITY = 20;
    this.size = 0;
    this.head = null;
    this.tail = head;
  }
  public RestaurantOrders(int capacity) {
    if(capacity <= 0)
      throw new IllegalArgumentException("Cannot have negative or zero capacity.");
    this.CAPACITY = capacity;
    this.size = 0;
    this.head = null;
    this.tail = null;
  }
  public int capacity() {
    return this.CAPACITY;
  }
  public String readForward() {
    if(this.size == 0)
      return "The list contains 0 order(s)";
    String order = "The list contains " + this.size + " order(s): [";
    LinkedOrder temp = head;
    while(temp != null) {
      order += " " + temp.getOrder().toString();
      temp = temp.getNext();
    }
    order += " ]";
    return order;
  }
  public String readBackward() {
    if(this.size == 0)
      return "The list contains 0 order(s)";
    String order = "The list contains " + this.size + " order(s): [";
    LinkedOrder temp = tail;
    while(temp != null) {
      order += " " + temp.getOrder().toString();
      temp = temp.getPrevious();
    }
    order += " ]";
    return order;
  }
  public void clear() {
    this.size = 0;
    this.head = null;
    this.tail = null;
  }
  public Order get(int index) {
    if(size <= 0 || size < index + 1)
      throw new IndexOutOfBoundsException();
    int count = 0;
    LinkedOrder temp = head;
    while(count != index) {
      temp = temp.getNext();
      count++;
    }
    return temp.getOrder();
  }
  public int indexOf(Order findOrder) {
    int count = 0;
    LinkedOrder temp = head;
    while(temp != null) {
      if(temp.getOrder().getDishes().equals(findOrder.getDishes())) {
        return count;
      }
      temp = temp.getNext();
      count++;
    }
    return -1;
  }
  public boolean isEmpty() {
    return this.size == 0;
  }
  public void placeOrder(Order newOrder) {
    if(newOrder == null)
      throw new IllegalArgumentException("Order cannot be null.");
    if(this.size < this.CAPACITY) {
      LinkedOrder newLO = new LinkedOrder(newOrder);
      Boolean found = false;
      LinkedOrder temp = head;
      if(this.size == 0) {
        this.head = newLO;
        this.tail = newLO;
        this.size++;
      } else {
        while(temp != null) {
          if(temp.getOrder().compareTo(newOrder) == 0)
            throw new IllegalArgumentException("Timestamp already exists.");
          else if(temp.getOrder().compareTo(newOrder) > 0) {
            //System.out.println("inside placeOrder else if");
            found = true;
            if(temp.getPrevious() == null) {
              this.head = newLO;
              head.setNext(temp);
              temp.setPrevious(newLO);
            } else {
              //System.out.println("Should be Sandwich " + temp.getOrder().getDishes());
              newLO.setPrevious(temp.getPrevious());
              //System.out.println("newLO getPrev " + newLO.getPrevious().getOrder().getDishes());
              newLO.setNext(temp);
              //System.out.println("newLO getNext " + newLO.getNext().getOrder().getDishes());
              temp.getPrevious().setNext(newLO);
              //System.out.println("temp getPrev.getNext " + temp.getPrevious().getNext().getOrder().getDishes());
              temp.setPrevious(newLO);
              //System.out.println("temp getPrev " + temp.getPrevious().getOrder().getDishes());
            }
            this.size++;
            break;
          }
          temp = temp.getNext();
        }
        if(!found) {
          newLO.setPrevious(tail);
          //System.out.println("newLO getPrev " + newLO.getPrevious().getOrder().getDishes());
          newLO.setNext(null);
          //System.out.println("newLO getNext " + newLO.getNext().getOrder().getDishes());
          tail.setNext(newLO);
          //System.out.println("tail getPrev.getNext " + tail.getPrevious().getNext().getOrder().getDishes());
          this.tail = newLO;
          //System.out.println("tail getPrev " + tail.getPrevious().getOrder().getDishes());
          this.size++;
        }
      }
    }
  }
  public Order removeOrder(int index) {
    if(size <= 0 || !(size > index) || index < 0)
      throw new IndexOutOfBoundsException("Index out of bounds.");
    int count = 0;
    LinkedOrder temp = head;
    while(count != index) {
      temp = temp.getNext();
      count++;
    }
    if(temp.getPrevious() == null && temp.getNext() == null) {
      this.head = null;
      this.tail = null;
    } else if(temp.getPrevious() == null) {
      head = temp.getNext();
      head.setPrevious(null);
    } else if(temp.getNext() == null) {
      tail = temp.getPrevious();
      tail.setNext(null);
    } else {
      temp.getPrevious().setNext(temp.getNext());
      temp.getNext().setPrevious(temp.getPrevious());
    }
    this.size--;
    return temp.getOrder();
  }
  public int size() {
    return this.size;
  }
}
