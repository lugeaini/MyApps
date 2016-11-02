package com.chenxulu.myapps.drag;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by xulu on 2016/10/12.
 */

public class MyScrollView extends ScrollView {
    DragGridView dragView;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (dragView.isDrag())
                onInterceptTouchEvent = false;
        }
        System.out.println(getClass().getSimpleName() + ":onInterceptTouchEvent:" + onInterceptTouchEvent);
        return onInterceptTouchEvent;
    }

    public void setDragView(DragGridView dragView) {
        this.dragView = dragView;
    }
}
