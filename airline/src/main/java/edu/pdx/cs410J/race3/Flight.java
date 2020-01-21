package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractFlight;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * This class is used to model a flight to and from an airport.
 * Flights are constructed using 6 arguments given on command line.
 * The flight is created using a constructor.
 * @author Tyler Race
 */
public class Flight extends AbstractFlight {
  /**
   * Holds the name of the airline. ex: Delta
   */
  String airline;

  /**
   * Flight number. Can contain both letters and numbers.
   */
  String flightNumber;

  /**
   * 3 letter source airport code.
   */
  String src;

  /**
   * Date and time of flight departure. xx/xx/xxxx ##:##.
   * Will change to use Date class later
   */
  String departDate, departTime;

  /**
   * 3 letter destination airport code.
   */
  String dest;

  /**
   * Date and time of flight arrival. xx/xx/xxxx ##:##.
   * Will change to use Date class later
   */
  String arriveDate, arriveTime;

  /**
   * Counts the number of options. 0, 1, or 2.
   * Available options are -README and -print
   */
  int optionNum;

  /**
   * Default constructor. Does nothing.
   */
  Flight() {}

  /**
   * Parameterized constructor. Build the flight with specified info.
   * @param airline
   * @param flightNumber
   * @param src
   * @param departDate
   * @param departTime
   * @param dest
   * @param arriveDate
   * @param arriveTime
   */
  Flight(String airline, String flightNumber, String src, String departDate, String departTime, String dest, String arriveDate, String arriveTime) {
    this.airline = airline;
    this.flightNumber = flightNumber;

    // src should be 3 characters! (no numbers or special characters)
    if(src.length() != 3 || Pattern.compile("[^a-zA-Z]").matcher(src).find()) {
      System.err.println("Airport code is not a 3 character letter-only code.");
      System.exit(1);
    }
    this.src = src;

    this.departDate = departDate;
    this.departTime = departTime;

    // src should be 3 characters! (no numbers or special characters)
    if(dest.length() != 3 || Pattern.compile("[^a-zA-Z]").matcher(dest).find()) {
      System.err.println("Airport code is not a 3 character letter-only code.");
      System.exit(1);
    }
    this.dest = dest;

    this.arriveDate = arriveDate;
    this.arriveTime = arriveTime;
  }

  /**
   * Method for obtaining the flight number for the flight.
   * @return The flight number, or the number 42 if none is specified.
   */
  @Override
  public int getNumber() {
    if(flightNumber == null)
      return 42;
    else
      return Integer.parseInt(flightNumber);
  }

  /**
   * Method for getting the arrival time of a flight.
   * @return A string containing arrival time.
   */
  public String getArriveTime() {
    return arriveTime;
  }

  /**
   * Method for getting the departure time of a flight.
   * @return A string containing arrival time.
   */
  public String getDepartureTime() {
    return departTime;
  }

  /**
   * Method for getting the source airport code of a flight.
   * @return A string containing the source airport code.
   */
  @Override
  public String getSource() {
    return this.src;
  }

  /**
   * Method for building a string containing both the departure date and time of a flight.
   * @return A string containing departure date and departure time separated by a space.
   */
  @Override
  public String getDepartureString() {
    return this.departDate + " " + this.departTime;
  }

  /**
   * Method for getting the destination airport code of a flight.
   * @return A string containing the destination airport code.
   */
  @Override
  public String getDestination() {
    return this.dest;
  }

  /**
   * Method for building a string containing both the arrival date and time of a flight.
   * @return A string containing arrival date and arrival time separated by a space.
   */
  @Override
  public String getArrivalString() {
    return this.arriveDate + " " + this.arriveTime;
  }
}
