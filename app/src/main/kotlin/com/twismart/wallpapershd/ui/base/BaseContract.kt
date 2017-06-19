package com.twismart.wallpapershd.ui.base

import android.support.annotation.StringRes

/**
 * Created by sneyd on 6/11/2017.
 **/

class BaseContract{
    interface Presenter<V> {
        fun attachView(view: V): Unit
        fun detachView(): Unit
    }

    interface View {
        fun showLoading(): Unit
        fun hideLoading(): Unit
        fun showToast(@StringRes resId: Int): Unit
        fun showToast(message: String): Unit
        fun showSnackBar(message: String): Unit
        fun showSnackBar(@StringRes resId: Int): Unit
        fun isNetworkConnected() : Boolean
        fun hideKeyboard(): Unit
    }
}