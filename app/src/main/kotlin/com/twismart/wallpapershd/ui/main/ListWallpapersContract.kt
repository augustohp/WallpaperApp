package com.twismart.wallpapershd.ui.main

import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.ui.base.BasePresenter
import com.twismart.wallpapershd.ui.base.BaseView

import java.util.ArrayList

/**
 * Created by sneyd on 5/8/2017.
 **/

class ListWallpapersContract {

    interface Presenter<V : ListWallpapersContract.View> : BasePresenter<V> {
        fun getWallpapersList()
    }

    interface View : BaseView {
        fun setWallpaperList(wallpaperList: ArrayList<Wallpaper>)
    }
}
