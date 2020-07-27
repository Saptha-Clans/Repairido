package com.example.expresso.sapthagiri.yogirproduct.Adapters.ServiceRequest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expresso.sapthagiri.yogirproduct.Activities.CustomerHomePage.CustomerHomePage;
import com.example.expresso.sapthagiri.yogirproduct.Activities.CustomerHomePage.EditNewAddress;
import com.example.expresso.sapthagiri.yogirproduct.Activities.ServiceRequest.ServiceTime;
import com.example.expresso.sapthagiri.yogirproduct.GetterSetter.HttpRequests.LoginGetter;
import com.example.expresso.sapthagiri.yogirproduct.NetworkServices.ServiceBuilder;
import com.example.expresso.sapthagiri.yogirproduct.R;
import com.example.expresso.sapthagiri.yogirproduct.Activities.ServiceRequest.ServiceAddress;
import com.example.expresso.sapthagiri.yogirproduct.GetterSetter.ServiceRequest.ServiceAddressGetterSetter;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceAddressAdapter extends RecyclerView.Adapter<ServiceAddressAdapter.ViewHolder> {

    private ArrayList<ServiceAddressGetterSetter> mDataset = new ArrayList<>();
    private Context context;

    public ServiceAddressAdapter(Context context) {
        this.context = context;
    }

    private String sCustomer_name, sAddress_one, sAddress_two, sCity, sState, sPincode;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView customer_name, address_one, address_two, city, state, pincode;
        private Button edit, remove;
        ConstraintLayout constraintLayout;

        public ViewHolder(View v) {
            super(v);
            customer_name = (TextView) v.findViewById(R.id.customerName);
            address_one = (TextView) v.findViewById(R.id.addressLineOne);
            address_two = v.findViewById(R.id.addressLineTwo);
            city = (TextView) v.findViewById(R.id.cardCity);
            constraintLayout = v.findViewById(R.id.constraintLayout);
            state = (TextView) v.findViewById(R.id.cardState);
            pincode = v.findViewById(R.id.cardPincode);
            edit = v.findViewById(R.id.editAddress);
            remove = v.findViewById(R.id.removeAddress);
        }
    }

    public ServiceAddressAdapter(ServiceAddress serviceAddress, ArrayList<ServiceAddressGetterSetter> dataset) {
        mDataset.clear();
        mDataset.addAll(dataset);
    }

    @Override
    public ServiceAddressAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_service_address, parent, false);
        final ServiceAddressAdapter.ViewHolder vh = new ServiceAddressAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ServiceAddressAdapter.ViewHolder holder, int position) {
        ServiceAddressGetterSetter serviceAddressGetterSetter = mDataset.get(position);
        holder.address_one.setText(serviceAddressGetterSetter.getAddress_line_1());
        holder.address_two.setText(serviceAddressGetterSetter.getGetAddress_line_2());
        holder.customer_name.setText(serviceAddressGetterSetter.getContractor_name());
        holder.city.setText(serviceAddressGetterSetter.getCity());
        holder.state.setText(serviceAddressGetterSetter.getState());
        holder.pincode.setText(serviceAddressGetterSetter.getPincode());
        final int position_check = holder.getAdapterPosition();
        System.out.println("*********" + position_check);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), ServiceTime.class);
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

                /*intent.putExtra("customer_name", serviceAddressGetterSetter.getContractor_name());
                intent.putExtra("address_one", serviceAddressGetterSetter.getAddress_line_1());
                intent.putExtra("address_two", serviceAddressGetterSetter.getGetAddress_line_2());
                intent.putExtra("city", serviceAddressGetterSetter.getCity());
                intent.putExtra("state", serviceAddressGetterSetter.getState());
                intent.putExtra("pincode", serviceAddressGetterSetter.getPincode());*/
                v.getContext().startActivity(intent);
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditNewAddress.class);
                switch (holder.getAdapterPosition()) {

                    case 0:
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
                        intent.putExtra("adapter_position", 0);

                        ((Activity) v.getContext()).startActivityForResult(intent, 0);
                        break;
                    case 1:
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
                        intent.putExtra("adapter_position", 1);
                        ((Activity) v.getContext()).startActivityForResult(intent, 1);
                        break;
                    case 2:
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
                        intent.putExtra("adapter_position", 2);
                        ((Activity) v.getContext()).startActivityForResult(intent, 2);
                        break;
                    case 3:
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
                        intent.putExtra("adapter_position", 3);
                        ((Activity) v.getContext()).startActivityForResult(intent, 3);
                        break;
                    case 4:
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
                        ((Activity) v.getContext()).startActivityForResult(intent, 4);
                        break;
                    case 5:
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
                        ((Activity) v.getContext()).startActivityForResult(intent, 5);
                        break;
                    case 6:
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
                        ((Activity) v.getContext()).startActivityForResult(intent, 6);
                        break;
                    case 7:
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
                        ((Activity) v.getContext()).startActivityForResult(intent, 7);
                        break;
                    case 8:
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
                        ((Activity) v.getContext()).startActivityForResult(intent, 8);
                        break;
                    case 9:
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
                        ((Activity) v.getContext()).startActivityForResult(intent, 9);
                        break;
                    case 10:
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
                        ((Activity) v.getContext()).startActivityForResult(intent, 10);
                        break;
                }
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(v.getContext(), ServiceAddress.class);

                JsonObject remove_address = new JsonObject();
                try {
                    remove_address.addProperty("address_id", "3");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                SharedPreferences preferences = v.getContext().getSharedPreferences("MY_APP", v.getContext().MODE_PRIVATE);
                String retrivedToken = "Token " + preferences.getString("TOKEN", null);

                Call<ResponseBody> call = ServiceBuilder.getInstance().getApi().deleteAddress(retrivedToken, "application/json", remove_address);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()) {
                            System.out.println("Response Code: " + response.code());
                        }
                        else{
                            System.out.println("Response Code: " + response.code());
                            mDataset.remove(holder.getAdapterPosition());
                            notifyItemRemoved(holder.getAdapterPosition());
                            notifyItemRangeChanged(holder.getAdapterPosition(), mDataset.size());
                            notifyDataSetChanged();
                        }

                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println("Message: " + t.getMessage());
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}


/*switch (holder.getAdapterPosition()) {
                    case 0:
                        ((Activity) v.getContext()).startActivityForResult(intent, 0);
                    case 1:
                        ((Activity) v.getContext()).startActivityForResult(intent, 1);
                }
                mDataset.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), mDataset.size());
                notifyDataSetChanged();*/