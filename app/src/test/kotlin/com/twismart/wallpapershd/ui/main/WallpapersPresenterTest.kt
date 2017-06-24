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
import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.data.IDataManager
import com.twismart.wallpapershd.ui.wallpaper.activity.WallpapersContract
import com.twismart.wallpapershd.ui.wallpaper.activity.WallpapersPresenter
import com.twismart.wallpapershd.util.RxImmediateSchedulerRule
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.*

class WallpapersPresenterTest {

    lateinit var mMockWallpapersView: WallpapersContract.View
    lateinit var mWallpapersPresenter: WallpapersPresenter<WallpapersContract.View>
    lateinit var mMockDataManager: IDataManager
    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Before
    fun setUp() {
        mMockDataManager = mock<IDataManager>()
        val compositeDisposable = CompositeDisposable()
        mWallpapersPresenter = WallpapersPresenter<WallpapersContract.View>(mMockDataManager, compositeDisposable)
        mMockWallpapersView = mock<WallpapersContract.View>()
        mWallpapersPresenter.attachView(mMockWallpapersView)
    }

    @After
    fun tearDown() {
        mWallpapersPresenter.detachView()
    }

    @Test
    fun testWallpaperFromUrlSuccess() {
        // prepare data
        val positionFragment = 5
        val urlImg = "http://i.imgur.com/iWiH6S6.jpg"
        whenever(mMockDataManager.setWallpaperFromUrl(urlImg)).thenReturn(Observable.just(Unit))

        // call
        mWallpapersPresenter.setWallpaperFromUrl(urlImg, positionFragment)

        // assert
        verify(mMockWallpapersView).readyWallpaper(positionFragment)
        verify(mMockWallpapersView).showSnackBar(R.string.set_wallpaper_sucessfully)
    }
}