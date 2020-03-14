package edu.pdx.cs410J.race3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FlightList extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_list);

        TextView airlineTextView = (TextView)findViewById(R.id.textHeaderAirline);
        final Airline airline = (Airline)getIntent().getSerializableExtra("Airline");
        assert airline != null;
        airlineTextView.setText(airline.getName());

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
}
