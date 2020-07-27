package com.example.expresso.sapthagiri.yogirproduct.Activities.CustomerHomePage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.expresso.sapthagiri.yogirproduct.GetterSetter.HttpRequests.PasswordGetter;
import com.example.expresso.sapthagiri.yogirproduct.NetworkServices.ServiceBuilder;
import com.example.expresso.sapthagiri.yogirproduct.R;
import com.google.gson.JsonObject;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {

    Button submit;
    TextInputLayout old_password, new_password, re_enter_new_password;
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
        setContentView(R.layout.activity_change_password);
        submit = findViewById(R.id.submit);
        old_password = findViewById(R.id.oldPassword);
        new_password = findViewById(R.id.newPassword);
        re_enter_new_password = findViewById(R.id.reEnterPassword);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password, re_enter_password, customer_old_password;
                customer_old_password = old_password.getEditText().getText().toString().trim();
                password = new_password.getEditText().getText().toString().trim();
                re_enter_password = re_enter_new_password.getEditText().getText().toString().trim();
                if (!passwordValidation(password, new_password) | !reEnterPasswordvalidation(re_enter_password, password, re_enter_new_password)) {
                    return;
                }
                changePassword(customer_old_password, password, re_enter_new_password, old_password);
            }
        });
    }

    public void changePassword(String old_password, String password, TextInputLayout customer_password, TextInputLayout customer_old_password) {
        PasswordGetter passwordGetter = new PasswordGetter("");

        JsonObject consumer_data = new JsonObject();
        try {
            consumer_data.addProperty("old_password", old_password);
            consumer_data.addProperty("new_password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(consumer_data);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken = "Token " + preferences.getString("TOKEN", null);


        Call<PasswordGetter> call = ServiceBuilder.getInstance().getApi().changePassword(retrivedToken, "application/json", consumer_data);
        call.enqueue(new Callback<PasswordGetter>() {
            @Override
            public void onResponse(Call<PasswordGetter> call, Response<PasswordGetter> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Response Code: " + response.code());
                    System.out.println("response.body(): " + response.body());

                    PasswordGetter responseBody = response.body();
                    System.out.println("response.body(): " + responseBody.getError_message());

                    return;
                } else {
                    System.out.println("Response Code: " + response.code());
                    customer_password.setError("");
                    onBackPressed();
                    return;
                }
            }

            @Override
            public void onFailure(Call<PasswordGetter> call, Throwable t) {
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

    /*private boolean oldPasswordvalidation(String customer_old_password, TextInputLayout old_password) {
         |  !oldPasswordvalidation(customer_old_password, old_password)
    }*/
}
