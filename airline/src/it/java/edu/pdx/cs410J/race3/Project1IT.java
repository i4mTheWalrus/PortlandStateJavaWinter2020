package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An integration test for the {@link Project1} main class.
 */
public class Project1IT extends InvokeMainTestCase {

  /**
   * Invokes the main method of {@link Project1} with the given arguments.
   */
  private MainMethodResult invokeMain(String... args) {
    return invokeMain( Project1.class, args );
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
    MainMethodResult result = invokeMain(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ");
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
   * Tests that if 9 arguments are given (8 for the flight, 1 for the -print option), and the -print flag is not given first, and error is issued.
   */
  @Test
  public void testNineArgumentsGivenButPrintNotGivenFirst() {
    MainMethodResult result = invokeMain(" ", " ", " ", " ", " ", "-print", " ", " ", " ");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Nine arguments given, expected -print first but not found."));
  }

  /**
   * Test that the source airport code cannot contain numbers.
   */
  @Test
  public void testSrcAirportCodeCantHaveNumbers() {
    MainMethodResult result = invokeMain(" ", " ", "555", " ", " ", "BOI", " ", " ");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Airport code is not a 3 character letter-only code."));
  }

  /**
   * Tests that the destination airport code cannot contain numbers.
   */
  @Test
  public void testDestAirportCodeCantHaveNumbers() {
    MainMethodResult result = invokeMain(" ", " ", "PDX", " ", " ", "666", " ", " ");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Airport code is not a 3 character letter-only code."));
  }

  /**
   * Tests that the source airport code cannot have more than 3 characters.
   */
  @Test
  public void testSrcAirportCodeCantHaveMoreThan3Characters() {
    MainMethodResult result = invokeMain(" ", " ", "BOOM", " ", " ", "KOA", " ", " ");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Airport code is not a 3 character letter-only code."));
  }

  /**
   * Tests that the destination airport code cannot have more than 3 characters.
   */
  @Test
  public void testDestAirportCodeCantHaveMoreThan3Characters() {
    MainMethodResult result = invokeMain(" ", " ", "BOO", " ", " ", "KOANS", " ", " ");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Airport code is not a 3 character letter-only code."));
  }
}