package edu.pdx.cs410J.race3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Help button
        Button helpButton = findViewById(R.id.helpButton3);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, HelpPopupActivity.class));
            }
        });

        // add airlines to spinner
        String[] airlines = (String[]) getIntent().getSerializableExtra("airlineList");
        getIntent().removeExtra("airlineList");
        Spinner airlineSpinner = (Spinner)findViewById(R.id.airlineSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, airlines);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        airlineSpinner.setAdapter(adapter);

        // add airports to spinners
        List<String> airportCodes = AirportNames.getCodes();
        List<String> srcItems = new ArrayList<>();
        srcItems.add("Select Airport");
        for(String str : airportCodes) {
            srcItems.add(str);
        }
        String[] srcSpinnerItems = srcItems.toArray( new String[] {} );
        Spinner s1 = (Spinner)findViewById(R.id.srcSpinner);
        ArrayAdapter<String> srcAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, srcSpinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(srcAdapter);

        Spinner s2 = (Spinner)findViewById(R.id.destSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(srcAdapter);
    }
}
