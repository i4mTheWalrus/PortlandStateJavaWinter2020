package edu.pdx.cs410J.race3;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Flight} class.
 */
public class FlightTest {
  Flight constructedFlight = new Flight("Delta", "3021", "PDX", "11/15/1986", "13:15", "BOI", "11/16/1986", "15:17");

  /**
   * Test getting the depart string matches what constructor set it to.
   */
  @Test
  public void testGetDepartString() {
    assertThat(constructedFlight.getDepartureString(), equalTo("11/15/1986 13:15"));
  }

  /**
   * Test getting the arrival string matches what the constructor set it to.
   */
  @Test
  public void testGetArrivalString() {
    assertThat(constructedFlight.getArrivalString(), equalTo("11/16/1986 15:17"));
  }

  /**
   * Test that a flight constructed with the default constructor sets the flight number to the initial value 42.
   */
  @Test
  public void initiallyAllFlightsHaveTheSameNumber() {
    Flight flight = new Flight();
    assertThat(flight.getNumber(), equalTo(42));
  }

  /**
   * Test the return value of the getArrivalTime method.
   */
  @Test
  public void testGetArrivalTime() {
    assertThat(constructedFlight.arriveTime, equalTo(constructedFlight.getArriveTime()));
  }

  /**
   * Test the return value of the getDepartureTime method.
   */
  @Test
  public void testGetDepartTime() {
    assertThat(constructedFlight.departTime, equalTo(constructedFlight.getDepartureTime()));
  }

  /**
   * Test that the parameterized constructor correctly sets the fields in flight.
   */
  @Test
  public void testParameterizedConstructor() {
    assertThat(constructedFlight.airline, equalTo("Delta"));
    assertThat(constructedFlight.flightNumber, equalTo("3021"));
    assertThat(constructedFlight.src, equalTo("PDX"));
    assertThat(constructedFlight.dest, equalTo("BOI"));
    assertThat(constructedFlight.departDate, equalTo("11/15/1986"));
    assertThat(constructedFlight.departTime, equalTo("13:15"));
    assertThat(constructedFlight.arriveDate, equalTo("11/16/1986"));
    assertThat(constructedFlight.arriveTime, equalTo("15:17"));
  }

  /**
   * Test the return value of the getNumber method.
   */
  @Test
  public void testGetFlightNumberMethodAfterSettingIt() {
    assertThat(constructedFlight.getNumber(), equalTo(3021));
  }

  /**
   * Test the return value of the getSource method.
   */
  @Test
  public void testGetSourceMethod() {
    assertThat(constructedFlight.getSource(), equalTo("PDX"));
  }

  /**
   * Test the return value of the getDestination method.
   */
  @Test
  public void testGetDestinationMethod() {
    assertThat(constructedFlight.getDestination(), equalTo("BOI"));
  }

  /**
   * Test that the Project1 class can even be instantiated.
   */
  @Test
  public void testProject2CanBeCreated() {
    Project3 testProj = new Project3();
    assertThat(testProj, notNullValue());
  }

  /**
   * Test to ensure exception is thrown if flight number passed in is not an integer.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testFlightNumberIsInteger() {
    Flight constructedFlight2 = new Flight("Delta", "LETTERS", "PDX", "11/15/1986", "13:15", "BOI", "11/16/1986", "15:17");
  }
}
