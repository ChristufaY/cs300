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
public class PokemonNode {
  private Pokemon data; // data field of this PokemonNode
  private PokemonNode leftChild; // reference to the left child
  private PokemonNode rightChild; // reference to the right child
  
  /**
   * A one argument constructor which sets leftChild and rightChild to null and initializes the 
   * data field
   * throws IllegalArgumentException if data is null
   * @param data data field being input
   */
  public PokemonNode(Pokemon data) {
    Pokemon temp = data;
    if(temp == null)
      throw new IllegalArgumentException("Data is null.");
    this.leftChild = null;
    this.rightChild = null;
    this.data = data;
  }
  /**
   * getter method that returns the left child of this PokemonNode
   * @return left child of this PokemonNode
   */
  public PokemonNode getLeftChild() {
    return this.leftChild;
  }
  /**
   * getter method that returns the right child of this PokemonNode
   * @return right child of this PokemonNode
   */
  public PokemonNode getRightChild() {
    return this.rightChild;
  }
  /**
   * getter method that returns a reference to the Pokemon contained in this PokemonNode
   * @return reference to the Pokemon contained in this PokemonNode
   */
  public Pokemon getPokemon() {
    return this.data;
  }
  /**
   * sets the left child of this PokemonNode (null values allowed)
   * @param left the value the left child will hold
   */
  public void setLeftChild(PokemonNode left) {
    this.leftChild = left;
  }
  /**
   * sets the right child of this PokemonNode (null values allowed)
   * @param right the value the right child will hold
   */
  public void setRightChild(PokemonNode right) {
    this.rightChild = right;
  }
}
