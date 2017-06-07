package com.twismart.wallpapershd.data

import android.app.WallpaperManager
import android.content.Context

import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.data.remote.WallpaperService

import java.util.ArrayList

import javax.inject.Inject
import javax.inject.Singleton

import io.reactivex.Observable
import retrofit2.http.Url


@Singleton
class DataManager @Inject
constructor(private val mContext: Context, private val mWallpaperService: WallpaperService) {
    private val TAG = javaClass.simpleName

    val mWallpaperManager = WallpaperManager.getInstance(mContext)

    fun getWallpapersList(@Url url: String): Observable<ArrayList<Wallpaper>> {
        return mWallpaperService.getWallpapersList(url)
    }
}
