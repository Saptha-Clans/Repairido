package com.example.expresso.sapthagiri.yogirproduct.Fragments.CustomerHomePage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.expresso.sapthagiri.yogirproduct.R;

public class FilterBottomDialog extends BottomSheetDialogFragment {

    Spinner sortSpinner, selectorSpinner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_dialog_content, container, false);

        sortSpinner = view.findViewById(R.id.sortBy);
        selectorSpinner = view.findViewById(R.id.selectorFilter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.sortArray));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(adapter);

        ArrayAdapter<String> selectorAdapter = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.selectorArray));
        selectorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectorSpinner.setAdapter(selectorAdapter);
        return view;
    }
}
