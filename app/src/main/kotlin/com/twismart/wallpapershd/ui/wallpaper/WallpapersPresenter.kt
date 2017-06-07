package com.twismart.wallpapershd.ui.wallpaper

import android.graphics.Bitmap
import com.twismart.wallpapershd.data.DataManager
import com.twismart.wallpapershd.ui.base.BasePresenterImpl
import com.twismart.wallpapershd.utils.debug

import javax.inject.Inject

import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by sneyd on 5/16/2017.
 **/

class WallpapersPresenter<V : WallpapersContract.View> @Inject
constructor(dataManager: DataManager, compositeDisposable: CompositeDisposable) : BasePresenterImpl<V>(dataManager, compositeDisposable), WallpapersContract.Presenter<V> {
    private val TAG = javaClass.simpleName

    override fun setWallpaper(url: String) {
        debug("WALLMANAGER " + (dataManager.mWallpaperManager==null))
        doAsync {
            val img: Bitmap? = DownloadImagesTask().doInBackground(url)
            img?.let { uiThread {  dataManager.mWallpaperManager.setBitmap(img) } }
        }

    }
}
