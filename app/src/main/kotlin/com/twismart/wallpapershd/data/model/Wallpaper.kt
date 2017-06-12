package com.twismart.wallpapershd.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by sneyd on 4/13/2017.
 **/

data class Wallpaper(
        @SerializedName("id") @Expose var id: String,
        @SerializedName("image_url") @Expose var urlImage: String,
        @SerializedName("image_path") @Expose var pathImage: String,
        @SerializedName("width") @Expose var width: String,
        @SerializedName("height") @Expose var height: String,
        @SerializedName("license") @Expose var license: String) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Wallpaper> = object : Parcelable.Creator<Wallpaper> {
            override fun createFromParcel(source: Parcel): Wallpaper = Wallpaper(source)
            override fun newArray(size: Int): Array<Wallpaper?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readString(),
    source.readString(),
    source.readString(),
    source.readString(),
    source.readString(),
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(urlImage)
        dest.writeString(pathImage)
        dest.writeString(width)
        dest.writeString(height)
        dest.writeString(license)
    }
}