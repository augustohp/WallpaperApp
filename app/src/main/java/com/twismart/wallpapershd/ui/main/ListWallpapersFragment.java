package com.twismart.wallpapershd.ui.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.appcompat.BuildConfig;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.twismart.wallpapershd.R;
import com.twismart.wallpapershd.data.model.Wallpaper;
import com.twismart.wallpapershd.ui.base.BaseFragment;
import com.twismart.wallpapershd.utils.Constants;

import java.util.ArrayList;

import javax.inject.Inject;


public class ListWallpapersFragment extends BaseFragment implements ListWallpapersContract.View {

    private final String TAG = getClass().getSimpleName();
    private static final String ARG_TYPE_LIST = "typeListWallpapers";
    private OnFragmentInteractionListener mListener;
    private ListWallpapersRecyclerViewAdapter mWallpapersRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private String typeListWallpapers;

    @Inject ListWallpapersPresenter<ListWallpapersContract.View> mPresenter;

    public ListWallpapersFragment() {
        // Required empty public constructor
    }

    public static ListWallpapersFragment newInstance(String typeListWallpapers) {
        ListWallpapersFragment fragment = new ListWallpapersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE_LIST, typeListWallpapers);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            typeListWallpapers = getArguments().getString(ARG_TYPE_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_wallpapers, container, false);

        getActivityComponent().inject(this);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_wallpapers);
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mWallpapersRecyclerViewAdapter = new ListWallpapersRecyclerViewAdapter(getContext());
        mRecyclerView.setAdapter(mWallpapersRecyclerViewAdapter);


        Log.d(TAG, "typelist");

        if(typeListWallpapers.equals(Constants.TypeListWallpapers.ALL.value)){
            if(BuildConfig.DEBUG){
                Log.d(TAG, "typelist all");
            }
            else{
                Log.d(TAG, "typelist all nodebug");
            }
            mPresenter.attachView(this);
            mPresenter.getWallpapersList();
            /*
            WallpaperService wallpaperService = WallpaperService.Factory.create();

            Observable<ArrayList<Wallpaper>> wallpapersList = wallpaperService.getWallpapersList("index.php");
            wallpapersList.enqueue(new Callback<ArrayList<Wallpaper>>() {
                @Override
                public void onResponse(Call<ArrayList<Wallpaper>> call, Response<ArrayList<Wallpaper>> response) {
                    Log.d(TAG, "onResponse: code " + response.code());
                    Log.d(TAG, "onResponse: body size " + (response.body().size()));
                    Log.d(TAG, "onResponse: toString " + response.body().toString());
                    mWallpapersRecyclerViewAdapter.setWallpaperList(response.body());
                }
                @Override
                public void onFailure(Call<ArrayList<Wallpaper>> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                }
            });
            */
        }


        return v;
    }

    @Override
    public void setWallpaperList(ArrayList<Wallpaper> wallpaperList) {
        mWallpapersRecyclerViewAdapter.setWallpaperList(wallpaperList);
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
        mListener = null;
        mPresenter.dettachView();
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}