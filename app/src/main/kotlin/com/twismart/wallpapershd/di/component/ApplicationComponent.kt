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

import android.app.Application
import android.content.Context

import com.twismart.wallpapershd.WallpaperApplication
import com.twismart.wallpapershd.data.IDataManager
import com.twismart.wallpapershd.data.local.database.MyDataBase
import com.twismart.wallpapershd.data.remote.WallpaperService
import com.twismart.wallpapershd.di.annotation.ApplicationContext
import com.twismart.wallpapershd.di.module.ApplicationModule
import javax.inject.Singleton
import dagger.Component
import retrofit2.Retrofit

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(app: WallpaperApplication)

    @ApplicationContext fun context(): Context

    fun application(): Application

    fun retrofit(): Retrofit

    fun wallpaperService(): WallpaperService

    fun dataManager(): IDataManager

    fun MyDataBase(): MyDataBase
}