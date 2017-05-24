package com.twismart.wallpapershd.ui.wallpaper;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;
import com.twismart.wallpapershd.R;
import com.twismart.wallpapershd.data.model.Wallpaper;

public class WallpaperFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();

    private static final String ARG_WALLPAPER = "wallpaper";

    private Wallpaper wallpaper = null;

    private OnFragmentInteractionListener mListener;

    private ImageView imageWallpaper;

    // Dimensions used for the wallpaper
    private float widthEnd, heightEnd;


    public WallpaperFragment() {
        // Required empty public constructor
    }

    public static WallpaperFragment newInstance(Wallpaper wallpaper) {
        WallpaperFragment fragment = new WallpaperFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_WALLPAPER, wallpaper);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            wallpaper = getArguments().getParcelable(ARG_WALLPAPER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wallpaper, container, false);

        imageWallpaper = (ImageView) v.findViewById(R.id.image_wallpaper);
        // It's necessary use post method because of other way this would cause error due to imageWallpaper.getWidth() or getHeight() == 0
        imageWallpaper.post(new Runnable() {
            @Override
            public void run() {
                try{
                    // Calculate the width and height in order to fit the image correctly
                    float widthWallpaper = Float.parseFloat(wallpaper.getWidth());
                    float heightWallpaper = Float.parseFloat(wallpaper.getHeight());

                    if(widthWallpaper > heightWallpaper){
                        widthEnd = imageWallpaper.getWidth();
                        heightEnd = (heightWallpaper / widthWallpaper) * widthEnd;
                    }
                    else {
                        heightEnd = imageWallpaper.getHeight();
                        widthEnd = (widthWallpaper / heightWallpaper) * heightEnd;
                    }
                    // Pick up whether the image comes from a url or a path
                    Log.d(TAG, "onCreate: widthEnd " + widthEnd + " heightEnd " + heightEnd);
                    String sourceImage = wallpaper.getUrlImage().isEmpty() ? wallpaper.getPathImage(): wallpaper.getUrlImage();
                    Glide.with(getContext())
                            .load(sourceImage)
                            .asBitmap()
                            .override((int)widthEnd, (int)heightEnd)
                            .diskCacheStrategy(DiskCacheStrategy.RESULT)
                            .into(imageWallpaper);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}