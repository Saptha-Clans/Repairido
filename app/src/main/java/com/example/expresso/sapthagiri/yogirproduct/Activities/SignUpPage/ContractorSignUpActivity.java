package com.example.expresso.sapthagiri.yogirproduct.Activities.SignUpPage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.expresso.sapthagiri.yogirproduct.Fragments.SignUp.ContractorCompleteSignUpFragment;
import com.example.expresso.sapthagiri.yogirproduct.Fragments.SignUp.ContractorStartSignUpFragment;
import com.example.expresso.sapthagiri.yogirproduct.R;

public class ContractorSignUpActivity extends AppCompatActivity implements ContractorStartSignUpFragment.OnFragmentContractorListener {

    ContractorStartSignUpFragment contractorStartSignUpFragment;
    ContractorCompleteSignUpFragment contractorCompleteSignUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_sign_up);

        ContractorStartSignUpFragment contractorStartSignUpFragment = new ContractorStartSignUpFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.start_sign_up, contractorStartSignUpFragment)
                .commit();
    }

    @Override
    public void onFragmentContractor(String first_name, String last_name, String email, String company) {
        Bundle bundle = new Bundle();
        bundle.putString("firstName", first_name);
        bundle.putString("lastName", last_name);
        bundle.putString("email", email);
        bundle.putString("company", company);
        ContractorCompleteSignUpFragment contractorCompleteSignUpFragment = new ContractorCompleteSignUpFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.start_sign_up, contractorCompleteSignUpFragment).addToBackStack(null).commit();
    }


    /*@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.fragment_contractor_sign_up);
        //setContentView(R.layout.fragment_contractor_complete_sign_up);
    }*/

}
