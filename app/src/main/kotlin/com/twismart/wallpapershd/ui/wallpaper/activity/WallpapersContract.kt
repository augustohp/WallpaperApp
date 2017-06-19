package com.twismart.wallpapershd.ui.wallpaper.activity

import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.ui.base.BaseContract

/**
 * Created by sneyd on 5/16/2017.
 **/

class WallpapersContract {

    interface Presenter<V : View> : BaseContract.Presenter<V> {
        fun setWallpaperFromUrl(url: String, positionFragment: Int)
        fun checkIfWallpaperIsFavorite(idWallpaper: String, positionFragment: Int)
        fun addWallpaperToFavorites(wallpaperToFavorites: Wallpaper, positionFragment: Int)
        fun deleteWallpaperFromFavorites(id: String, positionFragment: Int)
    }

    interface View : BaseContract.View {
        fun wallpaperIsInFavorites(positionFragment: Int)
        fun wallpaperIsNotInFavorites(positionFragment: Int)
        fun loadingWallpaper(positionFragment: Int)
        fun readyWallpaper(positionFragment: Int)
    }
}