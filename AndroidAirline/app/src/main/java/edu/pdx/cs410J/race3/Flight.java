package edu.pdx.cs410J.race3;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * This class is used to model a flight to and from an airport.
 * Flights are constructed using 6 arguments given on command line.
 * The flight is created using a constructor.
 * @author Tyler Race
 */
public class Flight implements Serializable {
    /**
     * Holds the name of the airline. ex: Delta
     */
    String airline;

    /**
     * Flight number. Can contain both letters and numbers.
     */
    String flightNumber;

    /**
     * 3 letter source airport code.
     */
    String src;

    /**
     * Date of flight departure. xx/xx/xxxx
     */
    String departDate;

    /**
     * Time of flight departure. ##:##
     */
    String departTime;

    /**
     * 3 letter destination airport code.
     */
    String dest;

    /**
     * Date of flight arrival. xx/xx/xxxx
     */
    String arriveDate;

    /**
     * Time of flight arrival. ##:##
     */
    String arriveTime;

    String arriveAP;

    String departAP;

    Date departure;

    Date arrival;

    /**
     * Default constructor. Does nothing.
     */
    Flight() {}

    /**
     * Parameterized constructor. Build the flight with specified info.
     * @param airline String containing name of airline.
     * @param flightNumber String of flight number. Numerical only.
     * @param src String of source airport. Must be 3 letters.
     * @param departDate String of flight departure date.
     * @param departTime String of flight departure time.
     * @param dest String of destination airport. Must be 3 letters.
     * @param arriveDate String of flight arrival date.
     * @param arriveTime String of flight arrival time.
     */
    Flight(String airline, String flightNumber, String src, String departDate, String departTime, String dest, String arriveDate, String arriveTime) {
        this.airline = airline;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.getDefault());

        // Time should be in ##:## format
        if(!arriveTime.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
            throw new IllegalArgumentException("Arrive time is not in correct format. (##:##). Found to be " + arriveTime);
        }
        this.arriveTime = arriveTime;

        // Date should be in ##/##/#### format
        if(!arriveDate.matches("([0-9]{1,2})/([0-9]{1,2})/([0-9]{4})")) {
            throw new IllegalArgumentException("Arrive date is not in correct format. (##/##/####)");
        }
        this.arriveDate = arriveDate;

        // src should be 3 characters! (no numbers or special characters)
        if(dest.length() != 3 || Pattern.compile("[^a-zA-Z]").matcher(dest).find()) {
            throw new IllegalArgumentException("Airport code is not a 3 character letter-only code.");
        }
        this.dest = dest;

        // Time should be in ##:## format
        if(!departTime.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
            throw new IllegalArgumentException("Depart time is not in correct format. (##:##)");
        }
        this.departTime = departTime;

        // Date should be in ##/##/#### format
        if(!departDate.matches("([0-9]{1,2})/([0-9]{1,2})/([0-9]{4})")) {
            throw new IllegalArgumentException("Depart date is not in correct format. (##/##/####)");
        }
        this.departDate = departDate;

        // Try to build depart date
        try {
            departure = sdf.parse(departDate + " " + departTime);
        } catch (ParseException e) {
            e.getStackTrace();
        }

        // Try to build arrive date
        try {
            arrival = sdf.parse(arriveDate + " " + arriveTime);
        } catch (ParseException e) {
            e.getStackTrace();
        }

        // src should be 3 characters! (no numbers or special characters)
        if(src.length() != 3 || Pattern.compile("[^a-zA-Z]").matcher(src).find()) {
            throw new IllegalArgumentException("Airport code is not a 3 character letter-only code.");
        }
        this.src = src;

        // flight number should be integer
        if(!flightNumber.matches("([0-9]+)")) {
            throw new IllegalArgumentException("Flight number should be integer");
        }
        this.flightNumber = flightNumber;
    }

    /**
     * Method for obtaining the flight number for the flight.
     * @return The flight number, or the number 42 if none is specified.
     */
    public int getNumber() {
        if(flightNumber == null)
            return 42;
        else
            return Integer.parseInt(flightNumber);
    }

    /**
     * Method to return the name of the airline that the flight belongs to.
     * @return String containing airline name.
     */
    public String getAirlineName() {
        return airline;
    }

    /**
     * Method for getting the arrival time of a flight.
     * @return A string containing arrival time.
     */
    public String getArriveTime() {
        return arriveTime;
    }

    /**
     * Method for getting the departure time of a flight.
     * @return A string containing arrival time.
     */
    public String getDepartureTime() {
        return departTime;
    }

    /**
     * Method for getting the source airport code of a flight.
     * @return A string containing the source airport code.
     */
    public String getSource() {
        return this.src;
    }

    /**
     * Method for building a string containing both the departure date and time of a flight.
     * @return A string containing departure date and departure time separated by a space.
     */
    public String getDepartureString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.getDefault());
        return sdf.format(departure);
    }

    /**
     * Method for getting the destination airport code of a flight.
     * @return A string containing the destination airport code.
     */
    public String getDestination() {
        return this.dest;
    }

    /**
     * Method for building a string containing both the arrival date and time of a flight.
     * @return A string containing arrival date and arrival time separated by a space.
     */
    public String getArrivalString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.getDefault());
        return sdf.format(arrival);
    }

    /**
     * Get the departure date class.
     * @return Date object of departure.
     */
    public Date getDeparture() {
        return departure;
    }

    /**
     * Get the arrival date class.
     * @return Date object of arrival.
     */
    public Date getArrival() {
        return arrival;
    }

    /**
     * Method used to calculate the duration of the flight. If it's negative, the flight should be invalidated.
     * @return The flight duration time in minutes.
     * @throws ParseException Unexpected exception thrown when parsing the dates.
     */
    private long getFlightDuration() throws ParseException {
        long difference = arrival.getTime() - departure.getTime();
        return difference / (60 * 1000);
    }

    /**
     * Returns a brief textual description of this flight.

    public final String toString() {
        return "Flight " + this.getNumber() + " departs " + this.getSource()
                + " at " + this.getDepartureString() + " arrives " +
                this.getDestination() + " at " + this.getArrivalString();
    }
     */
    @NonNull
    @Override
    public String toString() {
        try {
            return "--- #" + this.getNumber() + " ---\n" + this.getSource() + " -> " + this.getDestination() +
                    "\nDeparts: " + this.departDate + " " + this.departTime + "\nArrives: " +
                    this.arriveDate + " " + arriveTime + "\nDuration: " + String.valueOf(getFlightDuration()) + " minutes";
        } catch (ParseException e) {
            return "--- #" + this.getNumber() + " ---\n" + this.getSource() + " -> " + this.getDestination() +
                    "\nDeparts: " + this.departDate + " " + this.departTime + "\nArrives: " +
                    this.arriveDate + " " + arriveTime;
        }
    }
}
