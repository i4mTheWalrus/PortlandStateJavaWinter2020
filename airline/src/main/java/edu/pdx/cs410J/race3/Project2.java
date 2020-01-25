package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.ParserException;

/**
 * The main class for the CS410J airline Project
 */
public class Project2 {
  public static void main(String[] args) {
    Flight flight = new Flight();  // Refer to one of Dave's classes so that we can be sure it is on the classpath

    // If -readme is detected anywhere as an argument, print the readme and exit.
    for(String i : args) {
      if(i.toLowerCase().contains("-readme")) {
        printReadme();
      }
    }

    // Check for not no args, not enough args, or too many args.
    // Arg count should be between 8 and 10
    checkArgCount(args);

    // There should be 8 cmd line arguments, and possibly 1 option for -print (no readme by this point)
    // Expected -print but it wasn't the first argument
    if(args.length == 9 && !args[0].toLowerCase().contains("-print")) {
      System.err.println("Nine arguments given, expected -print first but not found.");
      System.exit(1);
    } else if(args.length == 9 && args[0].toLowerCase().contains("-print")) {
      // print specified, create flight and print to console window
      Airline airline = new Airline(args[1]);
      try {
        Flight flight2 = new Flight(args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
        airline.addFlight(flight2);
        System.out.println(flight2);
      } catch (IllegalArgumentException e) {
        System.err.println(e.getMessage());
        System.exit(1);
      }
    } else if(args.length == 8 && !args[0].toLowerCase().contains("-print") && !args[1].toLowerCase().contains("-print")) {
      // No options given, create the flight and do nothing
      Airline airline = new Airline(args[0]);
      try {
        Flight flight2 = new Flight(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
        airline.addFlight(flight2);
      } catch (IllegalArgumentException e) {
        System.err.println(e.getMessage());
        System.exit(1);
      }
    }
  }

  /**
   * Check the count of command line arguments.
   * Precondition: Readme flag is not specified anywhere in args
   * @param args The array of args passed in from the command line.
   */
  public static void checkArgCount(String[] args) {
    // Exit with error if no command line arguments are given
    if(args.length == 0) {
      System.err.println("Missing command line arguments.");
      System.exit(1);
    }

    // Minimum arg count if not options specified
    if(args.length < 8) {
      System.err.println("Not enough arguments given.");
      System.exit(1);
    }

    // Exit with error if too many command line arguments are given.
    // Readme is not present, so there should be no more than 9 args (1 for print, 8 for flight)
    if(args.length > 10) {
      System.err.println("Too many command line arguments.");
      System.exit(1);
    }
  }

  public static void printReadme() {
    System.out.println("About project 1:\n" +
        "Consists of a single class, Flight, which is used to represent a flight to and from an airport by a specified airline.\n" +
        "The flight is currently not stored in any persistent capacity, and is simply created using constructors from command\n" +
        "line arguments. If the -print option is given, the flight information based on the given arguments are printed to the console.");
    System.exit(0);
  }
}