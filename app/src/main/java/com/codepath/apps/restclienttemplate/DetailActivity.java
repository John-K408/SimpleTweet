package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {
    ImageView ivProfilePicture;
    TextView tvScreenName;
    TextView tvUserName;
    TextView tvBody;
    Tweet tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ivProfilePicture = findViewById(R.id.ivProfileImage2);
        tvScreenName = findViewById(R.id.tvscreeName2);
        tvUserName = findViewById(R.id.tvUserName2);
        tvBody = findViewById(R.id.tvBody2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        tvScreenName.setText(tweet.user.screenName);
        tvUserName.setText(tweet.user.name);
        tvBody.setText(tweet.body);
        Glide.with(this).load(tweet.user.profileImageUrl).into(ivProfilePicture);


    }
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}