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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    Flight flight;

    String airlineName, number, src, dDay, dMonth, dYear, dHour, dMinute, dest, aDay, aMonth, aYear, aHour, aMinute;
    String dAmPm = "AM", aAmPm = "AM";
    int hour;

    NodeList airlineFromXML = doc.getElementsByTagName("airline");
    Node node = airlineFromXML.item(0);
    Element element = (Element) node;
    airlineName = element.getElementsByTagName("name").item(0).getTextContent();
    airline.setAirlineName(airlineName);

    // Parse the flights in the file
    airlineFromXML = doc.getElementsByTagName("flight");
    try {
      for (int temp = 0; temp < airlineFromXML.getLength(); temp++) {
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

          // Format 24 hour to 12
          if (Integer.parseInt(dHour) > 12) {
            hour = Integer.parseInt(dHour) - 12;
            dHour = String.valueOf(hour);
            dAmPm = "PM";
          }
          // Format 24 hour to 12
          if (Integer.parseInt(aHour) > 12) {
            hour = Integer.parseInt(aHour) - 12;
            aHour = String.valueOf(hour);
            aAmPm = "PM";
          }

          flight = new Flight(airlineName, number, src, dMonth + "/" + dDay + "/" + dYear, dHour + ":" + dMinute, dAmPm, dest, aMonth + "/" + aDay + "/" + aYear, aHour + ":" + aMinute, aAmPm);
          airline.addFlight(flight);
        }
      }
    } catch(NullPointerException e) {
      throw new ParserException("Missing data in the XML file.");
    }

    return airline;
  }
}
