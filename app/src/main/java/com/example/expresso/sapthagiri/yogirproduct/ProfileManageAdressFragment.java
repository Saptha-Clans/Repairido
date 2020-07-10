package com.example.expresso.sapthagiri.yogirproduct;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileManageAdressFragment extends AppCompatActivity {

    public static final String SHARED_PREFERENCE = "sharedPrefs";
    public static final String CUSTOMER_NAME = "name";
    public static final String ADDRESS_ONE = "addressOne";
    public static final String ADDRESS_TWO = "addressTwo";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String PINCODE = "pincode";

    RecyclerView recyclerView;
    ProfileManageAddressRecyclerAdapter adapter;
    ArrayList<ProfileManageAddressGetterSetter> reviews;
    String customer_name, address_one, address_two, city, state, pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button addAddress;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile_manage_adress);
        addAddress = findViewById(R.id.addNewAddress);
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileManageAdressFragment.this, AddNewAddress.class);
                startActivityForResult(intent, 5);
            }
        });
        reviews = new ArrayList<>();
        recyclerView = findViewById(R.id.addressRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProfileManageAdressFragment.this));

        /*System.out.println("*******Result obtained*******" + customer_name + " " + address_one + " " + address_two + " " + city + " " + state + " " + pincode);
        loadData();
        updateViews();*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 5 && resultCode == RESULT_OK) {
            customer_name = data.getStringExtra("customer_name");
            address_one = data.getStringExtra("address_one");
            address_two = data.getStringExtra("address_two");
            city = data.getStringExtra("city");
            state = data.getStringExtra("state");
            pincode = data.getStringExtra("pincode");

            System.out.println("*******************************CHECKING");

            reviews.add(new ProfileManageAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
            adapter = new ProfileManageAddressRecyclerAdapter(ProfileManageAdressFragment.this, reviews);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);

            //saveData(customer_name, address_one, address_two, city, state, pincode);

        }else if(requestCode == 0 && resultCode == RESULT_OK) {
            customer_name = data.getStringExtra("customer_name");
            address_one = data.getStringExtra("address_one");
            address_two = data.getStringExtra("address_two");
            city = data.getStringExtra("city");
            state = data.getStringExtra("state");
            pincode = data.getStringExtra("pincode");

            System.out.println("*******************************CHECKINGGG");

            reviews.add(1, new ProfileManageAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
            System.out.println("SIZE" + reviews.size());
            reviews.remove(0);
            adapter = new ProfileManageAddressRecyclerAdapter(ProfileManageAdressFragment.this, reviews);

            recyclerView.setAdapter(adapter);

        }else if(requestCode == 1 && resultCode == RESULT_OK) {
            customer_name = data.getStringExtra("customer_name");
            address_one = data.getStringExtra("address_one");
            address_two = data.getStringExtra("address_two");
            city = data.getStringExtra("city");
            state = data.getStringExtra("state");
            pincode = data.getStringExtra("pincode");

            System.out.println("*******************************CHECKINGGG");

            reviews.add(2, new ProfileManageAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
            System.out.println("SIZE" + reviews.size());
            reviews.remove(1);
            adapter = new ProfileManageAddressRecyclerAdapter(ProfileManageAdressFragment.this, reviews);

            recyclerView.setAdapter(adapter);

        }else if(requestCode == 2 && resultCode == RESULT_OK) {
            customer_name = data.getStringExtra("customer_name");
            address_one = data.getStringExtra("address_one");
            address_two = data.getStringExtra("address_two");
            city = data.getStringExtra("city");
            state = data.getStringExtra("state");
            pincode = data.getStringExtra("pincode");

            System.out.println("*******************************CHECKINGGG");

            reviews.add(3, new ProfileManageAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
            System.out.println("SIZE" + reviews.size());
            reviews.remove(2);
            adapter = new ProfileManageAddressRecyclerAdapter(ProfileManageAdressFragment.this, reviews);

            recyclerView.setAdapter(adapter);

        }
        else if(requestCode == 3 && resultCode == RESULT_OK) {
            customer_name = data.getStringExtra("customer_name");
            address_one = data.getStringExtra("address_one");
            address_two = data.getStringExtra("address_two");
            city = data.getStringExtra("city");
            state = data.getStringExtra("state");
            pincode = data.getStringExtra("pincode");

            System.out.println("*******************************CHECKINGGG");

            reviews.add(4, new ProfileManageAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
            System.out.println("SIZE" + reviews.size());
            reviews.remove(3);
            adapter = new ProfileManageAddressRecyclerAdapter(ProfileManageAdressFragment.this, reviews);

            recyclerView.setAdapter(adapter);

        }
        else if(resultCode == RESULT_CANCELED) {
            Toast.makeText(ProfileManageAdressFragment.this, "Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveData(String customer_name, String address_one, String address_two, String city, String state, String pincode) {
        Intent intent = getIntent();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(CUSTOMER_NAME, this.customer_name);
        editor.putString(ADDRESS_ONE, this.address_one);
        editor.putString(ADDRESS_TWO, this.address_two);
        editor.putString(CITY, this.city);
        editor.putString(STATE, this.state);
        editor.putString(PINCODE, this.pincode);

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);

        customer_name = sharedPreferences.getString(CUSTOMER_NAME, "");
        address_one = sharedPreferences.getString(ADDRESS_ONE, "");
        address_two = sharedPreferences.getString(ADDRESS_TWO, "");
        city = sharedPreferences.getString(CITY, "");
        state = sharedPreferences.getString(STATE, "");
        pincode = sharedPreferences.getString(PINCODE, "");

        System.out.println("*******Result obtained*******" + customer_name + " " + address_one + " " + address_two + " " + city + " " + state + " " + pincode);
    }

    public void updateViews() {
        reviews.add(new ProfileManageAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
    }
}









//reviews.add(new ProfileManageAddressGetterSetter("Customer Name", "The headers should be treatonth per address. ", "The headers should be treated list each month per address.", "Mountain view", "California", "641001"));

/*this.customer_name = intent.getStringExtra("customer_name");
        this.address_one = intent.getStringExtra("address_one");
        this.address_two = intent.getStringExtra("address_two");
        this.city = intent.getStringExtra("city");
        this.state = intent.getStringExtra("state");
        this.pincode = intent.getStringExtra("pincode");*/

