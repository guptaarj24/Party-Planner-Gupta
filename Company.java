/**
@author: Arjun Gupta
@date: 1/20/2023
Company Class: This has the constructor for company as well as the attributes and methods. 
The attributes and methods allow PartyPlanner to handle a variety of data. This class stores the company number, company name and total number of attendees for the company. 
@param: takes the company number and company name
@return: getters can return the company number, company name, and total number of attendees for the company
*/
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*; // import for ArrayList

/*
Company class
*/
public class Company {
  private int companyNum; //companyNum starts at 1
  private String companyName;
  private int totalNumAtt;

  /*
  Company constructor
  */
  public Company(int companyNum, String companyName) {
    this.companyNum = companyNum;
    this.companyName = companyName;
    totalNumAtt = 0;
  }

  /*
  getter for company number
  */
  public int getCompanyNum() {
    return companyNum;
  } 

  /*
  getter for company name
  */  
  public String getCompanyName() {
    return companyName;
  } 

  /*
  getter for total number of attendees for the company
  */  
  public int getTotalNumAtt() {
    return totalNumAtt;
  }

  /*
  incerment total number of attendees for the company
  */  
  public void incrTotalNumAtt() {
    totalNumAtt++;
  }   
} //close Company class