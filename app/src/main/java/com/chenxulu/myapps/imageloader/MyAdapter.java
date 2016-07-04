package com.chenxulu.myapps.imageloader;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by xulu on 16/6/27.
 */
public class MyAdapter extends BaseAdapter {
    private ArrayList<String> list;

    public MyAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = new TextView(viewGroup.getContext());
            int width = view.getResources().getDisplayMetrics().widthPixels;
            int height = width / 4;
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(width, height);
            view.setLayoutParams(params);
        }
        ((TextView) view).setText(list.get(i));
        return view;
    }

    class ViewHolder {
        ImageView icon;
        TextView title;
        TextView content;
    }
}
