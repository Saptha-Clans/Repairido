package com.example.expresso.sapthagiri.yogirproduct.Adapters.ServiceRequest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.expresso.sapthagiri.yogirproduct.R;
import com.example.expresso.sapthagiri.yogirproduct.GetterSetter.ServiceRequest.ServiceGetterSetter;

import java.util.ArrayList;
import java.util.List;

public class ServiceDetialsAdapter extends ArrayAdapter<ServiceGetterSetter> {
    private List<ServiceGetterSetter> serviceListFull;

    public ServiceDetialsAdapter(@NonNull Context context, @NonNull List<ServiceGetterSetter> service) {
        super(context, 0, service);
        serviceListFull = new ArrayList<>(service);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return serviceFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.service_autocomplete_row, parent, false);
        }
        TextView textViewName = convertView.findViewById(R.id.serviceText);
        ImageView imageViewFlag = convertView.findViewById(R.id.searchImage);
        ServiceGetterSetter serviceItem = getItem(position);
        if (serviceItem != null) {
            textViewName.setText(serviceItem.getService());
            imageViewFlag.setImageResource(serviceItem.getImage());
        }
        return convertView;
    }

    private Filter serviceFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<ServiceGetterSetter> suggestions = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(serviceListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ServiceGetterSetter item : serviceListFull) {
                    if (item.getService().toLowerCase().contains(filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((ServiceGetterSetter) resultValue).getService();
        }
    };
}