package com.twismart.wallpapershd.ui.wallpaper

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.utils.debug
import com.twismart.wallpapershd.utils.inflate
import kotlinx.android.synthetic.main.fragment_wallpaper.*


class WallpaperFragment : Fragment() {

    companion object {
        private val ARG_WALLPAPER = "wallpaper"
        fun newInstance(wallpaper: Wallpaper): WallpaperFragment {
            val fragment = WallpaperFragment()
            val args = Bundle()
            args.putParcelable(ARG_WALLPAPER, wallpaper)
            fragment.arguments = args
            return fragment
        }
    }

    lateinit private var wallpaper: Wallpaper

    private var mListener: OnFragmentInteractionListener? = null

    // Dimensions used for the wallpaper
    private var widthEnd = 0f
    private var heightEnd = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wallpaper = arguments.getParcelable(ARG_WALLPAPER)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return context?.inflate(R.layout.fragment_wallpaper, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // It's necessary use post method because of other way this would cause error due to imageWallpaper.getWidth() or getHeight() == 0
        imageWallpaper.post {
            try {
                // Calculate the width and height in order to fit the image correctly
                val widthWallpaper = wallpaper.width.toFloat()
                val heightWallpaper = wallpaper.height.toFloat()

                if (widthWallpaper > heightWallpaper) {
                    widthEnd = imageWallpaper.width.toFloat()
                    heightEnd = heightWallpaper / widthWallpaper * widthEnd
                } else {
                    heightEnd = imageWallpaper.height.toFloat()
                    widthEnd = widthWallpaper / heightWallpaper * heightEnd
                }
                // Pick up whether the image comes from a url or a path
                debug("onCreate: widthEnd $widthEnd heightEnd $heightEnd")
                val sourceImage = if (wallpaper.urlImage.isEmpty()) wallpaper.pathImage else wallpaper.urlImage
                Glide.with(context)
                        .load(sourceImage)
                        .asBitmap()
                        .override(widthEnd.toInt(), heightEnd.toInt())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(imageWallpaper!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
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
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
}