package com.twismart.wallpapershd.ui.wallpaper

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.ui.wallpaper.WallpaperFragment

import java.util.ArrayList

/**
 * Created by sneyd on 4/19/2017.
 **/

class WallpapersPagerAdapter(fragmentManager: FragmentManager, val context: Context, val wallpaperList: ArrayList<Wallpaper>?) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = WallpaperFragment.newInstance(wallpaperList!![position])

    override fun getCount() = wallpaperList?.size ?: 0
}