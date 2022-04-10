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
// Persons: Father gave hints and suggestions on how to implement code concisely and reduce some
// unnecessary code
// Online Sources: None
//
////////////////////////////////////////////////////////////////////
///////////

public class COVIDTracker {
  /**
   * Adds the id to appropriate test array if there is room.
   * 
   * @param pos   The current array of positive tests
   * @param neg   The current array of negative tests
   * @param id    The tested individual's unique identifier string
   * @param isPos true if the test was positive, false otherwise
   * @return true if the new record was added, false otherwise
   */
  public static boolean addTest(String[] pos, String[] neg, String id, boolean isPos) {
    // If isPos is true, checks the positive array has any empty/null values
    // Replaces empty slot with the id and returns true, false otherwise
    if (isPos) {
      for (int i = 0; i < pos.length; i++) {
        if (pos[i] == null) {
          pos[i] = id;
          return true;
        }
      }
      // If isPos is false, checks the negative array has any empty
      // Replaces empty slot with the id and returns true, false otherwise
    } else if (isPos == false) {
      for (int j = 0; j < neg.length; j++) {
        if (neg[j] == null) {
          neg[j] = id;
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Removes the id from appropriate array(s) if applicable
   * 
   * @param pos The current array of positive tests
   * @param neg The current array of negative tests
   * @param id  The tested individual's unique identifier string
   * @return true if an id was removed from either array
   */
  public static boolean removeIndividual(String[] pos, String[] neg, String id) {
    // Keeps track of how many removals there were
    int removedCount = 0;
    // Checks the positive array and compares each value with the id
    // Increases "removedCount" if the id matched
    for (int i = 0; i < pos.length; i++) {
      if (pos[i] == id) {
        pos[i] = null;
        removedCount++;
      }
    }
    // Checks the negative array and compares each value with the id
    // Increases "removedCount" if the id matched
    for (int j = 0; j < neg.length; j++) {
      if (neg[j] == id) {
        neg[j] = null;
        removedCount++;
      }
    }
    // integer value that keeps track of the null index value while looping through
    // the arrays
    int nullIndex = 0;
    // Loops through the positive array and replaces the next null value with the
    // next non-null value in the array
    for (int l = 0; l < pos.length; l++) {
      if (pos[l] != null) {
        pos[nullIndex] = pos[l];
        nullIndex++;
      }
    }
    // Because the above loop does not REPLACE the non-null values in the positive
    // array,
    // The below code REPLACES the values in the array from the "nullIndex" to the
    // end, to null
    for (int m = nullIndex; m < pos.length; m++) {
      pos[m] = null;
    }
    // re-initialize "nullIndex" to 0 so that the loop for the negative array can
    // check for null values
    // and replace it with the next non-null value
    nullIndex = 0;
    for (int n = 0; n < neg.length; n++) {
      if (neg[n] != null) {
        neg[nullIndex] = neg[n];
        nullIndex++;
      }
    }
    // Because the above loop does not REPLACE the non-null values in the positive
    // array,
    // the below code REPLACES the values in the array from the "nullIndex" to the
    // end, to null
    for (int o = nullIndex; o < neg.length; o++) {
      neg[o] = null;
    }
    // returns true if there was a removal
    return (removedCount > 0);
  }

  /**
   * Retrieves data for the user regarding the total number of tests, total unique individuals
   * tested, % of positive tests, and % of positive individuals
   * 
   * @param pos The current array of positive tests
   * @param neg The current array of negative tests
   * @return formatted string corresponding to the data in the two arrays
   */
  public static String getPopStats(String[] pos, String[] neg) {
    // Keeps track of unique positive individuals, unique negative individuals,
    // unique individuals,
    // % of positive tests, and % of positive individuals, and pos/neg tests,
    // respectively
    int uniquePosInd = 0;
    int uniqueNegInd = 0;
    int uniqueInd = 0;
    double propPosTest = 0.0;
    double propPosInd = 0.0;
    int posTests = 0;
    int negTests = 0;
    // Make copies of arrays so that the original isn't updated/changed
    String[] posCopy = pos.clone();
    String[] negCopy = neg.clone();
    // Counts the number of positive tests plus negative tests
    for (int i = 0; i < pos.length; i++) {
      if (posCopy[i] != null)
        posTests++;
    }
    for (int j = 0; j < neg.length; j++) {
      if (negCopy[j] != null)
        negTests++;
    }
    // Checks if both strings are empty and returns default string if true
    if (posTests == 0 && negTests == 0) {
      return formattedPopStats((posTests + negTests), uniqueInd, propPosTest, propPosInd);
    } else {
      // Uses removeIndividual method to count unique positive individuals
      while (posCopy[0] != null) {
        if (removeIndividual(posCopy, negCopy, posCopy[0]))
          ;
        uniquePosInd++;
      }
      // Uses removeIndividual method to count unique negative individuals
      // Pos and neg are done separately in different loops because the positive while
      // loop
      // also removes from the negative array because we use the removeIndividual
      // method
      while (negCopy[0] != null) {
        if (removeIndividual(posCopy, negCopy, negCopy[0]))
          ;
        uniqueNegInd++;
      }
      // Updates each variable once the values are found
      uniqueInd = uniquePosInd + uniqueNegInd;
      propPosTest = ((double) posTests / (posTests + negTests)) * 100.0;
      propPosInd = ((double) uniquePosInd / uniqueInd) * 100.0;
    }
    return formattedPopStats((posTests + negTests), uniqueInd, propPosTest, propPosInd);
  }

  /**
   * Retrieves data for the user on the prompted individual and returns information such as their
   * total number of test, how many were positive, and how many were negative
   * 
   * @param pos The current array of positive tests
   * @param neg The current array of negative tests
   * @param id  The tested individual's unique identifier string
   * @return Formatted string corresponding to the data in the arrays and given id
   */
  public static String getIndividualStats(String[] pos, String[] neg, String id) {
    // Keeps track of the individual's positive tests
    int posTestCount = 0;
    // Keeps track of the individual's negative tests
    int negTestCount = 0;
    // Loops through the positive array and checks for matches and increases
    // count whenever found
    for (int i = 0; i < pos.length; i++) {
      if (pos[i] == id) {
        posTestCount++;
      }
    }
    // Loops through the negative array and checks for matches and increases
    // count whenever found
    for (int j = 0; j < neg.length; j++) {
      if (neg[j] == id) {
        negTestCount++;
      }
    }
    return "Total tests: " + (posTestCount + negTestCount) + "\nPositive: " + posTestCount
        + "\nNegative: " + negTestCount;
  }

  /**
   * Helper method for getPopStats that takes the inputs from that method and return a formatted
   * string which reduces the redundancy of typing out the whole string template twice in the
   * getPopStats method
   * 
   * @param totalTests  the total number of tests
   * @param uniqueInd   the number of unique individuals
   * @param propPosTest double/percent of positive tests
   * @param propPosInd  double/percent of positive individuals
   * @return formatted string according to the instructions. Uses \n to format neatly
   */
  public static String formattedPopStats(int totalTests, int uniqueInd, double propPosTest,
      double propPosInd) {
    return "Total tests: " + totalTests + "\nTotal individuals tested: " + uniqueInd
        + "\nPercent positive tests: " + propPosTest + "%\nPercent positive individuals: "
        + propPosInd + "%";
  }
}
