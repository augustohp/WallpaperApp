package com.twismart.wallpapershd.ui.wallpaper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask

import java.io.InputStream
import java.net.URL

/**
 * Created by sneyd on 4/24/2017.
 **/
class DownloadImagesTask : AsyncTask<String, Void, Bitmap?>() {

    public override fun doInBackground(vararg url: String): Bitmap? {
        return downloadImage(url[0])
    }

    override fun onPostExecute(result: Bitmap?) {}

    override fun onProgressUpdate(vararg values: Void) {
        super.onProgressUpdate(*values)
    }

    private fun downloadImage(url: String): Bitmap? {
        var bmp: Bitmap? = null
        try {
            val `is` = URL(url).openStream()
            bmp = BitmapFactory.decodeStream(`is`)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return bmp
    }
}
