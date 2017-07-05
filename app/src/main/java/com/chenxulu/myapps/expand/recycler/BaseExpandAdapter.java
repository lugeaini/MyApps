package com.chenxulu.myapps.expand.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by xulu on 2017/7/4.
 */

public abstract class BaseExpandAdapter extends RecyclerView.Adapter {
    private static final int GROUP_TYPE = 10001;
    private static final int CHILD_TYPE = 10002;

    abstract int getGroupCount();

    abstract int getChildCount(int position);

    @Override
    public int getItemCount() {
        int size = 0;
        for (int i = 0; i < getGroupCount(); i++) {
            size++;
            size += getChildCount(i);
        }
        return size;
    }

    @Override
    public int getItemViewType(int position) {
        if (isGroup(position))
            return GROUP_TYPE;
        else
            return CHILD_TYPE;
    }

    private boolean isGroup(int position) {
        int size = 0;
        for (int i = 0; i < getGroupCount(); i++) {
            if (position == size)
                return true;
            if (size > position)
                break;
            size += getChildCount(i) + 1;
        }
        return false;
    }

    int[] getGroupChildPositions(int position) {
        int groupPosition = 0;
        int childPosition = 0;

        int size = 0;
        for (int i = 0; i < getGroupCount(); i++) {
            if ((position - size) < (getChildCount(i) + 1)) {
                groupPosition = i;
                childPosition = position - size - 1;
                break;
            }
            size += getChildCount(i) + 1;
        }
        System.out.println(position + "," + groupPosition + "," + childPosition);
        return new int[]{groupPosition, childPosition};
    }

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
        int[] positionArray = getGroupChildPositions(position);
        if (holder.getItemViewType() == GROUP_TYPE) {
            onBindGroupViewHolder(holder, positionArray[0]);
        } else {
            onBindChildViewHolder(holder, positionArray[0], positionArray[1]);
        }
    }

    abstract RecyclerView.ViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType);

    abstract RecyclerView.ViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType);

    abstract void onBindGroupViewHolder(RecyclerView.ViewHolder holder, int groupPosition);

    abstract void onBindChildViewHolder(RecyclerView.ViewHolder holder, int groupPosition, int childPosition);

    protected OnChildItemClickListener mChildItemClickListener;
    protected OnGroupItemClickListener mGroupItemClickListener;

    public void setChildItemClickListener(OnChildItemClickListener mChildItemClickListener) {
        this.mChildItemClickListener = mChildItemClickListener;
    }

    public void setGroupItemClickListener(OnGroupItemClickListener mGroupItemClickListener) {
        this.mGroupItemClickListener = mGroupItemClickListener;
    }

    public interface OnChildItemClickListener {
        void onClick(int groupPosition, int childPosition);
    }

    public interface OnGroupItemClickListener {
        void onClick(int groupPosition);
    }
}
