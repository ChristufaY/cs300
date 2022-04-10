//////////////// FILE HEADER (INCLUDE IN EVERY FILE) ///////////////
///////////
//
// Title: P01 COVIDTracker
// Course: CS 300 Fall 2020
//
// Author: Christopher Yang
// Email: cyang397@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP ///////////////
///////////
// Persons: Father gave hints and suggestion on how to implement code concisely and reduce some
// unnecessary code
// Online Sources: None
//
////////////////////////////////////////////////////////////////////
///////////

class COVIDTrackerTester {

  public static void main(String[] args) {
    System.out.println("testAddTest(): " + testAddTest());
    System.out.println("testRemoveIndividual(): " + testRemoveIndividual());
    System.out.println("testGetPopStats(): " + testGetPopStats());
    System.out.println("testGetIndividualStats(): " + testGetIndividualStats());
  }

  /**
   * Checks whether addTest() works as expected
   * 
   * @return true if method functionality is verified, false otherwise
   */
  public static boolean testAddTest() {
    // Two empty arrays -> true; also checking that the arrays were updated properly
    String[] pos = new String[2];
    String[] neg = new String[2];
    if (!COVIDTracker.addTest(pos, neg, "ABC123", false) || neg[0] == null)
      return false;
    if (!COVIDTracker.addTest(pos, neg, "BCD234", true) || pos[0] == null)
      return false;
    // Two arrays with space -> true
    if (!COVIDTracker.addTest(pos, neg, "BCD234", false) || neg[1] == null)
      return false;
    // One full array but adding to one with space -> true
    if (!COVIDTracker.addTest(pos, neg, "DEF345", true) || pos[1] == null)
      return false;
    // One array with space but adding to full one -> false
    String[] pos2 = new String[2];
    if (COVIDTracker.addTest(pos2, neg, "DEF345", false))
      return false;
    // Two full arrays -> false;
    if (COVIDTracker.addTest(pos, neg, "DEF345", true))
      return false;
    return true;
  }

  /**
   * Checks whether removeIndividual() works as expected
   * 
   * @return true if method functionality is verified, false otherwise
   */
  public static boolean testRemoveIndividual() {
    // Two empty arrays -> false;
    String[] pos = new String[3];
    String[] neg = new String[3];
    if (COVIDTracker.removeIndividual(pos, neg, "ABC123") || pos[0] != null)
      return false;
    // One with space, One empty but doesn't match any -> false
    pos[0] = "ABC123";
    if (COVIDTracker.removeIndividual(pos, neg, "BCD234"))
      return false;
    // One with space, one empty and matches -> true
    if (!COVIDTracker.removeIndividual(pos, neg, "ABC123") || pos[1] != null)
      return false;
    // Two with space, matches in both arrays -> true
    neg[0] = "ABC123";
    pos[2] = "ABC123";
    if (!COVIDTracker.removeIndividual(pos, neg, "ABC123") || neg[0] != null)
      return false;
    // Two with multiple matches in one -> true
    String[] pos2 = new String[] {"ABC123", "BCD234", "DEF345", "DEF345", "BCD234"};
    String[] neg2 = new String[] {"DEF345", "ABC123", "BCD234", "BCD234", "ABC123"};
    if (!COVIDTracker.removeIndividual(pos2, neg2, "ABC123") || neg2[4] != null || pos2[4] != null)
      return false;
    return true;
  }

  /**
   * Checks whether getPopStats() works as expected
   * 
   * @return "Total tests: a\n Total individuals tested: b\n Percent positive tests: c%\n Percent
   *         positive individuals: d%\n"
   */
  public static boolean testGetPopStats() {
    // Two empty arrays
    String[] pos = new String[3];
    String[] neg = new String[3];
    if(!COVIDTracker.getPopStats(pos, neg).equals("Total tests: 0\nTotal individuals tested: "
        + "0\nPercent positive tests: 0.0%\nPercent positive individuals: 0.0%"))
      return false;
    // One empty one partially filled; pos is filled
    pos[0] = "ABC123";
    pos[1] = "BCD234";
    if(!COVIDTracker.getPopStats(pos, neg).equals("Total tests: 2\nTotal individuals tested: "
        + "2\nPercent positive tests: 100.0%\nPercent positive individuals: 100.0%"))
      return false;
    // One empty one partially filled; neg is filled, new empty string[]
    String[] pos2 = new String[3];
    neg[0] = "CDE345";
    neg[1] = "DEF456";
    if(!COVIDTracker.getPopStats(pos2, neg).equals("Total tests: 2\nTotal individuals tested: "
        + "2\nPercent positive tests: 0.0%\nPercent positive individuals: 0.0%"))
      return false;
    // Two partially filled; use updated pos and neg
    if(!COVIDTracker.getPopStats(pos, neg).equals("Total tests: 4\nTotal individuals tested: "
        + "4\nPercent positive tests: 50.0%\nPercent positive individuals: 50.0%"))
      return false;
    // One partially filled, One filled; pos is filled; use new pos3 and neg
    String[] pos3 = new String[] {"ABC123", "BCD234", "CDE345"};
    if(!COVIDTracker.getPopStats(pos3, neg).equals("Total tests: 5\nTotal individuals tested: "
        + "4\nPercent positive tests: 60.0%\nPercent positive individuals: 75.0%"))
      return false;
    // One partially filled, one filled; neg is filled use new neg3 and pos
    String[] neg3 = new String[] {"ABC123", "BCD234", "CDE345"};
    if(!COVIDTracker.getPopStats(pos, neg3).equals("Total tests: 5\nTotal individuals tested: "
        + "3\nPercent positive tests: 40.0%\nPercent positive individuals: 66.66666666666666%"))
      return false;
    // Two full
    if(!COVIDTracker.getPopStats(pos3, neg3).equals("Total tests: 6\nTotal individuals tested: "
        + "3\nPercent positive tests: 50.0%\nPercent positive individuals: 100.0%"))
      return false;
    return true;
  }
  /**
   * Checks whether getIndividualStats() works as expected
   * 
   * @return "Total tests: x\n Positive: y\n Negative: z\n"
   */
  public static boolean testGetIndividualStats() {
    String[] pos = {"ABC123", "BCD234", "ABC123", "DEF456"};
    String[] neg = {"ABC123", "BCD234", "CDE345", "ABC123"};
    // individual doesn't exist
    if(!COVIDTracker.getIndividualStats(pos, neg, "EFG567").equals("Total tests: 0\nPositive: "
        + "0\nNegative: 0"))
      return false;
    // individual exists and takes one test; results pos
    if(!COVIDTracker.getIndividualStats(pos, neg, "DEF456").equals("Total tests: 1\nPositive: "
        + "1\nNegative: 0"))
      return false;
    // individual exists and takes one test; results neg
    if(!COVIDTracker.getIndividualStats(pos, neg, "CDE345").equals("Total tests: 1\nPositive: "
        + "0\nNegative: 1"))
      return false;
    // individual exists and takes more than one test; one in each
    if(!COVIDTracker.getIndividualStats(pos, neg, "BCD234").equals("Total tests: 2\nPositive: "
        + "1\nNegative: 1"))
      return false;
    // individual exists and takes more than one test; multiple pos and neg
    if(!COVIDTracker.getIndividualStats(pos, neg, "ABC123").equals("Total tests: 4\nPositive: "
        + "2\nNegative: 2"))
      return false;
    return true;
  }

}

