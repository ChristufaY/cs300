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
/**
 * This class models a text message
 * 
 * @author Mouna
 *
 */
public class Message {
  private static int idGenerator = 1; // generator of unique identifiers
  private final int ID; // identifier of this message
  private final String SUBJECT; // subject of this message
  private final String TEXT; // text of this message

  /**
   * Creates a new message with a specific topic and text
   * 
   * @param topic topic for this message
   * @param text  text for this message
   */
  public Message(String topic, String text) {
    this.ID = idGenerator++;
    this.SUBJECT = topic;
    this.TEXT = text;
  }

  /**
   * Gets the id of this message
   * 
   * @return the identifier of this message
   */
  public int getID() {
    return ID;
  }

  /**
   * Gets the subject of this message
   * 
   * @return the subject of this message
   */
  public String getSUBJECT() {
    return SUBJECT;
  }

  /**
   * Gets the text description of this message
   * 
   * @return the text description of this message
   */
  public String getTEXT() {
    return TEXT;
  }

  /**
   * Returns a string representation of this message in the following format [ID] SUBJECT: TEXT
   * 
   * @return a String representation of this message
   */
  @Override
  public String toString() {
    return "[" + getID() + "] " + getSUBJECT() + ": " + getTEXT();
  }

  /**
   * Checks whether this message equals an object provided as input. This message and the object are
   * equal if o is an instance of Message and has the same ID, subject, and text as this message.
   * 
   * @param o the reference object with which to compare.
   * @return true if this message equals the provided object, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    return o instanceof Message && this.ID == ((Message)o).ID &&
           this.SUBJECT.equals(((Message) o).SUBJECT) && this.TEXT.equals(((Message) o).TEXT);
  }
  
  /**
   * Resets the id generator to 1. This method can be called at the top of each of 
   * the first created message in every test method will have 1 as ID.
   */
  public static void resetIdGenerator() {
    idGenerator = 1;
  }

}