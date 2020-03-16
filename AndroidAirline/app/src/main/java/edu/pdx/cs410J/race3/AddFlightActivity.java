package edu.pdx.cs410J.race3;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddFlightActivity extends Activity {
    TextView departDate, arriveDate, departTime, arriveTime, flightNumView;
    DatePickerDialog.OnDateSetListener departDateListener, arriveDateListener;
    TimePickerDialog.OnTimeSetListener departTimeListener, arriveTimeListener;
    Button addFlight;
    Calendar cal;
    Spinner s1, s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight);
        cal = Calendar.getInstance();
        flightNumView = (TextView) findViewById(R.id.flightNumberTextbox);

        // Airline name header
        TextView airlineTextView = (TextView)findViewById(R.id.airlineNameText);
        final Airline airline = (Airline)getIntent().getSerializableExtra("Airline");
        airlineTextView.setText(airline.getName());


        List<String> airportCodes = AirportNames.getCodes();
        List<String> srcItems = new ArrayList<>();
        srcItems.add("Select Airport");
        for(String str : airportCodes) {
            srcItems.add(str);
        }
        String[] srcSpinnerItems = srcItems.toArray( new String[] {} );

        // src spinner
        s1 = (Spinner)findViewById(R.id.srcSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, srcSpinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);

        // dest spinner
        s2 = (Spinner)findViewById(R.id.destSpinner);
        s2.setAdapter(adapter);

        // depart date
        departDate = (TextView) findViewById(R.id.departDate);
        departDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        // depart time
        departTime = (TextView) findViewById(R.id.departTime);
        departTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int hour = cal.get(Calendar.HOUR);
            int minute = cal.get(Calendar.MINUTE);

            TimePickerDialog dialog = new TimePickerDialog(
                    AddFlightActivity.this,
                    android.R.style.Theme_Holo_Dialog_MinWidth,
                    departTimeListener,
                    hour, minute, true);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }});
        departTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String newMinute = String.valueOf(minute);
                if(minute < 10) {
                    newMinute = "0" + minute;
                }
                String time = String.valueOf(hourOfDay) + ":" + newMinute;
                departTime.setText(time);
            }
        };

        // arrive time
        arriveTime = (TextView) findViewById(R.id.arriveTime);
        arriveTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = cal.get(Calendar.HOUR);
                int minute = cal.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(
                        AddFlightActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        arriveTimeListener,
                        hour, minute, true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }});
        arriveTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String newMinute = String.valueOf(minute);
                if(minute < 10) {
                    newMinute = "0" + minute;
                }
                String time = String.valueOf(hourOfDay) + ":" + newMinute;
                arriveTime.setText(time);
            }
        };



        // Submit button
        addFlight = findViewById(R.id.submitNewFlight);
        addFlight.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if(flightNumView.getText().toString().matches("") | s1.getSelectedItem().toString().equals("Select Airport") |
                        (departDate.getText().toString().matches("Depart Date")) | (departTime.getText().toString().matches("Depart Time")) |
                        (arriveDate.getText().toString().matches("Arrive Date")) | (arriveTime.getText().toString().matches("Arrive Time")) |
                        s2.getSelectedItem().toString().equals("Select Airport")) {
                    Toast.makeText(AddFlightActivity.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                }
                else {
                    // build the flight
                    flightNumView = (TextView)findViewById(R.id.flightNumberTextbox);
                    Flight flight = new Flight(
                            airline.getName(),
                            flightNumView.getText().toString(),
                            s1.getSelectedItem().toString(),
                            departDate.getText().toString(),
                            departTime.getText().toString(),
                            s2.getSelectedItem().toString(),
                            arriveDate.getText().toString(),
                            arriveTime.getText().toString()
                    );

                    final Intent intent = new Intent();
                    intent.putExtra("newFlight", flight);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
