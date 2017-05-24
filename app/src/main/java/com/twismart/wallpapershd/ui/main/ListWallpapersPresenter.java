package com.twismart.wallpapershd.ui.main;

import android.util.Log;

import com.twismart.wallpapershd.data.DataManager;
import com.twismart.wallpapershd.data.model.Wallpaper;
import com.twismart.wallpapershd.ui.base.BasePresenterImpl;


import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sneyd on 5/5/2017.
 **/

public class ListWallpapersPresenter<V extends ListWallpapersContract.View> extends BasePresenterImpl<V> implements ListWallpapersContract.Presenter<V> {

    private final String TAG = getClass().getSimpleName();
    @Inject
    public ListWallpapersPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void getWallpapersList() {
        getCompositeDisposable().add(getDataManager()
                .getWallpapersList("index.php")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ArrayList<Wallpaper>>(){
                    @Override
                    public void onNext(ArrayList<Wallpaper> wallpapers) {
                        Log.d(TAG, "onNext: " + wallpapers.toString());
                        getBaseView().setWallpaperList(wallpapers);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onCompleted: ");
                    }
                })
        );
    }
}