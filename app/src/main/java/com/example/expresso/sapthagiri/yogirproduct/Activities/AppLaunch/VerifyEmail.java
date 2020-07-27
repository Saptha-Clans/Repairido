package com.example.expresso.sapthagiri.yogirproduct.Activities.AppLaunch;

import android.os.SystemClock;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.expresso.sapthagiri.yogirproduct.NetworkServices.ServiceBuilder;
import com.example.expresso.sapthagiri.yogirproduct.R;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyEmail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);

        TextInputLayout email = findViewById(R.id.customerEmail);
        Button submit = findViewById(R.id.login_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customer_email = email.getEditText().getText().toString().trim();
                if(!emailValidation(customer_email, email)) {
                    return;
                }
                resetPassword(customer_email, email);
            }
        });
    }

    public void resetPassword(String customer_email, TextInputLayout email) {
        JsonObject consumer_data = new JsonObject();
        try {
            consumer_data.addProperty("email", customer_email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(consumer_data);

        Call<ResponseBody> call = ServiceBuilder.getInstance().getApi().verifyEmail("application/json", consumer_data);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Response Code: " + response.code());
                    email.setError("Password combination is not correct");
                    return;
                } else {
                    System.out.println("Response Code: " + response.code());
                    email.setError("");
                    //SystemClock.sleep(10000);
                    onBackPressed();
                    return;
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("Message: " + t.getMessage());
            }
        });
    }

    private boolean emailValidation(String email, TextInputLayout customer_email) {
        if (email.isEmpty()) {
            customer_email.setError("Verification link will be sent to this email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            customer_email.setError("Enter a valid email address");
            return false;
        } else {
            customer_email.setError(null);
            return true;
        }
    }
}
