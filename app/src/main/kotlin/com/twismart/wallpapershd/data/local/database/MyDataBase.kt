package com.twismart.wallpapershd.data.local.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.twismart.wallpapershd.data.model.Wallpaper

/**
 * Created by sneyd on 6/13/2017.
* */

class MyDataBase(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int)
    : SQLiteOpenHelper(context, name, factory, version) {

    companion object{
        val DATABASE_NAME = "database"
        val TABLE_NAME = "favoriteWallpapers"
        val COLUMN_NAME_ID = "id"
        val COLUMN_NAME_IMAGE_URL = "imageUrl"
        val COLUMN_NAME_IMAGE_PATH = "imagePath"
        val COLUMN_NAME_WIDTH = "width"
        val COLUMN_NAME_HEIGHT = "height"
        val COLUMN_NAME_LICENSE = "license"
        val COLUMN_NAME_RATING = "rating"
        val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME(" +
                "$COLUMN_NAME_ID text primary key, " +
                "$COLUMN_NAME_IMAGE_URL text, " +
                "$COLUMN_NAME_IMAGE_PATH text, " +
                "$COLUMN_NAME_WIDTH text, " +
                "$COLUMN_NAME_HEIGHT text, " +
                "$COLUMN_NAME_LICENSE text, " +
                "$COLUMN_NAME_RATING text)")

    }

    override fun onUpgrade(db: SQLiteDatabase, versionAnte: Int, versionNue: Int) {
    }
}

fun Cursor.toWallpaper() = Wallpaper(getString(0), getString(1), getString(2), getString(3), getString(4), getString(5), getString(6))

fun Wallpaper.toContentValues(): ContentValues{
    val values = ContentValues()
    values.put(MyDataBase.COLUMN_NAME_ID, id)
    values.put(MyDataBase.COLUMN_NAME_IMAGE_URL, urlImage)
    values.put(MyDataBase.COLUMN_NAME_IMAGE_PATH, pathImage)
    values.put(MyDataBase.COLUMN_NAME_WIDTH, width)
    values.put(MyDataBase.COLUMN_NAME_HEIGHT, height)
    values.put(MyDataBase.COLUMN_NAME_LICENSE, license)
    values.put(MyDataBase.COLUMN_NAME_RATING, rating)
    return values
}