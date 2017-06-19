package com.twismart.wallpapershd.data

import com.twismart.wallpapershd.data.local.database.IMyDatabaseHelper
import com.twismart.wallpapershd.data.remote.WallpaperService
import io.reactivex.Observable

/**
 * Created by sneyd on 6/19/2017.
* */

interface IDataManager: IMyDatabaseHelper, WallpaperService {
    fun setWallpaperFromUrl(url: String): Observable<Unit>
}