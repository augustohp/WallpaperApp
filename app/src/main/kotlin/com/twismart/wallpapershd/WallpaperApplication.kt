package com.twismart.wallpapershd

import android.app.Application

import com.crashlytics.android.Crashlytics
import com.twismart.wallpapershd.data.DataManager
import com.twismart.wallpapershd.di.component.ApplicationComponent
import com.twismart.wallpapershd.di.component.DaggerApplicationComponent
import com.twismart.wallpapershd.di.module.ApplicationModule

import io.fabric.sdk.android.Fabric
import javax.inject.Inject

/**
 * Created by sneyd on 4/15/2017.
 **/

class WallpaperApplication : Application() {

    @Inject lateinit var mDataManager: DataManager

    val component: ApplicationComponent by lazy {
         DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
        component.inject(this)
    }
}