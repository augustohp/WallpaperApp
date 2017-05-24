package com.twismart.wallpapershd.ui.main;

import com.twismart.wallpapershd.data.model.Wallpaper;
import com.twismart.wallpapershd.ui.base.BasePresenter;
import com.twismart.wallpapershd.ui.base.BaseView;

import java.util.ArrayList;

/**
 * Created by sneyd on 5/8/2017.
 **/

public class ListWallpapersContract {

    public interface Presenter<V extends ListWallpapersContract.View> extends BasePresenter<V> {
        void getWallpapersList();
    }

    public interface View extends BaseView {
        void setWallpaperList(ArrayList<Wallpaper> wallpaperList);
    }
}
