package com.example.expresso.sapthagiri.yogirproduct;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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



/*
//ongoing_view_details = (TextView) holder.findViewById(R.id.textViewDetails);

    String fragment_details_text = "View details";

    SpannableString fragment_spanableObject = new SpannableString(fragment_details_text);

    ClickableSpan fragment_clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
                */
/*if (context instanceof ContractorSignUpActivity) {
                    ((ContractorSignUpActivity)context);
                }*//*

            //Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
            //FilterBottomDialog filterBottomDialog = new FilterBottomDialog();
            //filterBottomDialog.show(parent.getContext().getApplicationContext() , "ModalMenu");
            //Intent intent = new Intent(Intent.ACTION_VIEW);
            //intent.setData(ContractorSignUpActivity.class);
            Intent intent = new Intent(context, ContractorSignUpActivity.class);
            context.startActivity(intent);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(Color.BLUE);
        }
    };
        fragment_spanableObject.setSpan(fragment_clickableSpan, 0, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                holder.ongoing_view_details.setText(fragment_spanableObject);

                holder.ongoing_view_details.setMovementMethod(LinkMovementMethod.getInstance());
//holder.ongoing_view_details.setText(ongoingGetterSetter.getOngoing_details());
//holder.<image_id>.setText(context.getResources().getDrawable(ongoingGetterSetter.<getImage()>));
//holder.history_text_description.setText(mDataset.get(position));
//holder.history_title.setText(mDataset.get(position));
//holder.ongoing_view_details.setText(mDataset.get(position));*/
