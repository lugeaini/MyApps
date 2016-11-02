package com.chenxulu.myapps.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chenxulu.myapps.R;

import java.util.ArrayList;

/**
 * Created by xulu on 16/8/15.
 */
public class MyMultiAdapter extends BaseAdapter {
    private ArrayList<String> list;

    public MyMultiAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        System.out.println("getCount:" + list.size());
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        System.out.println("getItem:" + position);
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        System.out.println("getItemId:" + position);
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        //System.out.println("getItemViewType:" + position);
        return position % 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("getView:" + position);
        if (getItemViewType(position) == 0) {
            return getTextView1(position, convertView, parent);
        }
        return getTextView2(position, convertView, parent);
    }

    private View getTextView1(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new TextView(parent.getContext());
            int width = convertView.getResources().getDisplayMetrics().widthPixels;
            int height = width / 4;
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(width, height);
            convertView.setBackgroundResource(R.color.alice_blue);
            convertView.setLayoutParams(params);
        }
        ((TextView) convertView).setText(list.get(position));
        return convertView;
    }

    private View getTextView2(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new TextView(parent.getContext());
            int width = convertView.getResources().getDisplayMetrics().widthPixels;
            int height = width / 2;
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(width, height);
            convertView.setBackgroundResource(R.color.aqua);
            convertView.setLayoutParams(params);
        }
        ((TextView) convertView).setText(list.get(position));
        return convertView;
    }


}
