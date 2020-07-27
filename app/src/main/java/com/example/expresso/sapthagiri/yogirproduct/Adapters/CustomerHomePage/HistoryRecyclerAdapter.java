package com.example.expresso.sapthagiri.yogirproduct.Adapters.CustomerHomePage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expresso.sapthagiri.yogirproduct.Activities.CustomerHomePage.HistoryEditReview;
import com.example.expresso.sapthagiri.yogirproduct.Fragments.CustomerHomePage.HistoryFragment;
import com.example.expresso.sapthagiri.yogirproduct.GetterSetter.CustomerHomePage.HistoryGetterSetter;
import com.example.expresso.sapthagiri.yogirproduct.R;

import java.util.ArrayList;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder> {

    private ArrayList<HistoryGetterSetter> mDataset = new ArrayList<>();
    private TextView history_contractor_profile, history_review;
    private Button history_edit_review, history_view_contractor_profile;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView history_text_description, history_title;
        private Button history_edit_review, history_view_contractor_profile;

        public ViewHolder(View v) {
            super(v);
            history_text_description = (TextView) v.findViewById(R.id.textDescription);
            history_title = (TextView) v.findViewById(R.id.textTitle);
            history_edit_review = v.findViewById(R.id.writeReview);
            history_view_contractor_profile = v.findViewById(R.id.contractorProfile);

            history_edit_review.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), HistoryEditReview.class);
                    v.getContext().startActivity(intent);
                }
            });

        }
    }

    public HistoryRecyclerAdapter(HistoryFragment historyFragment, ArrayList<HistoryGetterSetter> dataset) {
        mDataset.clear();
        mDataset.addAll(dataset);
    }

    @Override
    public HistoryRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_history, parent, false);
        HistoryRecyclerAdapter.ViewHolder vh = new HistoryRecyclerAdapter.ViewHolder(view);
        history_review = (TextView) view.findViewById(R.id.writeReview);
        history_contractor_profile = (TextView) view.findViewById(R.id.contractorProfile);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryGetterSetter historyGetterSetter = mDataset.get(position);
        holder.history_text_description.setText(historyGetterSetter.getRequest_description());
        holder.history_title.setText(historyGetterSetter.getRequest_title());


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}