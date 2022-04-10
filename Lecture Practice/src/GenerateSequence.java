import java.util.*;

public class GenerateSequence {
  
public static ArrayList<Integer> generateSequence(int n, int diff){
    ArrayList<Integer> numbers = new ArrayList<Integer>();
    if(n <= 0)
      return numbers;
    numbers.add(n);
    numbers.addAll(generateSequence(n-diff, diff));
    return numbers;
  }
  
  public static void main(String[] args) {
    ArrayList<Integer> sequence = generateSequence(20, 5);
    for(int i = 0; i < sequence.size(); i++) {
      System.out.print(sequence.get(i) + " ");
    }
  }
}