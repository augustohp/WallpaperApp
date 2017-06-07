package com.twismart.wallpapershd.ui.base

/**
 * Created by sneyd on 5/5/2017.
 **/

interface BasePresenter<V> {
    fun attachView(view: V)
    fun dettachView()
}
