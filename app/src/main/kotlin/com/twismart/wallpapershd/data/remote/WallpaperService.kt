package com.twismart.wallpapershd.data.remote

import com.twismart.wallpapershd.data.model.Wallpaper
import java.util.ArrayList

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface WallpaperService {

    @GET fun getWallpapersList(@Url url: String): Observable<ArrayList<Wallpaper>>

    companion object {
        val END_POINT = "http://52.45.144.5/wallpapers/"
        val GET_WALLPAPERS = "getWallpapers.php"
    }
}