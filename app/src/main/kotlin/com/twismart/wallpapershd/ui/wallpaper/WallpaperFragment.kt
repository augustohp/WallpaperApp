package com.twismart.wallpapershd.ui.wallpaper

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.squareup.picasso.Picasso
import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.data.model.Wallpaper


class WallpaperFragment : Fragment() {

    private val TAG = javaClass.simpleName

    private var wallpaper: Wallpaper? = null

    private var mListener: OnFragmentInteractionListener? = null

    private var imageWallpaper: ImageView? = null

    // Dimensions used for the wallpaper
    private var widthEnd: Float = 0.toFloat()
    private var heightEnd: Float = 0.toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            wallpaper = arguments.getParcelable<Wallpaper>(ARG_WALLPAPER)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_wallpaper, container, false)

        imageWallpaper = v.findViewById(R.id.image_wallpaper) as ImageView
        // It's necessary use post method because of other way this would cause error due to imageWallpaper.getWidth() or getHeight() == 0
        imageWallpaper?.post {
            try {
                // Calculate the width and height in order to fit the image correctly
                val widthWallpaper = java.lang.Float.parseFloat(wallpaper!!.width)
                val heightWallpaper = java.lang.Float.parseFloat(wallpaper!!.height)

                if (widthWallpaper > heightWallpaper) {
                    widthEnd = imageWallpaper!!.width.toFloat()
                    heightEnd = heightWallpaper / widthWallpaper * widthEnd
                } else {
                    heightEnd = imageWallpaper!!.height.toFloat()
                    widthEnd = widthWallpaper / heightWallpaper * heightEnd
                }
                // Pick up whether the image comes from a url or a path
                Log.d(TAG, "onCreate: widthEnd $widthEnd heightEnd $heightEnd")
                val sourceImage = if (wallpaper!!.urlImage.isEmpty()) wallpaper!!.pathImage else wallpaper!!.urlImage
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

        return v
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
}// Required empty public constructor