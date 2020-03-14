package edu.pdx.cs410J.race3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FlightList extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_list);

        TextView airlineTextView = (TextView)findViewById(R.id.textHeaderAirline);
        Airline airline = (Airline)getIntent().getSerializableExtra("Airline");
        airlineTextView.setText(airline.getName());
    }
}
