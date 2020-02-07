package edu.pdx.cs410J.race3;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Class to test the PrettyPrinter class.
 */
public class PrettyPrinterTest {

  /**
   * Method that tests if pretty print sends to the console window correctly when given the "-" argument.
   * @throws IOException Unexpected exception from file reader.
   */
  @Test
  public void testPrettyOutputToStandardOut() throws IOException {
    PrettyPrinter printer = new PrettyPrinter("TestPretty.txt");
    Airline airline = new Airline("Tester");
    Flight flight = new Flight("Tester", "234", "BOI", "2/2/2002", "4:30", "am", "PDX", "2/2/2002", "5:30", "am");
    airline.addFlight(flight);
    printer.dump(airline);
    BufferedReader reader = new BufferedReader(new FileReader("TestPretty.txt"));
    String line = reader.readLine();
    assertThat(line.contains("Tester flight 234 departs Boise, ID at 2/2/02, 4:30 AM and arrives at Portland, OR at 2/2/02, 5:30 AM for a total flight time of 60 minutes."), is(true));
    reader.close();
  }
}
