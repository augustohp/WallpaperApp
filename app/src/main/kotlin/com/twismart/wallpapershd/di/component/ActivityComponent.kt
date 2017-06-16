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
