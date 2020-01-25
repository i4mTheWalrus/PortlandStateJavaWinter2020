package edu.pdx.cs410J.race3;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.*;

public class TextDumperTest {
  TextDumper td = new TextDumper();

  @Test (expected = IOException.class)
  public void testNullPassedToDumpReturnsInvalidArgumentException() throws IOException {
    td.dump(null);
  }

  /**
   * Create an airline called test and check that is creates test.txt if it does not exist.
   * @throws IOException
   */
  @Test
  public void testIfFileForAirlineDoesntExistItIsCreated() throws IOException {
    File tempFile = new File("src/main/resources/test.txt");
    Airline a = new Airline("test");
    td.dump(a);
    assertThat(tempFile.exists(), is(true));
  }

  @Test
  public void testThatDumpWritesSuccessfullyToFile() throws IOException {
    Airline airline = new Airline("Delta");
    Flight flight = new Flight("Delta", "345", "BOI", "12/12/2012", "23:43", "PDX", "11/11/2011", "23:40");
    airline.addFlight(flight);
    flight = new Flight("Delta", "6543", "SFC", "01/01/2012", "03:43", "NYC", "01/01/2011", "03:40");
    airline.addFlight(flight);
    td.dump(airline);
  }
}
