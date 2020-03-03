package edu.pdx.cs410J.race3;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is used to model an airline. An airline has a name, and a collection of flights.
 * Flights are modelled with an arraylist.
 * @author Tyler Race
 */
public class Airline implements Serializable {
    /**
     * Name of the airline.
     */
    String airlineName;

    /**
     * Holds the collection of flights.
     */
    ArrayList<Flight> flights;

    /**
     * Default constructor, does nothing special.
     */
    Airline() {
        flights = new ArrayList<>();
    }

    /**
     * Parameterized constructor. Takes a string a sets that as the airline's name.
     * @param name String containing name of airline.
     */
    Airline(String name) {
        flights = new ArrayList<>();
        airlineName = name;
    }

    /**
     * Get the name of the airline.
     * @return A string containing the name of the airline.
     */
    public String getName() {
        return airlineName;
    }

    /**
     * Add a flight to the collection of flights for an airline. Takes a flight as an argument.
     * @param abstractFlight Flight to be added to the airline collection.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addFlight(Flight abstractFlight) {
        checkFlightSrcAndDestAreValid(abstractFlight);
        flights.add(abstractFlight);

        // Always sort after adding a flight
        flights.sort(new SortFlights());
    }

    /**
     * Get the flights that belong to an airline.
     * @return A collection of flights.
     */
    public Collection<Flight> getFlights() {
        return flights;
    }

    /**
     * Set the name of the airline with a method.
     * @param newName Name of airline.
     */
    public void setAirlineName(String newName) {
        this.airlineName = newName;
    }

    /**
     * Called before adding a flight to an airline, this method checks
     * @param abstractFlight
     */
    public void checkFlightSrcAndDestAreValid(Flight abstractFlight) {
        if(AirportNames.getName(abstractFlight.getSource()) == null) {
            System.err.println("Source airport unknown when adding to flight. Found to be " + abstractFlight.getSource());
            System.exit(1);
        }
        if(AirportNames.getName(abstractFlight.getDestination()) == null) {
            System.err.println("Destination airport unknown when adding to flight. Found to be " + abstractFlight.getDestination());
            System.exit(1);
        }
    }

    /**
     * Returns a brief textual description of this airline.
     */
    public final String toString() {
        return this.getName() + " with " + this.getFlights().size() +
                " flights";
    }
}
