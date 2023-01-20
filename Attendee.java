/**
@author: Arjun Gupta
@date: 1/20/2023
Attendee Class: This has the constructor for attendeee as well as the attributes and methods. 
The attributes and methods allow PartyPlanner to handle a variety of data. 
@param: takes the first name, last name, and company number
@return: getters can return the first name, last name, company number, and table number
*/
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*; // import for ArrayList

/*
class for the Attendees
*/
public class Attendee {
  private String firstName;
  private String lastName;  
  private int compNum; //compNum starts at 1
  private int tableNum; //assigned when seated

  /* constructor for Attendee 
*/
  public Attendee(String firstName, String lastName, int compNum) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.compNum = compNum;
    tableNum = 0;
  }

  /* getter method for first name
  */
  public String getFirstName() {
    return firstName;
  }

  /* getter method for last name
  */  
  public String getLastName() {
    return lastName;
  } 

  /* getter method for company number
  */  
  public int getCompNum() {
    return compNum;
  }  

  /* getter method for table number
  */  
  public int getTableNum() {
    return tableNum;
  }

  /* setter method for table number once the attendee is seated
  */  
  public void setTableNum(int tableNum) {
    this.tableNum = tableNum;    
  }
} //end Attendee class