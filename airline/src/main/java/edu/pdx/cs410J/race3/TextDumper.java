package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;

import java.io.IOException;

/**
 * Class that dumps the contents of an airline (including ﬂights) to a text ﬁle.
 */
public class TextDumper implements AirlineDumper {
  @Override
  public void dump(AbstractAirline abstractAirline) throws IOException {
    if(abstractAirline == null) {
      throw new IOException("Airline passed to text dump is a null object.");
    }
  }
}
