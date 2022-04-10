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
public class Ballot {

  private static ArrayList<Party> parties = new ArrayList<Party>();
  private static int counter = 0;
  /**
   * Adds a Party to the class ArrayList
   * If the class ArrayList already contains this Party, do nothing
   * @param p party object supposedly being added to the class ArrayList
   */
  public static void addParty(Party p) {
    boolean duplicate = false;
    for(int i = 0; i < parties.size(); i++) {
      if(parties.get(i).getName().equals(p.getName()))
        duplicate = true;
    }
    if(!duplicate)
      parties.add(p);
  }
  /**
   * Creates and returns an ArrayList of all Candidates running for the given office
   * SHould not crash if a Party has no Candidate running for the given office
   * @param office name of the office 
   * @return an ArrayList of all Candidates running for the given office
   */
  public static ArrayList<Candidate> getCandidates(String office) {
    ArrayList<Candidate> candidates = new ArrayList<Candidate>();
    for(int i = 0; i < parties.size(); i++) {
      try {
      if(parties.get(i).getCandidate(office) != null)
        candidates.add(parties.get(i).getCandidate(office));
    } catch (NoSuchElementException e) {
      e.getMessage();
      e.printStackTrace();
    }
    }
    return candidates;
  }

  //private ArrayList<Candidate> votes;
  private Candidate[] votes;
  private final int ID;
  /**
   * No argument constructor that initializes the votes array to the correct length and creates
   * a unique ID number for the Ballot
   */
  public Ballot() {
    this.votes = new Candidate[Candidate.OFFICE.length];
    //System.out.println(this.votes[0]);
    //System.out.println(this.votes[1]);
    //System.out.println(this.votes[2]);
    //for(int i = 0; i < Candidate.OFFICE.length; i++)
    //this.votes[i] = new Candidate(null, null);
    //this.votes = new ArrayList<Candidate>(Candidate.OFFICE.length);
    ID = counter;
    counter++;
  }
  /**
   * method that returns the Candidate that this Ballot has voted for, or null if there
   * is no vote for that office
   * @param office name of the office
   * @return Candidate that this Ballot has voted for
   */
  public Candidate getVote(String office) {
    System.out.println("votes array inside getVote: " + this.votes[0]);
    System.out.println("votes array inside getVote: " + this.votes[1]);
    System.out.println("votes array inside getVote: " + this.votes[2]);
    for(int i = 0; i < Candidate.OFFICE.length; i++) {
      if(this.votes[i] == null)
        continue;
      if(this.votes[i].getOffice().equals(office))
          return this.votes[i];
    }
    return null;
  }
  /**
   * overrides the default object method equals and determines equality by comparing the unique
   * ID values of two Ballots. If the provided Object o is not a Ballot, this should return false
   * @return true if Object o is a Ballot, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    //use toString method
    if(!(o instanceof Ballot))
      return false;
    if(this.ID == ((Ballot) o).ID)
      return true;
    else 
      return false;
  }
  /**
   * Records the vote for the given Candidate at the appropriate index in the Ballot's array.
   * Can overwrite an existing vote
   * @param c Candidate that this Ballot has voted for
   */
  public void vote(Candidate c) {
    // System.out.println(this.votes.size());
    //    for(int j = 0; j < this.votes.length; j++)
    //      if(this.votes[j] == null)
    //        this.votes[j] = new Candidate("Temp", Candidate.OFFICE[j]);
    for(int i = 0; i < this.votes.length; i++) {
      //System.out.println(c.getOffice());
      //System.out.println(Candidate.OFFICE[i]);
      if(c.getOffice().equals(Candidate.OFFICE[i])) {
        //System.out.println("Inside Ballot.vote");
        votes[i] = c;
      }
    }
  }
}
