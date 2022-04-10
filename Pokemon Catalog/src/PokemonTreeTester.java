//////////////// FILE HEADER (INCLUDE IN EVERY FILE) ///////////////
///////////
//
// Title: P09 Pokemon Catalog
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
 * This class checks the correctness of the implementation of the methods
 * defined in the class PokemonTree.
 *
 */
import java.util.NoSuchElementException;
public class PokemonTreeTester {

  /**
   * Checks the correctness of the implementation of both addPokemon() and
   * toString() methods implemented in the PokemonTree class. This unit test
   * considers at least the following scenarios. (1) Create a new empty
   * PokemonTree, and check that its size is 0, it is empty, and that its string
   * representation is an empty string "". (2) try adding one Pokemon and then
   * check that the addPokemon() method call returns true, the tree is not empty,
   * its size is 1, and the .toString() called on the tree returns the expected
   * output. (3) Try adding another Pokemon which is more powerful than the one at
   * the root, (4) Try adding a third Pokemon which is less powerful than the one
   * at the root, (5) Try adding at least two further Pokemons such that one must
   * be added at the left subtree, and the other at the right subtree. For all the
   * above scenarios, and more, double check each time that size() method returns
   * the expected value, the add method call returns true, and that the
   * .toString() method returns the expected string representation of the contents
   * of the binary search tree in an ascendant order from the most powerful
   * Pokemon to the least powerful one. (6) Try adding a Pokemon whose CP value
   * was used as a key for a Pokemon already stored in the tree. Make sure that
   * the addPokemon() method call returned false, and that the size of the tree
   * did not change.
   * 
   * @return true when this test verifies a correct functionality, and false
   *         otherwise
   */
  public static boolean testAddPokemonToStringSize() {
    // empty tree
    PokemonTree pt = new PokemonTree();
    if(!(pt.size() == 0))
      return false;
    if(!(pt.toString().equals("")))
      return false;
    //System.out.println("empty:" + pt.toString());
    // one pokemon
    Pokemon charmander = new Pokemon("Charmander", "3,2,1");
    boolean worked = pt.addPokemon(charmander);
    if(!worked)
      return false;
    if(!(pt.size() == 1))
      return false;
    if(!(pt.toString().equals("[Charmander CP:321 (A:3 S:2 D:1)]")))
      return false;
    //System.out.println("one:" + pt.toString());
    // add one more pokemon with high cp than previous
    Pokemon snorlax = new Pokemon("Snorlax", "4,4,8");
    worked = pt.addPokemon(snorlax);
    if(!(pt.size() == 2))
      return false;
    //System.out.println("size check");
    if(!worked)
      return false;
    //System.out.println("worked check");
    //System.out.println(pt.toString());
    if(!(pt.toString().equals("[Charmander CP:321 (A:3 S:2 D:1)]" 
        + "\n" + "[Snorlax CP:448 (A:4 S:4 D:8)]")))
      return false;
    //System.out.println("print check");
    //System.out.println("two" + pt.toString());
    // add one more pokemon with less cp than first
    Pokemon eevee = new Pokemon("Eevee", "2,2,4");
    worked = pt.addPokemon(eevee);
    if(!(worked))
      return false;
    if(!(pt.size() == 3))
      return false;
    if(!(pt.toString().equals("[Eevee CP:224 (A:2 S:2 D:4)]\n"
        + "[Charmander CP:321 (A:3 S:2 D:1)]\n[Snorlax CP:448 (A:4 S:4 D:8)]")))
      return false;
    //System.out.println("three:" + pt.toString());
    // add two. one left one right and check size
    Pokemon pikachu = new Pokemon("Pikachu", "1,2,3");
    Pokemon rayquaza = new Pokemon("Rayquaza", "8,2,3");
    worked = pt.addPokemon(pikachu);
    if(!(pt.size() == 4))
      return false;
    if(!worked)
      return false;
    if(!(pt.toString().equals("[Pikachu CP:123 (A:1 S:2 D:3)]\n[Eevee CP:224 (A:2 S:2 D:4)]\n"
        + "[Charmander CP:321 (A:3 S:2 D:1)]\n[Snorlax CP:448 (A:4 S:4 D:8)]")))
      return false;
    //System.out.println("four:" + pt.toString());
    worked = pt.addPokemon(rayquaza);
    if(!(pt.size() == 5))
      return false;
    if(!worked)
      return false;
    if(!(pt.toString().equals("[Pikachu CP:123 (A:1 S:2 D:3)]\n[Eevee CP:224 (A:2 S:2 D:4)]\n"
        + "[Charmander CP:321 (A:3 S:2 D:1)]\n[Snorlax CP:448 (A:4 S:4 D:8)]\n"
        + "[Rayquaza CP:823 (A:8 S:2 D:3)]")))
      return false;
    //System.out.println("five:" + pt.toString());
    // adding duplicate cp value pokemon
    Pokemon duplicate = new Pokemon("Duplicate","3,2,1");
    worked = pt.addPokemon(duplicate);
    if(!(pt.size() == 5))
      return false;
    if(worked)
      return false;
    return true;
  }

