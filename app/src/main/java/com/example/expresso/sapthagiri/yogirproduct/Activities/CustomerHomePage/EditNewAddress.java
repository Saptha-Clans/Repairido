package com.example.expresso.sapthagiri.yogirproduct.Activities.CustomerHomePage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expresso.sapthagiri.yogirproduct.Fragments.CustomerHomePage.ProfileManageAdressFragment;
import com.example.expresso.sapthagiri.yogirproduct.NetworkServices.ServiceBuilder;
import com.example.expresso.sapthagiri.yogirproduct.R;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditNewAddress extends AppCompatActivity {

    String sCustomer_name, sAddress_one, sAddress_two, sCity, sState, sPincode;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address);
        TextInputLayout customer_name, address_one, address_two, city, state, pincode;
        EditText ecustomer_name, eaddress_one, eaddress_two, ecity, estate, epincode;
        Button add_address;

        customer_name = findViewById(R.id.customerName);
        address_one = findViewById(R.id.addressOne);
        address_two = findViewById(R.id.addressTwo);
        add_address = findViewById(R.id.addAddress);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        pincode = findViewById(R.id.pincode);

        ecustomer_name = findViewById(R.id.editCustomerName);
        eaddress_one = findViewById(R.id.editAddressOne);
        eaddress_two = findViewById(R.id.editAddressTwo);
        ecity = findViewById(R.id.editCity);
        estate = findViewById(R.id.editState);
        epincode = findViewById(R.id.editPincode);

        Intent intent = getIntent();
        sCustomer_name = intent.getStringExtra("customer_name");
        sAddress_one = intent.getStringExtra("address_one");
        sAddress_two = intent.getStringExtra("address_two");
        sCity = intent.getStringExtra("city");
        sState = intent.getStringExtra("state");
        sPincode = intent.getStringExtra("pincode");
        position = 1 + intent.getIntExtra("adapter_position", 0);
        System.out.println("***************"+position);

        ecustomer_name.setText(sCustomer_name);
        eaddress_one.setText(sAddress_one);
        eaddress_two.setText(sAddress_two);
        ecity.setText(sCity);
        estate.setText(sState);
        epincode.setText(sPincode);

        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(customer_name.getEditText().getText().toString().equals("") || address_one.getEditText().getText().toString().equals("") ||
                        address_two.getEditText().getText().toString().equals("") || city.getEditText().getText().toString().equals("") ||
                        state.getEditText().getText().toString().equals("") || pincode.getEditText().getText().toString().equals("")
                        ) {
                    Toast.makeText(EditNewAddress.this, "Please input all fields", Toast.LENGTH_LONG).show();
                } else {
                    sCustomer_name = customer_name.getEditText().getText().toString();
                    sAddress_one = address_one.getEditText().getText().toString();
                    sAddress_two = address_two.getEditText().getText().toString();
                    sCity = city.getEditText().getText().toString();
                    sState = state.getEditText().getText().toString();
                    sPincode = pincode.getEditText().getText().toString();

                    Intent intent = new Intent(EditNewAddress.this, ProfileManageAdressFragment.class);
                    intent.putExtra("customer_name", sCustomer_name);
                    intent.putExtra("address_one", sAddress_one);
                    intent.putExtra("address_two", sAddress_two);
                    intent.putExtra("city", sCity);
                    intent.putExtra("state", sState);
                    intent.putExtra("pincode", sPincode);
                    if(!fieldValidation(sCustomer_name, sAddress_one, sAddress_two, sCity, sState, sPincode, pincode)) {
                        return;
                    }
                    customerAddAddress(sCustomer_name, sAddress_one, sAddress_two, sCity, sState, sPincode);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private Boolean customerAddAddress(String consumer_name, String address_one, String address_two, String city, String state, String pincode) {

        JsonObject consumer_details = new JsonObject();
        try {
            consumer_details.addProperty("consumer_name", consumer_name);
            consumer_details.addProperty("addressline1", address_one);
            consumer_details.addProperty("area", address_two);
            consumer_details.addProperty("city", city);
            consumer_details.addProperty("state", state);
            consumer_details.addProperty("pincode", pincode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(consumer_details);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken = "Token " + preferences.getString("TOKEN", null);
        Call<ResponseBody> call = ServiceBuilder.getInstance().getApi().updateAddress(retrivedToken,"application/json", consumer_details, position);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Response Code: " + response.code());
                    return;
                } else {
                    System.out.println("Response Code: " + response.code());
                    return;
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("Message: " + t.getMessage());
            }
        });
        return true;
    }

    private boolean fieldValidation(String consumer_name, String address_one, String address_two, String city, String state, String pincode, TextInputLayout pincodeLayout) {
        if (consumer_name.isEmpty() || address_one.isEmpty() || address_two.isEmpty() || city.isEmpty() || state.isEmpty() || pincode.isEmpty()) {
            pincodeLayout.setError("Input all the mandatory fields");
            return false;
        } else if (pincode.length() < 6 || pincode.length() >= 7) {
            pincodeLayout.setError("Enter a valid pincode");
            return false;
        } else {
            pincodeLayout.setError(null);
            return true;
        }
    }
}
