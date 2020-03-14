package edu.pdx.cs410J.race3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddAirlineActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_airline);
        final TextView airlineInputName = findViewById(R.id.airlineNameTextInput);

        Button addAirlineButton = findViewById(R.id.addNewAirlineButton);
        addAirlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(airlineInputName.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Airline name cannot be null", Toast.LENGTH_SHORT).show();
                }
                else {
                    Airline airline = new Airline(airlineInputName.getText().toString());
                    Intent intent = new Intent(AddAirlineActivity.this, MainActivity.class);
                    intent.putExtra("newAirline", airline);
                    startActivity(intent);
                }
            }
        });
    }
}
