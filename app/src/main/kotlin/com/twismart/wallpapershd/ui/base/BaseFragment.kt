package com.twismart.wallpapershd.ui.base

import android.content.Context
import android.support.annotation.StringRes
import android.support.v4.app.Fragment

import com.twismart.wallpapershd.di.component.ActivityComponent

/**
 * Created by sneyd on 5/5/2017.
* */

abstract class BaseFragment : Fragment(), BaseView {

    var baseActivity: BaseActivity? = null
        private set

    val activityComponent: ActivityComponent? get() = baseActivity?.activityComponent

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            baseActivity = context as BaseActivity?
        }
    }

    override fun showLoading() {
        baseActivity.let {
            baseActivity?.showLoading()
        }
    }

    override fun hideLoading() {
        baseActivity.let {
            baseActivity?.hideLoading()
        }
    }

    override fun onError(@StringRes resId: Int) {
        if (baseActivity != null) {
            baseActivity!!.onError(resId)
        }
    }

    override fun onError(message: String) {
        baseActivity.let {
            baseActivity?.onError(message)
        }
    }

    override fun isNetworkConnected(): Boolean {
        baseActivity.let {
            return baseActivity!!.isNetworkConnected()
        }
    }

    override fun hideKeyboard() {
        baseActivity.let {
            baseActivity?.hideKeyboard()
        }
    }
}
