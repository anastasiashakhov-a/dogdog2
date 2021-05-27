package com.example.dogdog2.Home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dogdog2.ImageLoader;
import com.example.dogdog2.Profile.Organization;
import com.example.dogdog2.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Friends_Adapter extends BaseAdapter {

    private Context context;
    private List<Friends> friends = new ArrayList<>();

    public void refresh(List<Friends> friendsList) {
        friends.clear();
        friends.addAll(friendsList);
        notifyDataSetChanged();
    }

    public void append(List<Friends> friendsList){
        friends.addAll(friendsList);
        notifyDataSetChanged();
    }

    public Friends_Adapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int position) {
        return friends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return friends.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v;
        if (convertView == null) {
            LayoutInflater nameInfl = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = nameInfl.inflate(R.layout.friends_home, parent, false);
        } else {
            v = convertView;
        }
        ImageView imageCv1 = v.findViewById(R.id.pet_image);
        TextView nameCv1 = v.findViewById(R.id.pet_name);
        TextView descrCv = v.findViewById(R.id.pet_description);
        Friends x = friends.get(position);
        nameCv1.setText(x.getName());
        descrCv.setText(x.getDescription());

        new ImageLoader(imageCv1, x.getFriend_image_url()).execute();
        return v;
    }

}
