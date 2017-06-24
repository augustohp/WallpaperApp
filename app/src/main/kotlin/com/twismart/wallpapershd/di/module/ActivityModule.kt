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

    @Provides fun provideCompositeDisposable() = CompositeDisposable()
}
