package com.example.expresso.sapthagiri.yogirproduct;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ContactorProfilePage extends AppCompatActivity {

    private ImageView contractor_image;
    int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactor_profile_page);

        contractor_image = findViewById(R.id.consumerImage);
        Intent intent = getIntent();
        image = intent.getIntExtra("Image", 0);
        contractor_image.setImageResource(image);
    }
}
