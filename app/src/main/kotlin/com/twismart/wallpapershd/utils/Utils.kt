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
@file:Suppress("unused")

package com.twismart.wallpapershd.utils

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.os.Environment
import android.provider.Settings
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern


// Logging
fun Any.debug(msg: () -> Any?, tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.d("TAG $tag", msg()?.toString() ?: "null", thr)
}

fun Any.debug(msg: Any? = "Debug", tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.d("TAG $tag", msg?.toString() ?: "null", thr)
}


fun Any.info(msg: () -> Any?, tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.i("TAG $tag", msg()?.toString() ?: "null", thr)
}

fun Any.info(msg: Any? = "Info", tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.i("TAG $tag", msg?.toString() ?: "null", thr)
}


fun Any.wtf(msg: () -> Any?, tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.wtf("TAG $tag", msg()?.toString() ?: "null", thr)
}

fun Any.wtf(msg: Any? = "WTF", tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.wtf("TAG $tag", msg?.toString() ?: "null", thr)
}


fun Any.warn(msg: () -> Any?, tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.w("TAG $tag", msg()?.toString() ?: "null", thr)
}

fun Any.warn(msg: Any? = "Warn", tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.w("TAG $tag", msg?.toString() ?: "null", thr)
}


fun Any.error(msg: () -> Any?, tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.e("TAG $tag", msg()?.toString() ?: "null", thr)
}

fun Any.error(msg: Any? = "Error", tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.e("TAG $tag", msg?.toString() ?: "null", thr)
}


fun Any.verbose(msg: () -> Any?, tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.v("TAG $tag", msg()?.toString() ?: "null", thr)
}

fun Any.verbose(msg: Any? = "Error", tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.v("TAG $tag", msg?.toString() ?: "null", thr)
}

private fun Class<*>.getTag(): String {
    val tag = simpleName
    return if (tag.length <= 19)
        tag
    else
        tag.substring(0, 19)
}


// Toasts
fun Fragment.toast(text: String) = activity.toast(text)

fun Fragment.toast(@StringRes resId: Int) = activity.toast(resId)
fun Context.toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
fun Context.toast(@StringRes resId: Int) = Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()

fun Fragment.longToast(text: String) = activity.longToast(text)
fun Fragment.longToast(@StringRes resId: Int) = activity.longToast(resId)
fun Context.longToast(text: String) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()
fun Context.longToast(@StringRes resId: Int) = Toast.makeText(this, resId, Toast.LENGTH_LONG).show()


// SnackBars
fun Activity.snackBar(
        message: String,
        view: View = findViewById(android.R.id.content)
): Snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)

fun Activity.snackBar(
        @StringRes resId: Int,
        view: View = findViewById(android.R.id.content)
): Snackbar = Snackbar.make(view, resId, Snackbar.LENGTH_SHORT)

fun Activity.longSnackBar(message: String) = findViewById(android.R.id.content).longSnackBar(message)

fun Activity.longSnackBar(@StringRes resId: Int) = findViewById(android.R.id.content).longSnackBar(resId)


fun View.snackBar(message: String) = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)

fun View.snackBar(@StringRes resId: Int) = Snackbar.make(this, resId, Snackbar.LENGTH_SHORT)

fun View.longSnackBar(message: String) = Snackbar.make(this, message, Snackbar.LENGTH_LONG)

fun View.longSnackBar(@StringRes resId: Int) = Snackbar.make(this, resId, Snackbar.LENGTH_LONG)


// Dimensions
fun View.setWidth(width: Int) {
    val params = layoutParams
    params.width = width
    layoutParams = params
}

fun View.setHeight(height: Int) {
    val params = layoutParams
    params.height = height
    layoutParams = params
}

fun View.setDimensions(width: Int, height: Int) {
    val params = layoutParams
    params.width = width
    params.height = height
    layoutParams = params
}


fun Activity.screenWidth(): Int {
    val metrics: DisplayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    return metrics.widthPixels
}

fun Activity.screenHeight(): Int {
    val metrics: DisplayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    return metrics.heightPixels
}


fun Context.screenWidth(): Int {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    return dm.widthPixels
}

fun Context.screenHeight(): Int {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    return dm.heightPixels
}

fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}


// Fragments
fun Activity.addFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment,
        func: (FragmentTransaction.() -> FragmentTransaction)? = null
) {
    fragmentManager.beginTransaction().apply {
        add(containerViewId, fragment)
        func?.let { func() }
    }.commit()
}

fun Activity.addFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment,
        fragmentTag: String,
        func: (FragmentTransaction.() -> FragmentTransaction)? = null
) {
    fragmentManager.beginTransaction().apply {
        add(containerViewId, fragment, fragmentTag)
        func?.let { func() }
    }.commit()
}


