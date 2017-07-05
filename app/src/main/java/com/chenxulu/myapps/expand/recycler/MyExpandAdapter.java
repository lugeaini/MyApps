package com.chenxulu.myapps.expand.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chenxulu.myapps.R;
import com.chenxulu.myapps.model.ClassItem;
import com.chenxulu.myapps.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xulu on 2017/7/4.
 */

public class MyExpandAdapter extends BaseExpandAdapter {
    private Context mContext;
    private List<ClassItem> mList;

    public MyExpandAdapter(Context context, List<ClassItem> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    int getGroupCount() {
        return mList.size();
    }

    @Override
    int getChildCount(int position) {
        return mList.get(position).getStudents().size();
    }

    @Override
    RecyclerView.ViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_expend_group_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.textView = (TextView) view.findViewById(R.id.txt);
        return viewHolder;
    }

    @Override
    RecyclerView.ViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_expend_child_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.textView = (TextView) view.findViewById(R.id.txt);
        return viewHolder;
    }

    @Override
    void onBindGroupViewHolder(RecyclerView.ViewHolder holder, int groupPosition) {
        ClassItem item = mList.get(groupPosition);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.textView.setText(item.getName());
    }

    @Override
    void onBindChildViewHolder(RecyclerView.ViewHolder holder, int groupPosition, int childPosition) {
        Student item = mList.get(groupPosition).getStudents().get(childPosition);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.textView.setText(item.getName());
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
