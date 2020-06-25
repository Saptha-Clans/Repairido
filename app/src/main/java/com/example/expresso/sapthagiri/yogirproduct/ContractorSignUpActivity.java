package com.example.expresso.sapthagiri.yogirproduct;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
    public void onFragmentContractor() {
        ContractorCompleteSignUpFragment contractorCompleteSignUpFragment = new ContractorCompleteSignUpFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.start_sign_up, contractorCompleteSignUpFragment).addToBackStack(null).commit();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.fragment_contractor_sign_up);
        //setContentView(R.layout.fragment_contractor_complete_sign_up);
    }

}
