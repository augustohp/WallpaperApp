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

package com.twismart.wallpapershd.di.component

import com.twismart.wallpapershd.di.annotation.PerActivity
import com.twismart.wallpapershd.di.module.ActivityModule
import com.twismart.wallpapershd.ui.main.ListWallpapersFragment
import com.twismart.wallpapershd.ui.main.MainActivity
import com.twismart.wallpapershd.ui.wallpaper.fragment.WallpaperFragment
import com.twismart.wallpapershd.ui.wallpaper.activity.WallpapersActivity

import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: WallpapersActivity)

    fun inject(fragment: ListWallpapersFragment)

    fun inject(fragment: WallpaperFragment)
}
