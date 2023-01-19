import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*; // import for ArrayList

public class PartyPlanner {
  public static ArrayList<Company> allCompanies = new ArrayList<Company>();
  public static ArrayList<Attendee> attendeeList = new ArrayList<Attendee>();
  public static ArrayList<Table> allTables = new ArrayList<Table>();   public static int maxTables; //static to refer in methods...declaring max number of tables
  public static int maxAttInCompany; //static to refer in methods...declaring max number of attendees for one company
  public static int maxSeatsAtTable = 10; //static to refer in methods...declaring max number of seats for one table  
  public static Scanner scan = new Scanner(System.in); //initializing scanner  
  
  public static void main( String[] args) {

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

    //initialize all tables and number of tables
    System.out.println("How many total tables do you want at the event?");
    maxTables = scan.nextInt();

    for (int i = 0; i < maxTables; i++) {
      allTables.add(new Table(i+1));
    }

    //get max number of attendees for one company
    System.out.println("What is the max number of attendees for one company?");
    maxAttInCompany = scan.nextInt();
    
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
    } 
    catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
    
    //going through checks to seat attendee
    for (Attendee individual: attendeeList) {
      System.out.println(individual.getLastName());
      if (checkCompExists(individual.getCompNum()) == true) {
        System.out.println("Valid Company");      
        if (checkNumAttInCompany(individual.getCompNum()) == true) {
          System.out.println("Less than 10 ppl from company");         
          if (checkTotalAtt() == true) {
            System.out.println("Less than a 100, company number: " + individual.getCompNum());         
            int tableNum = findTableAvailable(individual.getCompNum());
            if (tableNum != -1) {
               System.out.println("Found a table");        
              // add attendee to table
              allTables.get(tableNum).incrSeatsTaken();
              allTables.get(tableNum).addAttendee(individual);
              Table.totalSeated++;
              individual.setTableNum(tableNum);
              allCompanies.get(individual.getCompNum()-1).incrTotalNumAtt();
            }
          }
        }
      }
      else {
        System.out.println(individual.getFirstName() + individual.getLastName() + "'s company does not exist.");
      }
    }
    
    //print number of attendees in attendee list
    System.out.println("Total attendees: " + attendeeList.size());
    System.out.println("Total companies: " + allCompanies.size());       System.out.println("Total tables: " + allTables.size());
    
    System.out.println("Total number of seated attendees: " + Table.totalSeated);

    //print roster for all the companies
    System.out.println("Rosters of all companies");
    for (int i = 0; i < 16; i++) {
          printCompanyRoster(i);
    }    
        
    //print roster for all the tables
    System.out.println("Rosters of all tables");
    for (int i = 0; i < maxTables; i++) {
          printTableRoster(i);
    }
    
  } //close main

  public static void addAttendee(String First, String Last, int CompNumb) {
    Attendee a1 = new Attendee(First, Last, CompNumb);
    
  }

  public static boolean checkCompExists(int compNum) {
    for (Company c1: allCompanies) {
      if (c1.getCompanyNum() == compNum) {
        return true;
      }
    }
    return false;
  }

  public static boolean checkNumAttInCompany(int compNum) {
    if (allCompanies.get(compNum-1).getTotalNumAtt() < maxAttInCompany) {
      return true;
    }
    return false;
  }

  public static boolean checkTotalAtt() {
    if (Table.totalSeated < 100) {
      return true;
    }
    return false;
  }

  public static int findTableAvailable(int compNum) {
    System.out.println("Max Tables: " + maxTables);
    for (int t = 0; t < maxTables; t++) {
      if (allTables.get(t).getSeatsTaken() < maxSeatsAtTable) { 
        System.out.println("Less than 10 seats taken " + t);
        System.out.println("seats taken " + allTables.get(t).getSeatsTaken());
        //checks if seats are available at that table
        // check each seated attendee's company at this table
        boolean available = true;
        for (int s = 0; (s < allTables.get(t).getSeatsTaken()) && (available == true); s++) {
                  System.out.println("Going through seats: " + s);
          if (allTables.get(t).getAttSeated().get(s).getCompNum() == compNum) {
                            System.out.println("found same company");
            available = false;
                  }
        }
        if (allTables.get(t).getSeatsTaken() == 0)
          available = true;
        if (available == true) {
                  System.out.println("found seat");
          return t;
        }
      }
    }
    return -1; //indicates no table is available for this company
  }  // end findTableAvailable  
  
  //print rosters by company
  public static void printCompanyRoster(int compNum) {
    System.out.println("Attendees from Company " + allCompanies.get(compNum).getCompanyName());

    
  }
  
  //print rosters by table
  public static void printTableRoster(int tableNum) {
    System.out.println("Table number: " + (tableNum+1));
    System.out.println("The number of seats occupied: " + allTables.get(tableNum).getSeatsTaken());
    for (Attendee guest: allTables.get(tableNum).getAttSeated()) {
      System.out.println("The attendees seated at the table: " + guest.getFirstName() + guest.getLastName() + "from company " + guest.getCompNum());
    }  
  }

  
} //end class