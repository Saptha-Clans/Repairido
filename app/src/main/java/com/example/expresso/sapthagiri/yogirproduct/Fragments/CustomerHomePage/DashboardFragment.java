package com.example.expresso.sapthagiri.yogirproduct.Fragments.CustomerHomePage;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expresso.sapthagiri.yogirproduct.Adapters.CustomerHomePage.DashboardRecyclerAdapter;
import com.example.expresso.sapthagiri.yogirproduct.GetterSetter.CustomerHomePage.DashboardGetterSetter;
import com.example.expresso.sapthagiri.yogirproduct.R;
import com.example.expresso.sapthagiri.yogirproduct.Activities.ServiceRequest.ServiceDetails;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    RecyclerView recyclerView;
    DashboardRecyclerAdapter adapter;
    ArrayList<DashboardGetterSetter> items;
    FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        TextView fragment_filter = view.findViewById(R.id.filterLink);
        String fragment_filter_text = "Filter";
        SpannableString fragment_spanableObject = new SpannableString(fragment_filter_text);

        ClickableSpan fragment_clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                //Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
                FilterBottomDialog filterBottomDialog = new FilterBottomDialog();
                filterBottomDialog.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), "ModalMenu");
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
            }
        };
        fragment_spanableObject.setSpan(fragment_clickableSpan, 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        fragment_filter.setText(fragment_spanableObject);
        fragment_filter.setMovementMethod(LinkMovementMethod.getInstance());

        floatingActionButton = view.findViewById(R.id.requestIcon);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity().getApplicationContext() ,ServiceDetails.class);
                startActivity(intent);
            }
        });

        items = new ArrayList<>();
        for (int i = 105; i > 0; i--) {
            if (i % 2 == 0) {
                items.add(new DashboardGetterSetter("Contractor", "Category", "Specialize", R.drawable.consumer_profile_icon, (float) 2.5));
            } else if (i % 5 == 0) {
                items.add(new DashboardGetterSetter("Contractor", "Category", "Specialize", R.drawable.my_company_logo, (float) 4));
            } else {
                items.add(new DashboardGetterSetter("Contractor", "Category", "Specialize", R.drawable.yogi_r_home_page, (float) 3.5));
            }
        }
        recyclerView = view.findViewById(R.id.dashboardRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DashboardRecyclerAdapter(this, items);
        recyclerView.setAdapter(adapter);
        return view;
    }
}





