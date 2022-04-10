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
public class ComparisonMethods {
  /**
   * calculated the sum of integers from 1 to n using a for loop and running total
   * @param n - input value to add integers from 1 up to
   * @return long value which is the sum of all integers from 1 to n
   * Complexity: O(n)
   */
  public static long bruteForce(long n) {
    if(n == 0)
      return 0;
    long total = 0;
    for(int i = 1; i <= n; i++) {
      total += i;
    }
    return total;
  }
  /**
   * calculated the sum of integers from 1 to n using a formula
   * @param n - input value to add integers from 1 up to
   * @return long value which is the sum of all the integers from 1 to n
   * Complexity: O(1)
   */
  public static long constantTime(long n) {
    return (n*(n+1))/2;
  }
}
