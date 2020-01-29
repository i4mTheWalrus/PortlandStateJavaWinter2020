package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 * A class that reads the contents of a text ﬁle and from it creates an airline with its associated ﬂights.
 */
public class TextParser implements AirlineParser {

  /**
   * Variable of TextParser that holds the name of the file. Path is assumed to be in src/main/resources/
   */
  String fileName;

  /**
   * Constructor that takes in a filename. Path is assumed to be in src/main/resources/
   * @param filePath String containing name and path of file to read.
   */
  TextParser(String filePath) {
    this.fileName = filePath;
  }

  /**
   * Parses the text file referred to by constructor, stores is in an airline, and returns that airline.
   * @return Airline containing flights in the file specified.
   * @throws ParserException Thrown if text file is malformed.
   */
  @Override
  public AbstractAirline parse() throws ParserException {
    Airline airline = new Airline();
    File in = new File(this.fileName);
    try {
      if(!in.exists()) {
        if(!in.createNewFile()) {
          throw new IOException("Could not create file.");
        }
      }
      BufferedReader inFile = new BufferedReader(new FileReader(in));
      String line;

      while((line = inFile.readLine()) != null) {
        String[] flightData = line.split(",");

        // Check if the flight string is malformed
        checkFlightFormatting(flightData);

        Flight flight = new Flight(flightData[0], flightData[1], flightData[2], flightData[3], flightData[4], flightData[5], flightData[6], flightData[7]);
        airline.setAirlineName(flightData[0]);
        airline.addFlight(flight);
      }
      inFile.close();
      return airline;
    } catch (IOException | NullPointerException e) {
      throw new ParserException("Error reading from file. May be malformed.");
    } catch (IllegalArgumentException e) {
      throw new ParserException(e.getMessage());
    }
  }

  public void checkFlightFormatting(String[] flightArgs) {
    // airline name, flight #, src, departDate, departTime, dest, arriveDate, arriveTime
    if(!flightArgs[1].matches("([0-9]+)")) {
      throw new IllegalArgumentException("Text file: A flight number is not an integer.");
    }

    if(flightArgs[2].length() != 3 || Pattern.compile("[^a-zA-Z]").matcher(flightArgs[2]).find()) {
      throw new IllegalArgumentException("Text file: An airport code is not a 3 character letter-only code.");
    }

    if(!flightArgs[3].matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
      throw new IllegalArgumentException("Text file: A depart date are not in correct format. (##/##/####)");
    }

    if(!flightArgs[4].matches("([0-9]{2}):([0-9]{2})")) {
      throw new IllegalArgumentException("Text file: A depart time is not in correct format. (##:##)");
    }

    if(flightArgs[5].length() != 3 || Pattern.compile("[^a-zA-Z]").matcher(flightArgs[5]).find()) {
      throw new IllegalArgumentException("Text file: An airport code is not a 3 character letter-only code.");
    }

    if(!flightArgs[6].matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
      throw new IllegalArgumentException("Text file: An arrive date is not in correct format. (##/##/####)");
    }

    if(!flightArgs[7].matches("([0-9]{2}):([0-9]{2})")) {
      throw new IllegalArgumentException("Text file: An arrive time is not in correct format. (##:##)");
    }
  }
}