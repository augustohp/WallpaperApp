package com.twismart.wallpapershd.ui.wallpaper;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sneyd on 4/24/2017.
 **/
public class DownloadImagesTask extends AsyncTask<String, Void, Bitmap> {

    String url;

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
        Bitmap bmp =null;
        try{
            URL ulrn = new URL(url);
            HttpURLConnection con = (HttpURLConnection)ulrn.openConnection();
            InputStream is = con.getInputStream();
            bmp = BitmapFactory.decodeStream(is);
            if (null != bmp)
                return bmp;

        } catch(Exception e){
            e.printStackTrace();
        }
        return bmp;
    }
}
