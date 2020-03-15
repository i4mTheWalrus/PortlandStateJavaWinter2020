package edu.pdx.cs410J.race3;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import edu.pdx.cs410J.race3.R;

public class AddFlightActivity extends Activity {
    TextView departDate, arriveDate;
    DatePickerDialog.OnDateSetListener departDateListener, arriveDateListener;

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

        // depart date
        departDate = (TextView) findViewById(R.id.departDate);
        departDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddFlightActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        departDateListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        departDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month ++;
                String date = String.valueOf(month) + "/" + String.valueOf(dayOfMonth) + "/" + String.valueOf(year);
                departDate.setText(date);
            }
        };

        // arrive date
        arriveDate = (TextView) findViewById(R.id.arriveDate);
        arriveDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddFlightActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        arriveDateListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        arriveDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month ++;
                String date = String.valueOf(month) + "/" + String.valueOf(dayOfMonth) + "/" + String.valueOf(year);
                arriveDate.setText(date);
            }
        };
    }
}
