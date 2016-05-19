package com.chenxulu.demovideo;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by xulu on 16/5/10.
 */
public class MyVideoController implements AbsListView.OnScrollListener, MyVideoLayout.MyVideoLayoutListener, View.OnTouchListener {
    private static final int SCREEN_DEFAULT = 0;
    private static final int SCREEN_SMALL = 1;
    private static final int SCREEN_FULL = 2;

    private static final float VIDEO_SCALE = 1.77f;

    private MyVideoLayout myVideoLayout;
    private View headLayout;
    private View hideView;
    private ListView listView;

    private int screenType;

    private VideoControllerListener mListener;

    private int displayWidth;
    private int displayHeight;
    private float lastX;
    private float lastY;

    /**
     * @param videoLayout
     * @param headerView  listView headerView
     * @param hideView
     */
    public MyVideoController(MyVideoLayout videoLayout, ListView listView, View headerView, View hideView) {
        this.myVideoLayout = videoLayout;
        this.listView = listView;
        this.headLayout = headerView;
        this.hideView = hideView;
        this.initView();
    }

    private void initView() {
        displayWidth = myVideoLayout.getResources().getDisplayMetrics().widthPixels;
        displayHeight = myVideoLayout.getResources().getDisplayMetrics().heightPixels;

        setHideView();
        myVideoLayout.setVisibility(View.GONE);
        myVideoLayout.setOnTouchListener(this);
        myVideoLayout.setMyVideoLayoutListener(this);
    }

    /**
     * set video path
     *
     * @param videoPath
     */
    public void setVideoPath(String videoPath) {
        myVideoLayout.setVideoPath(videoPath);
    }

    /**
     * set listener
     *
     * @param listener
     */
    public void setMyVideoLayoutListener(VideoControllerListener listener) {
        this.mListener = listener;
    }

    /**
     * is full screen
     *
     * @return
     */
    public boolean isFullScreen() {
        return screenType == SCREEN_FULL;
    }


    /**
     * start play video
     */
    public void startPlay() {
        myVideoLayout.setVisibility(View.VISIBLE);
        setDefaultScreen();
        myVideoLayout.startPlay();
    }

    /**
     * video pause
     */
    public void pause() {
        myVideoLayout.pause();
    }

    /**
     * video stop
     */
    public void stop() {
        myVideoLayout.stop();
        myVideoLayout.setVisibility(View.GONE);
        screenType = SCREEN_DEFAULT;
    }

    /**
     * 设置视频占位视图
     */
    private void setHideView() {
        ViewGroup.LayoutParams layoutParams = hideView.getLayoutParams();
        layoutParams.width = displayWidth;
        layoutParams.height = (int) (layoutParams.width / VIDEO_SCALE);
        hideView.setLayoutParams(layoutParams);
    }

    /**
     * set video view to full screen
     */
    public void setFullScreen() {
        screenType = SCREEN_FULL;
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) myVideoLayout.getLayoutParams();
        layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = RelativeLayout.LayoutParams.MATCH_PARENT;
        layoutParams.setMargins(0, 0, 0, 0);
        myVideoLayout.setLayoutParams(layoutParams);
    }

    /**
     * set video view to default screen
     */
    public void setDefaultScreen() {
        screenType = SCREEN_DEFAULT;
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) myVideoLayout.getLayoutParams();
        layoutParams.width = displayWidth;
        layoutParams.height = (int) (layoutParams.width / VIDEO_SCALE);
        layoutParams.topMargin = headLayout.getTop() + hideView.getTop() + listView.getTop();
        myVideoLayout.setScreenSmall(false);
        myVideoLayout.setLayoutParams(layoutParams);
    }

    /**
     * set video view to small screen
     */
    public void setSmallScreen() {
        screenType = SCREEN_SMALL;
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) myVideoLayout.getLayoutParams();
        layoutParams.width = displayWidth / 2;
        layoutParams.height = (int) (layoutParams.width / VIDEO_SCALE);
        layoutParams.topMargin = listView.getTop();
        myVideoLayout.setScreenSmall(true);
        myVideoLayout.setLayoutParams(layoutParams);
    }

    /**
     * if video view to small screen
     */
    public boolean isSmallScreen() {
        return listView.getFirstVisiblePosition() > 0 || ((headLayout.getTop() + hideView.getBottom()) < 0);
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //System.out.println(headLayout.getTop() + "-" + hideView.getBottom() + "-" + hideView.getTop() + "-" + listView.getTop());
        if (screenType != SCREEN_FULL || myVideoLayout.getVisibility() == View.VISIBLE) {
            if (isSmallScreen()) {
                if (screenType != SCREEN_SMALL) {
                    setSmallScreen();
                }
            } else {
                setDefaultScreen();
            }
        }
    }


    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (screenType != SCREEN_SMALL) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = event.getRawX() - lastX;
                float dy = event.getRawY() - lastY;
                lastX = event.getRawX();
                lastY = event.getRawY();

                int left = (int) (v.getLeft() + dx);
                int top = (int) (v.getTop() + dy);
                int right = (int) (v.getRight() + dx);
                int bottom = (int) (v.getBottom() + dy);
                System.out.println(">>>>>>" + left + "-" + right + "-" + top + "-" + bottom);

                if (left > 0 && right < displayWidth && top > listView.getTop() && bottom < displayHeight) {
                    v.layout(left, top, right, bottom);
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void fullScreenChange() {
        if (screenType == SCREEN_FULL) {
            if (isSmallScreen()) {
                setSmallScreen();
            } else {
                setDefaultScreen();
            }
            if (mListener != null)
                mListener.fullScreen(false);
        } else {
            setFullScreen();
            if (mListener != null)
                mListener.fullScreen(true);
        }
    }

    @Override
    public void closeOnClick() {
        if (screenType == SCREEN_FULL) {
            //
        }
        screenType = SCREEN_DEFAULT;
        myVideoLayout.stop();
        myVideoLayout.setVisibility(View.GONE);
        if (mListener != null)
            mListener.fullScreen(false);
    }

    @Override
    public void playOnCompletion() {
        closeOnClick();
    }

    @Override
    public void playOnError() {
        closeOnClick();
        if (mListener != null) {
            mListener.playOnError();
        }
    }

    /**
     * if screen rotate,refresh view location
     */
    public void onConfigurationChanged() {
        displayWidth = myVideoLayout.getResources().getDisplayMetrics().widthPixels;
        displayHeight = myVideoLayout.getResources().getDisplayMetrics().heightPixels;
        setHideView();
        if (screenType != SCREEN_FULL) {
            if (isSmallScreen()) {
                setSmallScreen();
            } else {
                setDefaultScreen();
            }
        }
    }

    public interface VideoControllerListener {
        void fullScreen(boolean fullScreen);

        void playOnError();
    }
}
