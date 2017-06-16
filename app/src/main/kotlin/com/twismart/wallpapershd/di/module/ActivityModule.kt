package com.twismart.wallpapershd.di.module

import android.app.Activity
import com.twismart.wallpapershd.di.annotation.PerActivity

import com.twismart.wallpapershd.ui.main.ListWallpapersContract
import com.twismart.wallpapershd.ui.main.ListWallpapersPresenter
import com.twismart.wallpapershd.ui.wallpaper.activity.WallpapersContract
import com.twismart.wallpapershd.ui.wallpaper.activity.WallpapersPresenter

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module class ActivityModule(private val mActivity: Activity) {

    @Provides fun provideActivity(): Activity {
        return mActivity
    }

    @Provides @PerActivity fun listWallpapersPresenter(presenter: ListWallpapersPresenter<ListWallpapersContract.View>): ListWallpapersContract.Presenter<ListWallpapersContract.View> {
        return presenter
    }

    @Provides @PerActivity fun wallpapersPresenter(presenter: WallpapersPresenter<WallpapersContract.View>): WallpapersContract.Presenter<WallpapersContract.View> {
        return presenter
    }

    @Provides fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}
