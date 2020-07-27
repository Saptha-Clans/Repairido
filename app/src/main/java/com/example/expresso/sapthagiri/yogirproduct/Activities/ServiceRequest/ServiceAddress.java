package com.example.expresso.sapthagiri.yogirproduct.Activities.ServiceRequest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.expresso.sapthagiri.yogirproduct.Activities.CustomerHomePage.AddNewAddress;
import com.example.expresso.sapthagiri.yogirproduct.Adapters.ServiceRequest.ServiceAddressAdapter;
import com.example.expresso.sapthagiri.yogirproduct.GetterSetter.ServiceRequest.AddressGetter;
import com.example.expresso.sapthagiri.yogirproduct.NetworkServices.ServiceBuilder;
import com.example.expresso.sapthagiri.yogirproduct.R;
import com.example.expresso.sapthagiri.yogirproduct.GetterSetter.ServiceRequest.ServiceAddressGetterSetter;
import com.google.gson.JsonObject;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceAddress extends AppCompatActivity {

    RecyclerView recyclerView;
    ServiceAddressAdapter adapter;
    ArrayList<ServiceAddressGetterSetter> reviews;
    String customer_name, address_one, address_two, city, state, pincode;
    String[] descriptionData = {"Enter Details", "Select Address", "Choose Time"};
    //private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_address);
        reviews = new ArrayList<>();
        recyclerView = findViewById(R.id.addressRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ServiceAddress.this));

        listAddress();
        //next = findViewById(R.id.submit);
        /*next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceAddress.this, ServiceTime.class);
                startActivity(intent);
            }
        });*/
        init();
        Button addAddress;
        addAddress = findViewById(R.id.addNewAddress);
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ServiceAddress.this, AddNewAddress.class);
                startActivityForResult(intent, 5);
            }
        });

    }

    private Boolean listAddress() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken = "Token " + preferences.getString("TOKEN", null);

        Call<List<AddressGetter>> call = ServiceBuilder.getInstance().getApi().getConsumerAddress(retrivedToken,"application/json");
        call.enqueue(new Callback<List<AddressGetter>>() {
            @Override
            public void onResponse(Call<List<AddressGetter>> call, Response<List<AddressGetter>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Response Code: " + response.code());
                    return;
                } else {
                    System.out.println("Response Code: " + response.code());
                    List<AddressGetter> posts = response.body();
                    for(AddressGetter addressGetter : posts) {
                        customer_name = addressGetter.getConsumer_name();
                        address_one = addressGetter.getAddressline1();
                        address_two = addressGetter.getArea();
                        city = addressGetter.getCity();
                        state = addressGetter.getState();
                        pincode = addressGetter.getPincode();
                        reviews.add(new ServiceAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
                        adapter = new ServiceAddressAdapter(ServiceAddress.this, reviews);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    }
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<AddressGetter>> call, Throwable t) {
                System.out.println("Message: " + t.getMessage());
            }
        });
        return true;
    }

    public void init() {
        StateProgressBar stateProgressBar = findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateLineThickness(3);
        stateProgressBar.setDescriptionTopSpaceIncrementer(50);
        stateProgressBar.setStateDescriptionSize(13);
        stateProgressBar.setStateDescriptionData(descriptionData);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        customer_name = data.getStringExtra("customer_name");
        address_one = data.getStringExtra("address_one");
        address_two = data.getStringExtra("address_two");
        city = data.getStringExtra("city");
        state = data.getStringExtra("state");
        pincode = data.getStringExtra("pincode");

        if(requestCode == 5 && resultCode == RESULT_OK) {

            reviews.add(new ServiceAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
            adapter = new ServiceAddressAdapter(ServiceAddress.this, reviews);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);

        }else if(requestCode == 0 && resultCode == RESULT_OK) {
            reviews.add(1, new ServiceAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
            System.out.println("SIZE" + reviews.size());
            reviews.remove(0);
            adapter = new ServiceAddressAdapter(ServiceAddress.this, reviews);
            recyclerView.setAdapter(adapter);

        }else if(requestCode == 1 && resultCode == RESULT_OK) {
            reviews.add(2, new ServiceAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
            System.out.println("SIZE" + reviews.size());
            reviews.remove(1);
            adapter = new ServiceAddressAdapter(ServiceAddress.this, reviews);
            recyclerView.setAdapter(adapter);

        }else if(requestCode == 2 && resultCode == RESULT_OK) {
            reviews.add(3, new ServiceAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
            System.out.println("SIZE" + reviews.size());
            reviews.remove(2);
            adapter = new ServiceAddressAdapter(ServiceAddress.this, reviews);
            recyclerView.setAdapter(adapter);

        }
        else if(requestCode == 3 && resultCode == RESULT_OK) {
            reviews.add(4, new ServiceAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
            System.out.println("SIZE" + reviews.size());
            reviews.remove(3);
            adapter = new ServiceAddressAdapter(ServiceAddress.this, reviews);
            recyclerView.setAdapter(adapter);
        }
        else if(requestCode == 4 && resultCode == RESULT_OK) {
            reviews.add(5, new ServiceAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
            System.out.println("SIZE" + reviews.size());
            reviews.remove(4);
            adapter = new ServiceAddressAdapter(ServiceAddress.this, reviews);
            recyclerView.setAdapter(adapter);
        }
        else if(requestCode == 6 && resultCode == RESULT_OK) {
            reviews.add(7, new ServiceAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
            System.out.println("SIZE" + reviews.size());
            reviews.remove(6);
            adapter = new ServiceAddressAdapter(ServiceAddress.this, reviews);
            recyclerView.setAdapter(adapter);
        }
        else if(requestCode == 7 && resultCode == RESULT_OK) {
            reviews.add(8, new ServiceAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
            System.out.println("SIZE" + reviews.size());
            reviews.remove(7);
            adapter = new ServiceAddressAdapter(ServiceAddress.this, reviews);
            recyclerView.setAdapter(adapter);
        }
        else if(requestCode == 8 && resultCode == RESULT_OK) {
            reviews.add(9, new ServiceAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
            System.out.println("SIZE" + reviews.size());
            reviews.remove(8);
            adapter = new ServiceAddressAdapter(ServiceAddress.this, reviews);
            recyclerView.setAdapter(adapter);
        }
        else if(requestCode == 9 && resultCode == RESULT_OK) {
            reviews.add(10, new ServiceAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
            System.out.println("SIZE" + reviews.size());
            reviews.remove(9);
            adapter = new ServiceAddressAdapter(ServiceAddress.this, reviews);
            recyclerView.setAdapter(adapter);
        } else if(requestCode == 10 && resultCode == RESULT_OK) {
            reviews.add(11, new ServiceAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
            System.out.println("SIZE" + reviews.size());
            reviews.remove(10);
            adapter = new ServiceAddressAdapter(ServiceAddress.this, reviews);
            recyclerView.setAdapter(adapter);
        }
    }
}


/*Intent intent = new Intent(v.getContext(), EditNewAddress.class);
                for(int i=holder.getAdapterPosition(); i==holder.getAdapterPosition();i++) {
                    mDataset.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    notifyItemRangeChanged(holder.getAdapterPosition(), mDataset.size());

                    Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    System.out.println("*********" + holder.address_one.getText());
                    System.out.println("ADAPTER POSITION" + holder.getAdapterPosition());

                    sCustomer_name = holder.customer_name.getText().toString();
                    sAddress_one = holder.address_one.getText().toString();
                    sAddress_two = holder.address_two.getText().toString();
                    sCity = holder.city.getText().toString();
                    sState = holder.state.getText().toString();
                    sPincode = holder.pincode.getText().toString();

                    intent.putExtra("customer_name", sCustomer_name);
                    intent.putExtra("address_one", sAddress_one);
                    intent.putExtra("address_two", sAddress_two);
                    intent.putExtra("city", sCity);
                    intent.putExtra("state", sState);
                    intent.putExtra("pincode", sPincode);

                    ((Activity) v.getContext()).startActivityForResult(intent,i);
                }

                for (int i = 0; i <= holder.getAdapterPosition(); i++) {
                    if(holder.getAdapterPosition() == i) {
                        Intent intent = new Intent(v.getContext(), EditNewAddress.class);
                        mDataset.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                        notifyItemRangeChanged(holder.getAdapterPosition(), mDataset.size());

                        System.out.println("ADAPTER POSITION" + holder.getAdapterPosition());

                        sCustomer_name = holder.customer_name.getText().toString();
                        sAddress_one = holder.address_one.getText().toString();
                        sAddress_two = holder.address_two.getText().toString();
                        sCity = holder.city.getText().toString();
                        sState = holder.state.getText().toString();
                        sPincode = holder.pincode.getText().toString();

                        intent.putExtra("customer_name", sCustomer_name);
                        intent.putExtra("address_one", sAddress_one);
                        intent.putExtra("address_two", sAddress_two);
                        intent.putExtra("city", sCity);
                        intent.putExtra("state", sState);
                        intent.putExtra("pincode", sPincode);

                        ((Activity) v.getContext()).startActivityForResult(intent, i);
                    }
                }


                for(int i = 0; i <= requestCode; i++) {
            if(requestCode == i && resultCode == RESULT_OK) {
                reviews.add(new ServiceAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
                adapter = new ServiceAddressAdapter(ServiceAddress.this, reviews);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                if(requestCode != 5) {
                    reviews.add(i+1, new ServiceAddressGetterSetter(customer_name, address_one, address_two, city, state, pincode));
                    System.out.println("SIZE" + reviews.size());
                    reviews.remove(i);
                    adapter = new ServiceAddressAdapter(ServiceAddress.this, reviews);
                    recyclerView.setAdapter(adapter);
                }
            }  else if(resultCode == RESULT_CANCELED) {
                Toast.makeText(ServiceAddress.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }*/