  /**
   * This method checks mainly for the correctness of the PokemonTree.lookup()
   * method. It must consider at least the following test scenarios. (1) Create a
   * new PokemonTree. Then, check that calling the lookup() method with any valid
   * CP value must throw a NoSuchElementException. (2) Consider a PokemonTree of
   * height 3 which consists of at least 5 PokemonNodes. Then, try to call
   * lookup() method to search for the Pokemon at the root of the tree, then a
   * Pokemon at the right and left subtrees at different levels. Make sure that
   * the lookup() method returns the expected output for every method call. (3)
   * Consider calling .lookup() method on a non-empty PokemonTree with a CP value 
   * not stored in the tree, and ensure that the method call throws a
   * NoSuchElementException.
   * 
   * @return true when this test verifies a correct functionality, and false
   *         otherwise
   */
  public static boolean testAddPokemonAndLookup() {
    PokemonTree pt = new PokemonTree();
    //    try {
    //      pt.lookup(10);
    //      return false;
    //    } catch (NoSuchElementException e) {
    //      e.getMessage();
    //      e.printStackTrace();
    //    }
    Pokemon pikachu = new Pokemon("Pikachu", "1,2,3");
    Pokemon eevee = new Pokemon("Eevee", "2,2,4");
    Pokemon snorlax = new Pokemon("Snorlax", "4,4,8");
    Pokemon mewtwo = new Pokemon("Mewtwo", "9,9,9");
    Pokemon lapras = new Pokemon("Lapras", "7,3,5");
    pt.addPokemon(pikachu);
    pt.addPokemon(eevee);
    pt.addPokemon(snorlax);
    pt.addPokemon(mewtwo);
    pt.addPokemon(lapras);
    System.out.println(pt.toString());
    if(!pt.lookup(123).toString().equals(pikachu.toString()))
      return false;
    if(!pt.lookup(999).toString().equals(mewtwo.toString()))
      return false;
    if(!pt.lookup(735).toString().equals(lapras.toString()))
      return false;
    try {
      pt.lookup(1);
      return false;
    } catch (NoSuchElementException e) {
      ;
    }
    return true;
  }

