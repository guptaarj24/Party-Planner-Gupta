/**
@author: Arjun Gupta
@date: 1/20/2023
PartyPlanner Class: This has the main method, calls all the methods, and seats the attendees. It can also  register a new attendee, find an attendee, and print rosters by tabe and company.
*/
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*; // import for ArrayList

/*
PartyPlanner class
*/
public class PartyPlanner {
  public static ArrayList<Company> allCompanies = new ArrayList<Company>();
  public static ArrayList<Attendee> attendeeList = new ArrayList<Attendee>();
  public static ArrayList<Table> allTables = new ArrayList<Table>();
  public static int maxTables = 10; //static to refer in methods...declaring max number of tables
  public static int maxAttInCompany = 10; //static to refer in methods...declaring max number of attendees for one company
  public static int maxSeatsAtTable = 10; //static to refer in methods...declaring max number of seats for one table  
  public static int eventCapacity = 100; //static to refer in methods...declaring capacity of event (max attendees possible)
  public static Scanner scan = new Scanner(System.in); //initializing scanner  

  /*
  main method
  */
  public static void main( String[] args) {
    String yOrN; //initialize yOrN

    //initialize all companies
    allCompanies.add(new Company(1, "Walmart"));
    allCompanies.add(new Company(2, "Kroger"));    
    allCompanies.add(new Company(3, "Amazon"));    
    allCompanies.add(new Company(4, "Lowes"));    
    allCompanies.add(new Company(5, "Best Western"));    
    allCompanies.add(new Company(6, "KMart"));    
    allCompanies.add(new Company(7, "Fusian"));    
    allCompanies.add(new Company(8, "Heinz"));    
    allCompanies.add(new Company(9, "Gucci"));    
    allCompanies.add(new Company(10, "Prada"));    
    allCompanies.add(new Company(11, "Nike"));    
    allCompanies.add(new Company(12, "Dodge"));    
    allCompanies.add(new Company(13, "Maserati"));    
    allCompanies.add(new Company(14, "Razor"));    
    allCompanies.add(new Company(15, "AMD"));    
    allCompanies.add(new Company(16, "Razer"));       

    //add all tables to arraylist
    for (int i = 0; i < maxTables; i++) { 
      allTables.add(new Table(i+1));
    }

    //load attendees from data file
    try {
      File myObj = new File("PartyGuest.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String dataLine = myReader.nextLine();
        String [] attendee = dataLine.split(",");
        Attendee g1 = new Attendee(attendee[2], attendee[1], Integer.parseInt(attendee[3]));
        attendeeList.add(g1);
      }
      myReader.close();
    } //close try 
    catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    } //close catch
    
    //going through attendee list to seat each attendee and goes through all the checks
    for (Attendee individual: attendeeList) {
      if (checkCompExists(individual.getCompNum()) == true) {   
        if (checkNumAttInCompany(individual.getCompNum()) == true) {      
          if (checkTotalAtt() == true) {        
            int tableNum = findTableAvailable(individual.getCompNum());
            if (tableNum != -1) {        
              // add attendee to table
              //incerment all necessary variables
              allTables.get(tableNum).incrSeatsTaken();
              allTables.get(tableNum).addAttendee(individual);
              Table.totalSeated++;
              individual.setTableNum(tableNum + 1);
              allCompanies.get(individual.getCompNum()-1).incrTotalNumAtt();
            } //close inner conditional
            //error handling
            else {
              System.out.println("No table is available for " + individual.getFirstName() + " " + individual.getLastName() + "\n");
            }
          }
          //error handling  
          else {
            System.out.println("The event is full! We cannot register you! \n");
          }
        }
        //error handling  
        else {
          System.out.println("Company already has max attendees! We cannot register " + individual.getFirstName() + " " + individual.getLastName() + "\n");
        }
      }
      //error handling  
      else {
        System.out.println(individual.getFirstName() + " " + individual.getLastName() + "'s company does not exist.");
      }
    } //close for each 
    
    //print number of attendees seated
    System.out.println("Total number of seated attendees: " + Table.totalSeated);

    //print roster for all the companies
    printCompanyRoster();

    //print roster for all the tables
    printTableRoster();
    
    //ask to register new attendees
    System.out.print("Do you want to register additional attendees? (y/n) ");
    yOrN = scan.nextLine();    
    if (yOrN.equals("y")) {
      System.out.print("How many attendees would you like to register? ");
      int newAtt = scan.nextInt();
      registerNewAtt(newAtt);
      //print table and company rosters again
      printTableRoster();  
      printCompanyRoster();      
    }

