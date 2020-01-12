package edu.pdx.cs410J.race3;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Flight} class.
 */
public class FlightTest {
  Flight constructedFlight = new Flight("Delta", 3021, "PDX", "11/15/1986", "13:15", "BOI", "11/16/1986", "15:17");

  @Test(expected = UnsupportedOperationException.class)
  public void getArrivalStringNeedsToBeImplemented() {
    Flight flight = new Flight();
    flight.getArrivalString();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void getDepartureStringNeedsToBeImplemented() {
    Flight flight = new Flight();
    flight.getDepartureString();
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
  public void testGetArrivalTime() {
    assertThat(constructedFlight.arriveTime, equalTo(constructedFlight.getArriveTime()));
  }

  @Test
  public void testGetDepartTime() {
    assertThat(constructedFlight.departTime, equalTo(constructedFlight.getDepartureTime()));
  }

  @Test
  public void testParameterizedConstructor() {
    assertThat(constructedFlight.airline, equalTo("Delta"));
    assertThat(constructedFlight.flightNumber, equalTo(3021));
    assertThat(constructedFlight.src, equalTo("PDX"));
    assertThat(constructedFlight.dest, equalTo("BOI"));
    assertThat(constructedFlight.departDate, equalTo("11/15/1986"));
    assertThat(constructedFlight.departTime, equalTo("13:15"));
    assertThat(constructedFlight.arriveDate, equalTo("11/16/1986"));
    assertThat(constructedFlight.arriveTime, equalTo("15:17"));
  }

  @Test
  public void testGetFlightNumberMethodAfterSettingIt() {
    assertThat(constructedFlight.getNumber(), equalTo(3021));
  }

  @Test
  public void testGetSourceMethod() {
    assertThat(constructedFlight.getSource(), equalTo("PDX"));
  }

  @Test
  public void testGetDestinationMethod() {
    assertThat(constructedFlight.getDestination(), equalTo("BOI"));
  }
}
