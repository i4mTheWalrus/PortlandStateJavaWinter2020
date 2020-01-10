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
  String flightNumber;
  /**
   * 3 letter source airport code.
   */
  String src;
  /**
   * Date and time of flight departure. xx/xx/xxxx ##:##.
   * Will change to use Date class later
   */
  String depart;
  /**
   * 3 letter destination airport code.
   */
  String dest;
  /**
   * Date and time of flight arrival. xx/xx/xxxx ##:##.
   * Will change to use Date class later
   */
  String arrive;

  @Override
  public int getNumber() {
    return 42;
  }

  @Override
  public String getSource() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDepartureString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDestination() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getArrivalString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }
}
