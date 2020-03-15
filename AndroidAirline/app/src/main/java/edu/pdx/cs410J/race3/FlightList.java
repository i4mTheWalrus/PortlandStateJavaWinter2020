package edu.pdx.cs410J.race3;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class FlightList extends Activity {
    ArrayAdapter adapter;
    ArrayList<Flight> flights;
    Airline airline;
    private static final int REQUEST_CODE_NEWFLIGHT = 0x9988;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_list);

        airline = (Airline) getIntent().getSerializableExtra("Airline");
        TextView airlineTextView = (TextView) findViewById(R.id.textHeaderAirline);
        airlineTextView.setText(airline.getName());

        Button addFlightButton = findViewById(R.id.addFlightButton);
        addFlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FlightList.this, AddFlightActivity.class);
                intent.putExtra("Airline", airline);
                startActivityForResult(intent, REQUEST_CODE_NEWFLIGHT);
            }
        });
    }

    protected void onResume() {
        super.onResume();
        flights = (ArrayList<Flight>) airline.getFlights();

        // get new flight from extra and add to airline
        if(flights.size() != 0) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, flights);
            ListView listView = (ListView) findViewById(R.id.flightListView);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();


            // TESTING -----------------
            //TextView testTextView = (TextView) findViewById(R.id.testTextView);
            //testTextView.setText(flights.get(1).toString());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final int unmaskedRequestCode = requestCode & 0x0000ffff;

        if(unmaskedRequestCode == REQUEST_CODE_NEWFLIGHT) {
            if(resultCode == Activity.RESULT_OK) {
                // Get the result from the returned Intent
                if(data.hasExtra("newFlight")) {
                    Flight newFlight = (Flight) data.getSerializableExtra("newFlight");
                    data.removeExtra("newFlight");
                    airline.addFlight(newFlight);
                }
            } else {
                // AnotherActivity was not successful. No data to retrieve.
            }
        }
    }
}
