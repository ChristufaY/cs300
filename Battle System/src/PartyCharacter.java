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
 * Battle Character that the player controls. Is able to run from battle.
 * 
 * @author Michelle
 *
 */
public class PartyCharacter extends BattleCharacter {

  /**
   * Creates a new PartCharacter
   * 
   * @param name  to be assigned to this Battle character
   * @param stats stats commands for this Battle character
   */
  public PartyCharacter(String name, int[] stats) {
    super(name, stats);
  }


  /**
   * Runs successfully this PartyCharacter with a probability of 50% if their speed is faster than
   * the opponent's speed
   * 
   * @param opponentSpeed opponent's speed
   * @return true if runs successfully and false otherwise.
   */
  public boolean run(int opponentSpeed) {
    boolean succeed = RAND.nextBoolean();

    // speed check, if faster than opponent 50% of successfully running
    if (succeed && this.getSpeed() >= opponentSpeed)
      return true;

    return false;
  }
}