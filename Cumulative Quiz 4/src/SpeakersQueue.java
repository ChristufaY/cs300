///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: Christopher Yang
// Campus ID: 9082293151
// WiscEmail: cyang397@wisc.edu
////////////////////////////////////////////////////////////////////////////////

/**
 * This class models a circular queue which stores the names of speakers who 
 * take turns to contribute in a conversation.
 *
 * TODOs:
 *   1. Complete the constructor to check that the argument value is valid.
 *   2. Write the isEmpty queue operation.
 *   3. Write the peek queue operation.
 *   4. Write the enqueue queue operation.
 *   5. Write the dequeue queue operation.
 *   6. Complete the toString implementation to traverse the queue from front to back.
 *
 */
public class SpeakersQueue {

  private String[] names; // circular-indexing array which stores names of speakers
  private int headIndex;  // index of the name of the speaker at the front of this circular queue
  private int tailIndex;  // index of the next available position in this queue (next  
  // available position with respect to the back of this queue)
  private int size;       // number of speakers stored in this queue

  // Note that when the queue is not empty, speakers are stored in the range of indexes 
  // [headIndex .. tailIndex] excluding tailIndex in the circular-indexing array names.

  /**
   * Creates an empty array-based queue of speaker names.
   * 
   * BE CAREFUL: in this queue implementation, the tail index indicates the next available position
   * rather than the index of the element stored at the back of the queue.
   * 
   * @param capacity the maximum number of speakers that can be in the queue at once
   */
  public SpeakersQueue(int capacity) {
    // TODO: if the provided queue capacity is 0 or negative, throw an IllegalArgumentException
    if(capacity <= 0)
      throw new IllegalArgumentException("Capacity cannot be zero or negative.");
    // initialize the private data fields for this Speakers object to represent an empty queue
    this.names = new String[capacity];
    this.headIndex = -1;
    this.tailIndex = 0;
    this.size = 0;
  }

  /**
   * Determines whether the queue is currently empty
   * @return true if there are no names in the queue, false otherwise
   */
  public boolean isEmpty() {
    return (this.size == 0); // TODO CHANGE THIS: included for compiler
  }

  /**
   * Returns the next speaker in the queue without removing the name
   * @return null if nothing in the queue, otherwise the speaker name at the front of the queue
   */
  public String peek() {
    // TODO: return the name at the front of the queue
    if(this.size == 0)
      return null;
    return names[headIndex]; // CHANGE THIS: included for compiler
  }

  /**
   * Adds a new speaker to the back of the queue
   * @param newName the name to add
   */
  public void enqueue(String newName) {
    // TODO: if there is no more space to add a name, throw an IllegalArgumentException
    if(this.size == names.length)
      throw new IllegalArgumentException("Queue is full.");
    // add the new name to the back of the queue and update your indexes and size
    // remember to wrap the indexes around the end of the array if necessary!
    if(this.headIndex == -1)
      this.headIndex = 0;
    names[this.tailIndex] = newName;
    this.size++;
    this.tailIndex = (this.tailIndex + 1) % names.length;
  }

  /**
   * Removes and returns the next speaker in the queue
   * @return null if nothing in the queue, otherwise the speaker name at the front of the queue
   */
  public String dequeue() {
    // TODO: remove and return the name at the front of the queue, and update your indexes and size
    // remember to wrap the indexes around the end of the array if necessary!
    if(this.size == 0)
      return null;
    String temp = names[this.headIndex];
    this.headIndex = (this.headIndex + 1) % names.length;
    this.size--;
    return temp; // CHANGE THIS: included for compiler
  }

  /**
   * Creates and returns a string representation of the queue from front to back
   * 
   * Examples:
   *  Queue: [ ]              // an empty queue
   *  Queue: [ Deb ]          // a queue with one name
   *  Queue: [ Deb Jim Beck ] // the same queue after two more names were added
   */
  @Override
  public String toString() {
    String repr = "Queue: [ ";
    if(this.size == 0)
      return repr += "]";
    // TODO: add the names in the queue, ordered from head to tail, and separated by spaces
    for(int i = 0; i < this.size; i++) 
      if(names[(this.headIndex + i) % names.length] != null)
        repr += names[(this.headIndex + i) % names.length] + " ";
    repr += "]";  
    return repr;
  }

  /**
   * A basic method demonstrating some functionality of the queue.
   * 
   * Expected output for each method call is given in the inline comments.
   */
  public static void demo() {
    // basic functionality:
    SpeakersQueue test = new SpeakersQueue(5);
    System.out.println(test);                         // should be: Queue: [ ]
    System.out.println("Is Empty? "+test.isEmpty());  // should be: true

    // test the enqueue method:
    test.enqueue("Deb");
    System.out.println("Is Empty? "+test.isEmpty());  // should be: false
    test.enqueue("Jim");
    test.enqueue("Beck");
    System.out.println(test);                         // should be: Queue: [ Deb Jim Beck ]

    // test the dequeue method:
    System.out.println("Next: "+test.dequeue());      // should be: Deb
    test.enqueue("Hobbes");
    test.enqueue("Mouna");
    System.out.println(test);                         // should be: Queue: [ Jim Beck Hobbes Mouna ]
    System.out.println("Next: "+test.dequeue());      // should be: Jim
    System.out.println("Next: "+test.dequeue());      // should be: Beck

    // the queue only has two names, this should not throw an exception!
    test.enqueue("Gary");
    System.out.println(test);                         // should be: Queue: [ Hobbes Mouna Gary ]

    // empty out the queue:
    System.out.println("Next: "+test.dequeue());      // should be: Hobbes
    System.out.println("Next: "+test.dequeue());      // should be: Mouna
    System.out.println("Next: "+test.dequeue());      // should be: Gary
    System.out.println("Is Empty? "+test.isEmpty());  // should be: true
  }

  public static void main(String[] args) {
    demo();
  }

}