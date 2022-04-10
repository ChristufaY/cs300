//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P05 BallotBox
// Course:   CS 300 Fall 2020
//
// Author:   Christopher Yang
// Email:    cyang397@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         Father, helped debug methods to find the error in code
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Party {
  
  private String name;
  private ArrayList<Candidate> candidates;
  /**
   * Party constructor which initializes the instance variables for the object
   * @param name String representing the name of the Party
   */
  public Party(String name) {
    this.name = name;
    this.candidates = new ArrayList<Candidate>();
  }
  /**
   * Accessor method that gets the name of the Party
   * @return String representation of the Party's name
   */
  public String getName() {
    return this.name;
  }
  /**
   * Accessor method that gets the size of the Candidates Array List
   * @return int representation of size of Candidates Array List
   */
  public int getSize() {
    return this.candidates.size();
  }
  /**
   * Finds the candidates from the Party running for the given office.
   * Throws a NoSuchElementException if no Candidate is found
   * @param office String representation of name of Office
   * @return Candidate running for the given office within the party
   */
  public Candidate getCandidate(String office) {
    boolean found = false;
    int temp = 0;
    for(int i = 0; i < this.candidates.size(); i++) {
      if(this.candidates.get(i).getOffice().equals(office)) {
        temp = i;
        found = true;
      }
    }
    if(found)
      return this.candidates.get(temp);
    else 
      throw new NoSuchElementException("Candidate does not exist.");
  }
  /**
   * Adds a Candidate to the Party
   * Throws an IllegalArgumentException if the provided Candidate is running for the same office
   * as another member of the Party
   * @param c Candidate object
   */
  public void addCandidate(Candidate c) {
    boolean officeOverlap = false;
    for(int i = 0; i < this.candidates.size(); i++) {
      if(this.candidates.get(i).getOffice().equals(c.getOffice()))
        officeOverlap = true;
    }
    if(!officeOverlap) 
      this.candidates.add(c);
    else 
      throw new IllegalArgumentException("The candidate " + c + " is running for the same office "
          + "as another member of the party");
  }
}
