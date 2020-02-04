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

  Date departTime;

  /**
   * 3 letter destination airport code.
   */
  String dest;

  Date arriveTime;

  /**
   * Default constructor. Does nothing.
   */
  Flight() {}

  /**
   * Parameterized constructor. Build the flight with specified info.
   * @param airline String containing name of airline.
   * @param flightNumber String of flight number. Numerical only.
   * @param src String of source airport. Must be 3 letters.
   * @param departTime Date type containing departure date and time.
   * @param dest String of destination airport. Must be 3 letters.
   * @param arriveTime Date type containing arrival date and time.
   */
  Flight(String airline, String flightNumber, String src, Date departTime, String dest, Date arriveTime) {
    this.airline = airline;

    this.arriveTime = arriveTime;

    // src should be 3 characters! (no numbers or special characters)
    if(dest.length() != 3 || Pattern.compile("[^a-zA-Z]").matcher(dest).find()) {
      throw new IllegalArgumentException("Airport code is not a 3 character letter-only code.");
    }
    this.dest = dest;

    this.departTime = departTime;

    // src should be 3 characters! (no numbers or special characters)
    if(src.length() != 3 || Pattern.compile("[^a-zA-Z]").matcher(src).find()) {
      throw new IllegalArgumentException("Airport code is not a 3 character letter-only code.");
    }
    this.src = src;

    // flight number should be integer
    if(!flightNumber.matches("([0-9]+)")) {
      throw new IllegalArgumentException("Flight number should be integer");
    }
    this.flightNumber = flightNumber;
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

  @Override
  public Date getArrival() {
    return arriveTime;
  }

  @Override
  public Date getDeparture() {
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
    return departTime.toString();
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
    return arriveTime.toString();
  }

  /**
   * Format a flight for output made for text file (CSV).
   * @return String value of line to go into file
   */
  public String getTextFileString() {
    return airline + "," + flightNumber + "," + src + "," + departTime.toString() + "," +
        dest + "," + arriveTime.toString() + '\n';
  }
}