fun Activity.replaceFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment,
        func: (FragmentTransaction.() -> FragmentTransaction)? = null
) {
    fragmentManager.beginTransaction().apply {
        replace(containerViewId, fragment)
        func?.let { func() }
    }.commit()
}


fun Activity.replaceFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment,
        fragmentTag: String,
        func: (FragmentTransaction.() -> FragmentTransaction)? = null
) {
    fragmentManager.beginTransaction().apply {
        replace(containerViewId, fragment, fragmentTag)
        func?.let { func() }
    }.commit()
}


// Dialogs
fun Context.progressDialog(
        title: String? = null,
        message: String? = null,
        indeterminate: Boolean = true,
        init: (ProgressDialog.() -> Unit)? = null

): ProgressDialog = ProgressDialog(this).apply {
    isIndeterminate = indeterminate
    if (!indeterminate) setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
    title?.let { setTitle(title) }
    message?.let { setMessage(message) }
    init?.let { init() }
}


fun Context.alertDialog(
        title: CharSequence? = null,
        message: CharSequence? = null,
        positiveText: CharSequence? = null,
        positiveEvent: (() -> Unit)? = null,
        negativeText: CharSequence? = null,
        negativeEvent: (() -> Unit)? = null

): AlertDialog.Builder = AlertDialog.Builder(this).apply {
    title?.let { setTitle(title) }
    message?.let { setMessage(message) }
    positiveText?.let {
        setPositiveButton(positiveText) { _, _ ->
            positiveEvent?.let { positiveEvent() }
        }
    }
    negativeText?.let {
        setNegativeButton(negativeText) { _, _ ->
            negativeEvent?.let { negativeEvent() }
        }
    }
}


fun Context.selectorList(
        title: CharSequence? = null,
        items: Array<CharSequence>,
        onClick: ((Int) -> Unit)? = null

): AlertDialog.Builder = AlertDialog.Builder(this).apply {
    title?.let { setTitle(title) }
    setItems(items) { _, item ->
        onClick?.let { onClick(item) }
    }
}

fun Context.selectorSingle(
        title: CharSequence? = null,
        items: Array<CharSequence>,
        checkedItem: Int = -1,
        onClick: ((Int) -> Unit)? = null

): AlertDialog.Builder = AlertDialog.Builder(this).apply {
    title?.let { setTitle(title) }
    setSingleChoiceItems(items, checkedItem) { _, item ->
        onClick?.let { onClick(item) }
    }
}

fun Context.selectorMultiple(
        title: CharSequence? = null,
        items: Array<CharSequence>,
        checkedItems: BooleanArray,
        onClick: ((Int, Boolean) -> Unit)? = null
) = AlertDialog.Builder(this).apply {
    title?.let { setTitle(title) }
    setMultiChoiceItems(items, checkedItems) { _, item, isChecked ->
        onClick?.let { onClick(item, isChecked) }
    }
}


// Views visibility
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}


fun Context.inflate(
        res: Int,
        parent: ViewGroup? = null,
        attachToRoot: Boolean = false)
        : View = LayoutInflater.from(this).inflate(res, parent, attachToRoot)


// Keyboard utils
fun Activity.hideSoftInput() {
    var view = currentFocus
    if (view == null) view = View(this)
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showSoftInput(edit: EditText) {
    edit.isFocusable = true
    edit.isFocusableInTouchMode = true
    edit.requestFocus()
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(edit, 0)
}

fun Context.toggleSoftInput() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}


// Strings
fun String.isValidEmail(): Boolean {
    val EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    val pattern = Pattern.compile(EMAIL_PATTERN)
    return pattern.matcher(this).matches()
}


// Network
fun Context.isNetworkConnected(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}


// Storage
fun isExternalStorageWritable(): Boolean = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED


// Collections
fun <T> Collection<T>.showValues() {
    forEach { debug(it) }
}


// Check API Version
fun isJellyBeanOrLater(): Boolean = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN

fun isLollipopOrLater(): Boolean = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP

fun isNougatOrLater(): Boolean = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N


// Math
fun Int.toAbsoluteValue() = Math.abs(this)

fun Float.pxToDp(): Float {
    val densityDpi = Resources.getSystem().displayMetrics.densityDpi.toFloat()
    return this / (densityDpi / 160f)
}

fun Float.dpToPx(): Int {
    val density = Resources.getSystem().displayMetrics.density
    return Math.round(this * density)
}


// Some other stuff
val Context.notificationManager: NotificationManager
    get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

@SuppressLint("all")
fun getDeviceId(context: Context)
        : String = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)