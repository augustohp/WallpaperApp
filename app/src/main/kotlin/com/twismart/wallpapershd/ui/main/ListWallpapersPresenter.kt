package com.twismart.wallpapershd.ui.main

import com.twismart.wallpapershd.data.DataManager
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.ui.base.BasePresenter
import com.twismart.wallpapershd.utils.debug
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
constructor(dataManager: DataManager, compositeDisposable: CompositeDisposable) : BasePresenter<V>(dataManager, compositeDisposable), ListWallpapersContract.Presenter<V> {

    override fun getWallpapersList() {
        compositeDisposable.add(dataManager
                .getWallpapersList("index.php")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ArrayList<Wallpaper>>() {
                    override fun onNext(wallpapers: ArrayList<Wallpaper>) {
                        debug("onNext: " + wallpapers.toString())
                        baseView?.setWallpaperList(wallpapers)
                    }
                    override fun onError(e: Throwable) {
                        debug("onError")
                    }
                    override fun onComplete() {
                        debug("onComplete")
                    }
                })
        )
    }
}