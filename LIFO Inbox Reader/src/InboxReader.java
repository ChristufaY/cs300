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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class implements a LIFO Inbox Reader 
 * @author Mouna
 *
 */
/**
 * @author mouna
 *
 */
public class InboxReader {
  private final String WELCOME_MSG = "--- Welcome to our LIFO Inbox Reader App! ----";
  private final String GOOD_BYE_MSG = "------- BYE! Thanks for using our App! -------";
  private final String SYNTAX_ERROR_MSG = "Syntax Error: Please enter a valid command!";

  private final Inbox INBOX; // inbox to read
  private Scanner scanner; // scanner to read user input command lines

  /**
   * Creates and initializes a new InboxReader
   * 
   */
  public InboxReader() {
    scanner = new Scanner(System.in); // create a scanner object to read user inputs
    INBOX = new Inbox();
  }

  /**
   * Main method that launches this driver application
   * 
   * @param args
   */
  public static void main(String[] args) {
    // create a new Inbox Reader and start the application
    new InboxReader().runApplication();
  }


  /**
   * Runs the application
   */
  private void runApplication() {
    System.out.println(WELCOME_MSG); // display welcome message
    // read and process user command lines
    processUserCommands();
    scanner.close();// close the scanner
    System.out.println(GOOD_BYE_MSG);// display good bye message
  }

  /**
   * Prints out the menu of this application
   */
  private void displayMenu() {
    System.out.println("\n==================== MENU ====================");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <filename>] Load Inbox");
    System.out.println("[2] Read message");
    System.out.println("[3] Peek read message");
    System.out.println("[4] Print list of unread messages");
    System.out.println("[5] Print list of read messages");
    System.out.println("[6] Mark all messages as read");
    System.out.println("[7] Empty Read");
    System.out.println("[8] Print Statistics");
    System.out.println("[9] Logout and EXIT");
    System.out.println("----------------------------------------------");
  }
  
  /**
   * Loads unread messages from a given file. The file contains a message per line. The subject and
   * the text of a message are separated by a colon :
   * 
   * @param file  a java.io.File which contains a list of unread messages
   * @param inbox reference to the inbox where the messages in file will be uploaded
   */
  private void loadUnreadMessages(File file) {
    // start reading file contents
    Scanner fin = null;
    int lineNumber = 1; // report first line in file as lineNumber 1
    int nbMessages = 0; // number of messages already loaded.
    try {
      fin = new Scanner(file);
      while (fin.hasNextLine()) {
        // read the file line by line and create new messages
        String line = fin.nextLine().trim();
        if (line.length() < 1) {
          lineNumber++;
          continue;
        }
        // split the line with respect to colon :
        String[] messageParts = line.split(":",2);
        if (messageParts.length < 2) {
          // print error message - correct format (subject: text)
          lineNumber++;
          continue;
        }
        INBOX.receiveMessage(new Message(messageParts[0].trim(), messageParts[1].trim()));
        nbMessages++;
        lineNumber++;
      }
      System.out.println(nbMessages + " unread messages loaded to the inbox.");
      // catch and report warnings related to any problems experienced loading this file
    } catch (FileNotFoundException e) {
      System.out.println("WARNING: Unable to find or load file: " + file.getName());
    } catch (RuntimeException e) {
      System.out
          .println("WARNING: Problem loading file: " + file.getName() + " line: " + lineNumber);
      e.printStackTrace();
    } finally {
      if (fin != null)
        fin.close();
    }
  }


  /**
   * Reads and processes user command lines
   */
  private void processUserCommands() {
    String promptCommandLine = "ENTER COMMAND: ";
    displayMenu(); // display the library management system main menu
    System.out.print(promptCommandLine);
    String command = scanner.nextLine(); // read user command line
    String[] commands = command.trim().split(";"); // split user command
    // read and process user command lines until the user signs out
    while (!(commands[0].equals("9") && commands.length == 1)) { // 9 to logout: quit
      try {
        switch (commands[0]) {
          case "1": // [1 <filename>] Load Inbox's unread messages
            if (commands.length != 2)
              System.out.println(SYNTAX_ERROR_MSG);
            else {
              File f = new File(commands[1]);
              loadUnreadMessages(f);
            }
            break;
          case "2": // [2] Read message
            System.out.println(INBOX.readMessage());
            break;
          case "3": // [3] Peek read message
            System.out.println(INBOX.peekReadMessage());
            break;
          case "4": // [4] Print list of unread messages
            System.out.println(INBOX.traverseUnreadMessages());
            break;
          case "5": // [5] Print list of read messages
            System.out.println(INBOX.traverseReadMessages());
            break;
          case "6": // [6] Mark all messages as read
            System.out.println(INBOX.markAllMessagesAsRead() + " messages marked as read.");
            break;
          case "7": // [7] Empty Read
            System.out.println("Read Empty. " + INBOX.emptyReadMessageBox() + " read messages deleted.");
            break;
          case "8": // [8] Print Statistics
            System.out.println(INBOX.getStatistics());
            break;
          default:
            System.out.println(SYNTAX_ERROR_MSG); // Syntax Error

        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      // read and split next user command line
      displayMenu(); // display the library management system main menu
      System.out.print(promptCommandLine);
      command = scanner.nextLine(); // read user command line
      commands = command.trim().split(";"); // split user command line
    }
  }

  
}