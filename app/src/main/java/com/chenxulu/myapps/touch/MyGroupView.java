package com.chenxulu.myapps.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by xulu on 16/8/5.
 */
public class MyGroupView extends FrameLayout {

    public MyGroupView(Context context) {
        super(context);
    }

    public MyGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            //onInterceptTouchEvent = true;
        }
        System.out.println(getClass().getSimpleName() + ":onInterceptTouchEvent:" + onInterceptTouchEvent);
        return onInterceptTouchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean onTouchEvent = super.onTouchEvent(ev);
        System.out.println(getClass().getSimpleName() + ":onTouchEvent:" + onTouchEvent);
        return onTouchEvent;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean dispatchTouchEvent = super.dispatchTouchEvent(ev);
        System.out.println(getClass().getSimpleName() + ":dispatchTouchEvent:" + dispatchTouchEvent);
        return dispatchTouchEvent;
    }

}
