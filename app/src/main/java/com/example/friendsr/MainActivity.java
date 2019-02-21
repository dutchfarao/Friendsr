package com.example.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
        //initialising GridItemListener and calling it
        GridItemClickListener profileClick = new GridItemClickListener();
        grid.setOnItemClickListener(profileClick);


                //initialize list of all the rapper names and bios
        String[] rappers = {"21 Savage", "3robi", "ASAP Rocky", "Bartofso", "Louivos", "TjerraL", "Travis Scott", "Yung Nnelg"};
        String[] barz = {"21 21 21 21 21.", "SPOW, MADAFAK!", "My shades, Dior, my pants, velour. " +
                "Create, explore, expand, conquer", "Waar ik vandaan kom diamonds, waar ik vandaan kom gowtu." +
                " Ik ben me eigen politie, hierzo we kennen geen skotoe", "Pull up, is drukker dan gasfles. " +
                "De game is een school en ik gaf les.", "Heeft vooral lines met frisdrank.", "It's lit!", "Het is die boy met die overloop, vandaar ook dat ze overloopt."};
        ArrayList<Friend> friends = new ArrayList<>();
        int BarzCounter = 0;
        //creating rappers
        for (String rapper : rappers) {
            String rapperName = "drawable" + rapper.replaceAll("\\s+","");
            int id = getApplicationContext().getResources().getIdentifier(rapperName.toLowerCase(),"drawable", this.getPackageName());
            Friend friend = new Friend(rapper, barz[BarzCounter], id);
            friends.add(friend);
            BarzCounter++;

        }




        //implementing the adapter for the separate grid items
        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
        grid.setAdapter(adapter);





    }

}
