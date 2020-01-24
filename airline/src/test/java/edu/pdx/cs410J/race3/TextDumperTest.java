package edu.pdx.cs410J.race3;

import org.junit.Test;

import java.lang.IllegalArgumentException;
import java.io.IOException;

public class TextDumperTest {
  TextDumper td = new TextDumper();

  @Test (expected = IllegalArgumentException.class)
  public void testNullPassedToDumpReturnsInvalidArgumentException() {
    
  }
}
