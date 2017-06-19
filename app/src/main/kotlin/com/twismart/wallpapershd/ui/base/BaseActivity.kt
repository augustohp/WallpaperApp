package com.twismart.wallpapershd.ui.base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import com.twismart.wallpapershd.WallpaperApplication
import com.twismart.wallpapershd.di.component.ActivityComponent
import com.twismart.wallpapershd.di.component.DaggerActivityComponent
import com.twismart.wallpapershd.di.module.ActivityModule
import com.twismart.wallpapershd.utils.isNetworkConnected
import com.twismart.wallpapershd.utils.showLoadingDialog
import com.twismart.wallpapershd.utils.snackBar
import com.twismart.wallpapershd.utils.toast

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
        mProgressDialog = showLoadingDialog()
    }

    override fun hideLoading() {
        if (mProgressDialog?.isShowing ?: false)
            mProgressDialog?.cancel()
    }

    override fun showToast(resId: Int) = toast(resId)

    override fun showToast(message: String) = toast(message)

    override fun showSnackBar(message: String) = findViewById(android.R.id.content).snackBar(message).show()

    override fun showSnackBar(resId: Int) = findViewById(android.R.id.content).snackBar(resId).show()

    override fun isNetworkConnected() = applicationContext.isNetworkConnected()

    override fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    protected abstract fun setUp()
}
