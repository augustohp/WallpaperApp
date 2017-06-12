package com.twismart.wallpapershd.ui.base

import android.support.annotation.StringRes

/**
 * Created by sneyd on 6/11/2017.
 **/

class BaseContract{
    interface Presenter<V> {
        fun attachView(view: V)
        fun dettachView()
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun onError(@StringRes resId: Int)
        fun onError(message: String)
        fun isNetworkConnected() : Boolean
        fun hideKeyboard()
    }
}