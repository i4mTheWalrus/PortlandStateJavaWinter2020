package edu.pdx.cs410J.race3;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.*;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * A class that reads the contents of a text ﬁle and from it creates an airline with its associated ﬂights.
 */
public class TextParser {

    final Reader reader;

    /**
     * Constructor that takes in a filename. Path is assumed to be in src/main/resources/
     * @param filePath String containing name and path of file to read.
     */
    TextParser(String filePath) throws IOException {
        File file = new File(filePath);
        if(!file.exists()) {
            if(!file.createNewFile()) {
                throw new IOException("Could not create file.");
            }
        }
        this.reader = new FileReader(filePath);
    }

    /**
     * Parses the text file referred to by constructor, stores is in an airline, and returns that airline.
     * @return Airline containing flights in the file specified.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Airline parse() throws IOException {
        Airline airline = new Airline();
        try {

            BufferedReader inFile = new BufferedReader(reader);
            String line;

            while((line = inFile.readLine()) != null) {
                String[] flightData = line.split(",");

                // Check if the flight string is malformed
                checkFlightFormatting(flightData);

                Flight flight = new Flight(flightData[0], flightData[1], flightData[2], flightData[3], flightData[4], flightData[5], flightData[6], flightData[7], flightData[8], flightData[9]);
                airline.setAirlineName(flightData[0]);
                airline.addFlight(flight);
            }
            inFile.close();
            return airline;
        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Gets passed the flight string array read in from file. Checks each element eo ensure correctness of flight.
     * @param flightArgs String array of line read from file.
     */
    public void checkFlightFormatting(String[] flightArgs) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy mm:ss a", Locale.getDefault());
        Date arr, depart;

        if(flightArgs.length < 10) {
            throw new NullPointerException("A fight in the file doesn't have enough arguments");
        }

        if(!flightArgs[1].matches("([0-9]+)")) {
            throw new IllegalArgumentException("Text file: A flight number is not an integer.");
        }

        if(flightArgs[2].length() != 3 || Pattern.compile("[^a-zA-Z]").matcher(flightArgs[2]).find()) {
            throw new IllegalArgumentException("Text file: An airport code is not a 3 character letter-only code. Was found to be " + flightArgs[2]);
        }

        if(!flightArgs[3].matches("((0[1-9]|1[012])|([1-9]|1[012]))/((0[1-9]|[12][0-9]|3[01])|([1-9]|[12][0-9]|3[01]))/((19|2[0-9])[0-9]{2})")) {
            throw new IllegalArgumentException("Depart date in text file is not in correct format. (##/##/####) Was found to be " + flightArgs[3]);
        }

        if(!flightArgs[4].matches("((1[012]|[1-9]):[0-5][0-9])")) {
            throw new IllegalArgumentException("Depart time in text file is not in correct format. (##:##) Was found to be " + flightArgs[4]);
        }

        try {
            depart = sdf.parse(flightArgs[3] + " " + flightArgs[4] + " " + flightArgs[5]);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Incorrect departure date/time found in file.");
        }

        if(flightArgs[6].length() != 3 || Pattern.compile("[^a-zA-Z]").matcher(flightArgs[6]).find()) {
            throw new IllegalArgumentException("Text file: An airport code is not a 3 character letter-only code. Was found to be " + flightArgs[6]);
        }

        if(!flightArgs[7].matches("((0[1-9]|1[012])|([1-9]|1[012]))/((0[1-9]|[12][0-9]|3[01])|([1-9]|[12][0-9]|3[01]))/((19|2[0-9])[0-9]{2})")) {
            throw new IllegalArgumentException("Arrival date in text file is not in correct format. (##/##/####) Was found to be " + flightArgs[7]);
        }

        if(!flightArgs[8].matches("((1[012]|[1-9]):[0-5][0-9])")) {
            throw new IllegalArgumentException("Arrive time in text file is not in correct format. (##:##) Was found to be " + flightArgs[8]);
        }

        try {
            arr = sdf.parse(flightArgs[7] + " " + flightArgs[8] + " " + flightArgs[9]);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Incorrect arrival date/time found in file.");
        }

        if(depart.compareTo(arr) > 0) {
            throw new IllegalArgumentException("A flight in the file has a departure date/time that is before arrival date/time!");
        }
    }
}