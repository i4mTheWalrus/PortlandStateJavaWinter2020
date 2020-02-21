package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.ParserException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Converter {
  public static void main(String[] args) throws IOException, ParserConfigurationException, ParserException {
    Airline airline;
    TextParser tp = new TextParser(args[0]);
    XmlDumper xd = new XmlDumper(args[1]);

    airline = (Airline)tp.parse();
    xd.dump(airline);
  }
}