    //prompt to find attendee
    System.out.println("\nDo you want to search an attendee? (y/n) ");
    yOrN = scan.nextLine();    
    if (yOrN.equals("y")) {    
      searchAttendee();
    }
    
  } //close main

  /*
  add Attendee method...takes in whole name and company number to add attendee
  */
  public static void addAttendee(String First, String Last, int CompNumb) {
    Attendee a1 = new Attendee(First, Last, CompNumb);
    
  } //close addAttendee

  /*
  check if company exists method
  */
  public static boolean checkCompExists(int compNum) {
    for (Company c1: allCompanies) {
      if (c1.getCompanyNum() == compNum) {
        return true;
      }
    }
    return false;
  } //close checkCompExists

  /*
  method to check that company can have more attendees
  */    
  public static boolean checkNumAttInCompany(int compNum) {
    if (allCompanies.get(compNum-1).getTotalNumAtt() < maxAttInCompany) {
      return true;
    }
    return false;
  } //close checkNumAttInCompany

  /*
  method checking that event hasn't reached capacity
  */  
  public static boolean checkTotalAtt() {
    if (Table.totalSeated < eventCapacity) {
      return true;
    }
    return false;
  } //close checkTotalAtt

  /*
  method to find the table available
  */
  public static int findTableAvailable(int compNum) {
    for (int t = 0; t < maxTables; t++) {
      if (allTables.get(t).getSeatsTaken() < maxSeatsAtTable) { 
        //checks if seats are available at that table
        // check each seated attendee's company at this table
        boolean available = true;
        for (int s = 0; (s < allTables.get(t).getSeatsTaken()) && (available == true); s++) {
          if (allTables.get(t).getAttSeated().get(s).getCompNum() == compNum) {
            available = false; //check that someone from same company isn't already seated at table
          }
        } //close inner for loop
        if (allTables.get(t).getSeatsTaken() == 0) //if table is empty
          available = true;
        if (available == true) {
          return t;
        }
      } //close outer conditional
    } //close outer for loop
    return -1; //indicates no table is available for this company
  }  // end findTableAvailable  
  
  /*
  method to print rosters by company
  */
  public static void printCompanyRoster() {
    int compNum; 
    System.out.println("\nCompany Roster: ");
    //go through all companies
    for (Company c1: allCompanies) { 
       System.out.println("Company Name: " + c1.getCompanyName() + "\t Total Attendees: " + c1.getTotalNumAtt());
       compNum = c1.getCompanyNum();
      for (Table t1: allTables) {
        for (Attendee a1: t1.getAttSeated()) {
          //check which attendee is from the company
          if (a1.getCompNum() == compNum) {
            System.out.println("\t " + a1.getFirstName() + " " + a1.getLastName() + " at table " + a1.getTableNum());
          } //close inner conditional
        } 
      } //close inner for each
    } //close company for each       
  } // end printCompanyRoster
  
  /*
  method to print rosters of tables
  */
  public static void printTableRoster() {
    //print roster for all the tables
    System.out.println("\nRosters of all tables");    
    //go through all tables
    for (int i = 0; i < maxTables; i++) {
      System.out.print("Table number: " + (i+1));
      System.out.println("\t Seats occupied: " + allTables.get(i).getSeatsTaken());
      //print each attendee at the table
      for (Attendee guest: allTables.get(i).getAttSeated()) {
        System.out.println("\t Seat " + (allTables.get(i).getAttSeated().indexOf(guest) + 1) + " " + guest.getFirstName() + " " + guest.getLastName() + " from " + allCompanies.get(guest.getCompNum() - 1).getCompanyName());
      } //close inner for each  
    } //close for loop    

  } //close printTableRoster

  /*
  method to register new attendee
  */
  public static void registerNewAtt(int numNewAtt) {
    int compNum = 0;
    //run loop for each new attendee
    for (int i = 0; i < numNewAtt; i++) {
      if (checkTotalAtt() == true) {
        System.out.println("What's your first name? ");
        String firstName = scan.next();
        System.out.println("What is your last name? ");
        String lastName = scan.next();
        System.out.println("What is the name of your company? ");
        String companyName = scan.next();
        //go through all companies
        for (Company c1: allCompanies) {
          if (c1.getCompanyName().equals(companyName)) { //equivalent to company exists...just checking with the name instead of compNum
            compNum = c1.getCompanyNum();
          }
        } //end for each loop
        if (compNum == 0) {
          System.out.println("Company not found! \n");
        }
        else { // company found
          if (checkNumAttInCompany(compNum) == true) {
            int tableNum = findTableAvailable(compNum);
            if (tableNum != -1) {        
              // add attendee to table
              //increment all necessary variables
              Attendee individual = new Attendee(firstName, lastName, compNum);
              allTables.get(tableNum).incrSeatsTaken();
              allTables.get(tableNum).addAttendee(individual);
              Table.totalSeated++;
              individual.setTableNum(tableNum + 1);
        allCompanies.get(individual.getCompNum()-1).incrTotalNumAtt();
            } //close inner conditional 
            //error handling
            else {
              System.out.println("No table is available! \n");
            }
          } //close large conditional
          //error handling
          else {
            System.out.println("Company already has max attendees! We cannot register you! \n");
          }
        } //end else
      }
      //error handling
      else {
        System.out.println("The event is full! We cannot register you! \n");
      }
    }
  } //end registerNewAtt method

  /*
  method to search for an Attendee
  */
  public static void searchAttendee() {
    System.out.println("What's the attendee's first name? ");
    String firstName = scan.next();
    System.out.println("What's the attendee's last name? ");
    String lastName = scan.next(); 
    boolean found = false;
    //go through all tables
    for (Table t1: allTables) {
      //go through attendees seated
      for (Attendee a1: t1.getAttSeated()) {
        if ((a1.getFirstName().equals(firstName)) && ((a1.getLastName().equals(lastName)))) {
          found = true;
          System.out.println(a1.getFirstName() + " " + a1.getLastName() + " is at table " + a1.getTableNum() + " seat " + (t1.getAttSeated().indexOf(a1) + 1));
        }
      }
    }
    if (found == false) {
      System.out.println("Attendee not found! \n");
    }
    
  } //end of searchAttendee

  
} //end class