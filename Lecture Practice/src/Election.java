//import java.util.ArrayList;
//import java.util.zip.DataFormatException;
//
//public class Election {
//  private static ArrayList<String> voters = new ArrayList<String>();
//
//  /**
//   * Adds the name's of a voter to the voters list
//   * @param voterName - name of a potential voter
//   * @param voterAge - age of the potential voter
//   * @throws NullPointerException if voterName is null
//   * @throws IllegalArgumentException if voterAge is less than 18
//   */
//  public static void registerToVote(String voterName, int voterAge) 
//      throws DataFormatException {
//    if(voterName == null) 
//      throw new NullPointerException("Cannot add a null reference to the list of voters.");
//    if(voterAge < 18)
//      throw new DataFormatException("The age of the voter must be at least 18.");
//    voters.add(voterName);
//  }
//  /**
//   * Checks whether Election.registerToVote() method works as expected when we pass it a null
//   * reference as voterName (invalid input).
//   * 
//   * @return true when this test verifies a correct functionality, and false otherwise
//   */
//  public static boolean testRegisterToVoteNullName() {
//    // define test scenario with invalid input (null voterName)
//    try {
//      registerToVote(null, 25);
//      return false;
//    } catch (NullPointerException e) {
//      return true;
//    } catch (Exception e2) {
//      return false;
//    }
//    // return true if no error is detected
//    // return true;
//  }
//  public static boolean testRegisterToVoteWithoutErrors() {
//
//  }
//  public static boolean testRegisterToVote() {
//    // define test scenario with correct input
//    try {
//      registerToVote("Chris", 18);
//      if(!voters.contains("Chris"))
//        return false;
//    } catch (Exception e) {
//      return false;
//    }
//    // return true if no error is detected
//    return true;
//  }
//}