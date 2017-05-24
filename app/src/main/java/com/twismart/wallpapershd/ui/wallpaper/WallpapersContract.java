package com.twismart.wallpapershd.ui.wallpaper;

import com.twismart.wallpapershd.ui.base.BasePresenter;
import com.twismart.wallpapershd.ui.base.BaseView;

/**
 * Created by sneyd on 5/16/2017.
 **/

public class WallpapersContract {

    public interface Presenter<V extends WallpapersContract.View> extends BasePresenter<V> {
        void setWallpaper(String url);
    }

    public interface View extends BaseView {

    }
}