package com.twismart.wallpapershd.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.data.model.Wallpaper
import com.twismart.wallpapershd.ui.wallpaper.activity.WallpapersActivity
import com.twismart.wallpapershd.utils.debug
import com.twismart.wallpapershd.utils.getScreenWidth
import java.util.ArrayList

/**
 * Created by sneyd on 4/13/2017.
 **/

class ListWallpapersRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<ListWallpapersRecyclerViewAdapter.MyItemNewViewHolder>() {

    private var wallpaperList: List<Wallpaper> = ArrayList()

    fun setWallpaperList(wallpaperList: List<Wallpaper>) {
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

        val imageWallpaper: ImageView by lazy { mView.findViewById(R.id.imageWallpaper) as ImageView }

        fun bindData(itemWallpaper: Wallpaper, position: Int) {
            debug("bindData $position")

            Picasso.with(context)
                    .load(itemWallpaper.urlImage)
                    .resize(context.getScreenWidth().div(3), context.getScreenWidth().div(3))
                    .centerCrop()
                    .into(imageWallpaper)

            mView.setOnClickListener { context.startActivity(WallpapersActivity.newIntent(context, wallpaperList, position)) }
            mView.setOnLongClickListener { false }
        }
    }
}