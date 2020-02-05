package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.ParserException;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.processing.Filer;
import java.io.*;
import java.text.ParseException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Class to unit test the {@link TextParser} class.
 */
public class TextParserTest {
  /**
   * Test to check for thrown exception if flight number in file is not an integer.
   * @throws ParserException Expected exception to be thrown.
   * @throws IOException Unexpected exception related to file writer.
   */
  @Test (expected = ParserException.class)
  public void testMalformedFlightNumberInTextFileGivesException() throws ParserException, IOException {
    File file = new File("ParseTest.txt");
    FileWriter writeOut = new FileWriter(file);
    writeOut.write("Airline," + "ishouldbeanumber," + "BOI," + "12/12/1212," + "12:12," + "PDX," + "12/12/1212," + "12:13");
    writeOut.close();
    Airline airline = new Airline("Airline");
    TextParser tp = new TextParser("ParseTest.txt");
    airline = (Airline)tp.parse();
    file.delete();
  }

  /**
   * Test to check for thrown exception if the src airport is malformed.
   * @throws ParserException Expected exception to be thrown.
   * @throws IOException Unexpected exception related to file writer.
   */
  @Test (expected = ParserException.class)
  public void testMalformedSrcInTextFileGivesException() throws ParserException, IOException {
    File file = new File("ParseTest.txt");
    FileWriter writeOut = new FileWriter(file);
    writeOut.write("Airline," + "4567," + "BOISE," + "12/12/1212," + "12:12," + "PDX," + "12/12/1212," + "12:13");
    writeOut.close();
    Airline airline = new Airline("Airline");
    TextParser tp = new TextParser("ParseTest.txt");
    airline = (Airline) tp.parse();
    file.delete();
  }

  /**
   * Test to check for thrown exception if depart date is malformed in text file.
   * @throws ParserException Expected exception to be thrown.
   * @throws IOException Unexpected exception related to file writer.
   */
  @Test (expected = ParserException.class)
  public void testMalformedDepartDateInTextFileGivesException() throws ParserException, IOException {
    File file = new File("ParseTest.txt");
    FileWriter writeOut = new FileWriter(file);
    writeOut.write("Airline," + "4567," + "BOI," + "12/12/1," + "12:12," + "PDX," + "12/12/1212," + "12:13");
    writeOut.close();
    Airline airline = new Airline("Airline");
    TextParser tp = new TextParser("ParseTest.txt");
    airline = (Airline)tp.parse();
    file.delete();
  }

  /**
   * Test to check for thrown exception if depart time is malformed in text file.
   * @throws ParserException Expected exception to be thrown.
   * @throws IOException Unexpected exception related to file writer.
   */
  @Test (expected = ParserException.class)
  public void testMalformedDepartTimeInTextFileGivesException() throws ParserException, IOException {
    File file = new File("ParseTest.txt");
    FileWriter writeOut = new FileWriter(file);
    writeOut.write("Airline," + "4567," + "BOI," + "12/12/1212," + "12:1," + "PDX," + "12/12/1212," + "12:13");
    writeOut.close();
    Airline airline = new Airline("Airline");
    TextParser tp = new TextParser("ParseTest.txt");
    airline = (Airline) tp.parse();
    file.delete();
  }

  /**
   * Test to check for thrown exception if destination airport code is malformed in text file.
   * @throws ParserException Expected exception to be thrown.
   * @throws IOException Unexpected exception related to file writer.
   */
  @Test (expected = ParserException.class)
  public void testMalformedDestInTextFileGivesException() throws ParserException, IOException {
    File file = new File("ParseTest.txt");
    FileWriter writeOut = new FileWriter(file);
    writeOut.write("Airline," + "4567," + "BOI," + "12/12/1212," + "12:12," + "PDX IS BEST," + "12/12/1212," + "12:13");
    writeOut.close();
    Airline airline = new Airline("Airline");
    TextParser tp = new TextParser("ParseTest.txt");
    airline = (Airline) tp.parse();
    file.delete();
  }

