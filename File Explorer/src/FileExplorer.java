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
import java.util.NoSuchElementException;

public class FileExplorer {
  /**
   * Returns a list of the names of all files and directories in
   * the the given folder. Throws NotDirectoryException with a 
   * description error message if the provided currentFolder 
   * does not exist or if it is not a directory
   * @param currentFolder is the folder being looked through
   * @return a list of the names of all files and directories in the given folder
   * @throws NotDirectoryException if the provided currentFolder does not exist
   * or is not a directory
   */
  public static ArrayList<String> listContents(File currentFolder)
      throws NotDirectoryException {
    if(currentFolder == null)
      throw new NotDirectoryException("Null is not a valid directory.");
    if(!currentFolder.exists())
      throw new NotDirectoryException("CurrentFolder does not exist.");
    if(!currentFolder.isDirectory())
      throw new NotDirectoryException("CurrentFolder is not a directory.");
    String[] listOfNames = currentFolder.list();
    //    for(int a = 0; a < listOfNames.length; a++) {
    //      System.out.println(listOfNames[a]);
    //    }
    ArrayList<String> contents = new ArrayList<String>();
    if(listOfNames.length == 0) {
      return contents;
    } else {
      for(int i = 0; i < listOfNames.length; i++) {
          contents.add(listOfNames[i]);
      }
    }
    return contents;
  }

  /**
   * Recursive method that lists the names of all the files (not directories) 
   * in the given folder and its sub-folders. Throws NotDirectoryException 
   * with a description error message if the provided currentFolder 
   * does not exist or if it is not a directory
   * @param currentFolder is the folder being looked through
   * @return a list of names of all the files (not directories) 
   * @throws NotDirectoryException if provided currentFolder does not exist
   * or is not a directory
   */
  private static ArrayList<String> contents = new ArrayList<String>();

  public static ArrayList<String> deepListContents (File currentFolder)
      throws NotDirectoryException {
    contents.clear();
    _deepListContents(currentFolder);
    return contents;
  }
  private static void _deepListContents (File currentFolder)
      throws NotDirectoryException {
    ArrayList<String> content = listContents(currentFolder);
    for(int i = 0; i < content.size(); i++) {
      File fileName = new File(currentFolder.getAbsolutePath() + File.separator + 
          content.get(i));
      if(fileName.isFile())
        contents.add(content.get(i));
      else {
        _deepListContents(fileName);
      }
    }
  }

  /**
   * Searches the given folder and all of its sub-folders for an exact match 
   * to the provided fileName. This method must be recursive or must use a 
   * recursive private helper method to operate.
   * This method returns a path to the file, if it exists.
   * Throws NoSuchElementException with a descriptive error message if the
   * search operation returns with no results found (including the case if
   * fileName is null or currentFolder does not exist, or was not a directory)
   * @param currentFolder is the folder being looked through
   * @param fileName is the name of the file that is being looked for
   * @return a path to the file, if it exists and throws a NoSuchElementException
   * if the search returns with no results (null, DNE, not a directory)
   */
  private static String found = "";
  private static boolean isFound = false;

  public static String searchByName(File currentFolder, String fileName) 
      throws NotDirectoryException {
    _searchByName(currentFolder, fileName);
    if(isFound)
      return found;
    else 
      throw new NoSuchElementException(fileName + " not found.");
  }
  private static void _searchByName (File currentFolder, String fileName) 
      throws NotDirectoryException { 
    ArrayList<String> names = listContents(currentFolder);
    for(int i = 0; i < names.size(); i++) {
      File f = new File(currentFolder, names.get(i));
      if(f.isFile() && f.getName().equals(fileName)) {
        isFound = true;
        found = f.getAbsolutePath() + File.separator + names.get(i);
        break;
      } else if(f.isDirectory()) {
        _searchByName(f, fileName);
      } 
      if(isFound)
        return ;
    }
  }

  /**
   * Recursive method that searches the given folder and its sub-folders
   * for ALL files that contain the given key in part of their name.
   * Returns An arraylist of all the names of files that match and an empty arraylist 
   * when the operation returns with no results found (including the case where
   * currentFolder is not a directory).
   * @param currentFolder is the folder being looked through
   * @param key is what is being looked for in the files' names to check matches
   * @return an arraylist of all the names of files that match. Empty arraylist if
   * no results found (including not a directory)
   */

  public static ArrayList<String> searchByKey (File currentFolder, String key) 
      throws NotDirectoryException {
    if(key == null)
      return new ArrayList<String>();
    ArrayList<String> files = new ArrayList<String>();
    ArrayList<String> matches = deepListContents(currentFolder);
    for(int i = 0; i < matches.size(); i++) {
      if(matches.get(i).contains(key))
        files.add(matches.get(i));
    }
    return files;
  }

  /**
   * Recursive method that searches the given folder and its subfolders for
   * ALL files whose size is within the given max and min values, inclusive.
   * Returns an array list of the names of all files whose size are within
   * the boundaries and an empty arraylist if the search operation returns
   * with no results found (including the case where currentFolder is not a directory).
   * @param currentFolder is the folder being looked through
   * @param sizeMin minimum size of a file
   * @param sizeMax maximum size of a file
   * @return an arraylist of names of all files whose size are within the boundaries.
   * Empty arraylist if the search returns no results
   */
  public static ArrayList<String> searchBySize(File currentFolder, long sizeMin,
      long sizeMax) throws NotDirectoryException {
    if(sizeMin < 0 || sizeMax < 0)
      return new ArrayList<String>();
    ArrayList<String> files = new ArrayList<String>();
    ArrayList<String> matches = deepListContents(currentFolder);
    for(int i = 0; i < matches.size(); i++) {
      File f = new File(matches.get(i));
      if(f.length() >= sizeMin && f.length() <= sizeMax)
        files.add(matches.get(i));
    }
    return files;
  }

}
































