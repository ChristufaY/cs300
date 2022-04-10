//////////////// FILE HEADER (INCLUDE IN EVERY FILE) ///////////////
///////////
//
// Title: P10 Battle System
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
 * This interface models the priority queue abstract data type
 *
 * @param <T> type parameter bounded by the Comparable interface. It represents the type of elements
 *            stored in this priority queue
 */
public interface PriorityQueueADT<T extends Comparable<T>> {

  /**
   * Checks if this priority queue is empty. Returns true if it is empty and false otherwise.
   */
  public boolean isEmpty();


  /**
   * Returns the current size of this priority queue.
   * 
   * @return the size of this priority queue
   */
  public int size();


  /**
   * Adds the given element to the priority queue in the correct position based on the natural
   * ordering of the elements.
   * 
   * @param element to be added to this queue
   * @throws IllegalArgumentException if element is null
   * @throws IllegalStateException    of this priority queue is full
   */
  public void enqueue(T element);

  /**
   * Returns and removes the element at the front (aka root position) of this queue (the element
   * having the highest priority).
   * 
   * @return the removed element
   * @throws NoSuchElementException if this queue is empty
   */
  public T dequeue();
  
  /**
   * Returns without removing the element at the front (aka root position) of this queue (the element
   * having the highest priority).
   * 
   * @return the element with the highest priority in this queue
   * @throws NoSuchElementException if this queue is empty
   */
  public T peekBest();


  /**
   * Removes all the elements from this priority queue. The queue must be empty after this method
   * returns.
   */
  public void clear();


}