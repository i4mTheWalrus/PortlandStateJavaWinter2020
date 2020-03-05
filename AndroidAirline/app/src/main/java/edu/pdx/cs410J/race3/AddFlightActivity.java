package edu.pdx.cs410J.race3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import edu.pdx.cs410J.race3.R;

public class AddFlightActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight);

        // Airline name header
        TextView airlineTextView = (TextView)findViewById(R.id.airlineNameText);
        Airline airline = (Airline)getIntent().getSerializableExtra("Airline");
        airlineTextView.setText(airline.getName());

        // src spinner
        String[] srcSpinnerItems = new String[] {
                "[SRC]", "BOI", "SRC", "PDX", "ORD", "SFC"
        };
        Spinner s1 = (Spinner)findViewById(R.id.srcSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, srcSpinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);

        // dest spinner
        Spinner s2 = (Spinner)findViewById(R.id.destSpinner);
        s2.setAdapter(adapter);
    }
}
