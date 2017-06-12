package com.twismart.wallpapershd.ui.wallpaper

import com.twismart.wallpapershd.ui.base.BaseContract

/**
 * Created by sneyd on 5/16/2017.
 **/

class WallpapersContract {

    interface Presenter<V : WallpapersContract.View> : BaseContract.Presenter<V> {
        fun setWallpaper(url: String)
    }

    interface View : BaseContract.View
}