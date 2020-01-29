package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;

import java.io.*;
import java.util.Collection;

/**
 * Class that dumps the contents of an airline (including ﬂights) to a text ﬁle.
 */
public class TextDumper implements AirlineDumper {
  /**
   * String for class that holds the filename and path.
   */
  String filename;

  /**
   * Constructor that takes in a string to determine file path.
   * @param filePath String for filepath. File extension should be included when passed in.
   */
  TextDumper(String filePath) {
    this.filename = filePath;
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

    // Create the text file if it doesn't exist
    String airlineName = abstractAirline.getName();
    File newFile = new File(this.filename);
    if(!newFile.exists()) {
      if(!newFile.createNewFile()) {
        throw new IOException("Could not create file.");
      }
    }
    PrintWriter filePrinter = new PrintWriter(newFile);

    // Write each flight in the airline to a text file
    Collection<Flight> flights = abstractAirline.getFlights();
    for(Flight f : flights) {
      filePrinter.println(f.getTextFileString());
    }
    filePrinter.close();
  }
}
