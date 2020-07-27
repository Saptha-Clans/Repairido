package com.example.expresso.sapthagiri.yogirproduct.Fragments.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.expresso.sapthagiri.yogirproduct.GetterSetter.HttpRequests.CustomerGetter;
import com.example.expresso.sapthagiri.yogirproduct.Activities.ServiceRequest.EmailVerification;
import com.example.expresso.sapthagiri.yogirproduct.R;
import com.example.expresso.sapthagiri.yogirproduct.NetworkServices.RetrofitServiceApi;
import com.google.gson.JsonObject;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.Objects;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ContractorCompleteSignUpFragment extends Fragment {

    String first_name, last_name, email, phone_number, password, re_enter_password;
    String[] descriptionData = {"Completed", "Submit"};
    RetrofitServiceApi retrofitServiceApi;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" + "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=!)(*}{|';:/?.,<>`~])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{4,}" +               //at least 4 characters
            "$");

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = null;
        rootView = inflater.inflate(R.layout.fragment_contractor_complete_sign_up, container, false);

        Button submit;
        TextInputLayout contractor_password, contractor_phone_number, contractor_re_enter_password;
        submit = rootView.findViewById(R.id.next_button);
        contractor_password = rootView.findViewById(R.id.contractorPassword);
        contractor_phone_number = rootView.findViewById(R.id.contractorPhone);
        contractor_re_enter_password = rootView.findViewById(R.id.contractorReEnterPassword);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            first_name = bundle.getString("firstName");
            last_name = bundle.getString("lastName");
            email = bundle.getString("email");
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone_number = contractor_phone_number.getEditText().getText().toString().trim();
                password = contractor_password.getEditText().getText().toString().trim();
                re_enter_password = contractor_re_enter_password.getEditText().getText().toString().trim();

                if (!phoneNumberValidation(phone_number, contractor_phone_number) | !passwordValidation(password, contractor_password) | !reEnterPasswordvalidation(re_enter_password, password, contractor_re_enter_password)) {
                    return;
                }

                // Check phone number field is empty
                phoneNumberValidation(phone_number, contractor_phone_number);

                // Check password field is empty
                passwordValidation(password, contractor_password);

                // Check re-enter password field is empty
                reEnterPasswordvalidation(re_enter_password, password, contractor_re_enter_password);

                //ServiceBuilder serviceBuilder = new ServiceBuilder();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.0.4:8000/yrtp/contractor/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitServiceApi = retrofit.create(RetrofitServiceApi.class);
                customerDetailsPost(first_name, last_name, email, phone_number, password);

                Intent intent = new Intent(getActivity().getApplicationContext() , EmailVerification.class);
                startActivity(intent);
            }
        });
        StateProgressBar stateProgressBar = rootView.findViewById(R.id.your_state_progress_bar_id);
        init(stateProgressBar);
        return rootView;
    }

    public void init(StateProgressBar stateProgressBar) {
        stateProgressBar.setStateLineThickness(3);
        stateProgressBar.setDescriptionTopSpaceIncrementer(50);
        stateProgressBar.setStateDescriptionSize(13);
        stateProgressBar.setStateDescriptionData(descriptionData);
    }

    private void customerDetailsPost(String first_name, String last_name, String email, String phone_number, String password) {
        CustomerGetter customerGetter = new CustomerGetter(first_name, last_name, email, email, password, phone_number);

        JsonObject consumer_data = new JsonObject();
        try {
            JsonObject consumer_details = new JsonObject();
            consumer_details.addProperty("first_name", first_name);
            consumer_details.addProperty("last_name", last_name);
            consumer_details.addProperty("username", email);
            consumer_details.addProperty("email", email);
            consumer_details.addProperty("password", password);
            consumer_data.add("user", consumer_details);
            consumer_data.addProperty("phone_number", phone_number);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(consumer_data);

        Call<CustomerGetter> call = retrofitServiceApi.createPost(consumer_data);
        call.enqueue(new Callback<CustomerGetter>() {
            @Override
            public void onResponse(Call<CustomerGetter> call, Response<CustomerGetter> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Objects.requireNonNull(getContext()).getApplicationContext(), "Email Already exists", Toast.LENGTH_LONG).show();
                    System.out.println("Response Code: " + response.code());
                    return;
                } else {
                    System.out.println("Response Code: " + response.code());
                    return;
                }
            }

            @Override
            public void onFailure(Call<CustomerGetter> call, Throwable t) {
                System.out.println("Message: " + t.getMessage());
            }
        });

    }


    private boolean phoneNumberValidation(String phone_number, TextInputLayout contractor_phone_number) {
        if (phone_number.isEmpty()) {
            contractor_phone_number.setError("What's your phone number?");
            return false;
        } else if (phone_number.length() < 10 || phone_number.length() > 10) {
            contractor_phone_number.setError("Enter a valid phone number");
            return false;
        } else {
            contractor_phone_number.setError("");
            return true;
        }
    }

    private boolean passwordValidation(String password, TextInputLayout contractor_password) {
        if (password.isEmpty()) {
            contractor_password.setError("You'll use this password for login");
            return false;
        } else if (password.length() < 8) {
            contractor_password.setError("Password must contain 8 characters");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            contractor_password.setError("Must contain atleast one Uppercase, Lowercase, number and special character");
            return false;
        } else {
            contractor_password.setError("");
            return true;
        }
    }

    private boolean reEnterPasswordvalidation(String re_enter_password, String password, TextInputLayout contractor_re_enter_password) {
        if (re_enter_password.isEmpty()) {
            contractor_re_enter_password.setError("You'll use this password for login");
            return false;
        } else if (re_enter_password.length() < 8) {
            contractor_re_enter_password.setError("Password must contain 8 characters");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(re_enter_password).matches()) {
            contractor_re_enter_password.setError("Must contain atleast one Uppercase, Lowercase, number and special character");
            return false;
        } else if (!(password.equals(re_enter_password))) {
            contractor_re_enter_password.setError("Password doesn't match");
            return false;
        } else {
            contractor_re_enter_password.setError("");
            return true;
        }
    }
}
