
/**
 * This class implements a LinkedNode data type for a linked linear data structure
 * 
 * @param <T> type of data of this linked node
 * @author Mouna
 */
public class LinkedNode<T> {
  private final T data; // item data field of any type T
  private LinkedNode<T> next; // reference to the next node of this linkedNode


  /**
   * Creates an instance of LinkedNode with a given item. The created LinkedNode has null as next
   * field
   * 
   * @param data data of this linkedNode
   */
  public LinkedNode(T data) {
    this.data = data;
  }

  /**
   * Creates an instance of LinkedNode with given item and reference to next node
   * 
   * @param data value
   * @param next reference to the next node of the same type
   */
  public LinkedNode(T data, LinkedNode<T> next) {
    this.data = data;
    this.next = next;
  }


  /**
   * Accessor for data field
   * 
   * @return the data within this linked node
   */
  public T getData() {
    return data;
  }


  /**
   * Accessor for next field
   * 
   * @return the next
   */
  public LinkedNode<T> getNext() {
    return next;
  }


  /**
   * Setter for next field
   * 
   * @param next the next to set
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }
}