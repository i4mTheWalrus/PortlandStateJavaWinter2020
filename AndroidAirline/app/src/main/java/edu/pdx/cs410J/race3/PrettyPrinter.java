package edu.pdx.cs410J.race3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.util.Collection;

/**
 * Class to print the contents of an airline in a more readable fashion.
 */
public class PrettyPrinter {

    final Writer writer;

    /**
     * Constructor that takes a destination for the printing, be it a file, or standard output if equal to "-".
     * @param path String when printing should be directed to.
     * @throws IOException Unexpected exception from file writer.
     */
    public PrettyPrinter(String path) throws IOException {
        if(path.equals(null)) {
            throw new IllegalArgumentException("Pretty printer path cannot be null");
        } else if(path.equals("-")) {
            // Print to console window
            writer = new OutputStreamWriter(System.out);
        } else {
            // Print to file given
            writer = new FileWriter(path);
        }
    }

    /**
     * Method that does the actual printing. Destination determined by constructor.
     * @param abstractAirline Airline to dump the flight contents of.
     * @throws IOException Unexpected exception from file writer.
     */
    public void dump(Airline abstractAirline) throws IOException {
        if(abstractAirline == null) {
            throw new IOException("Airline passed to pretty print is a null object.");
        }

        Collection<Flight> flights = abstractAirline.getFlights();
        for(Flight f : flights) {
            try {
                writer.write(f.getPrettyPrintString());
            } catch (ParseException e) {
                throw new IOException(e.getMessage());
            }
        }
        writer.close();
    }
}
