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
  Airline() {
    flights = new ArrayList<Flight>();
  };

  /**
   * Parameterized constructor. Takes a string a sets that as the airline's name.
   * @param name
   */
  Airline(String name) {
    flights = new ArrayList<Flight>();
    airlineName = name;
  }

  /**
   * Get the name of the airline.
   * @return A string containing the name of the airline.
   */
  @Override
  public String getName() {
    return airlineName;
  }

  /**
   * Add a flight to the collection of flights for an airline. Takes a flight as an argument.
   * @param abstractFlight
   */
  @Override
  public void addFlight(AbstractFlight abstractFlight) {
    flights.add((Flight) abstractFlight);
  }

  /**
   * Get the flights that belong to an airline.
   * @return A collection of flights.
   */
  @Override
  public Collection getFlights() {
    return null;
  }
}
