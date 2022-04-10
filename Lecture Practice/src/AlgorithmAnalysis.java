
public class AlgorithmAnalysis {
   
  
  // Running time complexity of any loop with respect to the problem size n
  // = number of iterations at worst case *
  // running time complexity of the code that will be run for each iteration
  
  
  public static void fillArray(int[] data) {
    // Determine the runtime (running time) complexity of this method, assuming that the problem
    // size n represents the length of the array data provided as input.
    // T(n) = O(?)
    // n = data.length; 
    int n = data.length; // size of the input data
    // for loop: O(1) + (n/2) * (O(1)) = O(1) + O(n) * O(1) = O(n)
    for(int i = 0; i < n/2; i++) { // number of iterations * running time complexity of the 
                                           // code within the body of the loop
      data[i] = 1;
    }
  }
  public static void bestFillArray(int[] data) {
    // Determine the runtime (running time) complexity of this method, assuming that the problem
    // size 
    // n represents the length of the array data provided as input.
    // T(n) = O(?)
    // for loop: O(1) + 1000 * O(1) = O(1)
    for(int i = 0; i < 1000; i++) // number of iterations * running time complexity of the code
                                  // within
      // the body of the loop
      data[i] = i;
  }
  public static void SillyFillArray(int[] data) {
    int n = data.length;
    int x = 100;
    for(int i = n + x; i > n; i++) {
      data[0] = i;
      System.out.println(i);
    }
  }
}
