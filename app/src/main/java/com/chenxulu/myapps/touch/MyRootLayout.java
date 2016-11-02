package com.chenxulu.myapps.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by xulu on 16/8/11.
 */
public class MyRootLayout extends FrameLayout {

    public MyRootLayout(Context context) {
        super(context);
    }

    public MyRootLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRootLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //onInterceptTouchEvent = true;
        }
        System.out.println(getClass().getSimpleName() + ":onInterceptTouchEvent:" + onInterceptTouchEvent);
        return onInterceptTouchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean onTouchEvent = super.onTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //onTouchEvent = true;
        }
        System.out.println(getClass().getSimpleName() + ":onTouchEvent:" + onTouchEvent);
        return onTouchEvent;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println(MotionEventUtil.getMotionEvent(ev) + ":Start()");
        boolean dispatchTouchEvent = super.dispatchTouchEvent(ev);
        System.out.println(getClass().getSimpleName() + ":dispatchTouchEvent:" + dispatchTouchEvent);
        System.out.println(MotionEventUtil.getMotionEvent(ev) + ":Stop()");
        System.out.println("-");
        return dispatchTouchEvent;
    }

}
