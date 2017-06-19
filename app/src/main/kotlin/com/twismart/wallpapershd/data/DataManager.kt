package com.twismart.wallpapershd.data

import android.annotation.TargetApi
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.data.local.database.IMyDatabaseHelper
import com.twismart.wallpapershd.data.remote.WallpaperService
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.di.annotation.ApplicationContext
import com.twismart.wallpapershd.utils.isNougatOrLater
import javax.inject.Inject
import javax.inject.Singleton
import io.reactivex.Observable
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.uiThread
import retrofit2.http.Url
import java.net.URL

@Singleton class DataManager
@Inject constructor(
        private @ApplicationContext val mContext: Context,
        private val mWallpaperService: WallpaperService,
        private val myDatabaseHelper: IMyDatabaseHelper)
    : IDataManager {

    private val mWallpaperManager: WallpaperManager by lazy { WallpaperManager.getInstance(mContext) }

    override fun loadWallpapersList(@Url url: String) = mWallpaperService.loadWallpapersList(url)

    override fun increaseRating(url: String, extraRating: Int, idWallpaper: Int): Observable<Unit> = mWallpaperService.increaseRating(url, extraRating, idWallpaper)

    override fun setWallpaperFromUrl(url: String): Observable<Unit> {
        return Observable.create {
            subscriber ->
            try {
                val inputStream = URL(url).openStream()
                val bitmapWallpaper = BitmapFactory.decodeStream(inputStream)
                subscriber.onNext(setWallpaper(bitmapWallpaper))
                subscriber.onComplete()
            } catch (e: Exception) {
                e.printStackTrace()
                subscriber.onError(Throwable())
                subscriber.onComplete()
            }
        }
    }

    @TargetApi(24)
    fun setWallpaper(bitmapWallpaper: Bitmap){
        //if version API is at least nougat
        if (isNougatOrLater()){
            //get the wallpaper's width and height
            val wallpaperWidth : Int = bitmapWallpaper.width
            val wallpaperHeight: Int = bitmapWallpaper.height

            //set the wallpaper in lock screen
            mWallpaperManager.setBitmap(
                    bitmapWallpaper,
                    Rect(0, 0, wallpaperWidth, wallpaperHeight),
                    true,
                    WallpaperManager.FLAG_LOCK)

            //set the wallpaper in home(normal) screen
            mWallpaperManager.setBitmap(
                    bitmapWallpaper,
                    Rect(0, 0, wallpaperWidth, wallpaperHeight),
                    true,
                    WallpaperManager.FLAG_SYSTEM)
        }//else set wallpaper in a normal and typical way
        else{
            mWallpaperManager.setBitmap(bitmapWallpaper)
        }

    }

    override fun addWallpaperToFavorites(wallpaper: Wallpaper): Observable<Unit> = myDatabaseHelper.addWallpaperToFavorites(wallpaper)

    override fun loadFavoriteWallpapers(): Observable<ArrayList<Wallpaper>> = myDatabaseHelper.loadFavoriteWallpapers()

    override fun loadWallpaperFromFavorites(id: String): Observable<Wallpaper?> = myDatabaseHelper.loadWallpaperFromFavorites(id)

    override fun deleteWallpaperFromFavorites(id: String): Observable<Unit> = myDatabaseHelper.deleteWallpaperFromFavorites(id)
}