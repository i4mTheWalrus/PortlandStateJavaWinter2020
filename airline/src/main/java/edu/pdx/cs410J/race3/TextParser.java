package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

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
   * @param fileName Specify the name of the file. .txt extension should be given with filename.
   */
  TextParser(String fileName) {
    this.FileName = "src/main/resources/" + fileName;
  }

  @Override
  public AbstractAirline parse() throws ParserException {
    return null;
  }
}