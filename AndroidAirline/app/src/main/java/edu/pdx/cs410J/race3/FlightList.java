package edu.pdx.cs410J.race3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FlightList extends Activity {
    ArrayAdapter adapter;
    ArrayList<Flight> flights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_list);

        TextView airlineTextView = (TextView)findViewById(R.id.textHeaderAirline);
        final Airline airline = (Airline)getIntent().getSerializableExtra("Airline");
        assert airline != null;
        airlineTextView.setText(airline.getName());

        flights = (ArrayList<Flight>) airline.getFlights();

        Button addFlightButton = findViewById(R.id.addFlightButton);
        addFlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FlightList.this, AddFlightActivity.class);
                intent.putExtra("Airline", airline);
                startActivity(intent);
            }
        });

    }

    protected void onResume() {
        super.onResume();

        // get new flight from extra and add to airline
        if(flights.size() != 0) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, flights);
            ListView listView = (ListView) findViewById(R.id.flightListView);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}
