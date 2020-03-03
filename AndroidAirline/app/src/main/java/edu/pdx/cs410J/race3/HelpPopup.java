package edu.pdx.cs410J.race3;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import edu.pdx.cs410J.race3.R;

public class HelpPopup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.8), (int)(height * 0.6));
    }
}
