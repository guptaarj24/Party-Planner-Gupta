import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*; // import for ArrayList

public class Company {
  private int companyNum;
  private String companyName;
  private int totalNumAtt;

  public Company(int companyNum, String companyName) {
    this.companyNum = companyNum;
    this.companyName = companyName;
    totalNumAtt = 0;
  }
  
  public int getCompanyNum() {
    return companyNum;
  } 
  public String getCompanyName() {
    return companyName;
  } 
  public int getTotalNumAtt() {
    return totalNumAtt;
  }
  public void incrTotalNumAtt() {
    totalNumAtt++;
  }   

  public void printCompanyroster(int compNum) {
    
  }
}