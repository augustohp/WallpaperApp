package com.twismart.wallpapershd.ui.main

import com.twismart.wallpapershd.data.DataManager
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.data.remote.WallpaperService
import com.twismart.wallpapershd.ui.base.BasePresenter
import com.twismart.wallpapershd.utils.debug
import com.twismart.wallpapershd.utils.showValues
import java.util.ArrayList
import javax.inject.Inject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by sneyd on 5/5/2017.
 **/

class ListWallpapersPresenter<V : ListWallpapersContract.View>
@Inject constructor(dataManager: DataManager, compositeDisposable: CompositeDisposable) : BasePresenter<V>(dataManager, compositeDisposable), ListWallpapersContract.Presenter<V> {

    override fun getWallpapersList() {
        compositeDisposable.add(dataManager
                .getWallpapersList(WallpaperService.GET_WALLPAPERS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ArrayList<Wallpaper>>() {
                    override fun onNext(wallpapers: ArrayList<Wallpaper>) {
                        debug("onNext: " + wallpapers.toString())
                        baseView?.setWallpaperList(wallpapers)
                    }
                    override fun onError(e: Throwable) {
                        e.let { debug("onError ${e.message}") }
                    }
                    override fun onComplete() {
                        debug("onComplete")
                    }
                })
        )
    }

    override fun getAllFavoriteWallpapers() {
        compositeDisposable.add(dataManager
                .loadAllFavoriteWallpapers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    favoriteWallpapers: List<Wallpaper> ->
                    favoriteWallpapers.showValues()
                    baseView?.setWallpaperList(favoriteWallpapers)
                }
        )
    }
}