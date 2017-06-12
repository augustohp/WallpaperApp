package com.twismart.wallpapershd.ui.wallpaper

import android.graphics.Bitmap
import com.twismart.wallpapershd.data.DataManager
import com.twismart.wallpapershd.ui.base.BasePresenter
import com.twismart.wallpapershd.utils.debug
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsyncResult

/**
 * Created by sneyd on 5/16/2017.
 **/

class WallpapersPresenter<V : WallpapersContract.View> @Inject
constructor(dataManager: DataManager, compositeDisposable: CompositeDisposable) : BasePresenter<V>(dataManager, compositeDisposable), WallpapersContract.Presenter<V> {

    override fun setWallpaper(url: String) {
        compositeDisposable.add(dataManager
                .getBitmapFromUrl(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Bitmap?>(){
                    override fun onNext(bitmapWallpaper: Bitmap?) {
                        debug("onNext")
                        doAsyncResult {
                            //set Wallpaper in background because it consumes a lot of time
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
                        debug("onError")
                    }
                }))
    }
}
