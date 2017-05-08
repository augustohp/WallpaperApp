package com.twismart.wallpapershd.data;

import android.content.Context;

import com.twismart.wallpapershd.data.model.Wallpaper;
import com.twismart.wallpapershd.data.remote.WallpaperService;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by sneyd on 5/1/2017.
 **/

@Singleton
public class DataManager {
    private final String TAG = getClass().getSimpleName();

    private Context mContext;
    private WallpaperService mWallpaperService;

    @Inject
    public DataManager(WallpaperService mWallpaperService) {
        this.mWallpaperService = mWallpaperService;
    }

    public Observable<ArrayList<Wallpaper>> getWallpapersList(@Url String url){
        return mWallpaperService.getWallpapersList(url);
    }
}
