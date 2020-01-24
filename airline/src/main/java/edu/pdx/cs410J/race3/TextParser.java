package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

/**
 * A class that reads the contents of a text ﬁle and from it creates an airline with its associated ﬂights.
 */
public class TextParser implements AirlineParser {
  @Override
  public AbstractAirline parse() throws ParserException {
    return null;
  }

  /**
   * Method takes a file path, checks that it exists, then calls parse.
   * @param filePath
   */
  public AbstractAirline parseFile(String filePath) throws IllegalArgumentException {
    // check that the file path is formatted correctly
    // check that the file exists
    // try to parse the text file, parse returns an airline object
    return null;
  }

}
