package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractAirline;
import java.lang.*;

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

    // Exit with error if too many command line arguments are given
    if(args.length > 8) {
      System.err.println("Too many command line arguments");
      System.exit(1);
    }

    // Check that there are no duplicate options given
    if((args[0].toLowerCase().equals("-print") && args[1].toLowerCase().equals("-print")) ||
        (args[0].toLowerCase().equals("-readme") && args[1].toLowerCase().equals("-readme"))) {
      System.err.println("Duplicate option listed");
      System.exit(1);
    }

    // Check if there is -README in the first or second argument. If so, print the README and exit.
    if(args[0].toLowerCase().equals("-readme") || args[1].toLowerCase().equals("-readme")) {
      // print readme
      System.exit(0);
    }

    // Check if there is -print in first or second argument
    if(args[0].toLowerCase().equals("-print") || args[1].toLowerCase().equals("-print")) {

    }
    // After options there should be: airline, flight #, src, depart date/time, dest, arrive date/time

    // If -print is specified, print the flight info to the console
  }

}