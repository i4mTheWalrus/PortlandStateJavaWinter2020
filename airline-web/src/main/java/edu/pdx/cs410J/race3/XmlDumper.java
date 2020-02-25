package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;
import edu.pdx.cs410J.ParserException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;

public class XmlDumper implements AirlineDumper {
  DocumentBuilderFactory factory;
  DocumentBuilder builder;
  Document doc;
  String xmlPath;

  XmlDumper(String path) throws ParserConfigurationException {
    factory = DocumentBuilderFactory.newInstance();
    builder = factory.newDocumentBuilder();
    doc = builder.newDocument();
    xmlPath = path;
  }

  @Override
  public void dump(AbstractAirline abstractAirline) throws IOException {
    Date departDate, arriveDate;
    Calendar cal = Calendar.getInstance(TimeZone.getDefault());

    // Root element
    Element root = doc.createElement("airline");
    doc.appendChild(root);
    Element airline = doc.createElement("name");
    airline.appendChild(doc.createTextNode(abstractAirline.getName()));
    root.appendChild(airline);

    // Flights
    Collection<Flight> flights = abstractAirline.getFlights();
    for (Flight f : flights) {
      departDate = f.getDeparture();
      arriveDate = f.getArrival();
      cal.setTime(departDate);

      Element flight = doc.createElement("flight");
      Element number = doc.createElement("number");
      number.appendChild(doc.createTextNode(String.valueOf(f.getNumber())));
      flight.appendChild(number);

      Element src = doc.createElement("src");
      src.appendChild(doc.createTextNode(f.getSource()));
      flight.appendChild(src);

      Element depart = doc.createElement("depart");
      Element dDate = doc.createElement("date");
      Attr year = doc.createAttribute("year");
      Attr month = doc.createAttribute("month");
      Attr day = doc.createAttribute("day");
      year.setValue(String.valueOf(cal.get(Calendar.YEAR)));
      month.setValue(String.valueOf(cal.get(Calendar.MONTH) + 1));
      day.setValue(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
      dDate.setAttributeNode(year);
      dDate.setAttributeNode(month);
      dDate.setAttributeNode(day);
      depart.appendChild(dDate);

      Element dTime = doc.createElement("time");
      Attr minute = doc.createAttribute("minute");
      Attr hour = doc.createAttribute("hour");
      minute.setValue(String.valueOf(cal.get(Calendar.MINUTE)));
      hour.setValue(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
      dTime.setAttributeNode(minute);
      dTime.setAttributeNode(hour);
      depart.appendChild(dTime);
      flight.appendChild(depart);

      Element dest = doc.createElement("dest");
      dest.appendChild(doc.createTextNode(f.getDestination()));
      flight.appendChild(dest);

      cal.setTime(arriveDate);

      Element arrive = doc.createElement("arrive");
      Element aDate = doc.createElement("date");
      Attr year2 = doc.createAttribute("year");
      Attr month2 = doc.createAttribute("month");
      Attr day2 = doc.createAttribute("day");
      year2.setValue(String.valueOf(cal.get(Calendar.YEAR)));
      month2.setValue(String.valueOf(cal.get(Calendar.MONTH) + 1));
      day2.setValue(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
      aDate.setAttributeNode(year2);
      aDate.setAttributeNode(month2);
      aDate.setAttributeNode(day2);

      arrive.appendChild(aDate);

      Element aTime = doc.createElement("time");
      Attr minute2 = doc.createAttribute("minute");
      Attr hour2 = doc.createAttribute("hour");
      minute2.setValue(String.valueOf(cal.get(Calendar.MINUTE)));
      hour2.setValue(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
      aTime.setAttributeNode(minute2);
      aTime.setAttributeNode(hour2);
      arrive.appendChild(aTime);
      flight.appendChild(arrive);

      root.appendChild(flight);
    }

    // Build the xml file
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource domSource = new DOMSource(doc);
      StreamResult streamResult = new StreamResult(new File(xmlPath));

      transformer.transform(domSource, streamResult);
    } catch (TransformerException e) {
      throw new IOException("Something went wrong building the xml file.");
    }
  }
}
