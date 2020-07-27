package com.example.expresso.sapthagiri.yogirproduct.Activities.SignUpPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.expresso.sapthagiri.yogirproduct.Fragments.SignUp.CustomerCompleteSignUpFragment;
import com.example.expresso.sapthagiri.yogirproduct.Fragments.SignUp.CustomerStartSignUpFragment;
import com.example.expresso.sapthagiri.yogirproduct.R;

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
    public void onFragmentCustomer(String first_name, String last_name, String email) {
        Bundle bundle = new Bundle();
        bundle.putString("firstName", first_name);
        bundle.putString("lastName", last_name);
        bundle.putString("email", email);

        CustomerCompleteSignUpFragment customerCompleteSignUpFragment = new CustomerCompleteSignUpFragment();
        customerCompleteSignUpFragment.setArguments(bundle);
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
