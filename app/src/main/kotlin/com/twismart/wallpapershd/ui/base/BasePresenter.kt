package com.twismart.wallpapershd.ui.base

import com.twismart.wallpapershd.data.DataManager

import javax.inject.Inject

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by sneyd on 5/5/2017.
 **/

open class BasePresenter<V : BaseContract.View> @Inject
constructor(val dataManager: DataManager, val compositeDisposable: CompositeDisposable) : BaseContract.Presenter<V> {

    var baseView: V? = null

    override fun attachView(view: V) {
        baseView = view
    }

    override fun dettachView() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        baseView = null
    }

    val isViewAttached: Boolean get() = baseView != null
}
