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
import java.util.NoSuchElementException;
/**
 * This class implements unit test methods to check the correctness of the implementation of the
 * MessageStack, Inbox, and MessageStackIterator classes defined in the CS300 Fall 2020 - P08 LIFO
 * Inbox Reader programming assignment.
 *
 */
public class InboxReaderTester {

  /**
   * Calls your tester methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
//    System.out.println("testStackConstructorIsEmptyPushPeek(): " 
//        + testStackConstructorIsEmptyPushPeek());
//    System.out.println("testStackPop(): " 
//        + testStackPop());
//    System.out.println("testInboxReadMessage(): " 
//        + testInboxReadMessage());
//    System.out.println("testInboxReceiveMessage(): " 
//        + testInboxReceiveMessage());
//    System.out.println("testInboxMarkAllMessagesAsRead(): " 
//        + testInboxMarkAllMessagesAsRead());
//    System.out.println("testMessageStackIterator(): " 
//        + testMessageStackIterator());
    
    System.out.println("runInboxTestSuite(): " + runInboxTestSuite());
  }

  // add the runInboxReaderTestSuite() method and your additional tester methods

  /**
   * Checks for the correctness of the constructor of the MessageStack, MessageStack.push(),
   * MessageStack.peek(), MessageStack.isEmpty(), and MessageStack.size() methods. This method must
   * consider at least the test scenarios provided in the detailed description of these javadocs.
   * (1) Create a new MessageStack object. The new created stack must be empty and its size must be
   * zero. (2) You can consider calling peek method on the empty stack. An EmptyStackException is
   * expected to be thrown by the peek method call. (3) Then, push a Message onto the stack and then
   * use peek to verify that the correct item is at the top of the stack. Make sure also to check
   * that isEmpty() must return false and the size of the stack is one. The peek() method call
   * should not make any change to the contents of the stack. (4) You can further consider pushing
   * other elements onto the stack. Then, each call of peek() method should return the correct
   * Message object that should be at the top of the stack. After pushing a new message to the stack
   * double check that the size of the stack was incremented by one.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testStackConstructorIsEmptyPushPeek() {
    try {
      MessageStack m = new MessageStack();
      if(!m.isEmpty())
        return false;
      if(m.size() != 0)
        return false;
      m.peek();
      return false;
    } catch (EmptyStackException e) {
      ;
    }
    Message message = new Message("Test", "Tester");
    Message checker = new Message("Test", "Tester");
    MessageStack m = new MessageStack();
    m.push(message);
    if(m.isEmpty())
      return false;
    if(m.size() != 1)
      return false;
    if(!m.peek().getTEXT().equals(checker.getTEXT()))
      return false;
    Message message2 = new Message("Test2", "Tester2");
    Message checker2 = new Message("Test2", "Tester2");
    m.push(message2);
    if(m.size() != 2)
      return false;
    if(!m.peek().getTEXT().equals(checker2.getTEXT()))
      return false;
    return true;
  } //


  /**
   * Checks for the correctness of MessageStack.pop(). It calls MessageStack.push() and
   * MessageStack.peek() methods. This method must consider at least the test scenarios provided in
   * the detailed description of these javadocs. (1) Try to create a new empty MessageStack. Then,
   * try calling pop method on the empty stack. An EmptyStackException is expected to be thrown as a
   * result. (2) Try to push a message to the stack. Then, try to call the pop method on the stack
   * which contains only one element. Make sure that the pop() operation returned the expected
   * message, and that the stack is empty and its size is zero. (3) Then, try to push at least three
   * messages, then call pop method on the stack which contains 3 elements, the element at the top
   * of the stack must be returned and removed from the stack and its size must be decremented by
   * one. You can further keep popping the elements of the stack one by one until it becomes empty
   * and check each time that the pop operation performs appropriately.This test method must return
   * false if it detects at least one defect.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testStackPop() {
    try {
      MessageStack m = new MessageStack();
      m.pop();
      return false;
    } catch (EmptyStackException e) {
      ;
    }
    MessageStack m = new MessageStack();
    Message message = new Message("Test", "Tester");
    Message expected = new Message("Test", "Tester");
    m.push(message);
    Message actual = m.pop();
    if(!actual.getTEXT().equals(expected.getTEXT()))
      return false;
    if(!m.isEmpty())
      return false;
    if(m.size() != 0)
      return false;
    
    MessageStack m2 = new MessageStack();
    Message one = new Message("one", "one");
    Message two = new Message("two", "two");
    Message three = new Message("three", "three");
    Message expected2 = new Message("three", "three");
    m2.push(one);
    m2.push(two);
    m2.push(three);
    Message actual2 = m2.pop();
    if(!actual2.getTEXT().equals(expected2.getTEXT()))
      return false;
    if(m2.size() != 2)
      return false;
    if(m2.isEmpty())
      return false;
    return true;
  }

  /**
   * Checks for the correctness of the Inbox.ReadMessage() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxReadMessage() {
    // Define your own test scenarios to check the correctness of Inbox.ReadMessage()
    // Your test method must return false if it detects at least one defect
    // Vary your test scenarios. Make sure to consider at least two test scenarios:
    // (1) when Inbox.unreadMessageBox is empty
    // (2) when Inbox.unreadMessageBox is not empty. You have to make sure the read message
    // has been popped off the Inbox.unreadMessageBox and pushed into the Inbox.readMessageBox
    // You can rely on Inbox.peekReadMessage() and Inbox.getStatistics() to check the method
    // behavior was as expected.
    Message message = new Message("Test", "Tester");
    Inbox inbox = new Inbox();
    String actual = inbox.readMessage();
    if(!actual.equals("Nothing in Unread."))
      return false;
    //System.out.println("1");
    inbox.receiveMessage(message);
    inbox.readMessage();
    actual = inbox.peekReadMessage();
    //System.out.println(actual);
    //System.out.println("[1] Test: Tester");
    if(!actual.equals("[11] Test: Tester"))
      return false;
    //System.out.println("2");
    //System.out.println(inbox.getStatistics());
    //System.out.println("Unread (0)\nRead (1)");
    if(!inbox.getStatistics().equals("Unread (0)\nRead (1)"))
      return false;
    //System.out.println("3");
    return true;
  }


  /**
   * Checks for the correctness of the Inbox.ReceiveMessage() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxReceiveMessage() {
    // Define your own test scenarios to check the correctness of Inbox.receiveMessage()
    // Your test method must return false if it detects at least one defect
    Message message = new Message("Test", "Tester");
    Inbox inbox = new Inbox();
    inbox.receiveMessage(message);
    // System.out.println(inbox.getStatistics());
    if(!inbox.getStatistics().equals("Unread (1)\nRead (0)"))
      return false;
    return true;
  }

  /**
   * Checks for the correctness of the Inbox.markAllMessagesAsRead() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxMarkAllMessagesAsRead() {
    // Define your own test scenarios to check the correctness of Inbox.markAllMessagesAsRead()
    // Your test method must return false if it detects at least one defect
    Inbox inbox = new Inbox();
    Message message = new Message("Test", "Tester");
    int count = inbox.markAllMessagesAsRead();
    if(count != 0)
      return false;
    inbox.receiveMessage(message);
    count = inbox.markAllMessagesAsRead();
    if(count != 1)
      return false;
    return true;
  }

  /**
   * Checks for the correctness of MessageStackIterator.hasNext() and MessageStackIterator.next()
   * methods. You can rely on the constructor of the LinkedNode class which takes two input
   * parameters (setting both data and next instance fields) to create a chain of linked nodes (at
   * least 3 linked nodes) which carry messages as data fields. Then, create a new
   * MessageStackIterator() and pass it the head of the chain of linked nodes that you created. We
   * recommend that you consider at least the following test scenarios in this tester method. (1)
   * Try to call next() on the iterator. The first call of next() must return the message at the
   * head of your chain of linked nodes. (2) Try to call hasNext() on your iterator, it must return
   * true. (3) The second call of next() must return the message which has been initially at index 1
   * of your chain of linked nodes. (4) The third call of next() on your iterator must return the
   * message initially at index 2 of your chain of linked nodes. (4) If you defined a chain of 3
   * linked nodes in this scenario, hasNext() should return false, and the fourth call of next() on
   * the iterator must throw a NoSuchElementException.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testMessageStackIterator() {
    try {
    Message input = new Message("Test", "Tester");
    Message input2 = new Message("Test2", "Tester2");
    Message input3 = new Message("Test3", "Tester3");
    LinkedNode<Message> three = new LinkedNode<Message>(input3);
    LinkedNode<Message> two = new LinkedNode<Message>(input2, three);
    LinkedNode<Message> one = new LinkedNode<Message>(input, two);
    MessageStackIterator iterator = new MessageStackIterator(one);
    Message actual = iterator.next();
    if(!actual.getTEXT().equals("Tester"))
      return false;
    if(!iterator.hasNext())
      return false;
    actual = iterator.next();
    if(!actual.getTEXT().equals("Tester2"))
      return false;
    actual = iterator.next();
    if(!actual.getTEXT().equals("Tester3"))
      return false;
    if(iterator.hasNext())
      return false;
    iterator.next();
    return false;
    } catch (NoSuchElementException e) {
      return true;
    }
  }

  public static boolean runInboxTestSuite() {
    if(!testStackConstructorIsEmptyPushPeek())
      return false;
    if(!testStackPop())
      return false;
    if(!testInboxReadMessage())
      return false;
    if(!testInboxReceiveMessage())
      return false;
    if(!testInboxMarkAllMessagesAsRead())
      return false;
    if(!testMessageStackIterator())
      return false;
    return true;
  }

}