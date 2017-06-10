package com.twismart.wallpapershd.utils

import android.app.NotificationManager
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by sneyd on 5/31/2017.
* */


fun Any.debug(msg: Any? = "Debug", tag: String = this.javaClass.toString()){
    Log.d(tag, msg.toString())
}

fun Any.warn(msg: Any? = "Warn", tag: String = this.javaClass.toString()){
    Log.w(tag, msg.toString())
}

fun Any.error(msg: Any? = "Error", tag: String = this.javaClass.toString()){
    Log.e(tag, msg.toString())
}


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

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Context.inflate(res: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false) : View {
    return LayoutInflater.from(this).inflate(res, parent, false)
}

val Context.notificationManager: NotificationManager
    get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager