import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*; // import for ArrayList

public class Table {
  private int tableNum; //tableNum starts at 1
  private int seatsTaken;
  private ArrayList<Attendee> attSeated;  //seat number of attendee is index in arraylist+1
  public static int totalSeated = 0;


  public Table(int tableNum) {
    this.tableNum = tableNum;
    seatsTaken = 0;
    attSeated = new ArrayList <Attendee>(); 

  }

  public int getTableNum() {
    return tableNum;
  }
  public int getSeatsTaken() {
    return seatsTaken;
  }
  public ArrayList<Attendee> getAttSeated() {
    return attSeated;
  }
  public void incrSeatsTaken() {
    seatsTaken++;
  } 
  public void addAttendee(Attendee guest) {
    attSeated.add(guest);
  }

  
}