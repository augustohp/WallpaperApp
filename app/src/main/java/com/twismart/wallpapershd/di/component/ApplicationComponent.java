package com.twismart.wallpapershd.di.component;

import android.app.Application;
import android.content.Context;

import com.twismart.wallpapershd.WallpaperApplication;
import com.twismart.wallpapershd.data.DataManager;
import com.twismart.wallpapershd.data.remote.WallpaperService;
import com.twismart.wallpapershd.di.annotation.ApplicationContext;
import com.twismart.wallpapershd.di.module.ApplicationModule;
import javax.inject.Singleton;
import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(WallpaperApplication app);

    @ApplicationContext
    Context context();

    Application application();

    Retrofit retrofit();

    WallpaperService wallpaperService();

    DataManager getDataManager();
}