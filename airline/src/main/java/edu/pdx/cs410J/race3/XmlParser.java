package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;
import org.w3c.dom.*;
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
    doc = builder.parse(this.getClass().getResourceAsStream(xmlFile));
  }

  @Override
  public AbstractAirline parse() throws ParserException {
    Airline airline = new Airline();
    String number, src, dDay, dMonth, dYear, dHour, dMinute, dest, aDay, aMonth, aYear, aHour, aMinute;
    NodeList airlineFromXML = doc.getElementsByTagName("airline");
    Node node = airlineFromXML.item(0);
    Element element = (Element) node;
    airline.setAirlineName(element.getElementsByTagName("name").item(0).getTextContent());

    // Parse the flights in the file
    airlineFromXML = doc.getElementsByTagName("flight");
    for(int temp = 0; temp < airlineFromXML.getLength(); temp++) {
      node = airlineFromXML.item(temp);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        element = (Element) node;
        number = element.getElementsByTagName("number").item(0).getTextContent();
        src = element.getElementsByTagName("src").item(0).getTextContent();
        NamedNodeMap child = element.getElementsByTagName("date").item(0).getAttributes();
        dDay = child.getNamedItem("day").getTextContent();
        dMonth = child.getNamedItem("month").getTextContent();
        dYear = child.getNamedItem("year").getTextContent();
        child = element.getElementsByTagName("time").item(0).getAttributes();
        dHour = child.getNamedItem("hour").getTextContent();
        dMinute = child.getNamedItem("minute").getTextContent();
        dest = element.getElementsByTagName("dest").item(0).getTextContent();
        child = element.getElementsByTagName("date").item(1).getAttributes();
        aDay = child.getNamedItem("day").getTextContent();
        aMonth = child.getNamedItem("month").getTextContent();
        aYear = child.getNamedItem("year").getTextContent();
        child = element.getElementsByTagName("time").item(1).getAttributes();
        aHour = child.getNamedItem("hour").getTextContent();
        aMinute = child.getNamedItem("minute").getTextContent();
      }
    }

    return airline;
  }
}
