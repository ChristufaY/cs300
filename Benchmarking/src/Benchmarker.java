//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P03 Benchmarking
// Course:   CS 300 Fall 2020
//
// Author:   Christopher Yang
// Email:    cyang397@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: Father
// Online Sources: None  
//
///////////////////////////////////////////////////////////////////////////////
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.File;

public class Benchmarker {
  public static void main(String[] args) {
    File file = new File("output.txt");
    long[] arr = {0, 100000, 1000000, 10000000, 100000000, 1000000000};
    createResultsFile(file, arr);
  }
  /**
   * compares the runtime of the bruteForce method and the constantTime method 
   * @param n - input value both methods calculate the sum from 1 to n of
   * @return a formatted string containing the input value, bruteForceTime, and formulaTime
   * @throws NoSuchElementException if the runtime of the two methods are not equal
   */
  public static String compare(long n) throws NoSuchElementException { 
    long inputN = n;
    long start = System.currentTimeMillis();
    long bruteValue = ComparisonMethods.bruteForce(n);
    long end = System.currentTimeMillis();
    long bruteForceTime = end - start;
    start = System.currentTimeMillis();
    long constantValue = ComparisonMethods.constantTime(n);
    end = System.currentTimeMillis();
    long formulaTime = end - start;
    System.out.println(bruteValue);
    System.out.println(constantValue);
    if(bruteValue == constantValue)
      return inputN + "\t" + bruteForceTime + "\t" + formulaTime + "\n";
    else
      throw new NoSuchElementException(inputN + "\t" + bruteForceTime + "\t" + formulaTime + "\n");
  } 
  /**
   * creates a new file and writes the output of the compare() method on each line
   * @param f - the file to create a copy of
   * @param queryNs - the long[] which contains the n values which we input to the compare method
   */
  public static void createResultsFile(File f, long[] queryNs) {
    FileWriter results = null; 
    try {
      results = new FileWriter(f);
    } catch (IOException ioe) {
      System.out.println("Exception encountered, unable to complete method.");
    }
    String tempCompare = "";
    for(int i = 0; i < queryNs.length; i++) {
      try {
        tempCompare = compare(queryNs[i]);
      } catch (NoSuchElementException e) {
        tempCompare = e.getMessage();
        System.out.println("The runtime of the \"bruteForce\" method does not equal the runtime " 
            + "of the \"constantTime\" method.");
      }
      try {
        results.write(tempCompare);
      } catch (IOException ioe) {
        System.out.println("Exception encountered while writing for value N = ");
      }
    }
    try {
      results.close();
    } catch (IOException ioe) {
      System.out.println("Exception encountered while closing file.");
    } 
  }
}



