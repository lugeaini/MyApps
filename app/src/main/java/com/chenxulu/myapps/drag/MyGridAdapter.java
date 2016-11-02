package com.chenxulu.myapps.drag;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chenxulu.library.utils.DeviceUtil;

import java.util.List;

/**
 * Created by xulu on 2016/10/9.
 */

public class MyGridAdapter extends BaseAdapter implements DragGridViewListener {
    List<String> mList;
    int hidePosition = -1;

    public MyGridAdapter(List<String> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //System.out.println("getView:" + position);
        int displayWidth = DeviceUtil.getDisplayWidth(parent.getContext());

        TextView textView = new TextView(parent.getContext());
        textView.setWidth(displayWidth / 3);
        textView.setHeight(displayWidth / 3);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(Color.GRAY);

        textView.setText(mList.get(position));
        textView.setVisibility(hidePosition == position ? View.INVISIBLE : View.VISIBLE);
        return textView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        System.out.println("notifyDataSetChanged");
    }

    @Override
    public void reorderItems(int oldPosition, int newPosition) {
        String item = mList.get(oldPosition);
        mList.remove(oldPosition);
        mList.add(newPosition, item);
    }

    @Override
    public void setHideItem(int hidePosition) {
        this.hidePosition = hidePosition;
        notifyDataSetChanged();
    }

}
