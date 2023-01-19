import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*; // import for ArrayList

public class Attendee {
  private String firstName;
  private String lastName;  
  private int compNum;
  private int tableNum; //assigned when seated

  public Attendee(String firstName, String lastName, int compNum) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.compNum = compNum;
    tableNum = 0;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  } 
  
  public int getCompNum() {
    return compNum;
  }  

  public int getTableNum() {
    return tableNum;
  }

  public void setTableNum(int tableNum) {
    this.tableNum = tableNum;    
  }
}