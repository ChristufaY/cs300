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
public class BallotBoxTester {

  public static void main(String[] args) {
//    System.out.println("testCandidate(): " + testCandidate());
//    System.out.println("testPartyConstructor(): " + testPartyConstructor());
//    System.out.println("testPartyGetCandidate(): " + testPartyGetCandidate());
//    System.out.println("testPartyGetSize(): " +testPartyGetSize());
//    System.out.println("testBallotGetCandidates(): " + testBallotGetCandidates());
//    System.out.println("testBallotConstructor(): " + testBallotConstructor());
//    System.out.println("testBallotGetVote(): " + testBallotGetVote());
//    System.out.println("testBallotEquals(): " + testBallotEquals());
//    System.out.println("testBallotVote(): " + testBallotVote());
//    System.out.println("testBallotBoxConstructor(): " + testBallotBoxConstructor());
//    System.out.println("testBallotBoxGetNumBallots(): " + testBallotBoxGetNumBallots());
    System.out.println("testBallotBoxGetWinner(): " + testBallotBoxGetWinner());

  }
  public static boolean testCandidate() {
    try {
      Candidate c = new Candidate("Chris", "President");
      Candidate d = new Candidate("Ideal", "Vice President");
      if(!c.getName().equals("Chris"))
        return false;
      if(!c.getOffice().equals("President"))
        return false;
      if(!d.getName().equals("Ideal"))
        return false;
      if(!d.getOffice().equals("Vice President"))
        return false;
      String expectedC = "Chris (President)";
      if(!c.toString().equals(expectedC))
        return false;
    } catch (IllegalArgumentException e) {
      e.getMessage();
      e.printStackTrace();
      return false;
    }
    return true;
  }
  public static boolean testPartyConstructor() {
    Party p = new Party("Party");
    if(!p.getName().contentEquals("Party"))
      return false;
    return true;
  }
  public static boolean testPartyGetCandidate() {
    try {
      Party p = new Party("Jimothy");
      Candidate c = new Candidate("Jim", "Secretary");
      p.addCandidate(c);
      Candidate d = new Candidate("Jim", "Secretary");
      Candidate e = new Candidate("Billiam", "President");
      p.addCandidate(e);
      Candidate f = new Candidate("Billiam", "President");
      //p.printCandidates();
      if(!p.getCandidate("President").getOffice().equals(f.getOffice()))
        return false;
      if(!p.getCandidate("Secretary").getOffice().equals(d.getOffice()))
        return false;
    } catch (NoSuchElementException e) {
      e.getMessage();
      e.printStackTrace();
      return false;
    }
    return true;
  }
  public static boolean testPartyGetSize() {
    Party p = new Party("Jimothy");
    Candidate c = new Candidate("Jim", "Secretary");
    p.addCandidate(c);
    Candidate d = new Candidate("Jimmy", "Vice President");
    p.addCandidate(d);
    Party p2 = new Party("Billiam");
    if(p2.getSize() != 0)
      return false;
    if(p.getSize() != 2)
      return false;
    return true;
  }
  public static boolean testBallotGetCandidates() {
    Ballot b = new Ballot();
    Party p = new Party("Jimothy");
    Candidate c = new Candidate("Jim", "Vice President");
    p.addCandidate(c);
    Party p2 = new Party("Jimothy");
    Candidate c2 = new Candidate("Jimmy", "Vice President");
    p2.addCandidate(c2);
    Party p3 = new Party("Billiam");
    Candidate c3 = new Candidate ("Bill", "Vice President");
    p3.addCandidate(c3);
    Ballot.addParty(p);
    Ballot.addParty(p2);
    Ballot.addParty(p3);
    ArrayList<Candidate> candidates = Ballot.getCandidates("Vice President");
    ArrayList<Candidate> expected = new ArrayList<Candidate>();
    expected.add(c);
    expected.add(c3);
    //System.out.println(candidates.size());
    //System.out.println(expected.size());
    if(candidates.size() != expected.size())
      return false;
    else 
      return true;
  }
  public static boolean testBallotConstructor() {
    Ballot b = new Ballot();
    Party p = new Party("Jimothy");
    Candidate c = new Candidate("Jim", "Vice President");
    p.addCandidate(c);
    Party p2 = new Party("Jimothy");
    Candidate c2 = new Candidate("Jimmy", "Vice President");
    p2.addCandidate(c2);
    Party p3 = new Party("Billiam");
    Candidate c3 = new Candidate ("Bill", "Vice President");
    p3.addCandidate(c3);
    Ballot.addParty(p);
    Ballot.addParty(p2);
    Ballot.addParty(p3);
    if(Ballot.getCandidates("Vice President").size() != 2)
      return false;
    return true;
  }
  public static boolean testBallotGetVote() {
    Ballot b = new Ballot();
    Party p = new Party("Jimothy");
    Candidate c = new Candidate("Jim", "President");
    p.addCandidate(c);
    Ballot.addParty(p);
    Party p2 = new Party("Billiam");
    Candidate c2 = new Candidate("Bill", "Vice President");
    p2.addCandidate(c2);
    Ballot.addParty(p2);
    b.vote(c);
    b.vote(c2);
    //System.out.println(b.getVote("President"));
    //System.out.println(b.getVote("Vice President"));
    if(!(b.getVote("President").getName().equals("Jim")))
      return false;
    if(!(b.getVote("Vice President").getName().equals("Bill")))
      return false;
    return true;
  }
  public static boolean testBallotEquals() {
    Ballot b = new Ballot();
    Party p = new Party("Jimothy");
    Candidate c = new Candidate("Jim", "President");
    p.addCandidate(c);
    Ballot.addParty(p);
    Ballot b2 = new Ballot();
    Party p2 = new Party("Billiam");
    Candidate c2 = new Candidate("Bill", "Vice President");
    p2.addCandidate(c2);
    Ballot.addParty(p2);
    if(b.equals(b2))
      return false;
    return true;
    
  }
  public static boolean testBallotVote() {
    Ballot b = new Ballot();
    Party p = new Party("Jimothy");
    Party p2 = new Party("Billiam");
    Candidate c = new Candidate("Jim", "President");
    Candidate c2 = new Candidate("Bill", "President");
    p.addCandidate(c);
    p2.addCandidate(c2);
    Ballot.addParty(p);
    Ballot.addParty(p2);
    b.vote(c);
    b.vote(c2);
    if(!b.getVote("President").getName().equals("Bill"))
      return false;
    return true;
  }
  public static boolean testBallotBoxConstructor() {
    BallotBox box = new BallotBox();
    BallotBox box2 = new BallotBox();
    Ballot b = new Ballot();
    Party p = new Party("Jimothy");
    Party p2 = new Party("Billiam");
    Candidate c = new Candidate("Jim", "Vice President");
    Candidate c2 = new Candidate("Bill", "Secretary");
    p.addCandidate(c);
    p2.addCandidate(c2);
    Ballot.addParty(p2);
    Ballot.addParty(p);
    box.submit(b);
    box2.submit(b);
    if(!(box.getNumBallots() == (box2.getNumBallots())))
      return false;
    return true;
  }
  public static boolean testBallotBoxGetNumBallots() {
    Ballot b = new Ballot();
    Ballot b2 = new Ballot();
    BallotBox box = new BallotBox();
    Party p = new Party("Jimothy");
    Candidate c = new Candidate("Jim", "President");
    p.addCandidate(c);
    Ballot.addParty(p);
    box.submit(b);
    box.submit(b2);
    System.out.println(box.getNumBallots());
    if(box.getNumBallots() != 2)
      return false;
    return true;
  }
  public static boolean testBallotBoxGetWinner() {
    // Jim wins president, Bill wins, VP, Bim wins Secretary
    BallotBox box = new BallotBox();
    Ballot b = new Ballot();
    Ballot b2 = new Ballot();
    Ballot b3 = new Ballot();
    Party p = new Party("Jimothy");
    Party p2 = new Party("Billiam");
    Party p3 = new Party("Bimothy");
    Candidate c = new Candidate("Jim", "President");
    Candidate c2 = new Candidate("Bill", "Vice President");
    Candidate c3 = new Candidate("Bim", "Secretary");
    Candidate c4 = new Candidate("Jimmy", "Vice President");
    p.addCandidate(c);
    p2.addCandidate(c2);
    //p3.addCandidate(c3);
    p.addCandidate(c4);
    Ballot.addParty(p);
    Ballot.addParty(p2);
    Ballot.addParty(p3);
    b.vote(c);
    b2.vote(c2);
    b3.vote(c3);
    box.submit(b);
    box.submit(b2);
    box.submit(b3);
    Candidate temp = box.getWinner("President");
    if(temp != null && !temp.equals(c))
      return false;
    temp = box.getWinner("Vice President");
    if(temp != null && !temp.equals(c2))
      return false;
    temp = box.getWinner("Secretary");
    if(temp != null && !temp.equals(c3))
      return false;
    return true;
  }
}
