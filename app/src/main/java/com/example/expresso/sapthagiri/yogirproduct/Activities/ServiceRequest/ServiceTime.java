package com.example.expresso.sapthagiri.yogirproduct.Activities.ServiceRequest;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expresso.sapthagiri.yogirproduct.R;
import com.example.expresso.sapthagiri.yogirproduct.Fragments.ServiceRequest.TimePicker;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.Calendar;

public class ServiceTime extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    String[] descriptionData = {"Enter Details", "Select Address", "Choose Time"};
    private DatePickerDialog.OnDateSetListener DateSetListener;
    Button choose_date, choose_time, submit;
    private TextView customer_name, city, state, pincode;
    private String sCustomer_name, sAddress_one, sAddress_two, sCity, sState, sPincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_time);
        choose_date = findViewById(R.id.chooseDate);
        choose_time = findViewById(R.id.chooseTime);
        submit = findViewById(R.id.submit);
        customer_name = findViewById(R.id.customerName);
        city = findViewById(R.id.cardCity);
        state = findViewById(R.id.cardState);
        pincode = findViewById(R.id.cardPincode);

        Intent intent = getIntent();
        sCustomer_name = intent.getStringExtra("customer_name");
        sAddress_one = intent.getStringExtra("address_one");
        sAddress_two = intent.getStringExtra("address_two");
        sCity = intent.getStringExtra("city");
        sState = intent.getStringExtra("state");
        sPincode = intent.getStringExtra("pincode");

        customer_name.setText(sCustomer_name);
        city.setText(sCity);
        state.setText(sState);
        pincode.setText(sPincode);

        init();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ServiceTime.this, "Service Requested Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ServiceTime.this, ServiceRequested.class);
                startActivity(intent);
            }
        });

        /*back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
*/
        choose_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });
        DateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = month + "-" + dayOfMonth + "-" + year;
                choose_date.setText(date);
            }
        };

        choose_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    public void init() {
        StateProgressBar stateProgressBar = findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateLineThickness(3);
        stateProgressBar.setDescriptionTopSpaceIncrementer(50);
        stateProgressBar.setStateDescriptionSize(13);
        stateProgressBar.setStateDescriptionData(descriptionData);
    }

    public void datePicker() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                ServiceTime.this,
                android.R.style.Theme_DeviceDefault_Dialog,
                DateSetListener,
                year,
                month,
                day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        choose_time.setText(hourOfDay + "." + minute + "pm");
    }
}