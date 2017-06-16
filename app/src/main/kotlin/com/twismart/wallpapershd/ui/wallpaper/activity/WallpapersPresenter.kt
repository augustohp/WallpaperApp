package com.twismart.wallpapershd.ui.wallpaper.activity

import android.graphics.Bitmap
import com.twismart.wallpapershd.data.DataManager
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.ui.base.BasePresenter
import com.twismart.wallpapershd.utils.debug
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsyncResult
import javax.inject.Inject


/**
 * Created by sneyd on 5/16/2017.
 **/

class WallpapersPresenter<V : WallpapersContract.View>
@Inject constructor(dataManager: DataManager, compositeDisposable: CompositeDisposable) : BasePresenter<V>(dataManager, compositeDisposable), WallpapersContract.Presenter<V> {

    override fun setWallpaperFromUrl(url: String) {
        //load wallpaper(bitmap) from url
        compositeDisposable.add(dataManager
                .getBitmapFromUrl(url)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribeWith(object : io.reactivex.observers.DisposableObserver<Bitmap?>(){
                    override fun onNext(bitmapWallpaper: android.graphics.Bitmap?) {
                        debug("onNext")
                        //set Wallpaper in background because it consumes a lot of time
                        doAsyncResult {
                            bitmapWallpaper.let {
                                dataManager.mWallpaperManager.setBitmap(bitmapWallpaper)
                            }
                            debug("wallpaper is set up")
                        }
                    }
                    override fun onComplete() {
                        debug("onComplete")
                    }
                    override fun onError(e: Throwable?) {
                        e.let { debug("onError ${e!!.message}") }
                    }
                }))
    }


    override fun checkIfWallpaperIsFavorite(idWallpaper: String, positionFragment: Int) {
        compositeDisposable.add(dataManager
                .loadWallpaperFromFavorites(idWallpaper)
                .subscribeOn(Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Wallpaper?>() {
                    override fun onComplete() {
                        debug("onComplete")
                    }
                    override fun onError(t: Throwable?) {
                        t.let {
                            debug("onError ${t!!.message}")
                        }
                        baseView?.wallpaperIsNotInFavorites(positionFragment)
                    }
                    override fun onNext(wallpaper: Wallpaper?) {
                        debug("onNext checkIfWallpaperIsFavorite ")
                        baseView?.wallpaperIsInFavorites(positionFragment)
                    }
                })
        )
    }


    override fun addWallpaperToFavorites(wallpaperToFavorites: Wallpaper, positionFragment: Int) {
        compositeDisposable.add(dataManager
                .addWallpaperToFavorites(wallpaperToFavorites)
                .subscribeOn(Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Unit>() {
                    override fun onComplete() {
                        debug("onComplete")
                    }
                    override fun onError(t: Throwable?) {
                        t.let {
                            debug("onError ${t!!.message}")
                        }
                    }
                    override fun onNext(wallpaper: Unit) {
                        debug("onNext addWallpaperToFavorites ")
                        baseView?.wallpaperIsInFavorites(positionFragment)
                    }
                })
        )
    }


    override fun deleteWallpaperFromFavorites(id: String, positionFragment: Int) {
        compositeDisposable.add(dataManager
                .deleteWallpaperFromFavorites(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Unit>() {
                    override fun onComplete() {
                        debug("onComplete")
                    }
                    override fun onError(t: Throwable?) {
                        t.let {
                            debug("onError ${t!!.message}")
                        }
                    }
                    override fun onNext(wallpaper: Unit) {
                        debug("onNext deleteWallpaperFromFavorites ")
                        baseView?.wallpaperIsNotInFavorites(positionFragment)
                    }
                })
        )
    }
}
