package com.twismart.wallpapershd.data.local.database

import com.twismart.wallpapershd.data.model.Wallpaper
import io.reactivex.Observable

/**
 * Created by sneyd on 6/13/2017.
 **/
interface IMyDatabaseHelper {
    fun addWallpaperToFavorites(wallpaper: Wallpaper): Observable<Unit>
    fun deleteWallpaperFromFavorites(id: String): Observable<Unit>
    fun loadAllFavoriteWallpapers(): Observable<List<Wallpaper>>
    fun loadWallpaperFromFavorites(id: String): Observable<Wallpaper?>
}