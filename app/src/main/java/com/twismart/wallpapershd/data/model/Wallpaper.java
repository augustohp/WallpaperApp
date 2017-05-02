package com.twismart.wallpapershd.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sneyd on 4/13/2017.
 **/

public class Wallpaper implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("image_url")
    @Expose
    private String urlImage;

    @SerializedName("image_path")
    @Expose
    private String pathImage;

    @SerializedName("width")
    @Expose
    private String width;

    @SerializedName("height")
    @Expose
    private String height;

    @SerializedName("license")
    @Expose
    private String license;

    public Wallpaper() {

    }

    public Wallpaper(String id, String urlImage, String pathImage, String width, String height, String license) {
        this.id = id;
        this.urlImage = urlImage;
        this.pathImage = pathImage;
        this.width = width;
        this.height = height;
        this.license = license;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public String toString() {
        return "Wallpaper{" +
                "id='" + id + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", pathImage='" + pathImage + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", license='" + license + '\'' +
                '}';
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    protected Wallpaper(Parcel in) {
        id = in.readString();
        urlImage = in.readString();
        pathImage = in.readString();
        width = in.readString();
        height = in.readString();
        license = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(urlImage);
        dest.writeString(pathImage);
        dest.writeString(width);
        dest.writeString(height);
        dest.writeString(license);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Wallpaper> CREATOR = new Parcelable.Creator<Wallpaper>() {
        @Override
        public Wallpaper createFromParcel(Parcel in) {
            return new Wallpaper(in);
        }

        @Override
        public Wallpaper[] newArray(int size) {
            return new Wallpaper[size];
        }
    };
}