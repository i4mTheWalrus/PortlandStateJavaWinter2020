package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;

import java.io.*;
import java.util.Collection;

/**
 * Class that dumps the contents of an airline (including ﬂights) to a text ﬁle.
 */
public class TextDumper implements AirlineDumper {

  final Writer writer;

  /**
   * Constructor that takes in a string to determine file path.
   * @param filePath String for filepath. File extension should be included when passed in.
   */
  TextDumper(String filePath) throws IOException {
    if(filePath == null) {
      throw new IOException("Filepath cannot be null.");
    }
    File newFile = new File(filePath);
    if(!newFile.exists()) {
      if(!newFile.createNewFile()) {
        throw new IOException("Could not create file.");
      }
    }
    this.writer = new PrintWriter(newFile);
  }

  /**
   * Method to dump the contents of an airline to a textfile given via the constructor.
   * @param abstractAirline Airlines' contents to dump.
   * @throws IOException Throws the exception if the airline is null, or if the text file couldn't be created.
   */
  @Override
  public void dump(AbstractAirline abstractAirline) throws IOException {
    if(abstractAirline == null) {
      throw new IOException("Airline passed to text dump is a null object.");
    }

    String airlineName = abstractAirline.getName();

    // Write each flight in the airline to a text file
    Collection<Flight> flights = abstractAirline.getFlights();
    for(Flight f : flights) {
      writer.write(f.getTextFileString());
    }
    writer.close();
  }
}
