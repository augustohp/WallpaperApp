package com.twismart.wallpapershd.ui.main

import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.ui.base.BaseContract

/**
 * Created by sneyd on 5/8/2017.
 **/

class ListWallpapersContract {

    interface Presenter<V : ListWallpapersContract.View> : BaseContract.Presenter<V> {
        fun getWallpapersList()
        fun getAllFavoriteWallpapers()
    }

    interface View : BaseContract.View {
        fun setWallpaperList(wallpaperList: List<Wallpaper>)
    }
}
