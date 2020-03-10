package edu.pdx.cs410J.race3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ArrayList<Airline> airlineList = new ArrayList<>();

        // Help button
        Button helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HelpPopupActivity.class));
            }
        });

        // Add flight button
        Button addFlightButton = findViewById(R.id.addAirlineButton);
        addFlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Airline test = new Airline("testAir");
                Intent intent = new Intent(MainActivity.this, AddAirlineActivity.class);
                intent.putExtra("Airline", test);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(getIntent().hasExtra("Airline")) {
            Airline airline = (Airline) getIntent().getSerializableExtra("Airline");
            TextView view = (TextView) findViewById(R.id.testView);
            view.setText(airline.getName());
        }
    }

    private void writeToFile(ArrayList<Airline> data, Context context) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(context.openFileOutput("airlines.txt", Context.MODE_PRIVATE));
            out.writeObject(data);
            out.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private ArrayList<Airline> readFromFile(Context context) throws IOException {
        ObjectInputStream in = new ObjectInputStream(context.openFileInput("airlines.txt"));
        ArrayList<Airline> airlines = null;
        try {
            airlines = (ArrayList<Airline>) in.readObject();
            in.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return airlines;
    }
}
