package com.example.expresso.sapthagiri.yogirproduct.Adapters.CustomerHomePage;

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

import com.example.expresso.sapthagiri.yogirproduct.Fragments.CustomerHomePage.OngoingFragment;
import com.example.expresso.sapthagiri.yogirproduct.GetterSetter.CustomerHomePage.OngoingGetterSetter;
import com.example.expresso.sapthagiri.yogirproduct.Activities.CustomerHomePage.OngoingViewDetails;
import com.example.expresso.sapthagiri.yogirproduct.R;

import java.util.ArrayList;

public class OngoingRecyclerAdapter extends RecyclerView.Adapter<OngoingRecyclerAdapter.ViewHolder> {

    private ArrayList<OngoingGetterSetter> mDataset = new ArrayList<>();
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView ongoing_text_description, ongoing_title;
        private Button ongoing_view_details;

        public ViewHolder(View v) {
            super(v);
            ongoing_text_description = (TextView) v.findViewById(R.id.textDescription);
            ongoing_title = (TextView) v.findViewById(R.id.textTitle);
            ongoing_view_details = v.findViewById(R.id.textViewDetails);
            ongoing_view_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), OngoingViewDetails.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public OngoingRecyclerAdapter(OngoingFragment ongoingFragment, ArrayList<OngoingGetterSetter> dataset) {
        mDataset.clear();
        mDataset.addAll(dataset);
    }

    @Override
    public OngoingRecyclerAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_ongoing, parent, false);
        OngoingRecyclerAdapter.ViewHolder vh = new OngoingRecyclerAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull OngoingRecyclerAdapter.ViewHolder holder, int position) {
        //View view;
        OngoingGetterSetter ongoingGetterSetter = mDataset.get(position);
        holder.ongoing_text_description.setText(ongoingGetterSetter.getOngoing_description());
        holder.ongoing_title.setText(ongoingGetterSetter.getOngoing_title());


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}