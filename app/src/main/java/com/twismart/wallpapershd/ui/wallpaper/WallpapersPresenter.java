package com.twismart.wallpapershd.ui.wallpaper;

import android.app.WallpaperManager;

import com.twismart.wallpapershd.data.DataManager;
import com.twismart.wallpapershd.ui.base.BasePresenterImpl;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by sneyd on 5/16/2017.
 **/

public class WallpapersPresenter<V extends WallpapersContract.View> extends BasePresenterImpl<V> implements WallpapersContract.Presenter<V> {
    private final String TAG = getClass().getSimpleName();

    @Inject
    public WallpapersPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void setWallpaper(String url) {
        new DownloadImagesTask().doInBackground(url);
    }
}
