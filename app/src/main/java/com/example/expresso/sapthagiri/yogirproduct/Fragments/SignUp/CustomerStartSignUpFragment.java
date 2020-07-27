package com.example.expresso.sapthagiri.yogirproduct.Fragments.SignUp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.expresso.sapthagiri.yogirproduct.Activities.SignUpPage.CustomerSignUpActivity;
import com.example.expresso.sapthagiri.yogirproduct.R;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.regex.Pattern;

public class CustomerStartSignUpFragment extends Fragment {
    Button next;
    String[] descriptionData = {"Personal Details", "Submit"};
    TextInputLayout customer_first_name, customer_last_name, customer_email;
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^([^0-9]*)$" + "$");
    private static final Pattern NAME_PATTERN = Pattern.compile("(?=.*[a-zA-Z])" + "^((?=[A-Za-z@])(?![_~`!@#$%^&*)(+=}|:;'.>,<?/'{\\\\-]).)*$" + //no special character
            "$");
    private OnFragmentCustomerListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);

        customer_first_name = rootView.findViewById(R.id.customerFirstName);
        customer_last_name = rootView.findViewById(R.id.customerLastName);
        customer_email = rootView.findViewById(R.id.customerEmail);
        next = rootView.findViewById(R.id.next_button);

        next.setOnClickListener(v -> {
            String first_name, last_name, email;
            first_name = customer_first_name.getEditText().getText().toString().trim();
            last_name = customer_last_name.getEditText().getText().toString().trim();
            email = customer_email.getEditText().getText().toString().trim();

            if (!firstNameValidation(first_name, customer_first_name) | !lastNameValidation(last_name, customer_last_name) | !emailValidation(email, customer_email)) {
                return;
            }

            // Check phone number field is empty
            firstNameValidation(first_name, customer_first_name);

            // Check password field is empty
            lastNameValidation(last_name, customer_last_name);

            // Check re-enter password field is empty
            emailValidation(email, customer_email);

            mListener.onFragmentCustomer(first_name, last_name, email);
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

    private boolean firstNameValidation(String first_name, TextInputLayout customer_first_name) {
        if (first_name.isEmpty()) {
            customer_first_name.setError("What's your name?");
            return false;
        } else if (!NAME_PATTERN.matcher(first_name).matches()) {
            customer_first_name.setError("Cannot contain special characters and numbers");
            return false;
        } else if (!NUMBER_PATTERN.matcher(first_name).matches()) {
            customer_first_name.setError("Cannot contain special characters and numbers");
            return false;
        } else {
            customer_first_name.setError(null);
            return true;
        }
    }

    private boolean lastNameValidation(String last_name, TextInputLayout customer_last_name) {
        if (last_name.isEmpty()) {
            customer_last_name.setError("What's your name?");
            return false;
        } else if (!NAME_PATTERN.matcher(last_name).matches()) {
            customer_last_name.setError("Cannot contain special characters and numbers");
            return false;
        } else if (!NUMBER_PATTERN.matcher(last_name).matches()) {
            customer_last_name.setError("Cannot contain special characters and numbers");
            return false;
        } else {
            customer_last_name.setError(null);
            return true;
        }
    }

    private boolean emailValidation(String email, TextInputLayout customer_email) {
        if (email.isEmpty()) {
            customer_email.setError("You'll use this email for login and password reset");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            customer_email.setError("Enter a valid email address");
            return false;
        } else {
            customer_email.setError(null);
            return true;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentCustomerListener) {
            mListener = (OnFragmentCustomerListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement onFragmentCustomerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentCustomerListener {
        void onFragmentCustomer(String first_name, String last_name, String email);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mListener = (CustomerSignUpActivity) getActivity();
        }
    }
}
