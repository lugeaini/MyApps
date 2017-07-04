package com.chenxulu.myapps.expand;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by xulu on 2017/7/4.
 */

public abstract class BaseExpandAdapter extends RecyclerView.Adapter {
    private static final int GROUP_TYPE = 1;
    private static final int CHILD_TYPE = 1;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == GROUP_TYPE) {
            return onCreateGroupViewHolder(parent, viewType);
        } else {
            return onCreateChildViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int[] positions = getGroupChildPositions(position);
        if (holder.getItemViewType() == GROUP_TYPE) {
            onBindGroupViewHolder(holder, positions[0]);
        } else {
            onBindChildViewHolder(holder, positions[0], positions[1]);
        }
    }

    @Override
    public int getItemCount() {
        int size = 0;
        for (int i = 0; i < getGroupCount(); i++) {
            size++;
            size += getChildCount(i);
        }
        return size;
    }

    abstract int getGroupCount();

    abstract int getChildCount(int position);

    abstract RecyclerView.ViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType);

    abstract RecyclerView.ViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType);

    abstract void onBindGroupViewHolder(RecyclerView.ViewHolder holder, int groupPosition);

    abstract void onBindChildViewHolder(RecyclerView.ViewHolder holder, int groupPosition, int childPosition);

    int[] getGroupChildPositions(int position) {
        int groupPosition = 0;
        int childPosition = 0;

        int size = 0;
        for (int i = 0; i < getGroupCount(); i++) {
            if ((position - size) < (getChildCount(i) + 1)) {
                groupPosition = i;
                childPosition = position - size - 1;
            }
            size += getChildCount(i) + 1;
        }
        return new int[]{groupPosition, childPosition};
    }

    private boolean isGroup(int position) {
        int size = 0;
        for (int i = 0; i < getGroupCount(); i++) {
            if (position == size)
                return true;
            size += getChildCount(i) + 1;
        }
        return false;
    }

    @Override
    public int getItemViewType(int position) {
        if (isGroup(position))
            return GROUP_TYPE;
        else
            return CHILD_TYPE;
    }
}
