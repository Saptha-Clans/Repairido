package com.example.expresso.sapthagiri.yogirproduct.Activities.AppLaunch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.expresso.sapthagiri.yogirproduct.Activities.CustomerHomePage.CustomerHomePage;
import com.example.expresso.sapthagiri.yogirproduct.Activities.SignUpPage.ContractorSignUpActivity;
import com.example.expresso.sapthagiri.yogirproduct.Activities.SignUpPage.CustomerSignUpActivity;
import com.example.expresso.sapthagiri.yogirproduct.Fragments.SignUp.CustomerCompleteSignUpFragment;
import com.example.expresso.sapthagiri.yogirproduct.GetterSetter.HttpRequests.LoginGetter;
import com.example.expresso.sapthagiri.yogirproduct.R;
import com.example.expresso.sapthagiri.yogirproduct.NetworkServices.ServiceBuilder;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        fragmentManager = getSupportFragmentManager();

    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

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
                    TextInputLayout customer_email, customer_password;
                    Button customer_login, sign_up, forgot_password;
                    sign_up = rootView.findViewById(R.id.customerRegister);
                    forgot_password = rootView.findViewById(R.id.customerForgotPassword);
                    customer_login = rootView.findViewById(R.id.login_button);
                    customer_email = rootView.findViewById(R.id.customer_email);
                    customer_password = rootView.findViewById(R.id.customer_password);
                    ImageView customerImageView = rootView.findViewById(R.id.imageView);

                    Glide.with(this).load("https://wallpaperplay.com/walls/full/1/0/3/91297.jpg").into(customerImageView);

                    customer_login.setOnClickListener((View v) -> {
                        String email, password;
                        email = customer_email.getEditText().getText().toString().trim();
                        password = customer_password.getEditText().getText().toString().trim();
                        if (!emailValidation(email, customer_email) | !passwordValidation(password, customer_password)) {
                            return;
                        }
                        emailValidation(email, customer_email);
                        passwordValidation(password, customer_password);
                        customerLogin(email, password, customer_password);
                    });

                    sign_up.setOnClickListener((View v) -> {
                        inflater.inflate(R.layout.fragment_sign_up, container, false);
                        Intent intent = new Intent(getActivity(), CustomerSignUpActivity.class);
                        startActivity(intent);
                    });

                    forgot_password.setOnClickListener((View v) -> {
                            Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), VerifyEmail.class);
                            startActivity(intent);
                    });
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_contractor, container, false);
                    TextInputLayout contractor_email, contractor_password;
                    Button contractor_login, contractore_sign_up, contractore_forgot_password;
                    contractore_sign_up = rootView.findViewById(R.id.contractorRegister);
                    contractore_forgot_password = rootView.findViewById(R.id.contractorForgotPassword);
                    contractor_login = rootView.findViewById(R.id.login_button);
                    contractor_email = rootView.findViewById(R.id.contractorEmail);
                    contractor_password = rootView.findViewById(R.id.contractorPassword);
                    ImageView contractorImageView = rootView.findViewById(R.id.imageView);

                    Glide.with(this).load("https://wallpaperplay.com/walls/full/1/0/3/91297.jpg").into(contractorImageView);

                    contractor_login.setOnClickListener(v -> {
                        Intent intent = new Intent(getActivity(), CustomerHomePage.class);
                        startActivity(intent);
                    });

                    contractore_sign_up.setOnClickListener(v -> {
                        inflater.inflate(R.layout.fragment_sign_up, container, false);
                        Intent intent = new Intent(getActivity(), ContractorSignUpActivity.class);
                        startActivity(intent);
                    });

                    contractore_forgot_password.setOnClickListener(v -> Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show());
                    break;
            }
            return rootView;
        }

        public void customerLogin(String email, String password, TextInputLayout customer_password) {
            JsonObject login_data = new JsonObject();
            try {
                login_data.addProperty("username", email);
                login_data.addProperty("password", password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(login_data);

            Call<LoginGetter> call = ServiceBuilder.getInstance().getApi().loginUser(login_data);
            call.enqueue(new Callback<LoginGetter>() {
                @Override
                public void onResponse(Call<LoginGetter> call, Response<LoginGetter> response) {
                    if (!response.isSuccessful()) {
                        System.out.println("Response Code: " + response.code());
                        customer_password.setError("Email and password combination is not correct");
                        return;
                    } else {
                        System.out.println("Response Code: " + response.code());
                        System.out.println("response.body(): " + response.body());
                        customer_password.setError("");
                        LoginGetter responseBody = response.body();
                        String token = responseBody.getToken();
                        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        preferences.edit().putString("TOKEN",token).apply();
                        Intent intent = new Intent(getActivity(), CustomerHomePage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<LoginGetter> call, Throwable t) {
                    System.out.println("Message: " + t.getMessage());
                }
            });
        }

        private boolean emailValidation(String email, TextInputLayout contractor_email) {
            if (email.isEmpty()) {
                contractor_email.setError("Email cannot be empty");
                return false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                contractor_email.setError("Enter a valid email address");
                return false;
            } else {
                contractor_email.setError(null);
                return true;
            }
        }

        private boolean passwordValidation(String password, TextInputLayout customer_password) {
            if (password.isEmpty()) {
                customer_password.setError("Password cannot be empty");
                return false;
            } else {
                customer_password.setError(null);
                return true;
            }
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
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