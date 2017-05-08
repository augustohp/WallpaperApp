package com.twismart.wallpapershd.ui.main;

import com.twismart.wallpapershd.ui.base.BasePresenter;

/**
 * Created by sneyd on 5/5/2017.
 **/

public interface ListWallpapersPresenter<V extends ListWallpapersView> extends BasePresenter<V> {
    void getWallpapersList();
}
