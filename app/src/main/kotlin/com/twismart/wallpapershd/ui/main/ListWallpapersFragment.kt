package com.twismart.wallpapershd.ui.main

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.ui.base.BaseFragment
import com.twismart.wallpapershd.utils.Constants
import com.twismart.wallpapershd.utils.inflate
import kotlinx.android.synthetic.main.fragment_list_wallpapers.*
import javax.inject.Inject

class ListWallpapersFragment : BaseFragment(), ListWallpapersContract.View {

    companion object {
        private val ARG_TYPE_LIST = "typeListWallpapers"
        fun newInstance(typeListWallpapers: String): ListWallpapersFragment {
            val fragment = ListWallpapersFragment()
            val args = Bundle()
            args.putString(ARG_TYPE_LIST, typeListWallpapers)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject lateinit var mPresenter: ListWallpapersPresenter<ListWallpapersContract.View>

    var mWallpapersRecyclerViewAdapter: ListWallpapersRecyclerViewAdapter? = null
    var typeListWallpapers: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            typeListWallpapers = arguments.getString(ARG_TYPE_LIST)
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activityComponent.inject(this)

        return context?.inflate(R.layout.fragment_list_wallpapers, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerViewWallpapers.setHasFixedSize(true)
        recyclerViewWallpapers.layoutManager = GridLayoutManager(context, 3)

        mWallpapersRecyclerViewAdapter = ListWallpapersRecyclerViewAdapter(context)
        recyclerViewWallpapers.adapter = mWallpapersRecyclerViewAdapter

        mPresenter.attachView(this)
        when (typeListWallpapers) {
            Constants.TypeListWallpapers.ALL.value -> {
                mPresenter.getWallpapersList()
            }
            Constants.TypeListWallpapers.MY_FAVORITES.value -> {
                mPresenter.getAllFavoriteWallpapers()
            }
        }
    }

    override fun setWallpaperList(wallpaperList: List<Wallpaper>) {
        mWallpapersRecyclerViewAdapter?.setWallpaperList(wallpaperList)
    }

    override fun onDetach() {
        mPresenter.dettachView()
        super.onDetach()
    }
}