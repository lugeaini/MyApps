package com.chenxulu.library.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class BaseLibraryAdapter<E> extends BaseAdapter {
    protected ArrayList<E> list;
    protected Context context;

    public BaseLibraryAdapter(Context context, ArrayList<E> list) {
        this.context = context;
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
        System.out.println("getViewTypeCount:");
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        System.out.println("getItemViewType:");
        return super.getItemViewType(position);
    }
}
