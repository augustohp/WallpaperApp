package com.twismart.wallpapershd.ui.wallpaper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.twismart.wallpapershd.data.model.Wallpaper;
import com.twismart.wallpapershd.ui.wallpaper.WallpaperFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sneyd on 4/19/2017.
 **/

public class WallpapersPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Wallpaper> wallpaperList = new ArrayList<>();
    private Context context;

    public WallpapersPagerAdapter(FragmentManager fragmentManager, Context context, ArrayList<Wallpaper> wallpaperList){
        super(fragmentManager);
        this.context = context;
        this.wallpaperList = wallpaperList;
    }

    @Override
    public Fragment getItem(int position) {
        return WallpaperFragment.newInstance(wallpaperList.get(position));
    }

    @Override
    public int getCount() {
        return wallpaperList.size();
    }
}