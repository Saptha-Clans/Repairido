package com.example.expresso.sapthagiri.yogirproduct.Fragments.SignUp;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expresso.sapthagiri.yogirproduct.R;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.regex.Pattern;

public class ContractorStartSignUpFragment extends Fragment {
    Button next;
    String[] descriptionData = {"Personal Details", "Submit"};
    TextInputLayout contractor_first_name, contractor_last_name, contractor_email, contractor_company;
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^([^0-9]*)$" +
            "$");
    private static final Pattern NAME_PATTERN = Pattern.compile("(?=.*[a-zA-Z])" +
            "^((?=[A-Za-z@])(?![_~`!@#$%^&*)(+=}|:;'.>,<?/'{\\\\-]).)*$" + //no special character
            "$");

    private OnFragmentContractorListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contractor_sign_up, container, false);
        contractor_first_name = rootView.findViewById(R.id.contractorFirstName);
        contractor_last_name = rootView.findViewById(R.id.contractorlastName);
        contractor_email = rootView.findViewById(R.id.contractorEmail);
        contractor_company = rootView.findViewById(R.id.companyName);
        next = rootView.findViewById(R.id.next_button);
        next.setOnClickListener(v -> {
            String first_name, last_name, email, company;
            first_name = contractor_first_name.getEditText().getText().toString().trim();
            last_name = contractor_last_name.getEditText().getText().toString().trim();
            email = contractor_email.getEditText().getText().toString().trim();
            company = contractor_company.getEditText().getText().toString().trim();

            if (!firstNameValidation(first_name, contractor_first_name) | !lastNameValidation(last_name, contractor_last_name) | !emailValidation(email, contractor_email)) {
                return;
            }

            // Check phone number field is empty
            firstNameValidation(first_name, contractor_first_name);

            // Check password field is empty
            lastNameValidation(last_name, contractor_last_name);

            // Check re-enter password field is empty
            emailValidation(email, contractor_email);

            mListener.onFragmentContractor(first_name, last_name, email, company);
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
            contractor_first_name.setError("What's your name?");
            return false;
        } else if (!NAME_PATTERN.matcher(first_name).matches()) {
            contractor_first_name.setError("Cannot contain special characters and numbers");
            return false;
        } else if (!NUMBER_PATTERN.matcher(first_name).matches()) {
            contractor_first_name.setError("Cannot contain special characters and numbers");
            return false;
        } else {
            contractor_first_name.setError(null);
            return true;
        }
    }

    private boolean lastNameValidation(String last_name, TextInputLayout contractor_last_name) {
        if (last_name.isEmpty()) {
            contractor_last_name.setError("What's your name?");
            return false;
        } else if (!NAME_PATTERN.matcher(last_name).matches()) {
            contractor_last_name.setError("Cannot contain special characters and numbers");
            return false;
        } else if (!NUMBER_PATTERN.matcher(last_name).matches()) {
            contractor_last_name.setError("Cannot contain special characters and numbers");
            return false;
        } else {
            contractor_last_name.setError(null);
            return true;
        }
    }

    private boolean emailValidation(String email, TextInputLayout contractor_email) {
        if (email.isEmpty()) {
            contractor_email.setError("You'll use this email for login and password reset");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            contractor_email.setError("Enter a valid email address");
            return false;
        } else {
            contractor_email.setError(null);
            return true;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentContractorListener) {
            mListener = (OnFragmentContractorListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement onFragmentContractorListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentContractorListener {
        void onFragmentContractor(String first_name, String last_name, String email, String company);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mListener = (OnFragmentContractorListener) getActivity();
        }
    }
}
