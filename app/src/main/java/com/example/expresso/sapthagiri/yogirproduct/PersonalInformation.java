package com.example.expresso.sapthagiri.yogirproduct;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PersonalInformation extends AppCompatActivity {

    EditText full_name, phone_number, email;
    Button update, back;
    String sFull_name, sPhone_number, sEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        full_name = findViewById(R.id.fullName);
        phone_number = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.email);
        update = findViewById(R.id.submit);
        back = findViewById(R.id.backButton);

        Intent intent = getIntent();
        sFull_name = intent.getStringExtra("full_name");
        sPhone_number = intent.getStringExtra("phone_number");
        sEmail = intent.getStringExtra("email");

        System.out.println("***********Full Name" + sFull_name);

        full_name.setText(sFull_name);
        phone_number.setText(sPhone_number);
        email.setText(sEmail);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(full_name.getText().toString().equals("") || phone_number.getText().toString().equals("") ||
                        email.getText().toString().equals("")) {
                    Toast.makeText(PersonalInformation.this, "Please input all fields", Toast.LENGTH_LONG).show();
                }else {
                    sFull_name = full_name.getText().toString();
                    sPhone_number = phone_number.getText().toString();
                    sEmail = email.getText().toString();

                    Bundle bundle = new Bundle();
                    bundle.putString("full_name", sFull_name);
                    bundle.putString("phone_number", sPhone_number);
                    bundle.putString("email", sEmail);

                    ProfileFragment profileFragment = new ProfileFragment();
                    profileFragment.setArguments(bundle);
                    onBackPressed();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