  /**
   * Test to check for thrown exception if arrive date is malformed in text file.
   * @throws ParserException Expected exception to be thrown.
   * @throws IOException Unexpected exception related to file writer.
   */
  @Test (expected = ParserException.class)
  public void testMalformedArriveDateInTextFileGivesException() throws ParserException, IOException {
    File file = new File("ParseTest.txt");
    FileWriter writeOut = new FileWriter(file);
    writeOut.write("Airline," + "4567," + "BOI," + "12/12/1212," + "12:12," + "PDX," + "122/12/1212," + "12:13");
    writeOut.close();
    Airline airline = new Airline("Airline");
    TextParser tp = new TextParser("ParseTest.txt");
    airline = (Airline) tp.parse();
    file.delete();
  }

  /**
   * Test to check for thrown exception if arrive time is malformed in text file.
   * @throws ParserException Expected exception to be thrown.
   * @throws IOException Unexpected exception related to file writer.
   */
  @Test (expected = ParserException.class)
  public void testMalformedArriveTimeInTextFileGivesException() throws ParserException, IOException {
    File file = new File("ParseTest.txt");
    FileWriter writeOut = new FileWriter(file);
    writeOut.write("Airline," + "4567," + "BOI," + "12/12/1212," + "12:12," + "PDX," + "12/12/1212," + "125:13");
    writeOut.close();
    Airline airline = new Airline("Airline");
    TextParser tp = new TextParser("ParseTest.txt");
    airline = (Airline) tp.parse();
    file.delete();
  }

  @Test (expected = NullPointerException.class)
  public void testTextFileNullPointerExceptionNotEnoughArgs() throws IOException {
    TextParser tp = new TextParser("Whatever");
    String[] str = new String[]{"Delta", "Missing", "Args"};
    tp.checkFlightFormatting(str);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testTextFileIllegalArgumentExceptionBadFlightNumber() throws IOException {
    TextParser tp = new TextParser("Whatever");
    String[] str = new String[]{"Delta", "BADFLIGHTNUM", "PDX", "2/2/2012", "2:15", "am", "BOI", "5/12/2009", "3:26", "pm"};
    tp.checkFlightFormatting(str);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testTextFileIllegalArgumentExceptionBadSrcAirportCode() throws IOException {
    TextParser tp = new TextParser("Whatever");
    String[] str = new String[]{"Delta", "345", "XXPDX", "2/2/2012", "2:15", "am", "BOI", "5/12/2009", "3:26", "pm"};
    tp.checkFlightFormatting(str);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testTextFileIllegalArgumentExceptionBadDepartDate() throws IOException {
    TextParser tp = new TextParser("Whatever");
    String[] str = new String[]{"Delta", "456", "PDX", "2/2/2010342", "2:15", "am", "BOI", "5/12/2009", "3:26", "pm"};
    tp.checkFlightFormatting(str);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testTextFileIllegalArgumentExceptionBadDepartTime() throws IOException {
    TextParser tp = new TextParser("Whatever");
    String[] str = new String[]{"Delta", "5654", "PDX", "2/2/2012", "2:105", "am", "BOI", "5/12/2009", "3:26", "pm"};
    tp.checkFlightFormatting(str);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testTextFileIllegalArgumentExceptionBadDestAirportCode() throws IOException {
    TextParser tp = new TextParser("Whatever");
    String[] str = new String[]{"Delta", "45", "PDX", "2/2/2012", "2:15", "am", "6543BOI", "5/12/2009", "3:26", "pm"};
    tp.checkFlightFormatting(str);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testTextFileIllegalArgumentExceptionBadArrivalDate() throws IOException {
    TextParser tp = new TextParser("Whatever");
    String[] str = new String[]{"Delta", "98", "PDX", "2/2/2012", "2:15", "am", "BOI", "50/12/2009", "3:26", "pm"};
    tp.checkFlightFormatting(str);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testTextFileIllegalArgumentExceptionBadArrivalTime() throws IOException {
    TextParser tp = new TextParser("Whatever");
    String[] str = new String[]{"Delta", "56", "PDX", "2/2/2012", "2:15", "am", "BOI", "5/12/2009", "30:26", "pm"};
    tp.checkFlightFormatting(str);
  }
}