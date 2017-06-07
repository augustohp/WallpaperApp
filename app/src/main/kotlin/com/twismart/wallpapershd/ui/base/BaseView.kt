package com.twismart.wallpapershd.ui.base

import android.support.annotation.StringRes

interface BaseView {

    fun showLoading()

    fun hideLoading()

    fun onError(@StringRes resId: Int)

    fun onError(message: String)

    fun isNetworkConnected() : Boolean

    fun hideKeyboard()

}
