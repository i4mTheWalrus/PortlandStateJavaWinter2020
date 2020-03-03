package edu.pdx.cs410J.race3;

import java.util.Comparator;

/**
 * Class that implements the Comparator used for sorting flights. Flights are sorted by source airport alphabetically,
 * followed by the departure date/time. If these are equal the flights are considered the same.
 */
public class SortFlights implements Comparator<Flight> {

    /**
     * Compare method. Takes in 2 flights and compares their source airport and departure date/time.
     * @param flight1 First flight to compare.
     * @param flight2 Second flight to compare.
     * @return Return an int value -1,0, or 1, depending on the comparison results.
     */
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
