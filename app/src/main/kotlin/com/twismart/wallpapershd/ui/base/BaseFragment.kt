package com.twismart.wallpapershd.ui.base

import android.content.Context
import android.support.annotation.StringRes
import android.support.v4.app.Fragment

import com.twismart.wallpapershd.di.component.ActivityComponent

/**
 * Created by sneyd on 5/5/2017.
* */

abstract class BaseFragment : Fragment(), BaseContract.View {

    lateinit var baseActivity: BaseActivity

    val activityComponent: ActivityComponent by lazy{ baseActivity.activityComponent }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            baseActivity = context
        }
    }

    override fun showLoading() = baseActivity.showLoading()

    override fun hideLoading() = baseActivity.hideLoading()

    override fun onError(@StringRes resId: Int) = baseActivity.onError(resId)

    override fun onError(message: String) = baseActivity.onError(message)

    override fun isNetworkConnected() = baseActivity.isNetworkConnected()

    override fun hideKeyboard() = baseActivity.hideKeyboard()
}