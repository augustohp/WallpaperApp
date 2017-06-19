package com.twismart.wallpapershd.ui.main

import com.twismart.wallpapershd.data.IDataManager
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.data.remote.WallpaperService
import com.twismart.wallpapershd.ui.base.BasePresenter
import com.twismart.wallpapershd.utils.debug
import com.twismart.wallpapershd.utils.error
import io.reactivex.Observable
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
@Inject constructor(dataManager: IDataManager, compositeDisposable: CompositeDisposable) : BasePresenter<V>(dataManager, compositeDisposable), ListWallpapersContract.Presenter<V> {

    override fun getWallpapersList() {
        getListOfWallpapers(dataManager.loadWallpapersList(WallpaperService.GET_WALLPAPERS))
    }

    override fun getMostPopularWallpapers() {
        getListOfWallpapers(dataManager.loadWallpapersList(WallpaperService.GET_MOST_POPULAR_WALLPAPERS))
    }

    override fun getFavoriteWallpapers() {
        getListOfWallpapers(dataManager.loadFavoriteWallpapers())
    }

    fun getListOfWallpapers(func: Observable<ArrayList<Wallpaper>>){
        //use the same request scheme for the others methods because all of them return an Observable with an ArrayList of Wallpaper
        compositeDisposable.add(func
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ArrayList<Wallpaper>>() {
                    override fun onNext(wallpapers: ArrayList<Wallpaper>) {
                        //
                        baseView?.setWallpaperList(wallpapers)
                    }
                    override fun onError(e: Throwable) {
                        e.let { error("onError ${e.message}") }
                    }
                    override fun onComplete() {}
                })
        )
    }
}