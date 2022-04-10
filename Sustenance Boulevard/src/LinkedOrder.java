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
public class LinkedOrder {
  private final Order ORDER; // data field of this linked order
  private LinkedOrder previous; // reference to order before this one
  private LinkedOrder next; // reference to order after this one
  
  public LinkedOrder(Order order) {
    this.previous = null;
    this.next = null;
    this.ORDER = order;
    Order zero = new Order("Temp", 0);
    if(this.ORDER.compareTo(zero) == -1)
      throw new IllegalArgumentException("Cannot have a negative timestamp.");
  }
  public LinkedOrder(Order order, LinkedOrder previous, LinkedOrder next) {
    this.previous = previous;
    this.next = next;
    this.ORDER = order;
    Order zero = new Order("Temp", 0);
    if(this.ORDER.compareTo(zero) == -1)
      throw new IllegalArgumentException("Cannot have a negative timestamp.");
  }
  public Order getOrder() {
    return this.ORDER;
  }
  public LinkedOrder getPrevious() {
    return this.previous;
  }
  public LinkedOrder getNext() {
    return this.next;
  }
  public void setPrevious(LinkedOrder previous) {
    this.previous = previous;
  }
  public void setNext(LinkedOrder next) {
    this.next = next;
  }
}
