package com.example.expresso.sapthagiri.yogirproduct;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangePassword extends AppCompatActivity {

    Button submit, back;
    EditText old_password, new_password, re_enter_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        submit = findViewById(R.id.submit);
        back = findViewById(R.id.backButton);
        old_password = findViewById(R.id.oldPassword);
        new_password = findViewById(R.id.newPassword);
        re_enter_password = findViewById(R.id.reEnterPassword);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
