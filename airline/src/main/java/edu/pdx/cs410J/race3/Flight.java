package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractFlight;

import java.text.SimpleDateFormat;
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
   * Date of flight departure. xx/xx/xxxx
   */
  String departDate;

  /**
   * Time of flight departure. ##:##
   */
  String departTime;

  /**
   * 3 letter destination airport code.
   */
  String dest;

  /**
   * Date of flight arrival. xx/xx/xxxx
   */
  String arriveDate;

  /**
   * Time of flight arrival. ##:##
   */
  String arriveTime;

  /**
   * Default constructor. Does nothing.
   */
  Flight() {}

  /**
   * Parameterized constructor. Build the flight with specified info.
   * @param airline String containing name of airline.
   * @param flightNumber String of flight number. Numerical only.
   * @param src String of source airport. Must be 3 letters.
   * @param departDate String of flight departure date.
   * @param departTime String of flight departure time.
   * @param dest String of destination airport. Must be 3 letters.
   * @param arriveDate String of flight arrival date.
   * @param arriveTime String of flight arrival time.
   */
  Flight(String airline, String flightNumber, String src, String departDate, String departTime, String dest, String arriveDate, String arriveTime) {
    this.airline = airline;
    this.flightNumber = flightNumber;

    // src should be 3 characters! (no numbers or special characters)
    if(src.length() != 3 || Pattern.compile("[^a-zA-Z]").matcher(src).find()) {
      throw new IllegalArgumentException("Airport code is not a 3 character letter-only code.");
    }
    this.src = src;

    // Date should be in ##/##/#### format
    if(!departDate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
      throw new IllegalArgumentException("Depart date is not in correct format. (##/##/####)");
    }
    this.departDate = departDate;

    // Time should be in ##:## format
    if(!departTime.matches("([0-9]{2}):([0-9]{2})")) {
      throw new IllegalArgumentException("Depart time is not in correct format. (##:##)");
    }
    this.departTime = departTime;

    // src should be 3 characters! (no numbers or special characters)
    if(dest.length() != 3 || Pattern.compile("[^a-zA-Z]").matcher(dest).find()) {
      throw new IllegalArgumentException("Airport code is not a 3 character letter-only code.");
    }
    this.dest = dest;

    // Date should be in ##/##/#### format
    if(!arriveDate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
      throw new IllegalArgumentException("Arrive date is not in correct format. (##/##/####)");
    }
    this.arriveDate = arriveDate;

    // Time should be in ##:## format
    if(!arriveTime.matches("([0-9]{2}):([0-9]{2})")) {
      throw new IllegalArgumentException("Arrive time is not in correct format. (##:##)");
    }
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
   * Method to return the name of the airline that the flight belongs to.
   * @return String containing airline name.
   */
  public String getAirlineName() {
    return airline;
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

  /**
   * Format a flight for output made for text file (CSV).
   * @return String value of line to go into file
   */
  public String getTextFileString() {
    return airline + "," + flightNumber + "," + src + "," + departDate + "," + departTime + "," +
        dest + "," + arriveDate + "," + arriveTime;
  }
}
