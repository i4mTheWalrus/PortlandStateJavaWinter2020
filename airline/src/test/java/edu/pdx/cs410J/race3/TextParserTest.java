package edu.pdx.cs410J.race3;

import edu.pdx.cs410J.ParserException;
import org.junit.Test;

import java.text.ParseException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Class to unit test the {@link TextParser} class.
 */
public class TextParserTest {

  /**
   * Test that TextParser creates the correct file in the correct directory (resources).
   */
  @Test
  public void testConstructorCreatesCorrectFileAndFilePath() {
    TextParser tp = new TextParser("testPath.txt");
    assertThat(tp.FileName.contains("src/main/resources/"), is(true));
  }

  @Test (expected = ParserException.class)
  public void testFileNotFoundThrowsParserException() throws ParserException {
    TextParser tp = new TextParser("");
    Airline airline = new Airline();
    airline = (Airline)tp.parse();
  }
}