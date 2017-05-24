package com.twismart.wallpapershd.data;

import android.content.Context;

import com.twismart.wallpapershd.data.model.Wallpaper;
import com.twismart.wallpapershd.data.remote.WallpaperService;
import com.twismart.wallpapershd.di.annotation.ApplicationContext;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.http.Url;

/**
 * Created by sneyd on 5/1/2017.
 **/

@Singleton
public class DataManager {
    private final String TAG = getClass().getSimpleName();

    private Context mContext;
    private WallpaperService mWallpaperService;

    @Inject
    public DataManager(@ApplicationContext Context context, WallpaperService mWallpaperService) {
        this.mContext = context;
        this.mWallpaperService = mWallpaperService;
    }

    public Observable<ArrayList<Wallpaper>> getWallpapersList(@Url String url){
        return mWallpaperService.getWallpapersList(url);
    }
}
