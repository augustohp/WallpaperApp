package com.twismart.wallpapershd.ui.main;

import android.util.Log;

import com.twismart.wallpapershd.data.DataManager;
import com.twismart.wallpapershd.data.model.Wallpaper;
import com.twismart.wallpapershd.ui.base.BasePresenterImpl;
import java.util.ArrayList;
import javax.inject.Inject;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sneyd on 5/5/2017.
 **/

public class ListWallpapersPresenterImpl<V extends ListWallpapersView> extends BasePresenterImpl<V> implements ListWallpapersPresenter<V> {

    private final String TAG = getClass().getSimpleName();

    private DataManager mDataManager;

    @Inject
    public ListWallpapersPresenterImpl(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getWallpapersList() {
        mDataManager.getWallpapersList("index.php")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<Wallpaper>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }
                    @Override
                    public void onNext(ArrayList<Wallpaper> wallpapers) {
                        Log.d(TAG, "onNext: " + wallpapers.toString());
                        getBaseView().setWallpaperList(wallpapers);
                    }
                });
    }
}
