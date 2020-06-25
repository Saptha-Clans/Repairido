package com.example.expresso.sapthagiri.yogirproduct;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DashboardRecyclerAdapter extends RecyclerView.Adapter<DashboardRecyclerAdapter.ViewHolder> {

    private ArrayList<DashboardGetterSetter> mDataset = new ArrayList<>();
    ConstraintLayout constraintLayout;

    public DashboardRecyclerAdapter(ArrayList<DashboardGetterSetter> mDataset) {
        this.mDataset = mDataset;
    }

    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView contract_name, contractor_category, contractor_specialize;
        ConstraintLayout constraintLayout;

        ImageView contractor_image;

        public ViewHolder(View v) {
            super(v);
            contract_name = v.findViewById(R.id.contractorName);
            constraintLayout = v.findViewById(R.id.constraintLayout);
            contractor_category = v.findViewById(R.id.contractorCategory);
            contractor_specialize = v.findViewById(R.id.contractorSpecialize);
            contractor_image = v.findViewById(R.id.contractorImage);
        }
    }

    public DashboardRecyclerAdapter(DashboardFragment dashboardFragment, ArrayList<DashboardGetterSetter> dataset) {
        mDataset.clear();
        mDataset.addAll(dataset);
    }

    @Override
    public DashboardRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_dashboard, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DashboardGetterSetter dashboardGetterSetter = mDataset.get(position);
        holder.contract_name.setText(dashboardGetterSetter.getContractor_name());
        holder.contractor_category.setText(dashboardGetterSetter.getContractor_category());
        holder.contractor_specialize.setText(dashboardGetterSetter.getContractor_specialize());
        //holder.contractor_image.setImageDrawable(context.getResources().getDrawable(dashboardGetterSetter.getContractor_image()));
        holder.contractor_image.setImageResource(dashboardGetterSetter.getContractor_image());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), ContactorProfilePage.class);
                intent.putExtra("Image", dashboardGetterSetter.getContractor_image());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}









    /*private LayoutInflater layoutInflater;
    private List<String> data;
    private TextView contractName, contractorCategory, contractorSpecialize;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            contractName = (TextView) itemView.findViewById(R.id.contractorName);
            contractorCategory = itemView.findViewById(R.id.contractorCategory);
            contractorSpecialize = itemView.findViewById(R.id.contractorSpecialize);

        }
    }

    Adapter(Context context, List<String> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycler_dashboard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //String conName = data.get(position);
        holder.contractName.setText(data.get(position));

    }

    @Override
    public int getItemCount() {
        return 0;
    }*/

/*v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), ContactorProfilePage.class);
                    intent.putExtra("Image", mDataset.get(getAdapterPosition()));
                    v.getContext().startActivity(intent);
                }
            });*/
