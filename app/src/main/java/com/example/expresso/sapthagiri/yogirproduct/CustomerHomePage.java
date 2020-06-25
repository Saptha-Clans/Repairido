package com.example.expresso.sapthagiri.yogirproduct;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class CustomerHomePage extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    private DashboardFragment dashboardFragment;
    private HistoryFragment historyFragment;
    private OngoingFragment ongoingFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home_page);

        historyFragment = new HistoryFragment();
        ongoingFragment = new OngoingFragment();
        profileFragment = new ProfileFragment();
        dashboardFragment = new DashboardFragment();

        setFragment(dashboardFragment);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_navigation);
        frameLayout = (FrameLayout) findViewById(R.id.main_frame);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.dashboard :
                        setFragment(dashboardFragment);
                        return true;

                    case R.id.history :
                        setFragment(historyFragment);
                        return true;

                    case R.id.ongoing :
                        setFragment(ongoingFragment);
                        return true;

                    case R.id.profile :
                        setFragment(profileFragment);
                        return true;

                        default:
                            return false;
                }
            }
        });
    }

    private void setFragment(android.support.v4.app.Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment).commit();
    }

}
