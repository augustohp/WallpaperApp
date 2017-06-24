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

package com.twismart.wallpapershd.ui.main

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.twismart.wallpapershd.data.IDataManager
import com.twismart.wallpapershd.data.remote.WallpaperService
import com.twismart.wallpapershd.util.RxImmediateSchedulerRule
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Rule

class ListWallpapersPresenterTest {

    lateinit var mMockListWallpapersView: ListWallpapersContract.View
    lateinit var mListWallpapersPresenter: ListWallpapersPresenter<ListWallpapersContract.View>
    lateinit var mMockDataManager: IDataManager
    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Before
    fun setUp() {
        mMockDataManager = mock<IDataManager>()
        val compositeDisposable = CompositeDisposable()
        mListWallpapersPresenter = ListWallpapersPresenter(mMockDataManager, compositeDisposable)
        mMockListWallpapersView = mock<ListWallpapersContract.View>()
        mListWallpapersPresenter.attachView(mMockListWallpapersView)
    }

    @After
    fun tearDown() {
        mListWallpapersPresenter.detachView()
    }

    @Test
    fun getWallpapersList() {
        // prepare fake wallpapers
        val wallpapers = FakeWallpapersDataSource.makeListWallpapers(10)
        whenever(mMockDataManager.loadWallpapersList(WallpaperService.GET_WALLPAPERS)).thenReturn(Observable.just(wallpapers))

        // call
        mListWallpapersPresenter.getWallpapersList()

        // assert
        verify(mMockListWallpapersView).setWallpaperList(wallpapers)
    }

    @Test
    fun getMostPopularWallpapers() {
        // prepare fake wallpapers
        val wallpapers = FakeWallpapersDataSource.makeListWallpapers(10)
        whenever(mMockDataManager.loadWallpapersList(WallpaperService.GET_MOST_POPULAR_WALLPAPERS)).thenReturn(Observable.just(wallpapers))

        // call
        mListWallpapersPresenter.getMostPopularWallpapers()

        // assert
        verify(mMockListWallpapersView).setWallpaperList(wallpapers)
    }

    @Test
    fun getFavoriteWallpapers() {
        // prepare fake wallpapers
        val wallpapers = FakeWallpapersDataSource.makeListWallpapers(10)
        whenever(mMockDataManager.loadFavoriteWallpapers()).thenReturn(Observable.just(wallpapers))

        // call
        mListWallpapersPresenter.getFavoriteWallpapers()

        // assert
        verify(mMockListWallpapersView).setWallpaperList(wallpapers)
    }

}