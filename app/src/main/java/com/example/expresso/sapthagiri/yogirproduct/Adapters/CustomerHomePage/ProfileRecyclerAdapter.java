package com.example.expresso.sapthagiri.yogirproduct.Adapters.CustomerHomePage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expresso.sapthagiri.yogirproduct.Activities.CustomerHomePage.ChangePassword;
import com.example.expresso.sapthagiri.yogirproduct.Activities.CustomerHomePage.OngoingViewDetails;
import com.example.expresso.sapthagiri.yogirproduct.Fragments.CustomerHomePage.ProfileFragment;
import com.example.expresso.sapthagiri.yogirproduct.GetterSetter.CustomerHomePage.ProfileGetterSetter;
import com.example.expresso.sapthagiri.yogirproduct.Fragments.CustomerHomePage.ProfileManageAdressFragment;
import com.example.expresso.sapthagiri.yogirproduct.R;

import java.util.ArrayList;

public class ProfileRecyclerAdapter extends RecyclerView.Adapter<ProfileRecyclerAdapter.ViewHolder> {
    public static final String EXTRA_TEXT = "com.example.expresso.sapthagiri.yogirproduct.EXTRA_TEXT";

    private ArrayList<ProfileGetterSetter> mDataset = new ArrayList<>();
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView profile_name;
        private ImageView profile_image;
        ConstraintLayout profileConstraintLayout;

        public ViewHolder(View v) {
            super(v);
            profile_name = v.findViewById(R.id.profileRowText);
            profileConstraintLayout = v.findViewById(R.id.profileRowLayout);
            profile_image = v.findViewById(R.id.profileRowIcon);
        }
    }

    public ProfileRecyclerAdapter(ProfileFragment profileFragment, ArrayList<ProfileGetterSetter> dataset) {
        mDataset.clear();
        mDataset.addAll(dataset);
    }

    @Override
    public ProfileRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_profile, parent, false);
        ProfileRecyclerAdapter.ViewHolder vh = new ProfileRecyclerAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ProfileRecyclerAdapter.ViewHolder holder, int position) {
        final ProfileGetterSetter profileGetterSetter = mDataset.get(position);
        holder.profile_name.setText(profileGetterSetter.getTitle());
        holder.profile_image.setImageResource(profileGetterSetter.getImage());

        holder.profileConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), String.valueOf(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), OngoingViewDetails.class);
                System.out.println("getAdapterPosition" + holder.getAdapterPosition());
                int adapter = holder.getAdapterPosition();

                switch (holder.getAdapterPosition()) {
                    case 0:
                        intent = new Intent(v.getContext(), ProfileManageAdressFragment.class);
                        intent.putExtra(EXTRA_TEXT, adapter);
                        v.getContext().startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(v.getContext(), ChangePassword.class);
                        v.getContext().startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("two_factor_authentication", adapter);
                        v.getContext().startActivity(intent);
                        break;
                    case 5:
                        SharedPreferences preferences = v.getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String retrivedToken = "Token " + preferences.getString("TOKEN", null);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.commit();
                        intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        v.getContext().startActivity(intent);
                        break;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
