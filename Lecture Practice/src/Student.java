
public class Student {

  private String first;
  private String last;
  private int uniqueID;
  private int nextUniqueID;
  private int numberOfStudents;
  
  public Student(String first, String last) {
    this.first = first;
    this.last = last;
  }
  public int getID() {
    return uniqueID;
  }
  public void setID(int id) {
    uniqueID = id;
  }
  
  public static void main(String[] args) {
    

  }

}
