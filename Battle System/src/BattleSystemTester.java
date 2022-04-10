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
 * This class implements unit test methods to check the correctness of the implementation of the
 * BattleSystem and MoveQueue classes defined in the CS300 Fall 2020 - P10 Battle System
 * programming assignment.
 *
 */
import java.util.NoSuchElementException;
public class BattleSystemTester {

  public static void main(String[] args) {
    System.out.println("testEnqueueMoveQueue(): " + testEnqueueMoveQueue());
    System.out.println("testDequeueMoveQueue(): " + testDequeueMoveQueue());
    System.out.println("testClearMoveQueue(): " + testClearMoveQueue());
    System.out.println("testIsEmpty(): " + testIsEmpty());
  }
  // checks the correctness of the enqueue operation implemented in the MoveQueue class
  public static boolean testEnqueueMoveQueue() {
    // empty array
    MoveQueue mq = new MoveQueue(4);
    BattleCharacter pikachu = new BattleCharacter("Pikachu", new int[]{0,0,0,0,100});
    mq.enqueue(pikachu);
    if(mq.size() != 1)
      return false;
    // only root which is less than the one being added, which requires a swap
    BattleCharacter charmander = new BattleCharacter("Charmander", new int[] {0,0,0,0,150});
    mq.enqueue(charmander);
    if(!(mq.toString().equals("[ Charmander(2, 150) | Pikachu(1, 100) | ]")))
      return false;
    // root and parent which are both less than the one being added, which requires two swaps
    BattleCharacter squirtle = new BattleCharacter("Squirtle", new int[] {0,0,0,0,70});
    BattleCharacter bulbasaur = new BattleCharacter("Bulbasaur", new int[] {0,0,0,0,170});
    mq.enqueue(squirtle);
    mq.enqueue(bulbasaur);
    if(!(mq.toString().equals("[ Bulbasaur(4, 170) | Charmander(2, 150) | Squirtle(3, 70) | "
        + "Pikachu(1, 100) | ]")))
      return false;
    // full array
    try {
      BattleCharacter lapras = new BattleCharacter("Lapras", new int[] {0,0,0,0,10});
      mq.enqueue(lapras);
      return false;
    } catch (IllegalStateException e) {
      ;
    }
    // null element
    try {
      mq.enqueue(null);
      return false;
    } catch (IllegalArgumentException e) {
      ;
    }
    return true;
  }
  // checks the correctness of the dequeue operation implemented in MoveQueue class
  public static boolean testDequeueMoveQueue() {
    // empty
    MoveQueue mq = new MoveQueue(7);
    try {
      mq.dequeue();
      return false;
    } catch (NoSuchElementException e) {
      ;
    }
    //System.out.println("empty");
    // one value in array
    BattleCharacter apple = new BattleCharacter("Apple", new int[] {0,0,0,0,10});
    mq.enqueue(apple);
    mq.dequeue();
    //System.out.println(mq.size());
    if(mq.size() != 0)
      return false;
    //System.out.println("one");
    // two values in array
    BattleCharacter kiwi = new BattleCharacter("Kiwi", new int[] {0,0,0,0,20});
    mq.enqueue(kiwi);
    BattleCharacter papaya = new BattleCharacter("Papaya", new int[] {0,0,0,0,30});
    mq.enqueue(papaya);
    mq.dequeue();
    //System.out.println(mq.toString());
    if(!(mq.toString().equals("[ Kiwi(7, 20) | ]")))
      return false;
    //System.out.println("two");
    // multiple 
    BattleCharacter banana = new BattleCharacter("Banana", new int[] {0,0,0,0,40});
    BattleCharacter watermelon = new BattleCharacter("Watermelon", new int[] {0,0,0,0,50});
    BattleCharacter peach = new BattleCharacter("Peach", new int[] {0,0,0,0,60});
    mq.enqueue(banana);
    mq.enqueue(watermelon);
    mq.enqueue(peach);
    //System.out.println(mq.toString());
    mq.dequeue();
    //System.out.println(mq.toString());
    mq.dequeue();
    //System.out.println(mq.toString());
    mq.dequeue();
    //System.out.println(mq.toString());
    if(!(mq.toString().equals("[ Kiwi(7, 20) | ]")))
      return false;
    return true;
  }
  // checks the correctness of the clear operation implemented in the MoveQueue class
  public static boolean testClearMoveQueue() {
    // empty
    MoveQueue mq = new MoveQueue(3);
    mq.clear();
    if((mq.size() != 0))
      return false;
    // one value
    BattleCharacter orange = new BattleCharacter("Orange", new int[] {0,0,0,0,10});
    mq.enqueue(orange);
    mq.clear();
    if((mq.size() != 0))
      return false;
    // two or more values
    BattleCharacter strawberry = new BattleCharacter("Strawberry", new int[] {0,0,0,0,20});
    BattleCharacter melon = new BattleCharacter("Melon", new int[] {0,0,0,0,30});
    mq.enqueue(strawberry);
    mq.enqueue(melon);
    mq.clear();
    if((mq.size() != 0))
      return false;
    return true;
  }
  // checks the correctness of the isEmpty operation in the moveQueue class
  public static boolean testIsEmpty() {
 // empty
    MoveQueue mq = new MoveQueue(3);
    mq.clear();
    if(!(mq.isEmpty()))
      return false;
    // one value
    BattleCharacter pineapple = new BattleCharacter("Pineapple", new int[] {0,0,0,0,10});
    mq.enqueue(pineapple);
    mq.clear();
    if(!(mq.isEmpty()))
      return false;
    // two or more values
    BattleCharacter mango = new BattleCharacter("Mango", new int[] {0,0,0,0,20});
    BattleCharacter pear = new BattleCharacter("Pear", new int[] {0,0,0,0,30});
    mq.enqueue(mango);
    mq.enqueue(pear);
    mq.clear();
    if(!(mq.isEmpty()))
      return false;
    return true;
  }
}
