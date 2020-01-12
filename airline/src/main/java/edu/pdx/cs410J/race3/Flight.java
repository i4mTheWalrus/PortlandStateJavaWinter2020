package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractFlight;
import java.util.Date;

/**
 * This class is used to model a flight to and from an airport.
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
  int flightNumber;

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
   * Default constructor
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
  Flight(String airline, int flightNumber, String src, String departDate, String departTime, String dest, String arriveDate, String arriveTime) {
    this.airline = airline;
    this.flightNumber = flightNumber;
    this.src = src;
    this.departDate = departDate;
    this.departTime = departTime;
    this.dest = dest;
    this.arriveDate = arriveDate;
    this.arriveTime = arriveTime;
  }

  @Override
  public int getNumber() {
    if(flightNumber == 0)
      return 42;
    else
      return flightNumber;
  }

  public String getArriveTime() {
    return arriveTime;
  }

  public String getDepartureTime() {
    return departTime;
  }

  @Override
  public String getSource() {
    return this.src;
  }

  @Override
  public String getDepartureString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDestination() {
    return this.dest;
  }

  @Override
  public String getArrivalString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }
}