/*
private Integer int_condition;
System.out.println("int condition" + int_condition);
if(getIntent() != null) {
            int int_condition = intent.getExtras().getInt("manage_address");
        }*/
        /*if(intent.getExtras() != null) {
            intent.getExtras().getInt(ProfileRecyclerAdapter.EXTRA_TEXT);
        }
        private void setFragment(android.support.v4.app.Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.onClickProfileLayout, fragment).commit();
    }*/

/*
System.out.println("**********GET INTENT***********" + getIntent() + "*************************");
if(int_condition == 1) {
            Button addAddress = findViewById(R.id.addNewAddress);
            reviews = new ArrayList<>();
            addAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfileManageAdressFragment.this, AddNewAddress.class);
                    startActivity(intent);
                    reviews.add(new ProfileManageAddressGetterSetter("Customer Name", "The headers should be treated like categories as in I would only use one address per card but need to list each month per address. ", "The headers should be treated like categories as in I would only use one address per card but need to list each month per address. "));
                    reviews.add(new ProfileManageAddressGetterSetter("Customer Name", "The headers should be treated like categories as in I would only use one address per card but need to list each month per address. ", "The headers should be treated like categories as in I would only use one address per card but need to list each month per address. "));
                    recyclerView = findViewById(R.id.addressRecyclerView);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ProfileManageAdressFragment.this));
                    adapter = new ProfileManageAddressRecyclerAdapter(this, reviews);
                    recyclerView.setAdapter(adapter);
                }
            });
        } else {
            setFragment(dashboardFragment);
        }*/

/*@Override
    public void onBackPressed() {
        Intent intent = new Intent(this, OnClickProfileViews.class);
        startActivity(intent);
    }*/


