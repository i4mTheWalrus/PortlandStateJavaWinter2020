package edu.pdx.cs410J.race3;

import java.util.Comparator;

public class SortFlights implements Comparator<Flight> {

  @Override
  public int compare(Flight flight1, Flight flight2) {
    // First compare source airport
    int srcCompare = flight1.getSource().compareTo(flight2.getSource());
    int departCompare = flight1.getDeparture().compareTo(flight2.getDeparture());

    if(srcCompare == 0) {
      return ((departCompare == 0) ? srcCompare : departCompare);
    } else {
      return srcCompare;
    }
  }
}
