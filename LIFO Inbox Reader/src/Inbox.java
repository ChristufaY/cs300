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
public class Inbox {
  private MessageStack readMessageBox;
  private MessageStack unreadMessageBox;
  
  /**
   * no argument constructo that creates a new empty inbox
   * and initializes its instance fields
   * Both read and unread message boxes must be initially empty
   */
  public Inbox() {
    this.readMessageBox = new MessageStack();
    this.unreadMessageBox = new MessageStack();
  }
  /**
   * Reads the message at the top of the unreadMessageBox.
   * Once read, the message must be moved from the unreadMessageBox to the readMessageBox
   * @return message at the top of the unreadMessageBox or "Nothing in Unread" 
   * if the unreadMessageBox is empty
   */
  public String readMessage() {
    if(unreadMessageBox.isEmpty())
      return "Nothing in Unread.";
    LinkedNode<Message> message = new LinkedNode<Message>(unreadMessageBox.pop());
    readMessageBox.push(message.getData());
    return message.getData().toString();
  }
  /**
   * Returns the String representation of the message at the top of the readMessageBox
   * 
   * @return the string representation of the message at the top
   * readMessageBox and "Nothing in Read" if the readMessageBox is empty
   */
  public String peekReadMessage() {
    if(readMessageBox.isEmpty())
      return "Nothing in read.";
    LinkedNode<Message> message = new LinkedNode<Message>(readMessageBox.peek());
    return message.getData().toString();
  }
  /**
   * Marks all messages in the unread message box as read.
   * the unread message box must be empty after this method returns
   * every message marked read must be moved to the read messages box
   * @return the total number of messages marked as read.
   */
  public int markAllMessagesAsRead() {
    int counter = 0;
    while(!unreadMessageBox.isEmpty()) {
      readMessageBox.push(unreadMessageBox.pop());
      counter++;
    }
    return counter;
      
  }
  /**
   * Pushes a newMessage into the unread messageBox
   * note that this method can be invoked each time a new message will be received and pushed to the
   * unreadmessagebox
   * @param newMessage represents a message to the received message
   */
  public void receiveMessage(Message newMessage) {
    //System.out.println(newMessage);
    unreadMessageBox.push(newMessage);
  }
  /**
   * Removes all messages from readMessageBox permanently
   * @return total number of the removed messages
   */
  public int emptyReadMessageBox() {
    int counter = 0;
    while(!readMessageBox.isEmpty()) {
      readMessageBox.pop();
      counter++;
    }
    return counter;
  }
  /**
   * gets the stats of this inbox
   * 
   * @return a formatted String as follows:
   * "Unread (size1)" + "\n" + "Read (size2)",
   * where size1 and size2 represent the number of unread and read messages respectively 
   * 
   */
  public String getStatistics() {
    return "Unread (" + unreadMessageBox.size() + ")\nRead (" + readMessageBox.size() + ")";
  }
  /**
   * Traverses all the unread messages and returns a list of their ID + " " + SUBJECT, as a string
   * Every string representation of a message is provided in a new line
   * The output has the following format:
   * Unread(unreadMessageox_size)\n + list of the messages in unreadMessageBox(ID + " " + SUBJECT
   * each in a line
   * 
   * @return string representation of the contents of the unread message box
   */
  public String traverseUnreadMessages() {
    String temp = "Unread(" + unreadMessageBox.size() + ")\n";
    for(Message m : unreadMessageBox)
      temp += m.getID() + " " + m.getSUBJECT() + "\n";
    return temp;
  }
  /**
   * Traverses all the read messages and returns a list of their string representations,
   * ID + " " + SUBJECT, each per new line, as a string
   * The returned output has the following format:
   * Read(readMessageBox_size)\n + list of the messages in readMessageBox
   * (ID + " " + SUBJECT) each in a line
   * 
   * @return a string representation of the contents of the read message box
   */
  public String traverseReadMessages() {
    String temp = "Read(" + readMessageBox.size() + ")\n";
    for(Message m : readMessageBox)
      temp += m.getID() + " " + m.getSUBJECT() + "\n";
    return temp;
  }
}
