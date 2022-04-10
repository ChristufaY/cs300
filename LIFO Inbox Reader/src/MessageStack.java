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
import java.util.EmptyStackException;
import java.util.Iterator;
import java.lang.Iterable;

public class MessageStack implements StackADT<Message>, Iterable<Message>{
  private LinkedNode<Message> top; // refers to top of the linked stack
  private int size; // keeps track of total number of messages stores in stack
  
  public Message peek() {
    if(this.size == 0)
      throw new EmptyStackException();
    return top.getData();
  }
  
  public void push(Message m) {
    Message temp = m;
    if(temp == null)
      throw new IllegalArgumentException("Message is null.");
    LinkedNode<Message> message = new LinkedNode<Message>(m);
    message.setNext(top);
    top = message;
    this.size++;
  }
  
  public int size() {
    return this.size;
  }
  
  public Message pop() {
    if(this.size == 0)
      throw new EmptyStackException();
    Message m = top.getData();
    top = top.getNext();
    this.size--;
    return m;
  }
  
  public boolean isEmpty() {
    return (this.size == 0);
  }
  
  
  public Iterator<Message> iterator() {
    MessageStackIterator temp = new MessageStackIterator(this.top);
    return temp;
  }
}
