package com.twismart.wallpapershd.ui.base

import com.twismart.wallpapershd.data.IDataManager

import javax.inject.Inject

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by sneyd on 5/5/2017.
 **/

open class BasePresenter<V : BaseContract.View>
@Inject constructor(val dataManager: IDataManager, val compositeDisposable: CompositeDisposable) : BaseContract.Presenter<V> {

    var baseView: V? = null

    override fun attachView(view: V) {
        baseView = view
    }

    override fun detachView() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        baseView = null
    }

    val isViewAttached: Boolean get() = baseView != null
}
