package com.twismart.wallpapershd.data

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.twismart.wallpapershd.data.local.database.IMyDatabaseHelper
import com.twismart.wallpapershd.data.remote.WallpaperService
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.di.annotation.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import io.reactivex.Observable
import retrofit2.http.Url
import java.net.URL

@Singleton class DataManager
@Inject constructor(
        @ApplicationContext val mContext: Context,
        private val mWallpaperService: WallpaperService,
        private val myDatabaseHelper: IMyDatabaseHelper)
    : IMyDatabaseHelper {

    val mWallpaperManager: WallpaperManager by lazy { WallpaperManager.getInstance(mContext) }

    fun getWallpapersList(@Url url: String) = mWallpaperService.getWallpapersList(url)

    fun getBitmapFromUrl(url: String): Observable<Bitmap?> {
        return Observable.create {
            subscriber ->
            var bitmapWallpaper: Bitmap? = null
            try {
                val inputStream = URL(url).openStream()
                bitmapWallpaper = BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            subscriber.onNext(bitmapWallpaper)
            subscriber.onComplete()
        }
    }

    override fun addWallpaperToFavorites(wallpaper: Wallpaper): Observable<Unit> = myDatabaseHelper.addWallpaperToFavorites(wallpaper)

    override fun loadAllFavoriteWallpapers(): Observable<List<Wallpaper>> = myDatabaseHelper.loadAllFavoriteWallpapers()

    override fun loadWallpaperFromFavorites(id: String): Observable<Wallpaper?> = myDatabaseHelper.loadWallpaperFromFavorites(id)

    override fun deleteWallpaperFromFavorites(id: String): Observable<Unit> = myDatabaseHelper.deleteWallpaperFromFavorites(id)
}