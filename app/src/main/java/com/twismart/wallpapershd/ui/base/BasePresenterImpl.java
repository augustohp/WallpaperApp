package com.twismart.wallpapershd.ui.base;

/**
 * Created by sneyd on 5/5/2017.
 **/

public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V>{

    private V mBaseView;

    @Override
    public void attachView(V view) {
        mBaseView = view;
    }

    @Override
    public void dettachView() {
        mBaseView = null;
    }

    public V getBaseView() {
        return mBaseView;
    }
}
