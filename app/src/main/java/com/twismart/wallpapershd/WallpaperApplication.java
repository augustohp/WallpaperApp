package com.twismart.wallpapershd;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.twismart.wallpapershd.data.DataManager;
import com.twismart.wallpapershd.di.component.ApplicationComponent;
import com.twismart.wallpapershd.di.component.DaggerApplicationComponent;
import com.twismart.wallpapershd.di.module.ApplicationModule;

import io.fabric.sdk.android.Fabric;
import javax.inject.Inject;

/**
 * Created by sneyd on 4/15/2017.
 **/

public class WallpaperApplication extends Application {

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        initAppComponent();
    }

    public void initAppComponent(){
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }
}