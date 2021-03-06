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

import android.support.annotation.StringRes

class BaseContract {
    interface Presenter<in V> {
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
        fun isNetworkConnected(): Boolean
        fun hideKeyboard(): Unit
    }
}