package com.twismart.wallpapershd.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.squareup.picasso.Picasso
import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.ui.wallpaper.WallpapersActivity
import com.twismart.wallpapershd.utils.CommonUtils
import com.twismart.wallpapershd.utils.debug
import java.util.ArrayList

/**
 * Created by sneyd on 4/13/2017.
 **/

class ListWallpapersRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<ListWallpapersRecyclerViewAdapter.MyItemNewViewHolder>() {

    private val TAG = javaClass.simpleName
    private var wallpaperList = ArrayList<Wallpaper>()

    fun setWallpaperList(wallpaperList: ArrayList<Wallpaper>) {
        this.wallpaperList = wallpaperList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyItemNewViewHolder {
        return MyItemNewViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_wallpaper, parent, false))
    }

    override fun onBindViewHolder(holder: MyItemNewViewHolder, position: Int) {
        holder.bindData(wallpaperList[position], position)
    }

    override fun getItemCount(): Int {
        return wallpaperList.size
    }

    inner class MyItemNewViewHolder(private val mView: View) : RecyclerView.ViewHolder(mView) {
        val imageWallpaper: ImageView

        init {
            imageWallpaper = mView.findViewById(R.id.imageWallpaper) as ImageView
        }

        fun bindData(itemWallpaper: Wallpaper, position: Int) {
            debug("bindData $position")

            Picasso.with(context)
                    .load(itemWallpaper.urlImage)
                    .resize(CommonUtils.getWitdhOfWallpaperInList(context), CommonUtils.getWitdhOfWallpaperInList(context))
                    .centerCrop()
                    .into(imageWallpaper)
            /*
            Glide.with(context)
                    .load(itemWallpaper.urlImage)
                    .asBitmap()
                    .override(CommonUtils.getWitdhOfWallpaperInList(context), CommonUtils.getWitdhOfWallpaperInList(context))
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(imageWallpaper)*/

            mView.setOnClickListener { context.startActivity(WallpapersActivity.newIntent(context, wallpaperList, position)) }
            mView.setOnLongClickListener { false }
        }
    }
}
