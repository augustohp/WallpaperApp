package com.twismart.wallpapershd.ui.base;

import com.twismart.wallpapershd.data.DataManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by sneyd on 5/5/2017.
 **/

public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V>{

    private V mBaseView;

    private final DataManager mDataManager;

    private final CompositeDisposable mCompositeDisposable;

    @Inject
    public BasePresenterImpl(DataManager dataManager, CompositeDisposable compositeDisposable) {
        this.mDataManager = dataManager;
        this.mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void attachView(V view) {
        mBaseView = view;
    }

    @Override
    public void dettachView() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
        mBaseView = null;
    }

    public V getBaseView() {
        return mBaseView;
    }

    public boolean isViewAttached() {
        return mBaseView != null;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }
}
