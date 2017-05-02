package com.twismart.wallpapershd.ui.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.twismart.wallpapershd.R;
import com.twismart.wallpapershd.ui.main.ListWallpapersFragment;
import com.twismart.wallpapershd.utils.Constants;

/**
 * Created by sneyd on 12/3/2016.
* */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private int[] titleFragments = {R.string.title_tab_all, R.string.title_tab_most_popular, R.string.title_tab_my_favorites};
    private Context context;

    public MainPagerAdapter(FragmentManager fragmentManager, Context context){
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public int getCount() {
        return titleFragments.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ListWallpapersFragment.newInstance(Constants.TypeListWallpapers.ALL.value);
            case 1:
                return ListWallpapersFragment.newInstance(Constants.TypeListWallpapers.MOST_POPULAR.value);
            default:
                return ListWallpapersFragment.newInstance(Constants.TypeListWallpapers.MY_FAVORITES.value);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return context.getString(titleFragments[position]);
    }
}