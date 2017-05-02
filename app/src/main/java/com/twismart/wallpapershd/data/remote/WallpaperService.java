package com.twismart.wallpapershd.data.remote;

import com.twismart.wallpapershd.data.model.Wallpaper;
import com.twismart.wallpapershd.ui.main.ListWallpapersRecyclerViewAdapter;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WallpaperService {
    @GET
    Call<ArrayList<Wallpaper>> getWallpapersList(@Url String url);

    class Factory {
        private static final String BASE_URL = "http://52.45.144.5/wallpapers/";
        private static WallpaperService mWallpaperService = null;

        public static WallpaperService create() {
            if (mWallpaperService == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                return retrofit.create(WallpaperService.class);
            }
            return mWallpaperService;
        }
    }
}