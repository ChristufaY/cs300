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

public class BallotBox {

  private ArrayList<Ballot> ballots;
  /**
   * no argument constructor that initializes the instance variable for the object
   */
  public BallotBox() {
    ballots = new ArrayList<Ballot>();
  }
  /**
   * returns the total number of unique Ballots in this BallotBox
   * @return total number of unique Ballots in BallotBox in int
   */
  public int getNumBallots() {
    return ballots.size();
  }
  /**
   * Records all of the existing Ballots' votes for the given office and returns the Candidate
   * with the most votes. 
   * @param office name of the office
   * @return Candidate with the most votes
   */
  public Candidate getWinner(String office) {
    ArrayList<Candidate> candidates = Ballot.getCandidates(office);
    if(candidates.size() == 0)
      return null;
    Candidate c;
    int[] votes = new int[candidates.size()];
    for(int i = 0; i < ballots.size(); i++) {
      for(int j = 0; j < candidates.size(); j++) {
        c = ballots.get(i).getVote(office);
        if(c == null)
          continue;
        if(c.getName().equals(candidates.get(j).getName()))
          votes[j]++;
      }
    }
    int currentMax = 0;
    for(int k = 0; k < candidates.size(); k++) {
      if(votes[currentMax] < votes[k])
        currentMax = k;
    }
    for(int j = 0; j < candidates.size(); j++)
      if(votes[j] == votes[currentMax] && j != currentMax)
        return null;
    return candidates.get(currentMax);
  }
  /**
   * Adds a Ballot to the BallotBox if and only if the Ballot has not already been added
   * @param b Ballot that is being checked and possibly added to the class ArrayList if not a 
   * duplicate of another Ballot
   */
  public void submit(Ballot b) {
    if(ballots.size() == 0) {
      ballots.add(b);
    } else {
      boolean duplicate = false;
      for(int i = 0; i < ballots.size(); i++) 
        if(ballots.get(i).equals(b))
          duplicate = true;
      if(!duplicate)
        ballots.add(b);
    }
  }

}
