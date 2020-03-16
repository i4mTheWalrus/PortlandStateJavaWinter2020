package edu.pdx.cs410J.race3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {
    ArrayList<Airline> airlineList;
    ArrayList<Flight> flights;
    Spinner airlineSpinner, s1, s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        flights = new ArrayList<>();

        // Help button
        Button helpButton = findViewById(R.id.helpButton3);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, HelpPopupActivity.class));
            }
        });

        // add airlines to spinner
        airlineList = (ArrayList<Airline>) getIntent().getSerializableExtra("airlineList");
        getIntent().removeExtra("airlineList");

        ArrayList<String> airlineArrayList = new ArrayList<>();
        for(Airline air : airlineList) {
            airlineArrayList.add(air.getName());
        }
        String[] airlines = airlineArrayList.toArray( new String[] {} );

        airlineSpinner = (Spinner)findViewById(R.id.airlineSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, airlines);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        airlineSpinner.setAdapter(adapter);
        airlineSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateSearchResults();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // add airports to spinners
        List<String> airportCodes = AirportNames.getCodes();
        List<String> srcItems = new ArrayList<>();
        srcItems.add("Select Airport");
        for(String str : airportCodes) {
            srcItems.add(str);
        }
        String[] srcSpinnerItems = srcItems.toArray( new String[] {} );
        s1 = (Spinner)findViewById(R.id.srcSpinner);
        ArrayAdapter<String> srcAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, srcSpinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(srcAdapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateSearchResults();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        s2 = (Spinner)findViewById(R.id.destSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(srcAdapter);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateSearchResults();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateSearchResults();
    }

    protected void updateSearchResults() {
        String searchAirline = airlineSpinner.getSelectedItem().toString();
        String searchSrc = s1.getSelectedItem().toString();
        String searchDest = s2.getSelectedItem().toString();
        searchResults(searchAirline, searchSrc, searchDest);
        if(flights.size() > 0) {
            ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, flights);
            ListView listView = (ListView) findViewById(R.id.searchListView);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    private void searchResults(String searchAirline, String searchSrc, String searchDest) {
        flights = new ArrayList<>();
        for(Airline airline : airlineList) {
            if(airline.getName().equals(searchAirline)) {
                for(Flight flight : airline.getFlights()) {
                    if((flight.getSource().equals(searchSrc)) && (flight.getDestination().equals(searchDest))) {
                        flights.add(flight);
                    }
                }
            }
        }
    }
}
