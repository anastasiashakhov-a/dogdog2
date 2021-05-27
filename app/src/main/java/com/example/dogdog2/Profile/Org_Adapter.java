package com.example.dogdog2.Profile;

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

import com.example.dogdog2.R;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Org_Adapter extends BaseAdapter {

    private Context context;
    private List<Organization> org = new ArrayList<>();

    public void refresh (List <Organization> organizationList){
        org.clear();
        org.addAll(organizationList);
        notifyDataSetChanged();
    }

    public Org_Adapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return org.size();
    }

    @Override
    public Object getItem(int position) {
        return org.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null){
            v = new View(context);
            LayoutInflater nameInfl = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = nameInfl.inflate(R.layout.org_profile, parent, false);
        }
        else {
            v = convertView;
        }
        ImageView imageCv1 = v.findViewById(R.id.org_image);
        TextView nameCv1 = v.findViewById(R.id.org_name);
        TextView descrCv1 = v.findViewById(R.id.org_description);
        Organization x = org.get(position);
        nameCv1.setText(x.getName());
        descrCv1.setText(x.getDescription());
        new ImageLoader(imageCv1, x).execute();
//        imageCv1.setImageBitmap(x.getImage());
        return v;
    }
    private static class ImageLoader extends AsyncTask<Void, Void, Bitmap>{
        private ImageView imgViewOrg;
        private static final Bitmap defaultBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);

        public ImageLoader(ImageView imgViewOrg, Organization org) {
            this.org = org;
            this.imgViewOrg = imgViewOrg;
        }

        private  Organization org;

        @Override
        protected Bitmap doInBackground(Void... voids) {
            try {
                URL url = new URL(org.getBrande_image_url());

                return BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {

                e.printStackTrace();
            }


            return defaultBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bmp) {
            imgViewOrg.setImageBitmap(bmp);
        }

        @Override
        protected void onPreExecute() {
            imgViewOrg.setImageBitmap(defaultBitmap);
        }
    }
}
