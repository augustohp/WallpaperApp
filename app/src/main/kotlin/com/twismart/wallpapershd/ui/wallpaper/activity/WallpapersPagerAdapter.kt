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

package com.twismart.wallpapershd.ui.wallpaper.activity

import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.ui.wallpaper.fragment.WallpaperFragment
import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class WallpapersPagerAdapter(fragmentManager: FragmentManager, val context: Context, val wallpaperList: ArrayList<Wallpaper>?) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = WallpaperFragment.Companion.newInstance(wallpaperList!![position], position)

    override fun getCount() = wallpaperList?.size ?: 0
}