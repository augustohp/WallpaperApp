package com.twismart.wallpapershd.ui.wallpaper

import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import com.crashlytics.android.Crashlytics
import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.ui.base.BaseActivity
import com.twismart.wallpapershd.utils.Constants

import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList

import javax.inject.Inject

import hugo.weaving.DebugLog

class WallpapersActivity : BaseActivity(), WallpapersContract.View {

    private val TAG = javaClass.simpleName
    private var mViewPager: ViewPager? = null
    private var wallpapersList: ArrayList<Wallpaper>? = null

    @Inject internal val mPresenter: WallpapersContract.Presenter<WallpapersContract.View>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)
        activityComponent?.inject(this)
        setUp()
        mPresenter?.attachView(this)
    }

    @DebugLog
    override fun setUp() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mViewPager = findViewById(R.id.view_pager) as ViewPager
        try {
            wallpapersList = intent.getParcelableArrayListExtra<Wallpaper>(Constants.WALLPAPERS_LIST)
            mViewPager!!.adapter = WallpapersPagerAdapter(supportFragmentManager, applicationContext, wallpapersList)
            mViewPager!!.currentItem = intent.getIntExtra(Constants.WALLPAPER_TO_SHOW, 0)
        } catch (e: Exception) {
            Crashlytics.logException(e)
            e.printStackTrace()
            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_wallpapers, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.menu_set_wallpaper -> {
                val url = wallpapersList!![mViewPager!!.currentItem].urlImage
                Log.d(TAG, "onOptionsItemSelected: urlImage " + url)
                mPresenter!!.setWallpaper(url)

                val mWallpaperManager = WallpaperManager.getInstance(applicationContext)
                try {
                    mWallpaperManager.setBitmap(DownloadImagesTask().doInBackground(url))
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.dettachView()
    }

    companion object {

        // replace for start method because this one is more flexible
        fun newIntent(context: Context, wallpaperList: ArrayList<Wallpaper>, wallpaperToShow: Int): Intent {
            val newIntent = Intent(context, WallpapersActivity::class.java)
            newIntent.putParcelableArrayListExtra(Constants.WALLPAPERS_LIST, wallpaperList)
            newIntent.putExtra(Constants.WALLPAPER_TO_SHOW, wallpaperToShow)
            return newIntent
        }
    }
}
