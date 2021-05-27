package com.example.dogdog2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.dogdog2.Home.Friends;

import java.io.IOException;
import java.net.URL;

public class ImageLoader extends AsyncTask<Void, Void, Bitmap> {
    private ImageView imgViewFr;
    private static final Bitmap defaultBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);

    public ImageLoader(ImageView imgViewFr, String urlVal) {
        this.val = urlVal;
        this.imgViewFr = imgViewFr;
    }

    private  String val;

    @Override
    protected Bitmap doInBackground(Void... voids) {
        try {
            if (val != null){
                URL url = new URL(val);
                return BitmapFactory.decodeStream(url.openConnection().getInputStream());
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        return defaultBitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bmp) {
        imgViewFr.setImageBitmap(bmp);
    }

    @Override
    protected void onPreExecute() {
        imgViewFr.setImageBitmap(defaultBitmap);
    }
}
