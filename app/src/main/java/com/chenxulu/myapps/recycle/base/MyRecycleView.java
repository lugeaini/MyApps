package com.chenxulu.myapps.recycle.base;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xulu on 2016/12/29.
 */

public class MyRecycleView extends RecyclerView {

    public MyRecycleView(Context context) {
        super(context);
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Maps a point to a position in the list.
     *
     * @param x X in local coordinate
     * @param y Y in local coordinate
     * @return The position of the item which contains the specified point, or
     * {@link #NO_POSITION} if the point does not intersect an item.
     */
    public int pointToPosition(int x, int y) {
        Rect frame = new Rect();

        final int count = getChildCount();
        for (int i = count - 1; i >= 0; i--) {
            final View child = getChildAt(i);
            if (child.getVisibility() == View.VISIBLE) {
                child.getHitRect(frame);
                if (frame.contains(x, y)) {
                    return getFirstVisiblePosition() + i;
                }
            }
        }
        return NO_POSITION;
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        String logStr = "onScrollStateChanged: ";
        if (state == SCROLL_STATE_IDLE) {
            logStr += "SCROLL_STATE_IDLE";
        } else if (state == SCROLL_STATE_DRAGGING)
            logStr += "SCROLL_STATE_DRAGGING";
        else if (state == SCROLL_STATE_SETTLING)
            logStr += "SCROLL_STATE_SETTLING";
        System.out.println(logStr);

        int firstVisiblePosition = getFirstVisiblePosition();
        int lastVisiblePosition = getLastVisiblePosition();
        int itemCount = getItemCount();

        System.out.println("firstVisiblePosition:" + firstVisiblePosition);
        System.out.println("lastVisiblePosition:" + lastVisiblePosition);
        System.out.println("itemCount:" + itemCount);

        if (lastVisiblePosition - firstVisiblePosition + 1 == itemCount) {

        }
        if (lastVisiblePosition + 1 == itemCount) {

        }
    }

    public int getFirstVisiblePosition() {
        LayoutManager mLayoutManager = getLayoutManager();
        if (mLayoutManager == null)
            return NO_POSITION;

        if (mLayoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) mLayoutManager;
            int[] into = new int[layoutManager.getSpanCount()];
            layoutManager.findFirstVisibleItemPositions(into);
            return findMin(into);
        } else {
            LinearLayoutManager layoutManager = (LinearLayoutManager) mLayoutManager;
            return layoutManager.findFirstVisibleItemPosition();
        }
    }

    private int findMin(int[] firstPositions) {
        int min = firstPositions[0];
        for (int value : firstPositions) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    public int getLastVisiblePosition() {
        LayoutManager mLayoutManager = getLayoutManager();
        if (mLayoutManager == null)
            return NO_POSITION;

        if (mLayoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) mLayoutManager;
            int[] into = new int[layoutManager.getSpanCount()];
            layoutManager.findLastVisibleItemPositions(into);
            return findMax(into);
        } else {
            LinearLayoutManager layoutManager = (LinearLayoutManager) mLayoutManager;
            return layoutManager.findLastVisibleItemPosition();
        }
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private int getItemCount() {
        LayoutManager mLayoutManager = getLayoutManager();
        if (mLayoutManager == null)
            return NO_POSITION;
        return mLayoutManager.getItemCount();
    }

    public int getGridViewSpanCount() {
        GridLayoutManager layoutManager = (GridLayoutManager) getLayoutManager();
        if (layoutManager != null)
            return layoutManager.getSpanCount();
        return 0;
    }

}
