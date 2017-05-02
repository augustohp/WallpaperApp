package com.twismart.wallpapershd.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by sneyd on 4/12/2017.
 **/

public class Constants {
    public static final String URL_WALLPAPERS = "http://52.45.144.5/wallpapers/";
    public static final String WALLPAPERS_LIST = "listWallpapers", WALLPAPER_TO_SHOW = "wallpaperToShow";

    public enum TypeListWallpapers {
        ALL("all"), MY_FAVORITES("myFavorites"), MOST_POPULAR("mostPopular");

        public String value;

        TypeListWallpapers(String value){
            this.value = value;
        }
    }
}
