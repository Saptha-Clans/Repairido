package com.example.expresso.sapthagiri.yogirproduct;


import android.graphics.Color;
import android.os.Bundle;
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

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    RecyclerView recyclerView;
    HistoryRecyclerAdapter adapter;
    ArrayList<HistoryGetterSetter> reviews;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_ongoing, container, false);

        reviews = new ArrayList<>();
        for(int i = 105; i > 0; i--) {
            if(i%2 == 0 || i % 5 == 0) {
                reviews.add(new HistoryGetterSetter("Request title", "Success is not final(:)); failure is not fatal: It is the courage to continue that counts."));
            }
            reviews.add(new HistoryGetterSetter("Request title", "Success is not final(:)); failure is not fatal: It is the courage to continue that counts."));
        }
        recyclerView = view.findViewById(R.id.ongoingRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HistoryRecyclerAdapter(this, reviews);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
