package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;

import java.io.IOException;

public class XmlDumper implements AirlineDumper {
  @Override
  public void dump(AbstractAirline abstractAirline) throws IOException {
    AirlineXmlHelper helper = new AirlineXmlHelper();

  }
}
