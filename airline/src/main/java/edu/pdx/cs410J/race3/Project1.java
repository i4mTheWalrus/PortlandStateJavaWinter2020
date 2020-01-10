package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractAirline;

/**
 * The main class for the CS410J airline Project
 */
public class Project1 {

  public static void main(String[] args) {
    Flight flight = new Flight();  // Refer to one of Dave's classes so that we can be sure it is on the classpath

    // Exit with error if no command line arguments are given
    if(args.length == 0) {
      System.err.println("Missing command line arguments");
      System.exit(1);
    }
/*
    // Check if there is -print in first or second argument
    if(args[0].toLowerCase() == "-print" || args[1].toLowerCase() == "-print") {

    }

    // Check if there is -README in the first or second argument
    if(args[0].toLowerCase() == "-readme" || args[1].toLowerCase() == "-readme") {

    }
*/
    // After options there should be: airline, flight #, src, depart date/time, dest, arrive date/time

    // If -print is specified, print the flight info to the console
  }

}