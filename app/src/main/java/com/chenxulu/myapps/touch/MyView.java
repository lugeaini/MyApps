package com.chenxulu.myapps.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xulu on 16/8/5.
 */
public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean onTouchEvent = super.onTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //onTouchEvent = true;
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            //onTouchEvent = false;
        }
        System.out.println(getClass().getSimpleName() + ":onTouchEvent:" + MotionEventUtil.getMotionEvent(ev) + ":" + onTouchEvent);
        return onTouchEvent;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean dispatchTouchEvent = super.dispatchTouchEvent(ev);
        System.out.println(getClass().getSimpleName() + ":dispatchTouchEvent:" + dispatchTouchEvent);
        return dispatchTouchEvent;
    }
}
