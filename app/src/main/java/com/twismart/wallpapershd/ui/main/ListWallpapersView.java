package com.twismart.wallpapershd.ui.main;

import com.twismart.wallpapershd.data.model.Wallpaper;
import com.twismart.wallpapershd.ui.base.BaseView;

import java.util.ArrayList;

/**
 * Created by sneyd on 5/5/2017.
 **/

public interface ListWallpapersView extends BaseView {
    void setWallpaperList(ArrayList<Wallpaper> wallpaperList);
}
