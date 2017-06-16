package com.twismart.wallpapershd.ui.wallpaper.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.ui.base.BaseFragment
import com.twismart.wallpapershd.utils.debug
import com.twismart.wallpapershd.utils.inflate
import kotlinx.android.synthetic.main.fragment_wallpaper.*


class WallpaperFragment : BaseFragment(){

    companion object {
        private val ARG_WALLPAPER = "wallpaper"
        private val ARG_POSITION_FRAGMENT = "positionFragment"
        fun newInstance(wallpaper: Wallpaper, positionFragment: Int): WallpaperFragment {
            debug("WallpaperFragment newInstance id ${wallpaper.id}")
            val fragment = WallpaperFragment()
            val args = Bundle()
            args.putParcelable(ARG_WALLPAPER, wallpaper)
            args.putInt(ARG_POSITION_FRAGMENT, positionFragment)
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var mWallpaper: Wallpaper
    var positionFragment: Int = 0
    var mListener: OnWallpaperFragmentListener? = null

    // Dimensions used for the wallpaper
    private var widthEnd = 0f
    private var heightEnd = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mWallpaper = arguments.getParcelable(ARG_WALLPAPER)
        positionFragment = arguments.getInt(ARG_POSITION_FRAGMENT)
        debug("WallpaperFragment onCreate id ${mWallpaper.id}")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activityComponent.inject(this)
        debug("WallpaperFragment onCreateView id ${mWallpaper.id}")
        return context?.inflate(R.layout.fragment_wallpaper, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        debug("WallpaperFragment onActivityCreated id ${mWallpaper.id}")
        // It's necessary use post method because of other way this would cause error due to imageWallpaper.getWidth() or getHeight() == 0
        imageWallpaper.post {
            try {
                // Calculate the width and height in order to fit the image correctly
                val widthWallpaper = mWallpaper.width.toFloat()
                val heightWallpaper = mWallpaper.height.toFloat()

                if (widthWallpaper > heightWallpaper) {
                    widthEnd = imageWallpaper.width.toFloat()
                    heightEnd = heightWallpaper / widthWallpaper * widthEnd
                } else {
                    heightEnd = imageWallpaper.height.toFloat()
                    widthEnd = widthWallpaper / heightWallpaper * heightEnd
                }
                // Pick up whether the image comes from a url or a path
                debug("onActivityCreated: widthEnd $widthEnd heightEnd $heightEnd")
                val sourceImage = if (mWallpaper.urlImage.isEmpty()) mWallpaper.pathImage else mWallpaper.urlImage
                Glide.with(context)
                        .load(sourceImage)
                        .asBitmap()
                        .override(widthEnd.toInt(), heightEnd.toInt())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(imageWallpaper)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        imageSwitchFavorite.setOnClickListener {
            debug("clickFavorite id ${mWallpaper.id}")
            if (mWallpaper.isFavorite){
                mListener?.onClickUnFavorite(mWallpaper.id, positionFragment)
            }
            else{
                mListener?.onClickFavorite(mWallpaper, positionFragment)
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is OnWallpaperFragmentListener){
            mListener = context
        }
    }

    fun wallpaperIsInFavorites() {
        debug("wallpaperIsInFavorites ${mWallpaper.id}")
        imageSwitchFavorite.setColorFilter(Color.RED)
        mWallpaper.isFavorite = true
    }

    fun wallpaperIsNotInFavorites() {
        debug("wallpaperIsNotInFavorites ${mWallpaper.id}")
        imageSwitchFavorite.setColorFilter(Color.WHITE)
        mWallpaper.isFavorite = false
    }

    override fun onDestroy() {
        super.onDestroy()
        debug("fragment onDestroy")
    }

    interface OnWallpaperFragmentListener{
        fun onClickFavorite(wallpaperToFavorites: Wallpaper, positionFragment: Int)
        fun onClickUnFavorite(id: String, positionFragment: Int)
    }
}