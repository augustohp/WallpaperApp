package com.twismart.wallpapershd.ui.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.utils.Constants

/**
 * Created by sneyd on 12/3/2016.
 **/

class MainPagerAdapter(fragmentManager: FragmentManager, private val context: Context) : FragmentPagerAdapter(fragmentManager) {

    private val titleFragments = intArrayOf(R.string.title_tab_all, R.string.title_tab_most_popular, R.string.title_tab_my_favorites)

    override fun getCount() = titleFragments.size

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ListWallpapersFragment.newInstance(Constants.TypeListWallpapers.ALL.value)
            1 -> ListWallpapersFragment.newInstance(Constants.TypeListWallpapers.MOST_POPULAR.value)
            else -> ListWallpapersFragment.newInstance(Constants.TypeListWallpapers.MY_FAVORITES.value)
        }
    }

    override fun getPageTitle(position: Int) = context.getString(titleFragments[position])
}