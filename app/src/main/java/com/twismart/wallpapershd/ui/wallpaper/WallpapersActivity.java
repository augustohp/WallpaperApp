package com.twismart.wallpapershd.ui.wallpaper;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;
import com.twismart.wallpapershd.R;
import com.twismart.wallpapershd.data.model.Wallpaper;
import com.twismart.wallpapershd.ui.base.BaseActivity;
import com.twismart.wallpapershd.utils.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.inject.Inject;

import hugo.weaving.DebugLog;

public class WallpapersActivity extends BaseActivity implements WallpapersContract.View {

    private final String TAG = getClass().getSimpleName();
    private ViewPager mViewPager;
    private ArrayList<Wallpaper> wallpapersList;

    @Inject WallpapersContract.Presenter<WallpapersContract.View> mPresenter;

    // replace for start method because this one is more flexible
    public static Intent newIntent(Context context, ArrayList<Wallpaper> wallpaperList, int wallpaperToShow){
        Intent newIntent = new Intent(context, WallpapersActivity.class);
        newIntent.putParcelableArrayListExtra(Constants.WALLPAPERS_LIST, wallpaperList);
        newIntent.putExtra(Constants.WALLPAPER_TO_SHOW, wallpaperToShow);
        return newIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        getActivityComponent().inject(this);
        setUp();
        mPresenter.attachView(this);
    }

    @DebugLog
    @Override
    protected void setUp() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        try{
            wallpapersList = getIntent().getParcelableArrayListExtra(Constants.WALLPAPERS_LIST);
            mViewPager.setAdapter(new WallpapersPagerAdapter(getSupportFragmentManager(), getApplicationContext(), wallpapersList));
            mViewPager.setCurrentItem(getIntent().getIntExtra(Constants.WALLPAPER_TO_SHOW, 0));
        }
        catch (Exception e){
            Crashlytics.logException(e);
            e.printStackTrace();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_wallpapers, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_set_wallpaper:
                String url = wallpapersList.get(mViewPager.getCurrentItem()).getUrlImage();
                Log.d(TAG, "onOptionsItemSelected: urlImage " + url);
                mPresenter.setWallpaper(url);

                WallpaperManager mWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    mWallpaperManager.setBitmap(new DownloadImagesTask().doInBackground(url));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.dettachView();
    }
}
