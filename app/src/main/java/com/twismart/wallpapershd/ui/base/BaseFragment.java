package com.twismart.wallpapershd.ui.base;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.twismart.wallpapershd.di.component.ActivityComponent;

/**
 * Created by sneyd on 5/5/2017.
 **/

public abstract class BaseFragment extends Fragment implements BaseView {

    private BaseActivity mBaseActivity;

    public ActivityComponent getActivityComponent() {
        return mBaseActivity.getActivityComponent();
    }

    public BaseActivity getBaseActivity() {
        return mBaseActivity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BaseActivity){
            mBaseActivity = (BaseActivity) context;
        }
    }

    @Override
    public void showLoading() {
        if(mBaseActivity != null){
            mBaseActivity.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if(mBaseActivity != null){
            mBaseActivity.hideLoading();
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        if(mBaseActivity != null){
            mBaseActivity.onError(resId);
        }
    }

    @Override
    public void onError(String message) {
        if(mBaseActivity != null){
            mBaseActivity.onError(message);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if(mBaseActivity != null){
            return mBaseActivity.isNetworkConnected();
        }
        return false;
    }

    @Override
    public void hideKeyboard() {
        if(mBaseActivity != null){
            mBaseActivity.hideKeyboard();
        }
    }
}
