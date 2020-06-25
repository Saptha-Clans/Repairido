package com.example.expresso.sapthagiri.yogirproduct;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
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
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private CustomerCompleteSignUpFragment customerCompleteSignUpFragment = new CustomerCompleteSignUpFragment();
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the two
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        //getSupportFragmentManager().beginTransaction().add(R.id.sign_up_fragment, customerCompleteSignUpFragment).commit();

        fragmentManager = getSupportFragmentManager();

    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @NonNull
        @Override
        public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

            View rootView = null;

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_customer, container, false);
                    TextView register = rootView.findViewById(R.id.customer_register);
                    TextView forgot_password = rootView.findViewById(R.id.customer_forgot_password);
                    Button customer_login = rootView.findViewById(R.id.login_button);

                    customer_login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), CustomerHomePage.class);
                            startActivity(intent);
                        }
                    });
                    String forgot_password_text = "Forgot Password?";

                    String register_text = "You are not a member? Register";

                    SpannableString spanableObject = new SpannableString(register_text);
                    SpannableString passwordOject = new SpannableString(forgot_password_text);

                    ClickableSpan passwordSpan = new ClickableSpan() {
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


                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(View widget) {
                            Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();

                            //rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);

                            //register.setMovementMethod();

                            Intent intent = new Intent(getActivity(), CustomerSignUpActivity.class);
                            startActivity(intent);


                            //customerCompleteSignUpFragment = new CustomerCompleteSignUpFragment();
                            //fragmentTransaction.add(R.id.sign_up_fragment, customerCompleteSignUpFragment, "fragment_test");
                            //fragmentTransaction.addToBackStack(null);
                            //fragmentTransaction.commit();
                        }

                        @Override
                        public void updateDrawState(TextPaint ds) {
                            super.updateDrawState(ds);
                            ds.setColor(Color.BLUE);
                        }
                    };

                    spanableObject.setSpan(clickableSpan, 22, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    passwordOject.setSpan(passwordSpan, 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                    register.setText(spanableObject);
                    forgot_password.setText(passwordOject);

                    register.setMovementMethod(LinkMovementMethod.getInstance());
                    forgot_password.setMovementMethod(LinkMovementMethod.getInstance());
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_contractor, container, false);

                    TextView fragment_forgot_password = rootView.findViewById(R.id.contractor_forgot_password);
                    TextView fragment_register = rootView.findViewById(R.id.contractor_register);

                    String fragment_register_text = "You are not a member? Register";
                    String fragment_forgot_password_text = "Forgot Password?";

                    SpannableString fragment_spanableObject = new SpannableString(fragment_register_text);
                    SpannableString fragment_passwordOject = new SpannableString(fragment_forgot_password_text);

                    ClickableSpan fragment_passwordSpan = new ClickableSpan() {
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

                    ClickableSpan fragment_clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(View widget) {
                            Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), ContractorSignUpActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void updateDrawState(TextPaint ds) {
                            super.updateDrawState(ds);
                            ds.setColor(Color.BLUE);
                        }
                    };
                    fragment_passwordOject.setSpan(fragment_passwordSpan, 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    fragment_spanableObject.setSpan(fragment_clickableSpan, 22, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                    fragment_forgot_password.setText(fragment_passwordOject);
                    fragment_register.setText(fragment_spanableObject);

                    fragment_register.setMovementMethod(LinkMovementMethod.getInstance());
                    fragment_forgot_password.setMovementMethod(LinkMovementMethod.getInstance());
                    break;
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
            }
            return null;
        }
    }
}





/*Toolbar toolbar = (Toolbar) findViewById(R.id.tabs);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Yogi R Tech");
        toolbar.setSubtitle("product");
        toolbar.setLogo(R.drawable.consumer_profile_icon);*/