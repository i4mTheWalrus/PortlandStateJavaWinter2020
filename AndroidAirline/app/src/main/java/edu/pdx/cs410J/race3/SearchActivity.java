package edu.pdx.cs410J.race3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Help button
        Button helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, HelpPopupActivity.class));
            }
        });


    }
}
