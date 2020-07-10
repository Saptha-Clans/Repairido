package com.example.expresso.sapthagiri.yogirproduct;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewAddress extends AppCompatActivity {

    private EditText customer_name, address_one, address_two, city, state, pincode;
    private Button add_address;
    private String sCustomer_name, sAddress_one, sAddress_two, sCity, sState, sPincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address);

        customer_name = findViewById(R.id.customerName);
        address_one = findViewById(R.id.addressOne);
        address_two = findViewById(R.id.addressTwo);
        add_address = findViewById(R.id.addAddress);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        pincode = findViewById(R.id.pincode);

        final ProfileFragment profileFragment = new ProfileFragment();
        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(customer_name.getText().toString().equals("") || address_one.getText().toString().equals("") ||
                        address_two.getText().toString().equals("") || city.getText().toString().equals("") ||
                        state.getText().toString().equals("") || pincode.getText().toString().equals("")
                        ) {
                    Toast.makeText(AddNewAddress.this, "Please input all fields", Toast.LENGTH_LONG).show();
                }else {
                    sCustomer_name = customer_name.getText().toString();
                    sAddress_one = address_one.getText().toString();
                    sAddress_two = address_two.getText().toString();
                    sCity = city.getText().toString();
                    sState = state.getText().toString();
                    sPincode = pincode.getText().toString();

                    Intent intent = new Intent(AddNewAddress.this, ProfileManageAdressFragment.class);
                    intent.putExtra("customer_name", sCustomer_name);
                    intent.putExtra("address_one", sAddress_one);
                    intent.putExtra("address_two", sAddress_two);
                    intent.putExtra("city", sCity);
                    intent.putExtra("state", sState);
                    intent.putExtra("pincode", sPincode);
                    //startActivity(intent);
                    //startActivityForResult(intent, 1);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private void setFragment(android.support.v4.app.Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment).commit();
    }
}
