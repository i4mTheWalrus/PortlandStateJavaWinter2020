package edu.pdx.cs410J.race3;

import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Airline} class.
 */
public class AirlineTest {
  Airline airline1 = new Airline();
  Flight constructedFlight = new Flight("Delta", "3021", "PDX", "11/15/1986", "13:15", "BOI", "11/16/1986", "15:17");

  /**
   * Test that a flight can be added to an airline's collection.
   */
  @Test
  public void testAddingFlightToAirline() {
    airline1.addFlight(constructedFlight);
  }

  /**
   * Test that retrieving the first flight from an airline's collection matches expected toString value.
   */
  @Test
  public void testGetFlightsCollectionFromAirline() {
    airline1.addFlight(constructedFlight);
    Collection<Flight> flights;
    flights = airline1.getFlights();
    Flight first = flights.iterator().next();
    //System.out.println(first.toString());
    assertThat(first.toString(), equalTo("Flight 3021 departs PDX at 11/15/1986 13:15 arrives BOI at 11/16/1986 15:17"));
  }
}
