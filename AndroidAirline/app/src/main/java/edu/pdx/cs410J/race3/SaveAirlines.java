package edu.pdx.cs410J.race3;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class SaveAirlines {
    File file = new File("airlines.txt");

    SaveAirlines() throws IOException {
        if(!file.exists()) {
            if(!file.createNewFile()) {
                throw new IOException("Could not create file.");
            }
        }
    }

    public void dump(ArrayList<Airline> airlines) throws IOException {


        /*
        if(airline == null) {
            throw new IOException("Airline passed to text dump is a null object.");
        }

        String airlineName = airline.getName();

        // Write each flight in the airline to a text file
        Collection<Flight> flights = abstractAirline.getFlights();
        for(Flight f : flights) {
            writer.write(f.getTextFileString());
        }
        writer.close();*/
    }
}
