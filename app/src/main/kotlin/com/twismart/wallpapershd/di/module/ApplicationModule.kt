package com.twismart.wallpapershd.di.module

import android.app.Application
import android.content.Context
import com.twismart.wallpapershd.data.local.database.IMyDatabaseHelper
import com.twismart.wallpapershd.data.local.database.MyDataBase
import com.twismart.wallpapershd.data.local.database.MyDatabaseHelper


import com.twismart.wallpapershd.data.remote.WallpaperService
import com.twismart.wallpapershd.di.annotation.ApplicationContext

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module class ApplicationModule(val mApplication: Application) {

    @Provides @ApplicationContext fun provideContext(): Context =  mApplication

    @Provides fun provideApplication(): Application = mApplication

    @Provides @Singleton fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(WallpaperService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides @Singleton fun provideWallpaperService(): WallpaperService = provideRetrofit().create(WallpaperService::class.java)

    @Provides @Singleton fun provideMyDataBase(): MyDataBase = MyDataBase(provideContext(), MyDataBase.DATABASE_NAME, null, MyDataBase.VERSION)

    @Provides @Singleton fun providesIMyDatabaseHelper(myDatabaseHelper: MyDatabaseHelper): IMyDatabaseHelper = myDatabaseHelper
}
