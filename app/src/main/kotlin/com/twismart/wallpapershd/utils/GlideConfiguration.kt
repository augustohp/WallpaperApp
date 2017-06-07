package com.twismart.wallpapershd.utils

import android.content.Context

import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.module.GlideModule

/**
 * Created by sneyd on 5/18/2017.
 **/

class GlideConfiguration : GlideModule {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        // Change bitmap format to ARGB_8888
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888)
    }

    override fun registerComponents(context: Context, glide: Glide) {
        // register ModelLoaders here.
    }
}
