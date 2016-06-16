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
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
