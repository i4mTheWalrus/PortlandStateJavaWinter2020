package edu.pdx.cs410J.race3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final int REQUEST_CODE_SELECT_AIRLINNE = 1;
    ArrayList<Airline> airlineList;
    protected int airlinePos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        airlineList = new ArrayList<>();

        // Help button
        Button helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HelpPopupActivity.class));
            }
        });

        // search button
        Button searchButton = findViewById(R.id.searchButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
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
        try {
            airlineList = readFromFile(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(getIntent().hasExtra("newAirline")) {
            Airline airline = (Airline) getIntent().getSerializableExtra("newAirline");
            airlineList.add(airline);
            getIntent().removeExtra("newAirline");
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, airlineList);
        ListView listView = (ListView) findViewById(R.id.airlineListView);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Pass the airline to the next activity
                Intent intent = new Intent(MainActivity.this, FlightList.class);
                intent.putExtra("Airline", airlineList.get(position));
                airlinePos = airlineList.indexOf(airlineList.get(position));
                startActivityForResult(intent, REQUEST_CODE_SELECT_AIRLINNE);
            }
        };

        listView.setOnItemClickListener(messageClickedHandler);
    }

    @Override
    protected void onPause() {
        super.onPause();
        writeToFile(airlineList, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_SELECT_AIRLINNE) {
            if(data.hasExtra("updatedAirline")) {
                Airline updatedAirline = (Airline) data.getSerializableExtra("updatedAirline");
                data.removeExtra("updatedAirline");
                airlineList.set(airlinePos, updatedAirline);
                writeToFile(airlineList, this);
            }
        }
    }

    private void writeToFile(ArrayList<Airline> data, Context context) {
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            new BufferedOutputStream(
                                    new FileOutputStream(new File(context.getFilesDir(), "airlines.txt"))));
            oos.writeObject(data);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private ArrayList<Airline> readFromFile(Context context) throws IOException {

        ObjectInputStream input;
        String filename = "airlines.txt";
        ArrayList<Airline> airlines = null;
        try {
            input = new ObjectInputStream(new FileInputStream(new File(new File(getFilesDir(),"")+File.separator+filename)));
            airlines = (ArrayList<Airline>) input.readObject();
            input.close();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return airlines;
    }

    private void deleteAirlineFile() {
        deleteFile("airlines.txt");
    }
}
