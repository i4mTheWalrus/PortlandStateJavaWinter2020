package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.Collection;

public class Airline extends AbstractAirline {
  /**
   * Name of the airline.
   */
  String airlineName;

  /**
   * Holds the collection of flights.
   */
  Collection<Flight> flights;

  /**
   * Default constructor, does nothing special.
   */
  Airline() {};

  /**
   * Parameterized constructor. Takes a string a sets that as the airline's name.
   * @param name
   */
  Airline(String name) {
    airlineName = name;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public void addFlight(AbstractFlight abstractFlight) {
    flights.add((Flight) abstractFlight);
  }

  @Override
  public Collection getFlights() {
    return flights;
  }
}
