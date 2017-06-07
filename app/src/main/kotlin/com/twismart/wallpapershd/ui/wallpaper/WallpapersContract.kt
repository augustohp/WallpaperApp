package com.twismart.wallpapershd.ui.wallpaper

import com.twismart.wallpapershd.ui.base.BasePresenter
import com.twismart.wallpapershd.ui.base.BaseView

/**
 * Created by sneyd on 5/16/2017.
 **/

class WallpapersContract {

    interface Presenter<V : WallpapersContract.View> : BasePresenter<V> {
        fun setWallpaper(url: String)
    }

    interface View : BaseView
}