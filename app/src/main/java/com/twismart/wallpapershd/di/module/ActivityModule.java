package com.twismart.wallpapershd.di.module;

import android.app.Activity;
import android.content.Context;

import com.twismart.wallpapershd.di.annotation.ActivityContext;
import com.twismart.wallpapershd.di.annotation.PerActivity;
import com.twismart.wallpapershd.ui.main.ListWallpapersContract;
import com.twismart.wallpapershd.ui.main.ListWallpapersPresenter;
import com.twismart.wallpapershd.ui.wallpaper.WallpapersContract;
import com.twismart.wallpapershd.ui.wallpaper.WallpapersPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    ListWallpapersContract.Presenter<ListWallpapersContract.View> listWallpapersPresenter(ListWallpapersPresenter<ListWallpapersContract.View> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    WallpapersContract.Presenter<WallpapersContract.View> wallpapersPresenter(WallpapersPresenter<WallpapersContract.View> presenter){
        return presenter;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }
}
