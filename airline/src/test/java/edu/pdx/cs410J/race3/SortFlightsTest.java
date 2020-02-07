package edu.pdx.cs410J.race3;

import org.junit.Test;

/**
 * Class built to test the sorting functionality of the SortFlights class.
 */
public class SortFlightsTest {

  /**
   * Method that simply ass 2 flights to an airline. Checking if sorted should be done manually.
   */
  @Test
  public void testSort() {
    Airline airline = new Airline();
    Flight flight = new Flight("Delta", "123", "PDX", "4/2/2001", "4:23", "am", "ORD", "5/2/2002", "3:43", "pm");
    airline.addFlight(flight);
    flight = new Flight("Delta", "123", "BOI", "4/2/2001", "4:23", "am", "ORD", "5/2/2002", "3:43", "pm");
    airline.addFlight(flight);
  }
}
