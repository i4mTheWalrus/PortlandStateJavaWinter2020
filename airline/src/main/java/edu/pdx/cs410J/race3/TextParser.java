package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.*;

/**
 * A class that reads the contents of a text ﬁle and from it creates an airline with its associated ﬂights.
 */
public class TextParser implements AirlineParser {

  /**
   * Variable of TextParser that holds the name of the file. Path is assumed to be in src/main/resources/
   */
  String FileName;

  /**
   * Constructor that takes in a filename. Path is assumed to be in src/main/resources/
   * @param fileName Specify the name of the file. The file extension should be given with filename.
   */
  TextParser(String fileName) {
    this.FileName = "src/main/resources/" + fileName;
  }

  /**
   * Parses the text file referred to by constructor, stores is in an airline, and returns that airline.
   * @return Airline containing flights in the file specified.
   * @throws ParserException Thrown if text file is malformed.
   */
  @Override
  public AbstractAirline parse() throws ParserException {
    File in = new File(this.FileName);
    // Check that the file exists (thrown exception by file reader if not)
    try {
      BufferedReader inFile = new BufferedReader(new FileReader(in));
      String line;
      while((line = inFile.readLine()) != null) {
        String[] flightData = line.split(",");
        Flight flight = new Flight(flightData[0], flightData[1], flightData[2], flightData[3], flightData[4], flightData[5], flightData[6], flightData[7]);
        //System.out.println(flight);
      }
    } catch (FileNotFoundException e) {
      throw new ParserException("File not found!");
    } catch (IOException e) {
      throw new ParserException("Error reading from file. May be malformed.");
    }

    return null;
  }
}