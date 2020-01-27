package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.InvokeMainTestCase;
import edu.pdx.cs410J.ParserException;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An integration test for the {@link Project2} main class.
 */
public class Project2IT extends InvokeMainTestCase {

  /**
   * Invokes the main method of {@link Project2} with the given arguments.
   */
  private MainMethodResult invokeMain(String... args) {
    return invokeMain( Project2.class, args );
  }

  /**
   * Tests that invoking the main method with no arguments issues an error.
   */
  @Test
  public void testNoCommandLineArguments() {
    MainMethodResult result = invokeMain();
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments."));
  }

  /**
   * Tests that invoking the main method with too many arguments issues an error.
   */
  @Test
  public void testTooManyArgs() {
    MainMethodResult result = invokeMain(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Too many command line arguments."));
  }

  /**
   * Tests that invoking main with not enough arguments (but more than 0) issues an error.
   */
  @Test
  public void testNotEnoughArgsGiven() {
    MainMethodResult result = invokeMain(" ", " ", " ", " ", " ", " ", " ");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Not enough arguments given."));
  }

  /**
   * Test the string out contents for a flight.
   */
  @Test
  public void testToStringMethodOutputForFlight() {
    MainMethodResult result = invokeMain("-print", "Delta", "4567", "PDX", "12/12/2012", "15:24", "SFC", "12/12/2012", "17:15");
    assertThat(result.getTextWrittenToStandardOut().contains("Flight 4567 departs PDX at 12/12/2012 15:24 arrives SFC at 12/12/2012 17:15"), is(true));
  }

  /**
   * Test that the source airport code cannot contain numbers.
   */
  @Test
  public void testSrcAirportCodeCantHaveNumbers() {
    MainMethodResult result = invokeMain(" ", " ", "555", "11/11/1911", "14:25", "BOI", "12/12/2012", "16:23");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Airport code is not a 3 character letter-only code."));
  }

  /**
   * Tests that the destination airport code cannot contain numbers.
   */
  @Test
  public void testDestAirportCodeCantHaveNumbers() {
    MainMethodResult result = invokeMain(" ", " ", "PDX", "11/11/1911", "14:25", "666", "12/12/2012", "16:23");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Airport code is not a 3 character letter-only code."));
  }

  /**
   * Tests that the source airport code cannot have more than 3 characters.
   */
  @Test
  public void testSrcAirportCodeCantHaveMoreThan3Characters() {
    MainMethodResult result = invokeMain(" ", " ", "BOOM", "11/11/1911", "14:25", "KOA", "12/12/2012", "16:23");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Airport code is not a 3 character letter-only code."));
  }

  /**
   * Tests that the destination airport code cannot have more than 3 characters.
   */
  @Test
  public void testDestAirportCodeCantHaveMoreThan3Characters() {
    MainMethodResult result = invokeMain(" ", " ", "BOO", "11/11/1911", "14:25", "KOANS", "12/12/2012", "16:23");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Airport code is not a 3 character letter-only code."));
  }

  /**
   * Tests the formatting check for departure date.
   */
  @Test
  public void testDepartureDateFormatCheck() {
    MainMethodResult result = invokeMain(" ", " ", "PDX", "11/11/191", "14:25", "BOI", "12/12/2012", "16:23");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Depart date is not in correct format. (##/##/####)"));
  }

  /**
   * Tests the formatting check for the departure time.
   */
  @Test
  public void testDepartureTimeFormatCheck() {
    MainMethodResult result = invokeMain(" ", " ", "PDX", "11/11/1911", "14:2", "BOI", "12/12/2012", "16:23");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Depart time is not in correct format. (##:##)"));
  }

  /**
   * Tests the formatting check for the arrival date.
   */
  @Test
  public void testArrivalDateFormatCheck() {
    MainMethodResult result = invokeMain(" ", " ", "PDX", "11/11/1911", "14:25", "BOI", "192/12/212", "16:23");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Arrive date is not in correct format. (##/##/####)"));
  }

  /**
   * Tests the formatting check for the arrival time.
   */
  @Test
  public void testArrivalTimeFormatCheck() {
    MainMethodResult result = invokeMain(" ", " ", "PDX", "11/11/1911", "14:25", "BOI", "12/12/2012", "123:23");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Arrive time is not in correct format. (##:##)"));
  }

  /**
   * Test that an airline read from a file is saved back to the same file correctly.
   */
  @Test
  public void readIntoAirlineFromFileAndSaveBackToSameFileWithNoErrors() throws IOException, ParserException {
    TextParser tp = new TextParser("testAirline");
    Airline airline = (Airline)tp.parse();
    TextDumper td = new TextDumper();
    td.dump(airline);
  }

  /**
   * Test that a new airline dump matches what is read in from the file
   */
  /*
  @Test
  public void dumpIntoFileAndCheckThatParseMatchesAfterReadingIntoAirline() throws IOException, ParserException {
    TextDumper td = new TextDumper();
    Airline airline = new Airline("testDumpThenRead");
    Flight flight = new Flight("testDumpThenRead", "43", "PDX", "06/30/2019", "03:43", "SFC", "04/12/2019", "20:20");
    airline.addFlight(flight);
    flight = new Flight("testDumpThenRead", "6532", "NYC", "04/11/2020", "04:27", "SFC", "05/15/2021", "15:54");
    airline.addFlight(flight);
    td.dump(airline);

    TextParser tp = new TextParser("testDumpThenRead");
    Airline airline2 = (Airline)tp.parse();
    Collection<Flight> a = airline.getFlights();
    Collection<Flight> b = airline2.getFlights();

    Iterator<Flight> it = b.iterator();
    for(Flight f : a) {
      assertThat(f.toString().equals(it.next().toString()), is(true));
    }
  }
   */
}