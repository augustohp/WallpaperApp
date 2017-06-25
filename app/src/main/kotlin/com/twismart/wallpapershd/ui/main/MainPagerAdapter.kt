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

package com.twismart.wallpapershd.ui.main


import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.support.v13.app.FragmentPagerAdapter
import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.utils.Constants

class MainPagerAdapter(fragmentManager: FragmentManager, private val context: Context) : FragmentPagerAdapter(fragmentManager) {

    private val titleFragments: IntArray by lazy { intArrayOf(R.string.title_tab_all, R.string.title_tab_most_popular, R.string.title_tab_my_favorites) }

    override fun getCount() = titleFragments.size

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> WallpaperListFragment.newInstance(Constants.TypeListWallpapers.ALL.value)
            1 -> WallpaperListFragment.newInstance(Constants.TypeListWallpapers.MOST_POPULAR.value)
            else -> WallpaperListFragment.newInstance(Constants.TypeListWallpapers.MY_FAVORITES.value)
        }
    }

    override fun getPageTitle(position: Int): String = context.getString(titleFragments[position])
}