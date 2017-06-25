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
import com.twismart.wallpapershd.ui.wallpaper.WallpaperDetailContract
import com.twismart.wallpapershd.ui.wallpaper.WallpaperDetailPresenter
import com.twismart.wallpapershd.util.RxImmediateSchedulerRule
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.*

class WallpaperDetailPresenterTest {

    lateinit var mMockWallpaperDetailView: WallpaperDetailContract.View
    lateinit var mWallpaperDetailPresenter: WallpaperDetailPresenter<WallpaperDetailContract.View>
    lateinit var mMockDataManager: IDataManager
    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Before
    fun setUp() {
        mMockDataManager = mock<IDataManager>()
        val compositeDisposable = CompositeDisposable()
        mWallpaperDetailPresenter = WallpaperDetailPresenter<WallpaperDetailContract.View>(mMockDataManager, compositeDisposable)
        mMockWallpaperDetailView = mock<WallpaperDetailContract.View>()
        mWallpaperDetailPresenter.attachView(mMockWallpaperDetailView)
    }

    @After
    fun tearDown() {
        mWallpaperDetailPresenter.detachView()
    }

    @Test
    fun testWallpaperFromUrlSuccess() {
        // prepare data
        val positionFragment = 5
        val urlImg = "http://i.imgur.com/iWiH6S6.jpg"
        whenever(mMockDataManager.setWallpaperFromUrl(urlImg)).thenReturn(Observable.just(Unit))

        // call
        mWallpaperDetailPresenter.setWallpaperFromUrl(urlImg, positionFragment)

        // assert
        verify(mMockWallpaperDetailView).readyWallpaper(positionFragment)
        verify(mMockWallpaperDetailView).showSnackBar(R.string.set_wallpaper_sucessfully)
    }
}