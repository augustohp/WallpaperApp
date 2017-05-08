package com.twismart.wallpapershd.ui.base;

/**
 * Created by sneyd on 5/5/2017.
 **/

public interface BasePresenter<V> {
    void attachView(V view);
    void dettachView();
}
