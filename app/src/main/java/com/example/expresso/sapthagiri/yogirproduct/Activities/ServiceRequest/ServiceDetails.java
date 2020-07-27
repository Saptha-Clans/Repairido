package com.example.expresso.sapthagiri.yogirproduct.Activities.ServiceRequest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.expresso.sapthagiri.yogirproduct.Adapters.ServiceRequest.ServiceDetialsAdapter;
import com.example.expresso.sapthagiri.yogirproduct.R;
import com.example.expresso.sapthagiri.yogirproduct.GetterSetter.ServiceRequest.ServiceGetterSetter;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ServiceDetails extends AppCompatActivity {

    private ArrayList<ServiceGetterSetter> service;
    private String[] description_data = {"Enter Details", "Select Address", "Choose Time"};
    private TextInputLayout service_type, service_title, service_description;
    private ImageButton attach_file;
    private Button next, back, attachment_one, attachment_two, attachment_three, attachment_four, attachment_five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);

        service_type = findViewById(R.id.selectService);
        service_title = findViewById(R.id.serviceTitle);
        service_description = findViewById(R.id.serviceDescription);
        next = findViewById(R.id.submit);
        //back = findViewById(R.id.backButton);
        attach_file = findViewById(R.id.attachFile);
        attachment_one = findViewById(R.id.attachmentOne);
        attachment_two = findViewById(R.id.attachmentTwo);
        attachment_three = findViewById(R.id.attachmentThree);
        attachment_four = findViewById(R.id.attachmentFour);
        attachment_five = findViewById(R.id.attachmentFive);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceDetails.this, ServiceAddress.class);
                startActivity(intent);
            }
        });

        attach_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M &&
                        checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10001);
                }
                new MaterialFilePicker()
                        .withActivity(ServiceDetails.this)
                        .withRequestCode(1)
                        .withFilter(Pattern.compile(".*\\.(mkv|wmv|avi|mpeg|swf|mov|mp4|jpg|jpeg)$"))
                        .withHiddenFiles(true) // Show hidden files and folders
                        .start();
            }
        });

        attachment_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachment_one.setVisibility(View.INVISIBLE);
            }
        });

        attachment_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachment_two.setVisibility(View.INVISIBLE);
            }
        });

        attachment_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachment_three.setVisibility(View.INVISIBLE);
            }
        });

        attachment_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachment_four.setVisibility(View.INVISIBLE);
            }
        });

        attachment_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachment_five.setVisibility(View.INVISIBLE);
            }
        });

        /*back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/


        init();
        AutoCompleteTextView editText = findViewById(R.id.autoCompleteService);
        ServiceDetialsAdapter adapter = new ServiceDetialsAdapter(ServiceDetails.this, service);
        editText.setAdapter(adapter);
    }

    public void init() {
        StateProgressBar stateProgressBar = findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateLineThickness(3);
        stateProgressBar.setDescriptionTopSpaceIncrementer(50);
        stateProgressBar.setStateDescriptionSize(13);
        stateProgressBar.setStateDescriptionData(description_data);

        service = new ArrayList<>();
        service.add(new ServiceGetterSetter(R.drawable.ic_search_black_24dp, "Electrician"));
        service.add(new ServiceGetterSetter(R.drawable.ic_search_black_24dp, "Carpenter"));
        service.add(new ServiceGetterSetter(R.drawable.ic_search_black_24dp, "Plumber"));
        service.add(new ServiceGetterSetter(R.drawable.ic_search_black_24dp, "AC service"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String file_path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            String file_array[] = file_path.split("/");
            String file_name = file_array[file_array.length - 1];
            // Do anything with file

            if(attachment_one.getText().toString().isEmpty()) {
                attachment_one.setVisibility(View.VISIBLE);
                attachment_one.setText(file_name);
            } else if(attachment_two.getText().toString().isEmpty()) {
                attachment_two.setVisibility(View.VISIBLE);
                attachment_two.setText(file_name);
            } else if(attachment_three.getText().toString().isEmpty()) {
                attachment_three.setVisibility(View.VISIBLE);
                attachment_three.setText(file_name);
            } else if(attachment_four.getText().toString().isEmpty()) {
                attachment_four.setVisibility(View.VISIBLE);
                attachment_four.setText(file_name);
            } else if(attachment_five.getText().toString().isEmpty()) {
                attachment_five.setVisibility(View.VISIBLE);
                attachment_five.setText(file_name);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10001: {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(ServiceDetails.this, "Permission granted", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ServiceDetails.this, "Permission not granted", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }
}
