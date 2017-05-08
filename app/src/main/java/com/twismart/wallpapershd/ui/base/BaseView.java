package com.twismart.wallpapershd.ui.base;

import android.support.annotation.StringRes;

public interface BaseView {

    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    boolean isNetworkConnected();

    void hideKeyboard();

}
