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
import java.util.NoSuchElementException;

public class MoveQueue implements PriorityQueueADT<BattleCharacter>{
  private BattleCharacter[] data; // max heap array of BattleCharacters. root is at index 0
  private int size; // int which keeps track of the number of BattleCharacters stored in this
  // MoveQueue
  /**
   * Constructor that builds an empty priority queue with array size or the given capacity.
   * Throws IllegalArgumentException if capacity is zero or negative;
   * @param capacity size of the to be newly created MoveQueue
   */
  public MoveQueue(int capacity) {
    if(capacity <= 0)
      throw new IllegalArgumentException("Size cannot be less than or equal to zero.");
    this.size = 0;
    this.data = new BattleCharacter[capacity];
  }
  /**
   * Constructor that builds  an empty priority queue with array size 10
   */
  public MoveQueue() {
    this.size = 0;
    this.data = new BattleCharacter[10];
  }
  /**
   * Returns a String representation of the current contents of the MoveQueue in order from first 
   * to last.
   */
  @Override
  public String toString() {
    String s = ("[ ");
    for(int i = 0; i < this.size; i++) {
      s += (data[i].toString() + " | ");
    }
    s += ("]");
    return s;
  }
  /**
   * Checks if this priority queue is empty. Returns true if it is empty and false otherwise.
   */
  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }
  /**
   * Returns the current size of this priority queue.
   * 
   * @return the size of this priority queue
   */
  @Override
  public int size() {
    return this.size;
  }
  /**
   * Adds the given element to the priority queue in the correct position based on the natural
   * ordering of the elements.
   * 
   * @param element to be added to this queue
   * @throws IllegalArgumentException if element is null
   * @throws IllegalStateException of this priority queue is full
   */
  @Override
  public void enqueue(BattleCharacter element) {
    // element is null
    if(element == null)
      throw new IllegalArgumentException("BattleCharacter cannot be null.");
    // no more room
    if(this.size >= data.length)
      throw new IllegalStateException("Array is full.");
    // size is zero so add to top/make it the root
    if(this.size == 0) { 
      data[0] = element;
      this.size++;
    }
    // add to end of array/heap
    else {data[this.size] = element;
    percolateUp(this.size);
    this.size++;
    }
  }

  /**
   * helper method Returns the index of the parent of the node at position j of the heap
   * 
   * @param j index of a node
   * @return index of the parent of node at position j
   */
  private static int getParentIndex(int j) {
    return (j - 1) / 2;
  }
  /**
   * helper method Returns the index of the left child of the node of index j in the heap
   * 
   * @param j index of a node
   * @return index of the left child of j
   */
  private static int getLeftChildIndex(int j, int size) {
    if(((2 * j) + 1) < size)
      return 2 * j + 1;
    else 
      return -1;
  }

  /**
   * helper method Returns the index of the right child of the node of index j in the heap
   * 
   * @param j index of a node
   * @return index of the right child of j
   */
  private static int getRightChildIndex(int j, int size) {
    if(((2 * j) + 2) < size)
      return 2 * j + 2;
    else 
      return -1;
  }
  /**
   * Returns and removes the element at the front (aka root position) of this queue (the element
   * having the highest priority).
   * 
   * @return the removed element
   * @throws NoSuchElementException if this queue is empty
   */
  @Override
  public BattleCharacter dequeue() {
    // empty
    if(this.size <= 0)
      throw new NoSuchElementException("Heap is empty.");
    // only one element
    if(this.size == 1) {
      this.size--;
      BattleCharacter temp = data[0];
      data[0] = null;
      return temp;
    }
    BattleCharacter temp = data[0];
    data[0] = data[this.size - 1];
    data[this.size-1] = null;
    this.size--;
    percolateDown(0);
    return temp;
  }
  /**
   * Returns without removing the element at the front (aka root position) of this queue (the element
   * having the highest priority).
   * 
   * @return the element with the highest priority in this queue
   * @throws NoSuchElementException if this queue is empty
   */
  @Override
  public BattleCharacter peekBest() {
    if(this.size == 0)
      throw new NoSuchElementException("Queue is empty.");
    return data[0];
  }
  /**
   * Removes all the elements from this priority queue. The queue must be empty after this method
   * returns.
   */
  @Override
  public void clear() {
    for(int i = 0; i < this.size; i++) 
      data[i] = null;
    this.size = 0;
  }
  /**
   * Recursively propagates max-heap order violations up.
   * Checks to see if the current node i violates the max-heap order property by checking
   * its parent. If it does, swap them and continue to ensure that the heap condition is 
   * satisfied.
   * @param i index of the current node in this heap
   */
  protected void percolateUp(int i) {
    int parentIndex = getParentIndex(i);
    BattleCharacter temp;
    // not in violation/ base case (parent > child)
    // in violation/ recursive case (swap and continue if current > parent)
    if(data[i].compareTo(data[parentIndex]) > 0) {
      temp = data[parentIndex];
      data[parentIndex] = data[i];
      data[i] = temp;
      if(parentIndex != 0)
        percolateUp(parentIndex);
    } 
  }
  /**
   * Recursively propagates max-heap order violations down.
   * Checks to see if the current node i violates the max-heap order property by checking
   * its children. If it does, swap it with the optimal child and continue to ensure the heap 
   * condition is met.
   * @param i index of the current node in this heap
   */
  protected void percolateDown(int i) {
    //System.out.println(this.size);
    int leftIndex = getLeftChildIndex(i, this.size);
    int rightIndex = getRightChildIndex(i, this.size);
    //System.out.println("left " + leftIndex);
    //System.out.println("right " + rightIndex);
    // if one or the other child exists
    if(leftIndex != -1 || rightIndex != -1) {
      BattleCharacter temp;
      int optimalChildIndex = leftIndex;
      int result;
      // if both exist
      if(leftIndex != -1 && rightIndex != -1) {
        result = data[rightIndex].compareTo(data[leftIndex]);
        // left child is greater
        if(result < 0)
          optimalChildIndex = leftIndex;
        // right child is greater
        if(result > 0) 
          optimalChildIndex = rightIndex; 
      }
      // swap with whichever was greater if it is greater than parent
      if(data[i].compareTo(data[optimalChildIndex]) < 0) {
        temp = data[optimalChildIndex];
        data[optimalChildIndex] = data[i];
        data[i] = temp;
        percolateDown(optimalChildIndex);
      }
    }
  }
  
  
  /**
   * Eliminates all heap order violations from the heap data array
   */
  protected void heapify() {
    for(int i = this.size - 1; i >= 0; i--)
      percolateUp(i);
  }
  /**
   * TODO:
   * (1) Find matching character in the MoveQueue
   * (Rely on the BattleCharacter.equals() method to find the match with updated).
   * (2) Replace it with the updated version of the character.
   * If character is dead, remove it entirely.
   * (3) Make sure the structure is maintained.
   * Note: You can also use the code below to eliminate holes (null references)
   * in the array. Then, call heapify() method to eliminate all the ordering violations. 
   * @param updateChara BattleCharacter to be updated
   */
  public void updateCharacter(BattleCharacter updateChara){
      int gapIndex = 0; // change this to the index of the character that died
      // find the character
      for(int i = 0; i < this.size - 1; i++)
        if(data[i].equals(updateChara))
          gapIndex = i;
      // update status
      if(updateChara.getHP() == 0) {
        data[gapIndex] = null;
        // remove gaps
        for (int i = gapIndex; i < size-1; i++) {
          data[i] = data[i + 1];
        }
        data[size-1] = null;
        size--;
      }
      else 
        data[gapIndex] = updateChara;
      // heapify to correctly adjust structure
      heapify();
    }
}
