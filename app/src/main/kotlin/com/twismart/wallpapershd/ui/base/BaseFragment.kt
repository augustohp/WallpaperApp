package com.twismart.wallpapershd.ui.base

import android.content.Context
import android.support.annotation.StringRes
import android.support.v4.app.Fragment

import com.twismart.wallpapershd.di.component.ActivityComponent

/**
 * Created by sneyd on 5/5/2017.
* */

abstract class BaseFragment : Fragment(), BaseContract.View {

    var baseActivity: BaseActivity? = null

    val activityComponent: ActivityComponent? by lazy{ baseActivity?.activityComponent }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            baseActivity = context
        }
    }

    override fun showLoading() { baseActivity?.showLoading() }

    override fun hideLoading() { baseActivity?.hideLoading() }

    override fun showToast(resId: Int) { baseActivity?.showToast(resId)}

    override fun showToast(message: String) { baseActivity?.showToast(message) }

    override fun showSnackBar(message: String) { baseActivity?.showSnackBar(message) }

    override fun showSnackBar(resId: Int) { baseActivity?.showSnackBar(resId) }

    override fun isNetworkConnected() = baseActivity?.isNetworkConnected() ?: false

    override fun hideKeyboard() { baseActivity?.hideKeyboard() }
}