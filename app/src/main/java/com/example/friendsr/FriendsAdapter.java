package com.example.friendsr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class FriendsAdapter extends ArrayAdapter<Friend> {


    public ArrayList<Friend> rappers;
    public FriendsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Friend> objects) {
        super(context, resource, objects);
        rappers = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }
        ImageView rapperImageView = convertView.findViewById(R.id.rapperimageview);
        TextView rapperTxt = convertView.findViewById(R.id.rappertxt);
        Friend chooser = rappers.get(position);
        rapperImageView.setImageResource(chooser.getDrawableId());
        rapperTxt.setText(chooser.getName());

        return convertView;
    }
}
