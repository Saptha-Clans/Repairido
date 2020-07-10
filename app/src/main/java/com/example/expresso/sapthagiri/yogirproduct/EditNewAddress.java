package com.example.expresso.sapthagiri.yogirproduct;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditNewAddress extends AppCompatActivity {

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

        Intent intent = getIntent();
        sCustomer_name = intent.getStringExtra("customer_name");
        sAddress_one = intent.getStringExtra("address_one");
        sAddress_two = intent.getStringExtra("address_two");
        sCity = intent.getStringExtra("city");
        sState = intent.getStringExtra("state");
        sPincode = intent.getStringExtra("pincode");

        customer_name.setText(sCustomer_name);
        address_one.setText(sAddress_one);
        address_two.setText(sAddress_two);
        city.setText(sCity);
        state.setText(sState);
        pincode.setText(sPincode);

        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(customer_name.getText().toString().equals("") || address_one.getText().toString().equals("") ||
                        address_two.getText().toString().equals("") || city.getText().toString().equals("") ||
                        state.getText().toString().equals("") || pincode.getText().toString().equals("")
                        ) {
                    Toast.makeText(EditNewAddress.this, "Please input all fields", Toast.LENGTH_LONG).show();
                }else {
                    sCustomer_name = customer_name.getText().toString();
                    sAddress_one = address_one.getText().toString();
                    sAddress_two = address_two.getText().toString();
                    sCity = city.getText().toString();
                    sState = state.getText().toString();
                    sPincode = pincode.getText().toString();

                    Intent intent = new Intent(EditNewAddress.this, ProfileManageAdressFragment.class);
                    intent.putExtra("customer_name", sCustomer_name);
                    intent.putExtra("address_one", sAddress_one);
                    intent.putExtra("address_two", sAddress_two);
                    intent.putExtra("city", sCity);
                    intent.putExtra("state", sState);
                    intent.putExtra("pincode", sPincode);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
