package com.twismart.wallpapershd

import android.support.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.twismart.wallpapershd.di.component.ApplicationComponent
import com.twismart.wallpapershd.di.component.DaggerApplicationComponent
import com.twismart.wallpapershd.di.module.ApplicationModule
import io.fabric.sdk.android.Fabric

class WallpaperApplication : MultiDexApplication() {

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
        Stetho.initializeWithDefaults(this)
    }
}