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

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.ui.base.BaseFragment
import com.twismart.wallpapershd.utils.Constants
import com.twismart.wallpapershd.utils.debug
import com.twismart.wallpapershd.utils.inflate
import kotlinx.android.synthetic.main.fragment_list_wallpapers.*
import javax.inject.Inject

class ListWallpapersFragment : BaseFragment(), ListWallpapersContract.View {

    companion object {
        private val ARG_TYPE_LIST = "TypeListWallpapers"
        fun newInstance(typeListWallpapers: String): ListWallpapersFragment {
            val fragment = ListWallpapersFragment()
            val args = Bundle()
            args.putString(ARG_TYPE_LIST, typeListWallpapers)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject lateinit var mPresenter: ListWallpapersPresenter<ListWallpapersContract.View>

    private var mWallpapersRecyclerViewAdapter: ListWallpapersRecyclerViewAdapter? = null
    private var mTypeListWallpapers: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mTypeListWallpapers = arguments.getString(ARG_TYPE_LIST)
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activityComponent?.inject(this)

        return context?.inflate(R.layout.fragment_list_wallpapers, container)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mPresenter.attachView(this)

        recyclerViewWallpapers?.apply {
            setHasFixedSize(true)// improve performance
            layoutManager = GridLayoutManager(context, 3)// set grid layout manager with 3 columns
            mWallpapersRecyclerViewAdapter = ListWallpapersRecyclerViewAdapter(context)// create recyclerViewAdapter
            adapter = mWallpapersRecyclerViewAdapter// set recyclerViewAdapter to recyclerView
        }
        refreshContent()

        swipeRefresh?.setOnRefreshListener {
            refreshContent()
        }
    }

    fun refreshContent() {
        debug("refreshContent")
        when (mTypeListWallpapers) {
            Constants.TypeListWallpapers.ALL.value -> {
                mPresenter.getWallpapersList()
            }
            Constants.TypeListWallpapers.MOST_POPULAR.value -> {
                mPresenter.getMostPopularWallpapers()
            }
            Constants.TypeListWallpapers.MY_FAVORITES.value -> {
                mPresenter.getFavoriteWallpapers()
            }
        }
    }

    override fun setWallpaperList(wallpaperList: ArrayList<Wallpaper>) {
        mWallpapersRecyclerViewAdapter?.setWallpaperList(wallpaperList)
        swipeRefresh?.isRefreshing = false
    }

    override fun onDetach() {
        mPresenter.detachView()
        super.onDetach()
    }
}