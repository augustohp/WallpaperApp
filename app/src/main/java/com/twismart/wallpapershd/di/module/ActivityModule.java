package com.twismart.wallpapershd.di.module;

import android.app.Activity;
import android.content.Context;

import com.twismart.wallpapershd.di.annotation.ActivityContext;
import com.twismart.wallpapershd.di.annotation.PerActivity;
import com.twismart.wallpapershd.ui.main.ListWallpapersPresenter;
import com.twismart.wallpapershd.ui.main.ListWallpapersPresenterImpl;
import com.twismart.wallpapershd.ui.main.ListWallpapersView;

import dagger.Module;
import dagger.Provides;

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
    ListWallpapersPresenter<ListWallpapersView> listWallpapersPresenter(ListWallpapersPresenterImpl<ListWallpapersView> presenter){
        return presenter;
    }
}
