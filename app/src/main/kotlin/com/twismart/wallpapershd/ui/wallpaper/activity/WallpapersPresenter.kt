package com.twismart.wallpapershd.ui.wallpaper.activity

import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.data.IDataManager
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.data.remote.WallpaperService
import com.twismart.wallpapershd.ui.base.BasePresenter
import com.twismart.wallpapershd.utils.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by sneyd on 5/16/2017.
 **/

class WallpapersPresenter<V : WallpapersContract.View>
@Inject constructor(dataManager: IDataManager, compositeDisposable: CompositeDisposable) : BasePresenter<V>(dataManager, compositeDisposable), WallpapersContract.Presenter<V> {

    override fun setWallpaperFromUrl(url: String, positionFragment: Int) {
        //load wallpaper(bitmap) from url and set it
        baseView?.loadingWallpaper(positionFragment)
        compositeDisposable.add(dataManager
                .setWallpaperFromUrl(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Unit>(){
                    override fun onNext(h: Unit) {
                        //notify the view the wallpaper is ready
                        baseView?.readyWallpaper(positionFragment)
                        baseView?.showSnackBar(R.string.set_wallpaper_sucessfully)
                    }
                    override fun onComplete() {}
                    override fun onError(e: Throwable?) { baseView?.showSnackBar(R.string.set_wallpaper_error) }
                }))
    }


    override fun checkIfWallpaperIsFavorite(idWallpaper: String, positionFragment: Int) {
        compositeDisposable.add(dataManager
                .loadWallpaperFromFavorites(idWallpaper)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Wallpaper?>() {
                    override fun onComplete() { }
                    override fun onError(t: Throwable?) {
                        t?.let { debug("onError ${t.message}") }
                        //if wallpaper is not in favorites notify it
                        baseView?.wallpaperIsNotInFavorites(positionFragment)
                    }
                    override fun onNext(wallpaper: Wallpaper?) {
                        debug("onNext checkIfWallpaperIsFavorite ")
                        //if wallpaper is in favorites notify it
                        baseView?.wallpaperIsInFavorites(positionFragment)
                    }
                })
        )
    }


    override fun addWallpaperToFavorites(wallpaperToFavorites: Wallpaper, positionFragment: Int) {
        //add wallpapers in favorites in the local database
        requestToServer(dataManager.addWallpaperToFavorites(wallpaperToFavorites), {
            debug("onNext addWallpaperToFavorites ")
            baseView?.wallpaperIsInFavorites(positionFragment)
        })
        //increase rating of wallpaper in online database
        requestToServer(dataManager.increaseRating(WallpaperService.INCREASE_RATING, 3, wallpaperToFavorites.id.toInt()), {
            debug("onNext increaseRating ")
        })
    }

    override fun deleteWallpaperFromFavorites(id: String, positionFragment: Int) {
        //delete wallpaper from favorites in the local database
        requestToServer(dataManager.deleteWallpaperFromFavorites(id), {
            debug("onNext deleteWallpaperFromFavorites ")
            baseView?.wallpaperIsNotInFavorites(positionFragment)
        })
    }

    fun requestToServer(func: Observable<Unit>, next: () -> Unit) {
        //make a simple request to the server without to return any data
        compositeDisposable.add(func
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Unit>() {
                    override fun onNext(wallpapers: Unit) = next()
                    override fun onError(e: Throwable) = e.let { error("onError ${e.message}") }
                    override fun onComplete() {}
                })
        )
    }
}
