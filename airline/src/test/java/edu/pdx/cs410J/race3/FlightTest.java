package edu.pdx.cs410J.race3;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Flight} class.
 */
public class FlightTest {
  
  @Test(expected = UnsupportedOperationException.class)
  public void getArrivalStringNeedsToBeImplemented() {
    Flight flight = new Flight();
    flight.getArrivalString();
  }

  @Test
  public void initiallyAllFlightsHaveTheSameNumber() {
    Flight flight = new Flight();
    assertThat(flight.getNumber(), equalTo(42));
  }

  @Test
  public void forProject1ItIsOkayIfGetDepartureTimeReturnsNull() {
    Flight flight = new Flight();
    assertThat(flight.getDeparture(), is(nullValue()));
  }

  @Test
  public void testParameterizedConstructorExceptDepartureAndArrival() {
    var flight = new Flight("Delta", 3021, "PDX", "", "BOI", "");
    assertThat(flight.airline, equalTo("Delta"));
    assertThat(flight.flightNumber, equalTo(3021));
    assertThat(flight.src, equalTo("PDX"));
    assertThat(flight.dest, equalTo("BOI"));
  }
}
