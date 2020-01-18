package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractAirline;

import java.io.*;
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
    if(args.length > 10) {
      System.err.println("Too many command line arguments");
      System.exit(1);
    }

    // If -readme is detected anywhere as an argument, print the readme.
    for(String i : args) {
      if(i.toLowerCase().contains("-readme")) {
        System.out.println("About project 1:\n" +
                "Consists of a single class, Flight, which is used to represent a flight to and from an airport by a specified airline.\n" +
                "The flight is currently not stored in any persistent capacity, and is simply created using constructors from command\n" +
                "line arguments. If the -print option is given, the flight information based on the given arguments are printed to the console.");
      }
      System.exit(0);
    }

    /* IF THIS POINT IS REACHED, NO -README HAS BEEN SPECIFIED */



    // If -print is specified, print the flight info to the console

    // No options given, create the flight and do nothing
    if(args.length == 8 && !args[0].toLowerCase().contains("-print") && !args[1].toLowerCase().contains("-print")) {
      Flight flight2 = new Flight(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
    }

  }

}