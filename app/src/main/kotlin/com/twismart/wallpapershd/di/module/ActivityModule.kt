/*
 * Copyright (C) 2017 Sneyder Angulo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.twismart.wallpapershd.di.module

import android.app.Activity
import com.twismart.wallpapershd.di.annotation.PerActivity

import com.twismart.wallpapershd.ui.main.WallpaperListContract
import com.twismart.wallpapershd.ui.main.WallpaperListPresenter
import com.twismart.wallpapershd.ui.wallpaper.WallpaperDetailContract
import com.twismart.wallpapershd.ui.wallpaper.WallpaperDetailPresenter

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module class ActivityModule(private val mActivity: Activity) {

    @Provides fun provideActivity(): Activity {
        return mActivity
    }

    @Provides @PerActivity fun listWallpapersPresenter(listPresenter: WallpaperListPresenter<WallpaperListContract.View>): WallpaperListContract.Presenter<WallpaperListContract.View> {
        return listPresenter
    }

    @Provides @PerActivity fun wallpapersPresenter(presenter: WallpaperDetailPresenter<WallpaperDetailContract.View>): WallpaperDetailContract.Presenter<WallpaperDetailContract.View> {
        return presenter
    }

    @Provides fun provideCompositeDisposable() = CompositeDisposable()
}
