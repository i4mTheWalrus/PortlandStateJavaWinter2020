package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.ParserException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

/**
 * The main class for the CS410J airline Project
 */
public class Project4 {
  static boolean readmeFlag = false;
  static boolean printFlag = false;
  static boolean searchFlag = false;
  static String searchSrc;
  static String searchDest;
  static int maxOptionCount = 7;

  /**
   * Entry point of the program.
   * @param args Command line arguments
  */
  public static void main(String[] args) {
    int argCount = args.length;

    // Check for not no args, not enough args, or too many args.
    // Arg count should be between 8 and 11
    findOptions(args);
    checkArgCount(args);

    if(readmeFlag) {
      printReadme();
      System.exit(0);
    }

    try {
      Flight flight = new Flight(args[argCount - 10], args[argCount - 9], args[argCount - 8], args[argCount - 7], args[argCount - 6], args[argCount - 5], args[argCount - 4], args[argCount - 3], args[argCount - 2], args[argCount - 1]);
      Airline airline = new Airline();
      airline.setAirlineName(args[argCount - 10]);

      if(printFlag) {
        System.out.println(flight);
      }
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
      System.exit(1);
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
    if(args.length < 8 && !readmeFlag) {
      System.err.println("Not enough arguments given.");
      System.exit(1);
    }

    // Exit with error if too many command line arguments are given.
    // Readme is not present, so there should be no more than 9 args (1 for print, 8 for flight)
    if(args.length > 8 + maxOptionCount) {
      System.err.println("Too many command line arguments.");
      System.exit(1);
    }
  }

  /**
   * Prints the readme to standard out.
   */
  public static void printReadme() {
    System.out.println("About project 3:\n" +
        "Usage: cmdLineExec [options] <flight args>\n" +
        "This project extends project 2. Date class has been implemented for departures and arrivals, flights are sorted by departure airport/time, and a pretty printer has been added to show the flights in a more readable form.\n" +
        "Command line options: -print prints the flight given on the command line\n" +
        "                      -readme prints this readme\n" +
        "                      -textFile [filename] Specifies the title of a text file to load/save with. (should not include file extension)\n" +
        "                      -pretty [filename] Specifies a file to pretty print to. Use \"-\" to specify printing to console window.\n" +
        "The overall purpose is to represent an airline that consists of flights to and from various airports.");
  }

  /**
   * Searches the beginning of the command line arguments for any specified options
   * @param args Command line arguments.
   */
  public static void findOptions(String[] args) {
    // If -readme is detected anywhere as an argument, print the readme and exit.
    for(String i : args) {
      if (i.contains("-README")) {
        readmeFlag = true;
        break;
      }
    }

    // If the -print flag is specified, set print flag
    for(int i = 0; i < maxOptionCount && i < args.length; i++) {
      if (args[i].contains("-print")) {
        printFlag = true;
        break;
      }
    }

    // If the -search flag is specified, set print flag
    for (int i = 0; i < maxOptionCount && i < args.length; i++) {
      if (args[i].contains("-search")) {
        searchFlag = true;
        searchSrc = args[i + 1];
        searchDest = args[i + 2];
        break;
      }
    }
  }
}