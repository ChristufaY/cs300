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
import java.util.Random;
/**
 * Class to represent characters that are able to battle
 * 
 * @author Michelle & <YOUR NAME HERE>
 *
 */
public class BattleCharacter implements Comparable<BattleCharacter>{

  protected static final Random RAND = new Random(100); // generator of random numbers
  private static int idGenerator = 1; // generator of the id of the next BattleCharacter to be
  // created.
  private int[] stats; // size 5 array stats are as follows [Health, Attack, Defense, Magic,
  // Speed]
  private final int ID; // Unique identifier of this battle character
  private String name; // name of this BattleCharacter


  /**
   * Creates a new BattleCharacter
   * 
   * @param name  name for this BattleCharacter
   * @param stats stats for this BattleCharacter
   */
  public BattleCharacter(String name, int[] stats) {
    if (name == null || stats.length != 5)
      throw new IllegalArgumentException(
          "Name cannot be null or stats array is not of the proper size.");
    ID = idGenerator++;
    this.name = name;
    this.stats = stats;
  }

  /**
   * Returns the name of this BattleCharacter
   * @return the name of this BattleCharacter
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the ID of this BattleCharacter
   * @return the ID of this BattleCharacter
   */
  public int getID() {
    return this.ID;
  }


  /**
   * Gets the HP (Health Points) of this BattleCharacter
   * @return the HP of this BattleCharacter
   */
  public int getHP() {
    return stats[0];
  }

  /**
   * Returns the Attack value of this BattleCharacter
   * @return the Attack value of this BattleCharacter
   */
  public int getAttack() {
    return stats[1];
  }

  /**
   * Returns the Magic value of this BattleCharacter
   * @return the Magic value of this BattleCharacter
   */
  public int getMagic() {
    return stats[3];
  }

  /**
   * Returns the speed of this BattleCharacter
   * @return the speed of this BattleCharacter
   */
  public int getSpeed() {
    return stats[4];
  }

  /**
   * Decreases this character's speed by 25%, rounded down to nearest int
   */
  public void slow() {
    stats[4] = (int) Math.floor(stats[4] * .75);
  }


  /**
   * Increases this character's speed by 25%, rounded down to nearest int
   */
  public void haste() {
    stats[4] = (int) Math.floor(stats[4] * 1.25);
  }


  /**
   * Changes HP based on base damage and half of their defense
   * @param damageAmount
   */
  public void takeDamage(int damageAmount) {
    stats[0] -= (damageAmount - (int) Math.floor(stats[2] / 2));
  }


  /**
   * Returns whether or not the current character is still alive. If Health hits or drops below 0,
   * they are considered dead.
   * 
   * @return returns whether or not the current character is still alive
   */
  public boolean isAlive() {
    return stats[0] > 0;
  }

  /**
   * Resets the BattleCharacter.idGenerator to 1. This method can be used at the beginning of your
   * test methods.
   */
  public static void resetIDGenerator() {
    idGenerator = 1;
  }

  /**
   * Returns a String representation of this BattleCharacter in the following format: name(ID, speed)
   * @return a string representation of this BattleCharacter
   */
  @Override
  public String toString() {
    return this.name + "(" + this.ID + ", " + this.getSpeed() + ")";
  }
  /**
   * Checks to see if two BattleCharacters are equal.
   * Returns true if their IDs match, and false if they do not or if the
   * object is not an instance of a BattleCharacter
   * @return true if IDs match, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if(o instanceof BattleCharacter)
      return ((BattleCharacter) o).getID() == this.getID();
    
    return false;
  }
  /**
   * Compares BattleCharacters based on their speed stat.
   * In the event of a tie, the one with the lower ID number get priority 
   * (meaning it will be the greatest)
   * @return 1 or -1 depending on whichever BattleCharacter is faster
   */
  @Override
  public int compareTo(BattleCharacter compObj) {
    if(this.getID() == compObj.getID())
      return 0;
    if(this.getSpeed() == compObj.getSpeed()) {
      if(this.getID() < compObj.getID())
        return 1;
      else return -1;
    }
    else if(this.getSpeed() > compObj.getSpeed())
      return 1;
    else return -1;
  }
}