package com.chenxulu.myapps.recycle.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xulu on 2016/12/29.
 */

public abstract class MyRecycleAdapter extends RecyclerView.Adapter {
    protected OnItemClickListener onItemClickListener;
    protected OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position, View view);
    }
}
