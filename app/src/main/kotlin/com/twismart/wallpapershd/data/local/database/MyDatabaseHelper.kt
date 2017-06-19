package com.twismart.wallpapershd.data.local.database

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
            myDatabase.writableDatabase.use { SQLiteDatabase ->
                if(SQLiteDatabase.insert(MyDataBase.TABLE_NAME, null, wallpaper.toContentValues()).toInt()!=-1){
                    subscriber.onNext(Unit)
                    subscriber.onComplete()
                }
                else{
                    subscriber.onError(Throwable())
                    subscriber.onComplete()
                }
            }
        }
    }

    override fun deleteWallpaperFromFavorites(id: String): Observable<Unit> {
        return Observable.create { subscriber ->
            myDatabase.writableDatabase.use { SQLiteDatabase ->
                if (SQLiteDatabase.delete(MyDataBase.TABLE_NAME, "id=?", arrayOf(id)) != 0){
                    subscriber.onNext(Unit)
                    subscriber.onComplete()
                }
                else{
                    subscriber.onError(Throwable())
                    subscriber.onComplete()
                }
            }
        }
    }


    override fun loadAllFavoriteWallpapers(): Observable<ArrayList<Wallpaper>> {
        return Observable.create { subscriber ->
            myDatabase.readableDatabase.use { SQLiteDatabase ->
                SQLiteDatabase.rawQuery("SELECT * FROM ${MyDataBase.TABLE_NAME}", null).use { cursor ->
                    val listWallpapers: ArrayList<Wallpaper> = ArrayList()
                    while (cursor.moveToNext()){
                        listWallpapers.add(cursor.toWallpaper())
                    }
                    subscriber.onNext(listWallpapers)
                    subscriber.onComplete()
                }
            }
        }
    }

    override fun loadWallpaperFromFavorites(id: String): Observable<Wallpaper?> {
        return Observable.create { subscriber ->
            myDatabase.readableDatabase.use { SQLiteDatabase ->
                SQLiteDatabase.rawQuery("SELECT * FROM ${MyDataBase.TABLE_NAME} WHERE id=$id", null).use { cursor ->
                    if(cursor.moveToFirst()) {
                        val wallpaper = cursor.toWallpaper()
                        subscriber.onNext(wallpaper)
                        subscriber.onComplete()
                    }
                    else{
                        subscriber.onError(Throwable())
                        subscriber.onComplete()
                    }
                }
            }
        }
    }
}