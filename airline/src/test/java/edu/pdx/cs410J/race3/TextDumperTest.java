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

  @Test
  public void testIfFileForAirlineDoesntExistItIsCreated() throws IOException {
    File tempFile = new File("test.txt");
    Airline a = new Airline("test");
    td.dump(a);
    assertThat(tempFile.exists(), is(true));
  }
}
