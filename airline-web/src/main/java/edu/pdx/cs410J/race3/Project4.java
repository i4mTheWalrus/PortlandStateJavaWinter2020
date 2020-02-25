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
  /**
   * Define the number of options that can come before flight arguments.
   */
  static int maxOptionCount = 8;

  /**
  * Flag option for printing the flight given via the command line.
  */
  static boolean printFlag = false;

  /**
  * Flag option to print the readme.
  */
  static boolean readmeFlag = false;

  /**
   * Flag option to specify a file to read or write from.
   */
  static boolean textFileFlag = false;

  /**
   * String used to store file name for command line argument.
   */
  static String fileName;

  /**
   * Flag option to specify if the program should pretty print somewhere.
   */
  static boolean prettyFlag = false;

  /**
   * Path for pretty print destination. '-' specifies a print to std out.
   */
  static String prettyFile;

  static boolean xmlFlag = false;

  static String xmlFile;

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

    if(fileName != null && prettyFile != null) {
      if(fileName.equals(prettyFile)) {
        System.err.println("Filename given for -textFile and -pretty cannot be the same.");
        System.exit(1);
      }
    }

    try {
      Flight flight = new Flight(args[argCount - 10], args[argCount - 9], args[argCount - 8], args[argCount - 7], args[argCount - 6], args[argCount - 5], args[argCount - 4], args[argCount - 3], args[argCount - 2], args[argCount - 1]);
      Airline airline = new Airline();
      airline.setAirlineName(args[argCount - 10]);

      // Having both textFile flag and xmlFlag is an error
      if(textFileFlag && xmlFlag) {
        System.err.println("Cannot specify both -textFile and -xmlFile!");
        System.exit(1);
      }

      if(textFileFlag) {
        TextParser tp = new TextParser(fileName);
        airline = (Airline)tp.parse();
        airline.addFlight(flight);

        // Check that airline found in file is the same as the one on the command line
        Collection<Flight> flightsFromFile = airline.getFlights();
        for(Flight f : flightsFromFile) {
          if(!f.getAirlineName().equals(args[argCount-10])) {
            System.err.println("The airline name provided on command line does not match what is found in file!");
            System.exit(1);
          }
        }
        TextDumper td = new TextDumper(fileName);
        td.dump(airline);
      }
      if(xmlFlag) {
        XmlParser parser = new XmlParser(xmlFile);
        airline = (Airline)parser.parse();
        airline.setAirlineName(args[argCount - 10]);
        airline.addFlight(flight);

        // Check that airline found in file is the same as the one on the command line
        Collection<Flight> flightsFromFile = airline.getFlights();
        for (Flight f : flightsFromFile) {
          if (!f.getAirlineName().equals(args[argCount - 10])) {
            System.err.println("The airline name provided on command line does not match what is found in file!");
            System.exit(1);
          }
        }

        // DUMP TO XML
        XmlDumper dumper = new XmlDumper(xmlFile);
        dumper.dump(airline);
      }
      if(printFlag) {
        System.out.println(flight);
      }
      if(prettyFlag) {
        PrettyPrinter printer = new PrettyPrinter(prettyFile);
        printer.dump(airline);
      }
    } catch (IllegalArgumentException | ParserException | IOException | ParserConfigurationException | SAXException e) {
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

    // If the -textFile flag is specified, the following option is used as a file to read/write from
    for(int i = 0; i < maxOptionCount && i < args.length; i++) {
      if(args[i].contains("-textFile")) {
        textFileFlag = true;
        fileName = args[i+1];
        break;
      }
    }

    // Determine if pretty print option is given
    for(int i = 0; i < maxOptionCount && i < args.length; i++) {
      if(args[i].contains("-pretty")) {
        prettyFlag = true;
        prettyFile = args[i+1];
        break;
      }
    }

    // Determine if xmlfile option is given
    for(int i = 0; i < maxOptionCount && i < args.length; i++) {
      if(args[i].contains("xmlFile")) {
        xmlFlag = true;
        xmlFile = args[i+1];
        break;
      }
    }
  }
}