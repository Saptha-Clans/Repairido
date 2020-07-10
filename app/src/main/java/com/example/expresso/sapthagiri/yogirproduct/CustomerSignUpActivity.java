package com.example.expresso.sapthagiri.yogirproduct;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class CustomerSignUpActivity extends AppCompatActivity implements CustomerStartSignUpFragment.OnFragmentCustomerListener {

    CustomerStartSignUpFragment customerStartSignUpFragment;
    CustomerCompleteSignUpFragment customerCompleteSignUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        CustomerStartSignUpFragment customerStartSignUpFragment = new CustomerStartSignUpFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.start_sign_up, customerStartSignUpFragment)
                .commit();
    }

    @Override
    public void onFragmentCustomer() {
        CustomerCompleteSignUpFragment customerCompleteSignUpFragment = new CustomerCompleteSignUpFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.start_sign_up, customerCompleteSignUpFragment).addToBackStack(null).commit();
    }

    /*@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.fragment_sign_up);
        //View view = null;
        //LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //view = inflater.inflate(R.layout.fragment_sign_up, null);
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //view = inflater.inflate(R.layout.fragment_sign_up, null);
        }
    }*/


}
