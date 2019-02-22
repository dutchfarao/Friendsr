package com.example.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    public static final String TAG = "ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        final Friend retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");
        // Storing the users shared preferences in 'preferences'
        SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();

        //get id, name & bio
        int rapperId = retrievedFriend.getDrawableId();
        final String rapperName= retrievedFriend.getName();
        String rapperBio = retrievedFriend.getBio();

        //activate ratingbar method
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        onRatingBarChangeListener ratingBarChangeListener = new onRatingBarChangeListener();
        ratingBar.setOnRatingBarChangeListener(ratingBarChangeListener);




        //generating key so that we can check if there's a saved rating
        String key = rapperName + "stars";

        //get saved rating
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        Float rating = prefs.getFloat(key, 0.0f);

        if (rating != null) {
            // we have something stored under "example_key", set the bar to the rating stored
            ratingBar.setRating(rating);
        }
        else {
            // there is nothing stored under "example_key", set the rating to 0
            ratingBar.setRating(0.0f);
        }

        //set id, name & bio
        ImageView profileImage = findViewById(R.id.profileImage);
        TextView profileName = findViewById(R.id.profileName);
        TextView profileBio = findViewById(R.id.profileBio);
        profileImage.setImageResource(rapperId);
        Log.d(TAG, "setimage" + rapperId);
        profileName.setText(rapperName);
        Log.d(TAG, "setname" + rapperName);
        profileBio.setText(rapperBio);
        Log.d(TAG, "setBio" + rapperBio);




    }
    private class onRatingBarChangeListener implements RatingBar.OnRatingBarChangeListener
    {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            //save a key if the rating is changed
            TextView profileName = findViewById(R.id.profileName);
            String key = profileName.getText() + "stars";
            SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
            editor.putFloat(key, rating);
            //actually setting the rating
            editor.apply();
            Intent intent = getIntent();
            final Friend retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");
            retrievedFriend.setRating(rating);

        }
    }
}
