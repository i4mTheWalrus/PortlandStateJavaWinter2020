package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.util.Collection;

public class PrettyPrinter implements AirlineDumper {

  final Writer writer;

  public PrettyPrinter(String path) throws IOException {
    if(path.equals(null)) {
      throw new IllegalArgumentException("Pretty printer path cannot be null");
    } else if(path.equals("-")) {
      // Print to console window
      writer = new OutputStreamWriter(System.out);
    } else {
      // Print to file given
      writer = new FileWriter(path);
    }
  }

  @Override
  public void dump(AbstractAirline abstractAirline) throws IOException {
    if(abstractAirline == null) {
      throw new IOException("Airline passed to pretty print is a null object.");
    }

    Collection<Flight> flights = abstractAirline.getFlights();
    for(Flight f : flights) {
      try {
        writer.write(f.getPrettyPrintString());
      } catch (ParseException e) {
        throw new IOException(e.getMessage());
      }
    }
    writer.close();
  }
}
