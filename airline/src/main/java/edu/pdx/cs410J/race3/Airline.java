package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Airline extends AbstractAirline {
  /**
   * Name of the airline.
   */
  String airlineName;

  /**
   * Holds the collection of flights.
   */
  ArrayList<Flight> flights;

  /**
   * Default constructor, does nothing special.
   */
  Airline() {};

  /**
   * Parameterized constructor. Takes a string a sets that as the airline's name.
   * @param name
   */
  Airline(String name) {
    flights = new ArrayList<Flight>();
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
    return null;
  }
}
