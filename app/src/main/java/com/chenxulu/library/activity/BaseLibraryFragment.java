package com.chenxulu.library.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseLibraryFragment extends Fragment {
    protected View mView;

    protected void log(String message) {
        Log.d(getClass().getSimpleName(), message);
    }

    /**
     * called to do initial creation of the fragment.
     *
     * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("onCreate()");
    }

    /**
     * creates and returns the view hierarchy associated with the fragment.
     *
     * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
     * android.view.ViewGroup, android.os.Bundle)
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        log("onCreateView()");
        if (mView == null) {
            initView(inflater, container, savedInstanceState);
        } else {
            ViewGroup mGroup = (ViewGroup) mView.getParent();
            if (mGroup != null) {
                mGroup.removeView(mView);
            }
        }
        return mView;
    }

    /**
     * 初始化布局，不使用onCreateView初始化
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    protected abstract void initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * tells the fragment that its activity has completed its own
     * Activity.onCreaate.
     *
     * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        log("onActivityCreated()");
    }

    /**
     * makes the fragment visible to the user (based on its containing activity
     * being started).
     *
     * @see android.support.v4.app.Fragment#onStart()
     */
    @Override
    public void onStart() {
        super.onStart();
        log("onStart()");
    }

    /**
     * makes the fragment interacting with the user (based on its containing
     * activity being resumed). As a fragment is no longer being used, it goes
     * through a reverse series of callbacks:
     *
     * @see android.support.v4.app.Fragment#onResume()
     */
    @Override
    public void onResume() {
        super.onResume();
        log(String.format("onResume(NativeHeapAllocatedSize:%d, NativeHeapFreeSize:%d)",
                android.os.Debug.getNativeHeapAllocatedSize(),
                android.os.Debug.getNativeHeapFreeSize()));
    }

    /**
     * fragment is no longer interacting with the user either because its
     * activity is being paused or a fragment operation is modifying it in the
     * activity.
     *
     * @see android.support.v4.app.Fragment#onPause()
     */
    @Override
    public void onPause() {
        super.onPause();
        log("onPause()");
    }

    /**
     * fragment is no longer visible to the user either because its activity is
     * being stopped or a fragment operation is modifying it in the activity.
     *
     * @see android.support.v4.app.Fragment#onStop()
     */
    @Override
    public void onStop() {
        super.onStop();
        log("onStop()");
    }

    /**
     * allows the fragment to clean up resources associated with its View.
     *
     * @see android.support.v4.app.Fragment#onDestroyView()
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        log("onDestroyView()");
    }

    /**
     * called immediately prior to the fragment no longer being associated with
     * its activity.
     *
     * @see android.support.v4.app.Fragment#onDetach()
     */
    @Override
    public void onDetach() {
        super.onDetach();
        log("onDetach()");
    }

    /**
     * called to do final cleanup of the fragment's state.
     *
     * @see android.support.v4.app.Fragment#onDestroy()
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        log("onDestroy()");
    }

    /**
     * @see android.support.v4.app.Fragment#onHiddenChanged(boolean)
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        log(String.format("onHiddenChanged(%s)", hidden));
    }


    /**
     * @see android.support.v4.app.Fragment#onSaveInstanceState(Bundle)
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        log("onSaveInstanceState()");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        log(String.format("onActivityResult(requestCode=%d, resultCode=%d)", requestCode, resultCode));
    }

}