  /**
   * Checks for the correctness of PokemonTree.height() method. This test must
   * consider several scenarios such as, (1) ensures that the height of an empty
   * Pokemon tree is zero. (2) ensures that the height of a tree which consists of
   * only one node is 1. (3) ensures that the height of a PokemonTree with the
   * following structure for instance, is 4. 
   *      (*) 
   *     /   \ 
   *   (*)   (*) 
   *     \   / \ 
   *    (*)(*) (*) 
   *           /
   *         (*)
   * 
   * @return true when this test verifies a correct functionality, and false
   *         otherwise
   */
  public static boolean testHeight() {
    PokemonTree pt = new PokemonTree();
    if(!(pt.height() == 0))
      return false;
    Pokemon charmander = new Pokemon("Charmander", "3,2,1");  
    pt.addPokemon(charmander);
    if(!(pt.height() == 1))
      return false;
    Pokemon pikachu = new Pokemon("Pikachu", "1,2,3");
    Pokemon eevee = new Pokemon("Eevee", "2,2,4");
    Pokemon snorlax = new Pokemon("Snorlax", "4,4,8");
    Pokemon mewtwo = new Pokemon("Mewtwo", "9,9,9");
    Pokemon lapras = new Pokemon("Lapras", "7,3,5");
    Pokemon rayquaza = new Pokemon("Rayquaza", "8,2,3");
    pt.addPokemon(pikachu);
    pt.addPokemon(eevee);
    pt.addPokemon(lapras);
    pt.addPokemon(mewtwo);
    pt.addPokemon(snorlax);
    pt.addPokemon(rayquaza);
    if(!(pt.height() == 4))
      return false;
    return true;
  }

  /**
   * Checks for the correctness of PokemonTree.getLeastPowerfulPokemon() method.
   * 
   * @return true when this test verifies a correct functionality, and false
   *         otherwise
   */
  public static boolean testGetLeastPowerfulPokemon() {
    // least powerful in tree
    PokemonTree pt = new PokemonTree();
    Pokemon charmander = new Pokemon("Charmander", "3,2,1");
    Pokemon pikachu = new Pokemon("Pikachu", "1,2,3");
    Pokemon eevee = new Pokemon("Eevee", "2,2,4");
    Pokemon snorlax = new Pokemon("Snorlax", "4,4,8");
    Pokemon mewtwo = new Pokemon("Mewtwo", "9,9,9");
    Pokemon lapras = new Pokemon("Lapras", "7,3,5");
    Pokemon rayquaza = new Pokemon("Rayquaza", "8,2,3");
    pt.addPokemon(charmander);
    pt.addPokemon(pikachu);
    pt.addPokemon(eevee);
    pt.addPokemon(lapras);
    pt.addPokemon(mewtwo);
    pt.addPokemon(snorlax);
    pt.addPokemon(rayquaza);
    Pokemon least = pt.getLeastPowerfulPokemon();
    if(!(least.equals(pikachu)))
      return false;
    return true;
  }

  /**
   * Checks for the correctness of PokemonTree.getMostPowerfulPokemon() method.
   * 
   * @return true when this test verifies a correct functionality, and false
   *         otherwise
   */
  public static boolean testGetMostPowerfulPokemon() {
    // most powerful in tree
    PokemonTree pt = new PokemonTree();
    Pokemon charmander = new Pokemon("Charmander", "3,2,1");
    Pokemon pikachu = new Pokemon("Pikachu", "1,2,3");
    Pokemon eevee = new Pokemon("Eevee", "2,2,4");
    Pokemon snorlax = new Pokemon("Snorlax", "4,4,8");
    Pokemon mewtwo = new Pokemon("Mewtwo", "9,9,9");
    Pokemon lapras = new Pokemon("Lapras", "7,3,5");
    Pokemon rayquaza = new Pokemon("Rayquaza", "8,2,3");
    pt.addPokemon(charmander);
    pt.addPokemon(pikachu);
    pt.addPokemon(eevee);
    pt.addPokemon(lapras);
    pt.addPokemon(mewtwo);
    pt.addPokemon(snorlax);
    pt.addPokemon(rayquaza);
    Pokemon most = pt.getMostPowerfulPokemon();
    if(!(most.equals(mewtwo)))
      return false;
    return true;
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testAddPokemonToStringSize: " + testAddPokemonToStringSize());
    System.out.println("testAddPokemonAndLookup: " + testAddPokemonAndLookup());
    System.out.println("testHeight: " + testHeight());
    System.out.println("testGetLeastPowerfulPokemon: " + testGetLeastPowerfulPokemon());
    System.out.println("testGetMostPowerfulPokemon: " + testGetMostPowerfulPokemon());
  }

}
