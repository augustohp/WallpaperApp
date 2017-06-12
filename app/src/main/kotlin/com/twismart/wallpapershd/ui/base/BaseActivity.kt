package com.twismart.wallpapershd.ui.base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import com.twismart.wallpapershd.WallpaperApplication
import com.twismart.wallpapershd.di.component.ActivityComponent
import com.twismart.wallpapershd.di.component.DaggerActivityComponent
import com.twismart.wallpapershd.di.module.ActivityModule
import com.twismart.wallpapershd.utils.isNetworkConnected
import com.twismart.wallpapershd.utils.showLoadingDialog

abstract class BaseActivity : AppCompatActivity(), BaseContract.View {

    var mProgressDialog: ProgressDialog? = null

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as WallpaperApplication).component)
                .build()
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun showLoading() {
        hideLoading()
        mProgressDialog = showLoadingDialog(this)
    }

    override fun hideLoading() {
        mProgressDialog?.let {
            if (mProgressDialog!!.isShowing) {
                mProgressDialog?.cancel()
            }
        }
    }

    override fun onError(message: String) = showSnackBar(message)

    fun showSnackBar(message: String) = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()

    override fun onError(@StringRes resId: Int) = onError(getString(resId))

    override fun isNetworkConnected() = applicationContext.isNetworkConnected()

    override fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onDestroy() = super.onDestroy()

    protected abstract fun setUp()
}
