package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlParser implements AirlineParser {
  DocumentBuilderFactory factory;
  DocumentBuilder builder;
  Document doc;

  XmlParser(String xmlFile) throws ParserConfigurationException, IOException, SAXException {
    AirlineXmlHelper helper = new AirlineXmlHelper();
    factory = DocumentBuilderFactory.newInstance();
    builder = factory.newDocumentBuilder();
    builder.setErrorHandler(helper);
    builder.setEntityResolver(helper);
    doc = builder.parse("valid-airline.xml");
  }

  @Override
  public AbstractAirline parse() throws ParserException {
    Airline airline = new Airline();

    return null;
  }
}
