package edu.pdx.cs410J.race3;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class LoadAirlines extends Activity {
    LoadAirlines() throws IOException {
        try {
            FileOutputStream fOut = openFileOutput("airlines.txt", MODE_PRIVATE);
        } catch(FileNotFoundException e) {
            File file = new File("airlines.txt");
            file.createNewFile();
            FileOutputStream fOut = openFileOutput("airlines.txt", MODE_PRIVATE);
        }
    }

    // Main parse method
    public ArrayList<Airline> parse() {


        return null;
        /*
        try {
            BufferedReader inFile = new BufferedReader(new FileReader(file));
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
            throw new ParserException(e.getMessage());
        }*/
    }
}
