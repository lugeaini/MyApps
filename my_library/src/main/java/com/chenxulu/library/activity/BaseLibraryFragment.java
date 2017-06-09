package com.chenxulu.library.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseLibraryFragment extends Fragment {
    private boolean lifecycle;
    protected View mView;

    /**
     * called to do initial creation of the fragment.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("onCreate()");
    }

    /**
     * creates and returns the view hierarchy associated with the fragment.
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
     * Activity.onCreate().
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        log("onActivityCreated()");
    }

    /**
     * makes the fragment visible to the user (based on its containing activity
     * being started).
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
     */
    @Override
    public void onPause() {
        super.onPause();
        log("onPause()");
    }

    /**
     * fragment is no longer visible to the user either because its activity is
     * being stopped or a fragment operation is modifying it in the activity.
     */
    @Override
    public void onStop() {
        super.onStop();
        log("onStop()");
    }

    /**
     * allows the fragment to clean up resources associated with its View.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        log("onDestroyView()");
    }

    /**
     * called immediately prior to the fragment no longer being associated with
     * its activity.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        log("onDetach()");
    }

    /**
     * called to do final cleanup of the fragment's state.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        log("onDestroy()");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        log(String.format("onHiddenChanged(%s)", hidden));
    }


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

    /**
     * 是否打印生命周期
     *
     * @param state
     */
    protected void logLifeCycle(boolean state) {
        this.lifecycle = state;
    }

    /**
     * 本类中log输出函数，tag为类名
     */
    protected void log(String message) {
        if (lifecycle)
            Log.d(getClass().getSimpleName(), message);
    }

}
