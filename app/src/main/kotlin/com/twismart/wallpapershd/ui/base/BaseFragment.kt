/*
 * Copyright (C) 2017 Sneyder Angulo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.twismart.wallpapershd.ui.base

import android.content.Context
import android.support.v4.app.Fragment

import com.twismart.wallpapershd.di.component.ActivityComponent

abstract class BaseFragment : Fragment(), BaseContract.View {

    var baseActivity: BaseActivity? = null

    val activityComponent: ActivityComponent? by lazy { baseActivity?.activityComponent }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            baseActivity = context
        }
    }

    override fun showLoading() {
        baseActivity?.showLoading()
    }

    override fun hideLoading() {
        baseActivity?.hideLoading()
    }

    override fun showToast(resId: Int) {
        baseActivity?.showToast(resId)
    }

    override fun showToast(message: String) {
        baseActivity?.showToast(message)
    }

    override fun showSnackBar(message: String) {
        baseActivity?.showSnackBar(message)
    }

    override fun showSnackBar(resId: Int) {
        baseActivity?.showSnackBar(resId)
    }

    override fun isNetworkConnected() = baseActivity?.isNetworkConnected() ?: false

    override fun hideKeyboard() {
        baseActivity?.hideKeyboard()
    }
}