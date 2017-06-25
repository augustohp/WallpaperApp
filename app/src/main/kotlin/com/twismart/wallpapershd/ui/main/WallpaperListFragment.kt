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

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.ui.base.BaseFragment
import com.twismart.wallpapershd.utils.*
import kotlinx.android.synthetic.main.fragment_list_wallpapers.*
import javax.inject.Inject

class WallpaperListFragment : BaseFragment(), WallpaperListContract.View {

    companion object {
        private val ARG_TYPE_LIST = "TypeListWallpapers"
        fun newInstance(typeListWallpapers: String): WallpaperListFragment {
            val fragment = WallpaperListFragment()
            fragment.withArguments(ARG_TYPE_LIST to typeListWallpapers)
            return fragment
        }
    }

    @Inject lateinit var mListPresenter: WallpaperListPresenter<WallpaperListContract.View>

    private var mWallpaperListAdapter: WallpaperListAdapter? = null
    private var mTypeListWallpapers: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        debug("F onCreate")

        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mTypeListWallpapers = arguments.getString(ARG_TYPE_LIST)
        }
    }

    override fun onAttach(context: Context?) {
        debug("F onAttach")
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        debug("onCreateView F")
        val v = activity.inflate(R.layout.fragment_list_wallpapers, container)
        activityComponent?.inject(this)
        return v
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        debug("onActivityCreated F")
        mListPresenter.attachView(this)

        recyclerViewWallpapers?.apply {
            setHasFixedSize(true)// improve performance
            layoutManager = GridLayoutManager(context, 3)// set grid layout manager with 3 columns
            mWallpaperListAdapter = WallpaperListAdapter(context)// create recyclerViewAdapter
            adapter = mWallpaperListAdapter// set recyclerViewAdapter to recyclerView
        }
        refreshContent()

        swipeRefresh?.setOnRefreshListener {
            refreshContent()
        }
        super.onActivityCreated(savedInstanceState)
    }

    fun refreshContent() {
        when (mTypeListWallpapers) {
            Constants.TypeListWallpapers.ALL.value -> {
                mListPresenter.loadWallpapersList()
            }
            Constants.TypeListWallpapers.MOST_POPULAR.value -> {
                mListPresenter.loadMostPopularWallpapers()
            }
            Constants.TypeListWallpapers.MY_FAVORITES.value -> {
                mListPresenter.loadFavoriteWallpapers()
            }
        }
    }

    override fun setWallpaperList(wallpaperList: ArrayList<Wallpaper>) {
        mWallpaperListAdapter?.setWallpaperList(wallpaperList)
        swipeRefresh?.isRefreshing = false
    }

    override fun onDestroyView() {
        mListPresenter.detachView()
        recyclerViewWallpapers?.gone()
        swipeRefresh?.gone()
        System.gc()
        super.onDestroyView()
    }
}