package com.example.expresso.sapthagiri.yogirproduct;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    RecyclerView recyclerView;
    ProfileRecyclerAdapter adapter;
    ArrayList<ProfileGetterSetter> reviews;
    ImageButton edit_button, save_button;
    TextView full_name, phone_number, email;
    String sFull_name, sPhone_number, sEmail;
    EditText edit_full_name, edit_phone_number, edit_email;

    /*public ProfileFragment() {
        // Required empty public constructor
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        if(getArguments() != null) {
            sFull_name = getArguments().getString("full_name");
            sPhone_number = getArguments().getString("phone_number");
            sEmail = getArguments().getString("email");
            System.out.println("*****sFull_name******" + sFull_name);
        }

        System.out.println("*****sFull_name******");
        view = inflater.inflate(R.layout.fragment_profile, container, false);


        full_name = view.findViewById(R.id.fullName);
        phone_number = view.findViewById(R.id.phoneNumber);
        email = view.findViewById(R.id.email);
        edit_button = view.findViewById(R.id.editButton);
        edit_full_name = view.findViewById(R.id.editFullNamel);
        edit_phone_number = view.findViewById(R.id.editPhoneNumber);
        edit_email = view.findViewById(R.id.editEmail);
        save_button = view.findViewById(R.id.saveButton);

        sFull_name = full_name.getText().toString();
        sPhone_number = phone_number.getText().toString();
        sEmail = email.getText().toString();

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getActivity().getApplicationContext(), PersonalInformation.class);
                intent.putExtra("full_name", sFull_name);
                intent.putExtra("phone_number", sPhone_number);
                intent.putExtra("email", sEmail);
                startActivity(intent);*/
                full_name.setVisibility(View.INVISIBLE);
                phone_number.setVisibility(View.INVISIBLE);
                email.setVisibility(View.INVISIBLE);
                edit_button.setVisibility(View.INVISIBLE);

                edit_full_name.setVisibility(View.VISIBLE);
                edit_phone_number.setVisibility(View.VISIBLE);
                edit_email.setVisibility(View.VISIBLE);
                save_button.setVisibility(View.VISIBLE);
            }
        });



        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sFull_name = edit_full_name.getText().toString();
                sPhone_number = edit_phone_number.getText().toString();
                sEmail = edit_email.getText().toString();

                edit_full_name.setVisibility(View.INVISIBLE);
                edit_phone_number.setVisibility(View.INVISIBLE);
                edit_email.setVisibility(View.INVISIBLE);
                save_button.setVisibility(View.INVISIBLE);

                full_name.setVisibility(View.VISIBLE);
                phone_number.setVisibility(View.VISIBLE);
                email.setVisibility(View.VISIBLE);
                edit_button.setVisibility(View.VISIBLE);

                full_name.setText(sFull_name);
                phone_number.setText(sPhone_number);
                email.setText(sEmail);
            }
        });



        CircleImageView circleImageView = view.findViewById(R.id.profileCircleImage);
        recyclerView = view.findViewById(R.id.profileRecyclerView);

        Glide
                .with(this)
                .load("https://images.unsplash.com/photo-1529888730501-a20357637532?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60")
                .into(circleImageView);

        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.custom_divider)));
        recyclerView.addItemDecoration(divider);

        reviews = new ArrayList<>();
        for(int i = 1; i < 7; i++) {
            if(i == 1) {
                reviews.add(new ProfileGetterSetter("Manage address", R.drawable.address));
            }else if(i == 2) {
                reviews.add(new ProfileGetterSetter("Change password", R.drawable.password));
            }else if(i == 3) {
                reviews.add(new ProfileGetterSetter("Two factor authentication", R.drawable.authentication));
            }/*else if(i == 4) {
                reviews.add(new ProfileGetterSetter("Customer support", R.drawable.support));
            }*/else if(i == 4) {
                reviews.add(new ProfileGetterSetter("Share", R.drawable.share));
            }else if(i == 5) {
                reviews.add(new ProfileGetterSetter("Rate", R.drawable.star));
            }else {
                reviews.add(new ProfileGetterSetter("Logout", R.drawable.logout));
            }
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ProfileRecyclerAdapter(this, reviews);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
