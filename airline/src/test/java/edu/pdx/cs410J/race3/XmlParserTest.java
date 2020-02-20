package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.ParserException;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlParserTest {

  @Test(expected = ParserException.class)
  public void cantParseInvalidXmlFile() throws ParserConfigurationException, IOException, SAXException, ParserException {
    XmlParser test = new XmlParser("invalid-airline.xml");
    Airline airline = (Airline) test.parse();
  }
}
