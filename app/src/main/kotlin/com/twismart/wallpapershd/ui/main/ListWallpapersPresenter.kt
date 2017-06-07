package com.twismart.wallpapershd.ui.main

import android.util.Log

import com.twismart.wallpapershd.data.DataManager
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.ui.base.BasePresenterImpl


import java.util.ArrayList

import javax.inject.Inject

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by sneyd on 5/5/2017.
 **/

class ListWallpapersPresenter<V : ListWallpapersContract.View> @Inject
constructor(dataManager: DataManager, compositeDisposable: CompositeDisposable) : BasePresenterImpl<V>(dataManager, compositeDisposable), ListWallpapersContract.Presenter<V> {

    private val TAG = javaClass.simpleName

    override fun getWallpapersList() {
        compositeDisposable.add(dataManager
                .getWallpapersList("index.php")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ArrayList<Wallpaper>>() {
                    override fun onNext(wallpapers: ArrayList<Wallpaper>) {
                        Log.d(TAG, "onNext: " + wallpapers.toString())
                        baseView!!.setWallpaperList(wallpapers)
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "onError: ")
                    }

                    override fun onComplete() {
                        Log.d(TAG, "onCompleted: ")
                    }
                })
        )
    }
}