package com.twismart.wallpapershd.di.component;

import com.twismart.wallpapershd.di.annotation.PerActivity;
import com.twismart.wallpapershd.di.module.ActivityModule;
import com.twismart.wallpapershd.ui.main.ListWallpapersFragment;
import com.twismart.wallpapershd.ui.main.MainActivity;
import com.twismart.wallpapershd.ui.wallpaper.WallpaperFragment;
import com.twismart.wallpapershd.ui.wallpaper.WallpapersActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(WallpapersActivity activity);

    void inject(ListWallpapersFragment fragment);

    void inject(WallpaperFragment fragment);
}
