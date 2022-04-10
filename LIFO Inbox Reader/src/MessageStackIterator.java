//////////////// FILE HEADER (INCLUDE IN EVERY FILE) ///////////////
///////////
//
// Title: P08 LIFO Inbox Reader
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
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MessageStackIterator implements Iterator<Message>{
  private LinkedNode<Message> next; // keeps track of the next element in the iteration
  
  public MessageStackIterator(LinkedNode<Message> m) {
    this.next = m;
  }
  
  public Message next() {
    if(this.next == null)
      throw new NoSuchElementException("There are no items left.");
    LinkedNode<Message> message = this.next;
    this.next = this.next.getNext();
    return message.getData();
  }
  
  public boolean hasNext() {
    return (next != null);
  }
  
}
