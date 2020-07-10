package com.example.expresso.sapthagiri.yogirproduct;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfileManageAddressRecyclerAdapter extends RecyclerView.Adapter<ProfileManageAddressRecyclerAdapter.ViewHolder> {

    private ArrayList<ProfileManageAddressGetterSetter> mDataset = new ArrayList<>();
    private Context context;

    public ProfileManageAddressRecyclerAdapter(Context context) {
        this.context = context;
    }

    private String sCustomer_name, sAddress_one, sAddress_two, sCity, sState, sPincode;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView customer_name, address_one, address_two, city, state, pincode;
        private Button edit, remove;

        public ViewHolder(View v) {
            super(v);
            customer_name = (TextView) v.findViewById(R.id.customerName);
            address_one = (TextView) v.findViewById(R.id.addressLineOne);
            address_two = v.findViewById(R.id.addressLineTwo);
            city = (TextView) v.findViewById(R.id.cardCity);
            state = (TextView) v.findViewById(R.id.cardState);
            pincode = v.findViewById(R.id.cardPincode);
            edit = v.findViewById(R.id.editAddress);
            remove = v.findViewById(R.id.removeAddress);
        }
    }

    public ProfileManageAddressRecyclerAdapter(ProfileManageAdressFragment profileManageAdressFragment, ArrayList<ProfileManageAddressGetterSetter> dataset) {
        mDataset.clear();
        mDataset.addAll(dataset);
    }

    @Override
    public ProfileManageAddressRecyclerAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_manage_address, parent, false);
        final ProfileManageAddressRecyclerAdapter.ViewHolder vh = new ProfileManageAddressRecyclerAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileManageAddressRecyclerAdapter.ViewHolder holder, int position) {
        ProfileManageAddressGetterSetter profileManageAddressGetterSetter = mDataset.get(position);
        holder.address_one.setText(profileManageAddressGetterSetter.getAddress_line_1());
        holder.address_two.setText(profileManageAddressGetterSetter.getGetAddress_line_2());
        holder.customer_name.setText(profileManageAddressGetterSetter.getContractor_name());
        holder.city.setText(profileManageAddressGetterSetter.getCity());
        holder.state.setText(profileManageAddressGetterSetter.getState());
        holder.pincode.setText(profileManageAddressGetterSetter.getPincode());
        final int position_check = holder.getAdapterPosition();
        System.out.println("*********" + position_check);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditNewAddress.class);
                switch (holder.getAdapterPosition()) {

                    case 0:
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

                        ((Activity) v.getContext()).startActivityForResult(intent,0);
                        break;
                    case 1:
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
                        ((Activity) v.getContext()).startActivityForResult(intent,1);
                        break;
                    case 2:
                        mDataset.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                        notifyItemRangeChanged(holder.getAdapterPosition(), mDataset.size());

                        Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                        System.out.println("*********" + holder.address_one.getText());

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
                        ((Activity) v.getContext()).startActivityForResult(intent,2);
                        break;
                    case 3:
                        mDataset.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                        notifyItemRangeChanged(holder.getAdapterPosition(), mDataset.size());

                        Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                        System.out.println("*********" + holder.address_one.getText());

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
                        ((Activity) v.getContext()).startActivityForResult(intent,3);
                        break;
                }
                /*if(position_check == 0) {
                }*/
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataset.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), mDataset.size());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}