/**
@author: Arjun Gupta
@date: 1/20/2023
Table Class: This has the constructor for table as well as the attributes and methods. 
The attributes and methods allow PartyPlanner to handle a variety of data. This class stores the table number, seats  taken at the table, the attendees seated at the table, and the total seated attendees from all the tables combined. 
@param: takes the table number
@return: getters can return the table number, seats taken at the table, the arraylist of attendees seated at the table, and the total seated attendees at the event is public
*/
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*; // import for ArrayList

/*
Table class
*/
public class Table {
  private int tableNum; //tableNum starts at 1
  private int seatsTaken;
  private ArrayList<Attendee> attSeated;  //seat number of attendee is index in arraylist+1
  public static int totalSeated = 0;

  /*
  Table constructor
  */
  public Table(int tableNum) {
    this.tableNum = tableNum;
    seatsTaken = 0;
    attSeated = new ArrayList <Attendee>(); 

  }

  /*
  table number getter
  */
  public int getTableNum() {
    return tableNum;
  }

  /*
  seats taken getter
  */
  public int getSeatsTaken() {
    return seatsTaken;
  }

  /*
  arraylist of attendees seated at the table getter
  */  
  public ArrayList<Attendee> getAttSeated() {
    return attSeated;
  }

  /*
  seatsTaken incrementer
  */  
  public void incrSeatsTaken() {
    seatsTaken++;
  } 

  /*
  add attendees to the attendees seated arraylist 
  */  
  public void addAttendee(Attendee guest) {
    attSeated.add(guest);
  }

  
} //end table class