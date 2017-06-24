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

import android.annotation.TargetApi
import android.app.ActivityOptions
import android.content.Context
import android.graphics.drawable.BitmapDrawable
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
import com.twismart.wallpapershd.utils.isJellyBeanOrLater
import com.twismart.wallpapershd.utils.screenWidth
import java.util.ArrayList

class ListWallpapersRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<ListWallpapersRecyclerViewAdapter.WallpaperViewHolder>() {

    private var wallpaperList: ArrayList<Wallpaper> = ArrayList()

    fun setWallpaperList(wallpaperList: ArrayList<Wallpaper>) {
        this.wallpaperList = wallpaperList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        return WallpaperViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_wallpaper, parent, false))
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        holder.bindData(wallpaperList[position], position)
    }

    override fun getItemCount(): Int {
        return wallpaperList.size
    }

    inner class WallpaperViewHolder(private val mView: View) : RecyclerView.ViewHolder(mView) {

        val imageWallpaper: ImageView by lazy { mView.findViewById(R.id.imageWallpaper) as ImageView }

        @TargetApi(16)
        fun bindData(itemWallpaper: Wallpaper, position: Int) {
            debug("bindData $position")

            Picasso.with(context)
                    .load(itemWallpaper.urlImage)
                    .resize(context.screenWidth().div(3), context.screenWidth().div(3))
                    .centerCrop()
                    .into(imageWallpaper)

            mView.setOnClickListener {
                //start activity that shows the selected wallpaper in detail through a list of fragments in a ViewPager
                if (isJellyBeanOrLater()) {// if the below code is supported
                    try {
                        // try to start the activity with a cute animation
                        context.startActivity(WallpapersActivity.newIntent(context, wallpaperList, position),
                                ActivityOptions.makeThumbnailScaleUpAnimation(mView, (imageWallpaper.drawable as BitmapDrawable).bitmap, 0, 0).toBundle())
                    } catch (e: Exception) {
                        // if something get wrong start the activity in a normal way
                        context.startActivity(WallpapersActivity.newIntent(context, wallpaperList, position))
                    }
                } else {
                    // other way just start the Activity in a normal way
                    context.startActivity(WallpapersActivity.newIntent(context, wallpaperList, position))
                }
            }
        }
    }
}