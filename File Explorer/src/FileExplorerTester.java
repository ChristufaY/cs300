//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P04 File Explorer
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
import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Arrays;

public class FileExplorerTester {

  public static boolean testListContents(File folder) {
    try {
      // Scenario 1
      // list the basic contents of the cs300 folder
      ArrayList<String> listContent = FileExplorer.listContents(folder);
      // expected output content
      String[] contents = new String[] {"grades", "lecture notes", "programs",
          "quizzes preparation", "reading notes", "syllabus.txt", "todo.txt"}; 
      List<String> expectedList = Arrays.asList(contents);
      // check the size and the contents of the output
      if (listContent.size() != 7) {
        System.out.println("Problem detected: cs300 folder must contain 7 elements.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i)
          + " is missing from the output of the list contents of cs300 folder.");
          return false;
        }
      }

      // Scenario 2 - list the contents of the grades folder
      File f = new File(folder.getPath() + File.separator + "grades"); 
      listContent = FileExplorer.listContents(f);
      if (listContent.size() != 0) {
        System.out.println("Problem detected: grades folder must be empty.");
        return false;
      }
      // Scenario 3 - list the contents of the p02 folder
      f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02");
      listContent = FileExplorer.listContents(f);
      if (listContent.size() != 1 || !listContent.contains("WisconsinPrairie.java")) {
        System.out.println("Problem detected: p02 folder must contain only one file named " 
            + "WisconsinPrairie.java.");
        return false;
      }
      // Scenario 4 - Try to list the contents of a file
      f = new File(folder.getPath() + File.separator + "todo.txt"); try {
        listContent = FileExplorer.listContents(f);
        System.out.println("Problem detected: Your FileExplorer.listContents() must "
            + "throw a NotDirectoryException if it is provided an input which is not"
            + "a directory."); return false;
      } catch (NotDirectoryException e) { // catch only the expected exception 
        // Expected behavior -- no problem detected
      }
      // Scenario 5 - Try to list the contents of not found directory/file
      f = new File(folder.getPath() + File.separator + "music.txt"); try {
        listContent = FileExplorer.listContents(f);
        System.out.println("Problem detected: Your FileExplorer.listContents() must "
            + "throw a NotDirectoryException if the provided File does not exist."); return false;
      } catch (NotDirectoryException e) {
        // catch only the expected exception to be thrown -- no problem detected
      }
    } catch (Exception e) {
      System.out.println("Problem detected: Your FileExplorer.listContents() has thrown" 
          + " a non expected exception."); 
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public static boolean testDeepListBaseCase(File folder) {
    try {
      ArrayList<String> listContent = FileExplorer.deepListContents(folder);
      if (listContent.size() != 0) {
        System.out.println("Problem detected: grades folder must be empty.");
        return false;
      }
    } catch (NotDirectoryException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public static boolean testDeepListRecursiveCase(File folder) {
    try {
      String[] expecteds = {"ExceptionHandling.txt", "proceduralProgramming.txt", 
          "UsingObjectsAndArrayLists.txt", "AlgorithmAnalysis.txt", "Recursion.txt"};
      List<String> expected = Arrays.asList(expecteds);
      ArrayList<String> actual = FileExplorer.deepListContents(folder);
      if(actual.size() != expected.size())  {
        System.out.println("The lengths of the expected and actual arrayLists don't match.");
        return false;
      }
      for (int i = 0; i < expected.size(); i++) {
        if (!actual.contains(expected.get(i))) {
          System.out.println("Problem detected: " + expected.get(i)
          + " is missing from the output of the list contents of " + folder.getName() 
          + " folder.");
          return false;
        }
      }
    } catch (NotDirectoryException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public static boolean testSearchByName(File folder, String fileName) {
    try {
      String expected = "cs300/lecture notes/unit2/Recursion.txt";
      String actual = FileExplorer.searchByName(folder, fileName);
      if(actual.equals(expected))
        return true;
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      return false;
    } catch (NotDirectoryException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public static boolean testSearchByKeyBaseCase(File folder, String key) {
    try {
      String[] expecteds = {"ExceptionHandling.txt", "proceduralProgramming.txt",
      "UsingObjectsAndArrayLists.txt"};
      List<String> expected = Arrays.asList(expecteds);
      ArrayList<String> actual = FileExplorer.searchByKey(folder, key);
      for(int i = 0; i < expected.size(); i++) {
        if(!actual.contains(expected.get(i))) {
          System.out.println("Problem detected: " + expected.get(i) + " is missing"
              + " from the output of the list of contents of " + folder.getName()
              + " folder.");
          return false;
        }
      }
    } catch (NotDirectoryException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public static boolean testSearchByKey(File folder, String key) {
    try {
      String[] expecteds = {"ExceptionHandling.txt", "proceduralProgramming.txt", 
          "UsingObjectsAndArrayLists.txt", "AlgorithmAnalysis.txt", "Recursion.txt",
          "outline.txt", "zyBooksCh1.txt", "zyBooksCh2.txt", "zyBooksCh3.txt",
          "zyBooksCh4.txt", "syllabus.txt", "todo.txt"};
      List<String> expected = Arrays.asList(expecteds);
      ArrayList<String> actual = FileExplorer.searchByKey(folder, key);
      for(int i = 0; i < expected.size(); i++) {
        if(!actual.contains(expected.get(i))) {
          System.out.println("Problem detected: " + expected.get(i) + " is missing"
              + " from the output of the list of contents of " + folder.getName()
              + " folder.");
          return false;
        }
      }
    } catch (NotDirectoryException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public static boolean testSearchBySize(File folder, long sizeMin, long sizeMax) {
    try {
      String[] expecteds = {"todo.txt"};
      List<String> expected = Arrays.asList(expecteds);
      ArrayList<String> actual = FileExplorer.searchBySize(folder, sizeMin, sizeMax);
      for(int i = 0; i < actual.size(); i++) {
        File f = new File(actual.get(i));
        File g = new File(expected.get(i));
        if(f.length() != g.length()) {
          System.out.println("Problem detected: " + expected.get(i) + " is missing "
              + "from the output of the list contets of " + folder.getName() 
              + " folder.");
          return false;
        }
      }
    } catch (NotDirectoryException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println("testListContents: " + testListContents(new File("cs300")));
    System.out.println("testDeepListBaseCase: "  
        + testDeepListBaseCase(new File("cs300/grades")));
    System.out.println("testDeepListRecursiveCase: "
        + testDeepListRecursiveCase(new File("cs300/lecture notes")));
    System.out.println("testSearchByName: " + testSearchByName(new File("cs300"), "Recursion.txt"));
    System.out.println("testSearchByKeyBaseCase: " 
        + testSearchByKeyBaseCase(new File("cs300/lecture notes/unit1"), ".txt"));
    System.out.println("testSearchByKey: " + testSearchByKey(new File("cs300"), ".txt"));
    System.out.println("testSearchBySize: " + testSearchBySize(new File("cs300"),
        1, 200));
  }
}









