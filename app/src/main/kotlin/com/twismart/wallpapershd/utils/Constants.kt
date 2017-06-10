package com.twismart.wallpapershd.utils

/**
 * Created by sneyd on 4/12/2017.
 **/

object Constants {
    val WALLPAPERS_LIST = "listWallpapers"
    val WALLPAPER_TO_SHOW = "wallpaperToShow"

    enum class TypeListWallpapers (var value: String) {
        ALL("all"), MY_FAVORITES("myFavorites"), MOST_POPULAR("mostPopular")
    }
}
