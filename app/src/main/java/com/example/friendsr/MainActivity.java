package com.example.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d(TAG, "item click" + id);
            Friend clickedFriend = (Friend) parent.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("clicked_friend", clickedFriend);
            startActivity(intent);




        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView grid = findViewById(R.id.grid);
        //initialising griditemlistener and calling it
        GridItemClickListener profileclick = new GridItemClickListener();
        grid.setOnItemClickListener(profileclick);


                //initialize list of all the rapper names and bios
        String[] rappers = {"21 Savage", "3robi", "ASAP Rocky", "Bartofso", "Louivos", "TjerraL", "Travis Scott", "Yung Nnelg"};
        String[] barz = {"21 21 21 21 21", "SPOW, MADAFAK!", "My shades, Dior, my pants, velour" +
                "Create, explore, expand, conquer", "Waar ik vandaan kom diamonds, waar ik vandaan kom gowtu" +
                "Ik ben me eigen politie, hierzo we kennen geen skotoe", "Pull up, is drukker dan gasfles" +
                "De game is een school en ik gaf les", "Heeft vooral lines met frisdrank", "It's lit!", "Het is die boy met die overloop, vandaar ook dat ze overloopt"};
        ArrayList<Friend> friends = new ArrayList<>();
        int barzcounter = 0;
        //creating rappers
        for (String rapper : rappers) {
            String rappername = "drawable" + rapper.replaceAll("\\s+","");
            int id = getApplicationContext().getResources().getIdentifier(rappername.toLowerCase(),"drawable", this.getPackageName());
            Friend friend = new Friend(rapper, barz[barzcounter], id);
            friends.add(friend);
            barzcounter++;

        }




        //implementing the adapter for the seperate grid items
        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
        grid.setAdapter(adapter);





    }

}
