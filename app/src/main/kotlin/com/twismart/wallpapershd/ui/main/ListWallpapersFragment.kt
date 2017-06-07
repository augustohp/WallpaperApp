package com.twismart.wallpapershd.ui.main

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v7.appcompat.BuildConfig
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.ui.base.BaseFragment
import com.twismart.wallpapershd.utils.Constants
import java.util.ArrayList

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

    val TAG = javaClass.simpleName
    var mListener: OnFragmentInteractionListener? = null
    var mWallpapersRecyclerViewAdapter: ListWallpapersRecyclerViewAdapter? = null
    var typeListWallpapers: String? = null

    @Inject lateinit var mPresenter: ListWallpapersPresenter<ListWallpapersContract.View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            typeListWallpapers = arguments.getString(ARG_TYPE_LIST)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.fragment_list_wallpapers, container, false)

        activityComponent?.inject(this)

        val recyclerViewWallpapers: RecyclerView? = v?.findViewById(R.id.recyclerViewWallpapers) as RecyclerView?
        recyclerViewWallpapers?.setHasFixedSize(true)
        val gridLayoutManager : RecyclerView.LayoutManager = GridLayoutManager(context, 3)
        recyclerViewWallpapers?.layoutManager = gridLayoutManager

        mWallpapersRecyclerViewAdapter = ListWallpapersRecyclerViewAdapter(context)
        recyclerViewWallpapers?.adapter = mWallpapersRecyclerViewAdapter

        if (typeListWallpapers == Constants.TypeListWallpapers.ALL.value) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "typelist all")
            } else {
                Log.d(TAG, "typelist all nodebug ")
            }
            mPresenter.attachView(this)
            mPresenter.getWallpapersList()
        }
        return v
    }

    override fun setWallpaperList(wallpaperList: ArrayList<Wallpaper>) {
        mWallpapersRecyclerViewAdapter?.setWallpaperList(wallpaperList)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context as OnFragmentInteractionListener?
        } else {
            //throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    override fun onDetach() {
        mListener = null
        mPresenter.dettachView()
        super.onDetach()
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
}