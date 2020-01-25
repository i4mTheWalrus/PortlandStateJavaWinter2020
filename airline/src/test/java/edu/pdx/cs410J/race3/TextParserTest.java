package edu.pdx.cs410J.race3;

import org.junit.Test;
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
    assertThat(tp.FileName, is("src/main/resources/testPath.txt"));
  }
}