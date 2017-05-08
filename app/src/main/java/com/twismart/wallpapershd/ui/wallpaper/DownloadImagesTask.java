package com.twismart.wallpapershd.ui.wallpaper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by sneyd on 4/24/2017.
 **/
public class DownloadImagesTask extends AsyncTask<String, Void, Bitmap> {

    @Override
    protected Bitmap doInBackground(String... url) {
        return downloadImage(url[0]);
    }

    @Override
    protected void onPostExecute(Bitmap result) {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    private Bitmap downloadImage(String url) {
        Bitmap bmp = null;
        try{
            InputStream is = new URL(url).openStream();
            bmp = BitmapFactory.decodeStream(is);
        } catch(Exception e){
            e.printStackTrace();
        }
        return bmp;
    }
}
