package com.example.expresso.sapthagiri.yogirproduct.Activities.AppLaunch;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.expresso.sapthagiri.yogirproduct.NetworkServices.ServiceBuilder;
import com.example.expresso.sapthagiri.yogirproduct.R;
import com.google.gson.JsonObject;

import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" + "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=!)(*}{|';:/?.,<>`~])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{4,}" +               //at least 4 characters
            "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Button submit;
        TextView success = findViewById(R.id.successMsg);
        TextInputLayout new_password, re_enter_new_password;
        submit = findViewById(R.id.submit);
        new_password = findViewById(R.id.newPassword);
        re_enter_new_password = findViewById(R.id.reEnterPassword);
        LottieAnimationView submit_success = findViewById(R.id.submitSuccessAnim);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password, re_enter_password;
                password = new_password.getEditText().getText().toString().trim();
                re_enter_password = re_enter_new_password.getEditText().getText().toString().trim();
                if(!passwordValidation(password, new_password) | !reEnterPasswordvalidation(re_enter_password, password, re_enter_new_password)) {
                    return;
                }
                resetPassword(password, re_enter_password, re_enter_new_password, submit_success, success, submit);
            }
        });
    }

    public void resetPassword(String old_password, String password, TextInputLayout customer_password, LottieAnimationView submit_success, TextView success, Button submit) {
        JsonObject consumer_data = new JsonObject();
        try {
            consumer_data.addProperty("password", old_password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(consumer_data);

        Call<ResponseBody> call = ServiceBuilder.getInstance().getApi().resetPassword("application/json", consumer_data);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Response Code: " + response.code());
                    customer_password.setError("Password combination is not correct");
                    /*String reponse_text = response.body().toString();
                    if(reponse_text.equals("This Password has been already used")) {
                        customer_password.setError("Old password and new password cannot be same");
                    } else if(reponse_text.equals("Please enter the correct old password")) {
                        customer_old_password.setError("Incorrect old passowrd");
                    } else {
                        customer_password.setError("Password combination is not correct");
                    }*/
                    return;
                } else {
                    System.out.println("Response Code: " + response.code());
                    submit.setVisibility(View.INVISIBLE);
                    submit_success.setVisibility(View.VISIBLE);
                    success.setVisibility(View.VISIBLE);
                    customer_password.setError("");
                    SystemClock.sleep(10000);
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

    private boolean passwordValidation(String password, TextInputLayout customer_password) {
        if (password.isEmpty()) {
            customer_password.setError("You'll use this password for login");
            return false;
        } else if (password.length() < 8) {
            customer_password.setError("Password must contain 8 characters");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            customer_password.setError("Must contain atleast one Uppercase, Lowercase, number and special character");
            return false;
        } else {
            customer_password.setError("");
            return true;
        }
    }

    private boolean reEnterPasswordvalidation(String re_enter_password, String password, TextInputLayout customer_re_enter_password) {
        if (re_enter_password.isEmpty()) {
            customer_re_enter_password.setError("You'll use this password for login");
            return false;
        } else if (re_enter_password.length() < 8) {
            customer_re_enter_password.setError("Password must contain 8 characters");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(re_enter_password).matches()) {
            customer_re_enter_password.setError("Must contain atleast one Uppercase, Lowercase, number and special character");
            return false;
        } else if (!(password.equals(re_enter_password))) {
            customer_re_enter_password.setError("Password doesn't match");
            return false;
        } else {
            customer_re_enter_password.setError("");
            return true;
        }
    }
}
