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
public class Candidate {
  
  protected static final String[] OFFICE = {"President", "Vice President", "Secretary"};
  private String name;
  private String office;
  /**
   * Two-argument constructor that initializes the instance variables for the object
   * and checks that office is valid and throws an IllegalArgumentException if not 
   * @param name String representing the Candidate's name
   * @param office String representing the Candidate's office
   */
  public Candidate(String name, String office) {
    this.name = name;
    boolean validOffice = false;
    for(int i = 0; i < OFFICE.length; i++) {
      if(OFFICE[i].equals(office))
        validOffice = true;
    }
    if(validOffice)
      this.office = office;
    else 
      throw new IllegalArgumentException(office + " is not a valid office.");
  }
  /**
   * Getter method that returns the name of the Candidate
   * @return the String name of the Candidate
   */
  public String getName() {
    return this.name;
  }
  /**
   * Getter method that returns the name of the Candidate's office
   * @return the name of the Candidate's office 
   */
  public String getOffice() {
    return this.office;
  }
  /**
   * returns a String representation of this Candidate as NAME (OFFICE)
   * @return String representation of this Candidate as NAME (OFFICE)
   */
  public String toString() {
    return (this.name + " (" + this.office + ")");
  }
}
