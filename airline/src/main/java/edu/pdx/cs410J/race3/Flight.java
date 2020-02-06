package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirportNames;
import edu.pdx.cs410J.ParserException;

import javax.print.attribute.DateTimeSyntax;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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

  String arriveAP;

  String departAP;

  Date departure;

  Date arrival;

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

    // Time should be in ##:## format
    if(!arriveTime.matches("([0-9]{2}):([0-9]{2})")) {
      throw new IllegalArgumentException("Arrive time is not in correct format. (##:##)");
    }
    this.arriveTime = arriveTime;

    // Date should be in ##/##/#### format
    if(!arriveDate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
      throw new IllegalArgumentException("Arrive date is not in correct format. (##/##/####)");
    }
    this.arriveDate = arriveDate;

    // src should be 3 characters! (no numbers or special characters)
    if(dest.length() != 3 || Pattern.compile("[^a-zA-Z]").matcher(dest).find()) {
      throw new IllegalArgumentException("Airport code is not a 3 character letter-only code.");
    }
    this.dest = dest;

    // Time should be in ##:## format
    if(!departTime.matches("([0-9]{2}):([0-9]{2})")) {
      throw new IllegalArgumentException("Depart time is not in correct format. (##:##)");
    }
    this.departTime = departTime;

    // Date should be in ##/##/#### format
    if(!departDate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
      throw new IllegalArgumentException("Depart date is not in correct format. (##/##/####)");
    }
    this.departDate = departDate;

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
   * Parameterized constructor. Build the flight with specified info.
   * @param airline String containing name of airline.
   * @param flightNumber String of flight number. Numerical only.
   * @param src String of source airport. Must be 3 letters.
   * @param departDate String of flight departure date.
   * @param departTime String of flight departure time.
   * @param departAmPm String containing either am or pm.
   * @param dest String of destination airport. Must be 3 letters.
   * @param arriveDate String of flight arrival date.
   * @param arriveTime String of flight arrival time.
   * @param arriveAmPm String containing either am or pm.
   */
  Flight(String airline, String flightNumber, String src, String departDate, String departTime, String departAmPm, String dest, String arriveDate, String arriveTime, String arriveAmPm) {
    this.airline = airline;
    this.arriveAP = arriveAmPm;
    this.departAP = departAmPm;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm a", Locale.getDefault());

    if(!arriveTime.matches("((1[012]|[1-9]):[0-5][0-9])")) {
      throw new IllegalArgumentException("Arrive time is not in correct format. (##:##)");
    }
    this.arriveTime = arriveTime;

    if(!arriveDate.matches("((0[1-9]|1[012])|([1-9]|1[012]))/((0[1-9]|[12][0-9]|3[01])|([1-9]|[12][0-9]|3[01]))/((19|2[0-9])[0-9]{2})")) {
      throw new IllegalArgumentException("Arrive date is not in correct format. (##/##/####)");
    }
    this.arriveDate = arriveDate;

    // arriveAmPm should be either 'am' or 'pm'
    if(!(arriveAmPm.toLowerCase().equals("am") || arriveAmPm.toLowerCase().equals("pm"))) {
      throw new IllegalArgumentException("Arrival time should include an am or pm");
    }

    // Try to build arrival date
    try {
      arrival = sdf.parse(arriveDate + " " + arriveTime + " " + arriveAmPm);
    } catch (ParseException e) {
      e.getStackTrace();
    }

    // src should be 3 characters! (no numbers or special characters)
    if(dest.length() != 3 || Pattern.compile("[^a-zA-Z]").matcher(dest).find()) {
      throw new IllegalArgumentException("Airport code is not a 3 character letter-only code.");
    }
    this.dest = dest;

    if(!departTime.matches("((1[012]|[1-9]):[0-5][0-9])")) {
      throw new IllegalArgumentException("Depart time is not in correct format. (##:##)");
    }
    this.departTime = departTime;

    if(!departDate.matches("((0[1-9]|1[012])|([1-9]|1[012]))/((0[1-9]|[12][0-9]|3[01])|([1-9]|[12][0-9]|3[01]))/((19|2[0-9])[0-9]{2})")) {
      throw new IllegalArgumentException("Depart date is not in correct format. (##/##/####)");
    }
    this.departDate = departDate;

    // departAmPm should be either 'am' or 'pm'
    if(!(departAmPm.toLowerCase().equals("am") || departAmPm.toLowerCase().equals("pm"))) {
      throw new IllegalArgumentException("Departure time should include an am or pm");
    }

    // Try to build depart date
    try {
      departure = sdf.parse(departDate + " " + departTime + " " + departAmPm);
    } catch (ParseException e) {
      e.getStackTrace();
    }

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
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy mm:ss a", Locale.getDefault());
    return sdf.format(departure);
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
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy mm:ss a", Locale.getDefault());
    return sdf.format(arrival);
  }

  /**
   * Format a flight for output made for text file (CSV).
   * @return String value of line to go into file
   */
  public String getTextFileString() {
    return airline + "," + flightNumber + "," + src + "," + departDate + "," + departTime + "," + departAP + "," +
        dest + "," + arriveDate + "," + arriveTime + "," + arriveAP + '\n';
  }

  @Override
  public Date getDeparture() {
    return departure;
  }

  @Override
  public Date getArrival() {
    return arrival;
  }

  public String getPrettyPrintString() throws ParseException {
    String srcName = AirportNames.getName(src);
    String destName = AirportNames.getName(dest);
    return airline + " flight " + flightNumber + " departs " + srcName + " at " + departure + " and arrives at " + destName + " at " + arrival + " for a total flight time of " + getFlightDuration() + " minutes\n";
  }

  private long getFlightDuration() throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    Date d = format.parse(departTime);
    Date a = format.parse(arriveTime);
    long difference = d.getTime() - a.getTime();
    // Return seconds instead of milliseconds
    return difference * 1000;
  }
/*
  @Override
  public int compareTo(Flight f) {

    return 0;
  }*/
}
