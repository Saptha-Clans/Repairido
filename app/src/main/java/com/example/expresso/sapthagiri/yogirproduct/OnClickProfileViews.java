package com.example.expresso.sapthagiri.yogirproduct;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OnClickProfileViews extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_click_profile_views);

        ProfileManageAdressFragment profileManageAdressFragment = new ProfileManageAdressFragment();
        DashboardFragment dashboardFragment = new DashboardFragment();

        Intent intent = getIntent();

        int int_condition = getIntent().getExtras().getInt("manage_address");

        System.out.println("int condition" + int_condition);

        if(int_condition == 1) {
            intent = new Intent(OnClickProfileViews.this, ProfileManageAdressFragment.class);
            startActivity(intent);
        } else {
            setFragment(dashboardFragment);
        }

    }

    private void setFragment(android.support.v4.app.Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.onClickProfileLayout, fragment).commit();
    }

}














//int_condition = Integer.parseInt(String.valueOf(getIntent().getExtras().getInt("personal_information")));
//int_condition = intent.getIntExtra("personal_information", 0);

/*Bundle extras = getIntent().getExtras();
        String adapterPosition = extras.getString("personal_information");
        int int_condition = Integer.parseInt(adapterPosition);*/