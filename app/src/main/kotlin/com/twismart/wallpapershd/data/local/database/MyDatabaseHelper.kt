package com.twismart.wallpapershd.data.local.database

import android.content.ContentValues
import com.twismart.wallpapershd.data.model.Wallpaper
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by sneyd on 6/13/2017.
**/

@Singleton class MyDatabaseHelper
@Inject constructor(val myDatabase: MyDataBase)
    : IMyDatabaseHelper {

    override fun addWallpaperToFavorites(wallpaper: Wallpaper): Observable<Unit> {
        return Observable.create { subscriber ->

            val SQLiteDatabase = myDatabase.writableDatabase

            val values = ContentValues()
            values.put(MyDataBase.COLUMN_NAME_ID, wallpaper.id)
            values.put(MyDataBase.COLUMN_NAME_IMAGE_URL, wallpaper.urlImage)
            values.put(MyDataBase.COLUMN_NAME_IMAGE_PATH, wallpaper.pathImage)
            values.put(MyDataBase.COLUMN_NAME_WIDTH, wallpaper.width)
            values.put(MyDataBase.COLUMN_NAME_HEIGHT, wallpaper.height)
            values.put(MyDataBase.COLUMN_NAME_LICENSE, wallpaper.license)
            values.put(MyDataBase.COLUMN_NAME_RATING, wallpaper.rating)

            if(SQLiteDatabase.insert(MyDataBase.TABLE_NAME, null, values).toInt()!=-1){
                subscriber.onNext(Unit)
                subscriber.onComplete()
            }
            else{
                subscriber.onError(Throwable())
                subscriber.onComplete()
            }
        }
    }

    override fun deleteWallpaperFromFavorites(id: String): Observable<Unit> {
        return Observable.create { subscriber ->

            val SQLiteDatabase = myDatabase.writableDatabase

            if (SQLiteDatabase.delete(MyDataBase.TABLE_NAME, "id=$id", null) != 0){
                subscriber.onNext(Unit)
                subscriber.onComplete()
            }
            else{
                subscriber.onError(Throwable())
                subscriber.onComplete()
            }
        }
    }


    override fun loadAllFavoriteWallpapers(): Observable<List<Wallpaper>> {
        return Observable.create { subscriber ->

            val SQLiteDatabase = myDatabase.readableDatabase
            val cursor = SQLiteDatabase.rawQuery("SELECT * FROM ${MyDataBase.TABLE_NAME}", null)
            val listWallpapers: ArrayList<Wallpaper> = ArrayList()
            while (cursor.moveToNext()){
                listWallpapers.add(cursor.toWallpaper())
            }
            cursor.close()
            subscriber.onNext(listWallpapers)
            subscriber.onComplete()
        }
    }

    override fun loadWallpaperFromFavorites(id: String): Observable<Wallpaper?> {
        return Observable.create { subscriber ->

            val SQLiteDatabase = myDatabase.readableDatabase
            val cursor = SQLiteDatabase.rawQuery("SELECT * FROM ${MyDataBase.TABLE_NAME} WHERE id=$id", null)

            if(cursor.moveToFirst()) {
                val wallpaper = cursor.toWallpaper()
                cursor.close()
                subscriber.onNext(wallpaper)
                subscriber.onComplete()
            }
            else{
                cursor.close()
                subscriber.onError(Throwable())
                subscriber.onComplete()
            }
        }
    }
}