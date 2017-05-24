package com.twismart.wallpapershd.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.twismart.wallpapershd.R;
import com.twismart.wallpapershd.data.model.Wallpaper;
import com.twismart.wallpapershd.ui.wallpaper.WallpapersActivity;
import com.twismart.wallpapershd.utils.CommonUtils;

import java.util.ArrayList;

/**
 * Created by sneyd on 4/13/2017.
 **/

public class ListWallpapersRecyclerViewAdapter extends RecyclerView.Adapter<ListWallpapersRecyclerViewAdapter.MyItemNewViewHolder> {

    private final String TAG = getClass().getSimpleName();
    private ArrayList<Wallpaper> wallpaperList = new ArrayList<>();
    private Context context;

    public ListWallpapersRecyclerViewAdapter(Context context){
        this.context = context;
    }

    public void setWallpaperList(ArrayList<Wallpaper> wallpaperList) {
        this.wallpaperList = wallpaperList;
        notifyDataSetChanged();
    }

    @Override
    public MyItemNewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemNewViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallpaper, parent, false));
    }

    @Override
    public void onBindViewHolder(MyItemNewViewHolder holder, int position) {
        holder.bindData(wallpaperList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return wallpaperList.size();
    }

    class MyItemNewViewHolder extends RecyclerView.ViewHolder {

        private final View mView;
        private final ImageView imageWallpaper;

        MyItemNewViewHolder(View view){
            super(view);
            mView = view;
            imageWallpaper = (ImageView) view.findViewById(R.id.image_wallpaper);
        }

        private void bindData(final Wallpaper itemWallpaper, final int position){
            Glide.with(context)
                    .load(itemWallpaper.getUrlImage())
                    .asBitmap()
                    .override(CommonUtils.getWitdhOfWallpaperInList(context), CommonUtils.getWitdhOfWallpaperInList(context))
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(imageWallpaper);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(WallpapersActivity.newIntent(context, wallpaperList, position));
                }
            });
            mView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });
        }
    }
}
