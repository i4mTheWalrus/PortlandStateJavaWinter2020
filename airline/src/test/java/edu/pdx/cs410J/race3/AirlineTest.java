package edu.pdx.cs410J.race3;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AirlineTest {
  Airline airline1 = new Airline();
  Flight constructedFlight = new Flight("Delta", "3021", "PDX", "11/15/1986", "13:15", "BOI", "11/16/1986", "15:17");

  @Test
  public void testAddingFlightToAirline() {
    airline1.addFlight(constructedFlight);
  }


}
