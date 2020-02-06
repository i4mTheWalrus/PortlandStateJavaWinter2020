package edu.pdx.cs410J.race3;

import org.junit.Test;

public class SortFlightsTest {
  @Test
  public void testSort() {
    Airline airline = new Airline();
    Flight flight = new Flight("Delta", "123", "PDX", "4/2/2001", "4:23", "am", "ORD", "5/2/2002", "3:43", "pm");
    airline.addFlight(flight);
    flight = new Flight("Delta", "123", "BOI", "4/2/2001", "4:23", "am", "ORD", "5/2/2002", "3:43", "pm");
    airline.addFlight(flight);
  }
}
