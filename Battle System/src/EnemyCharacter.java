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
 * Enemy Character that the computer controls. Based on their temperament type their probability of
 * certain actions changes.
 * 
 * @author Michelle
 *
 */
public class EnemyCharacter extends BattleCharacter {

  private static final String[] TEMPERAMENT_TYPES = {"PASSIVE", "AGGRESSIVE", "STRATEGIC"};
  private String temperament; // temperament of this EnemyCharacter
  private int[][] pr; // command probability distribution of this EnemyCharacter

  /**
   * Creates a new EnemyCharacter
   * 
   * @param name        name to be assigned to this EnemyCharacter
   * @param stats       stats control for this EnemyCharacter
   * @param temperament temperament for this EnemyCharacter
   * @throws IllegalArgumentException if the provided temperament is not valid
   */
  public EnemyCharacter(String name, int[] stats, String temperament) {
    super(name, stats);
    for (String s : TEMPERAMENT_TYPES) {
      if (s.equals(temperament)) {
        this.temperament = temperament;
        this.pr = generateProbDistribution();
        return;
      }
    }

    throw new IllegalArgumentException("Not a supported temperament.");
  }


  /**
   * Randomly pick a command/spell based on a probability distribution
   * 
   * @return a random pick of a move command
   */
  public String pickMove() {
    int choice = RAND.nextInt(100);

    if (choice >= pr[0][0] && choice <= pr[0][1])
      return "ATTACK";
    else if (choice >= pr[1][0] && choice <= pr[1][1])
      return "STARSTORM";
    else if (choice >= pr[2][0] && choice <= pr[2][1])
      return "HASTE";
    else if (choice >= pr[3][0] && choice <= pr[3][1])
      return "SLOW";
    else if (choice >= pr[4][0] && choice <= pr[4][1])
      return "WAIT";

    return "WAIT";
  }

  /**
   * Ramdomly picks a target
   * 
   * @param numOfTargets number of targets for this EnemyCharacter
   * @return the target
   */
  public int pickTarget(int numOfTargets) {
    return RAND.nextInt(numOfTargets);
  }

  /**
   * Assigns the correct command probability distribution based on this enemy character's
   * temperament
   * 
   * @return command probability distribution for this enemy character
   */
  private int[][] generateProbDistribution() {
    switch (temperament) {
      case "PASSIVE": // 10% atk, 10% magic attack, 15% haste, 15% slow, 50% wait
        int temp[][] = {{0, 9}, {10, 19}, {20, 34}, {35, 49}, {50, 99}};
        return temp;

      case "AGGRESSIVE": // 40% atk, 40% magic attack, 10% haste, 10% slow, 0% wait
        int temp2[][] = {{0, 49}, {50, 79}, {80, 89}, {90, 99}, {-1, -1}};
        return temp2;

      case "STRATEGIC": // 20% atk, 30% magic attack, 25% haste, 25% slow, 0% wait
        int temp3[][] = {{0, 19}, {20, 49}, {50, 74}, {75, 99}, {-1, -1}};
        return temp3;

      default:
        return null;
    }
  }
}