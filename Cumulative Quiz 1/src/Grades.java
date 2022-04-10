///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: Christopher Yang
// Campus ID: 9082293151
// WiscEmail: cyang397@wisc.edu
////////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * This class implements methods to store and manage students grades anonymously. The grades are
 * stored in an oversize array defined by a reference to an array which stores grades of type
 * double, and a variable of type int to keep track of the number of grades stored in the array.
 * 
 * TODO: Complete the implementation of the two methods below: 
 *       1. insertGrade()
 *       2. getSpecificGradesRate()
 *       3. testInsertInvalidGrade()
 * 
 * Note that when creating new exception objects, including descriptive messages within 
 * these objects is optional.
 *
 */
public class Grades {

  /**
   * @param grades   - an array which stores students grades
   * @param size     - number of grades stored in the grades array
   * @param newGrade - grade to be inserted in grades
   * @return the new size of the oversize array after newGrade is successfully inserted to the
   *         grades array
   * @throws IllegalStateException    if the array grades is full
   * @throws IllegalArgumentException if new Grade is out of the range of [0.0 .. 100.0]
   */
  public static int insertGrade(double[] grades, int size, double newGrade) {
    // TODO
    // throw an IllegalStateException if grades array is full
    if(size >= grades.length)
      throw new IllegalStateException("The array is full.");
    // throw an IllegalArgumentException if newGrade is less than 0.0 or
    // greater than 100.0
    if(newGrade < 0.0 || newGrade > 100.0) 
      throw new IllegalArgumentException("Grade cannot be less than 0.0 or greater than 100.0.");
    // add newGrade at the end of the oversize array
    grades[size] = newGrade;
    size++;
    return size; // This statement was added to make this code compiles without errors
  }

  /**
   * Gets the rate of grades which are greater or equal to a given threshold
   * 
   * @param grades    - an array which stores students grades
   * @param size      - number of grades stored in the grades array
   * @param threshold - cutoff for grades
   * @return the portion of grades which are greater or equal to threshold among all the grades 
   * stored in the array grades.
   */
  public static double getSpecificGradesPortion(double[] grades, int size, double threshold) {
    // TODO implement this method
    double scoreAboveOrEqual = 0.0;
    for(int i = 0; i < size; i++) {
      if(grades[i] >= threshold)
        scoreAboveOrEqual++;
    }
    return scoreAboveOrEqual/size; // This statement was added to make this code 
    // compiles without errors
  }

  /**
   * Checks the correctness of the implementation of the Grades.getSpecificGradesRate() method
   * @return true if the method passes this test and false otherwise
   */
  public static boolean testSpecificGradesPortion() {
    // create an non-empty oversize array
    double[] grades = new double[] {92.3, 87.5, 0.0, 98.2, 75.4, 100.0, 0.0, 0.0, 0.0};
    int size = 6;
    double threshold = 90.0;
    double expectedPortion = 0.5;
    // call getSpecificGradesPortion() and check whether it returns the expected output
    double portion = getSpecificGradesPortion(grades, size, threshold);
    if (Math.abs(portion - expectedPortion) > 0.001) // compare portion and expectedPortion
      return false;

    return true;
  }

  /**
   * Checks whether the Grades.insertGrade() method works as expected when called to insert
   * a valid grade into a non-empty oversize array of doubles
   * @return true when this test verifies a correct functionality, and false otherwise 
   */
  public static boolean testSuccessfulGradeInsertion() {
    // create an non-empty oversize array
    double[] grades = new double[] {92.3, 87.5, 0.0, 98.2, 75.4, 0.0, 0.0, 0.0};
    int size = 5;
    try {
      // try to add a valid new grade to the array grades
      size = insertGrade(grades, size, 53.9);
      // expected content of grades array after insertGrade() is called
      double[] expectedGrades = new double[] {92.3, 87.5, 0.0, 98.2, 75.4, 53.9, 0.0, 0.0};
      // check whether the size and the content of the array are correct
      if (size != 6)
        return false;
      if (!Arrays.equals(grades, expectedGrades))
        return false;
    } catch (Exception e) {
      return false; // no exception is expected to be thrown
    }

    return true;

  }


  /**
   * Checks whether the the Grades.insertGrade() method throws an IllegalArgumentException
   * without making any change to the contents of the oversize array provided as input parameter
   * or to its size when called to insert an invalid grade value.
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testInsertInvalidGrade() {
    // TODO
    // 1. Create a non-empty oversize array which stores 4 grades of type double
    double[] grades = new double[4];
    grades[0] = 10.2;
    grades[1] = 50.0;
    grades[2] = 99.9;
    int size = 3;
    // 2. Try to add a non-valid grade in your choice
    double invalidGrade = -10.2;
    // 3. If an IllegalArgumentException was thrown return true. 
    //    If no exception was thrown return false.
    //    If an exception other than IllegalArgumentException was thrown, return false.
    try {
      insertGrade(grades, size, invalidGrade);
      return false;
    } catch (IllegalArgumentException e) {
      return true;
    } catch (Exception e) {
      return false;
    }
    //return false; // This statement was added to make this code compiles without errors
  }


  /**
   * Main method to call the test methods
   * 
   * @param args - input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testSuccessfulGradeInsertion(): " + testSuccessfulGradeInsertion());
    System.out.println("testInsertInvalidGrade(): " + testInsertInvalidGrade());
    System.out.println("testSpecificGradesPortion(): " + testSpecificGradesPortion());
  }
}