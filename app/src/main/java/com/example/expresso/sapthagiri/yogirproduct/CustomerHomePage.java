package com.example.expresso.sapthagiri.yogirproduct;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomerHomePage extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private FrameLayout frameLayout;
    private DrawerLayout drawerLayout;
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

        if(savedInstanceState == null) {
            setFragment(dashboardFragment);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.customerHomePageBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        //toolbar.setSubtitle("product");
        toolbar.setLogo(R.drawable.yogi_r_tech);

        final Intent intent = new Intent(CustomerHomePage.this, OngoingViewDetails.class);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.about_yrt:
                        startActivity(intent);
                        break;
                    case R.id.products:
                        startActivity(intent);
                        break;
                    case R.id.support:
                        startActivity(intent);
                        break;
                    case R.id.share:
                        startActivity(intent);
                        break;
                    case R.id.send:
                        startActivity(intent);
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_picture_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.profilePicture);
        View view = (View) MenuItemCompat.getActionView(menuItem);

        CircleImageView circleImageView = view.findViewById(R.id.circleImage);

        Glide
                .with(this)
                .load("https://images.unsplash.com/photo-1529888730501-a20357637532?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60")
                .into(circleImageView);

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomerHomePage.this, "Clicked", Toast.LENGTH_SHORT).show();
                setFragment(profileFragment);
                bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_navigation);
                frameLayout = (FrameLayout) findViewById(R.id.main_frame);
                bottomNavigationView.setSelectedItemId(R.id.profile);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.profilePicture:
                Toast.makeText(CustomerHomePage.this, "Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setFragment(android.support.v4.app.Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
