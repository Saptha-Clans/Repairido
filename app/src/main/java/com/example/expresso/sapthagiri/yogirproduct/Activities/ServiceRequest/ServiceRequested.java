package com.example.expresso.sapthagiri.yogirproduct.Activities.ServiceRequest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.example.expresso.sapthagiri.yogirproduct.Activities.CustomerHomePage.CustomerHomePage;
import com.example.expresso.sapthagiri.yogirproduct.R;

public class ServiceRequested extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_requested);
        LottieAnimationView email_verfication_sent = findViewById(R.id.emailVerificationAnim);

        //email_verfication_sent.setAnimationFromUrl("https://assets4.lottiefiles.com/packages/lf20_oKfcqB.json");
        //email_verfication_sent.playAnimation();
        //email_verfication_sent.setProgress((float) 1.0);
        Button login = findViewById(R.id.login_button);

        login.setOnClickListener(v -> {
            Intent intent = new Intent(ServiceRequested.this, CustomerHomePage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
