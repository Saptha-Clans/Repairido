package com.example.expresso.sapthagiri.yogirproduct;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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


public class ContractorCompleteSignUpFragment extends Fragment {



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = null;

        rootView = inflater.inflate(R.layout.fragment_contractor_complete_sign_up, container, false);
        TextView cancel = rootView.findViewById(R.id.complete_cancel);
        String cancel_text = "Cancel";

        SpannableString spanableObject = new SpannableString(cancel_text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
            }
        };

        spanableObject.setSpan(clickableSpan, 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        cancel.setText(spanableObject);

        cancel.setMovementMethod(LinkMovementMethod.getInstance());
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Sign up");
    }


}
