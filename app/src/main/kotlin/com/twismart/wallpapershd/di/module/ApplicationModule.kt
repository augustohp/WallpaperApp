package com.twismart.wallpapershd.di.module

import android.app.Application
import android.content.Context


import com.twismart.wallpapershd.data.remote.WallpaperService
import com.twismart.wallpapershd.di.annotation.ApplicationContext

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module class ApplicationModule(val mApplication: Application) {

    @Provides fun provideContext(): Context {
        return mApplication
    }

    @Provides fun provideApplication(): Application {
        return mApplication
    }

    @Provides @Singleton fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(WallpaperService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides @Singleton fun provideWallpaperService(): WallpaperService {
        return WallpaperService.Factory.create()
    }
}
