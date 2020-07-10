package com.example.expresso.sapthagiri.yogirproduct;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class ContactorProfilePage extends AppCompatActivity {

    private ImageView contractor_image;
    private int image;
    private float rating;
    private CarouselView carouselView;
    private String image_Urls[], rating_number;
    TextView contractor_name, address_line_one, address_line_two, vrating_number;
    RatingBar v_rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactor_profile_page);

        contractor_name = findViewById(R.id.customerName);
        address_line_one = findViewById(R.id.addressLineOne);
        address_line_two = findViewById(R.id.addressLineTwo);
        vrating_number = findViewById(R.id.ratingNumber);
        v_rating = findViewById(R.id.ratingBar);

        Intent intent = getIntent();
        image = intent.getIntExtra("Image", 0);
        String img = String.valueOf(image);
        rating = intent.getFloatExtra("Rating", 0);
        rating_number = String.valueOf(intent.getFloatExtra("Rating", 0));

        //contractor_image.setImageResource(image);
        //image_Urls = new int[]{image, R.drawable.adiyogi, R.drawable.consumer_profile_icon, R.drawable.yogi_r_home_page};

        image_Urls = new String[]{
                "https://images.unsplash.com/photo-1511367461989-f85a21fda167?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
                "https://images.unsplash.com/photo-1457449940276-e8deed18bfff?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
                "https://images.unsplash.com/photo-1520592978680-efbdeae5d036?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
        };
        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(image_Urls.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                //imageView.setImageResource(image_Urls[position]);
                Picasso.get().load(image_Urls[position])
                        .fit().centerCrop().into(imageView);
            }
        });
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(ContactorProfilePage.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        vrating_number.setText(rating_number);
        v_rating.setRating(rating);
        //contractor_image = findViewById(R.id.consumerImage);
    }
}
