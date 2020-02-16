package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An integration test for the {@link Project4} main class.
 */
public class Project4IT extends InvokeMainTestCase {

  /**
   * Invokes the main method of {@link Project4} with the given arguments.
   */
  private MainMethodResult invokeMain(String... args) {
    return invokeMain( Project4.class, args );
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
    MainMethodResult result = invokeMain(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ");
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
    MainMethodResult result = invokeMain("-print", "Delta", "4567", "PDX", "12/12/2012", "5:24", "AM", "BOI", "12/12/2012", "7:15", "PM");
    assertThat(result.getTextWrittenToStandardOut().contains("Flight 4567 departs PDX at 12/12/2012 05:24 AM arrives BOI at 12/12/2012 07:15 PM"), is(true));
  }

  /**
   * Test that the source airport code cannot contain numbers.
   */
  @Test
  public void testSrcAirportCodeCantHaveNumbers() {
    MainMethodResult result = invokeMain("test", "234", "555", "11/11/1911", "4:25", "AM", "BOI", "12/12/2012", "6:23", "PM");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Airport code is not a 3 character letter-only code."));
  }

  /**
   * Tests that the destination airport code cannot contain numbers.
   */
  @Test
  public void testDestAirportCodeCantHaveNumbers() {
    MainMethodResult result = invokeMain(" ", "234", "PDX", "11/11/1911", "4:25", "AM", "666", "12/12/2012", "6:23", "PM");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Airport code is not a 3 character letter-only code."));
  }

  /**
   * Tests that the source airport code cannot have more than 3 characters.
   */
  @Test
  public void testSrcAirportCodeCantHaveMoreThan3Characters() {
    MainMethodResult result = invokeMain(" ", "234", "BOOM", "11/11/1911", "4:25", "AM", "BOI", "12/12/2012", "6:23", "PM");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Airport code is not a 3 character letter-only code."));
  }

  /**
   * Tests that the destination airport code cannot have more than 3 characters.
   */
  @Test
  public void testDestAirportCodeCantHaveMoreThan3Characters() {
    MainMethodResult result = invokeMain(" ", "234", "BOI", "11/11/1911", "4:25", "AM", "KOANS", "12/12/2012", "6:23", "PM");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Airport code is not a 3 character letter-only code."));
  }

  @Test
  public void testIllegalDepartDateGivesException() {
    MainMethodResult result = invokeMain(" ", "234", "PDX", "11/11/19111", "4:25", "AM", "BOI", "12/12/2012", "6:23", "PM");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Depart date is not in correct format. (##/##/####)"));
  }

  @Test
  public void testIllegalDepartTimeGivesException() {
    MainMethodResult result = invokeMain(" ", "234", "PDX", "11/11/1911", "487:25", "AM", "BOI", "12/12/2012", "6:23", "PM");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Depart time is not in correct format. (##:##)"));
  }

  @Test
  public void testIllegalArrivalDateGivesException() {
    MainMethodResult result = invokeMain(" ", "234", "PDX", "11/11/1911", "4:25", "AM", "BOI", "12/132/2012", "6:23", "PM");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Arrive date is not in correct format. (##/##/####)"));
  }

  @Test
  public void testIllegalArrivalTimeGivesException() {
    MainMethodResult result = invokeMain(" ", "234", "PDX", "11/11/1911", "4:25", "AM", "BOI", "12/12/2012", "006:23", "PM");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Arrive time is not in correct format. (##:##)"));
  }

  /**
   * Test that an airline read from a file is saved back to the same file correctly.
   */
  /*
  @Test
  public void readIntoAirlineFromFileAndSaveBackToSameFileWithNoErrors() throws IOException, ParserException {
    TextParser tp = new TextParser("testAirline");
    Airline airline = (Airline)tp.parse();
    TextDumper td = new TextDumper("testAirline");
    td.dump(airline);
  }

   */

  /**
   * Test that a new airline dump matches what is read in from the file
   */
  /*
  @Test
  public void dumpIntoFileAndCheckThatParseMatchesAfterReadingIntoAirline() throws IOException, ParserException {
    TextDumper td = new TextDumper("testDumpThenRead.txt");
    Airline airline = new Airline("testDumpThenRead.txt");
    Flight flight = new Flight("testDumpThenRead.txt", "43", "PDX", "06/30/2019", "03:43", "SFC", "04/12/2019", "20:20");
    airline.addFlight(flight);
    flight = new Flight("testDumpThenRead.txt", "6532", "NYC", "04/11/2020", "04:27", "SFC", "05/15/2021", "15:54");
    airline.addFlight(flight);
    td.dump(airline);

    TextParser tp = new TextParser("testDumpThenRead.txt");
    Airline airline2 = (Airline)tp.parse();
    Collection<Flight> a = airline.getFlights();
    Collection<Flight> b = airline2.getFlights();

    Iterator<Flight> it = b.iterator();
    for (Flight f : a) {
      assertThat(f.toString().equals(it.next().toString()), is(true));
    }
  }

   */

  /**
   * Tests to make sure an exception is thrown if there is a mismatched airline name on command line arg vs found in text file.
   */
  public void testCheckingForFlightPassedOnCommandLineMatchesNameInFile() {
    MainMethodResult result = invokeMain("-textFile", "TestNameMatch", "Test1", "1254", "PDX", "05/05/2015", "4:14", "AM", "ORD", "05/05/2015", "4:55", "PM");
    MainMethodResult result2 = invokeMain("-textFile", "TestNameMatch", "Test1", "1254", "PDX", "05/05/2015", "4:14", "AM", "ORD", "05/05/2015", "4:55", "PM");
    assertThat(result2.getTextWrittenToStandardError(), containsString("The airline name provided on command line does not match what is found in file!"));
    assertThat(result2.getExitCode(), is(1));
  }

  /**
   * Test that file is created properly from command line
   */
  public void createFileFroCommandLineArgWorksProperly() {
    MainMethodResult result = invokeMain("-textFile", "TestCreate", "TestCreate", "1254", "PDX", "05/05/2015", "4:14", "AM", "ORD", "05/05/2015", "4:55", "PM");
    File aFile = new File("TestCreate");
    assertThat(aFile.exists(), is(true));
  }
}