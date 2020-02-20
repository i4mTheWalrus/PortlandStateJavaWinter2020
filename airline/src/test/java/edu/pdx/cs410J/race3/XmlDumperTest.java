package edu.pdx.cs410J.race3;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class XmlDumperTest {

  @Test (expected = IOException.class)
  public void testStuff() throws IOException {
    XmlDumper test = new XmlDumper();
    Airline airline = new Airline();
    test.dump(airline);
  }
}
